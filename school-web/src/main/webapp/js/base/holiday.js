$(function () {
    $("#jqGrid").jqGrid({
        url: '../holiday/list',
        datatype: "json",
        colModel: [			
			{ label: 'id', name: 'id', index: 'id', width: 50, key: true,hidden : true  },
			{ label: '日期', name: 'holiday', index: 'holiday', width: 80,formatter:'showlink',formatoptions:{baseLinkUrl:'../holiday/goHoliday',idName: "id", addParam:"&holiday="}}, 			
			{ label: '星期', name: 'week', index: 'week', width: 80 }, 			
			{ label: '是否节假日', name: 'isholiday',index: 'isholiday', width: 80, formatter: function(value, options, row){
				return value === 'Y' ? 
					'<span class="label label-danger">休息日</span>' : 
					'<span class="label label-success">工作日</span>';
			}}
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
		q:{name: null},
		showList: true,
		addList: true,
		title: null,
		holiday: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){ 	
		vm.showList = false;
		vm.addList = false;
		vm.title = "新增节假日";
		vm.holiday = {isholiday:"N"};
		},
		update: function (event) {
			console.log(event);
				var ids = getSelectedRows();
				if(ids == null){
					return ;
				}
				console.log(ids);
				var url = event == 1 ? "../holiday/updateYes" : "../holiday/updateNo";
				confirm('确定启用选中的记录？', function(){
					$.ajax({
						type: "POST",
						url: url,
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
		saveOrUpdate: function (event) {
			vm.holiday.holiday=$("#holiday").val();
			vm.holiday.endholiday=$("#endholiday").val();
			var url = vm.holiday.id == null ? "../holiday/save" : "../holiday/update";
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.holiday),
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
				    url: "../holiday/delete",
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
			$.get("../holiday/info/"+id, function(r){
                vm.holiday = r.holiday;
            });
		},
		reload: function (event) {
			vm.showList = true;
			vm.addList = true;
			vm.q.name=$("#nameId").val();
			 console.log($("#nameId").val());
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{ 
		 
				postData:{'name': vm.q.name},
                page:page
            }).trigger("reloadGrid");
		}
	}
});