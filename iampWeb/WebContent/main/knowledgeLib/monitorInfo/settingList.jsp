<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>�������</title>
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
<style type="text/css">
	.btn{
		margin-top:10px;
		margin-left:10px;
	}
	.title{
		
	}
</style>

<body>
	<form class="layui-form" action="">	
 	<table class="layui-table" lay-skin="nob">
		<tr ><td colspan="2"><blockquote class="layui-elem-quote">���������(������������֮һ)</blockquote></td></tr>
		<tr>
			<td>
				<label class="layui-form-label">�޶����ࣺ</label>
				<div class="layui-input-block"><input type="hidden" id="monId" value="${id}">
				    <input type="text" id="subParents" name="title"   value="${subParents}"  placeholder="����д���࣬��Ӣ�ķֺţ�;������" autocomplete="off" class="layui-input">
				 </div>
			</td>
			<td>
				<span class="layui-btn layui-btn-normal">ѡ��</span>
			</td>
		</tr>
		<tr>
			<td>
				<label class="layui-form-label">�޶����</label>
			    <div class="layui-input-block">
			      <textarea name="desc" id="subConcepts" value="${subConcepts}"  placeholder="����д�����Ӣ�ķֺţ�;������" class="layui-textarea">${subConcepts}</textarea>
			    </div>
			</td>
			<td>
				<span class="layui-btn layui-btn-normal">ѡ��</span>
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<label class="layui-form-label">�޶����ͣ�</label>
				<div class="layui-input-block">
					<c:choose>  					  
						   <c:when test="${!empty subType && subType =='clazz'}">
						   		<input type="radio" name="subType" value="no" title="���޶�" >
								<input type="radio" name="subType" value="clazz" title="�޶���" checked>
								<input type="radio" name="subType" value="concrete" title="�޶�ʵ��" > 
						   </c:when>
						   <c:when test="${!empty subType && subType =='concrete'}"> 
						   		<input type="radio" name="subType" value="no" title="���޶�" >
								<input type="radio" name="subType" value="clazz" title="�޶���" >
								<input type="radio" name="subType" value="concrete" title="�޶�ʵ��" checked> 
						   </c:when>
						   <c:otherwise>
						   		 <input type="radio" name="subType" value="no" title="���޶�" checked>
								<input type="radio" name="subType" value="clazz" title="�޶���" >
								<input type="radio" name="subType" value="concrete" title="�޶�ʵ��" >
						   </c:otherwise>
					</c:choose>
				 </div>
			</td>
		</tr>
		<tr><td colspan="2"><blockquote class="layui-elem-quote">��ؿ�����</blockquote></td></tr>
		<tr>
			<td>
				<label class="layui-form-label">�޶����ࣺ</label>
				<div class="layui-input-block">
				    <input type="text" id="objParents" name="title"   value="${objParents}"  placeholder="����д���࣬��Ӣ�ķֺţ�;������" autocomplete="off" class="layui-input">
				 </div>
			</td>
			<td>
				<span class="layui-btn layui-btn-normal">ѡ��</span>
			</td>
		</tr>
		<tr>
			<td>
				<label class="layui-form-label">�޶����</label>
			    <div class="layui-input-block">
			      <textarea name="desc" id="objConcepts" value="${objConcepts}"  placeholder="����д�����Ӣ�ķֺţ�;������" class="layui-textarea">${objConcepts}</textarea>
			    </div>
			</td>
			<td>
				<span class="layui-btn layui-btn-normal">ѡ��</span>
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<label class="layui-form-label">�޶����ͣ�</label>
				<div class="layui-input-block">
					<c:choose>  					  
						   <c:when test="${!empty objType && objType =='clazz'}">
						   		<input type="radio" name="objType" value="no" title="���޶�" >
								<input type="radio" name="objType" value="clazz" title="�޶���" checked>
								<input type="radio" name="objType" value="concrete" title="�޶�ʵ��" > 
						   </c:when>
						   <c:when test="${!empty objType && objType =='concrete'}"> 
						   		<input type="radio" name="objType" value="no" title="���޶�" >
								<input type="radio" name="objType" value="clazz" title="�޶���" >
								<input type="radio" name="objType" value="concrete" title="�޶�ʵ��" checked> 
						   </c:when>
						   <c:otherwise>
						   		 <input type="radio" name="objType" value="no" title="���޶�" checked>
								<input type="radio" name="objType" value="clazz" title="�޶���" >
								<input type="radio" name="objType" value="concrete" title="�޶�ʵ��" >
						   </c:otherwise>
					</c:choose>
				 </div>
			</td>
		</tr>
		<tr><td colspan="2"><blockquote class="layui-elem-quote">�����Ϊ���</blockquote></td></tr>
		<tr>
			<td>
				<label class="layui-form-label">�޶����ࣺ</label>
				<div class="layui-input-block">
				    <input type="text" id="actParents" name="title"   value="${actParents}"  placeholder="����д���࣬��Ӣ�ķֺţ�;������" autocomplete="off" class="layui-input">
				 </div>
			</td>
			<td>
				<span class="layui-btn layui-btn-normal">ѡ��</span>
			</td>
		</tr>
		<tr>
			<td>
				<label class="layui-form-label">�޶����</label>
			    <div class="layui-input-block">
			      <textarea name="desc" id="actConcepts" value="${actConcepts}"  placeholder="����д�����Ӣ�ķֺţ�;������" class="layui-textarea">${actConcepts}</textarea>
			    </div>
			</td>
			<td>
				<span class="layui-btn layui-btn-normal">ѡ��</span>
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<label class="layui-form-label">�޶����ͣ�</label>
				<div class="layui-input-block">
					<c:choose>  					  
						   <c:when test="${!empty actType && actType =='clazz'}">
						   		<input type="radio" name="actType" value="no" title="���޶�" >
								<input type="radio" name="actType" value="clazz" title="�޶���" checked>
								<input type="radio" name="actType" value="concrete" title="�޶�ʵ��" > 
						   </c:when>
						   <c:when test="${!empty actType && actType =='concrete'}"> 
						   		<input type="radio" name="actType" value="no" title="���޶�" >
								<input type="radio" name="actType" value="clazz" title="�޶���" >
								<input type="radio" name="actType" value="concrete" title="�޶�ʵ��" checked> 
						   </c:when>
						   <c:otherwise>
						   		 <input type="radio" name="actType" value="no" title="���޶�" checked>
								<input type="radio" name="actType" value="clazz" title="�޶���" >
								<input type="radio" name="actType" value="concrete" title="�޶�ʵ��" >
						   </c:otherwise>
					</c:choose>
				 </div>
			</td>
		</tr>
	   <tr><td colspan="2"><blockquote class="layui-elem-quote">���ʱ����</blockquote></td></tr>
		<tr>
			<td>
				<label class="layui-form-label">�޶����ࣺ</label>
				<div class="layui-input-block">
				    <input type="text" id="timeParents" name="title"   value="${timeParents}"  placeholder="����д���࣬��Ӣ�ķֺţ�;������" autocomplete="off" class="layui-input">
				 </div>
			</td>
			<td>
				<span class="layui-btn layui-btn-normal">ѡ��</span>
			</td>
		</tr>
		<tr>
			<td>
				<label class="layui-form-label">�޶����</label>
			    <div class="layui-input-block">
			      <textarea name="desc" id="timeConcepts" value="${timeConcepts}"  placeholder="����д�����Ӣ�ķֺţ�;������" class="layui-textarea">${timeConcepts}</textarea>
			    </div>
			</td>
			<td>
				<span class="layui-btn layui-btn-normal">ѡ��</span>
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<label class="layui-form-label">�޶����ͣ�</label>
				<div class="layui-input-block">
					<c:choose>  					  
						   <c:when test="${!empty timeType && timeType =='clazz'}">
						   		<input type="radio" name="timeType" value="no" title="���޶�" >
								<input type="radio" name="timeType" value="clazz" title="�޶���" checked>
								<input type="radio" name="timeType" value="concrete" title="�޶�ʵ��" > 
						   </c:when>
						   <c:when test="${!empty timeType && timeType =='concrete'}"> 
						   		<input type="radio" name="timeType" value="no" title="���޶�" >
								<input type="radio" name="timeType" value="clazz" title="�޶���" >
								<input type="radio" name="timeType" value="concrete" title="�޶�ʵ��" checked> 
						   </c:when>
						   <c:otherwise>
						   		 <input type="radio" name="timeType" value="no" title="���޶�" checked>
								<input type="radio" name="timeType" value="clazz" title="�޶���" >
								<input type="radio" name="timeType" value="concrete" title="�޶�ʵ��" >
						   </c:otherwise>
					</c:choose>
				 </div>
			</td>
		</tr>
		<tr><td colspan="2"><blockquote class="layui-elem-quote">��ؿռ���</blockquote></td></tr>
		<tr>
			<td>
				<label class="layui-form-label">�޶����ࣺ</label>
				<div class="layui-input-block">
				    <input type="text" id="spaceParents" name="title"   value="${spaceParents}"  placeholder="����д���࣬��Ӣ�ķֺţ�;������" autocomplete="off" class="layui-input">
				 </div>
			</td>
			<td>
				<span class="layui-btn layui-btn-normal">ѡ��</span>
			</td>
		</tr>
		<tr>
			<td>
				<label class="layui-form-label">�޶����</label>
			    <div class="layui-input-block">
			      <textarea name="desc" id="spaceConcepts" value="${spaceConcepts}"  placeholder="����д�����Ӣ�ķֺţ�;������" class="layui-textarea">${spaceConcepts}</textarea>
			    </div>
			</td>
			<td>
				<span class="layui-btn layui-btn-normal">ѡ��</span>
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<label class="layui-form-label">�޶����ͣ�</label>
				<div class="layui-input-block">
					<c:choose>  					  
						   <c:when test="${!empty spaceType && spaceType =='clazz'}">
						   		<input type="radio" name="spaceType" value="no" title="���޶�" >
								<input type="radio" name="spaceType" value="clazz" title="�޶���" checked>
								<input type="radio" name="spaceType" value="concrete" title="�޶�ʵ��" > 
						   </c:when>
						   <c:when test="${!empty spaceType && spaceType =='concrete'}"> 
						   		<input type="radio" name="spaceType" value="no" title="���޶�" >
								<input type="radio" name="spaceType" value="clazz" title="�޶���" >
								<input type="radio" name="spaceType" value="concrete" title="�޶�ʵ��" checked> 
						   </c:when>
						   <c:otherwise>
						   		 <input type="radio" name="spaceType" value="no" title="���޶�" checked>
								<input type="radio" name="spaceType" value="clazz" title="�޶���" >
								<input type="radio" name="spaceType" value="concrete" title="�޶�ʵ��" >
						   </c:otherwise>
					</c:choose>
				 </div>
			</td>
		</tr>
		<tr><td colspan="2"><blockquote class="layui-elem-quote">�������Ŀ����</blockquote></td></tr>
		<tr>
			<td>
				<label class="layui-form-label">�޶����ࣺ</label>
				<div class="layui-input-block">
				    <input type="text" id="proParents" name="title"   value="${proParents}"  placeholder="����д���࣬��Ӣ�ķֺţ�;������" autocomplete="off" class="layui-input">
				 </div>
			</td>
			<td>
				<span class="layui-btn layui-btn-normal">ѡ��</span>
			</td>
		</tr>
		<tr>
			<td>
				<label class="layui-form-label">�޶����</label>
			    <div class="layui-input-block">
			      <textarea name="desc" id="proConcepts" value="${proConcepts}"  placeholder="����д�����Ӣ�ķֺţ�;������" class="layui-textarea">${proConcepts}</textarea>
			    </div>
			</td>
			<td>
				<span class="layui-btn layui-btn-normal">ѡ��</span>
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<label class="layui-form-label">�޶����ͣ�</label>
				<div class="layui-input-block">
					<c:choose>  					  
						   <c:when test="${!empty proType && proType =='clazz'}">
						   		<input type="radio" name="proType" value="no" title="���޶�" >
								<input type="radio" name="proType" value="clazz" title="�޶���" checked>
								<input type="radio" name="proType" value="concrete" title="�޶�ʵ��" > 
						   </c:when>
						   <c:when test="${!empty proType && proType =='concrete'}"> 
						   		<input type="radio" name="proType" value="no" title="���޶�" >
								<input type="radio" name="proType" value="clazz" title="�޶���" >
								<input type="radio" name="proType" value="concrete" title="�޶�ʵ��" checked> 
						   </c:when>
						   <c:otherwise>
						   		 <input type="radio" name="proType" value="no" title="���޶�" checked>
								<input type="radio" name="proType" value="clazz" title="�޶���" >
								<input type="radio" name="proType" value="concrete" title="�޶�ʵ��" >
						   </c:otherwise>
					</c:choose>
				 </div>
			</td>
		</tr>
		<tr><td colspan="2"><blockquote class="layui-elem-quote">�ؼ��ʼ��</blockquote></td></tr>
		<tr>
			<td>
				<label class="layui-form-label">�޶����</label>
			    <div class="layui-input-block">
			      <textarea name="desc" id="keywordConcepts" value="${keywordConcepts}"  placeholder="����д�����Ӣ�ķֺţ�;������" class="layui-textarea">${keywordConcepts}</textarea>
			    </div>
			</td>
			<td>
				<span class="layui-btn layui-btn-normal">ѡ��</span>
			</td>
		</tr>
	</table> 
		<center>
			<span class="layui-btn layui-btn layui-btn-small foot"  id="save" >ȷ��</span>
			<span class="layui-btn layui-btn-danger layui-btn-small foot" id="cancel" >ȡ��</span>
	 	 </center>
	</form>
