<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>监控设置</title>
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
		<tr ><td colspan="2"><blockquote class="layui-elem-quote">相关主体监控(满足以下条件之一)</blockquote></td></tr>
		<tr>
			<td>
				<label class="layui-form-label">限定父类：</label>
				<div class="layui-input-block"><input type="hidden" id="monId" value="${id}">
				    <input type="text" id="subParents" name="title"   value="${subParents}"  placeholder="请填写父类，用英文分号（;）隔开" autocomplete="off" class="layui-input">
				 </div>
			</td>
			<td>
				<span class="layui-btn layui-btn-normal">选择</span>
			</td>
		</tr>
		<tr>
			<td>
				<label class="layui-form-label">限定概念：</label>
			    <div class="layui-input-block">
			      <textarea name="desc" id="subConcepts" value="${subConcepts}"  placeholder="请填写概念，用英文分号（;）隔开" class="layui-textarea">${subConcepts}</textarea>
			    </div>
			</td>
			<td>
				<span class="layui-btn layui-btn-normal">选择</span>
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<label class="layui-form-label">限定类型：</label>
				<div class="layui-input-block">
					<c:choose>  					  
						   <c:when test="${!empty subType && subType =='clazz'}">
						   		<input type="radio" name="subType" value="no" title="不限定" >
								<input type="radio" name="subType" value="clazz" title="限定类" checked>
								<input type="radio" name="subType" value="concrete" title="限定实例" > 
						   </c:when>
						   <c:when test="${!empty subType && subType =='concrete'}"> 
						   		<input type="radio" name="subType" value="no" title="不限定" >
								<input type="radio" name="subType" value="clazz" title="限定类" >
								<input type="radio" name="subType" value="concrete" title="限定实例" checked> 
						   </c:when>
						   <c:otherwise>
						   		 <input type="radio" name="subType" value="no" title="不限定" checked>
								<input type="radio" name="subType" value="clazz" title="限定类" >
								<input type="radio" name="subType" value="concrete" title="限定实例" >
						   </c:otherwise>
					</c:choose>
				 </div>
			</td>
		</tr>
		<tr><td colspan="2"><blockquote class="layui-elem-quote">相关客体监控</blockquote></td></tr>
		<tr>
			<td>
				<label class="layui-form-label">限定父类：</label>
				<div class="layui-input-block">
				    <input type="text" id="objParents" name="title"   value="${objParents}"  placeholder="请填写父类，用英文分号（;）隔开" autocomplete="off" class="layui-input">
				 </div>
			</td>
			<td>
				<span class="layui-btn layui-btn-normal">选择</span>
			</td>
		</tr>
		<tr>
			<td>
				<label class="layui-form-label">限定概念：</label>
			    <div class="layui-input-block">
			      <textarea name="desc" id="objConcepts" value="${objConcepts}"  placeholder="请填写概念，用英文分号（;）隔开" class="layui-textarea">${objConcepts}</textarea>
			    </div>
			</td>
			<td>
				<span class="layui-btn layui-btn-normal">选择</span>
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<label class="layui-form-label">限定类型：</label>
				<div class="layui-input-block">
					<c:choose>  					  
						   <c:when test="${!empty objType && objType =='clazz'}">
						   		<input type="radio" name="objType" value="no" title="不限定" >
								<input type="radio" name="objType" value="clazz" title="限定类" checked>
								<input type="radio" name="objType" value="concrete" title="限定实例" > 
						   </c:when>
						   <c:when test="${!empty objType && objType =='concrete'}"> 
						   		<input type="radio" name="objType" value="no" title="不限定" >
								<input type="radio" name="objType" value="clazz" title="限定类" >
								<input type="radio" name="objType" value="concrete" title="限定实例" checked> 
						   </c:when>
						   <c:otherwise>
						   		 <input type="radio" name="objType" value="no" title="不限定" checked>
								<input type="radio" name="objType" value="clazz" title="限定类" >
								<input type="radio" name="objType" value="concrete" title="限定实例" >
						   </c:otherwise>
					</c:choose>
				 </div>
			</td>
		</tr>
		<tr><td colspan="2"><blockquote class="layui-elem-quote">相关行为监控</blockquote></td></tr>
		<tr>
			<td>
				<label class="layui-form-label">限定父类：</label>
				<div class="layui-input-block">
				    <input type="text" id="actParents" name="title"   value="${actParents}"  placeholder="请填写父类，用英文分号（;）隔开" autocomplete="off" class="layui-input">
				 </div>
			</td>
			<td>
				<span class="layui-btn layui-btn-normal">选择</span>
			</td>
		</tr>
		<tr>
			<td>
				<label class="layui-form-label">限定概念：</label>
			    <div class="layui-input-block">
			      <textarea name="desc" id="actConcepts" value="${actConcepts}"  placeholder="请填写概念，用英文分号（;）隔开" class="layui-textarea">${actConcepts}</textarea>
			    </div>
			</td>
			<td>
				<span class="layui-btn layui-btn-normal">选择</span>
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<label class="layui-form-label">限定类型：</label>
				<div class="layui-input-block">
					<c:choose>  					  
						   <c:when test="${!empty actType && actType =='clazz'}">
						   		<input type="radio" name="actType" value="no" title="不限定" >
								<input type="radio" name="actType" value="clazz" title="限定类" checked>
								<input type="radio" name="actType" value="concrete" title="限定实例" > 
						   </c:when>
						   <c:when test="${!empty actType && actType =='concrete'}"> 
						   		<input type="radio" name="actType" value="no" title="不限定" >
								<input type="radio" name="actType" value="clazz" title="限定类" >
								<input type="radio" name="actType" value="concrete" title="限定实例" checked> 
						   </c:when>
						   <c:otherwise>
						   		 <input type="radio" name="actType" value="no" title="不限定" checked>
								<input type="radio" name="actType" value="clazz" title="限定类" >
								<input type="radio" name="actType" value="concrete" title="限定实例" >
						   </c:otherwise>
					</c:choose>
				 </div>
			</td>
		</tr>
	   <tr><td colspan="2"><blockquote class="layui-elem-quote">相关时间监控</blockquote></td></tr>
		<tr>
			<td>
				<label class="layui-form-label">限定父类：</label>
				<div class="layui-input-block">
				    <input type="text" id="timeParents" name="title"   value="${timeParents}"  placeholder="请填写父类，用英文分号（;）隔开" autocomplete="off" class="layui-input">
				 </div>
			</td>
			<td>
				<span class="layui-btn layui-btn-normal">选择</span>
			</td>
		</tr>
		<tr>
			<td>
				<label class="layui-form-label">限定概念：</label>
			    <div class="layui-input-block">
			      <textarea name="desc" id="timeConcepts" value="${timeConcepts}"  placeholder="请填写概念，用英文分号（;）隔开" class="layui-textarea">${timeConcepts}</textarea>
			    </div>
			</td>
			<td>
				<span class="layui-btn layui-btn-normal">选择</span>
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<label class="layui-form-label">限定类型：</label>
				<div class="layui-input-block">
					<c:choose>  					  
						   <c:when test="${!empty timeType && timeType =='clazz'}">
						   		<input type="radio" name="timeType" value="no" title="不限定" >
								<input type="radio" name="timeType" value="clazz" title="限定类" checked>
								<input type="radio" name="timeType" value="concrete" title="限定实例" > 
						   </c:when>
						   <c:when test="${!empty timeType && timeType =='concrete'}"> 
						   		<input type="radio" name="timeType" value="no" title="不限定" >
								<input type="radio" name="timeType" value="clazz" title="限定类" >
								<input type="radio" name="timeType" value="concrete" title="限定实例" checked> 
						   </c:when>
						   <c:otherwise>
						   		 <input type="radio" name="timeType" value="no" title="不限定" checked>
								<input type="radio" name="timeType" value="clazz" title="限定类" >
								<input type="radio" name="timeType" value="concrete" title="限定实例" >
						   </c:otherwise>
					</c:choose>
				 </div>
			</td>
		</tr>
		<tr><td colspan="2"><blockquote class="layui-elem-quote">相关空间监控</blockquote></td></tr>
		<tr>
			<td>
				<label class="layui-form-label">限定父类：</label>
				<div class="layui-input-block">
				    <input type="text" id="spaceParents" name="title"   value="${spaceParents}"  placeholder="请填写父类，用英文分号（;）隔开" autocomplete="off" class="layui-input">
				 </div>
			</td>
			<td>
				<span class="layui-btn layui-btn-normal">选择</span>
			</td>
		</tr>
		<tr>
			<td>
				<label class="layui-form-label">限定概念：</label>
			    <div class="layui-input-block">
			      <textarea name="desc" id="spaceConcepts" value="${spaceConcepts}"  placeholder="请填写概念，用英文分号（;）隔开" class="layui-textarea">${spaceConcepts}</textarea>
			    </div>
			</td>
			<td>
				<span class="layui-btn layui-btn-normal">选择</span>
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<label class="layui-form-label">限定类型：</label>
				<div class="layui-input-block">
					<c:choose>  					  
						   <c:when test="${!empty spaceType && spaceType =='clazz'}">
						   		<input type="radio" name="spaceType" value="no" title="不限定" >
								<input type="radio" name="spaceType" value="clazz" title="限定类" checked>
								<input type="radio" name="spaceType" value="concrete" title="限定实例" > 
						   </c:when>
						   <c:when test="${!empty spaceType && spaceType =='concrete'}"> 
						   		<input type="radio" name="spaceType" value="no" title="不限定" >
								<input type="radio" name="spaceType" value="clazz" title="限定类" >
								<input type="radio" name="spaceType" value="concrete" title="限定实例" checked> 
						   </c:when>
						   <c:otherwise>
						   		 <input type="radio" name="spaceType" value="no" title="不限定" checked>
								<input type="radio" name="spaceType" value="clazz" title="限定类" >
								<input type="radio" name="spaceType" value="concrete" title="限定实例" >
						   </c:otherwise>
					</c:choose>
				 </div>
			</td>
		</tr>
		<tr><td colspan="2"><blockquote class="layui-elem-quote">相关问题目标监控</blockquote></td></tr>
		<tr>
			<td>
				<label class="layui-form-label">限定父类：</label>
				<div class="layui-input-block">
				    <input type="text" id="proParents" name="title"   value="${proParents}"  placeholder="请填写父类，用英文分号（;）隔开" autocomplete="off" class="layui-input">
				 </div>
			</td>
			<td>
				<span class="layui-btn layui-btn-normal">选择</span>
			</td>
		</tr>
		<tr>
			<td>
				<label class="layui-form-label">限定概念：</label>
			    <div class="layui-input-block">
			      <textarea name="desc" id="proConcepts" value="${proConcepts}"  placeholder="请填写概念，用英文分号（;）隔开" class="layui-textarea">${proConcepts}</textarea>
			    </div>
			</td>
			<td>
				<span class="layui-btn layui-btn-normal">选择</span>
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<label class="layui-form-label">限定类型：</label>
				<div class="layui-input-block">
					<c:choose>  					  
						   <c:when test="${!empty proType && proType =='clazz'}">
						   		<input type="radio" name="proType" value="no" title="不限定" >
								<input type="radio" name="proType" value="clazz" title="限定类" checked>
								<input type="radio" name="proType" value="concrete" title="限定实例" > 
						   </c:when>
						   <c:when test="${!empty proType && proType =='concrete'}"> 
						   		<input type="radio" name="proType" value="no" title="不限定" >
								<input type="radio" name="proType" value="clazz" title="限定类" >
								<input type="radio" name="proType" value="concrete" title="限定实例" checked> 
						   </c:when>
						   <c:otherwise>
						   		 <input type="radio" name="proType" value="no" title="不限定" checked>
								<input type="radio" name="proType" value="clazz" title="限定类" >
								<input type="radio" name="proType" value="concrete" title="限定实例" >
						   </c:otherwise>
					</c:choose>
				 </div>
			</td>
		</tr>
		<tr><td colspan="2"><blockquote class="layui-elem-quote">关键词监控</blockquote></td></tr>
		<tr>
			<td>
				<label class="layui-form-label">限定概念：</label>
			    <div class="layui-input-block">
			      <textarea name="desc" id="keywordConcepts" value="${keywordConcepts}"  placeholder="请填写概念，用英文分号（;）隔开" class="layui-textarea">${keywordConcepts}</textarea>
			    </div>
			</td>
			<td>
				<span class="layui-btn layui-btn-normal">选择</span>
			</td>
		</tr>
	</table> 
		<center>
			<span class="layui-btn layui-btn layui-btn-small foot"  id="save" >确定</span>
			<span class="layui-btn layui-btn-danger layui-btn-small foot" id="cancel" >取消</span>
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
	   var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
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
		          ,title:"提醒"
		          ,content: '<div style="padding: 20px 100px;">操作成功!</div>'
		          ,btn: '关闭'
		          ,btnAlign: 'c' //按钮居中
		          ,shade: 0 //不显示遮罩
		          ,yes: function(){
		        	closeCurrentWin();
		          }
		        });
		}else{
			error("操作失败！");
		}
	},"json"); 
});

</script>
</html>