$(function()
{
	$("#jqGrid").jqGrid(
	{
		url : '../userrule/list',
		datatype : "json",
		colModel : [
		{
			label : 'id',
			name : 'id',
			index : 'id',
			width : 50,
			key : true,
			hidden : true
		},
		{
			label : '规则名称',
			name : 'name',
			index : 'name',
			width : 80
		},
		{
			label : '开始时间',
			name : 'begintime',
			index : 'begintime',
			width : 80
		},
		{
			label : '备注',
			name : 'remarks',
			index : 'remarks',
			width : 80
		} ],
		viewrecords : true,
		height : 385,
		rowNum : 20,
		rowList : [ 20, 30, 50, 100, 200 ],
		rownumbers : true,
		rownumWidth : 25,
		autowidth : true,
		multiselect : true,
		pager : "#jqGridPager",
		jsonReader :
		{
			root : "page.list",
			page : "page.currPage",
			total : "page.totalPage",
			records : "page.totalCount"
		},
		prmNames :
		{
			page : "page",
			rows : "limit",
			order : "order"
		},
		gridComplete : function()
		{
			// 隐藏grid底部滚动条
			$("#jqGrid").closest(".ui-jqgrid-bdiv").css(
			{
				"overflow-x" : "hidden"
			});
		}
	});
});

function query()
{
	reload();
}
function add()
{
	location.href = 'userruleAdd.html';

}
function update(event)
{
	var id = getSelectedRow();
	if (id == null)
	{
		return;
	}
	vm.showList = false;
	vm.title = "修改";

	vm.getInfo(id)
}
function saveOrUpdate(id)
{
	console.log($('#userruleForm').serialize());
	$.ajax(
	{
		type : "POST",
		url : "../userrule/save?" + $('#userruleForm').serialize(),
		success : function(r)
		{
			console.log(r);
			if (r.code === 0)
			{
				console.log("操作成功");
			//	alert('操作成功');
				location.href = 'userrule.html';		 
			} else
			{
				parent.layer.alert("r.msg", function(index){});
				//alert(r.msg);
			}
		}
	});
}
function del(event)
{
	var ids = getSelectedRows();
	if (ids == null)
	{
		return;
	}

	confirm('确定要删除选中的记录？', function()
	{
		$.ajax(
		{
			type : "POST",
			url : "../userrule/delete",
			data : JSON.stringify(ids),
			success : function(r)
			{
				if (r.code == 0)
				{
					alert('操作成功', function(index)
					{
						$("#jqGrid").trigger("reloadGrid");
					});
				} else
				{
					alert(r.msg);
				}
			}
		});
	});
}
function getInfo(id)
{
	$.get("../userrule/info/" + id, function(r)
	{
	});
}
function reload(event)
{
	location.href = 'userrule.html';
}
