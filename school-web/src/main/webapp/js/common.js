//jqGrid的配置信息
$.jgrid.defaults.width = 1000;
$.jgrid.defaults.responsive = true;
$.jgrid.defaults.styleUI = 'Bootstrap';
Vue.use(VueForm);
//工具集合Tools
window.T = {};
//同步
$.ajaxSetup({  
    async : false  
});
// 获取请求参数
// 使用示例
// location.href = http://localhost:8080/index.html?id=123
// T.p('id') --> 123;
var url = function(name) {
	var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
	var r = window.location.search.substr(1).match(reg);
	if(r!=null)return  unescape(r[2]); return null;
};
T.p = url;

//全局配置
$.ajaxSetup({
	dataType: "json",
	contentType: "application/json",
	cache: false
});

//重写alert
window.alert = function(msg, callback){
	console.log(msg);
	parent.layer.alert(msg, function(index){
		parent.layer.close(index);
		if(typeof(callback) === "function"){
			callback("ok");
		}
	});
}


//重写confirm式样框
window.tips = function(msg, id){
	layer.tips(msg, $(id),{tips: [4, '#1ab394'],time: 2000});
	$(id).focus();
}



//重写confirm式样框
window.confirm = function(msg, callback){
	parent.layer.confirm(msg, {btn: ['确定','取消']},
	function(){//确定事件
		if(typeof(callback) === "function"){
			callback("ok");
		}
	});
}

//选择一条记录
function getSelectedRow() {
    var grid = $("#jqGrid");
    var rowKey = grid.getGridParam("selrow");
    if(!rowKey){
    	alert("请选择一条记录");
    	return ;
    }
    
    var selectedIDs = grid.getGridParam("selarrrow");
    if(selectedIDs.length > 1){
    	alert("只能选择一条记录");
    	return ;
    }
    
    return selectedIDs[0];
}

//选择多条记录
function getSelectedRows() {
    var grid = $("#jqGrid");
    var rowKey = grid.getGridParam("selrow");
    if(!rowKey){
    	alert("请选择一条记录");
    	return ;
    }
    
    return grid.getGridParam("selarrrow");
}

//注册弹出方法
function layer_show(title, url, id, w, h) {
  if(title == null || title == '') {
    title = false;
  };
  if(w == null || w == '') {
    w = 800;
  };
  if(h == null || h == '') {
    h = ($(window).height() - 300);
  };
  layer.open({
    type: 2,
    offset: ['100px','250px'],
    area: [w + 'px', h + 'px'],
    fixed: false, //不固定
    shadeClose: false, //开启遮罩关闭
    maxmin: true,
	title: [
		  	 title,
	         'background-color: #009688; color:#fff;'
	       ],
    content: url
  });
};
 