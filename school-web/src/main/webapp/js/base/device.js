$(function () {
    $("#jqGrid").jqGrid({
        url: '../device/list',
        datatype: "json",
        colModel: [			
			{ label: 'id', name: 'id', index: 'id', width: 50, key: true,hidden : true  },
			{ label: '楼栋', name: 'building.name',   width: 80 }, 			
			{ label: '房间', name: 'room.name',   width: 80 }, 			
			{ label: '用电规则', name: 'rule.name',   width: 80 }, 			
			{ label: '电表名称', name: 'name', index: 'name', width: 80 }, 			
			{ label: '电表号', name: 'code', index: 'code', width: 80 }, 	
			{ label: '状态', name: 'status',  width: 80, formatter: function(value, options, row){
				return value === '01' ? 
					'<span class="label label-success">通电</span>' : 
					'<span class="label label-danger">停电</span>';
			}},
			{ label: '电价', name: 'price', index: 'price', width: 80 }, 			
			{ label: '是否允许欠费', name: 'isover',  width: 80, formatter: function(value, options, row){
				return value === 'Y' ? 
					'<span class="label label-success">允许欠费</span>' : 
					'<span class="label label-danger">不许欠费</span>';
			}},
			{ label: '最近电量', name: 'lateuse', index: 'lateuse', width: 80 }, 			
			{ label: '最近记录时间', name: 'latetime', index: 'latetime', width: 80 }, 			
			{ label: '参数', name: 'paraname', index: 'paraname', width: 150 }, 			
			{ label: '单线双线', name: 'isdouble',  formatter: function(value, options, row){
				return value === '1' ? 
						'<span class="label label-success">单线</span>' : 
						'<span class="label label-danger">双线</span>';
				}}, 			
			{ label: '可用电量', name: 'freeuse', index: 'freeuse', width: 80 }, 			
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
		buildings: null,
		rooms: null,
		ruleids: null,
		device: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增电表";
			vm.device = {isover:"Y",isdouble:"1" };	 
			vm.getSelect();
		},
		update: function (event) {
			var id = getSelectedRow();
			if(id == null){
				return ;
			}
			location.href = "deviceUpdate.html?deviceid="+id;
		},
		setChange: function (event) {
			var id = getSelectedRow();
			if(id == null){
				return ;
			}
			location.href = "deviceChange.html?deviceid="+id;
		},
		setPrice: function (event) {
			location.href = "deviceSetPrice.html?";
		},
		setRule: function (event) {
			location.href = "deviceSetRule.html?";
		},
		setFree: function (event) {
			location.href = "deviceSetFree.html?";
		},
		setRun: function (event) {
			location.href = "deviceSetRun.html?";
		},
		devicekwh: function (event) {
			var id = getSelectedRow();
			if(id == null){
				return ;
			}
			location.href = "devicekwh.html?deviceid="+id;
		},
		getSelect: function(){
			$.ajax({
				type: "get",
				url: "../device/getSelect",
				async: false,
				success: function(r) {
					vm.ruleids=r.ruleList;
					vm.buildings=r.buildingList;
					vm.rooms=r.roomList;
					console.log(r.ruleList);
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
		saveOrUpdate: function (event) {
			if(dateCheck()){
			var url = vm.device.id == null ? "../device/save" : "../device/update";
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.device),
			    success: function(r){
			    	if(r.code === 0){
						alert('操作成功', function(index){
							vm.reload();
						});
					}else{
						alert(r.msg);
					}
				}
			});	 }
		},
		del: function (event) {
			var ids = getSelectedRows();
			if(ids == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: "../device/delete",
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
			$.get("../device/info/"+id, function(r){
                vm.device = r.device;
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
	var buildingId=$('#buildingId').val();
	if (buildingId == ''||buildingId==null)
	{ 
		tips("楼栋不能为空！",'#buildingId');  return false;
	}
	var roomid=$('#roomid').val();
	if (roomid == ''||roomid==null)
	{ 
		tips("房间不能为空！",'#roomid');  return false;
	}
	var ruleid=$('#ruleid').val();
	if (ruleid == ''||ruleid==null)
	{ 
		tips("用电规则不能为空！",'#ruleid');  return false;
	}
	var name=$('#name').val();
	if (name == ''||name ==null)
	{ 
		tips("电表名称不能为空！",'#name');  return false;
	}
	var code=$('#code').val();
	if (code == ''||code==null)
	{ 
		tips("电表号不能为空！",'#code');  return false;
	}
	var reg = /^[0-9]+(.[0-9]{1,2})?$/;
	var lateuse=$('#lateuse').val();
	if (lateuse == ''||lateuse==null)
	{ 
		tips("当前电量不能为空！",'#lateuse');  return false;
	}
	if (!reg.test($("#lateuse").val())) {
		tips("当前电量只能为2位小数位的数字！",'#lateuse');  return false;
		return false;
	}
	var freeuse=$('#freeuse').val();
	if (freeuse == ''||freeuse==null)
	{ 
		tips("可用电量不能为空！",'#freeuse');  return false;
	}
	if (!reg.test($("#freeuse").val())) {
		tips("可用电量只能为2位小数位的数字！",'#freeuse');  return false;
		return false;
	}
	var price=$('#price').val();
	if (price != ''&&price!=null)
	{ 
		if (!reg.test($("#price").val())) {
			tips("可用电量只能为2位小数位的数字！",'#price');  return false;
			return false;
		}
	}
    return true;
}