$(function () {
    $("#jqGrid").jqGrid({
        url: '../person/list',
        datatype: "json",
        colModel: [			
			{ label: 'id', name: 'id', index: 'id', width: 50, key: true,hidden : true  },
			{ label: '楼栋', name: 'building.name',sortable:false, width: 80 }, 			
			{ label: '房间', name: 'room.name',sortable:false,  width: 80 }, 			
			{ label: '名称', name: 'name', index: 'name', width: 80 }, 			
			{ label: '学号', name: 'code', index: 'code', width: 80 }, 			
			{ label: '手机号码', name: 'mobile', index: 'mobile', width: 80 }, 			
			{ label: '身份证号', name: 'idnumber', index: 'idnumber', width: 80 }, 			
			{ label: '状态', name: 'status', width: 80, formatter: function(value, options, row){
				return value === '0' ? 
					'<span class="label label-danger">禁用</span>' : 
					'<span class="label label-success">正常</span>';
			}},
			{ label: '备注', name: 'remarks', index: 'remarks', width:200 } 
        ],
		viewrecords: true,
        height: 385,
        rowNum: 20,
		rowList : [20,30,50,100,200],
        rownumbers: true, 
        rownumWidth: 25, 
        autowidth:true,
        multiselect: true,
        pager: "#jqGridPager",
        jsonReader : {
            root: "page.list",
            page: "page.currPage",
            total: "page.totalPage",
            records: "page.totalCount"
        },
        prmNames : {
            page:"page", 
            rows:"limit", 
            order: "order"
        },
        gridComplete:function(){
        	//隐藏grid底部滚动条
        	$("#jqGrid").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "hidden" }); 
        }
    });
});
var vm = new Vue({
	el:'#rrapp',
	data:{
		showList: true,
		title: null,
		q:{name: null},
		buildings: null,
		rooms: null,
		person: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		getSelect: function(){
			$.ajax({
				type: "get",
				url: "../person/getSelect",
				async: false,
				success: function(r) {
					vm.buildings=r.buildingList;
					vm.rooms=r.roomList;
					console.log(r.buildingList);
				}
			});
			
		},
		changeBuild: function(e){
		    console.log(e.target.value);
		    var buildingId =e.target.value;
			//加载 
			$.get("../room/select?buildingId="+buildingId, function(r){
				vm.rooms=r.roomList;
			})
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增住户（学生）";
			vm.person = {};
			vm.getSelect();
		},
		update: function (event) {
			var id = getSelectedRow();
			if(id == null){
				return ;
			}
			vm.showList = false;
            vm.title = "修改住户（学生）";
            vm.getInfo(id);
        	vm.getSelect();
		},
		saveOrUpdate: function (event) {
			var url = vm.person.id == null ? "../person/save" : "../person/update";
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.person),
			    success: function(r){
			    	if(r.code === 0){
						alert('操作成功', function(index){
							vm.reload();
						});
					}else{
						alert(r.msg);
					}
				}
			});
		},
		del: function (event) {
			var ids = getSelectedRows();
			if(ids == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: "../person/delete",
				    data: JSON.stringify(ids),
				    success: function(r){
						if(r.code == 0){
							alert('操作成功', function(index){
								$("#jqGrid").trigger("reloadGrid");
							});
						}else{
							alert(r.msg);
						}
					}
				});
			});
		},
		setStatusOk: function (event) {
			var ids = getSelectedRows();
			if(ids == null){
				return ;
			}
			confirm('确定启用选中的记录？', function(){
				$.ajax({
					type: "POST",
					url: "../person/setStatusOk",
					data: JSON.stringify(ids),
					success: function(r){
						if(r.code == 0){
							alert('操作成功', function(index){
								$("#jqGrid").trigger("reloadGrid");
							});
						}else{
							alert(r.msg);
						}
					}
				});
			});
		},
		setStatusNo: function (event) {
			var ids = getSelectedRows();
			if(ids == null){
				return ;
			}
			confirm('确定禁用选中的记录？', function(){
				$.ajax({
					type: "POST",
					url: "../person/setStatusNo",
					data: JSON.stringify(ids),
					success: function(r){
						if(r.code == 0){
							alert('操作成功', function(index){
								$("#jqGrid").trigger("reloadGrid");
							});
						}else{
							alert(r.msg);
						}
					}
				});
			});
		},
		getInfo: function(id){
			$.get("../person/info/"+id, function(r){
                vm.person = r.person;
            });
		},
		reload: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{ 
				postData:{'name': vm.q.name},
                page:page
            }).trigger("reloadGrid");
		}
	} 
});