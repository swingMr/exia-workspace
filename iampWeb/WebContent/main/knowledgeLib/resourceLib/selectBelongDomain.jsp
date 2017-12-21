<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>设置归属领域</title>
</head>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="format-detection" content="telephone=no">
<link rel="stylesheet" href="/iamp/js/layui-v2/css/layui.css" media="all" />
<link rel="stylesheet" href="/iamp/js/layui-v2/css/global.css" media="all">
<script src="/iamp/js/jquery-1.7.2.min.js"></script>
<script src="/iamp/js/jquery.form.js"></script>
<script type="text/javascript" src="/iamp/js/layui-v2/layui.js"></script>
<body>
	<div>	
 	<form class="layui-form" action="" id="form" enctype="multipart/form-data" method="post">
    		<div class="layui-form-item" style="margin-top:20px">
    		<label class="layui-form-label"><font color="red">*</font>归属领域：</label>
		    <div class="layui-input-inline" style="width:425px">
		      <input type="text" id="belongDomain" name="belongDomain" required lay-verify="required" placeholder="请输入领域" autocomplete="off" class="layui-input">
		      <input type="hidden" id="belongDomainId" name="belongDomainId"/>
		    </div>
		    <div class="layui-form-mid layui-word-aux"><span class="layui-btn btn layui-btn-small" id="selectDomain">选择</span></div>
		    </div>
	</form>
	</div> 
</body>
<script type="text/javascript">
	//获取浏览器页面可见高度和宽度
	var _PageHeight = document.documentElement.clientHeight,
  _PageWidth = document.documentElement.clientWidth;
//计算loading框距离顶部和左部的距离（loading框的宽度为215px，高度为61px）
	var _LoadingTop = _PageHeight > 61 ? (_PageHeight - 61) / 2 : 0,
  _LoadingLeft = _PageWidth > 215 ? (_PageWidth - 215) / 2 : 0;
  var layer;
	layui.use(['layer','laypage','form','element'], function(){
		var form = layui.form;
		var element = layui.element;
		  layer = layui.layer;
		  window.layer = layer;
	});
  $("#selectDomain").on("click",function(){
		parent.layer.open({
		  type: 2,
		  title: '选择领域',
  		  shade: false,
          area: ['350px', '600px'],
		  content: '/iamp/main/datadig/domainTree.jsp',
		  btn: ['确定', '取消'],
		  yes: function(index, layero){
		     var body = layer.getChildFrame('body', index);
		     var iframeHtml = layer.getChildFrame('body');
		     var treeObj = $(layero).find("iframe")[0].contentWindow.treeObj;
		     var sNodes = treeObj.getCheckedNodes(true);
		     var domainNames = "";
		     var domainIds = "";
		     for(var i=0;i<sNodes.length;i++){
		     	var domianName = sNodes[i].name;
		     	var domianId = sNodes[i].id;
		     	if(domainNames){
		     		domainNames += ";";
		     	}
		     	if(domainIds){
		     		domainIds += ";";
		     	}
		     	domainNames += domianName;
		     	domainIds += domianId;
		     }
		     $("#belongDomain").val(domainNames);
		     $("#belongDomainId").val(domainIds);
		     parent.layer.close(index);
		  },
		  btn2: function(index, layero){
		    parent.layer.close(index);
		  }
		});
	});
</script>
</html>