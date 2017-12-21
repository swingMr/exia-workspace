<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>文件格式转换配置</title>
</head>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="format-detection" content="telephone=no">
<link rel="stylesheet" href="/iamp/js/layui-v2/css/layui.css" media="all" />
<script src="/iamp/js/jquery-1.7.2.min.js"></script>
<style>
	.widelabel {
		width:150px
	}
</style>
<body>	
<div class="layui-tab" lay-filter="tab">
  <div class="layui-tab-content">
 	<form class="layui-form" action="/iamp/fileconfig/save.do" id="form">
 	<table lay-skin="nob" class="layui-table">
	  <colgroup>
	    <col width="150">
	    <col width="200">
	    <col>
	  </colgroup>
         	<tr>
		  	<td>
				<label class="layui-form-label widelabel"><font color="red">*</font>文件数量：</label>
			    <div class="layui-input-block">
			    <input type="hidden" name="configId" value="${!empty config?config.configId:'' }">
			      <input type="text"  style="width:60%" id="fileCount" name="fileCount" required  lay-verify="required|number" placeholder="请输入文件数量" autocomplete="off" class="layui-input" value="${!empty config?config.fileCount:0 }">
			    </div>
	    	</td>
		  	<td>
				<label class="layui-form-label "><font color="red">*</font>过期时间：</label>
			    <div class="layui-input-block">
			      <select lay-filter="classify" lay-verify="required" name="expireType" style="width:60%">
			      	<option value="0" ${!empty config && config.expireType == 0?'selected':'' }>6小时</option>
			        <option value="1" ${!empty config && config.expireType == 1?'selected':'' }>1天</option>
			        <option value="2" ${!empty config && config.expireType == 2?'selected':'' }>3天</option>
			        <option value="3" ${!empty config && config.expireType == 3?'selected':'' }>7天</option>
			        <option value="4" ${!empty config && config.expireType == 4?'selected':'' } >30天</option>
			      </select>
			    </div>
	    	</td>
    	</tr>
		<tr >
		  	<td>
			  <div class="layui-form-item">
			    <label class="layui-form-label widelabel"><font color="red">*</font>空间大小(MB)：</label>
			    <div class="layui-input-block">
			      <input style="width:60%" type="text" id="spaceSize" name="spaceSize" required  lay-verify="required|number" placeholder="请输入空间大小" autocomplete="off" class="layui-input" value="${!empty config?config.spaceSize:0 }">
			    </div>
			  </div>
	    	</td>
	    	<td>
	    	</td>
	    </tr>
		<tr>
		  	<td colspan="2">
			  <div class="layui-form-item layui-form-text">
			    <label class="layui-form-label widelabel">支持txt转换格式：</label>
					      <input type="checkbox" name="txtExt" title="html" value="html" ${!empty config  && fn:contains(config.txtExt,"html")?'checked':'' }>
					      <input type="checkbox" name="txtExt" title="pdf" value="pdf" ${!empty config  && fn:contains(config.txtExt,"pdf")?'checked':'' }>
					      <input type="checkbox" name="txtExt" title="doc" value="doc" ${!empty config  && fn:contains(config.txtExt,"doc")?'checked':'' }>
					      <input type="checkbox" name="txtExt" title="docx" value="docx" ${!empty config  && fn:contains(config.txtExt,"docx")?'checked':'' }>
			 		 </div>
  	    	</td>
	    </tr>
	    <tr>
		  	<td colspan="2">
			  <div class="layui-form-item layui-form-text">
			    <label class="layui-form-label widelabel">支持html转换格式：</label>
			            <div class="layui-input-block">
					      <input type="checkbox" name="htmlExt" title="pdf" value="pdf" ${!empty config  && fn:contains(config.htmlExt,"pdf")?'checked':'' }>
					      <input type="checkbox" name="htmlExt" title="doc" value="doc" ${!empty config  && fn:contains(config.htmlExt,"doc")?'checked':'' }>
					      <input type="checkbox" name="htmlExt" title="docx" value="docx" ${!empty config  && fn:contains(config.htmlExt,"docx")?'checked':'' }>
					      <input type="checkbox" name="htmlExt" title="txt" value="txt" ${!empty config  && fn:contains(config.htmlExt,"txt")?'checked':'' }>
					    </div>
			 		 </div>
  	    	</td>
	    </tr>
	    <tr>
		  	<td colspan="2">
			  <div class="layui-form-item layui-form-text">
			    <label class="layui-form-label widelabel">支持word转换格式：</label>
			            <div class="layui-input-block">
					      <input type="checkbox" name="wordExt" title="html" value="html" ${!empty config  && fn:contains(config.wordExt,"html")?'checked':'' }>
					      <input type="checkbox" name="wordExt" title="pdf" value="pdf" ${!empty config  && fn:contains(config.wordExt,"pdf")?'checked':'' }>
					      <input type="checkbox" name="wordExt" title="txt" value="txt" ${!empty config  && fn:contains(config.wordExt,"txt")?'checked':'' }>
					    </div>
			 		 </div>
  	    	</td>
	    </tr>
	    <tr>
		  	<td colspan="2">
			  <div class="layui-form-item layui-form-text">
			    <label class="layui-form-label widelabel">支持pdf转换格式：</label>
			            <div class="layui-input-block">
					      <input type="checkbox" name="pdfExt" title="html" value="html" ${!empty config  && fn:contains(config.pdfExt,"html")?'checked':'' }>
					      <input type="checkbox" name="pdfExt" title="jpg" value="jpg" ${!empty config  && fn:contains(config.pdfExt,"jpg")?'checked':'' }>
					      <input type="checkbox" name="pdfExt" title="txt" value="txt" ${!empty config  && fn:contains(config.pdfExt,"txt")?'checked':'' }>
					      <input type="checkbox" name="pdfExt" title="doc" value="doc" ${!empty config  && fn:contains(config.pdfExt,"doc")?'checked':'' }>
					      <input type="checkbox" name="pdfExt" title="docx" value="docx" ${!empty config  && fn:contains(config.pdfExt,"docx")?'checked':'' }>
					    </div>
			 		 </div>
  	    	</td>
	    </tr>
	    <tr>
		  	<td colspan="2">
			  <div class="layui-form-item layui-form-text">
			    <label class="layui-form-label widelabel">支持jpg转换格式：</label>
			            <div class="layui-input-block">
					      <input type="checkbox" name="jpgExt" title="pdf" value="pdf" ${!empty config  && fn:contains(config.jpgExt,"pdf")?'checked':'' }>
					      <input type="checkbox" name="jpgExt" title="doc" value="doc" ${!empty config  && fn:contains(config.jpgExt,"doc")?'checked':'' }>
					      <input type="checkbox" name="jpgExt" title="docx" value="docx" ${!empty config  && fn:contains(config.jpgExt,"docx")?'checked':'' }>
					    </div>
			 		 </div>
  	    	</td>
	    </tr>
    </table>
    <center>
	<button class="layui-btn layui-btn-normal layui-btn-normal" id="save" lay-submit lay-filter="save" >确定</button>
