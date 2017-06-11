$(function () {
	 console.log(T.p("ruleid"));
	 var ruleid=T.p("ruleid");
    $("#jqGrid").jqGrid({
        url: '../ruleitem/list?ruleid='+ruleid,
        datatype: "json",
        colModel: [			
			{ label: 'id', name: 'id', index: 'id', width: 50, key: true,hidden : true  },
			{ label: '用电规则ID', name: 'ruleid', index: 'ruleId', width: 50 ,hidden : true }, 			
			{ label: '日期类型', name: 'datetype', index: 'datetype', width: 50 ,hidden : true }, 			
			{ label: '时段序号', name: 'orderno', index: 'orderno',hidden : true  }, 			
			{ label: '时段', name: 'ruletime', index: 'ruletime', width: 50 }, 			
			{ label: '上限功率', name: 'maxkw', index: 'maxKw', editable:true,editrules:{number:true, required:true},editoptions: {size:10, maxlength: 10},width: 50 },
			{ label: '日期类型', name: 'datetype', index: 'datetype', width: 200 ,
				formatter : function(value, options, row)
				{
					return value === '1' ? '<span class="label label-danger">休息日</span>'
							: '<span class="label label-success">工作日</span>';
				}}			
        ],
		viewrecords: true,
        height: 385,
        rowNum: 50,
		rowList : [10,30,50,100,200],
        rownumbers: true, 
        rownumWidth: 25, 
        autowidth:false,
        multiselect: false,
        cellEdit:true,
        cellsubmit : 'remote',
        cellurl : '../ruleitem/update',
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
        afterSaveCell :function(rowid, cellname, value, iRow, iCol){
       	 vm.ruleitem.id=rowid;
       	 vm.ruleitem.maxkw=value;
    		$.ajax({
				type: "POST",
			    url: "../ruleitem/updateMaxkw",
			    async: false,
			    data: JSON.stringify(vm.ruleitem),
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
		ruleitem: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.ruleitem = {};
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
			var url = vm.ruleitem.id == null ? "../ruleitem/save" : "../ruleitem/update";
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.ruleitem),
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
				    url: "../ruleitem/delete",
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
			$.get("../ruleitem/info/"+id, function(r){
                vm.ruleitem = r.ruleitem;
            });
		},
		reBack: function(){
			location.href = "devicerule.html";
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