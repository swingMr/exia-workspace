<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>�½���Ա��Ϣ</title>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="format-detection" content="telephone=no">
<script type="text/javascript" src="/iamp/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="/iamp/js/layui-v2/layui.js"></script>
<link rel="stylesheet" href="/iamp/js/layui-v2/css/layui.css" media="all" />
<link rel="stylesheet" href="/iamp/js/layui-v2/css/global.css" media="all">

<style>
    body{margin: 30px;}
</style>
</head>
<body>
	<div class="site-title">
      <fieldset><legend><a name="use">������Ϣ</a></legend></fieldset>
    </div>
	<form class="layui-form" id="form" action="/iamp/member/create.do">
		<input type="hidden" name="memberId" value="${!empty member? member.memberId:''}">
		<input type="hidden" name="appId" value="${!empty appId? appId:''}">
	  <div class="layui-form-item">
	    <label class="layui-form-label"><font style="color: #FF5722">*</font>��Ա�˺ţ�</label>
	    <div class="layui-input-inline" style="width: 300px">
	      <input type="text" name="memberAccount" required  lay-verify="required" placeholder="" autocomplete="off" class="layui-input" value="${!empty member? member.memberAccount:''}">
	    </div>
	    <label class="layui-form-label"><font style="color: #FF5722">*</font>��Ա������</label>
	    <div class="layui-input-inline" style="width: 300px">
	      <input type="text" name="memberName" required  lay-verify="required" placeholder="" autocomplete="off" class="layui-input" value="${!empty member? member.memberName:''}">
	    </div>
	  </div>
	  <div class="layui-form-item">
	    <label class="layui-form-label"><font style="color: #FF5722">*</font>��Ա���</label>
	    <div class="layui-input-inline" style="width: 300px">
		    <c:choose>
		    	<c:when test="${empty member}">
		    		<input type="password" id="memberPsw" name="memberPsw" required  lay-verify="required" placeholder="�������Ա����" autocomplete="off" class="layui-input" value="">
		    	</c:when>
		    	<c:otherwise>
		    		<input type="password" id="memberPsw" name="memberPsw"  placeholder="Ĭ�ϲ��޸�����" autocomplete="off" class="layui-input" value="">
		    	</c:otherwise>
		    </c:choose>
	    </div>
	    <label class="layui-form-label"><font style="color: #FF5722">*</font>����ȷ�ϣ�</label>
	    <div class="layui-input-inline" style="width: 300px">
		    <c:choose>
		    	<c:when test="${empty member}">
		    		<input type="password" id="memberPswRepeat" name="memberPswRepeat" required  lay-verify="required" placeholder="�������Ա����" autocomplete="off" class="layui-input" value="">
		    	</c:when>
		    	<c:otherwise>
		    		<input type="password" id="memberPswRepeat" name="memberPswRepeat"  placeholder="Ĭ�ϲ��޸�����" autocomplete="off" class="layui-input" value="">
		    	</c:otherwise>
	    	</c:choose>
	    </div>
	  </div>
	  <div class="layui-form-item">
	  	 <label class="layui-form-label">�ֻ����룺</label>
	    <div class="layui-input-inline" style="width: 300px">
	      <input type="text" name="phoneNum"  placeholder="" autocomplete="off" class="layui-input" value="${!empty member? member.phoneNum:''}">
	    </div>
	    <label class="layui-form-label">���䣺</label>
	    <div class="layui-input-inline" style="width: 235px;margin-right: 0px">
	      <input type="text" name="emailAddress"  autocomplete="off" class="layui-input" value="${!empty member? member.emailAddress:''}">
	    </div>
	    <div class="layui-input-inline" style="width: 65px">
	    	<button class="layui-btn">��</button>
	    </div>
	  </div>
	  <div class="layui-form-item">
	  	<label class="layui-form-label">QQ�ţ�</label>
	    <div class="layui-input-inline" style="width: 235px;margin-right: 0px">
	      <input type="text" name="qqNum"  autocomplete="off" class="layui-input" value="${!empty member? member.qqNum:''}">
	    </div>
	    <div class="layui-input-inline" style="width: 65px">
	    	<button class="layui-btn">��</button>
	    </div>
	    <label class="layui-form-label">΢�źţ�</label>
	    <div class="layui-input-inline" style="width: 235px;margin-right: 0px">
	      <input type="text" name="wechatNum" autocomplete="off" class="layui-input" value="${!empty member? member.wechatNum:''}">
	    </div>
	    <div class="layui-input-inline" style="width: 65px">
	    	<button class="layui-btn">��</button>
	    </div>
	  </div>
	  <div class="layui-form-item">
	  	<label class="layui-form-label">�����ˣ�</label>
	    <div class="layui-input-inline" style="width: 300px">
	      <label class="layui-form-label" style="text-align:left;min-width:100px">ϵͳ����Ա</label>
	    </div>
	  </div>
	  <c:if test="${!empty appId}">
	  	<div class="layui-form-item">
		    <label class="layui-form-label"><font style="color: #FF5722">*</font>�������飺</label>
		    <div class="layui-input-inline" style="width: 300px">
		      <input type="text" name="appGroup" required  lay-verify="required" placeholder="" autocomplete="off" class="layui-input" value="">
		    </div>
		 </div>
	  </c:if>
	  <div class="layui-form-item layui-form-text">
	    <label class="layui-form-label">��ע��</label>
	    <div class="layui-input-block" style="width: 720px">
	      <textarea name="desc" placeholder="" class="layui-textarea"></textarea>
	    </div>
	  </div>
	  <c:if test="${(domainNameList!=null&& fn:length(domainNameList) > 0) || (tagList!=null&& fn:length(tagList) > 0)}">
	  <blockquote class="layui-elem-quote">�������� </blockquote>
	  </c:if>
		<div id="tagDiv">
			<c:if test="${domainNameList!= null && fn:length(domainNameList) > 0}">
				��ע����<c:forEach var="domain" items="${domainNameList}" varStatus="status">
					<span class="layui-btn layui-btn-small domains" value="${domain}" style="margin:10px;">${domain}</span>
				</c:forEach><br>
			</c:if>
			
			<c:if test="${tagList!= null && fn:length(tagList) > 0}">
				������ǩ��<c:forEach var="tagVal" items="${tagList}" varStatus="status">
					<span class="layui-btn layui-btn-small domains" value="${tagVal}" style="margin:10px;">${tagVal}</span>
				</c:forEach><br>
			</c:if>
		</div>
	  <center>
		<button class="layui-btn layui-btn-normal layui-btn-normal" lay-submit="" lay-filter="save" id="save">ȷ��</button>
		<span class="layui-btn layui-btn-danger layui-btn-normal"  id="cancel">ȡ��</span>
	</center>
	</form>
	