</center>
	</form> 
  </div>
</div>

</body>
<script type="text/javascript" src="/iamp/js/layui-v2/layui.js"></script>
<script>
var layer;
	
	layui.use(['element','layer','form'], function(){
		var element = layui.element;
		layer = layui.layer;
		window.element = element;
		var form = layui.form;
		form.on('submit(save)', function (data) {
			submit();
			return false;
		}) 
		
	});
	
	
	function showMsg(title, msg) {
		layer.open({
          type: 1
          ,title:title
          ,content: '<div style="padding: 20px 100px;">'+msg+'</div>'
          ,btn: '关闭'
          ,btnAlign: 'c' //按钮居中
          ,shade: 0 //不显示遮罩
          ,yes: function(){
        	  layer.closeAll();
          }
        });	
	}
	
	function submit() {
		var data = {};
			var txtExt =[];
		    $('input[name="txtExt"]:checked').each(function(){
		       txtExt.push($(this).val());
		       });
		    var pdfExt = [];
		    $('input[name="pdfExt"]:checked').each(function(){
		       pdfExt.push($(this).val());
		       });
		    var htmlExt = [];
		    $('input[name="htmlExt"]:checked').each(function(){
		       htmlExt.push($(this).val());
		       });  
		    var jpgExt = [];
		    $('input[name="jpgExt"]:checked').each(function(){
		       jpgExt.push($(this).val());
		       }); 
		     var wordExt = [];
		    $('input[name="wordExt"]:checked').each(function(){
		       wordExt.push($(this).val());
		       }); 
		    data.configId = $('input[name="configId"]').val();
		    data.fileCount = $('input[name="fileCount"]').val();
		     data.spaceSize = $('input[name="spaceSize"]').val();
		    data.expireType =   $('select[name="expireType"]').val();
		    data.txtExt = txtExt.join(';');
		    data.pdfExt = pdfExt.join(';');
		    data.htmlExt = htmlExt.join(';');
		    data.jpgExt = jpgExt.join(';');
		    data.wordExt = wordExt.join(';');
			$.ajax({
                type: "POST",
                url:$('#form').attr('action'),
                data:data,
                async: false,
                error: function(request) {
                    alert("异常");
                },
                dataType:"json",
                success: function(data) {
                	if(data.result) {
                		layer.open({
				          type: 1
				          ,title:'提醒'
				          ,content: '<div style="padding: 20px 100px;">'+data.msg+'</div>'
				          ,btn: '关闭'
				          ,btnAlign: 'c' //按钮居中
				          ,shade: 0 //不显示遮罩
				          ,yes: function(){
				        	  layer.closeAll();
				          }
				        });	
                	} else {
                		showMsg('提醒',data.msg);
						return;
                	}
                }
            });
	}	
	
</script>
</html>