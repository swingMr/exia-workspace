<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>关键词和摘要</title>
</head> 
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="format-detection" content="telephone=no">
<link rel="stylesheet" href="/iamp/js/layui/css/layui.css" media="all" />
<link rel="stylesheet" href="/iamp/js/layui/css/global.css" media="all">
<script type="text/javascript" src="/iamp/js/layui/layui.js"></script>
<script type="text/javascript" src="/iamp/js/loading.js"></script>
<script src="/iamp/js/jquery-1.7.2.min.js"></script>
<script src="/iamp/js/jquery.form.js"></script>
<style type="text/css">
.foot{
	position:relative;
	bottom:5px;  
	overflow:hidden;
}
</style>
<body>	
	<table lay-skin="nob" class="layui-table" >
	  <colgroup>
	    <col width="150">
	    <col width="200">
	    <col>
	  </colgroup>
		 <tr>
		  	<td >
				<label class="layui-form-label"><span style="color:red">*</span>关键词个数:</label>
			    <div class="layui-input-block" style="width:40%">
			      <input type="text" id="keyWordLimit"  name="keyWordLimit" required  value="20" lay-verify="required"  autocomplete="off" class="layui-input">
			    </div>
	    	</td>
		  	<td >
				<label class="layui-form-label"><span style="color:red">*</span>摘要长度:</label>
			    <div class="layui-input-block"  style="width:40%">
			      <input type="text" id="abstractLength" name="abstractLength" required value="256" lay-verify="required"  autocomplete="off" class="layui-input">
			    </div>
	    	</td>
    	</tr>
	</table>
	
	<div class="site-tips">
		<form target="layui-upload-iframe" method="post" key="set-mine" id="form" enctype="multipart/form-data" action="/iamp/fileHander/getFileText.do">
        <label class="layui-form-label">选择文件：</label>
        <input type="file" name="file" id="demo-upload-unwrap" >
		<div class="layui-btn-group">
		  <span id="upload" class="layui-btn layui-btn-normal">上传</span>
		  <span id="fenci" class="layui-btn layui-btn-normal">提取</span>
		</div>
		 </form>
     </div>
    
   	<div class="layui-form">
		<div class="site-tips" style="margin-top:10px">
	        	文本内容
	     </div>
	     <div style="padding:10px">
	     	<textarea id="fileText" name="fileText" placeholder="文本内容" class="layui-textarea" style="height:170px;"></textarea>
	     </div>
		<div class="site-tips" style="margin-top:10px">
	        	提取结果<span style="margin-left:20px"></span>
	     </div>  
	   	<table style="width:100%;height:220px">
				<tr>
					<td style="width:20%;padding-left:10px">
						<div  style="background-color:#EFF7EF;height:30px;line-height:30px">
				        	<a>关键词</a>
				     	</div>
						<div style="overflow:auto;">
							<div class="layui-collapse" >
			 					 <div class="layui-colla-item">
			    					<div id="keyWords" class="layui-colla-content layui-show" style="overflow:auto;height:200px;">
			    					</div>
			 					 </div>
							</div>
					    </div>
					</td>
							    
					<td style="width:80%;padding-left:40px">
						<div  style="background-color:#EFF7EF;height:30px;line-height:30px">
				        	<a>摘要内容</a>
				     	</div>
				       <div>
				         <textarea style="height:220px"id="abstractContent" placeholder="摘要内容" class="layui-textarea" ></textarea>
				       </div>
			   		</td>
		   		 </tr>
	   		 </table>
   		</div>
</body>
<script>
var layer;
$(function(){
	/*layUI相关操作 有些需要依赖才能使用  */
	layui.use(['form','element','layer'], function(){
		var element = layui.element();
		var form = layui.form();
		var layer = layui.layer;
		window.element = element;
		window.form = form;
		window.layer = layer;
		/* 根据服务类型判断 是否显示维护脚本按钮 */
		form.on('select(serviceType)', function(data){
			 var val =  data.value;
			if(val == "2"){
				$("#script").show();
			}else{
				$("#script").hide();
			}
		});
	});
	
	$("#upload").on("click",function(){
		var str = document.getElementById("demo-upload-unwrap").value;
		if(str.length==0)
		{
			layer.open({
			  content: '请选择上传文件！'
			});
			return false;
		}
		$("#form").ajaxSubmit({
   			dataType: "text",
            success: function (data) {
            	if(data){
            		$("#fileText").val(data);
            	}
            }
         });
	});
	
	$("#fenci").on("click",function(){
		var str = document.getElementById("demo-upload-unwrap").value;
		var keyWordLimit = $("#keyWordLimit").val();
		var abstractLength = $("#abstractLength").val();
		var fileText = $("#fileText").val();
		var fileName = document.getElementById("demo-upload-unwrap").files[0].name;
		if(keyWordLimit && abstractLength){}else{
			layer.open({
			  content: '必填项不能为空！'
			});
			return false;
		}
		if(str.length==0)
		{
			layer.open({
			  content: '请选择上传文件！'
			});
			return false;
		}
		showLoadingMsg('正在加载,请稍后...');
		$.ajax({
			type : "POST",
			url : "/iamp/fileHander/getKeyWordsAndAbs.do",
			data : {"fileName":fileName,"keyWordLimit":keyWordLimit,"abstractLength":abstractLength,"fileText":fileText},
			dataType : "json",
			success:function(data){
				if(data){
					$("#keyWords").html("");
					$("#abstractContent").val("");
					var abstractContent = data.abstractContent;
	            	var keyWords = data.keyWords;
	            	var keyWordArr = keyWords.split("，");
	            	for(var j=0;j<keyWordArr.length;j++){
	            		var appendDiv = '<div class="classify" style="width:100%;">'+keyWordArr[j]+'</div>';
	            		$("#keyWords").append(appendDiv);
	            	}
	            	$("#abstractContent").val(abstractContent);
	            	removeLoadingMsg();
				}
			}
		});
	});
});
</script>
</html>