</body>
<script type="text/javascript" src="/iamp/js/layui/layui.js"></script>
<script>
var layer; 
var SAVE_URL = "/iamp/monitoringSetting/save";
layui.use(['layer','laypage','form'], function(){
	var form = layui.form();
	  var layer = layui.layer;
	  window.layer = layer;
	  
		  
		  form.on('select(type)', function(data){
			  $(".layui-form").submit();
		  });
});	

function closeCurrentWin(){
	   var index = parent.layer.getFrameIndex(window.name); //�ȵõ���ǰiframe�������
	   parent.layer.close(index);
}

$("#save").on("click",function(){
	var id = $("#monId").val();
	var obj = new Object();
	var subType = $("input[name=subType]:checked").val();
	var objType = $("input[name=objType]:checked").val();
	var actType = $("input[name=actType]:checked").val();
	var timeType = $("input[name=timeType]:checked").val();
	var spaceType = $("input[name=spaceType]:checked").val();
	var proType = $("input[name=proType]:checked").val();
	obj.subType = subType;
	obj.objType = objType;
	obj.actType = actType;
	obj.timeType = timeType;
	obj.spaceType = spaceType; 
	obj.proType = proType;
	var subParents = $("#subParents").val();
	var subConcepts = $("#subConcepts").val();
	var objParents = $("#objParents").val();
	var objConcepts = $("#objConcepts").val();
	var actParents = $("#actParents").val();
	var actConcepts = $("#actConcepts").val();
	var timeParents = $("#timeParents").val();
	var timeConcepts = $("#timeConcepts").val();
	var spaceParents = $("#spaceParents").val();
	var spaceConcepts = $("#spaceConcepts").val();
	var proParents = $("#proParents").val();
	var proConcepts = $("#proConcepts").val();
	var keywordConcepts = $("#keywordConcepts").val();
	obj.subParents = subParents!="" ? '['+subParents+']':'';
	obj.subConcepts =subConcepts!="" ? '['+subConcepts+']':'';
	obj.objParents = objParents !=""? '['+objParents+']':'';
	obj.objConcepts = objConcepts!="" ?'['+objConcepts+']':'';
	obj.actParents = actParents!="" ? '['+actParents+']':'';
	obj.actConcepts = actConcepts !=""?'['+actConcepts+']':'';
	obj.timeParents = timeParents !=""?'['+timeParents+']':'';
	obj.timeConcepts =timeConcepts!=""?'['+timeConcepts+']':'';
	obj.spaceParents = spaceParents!=""?'['+spaceParents+']':'';
	obj.spaceConcepts = spaceConcepts!=""?'['+spaceConcepts+']':'';
	obj.proParents = proParents!=""?'['+proParents+']':'';
	obj.proConcepts = proConcepts!=""?'['+proConcepts+']':'';
	obj.kyParents = "";
	obj.kyConcepts = keywordConcepts!=""?'['+keywordConcepts+']':'';
	var data = JSON.stringify(obj);
	console.log(data);
 	$.post(SAVE_URL
		,{
		"data":data,
		"id":id
	}
	,function(data){
		if(data.result == 'success'){
		      layer.open({
		          type: 1
		          ,title:"����"
		          ,content: '<div style="padding: 20px 100px;">�����ɹ�!</div>'
		          ,btn: '�ر�'
		          ,btnAlign: 'c' //��ť����
		          ,shade: 0 //����ʾ����
		          ,yes: function(){
		        	closeCurrentWin();
		          }
		        });
		}else{
			error("����ʧ�ܣ�");
		}
	},"json"); 
});

</script>
</html>