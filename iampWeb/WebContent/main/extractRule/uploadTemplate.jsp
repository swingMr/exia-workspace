<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="gbk"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>自动生成结构化数据抽取规则</title>
</head>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="format-detection" content="telephone=no">
<link rel="stylesheet" href="/iamp/js/layui/css/layui.css" media="all" />
<link rel="stylesheet" href="/iamp/js/layui/css/global.css" media="all">
<script src="/iamp/js/jquery-1.7.2.min.js"></script>
<script src="/iamp/js/layui/layui.js" ></script>
<style>
.layui-elem-field legend{font-size: 16px}
</style>
<body>
<div class="layui-layout">
	<p style="margin-left:20px;margin-bottom:10px;"><strong><label style="color:red;">&nbsp;温馨提示：&nbsp;&nbsp;&nbsp;&nbsp;目前仅支持通过Word模板生成规则</label></strong></p>
	<form class="layui-form" id="form" action="" method="post" enctype="multipart/form-data">
	  <div class="layui-form-item">  
	    <label class="layui-form-label"><span style="color:red;">*</span>选择文件：</label>
	    <div class="layui-input-block">
	      <input type="text" id="fileInput" style="width:380px;" class="layui-input">
	      <input type="file" id="file" name="file" style="display:none;" accept="*.doc">
	      <input type="hidden" name="genreId" value="${genreId}">
	      <input type="hidden" name="genreName" value="${genreName}">
	    </div>
	  </div>
	  <div class="layui-form-item">
	    <label class="layui-form-label"><span style="color:red;">*</span>支持格式：</label>
	    <div class="layui-input-block">
	      <input type="radio" name="fileType" value="DOC/DOCX" title="DOC/DOCX" checked>
      	  <input type="radio" name="fileType" value="PDF" title="PDF" >
      	  <input type="radio" name="fileType" value="TXT" title="TXT" >
      	  <input type="radio" name="fileType" value="HTML" title="HTML" >
	    </div>
	  </div>
    </form>
    <hr/>
    <center>
		<button class="layui-btn layui-btn layui-btn-small foot" id="save">确定</button>
		<button class="layui-btn layui-btn-danger layui-btn-small foot" id="cancel">取消</button>
	</center>
</div>
</body>
<script>
var layer;
var element;
var form;
//一般直接写在一个js文件中
layui.use(['element','layer','form'], function(){
  element = layui.element();
  form = layui.form();
  layer = layui.layer;
});

$('#fileInput').bind('click', function(){
	$("#file").trigger("click");
});

$('#file').change(function(){
	 var f = document.getElementById("file").files;  
	  //名称  
	 $('#fileInput').val(f[0].name);
});

$('#save').bind('click', function(){
	 var f = document.getElementById("file").files;  
	 if(f!=null && f.length>0) {
		 var formData = new FormData($("#form")[0]);  
	     $.ajax({
	          url: '/iamp/extractRule/extractInfo',  
	          type: 'POST',  
	          data: formData,
	          contentType: false,
	          processData: false,
	          async: false,
	          dataType: 'json',
	          success: function (data) { 
	        	  console.log(data);
	        	  parent.reload();
	        	  closeCurrentWin();
	          }
	     })
	 } else {
		 layer.msg('请选择文件！',{time: 1000});
			return;
	 }
});

$("#cancel").on("click",function(){
	closeCurrentWin();
});

function closeCurrentWin(){
   var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
   parent.layer.close(index);
}

</script>

</html>