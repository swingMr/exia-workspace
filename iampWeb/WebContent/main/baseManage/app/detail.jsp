<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>应用管理</title>
</head>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="format-detection" content="telephone=no">
<link rel="stylesheet" href="/iamp/js/layui-v2/css/layui.css" media="all" />
<script src="/iamp/js/jquery-1.7.2.min.js"></script>
<body>	
 	<form class="layui-form" action="/iamp/app/${method}.do" id="form">
 	<table lay-skin="nob" class="layui-table">
	  <colgroup>
	    <col width="150">
	    <col width="200">
	    <col>
	  </colgroup>
	  <c:choose>
         <c:when test="${method == 'create'}">
         	<tr>
		  	<td>
				<label class="layui-form-label"><font color="red">*</font>应用名称：</label>
			    <div class="layui-input-block">
			      <input type="text" id="appName" name="appName" required  lay-verify="required" placeholder="请输入应用名称" autocomplete="off" class="layui-input">
			    </div>
	    	</td>
		  	<td>
				<label class="layui-form-label"><font color="red">*</font>应用代码：</label>
			    <div class="layui-input-block">
			      <input type="text" id="appCode" name="appCode" required  lay-verify="required" placeholder="请输入应用代码" autocomplete="off" class="layui-input">
			    </div>
	    	</td>
    	</tr>
         </c:when>
         <c:otherwise>
         	<tr>
		  	<td>
				<label class="layui-form-label"><font color="red">*</font>应用名称：</label>
			  	<label class="layui-form-label" style="text-align:left;min-width:100px">
			    ${app.appName}<input type="hidden" name="appId" value="${!empty app? app.appId:''}">
			    <input type="hidden" name="appCode" value="${!empty app? app.appCode:''}">
			    <input type="hidden" name="appName" value="${!empty app? app.appName:''}">
			   </label>
	    	</td>
		  	<td>
				<label class="layui-form-label"><font color="red">*</font>应用代码：</label>
			   <label class="layui-form-label" style="text-align:left;min-width:100px">
			    ${app.appCode}
			    </label>
	    	</td>
    	</tr>
         </c:otherwise>
      </c:choose>
		<tr >
		  	<td>
			  <div class="layui-form-item">
			    <label class="layui-form-label"><font color="red">*</font>应用密码：</label>
			    <div class="layui-input-block">
			    <c:choose>
			    	<c:when test="${method == 'create' }">
			    		<input type="password" id="appPwd" name="appPwd" required  lay-verify="required" placeholder="请输入应用密码" autocomplete="off" class="layui-input" value="">
			    	</c:when>
			    	<c:otherwise>
			    		<input type="password" id="appPwd" name="appPwd"  placeholder="默认不修改密码" autocomplete="off" class="layui-input" value="">
			    	</c:otherwise>
			    </c:choose>
			      
			    </div>
			  </div>
	    	</td>
	    	<td>
	    		<div class="layui-form-item">
	    		<label class="layui-form-label"><font color="red">*</font>密码确认：</label>
			    <div class="layui-input-block">
			    <c:choose>
			    <c:when test="${method == 'create' }">
			    		<input type="password" id="appPwdRepeat" name="appPwdRepeat" required  lay-verify="required" placeholder="请输入应用密码" autocomplete="off" class="layui-input" value="">
			    	</c:when>
			    	<c:otherwise>
			    		<input type="password" id="appPwdRepeat" name="appPwdRepeat" placeholder="默认不修改密码" autocomplete="off" class="layui-input" value="">
			    	</c:otherwise>
		    	 </c:choose>
			    </div>
			    </div>
	    	</td>
	    </tr>
		<tr >
		  	<td>
			  <div class="layui-form-item">
			    <label class="layui-form-label">过期时间：</label>
			    <div class="layui-input-block">
			      <select lay-filter="classify" lay-verify="required" name="expireType">
			      	<option value="0">默认永久</option>
			        <option value="1">3个月</option>
			        <option value="2">半年</option>
			        <option value="3">1年</option>
			        <option value="4">3年</option>
			        <option value="5">5年</option>
			      </select>
			    </div>
			  </div>
	    	</td>
	    	<td>
	    	  <div class="layui-form-item">
			    <label class="layui-form-label">使用单位：</label>
		             <div class="layui-input-block">
	    			<input  id="appUnit" name="appUnit"   autocomplete="off" class="layui-input" value="${app.appUnit}">
	    		</div>
	    			<c:choose>
			      	<c:when test="${!empty app}">
			      		<input type="hidden" name="expireDate" value="<fmt:formatDate value="${app.expireDate}" pattern="yyyy-MM-dd" type="date"/>">
			      	</c:when>
			      	<c:otherwise>
			      		<input type="hidden" name="expireDate" value="">
			      	</c:otherwise>
			      </c:choose>
	    	</td>
	    </tr>
	    <c:choose>
	    <c:when test="${method == 'update'}">
	    	<tr>
		  	<td colspan="2">
			  <div class="layui-form-item layui-form-text">
			    <label class="layui-form-label">token：</label>
			    <div class="layui-input-block">
			    	<input class="layui-input" value="${app.token}" readonly="true">
			    </div>
			  </div>
  	    	</td>
	    </tr>
	    </c:when>
	    </c:choose>
		<tr>
		  	<td colspan="2">
			  <div class="layui-form-item layui-form-text">
			    <label class="layui-form-label">限制ip：</label>
			    <div class="layui-input-block">
			      <textarea name="restrictIp" placeholder="以;分割" class="layui-textarea" >${!empty app ? app.restrictIp:'' }</textarea>
			    </div>
			  </div>
  	    	</td>
	    </tr>
		<tr>
		  	<td colspan="2">
			  <div class="layui-form-item layui-form-text">
			    <label class="layui-form-label">备注：</label>
			    <div class="layui-input-block">
			      <textarea name="remark" placeholder="请输入内容" class="layui-textarea">${!empty app ? app.remark:'' }</textarea>
			    </div>
			  </div>
  	    	</td>
	    </tr>
    </table>
    <center>
			<button class="layui-btn layui-btn-normal layui-btn-normal" lay-submit lay-filter="save" id="save">确定</button>
			<span class="layui-btn layui-btn-danger layui-btn-normal"  id="cancel">取消</span>
		</center>
	</form> 
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
			var pwd = $('#appPwd').val();
		var pwdR = $('#appPwdRepeat').val();
		if(pwd != pwdR) {
			//showMsg('提醒','密码输入不一致');
			layer.open({
	          type: 1
	          ,title:'提醒'
	          ,content: '<div style="padding: 20px 100px;">密码输入不一致</div>'
	          ,btn: '关闭'
	          ,btnAlign: 'c' //按钮居中
	          ,shade: 0 //不显示遮罩
	          ,yes: function(){
	        	  layer.closeAll();
	          }
	        });	
			return false;
		} else {
			submit();
		}
		return false;
		}) 
		
	});
	
	function submit() {
		$.ajax({
                type: "POST",
                url:$('#form').attr('action'),
                data:$('#form').serialize(),// 你的formid
                async: false,
                error: function(request) {
                    alert("出现异常");
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
							var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
				      		parent.layer.close(index);
				    		parentRefresh();
				          }
				        });	
                	} else {
                		showMsg('提醒',data.msg);
						return;
                	}
                }
            });
	}
	
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
	
	
	function parentRefresh(){
	    var frameName = 'appidgl';
		window.parent.loadIframe(frameName);
	}
	
	$("#cancel").on("click",function(){
	closeCurrentWin();
});

	function closeCurrentWin(){
	   var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
	   parent.layer.close(index);
	}
	
</script>
</html>