<script>
var layer;
	
	layui.use(['element','layer','form'], function(){
		var element = layui.element;
		layer = layui.layer;
		window.element = element;
		var form = layui.form;
		form.on('submit(save)', function (data) {
			var pwd = $('#memberPsw').val();
			var pwdR = $('#memberPswRepeat').val();
			if(pwd != pwdR) {
				//showMsg('����','�������벻һ��');
				layer.open({
		          type: 1
		          ,title:'����'
		          ,content: '<div style="padding: 20px 100px;">�������벻һ��</div>'
		          ,btn: '�ر�'
		          ,btnAlign: 'c' //��ť����
		          ,shade: 0 //����ʾ����
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
                data:$('#form').serialize(),// ���formid
                async: false,
                error: function(request) {
                    alert("�����쳣");
                },
                dataType:"json",
                success: function(data) {
               		layer.open({
			          type: 1
			          ,title:'����'
			          ,content: '<div style="padding: 20px 100px;">'+data.msg+'</div>'
			          ,btn: '�ر�'
			          ,btnAlign: 'c' //��ť����
			          ,shade: 0 //����ʾ����
			          ,yes: function(){
				          closeCurrentWin();
				          parentRefresh();
				          var appId = '${appId}';
				          if(appId!=null){
				        	  window.parent.refresh();
					      }
			          }
			        });	
                }
            });
	}
	
	function showMsg(title, msg) {
		layer.open({
          type: 1
          ,title:title
          ,content: '<div style="padding: 20px 100px;">'+msg+'</div>'
          ,btn: '�ر�'
          ,btnAlign: 'c' //��ť����
          ,shade: 0 //����ʾ����
          ,yes: function(){
        	  layer.closeAll();
          }
        });	
	}
	
	
	function parentRefresh(){
	    var frameName = 'hyzhgl';
		window.parent.loadIframe(frameName);
	}
	
	$("#cancel").on("click",function(){
		closeCurrentWin();
	});

	function closeCurrentWin(){
	   var index = parent.layer.getFrameIndex(window.name); //�ȵõ���ǰiframe�������
	   parent.layer.close(index);
	}
</script>
</body>
</html>