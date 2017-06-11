$(function () {
    $("#jqGrid").jqGrid({
        url: '../room/list',
        datatype: "json",
        colModel: [			
			{ label: 'id', name: 'id', index: 'id', width: 50, key: true,hidden : true  },
			{ label: '楼栋名称', name: 'building.name', sortable:false, width: 80 }, 			
			{ label: '房间编号', name: 'code', index: 'code', width: 80 }, 			
			{ label: '房间名称', name: 'name', index: 'name', width: 80 }, 			
			{ label: '电表编号', name: 'device.code', width: 80 }, 			
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
		buildingList: null,  
		room: {}
	},
	
	methods: {
		query: function () {
			vm.reload();
		},		
		getBuildingList: function(){
			//加载 
			$.get("../building/select", function(r){
		         console.log(r.buildingList);
		         vm.buildingList=r.buildingList;
			})
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增房间";
			vm.getBuildingList();
			vm.room = {};
		},
		update: function (event) {
			var id = getSelectedRow();
			if(id == null){
				return ;
			}
			vm.showList = false;
            vm.title = "修改房间";
            vm.getInfo(id);
            vm.getBuildingList();
		},
		saveOrUpdate: function (event) {
			if(dateCheck()){
			 console.log(JSON.stringify(vm.room));
			var url = vm.room.id == null ? "../room/save" : "../room/update";
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.room),
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
				    url: "../room/delete",
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
			$.get("../room/info/"+id, function(r){
                vm.room = r.room;
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
	var buildingid=$('#buildingid').val();
	 console.log(buildingid);
	if (buildingid == ''||buildingid == null)
	{ 
		tips("楼栋不能为空！",'#buildingid');  return false;
	}
	var code=$('#code').val();
	if (code == ''||code == null)
	{ 
		tips("房间编号不能为空！",'#code');  return false;
	}
	var name=$('#name').val();
	if (name == ''||name == null)
	{ 
		tips("房间名称不能为空！",'#name');  return false;
	}
    return true;
}