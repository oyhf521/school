$(function () {
    $("#jqGrid").jqGrid({
        url: '../building/list',
        datatype: "json",
        colModel: [			
			{ label: 'id', name: 'id', index: 'id', width: 50, key: true,hidden : true  },
			{ label: '编号', name: 'code', index: 'code', width: 80 }, 			
			{ label: '名称', name: 'name', index: 'name', width: 80 }, 			
			{ label: '地址', name: 'adress', index: 'adress', width: 80 }, 			
			{ label: '备注', name: 'remark', index: 'remark', width: 200 }			
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
		title: null,
		building: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增楼栋";
			vm.building = {};
		},
		update: function (event) {
			var id = getSelectedRow();
			if(id == null){
				return ;
			}
			vm.showList = false;
            vm.title = "修改楼栋";
            
            vm.getInfo(id);
      
		},
		saveOrUpdate: function (event) {
			console.log(JSON.stringify(vm.building));
			if(dateCheck()){
			var url = vm.building.id == null ? "../building/save" : "../building/update";
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.building),
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
			}
		},
		del: function (event) {
			var ids = getSelectedRows();
			if(ids == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: "../building/delete",
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
			$.get("../building/info/"+id, function(r){
                vm.building = r.building;
                console.log(r.building);
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

function dateCheck()
{
	var code=$('#code').val();
	if (code == '')
	{ 
		tips("编码不能为空！",'#code');  return false;
	}
	var name=$('#name').val();
	if (name == '')
	{ 
		tips("名称不能为空！",'#name');  return false;
	}
    return true;
}