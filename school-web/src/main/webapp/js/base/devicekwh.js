$(function () {
var deviceid=	T.p("deviceid");
    $("#jqGrid").jqGrid({
        url: '../devicekwh/list?deviceid='+deviceid,
        datatype: "json",
        colModel: [			
			{ label: 'id', name: 'id', index: 'id', width: 50, key: true,hidden : true  },
			{ label: '电表名称', name: 'devicename', index: 'devicename', width: 80 }, 			
			{ label: '电表号', name: 'devicecode', index: 'devicecode', width: 80 }, 			
			{ label: '操作类型', name: 'optype', index: 'optype', width: 80 }, 			
			{ label: '操作前（可用电量）', name: 'befkwh', index: 'befKwh', width: 80 }, 			
			{ label: '增减数', name: 'changekwh', index: 'changeKwh', width: 80 }, 			
			{ label: '操作后（可用电量）', name: 'aftkwh', index: 'aftKwh', width: 80 }, 			
			{ label: '操作人', name: 'opuse', index: 'opuse', width: 80 }, 			
			{ label: '操作时间', name: 'optime', index: 'optime', width: 80 }, 			
			{ label: '备注', name: 'remarks', index: 'remarks', width: 80 }			
        ],
		viewrecords: true,
        height: 385,
        rowNum: 10,
		rowList : [10,30,50,100,200],
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
		q:{name: null},
		showList: true,
		title: null,
		devicekwh: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.devicekwh = {};
		},
		goBack: function(){
			location.href = "device.html";
		},
		update: function (event) {
			var id = getSelectedRow();
			if(id == null){
				return ;
			}
			vm.showList = false;
            vm.title = "修改";
            
            vm.getInfo(id)
		},
		saveOrUpdate: function (event) {
			var url = vm.devicekwh.id == null ? "../devicekwh/save" : "../devicekwh/update";
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.devicekwh),
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
				    url: "../devicekwh/delete",
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
			$.get("../devicekwh/info/"+id, function(r){
                vm.devicekwh = r.devicekwh;
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