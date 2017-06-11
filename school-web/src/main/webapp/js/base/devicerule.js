$(function () {
    $("#jqGrid").jqGrid({
        url: '../devicerule/list',
        datatype: "json",
        colModel: [			
			{ label: 'id', name: 'id', index: 'id', width: 50, key: true,hidden : true  },
			{ label: '规则名称', name: 'name', index: 'name', width: 80 }, 			
					
			{ label: '开始时间', name: 'begintime', index: 'begintime', width: 80 }, 			
			{ label: '备注', name: 'remarks', index: 'remarks', width: 200 }			
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
		devicerule: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.devicerule = {};
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
			vm.devicerule.begintime=$("#begintime").val();
			var url = vm.devicerule.id == null ? "../devicerule/save" : "../devicerule/update";
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.devicerule),
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
		updateItem: function (event) {
			var id = getSelectedRow();
			if(id == null){
				return ;
			} 
			location.href = "ruleitem.html?ruleid="+id;
		},
		getInfo: function(id){
			$.get("../devicerule/info/"+id, function(r){
                vm.devicerule = r.devicerule;
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