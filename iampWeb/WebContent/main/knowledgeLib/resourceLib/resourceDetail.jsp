<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>�½�/�޸���Ϣ��Դά��</title>
</head> 
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="format-detection" content="telephone=no">
<link rel="stylesheet" href="/iamp/js/layui/css/layui.css" media="all" />
<link rel="stylesheet" href="/iamp/js/layui/css/global.css" media="all">
<link type="text/css" href="/iamp/js/codemirror-5.2/lib/codemirror.css" rel="stylesheet" >
<link type="text/css" href="/iamp/js/codemirror-5.2/addon/hint/show-hint.css" rel="stylesheet"></link>
<script src="/iamp/js/codemirror-5.2/lib/codemirror.js"></script>
<script src="/iamp/js/codemirror-5.2/mode/python/python.js"></script>
<script src="/iamp/js/codemirror-5.2/addon/hint/show-hint.js"></script>
<script type="text/javascript" src="/iamp/js/layui/layui.js"></script>
<script src="/iamp/js/jquery-1.7.2.min.js"></script>
<script src="/iamp/js/jquery.form.js"></script>
<script type="text/javascript" charset="gbk" src="/iamp/js/ueeditor/ueditor.config.js"></script>
<script type="text/javascript" charset="gbk" src="/iamp/js/ueeditor/ueditor.all.min.js"> </script>
<script type="text/javascript" charset="gbk" src="/iamp/js/ueeditor/lang/zh-cn/zh-cn.js"></script>
<style type="text/css">
.foot{
	position:relative;
	bottom:5px;  
	overflow:hidden;
}
</style>
<body id="bodyT">	
<div class="layui-tab" lay-filter="tab">
  <ul class="layui-tab-title">
    <li class="layui-this">����&��ǩ</li>
    <li class="resourceDiv">��Դ����</li>
    <li class="attachmentDiv">����</li>
<!--     <li >������ǩ</li> -->
  </ul>
  <div class="layui-tab-content">
    <div class="layui-tab-item layui-show">
 	<form class="layui-form" action="">
 	<blockquote class="layui-elem-quote">��������</blockquote>
 	<table lay-skin="nob" class="layui-table">
		 <tr>
		  	<td>
				<label class="layui-form-label" style="width:100px;margin-left:-20px;">Ψһ��Դ�ţ�</label>
			    <div class="layui-input-block">
					<span class="layui-form-label" style="width:20%;text-align:left">${obj.id}</span>
					<input type="hidden" value="${obj.id}" id="resourceId">
					<input type="hidden" value="${catalogId}" id="catalogId">
					<input type="hidden" value="${catalogNum}" id="catalogNum">
					
			    </div>
	    	</td>
		  	<td>
				<label class="layui-form-label">�䲼�ĺţ�</label>
			    <div class="layui-input-block">
			      <input type="text" id="issuedNum" name="title"  value="${!empty obj? obj.issuedNum:''}"  placeholder="" autocomplete="off" class="layui-input">
			    </div>
	    	</td>
    	</tr>
     	<tr>
    		<td >
    			<label class="layui-form-label"><span style="color:red">*</span>���⣺</label>
			    <div class="layui-input-block">
			      <input type="text" id="title" name="title" required value="${!empty obj? obj.title:''}" lay-verify="required" placeholder="" autocomplete="off" class="layui-input">
			    </div>
    		</td>
    		<td>
    			<label class="layui-form-label">�汾�ţ�</label>
			    <div class="layui-input-block">
					<input type="text" id="version" name="title"  value="${obj.version!=null ? obj.version:'1.0'}"  placeholder="" autocomplete="off" class="layui-input">
			    </div>
    		</td>
    	</tr>
		<tr >
		  	<td>
				<label class="layui-form-label">�����ߣ�</label>
			    <div class="layui-input-block">
					<input type="text" id="publisher" name="title"  value="${!empty obj? obj.publisher:''}"  placeholder="" autocomplete="off" class="layui-input">
			    </div>
	    	</td>
		  	<td>
				<label class="layui-form-label">�������ڣ�</label>
					<div class="layui-input-block" >
					  <input class="layui-input" id="publishDate" onclick="layui.laydate({elem: this, festival: true,istime: true,format:'YYYY-MM-DD'})" value="${publishDate}" style="width:70%;display:inline;">
					  <input class="layui-input" id="publishDate2" type="text" style="display:inline;width:70%;">
					  <span class="layui-btn layui-btn-danger btn layui-btn-small" id="changeDateDiv">�ֶ���д</span>
					</div>
									
	    	</td>
	    </tr>
		<tr >
		  	<td>
				<label class="layui-form-label">��������</label>
			    <div class="layui-input-block">
			      <input type="text" id="belongDomainList"  name="title"  value="${!empty obj.domainstr? obj.domainstr:''}"  placeholder="" autocomplete="off" class="layui-input">
			    </div>
	    	</td>
	    	<td>
	    		<label class="layui-form-label"><span style="color:red">*</span>���ȣ�</label>
			    <div class="layui-input-block">
			    	<select lay-filter="granularity" lay-verify="required" name="type"  class="granularity" id="granularity">
			       		<c:forEach var="list" items="${granularityList}" varStatus="status">
			       			<c:choose>
			       			  <c:when test="${ obj.granularity !=null && list == obj.granularity }"> 
							   		<option value="${list}" selected>${list}</option>
							   </c:when> 
							   <c:when test="${obj.granularity == null  && list =='ƪ'}"> 
							   			<option value="${list}" selected>${list}</option>
							  	 </c:when>
							   <c:otherwise>
			       			 		<option value="${list}">${list}</option>
			       			  </c:otherwise>
			       			</c:choose>
			       		 </c:forEach>
			      	</select>
			    </div>
	    	</td>
	    </tr>
		<tr >
		  	<td >
				<label class="layui-form-label"><span style="color:red">*</span>��ã�</label>
			    <div class="layui-input-block">
					<input type="text" id="genreList" name="title" required value="${!empty obj.genre? obj.genre:''}" lay-verify="required" placeholder="" autocomplete="off" class="layui-input">
			    </div>
	    	</td>
	    	<td >
				<label class="layui-form-label">��Դ��</label>
			    <div class="layui-input-block">
					<input type="text" id="source" name="title"  value="${!empty obj? obj.source:''}"  placeholder="" autocomplete="off" class="layui-input">
			    </div>
	    	</td>
	    </tr>
		<tr >
		  	<td>
				<label class="layui-form-label"><span style="color:red">*</span>��Чʱ�䣺</label>
				    <div class="layui-input-block">
			      		<select lay-filter="validTime" lay-verify="required" id="validTime"  >
			      		<c:choose>     
		       		 		<c:when test="${validTime !=null && validTime !=''}"> 
		       		 			<option value="nChange" >��Ч�ڣ�${validTime}</option>
		       		 		</c:when>
		       		 	</c:choose>
	       		 			     <option value="all" >����</option>
							   	 <option value="1" >����</option>
							   	 <option value="2">1��</option>
							   	 <option value="3">3��</option>
							   	 <option value="5">5��</option>
							   	 <option value="10">10��</option>
				      	 </select>
				    </div>
	    	</td>
		  	<td>
				<label class="layui-form-label">ʧЧ״̬��</label>
					  <div class="layui-input-block">
					  	<c:choose>     
		       		 		<c:when test="${ obj.status!=null && obj.status=='0'}"> 
		       		 				<input type="radio" name="status" value="1" title="����" >
					   				 <input type="radio" name="status" value="0" title="����" checked>
					    			<input type="radio" name="status" value="-1" title="�ϳ�" >
		       		 		</c:when>
		       		 		<c:when test="${obj.status!=null && obj.status=='-1'}"> 
		       		 				 <input type="radio" name="status" value="1" title="����" >
					   				 <input type="radio" name="status" value="0" title="����" >
					    			<input type="radio" name="status" value="-1" title="�ϳ�" checked>
		       		 		</c:when>
		       		 		<c:otherwise >
		       		 				 <input type="radio" name="status" value="1" title="����" checked>
					    			<input type="radio" name="status" value="0" title="����" >
					    			<input type="radio" name="status" value="-1" title="�ϳ�" >
		       		 		</c:otherwise>
		       		 	</c:choose>
					  </div>
	    	</td>
	    </tr> 
	    <tr>
	    	<td >
   			<label class="layui-form-label">Ȩ�޿��ƣ�</label>
			  <div class="layui-input-block">
			  	<c:choose>     
       		 		<c:when test="${ obj.authority!=null && obj.authority=='1'}"> 
       		 				<input type="radio" name="authority" value="1" title="����" checked>
			   				 <input type="radio" name="authority" value="2" title="���е�¼�û�" >
			    			<input type="radio" name="authority" value="3" title="��Ȩ" >
       		 		</c:when>
       		 		<c:when test="${obj.authority!=null && obj.authority=='2'}"> 
       		 				 <input type="radio" name="authority" value="1" title="����" >
			   				 <input type="radio" name="authority" value="2" title="���е�¼�û�" checked>
			    			<input type="radio" name="authority" value="3" title="��Ȩ" >
       		 		</c:when>
       		 		<c:when test="${obj.authority!=null && obj.authority=='3'}"> 
       		 				 <input type="radio" name="authority" value="1" title="����" >
			   				 <input type="radio" name="authority" value="2" title="���е�¼�û�" >
			    			<input type="radio" name="authority" value="3" title="��Ȩ" checked>
       		 		</c:when>
       		 		<c:otherwise >
       		 				 <input type="radio" name="authority" value="1" title="����" checked>
			    			<input type="radio" name="authority" value="2" title="���е�¼�û�" >
			    			<input type="radio" name="authority" value="3" title="��Ȩ" >
       		 		</c:otherwise>
       		 	</c:choose>
			  </div>
	    	</td>
	    	<td>
	    	  <label class="layui-form-label">����״̬��</label>
			  <div class="layui-input-block">
			  	<c:choose>     
       		 		<c:when test="${ obj.domainConfirmStatus!=null && obj.domainConfirmStatus=='1'}">
       		 				 <input type="radio" name="domainConfirmStatus" value="0" title="δȷ��" > 
       		 				<input type="radio" name="domainConfirmStatus" value=1 title="ȷ��" checked>
       		 		</c:when>
       		 		<c:otherwise >
       		 				<input type="radio" name="domainConfirmStatus" value="0" title="δȷ��" checked >
       		 				 <input type="radio" name="domainConfirmStatus" value="1" title="ȷ��" >
       		 		</c:otherwise>
       		 	</c:choose>
			  </div>
	    	</td>
	    </tr>
	    <tr>
	    	<td>
	    	  <label class="layui-form-label"><span style="color:red">*</span>������ࣺ</label>
			    <div class="layui-input-block">
					<input type="text" id="classify" name="classify" required value="${!empty obj? obj.classify:''}" lay-verify="required" placeholder="" autocomplete="off" class="layui-input">
			    </div>
	    	</td>
	    	<td></td>
	    </tr>
		<tr >
		  	<td colspan="2">
			  <div class="layui-form-item layui-form-text">
			    <label class="layui-form-label">ժҪ��</label>
			    <div class="layui-input-block">
			      <textarea name="desc" id="abstractText" value="${!empty obj? obj.abstractText:''}"  placeholder="����������" class="layui-textarea">${!empty obj? obj.abstractText:''}</textarea>
			    </div>
			  </div>
  	    	</td>
	    </tr>
		<tr >
		  	<td colspan="2">
				<label class="layui-form-label">ԭ�ĵ�ַ��</label>
			    <div class="layui-input-inline" >
			      <input  type="text" id="originalAddress" name="title"  value="${!empty obj? obj.originalAddress:''}"  placeholder="��������������" autocomplete="off" class="layui-input" style="width:700px;float:left;position:relative;">
			   		<span class="layui-btn layui-btn-small layui-btn-normal"  id="look" style="float:left;position:relative;margin:3px">�鿴</span>
			    </div>
  	    	</td>
	    </tr>
    </table>
	<blockquote class="layui-elem-quote">��չ����</blockquote>
	 <table lay-skin="nob" class="layui-table" id="paramTable">
	  <colgroup>
	    <col width="150">
	    <col width="200">
	    <col>
	  </colgroup>
	  <thead>
	    <tr>
	     <th style="width:100px;">������</th>
	   	 <th style="width:100px;">��������</th><th style="width:100px;">��������</th>
	     <th >ֵ</th><th><i class="layui-icon" id="addParam" style="font-size: 15px;color: #009688;">&#xe608;</i></th>
	    </tr> 
	  </thead>
	  <tbody id="attrListTbody">
	  	<c:choose>
	  		<c:when test="${method == 'create'}"><!-- ������Lib������ -->
	  					 		<c:forEach var="extendAttrs" items="${lib}" varStatus="status">
				  <tr>
				  		<td>
				  			<input type="text" name="attrName" name="title" required value="${!empty obj? extendAttrs.attrName:''}" lay-verify="required"  autocomplete="off" class="layui-input">
				  		</td>
				  		<td>
				  			<input type="text" name="attrCName" name="title" required value="${!empty obj? extendAttrs.attrCName:''}" lay-verify="required"  autocomplete="off" class="layui-input">
				  		</td>
				  		<td>
						 	<select lay-filter="paramType" lay-verify="required" name="type"  class="paramType">
					       		 <option value="" >��ѡ��</option>
					       		 <c:choose>     
					       		 	<c:when test="${!empty extendAttrs.type && extendAttrs.type=='int'}"> 
								  		 <option value="int"  selected = "selected">����</option>
								  		 <option value="String" >�ַ���</option>
							       		 <option value="longtext" >���ı�</option>
							       		 <option value="date" >����</option>
							       		 <option value="array" >����</option>
								   </c:when>     
			 		       		 	<c:when test="${!empty extendAttrs.type && extendAttrs.type=='String'}"> 
								  		 <option value="int"  >����</option>
								  		 <option value="String" selected = "selected">�ַ���</option>
							       		 <option value="longtext" >���ı�</option>
							       		 <option value="date" >����</option>
							       		 <option value="array" >����</option>
								   </c:when>     
					       		 	<c:when test="${!empty extendAttrs.type && extendAttrs.type=='longtext'}"> 
								  		 <option value="int"  >����</option>
								  		 <option value="String" >�ַ���</option>
							       		 <option value="longtext" selected = "selected">���ı�</option>
							       		 <option value="date" >����</option>
							       		 <option value="array" >����</option>
								   </c:when>     
					       		 	<c:when test="${!empty extendAttrs.type && extendAttrs.type=='date'}"> 
								  		 <option value="int" >����</option>
								  		 <option value="String" >�ַ���</option>
							       		 <option value="longtext" >���ı�</option>
							       		 <option value="date" selected = "selected">����</option>
							       		 <option value="array" >����</option>
								   </c:when>     
					       		 	<c:when test="${!empty extendAttrs.type && extendAttrs.type=='array'}"> 
								  		 <option value="int"  >����</option>
								  		 <option value="String" >�ַ���</option>
							       		 <option value="longtext" >���ı�</option>
							       		 <option value="date" >����</option>
							       		 <option value="array" selected = "selected">����</option>
								   </c:when>   
								   <c:otherwise>
										 <option value="int" >����</option>
							       		 <option value="String" >�ַ���</option>
							       		 <option value="longtext" >���ı�</option>
							       		 <option value="date" >����</option>
							       		 <option value="array" >����</option>
								   </c:otherwise>  
					       		</c:choose>
					      	 </select>
				  		</td>
				  		<td>
				  			<input type="text" name="value" name="title"  value="${!empty obj? extendAttrs.value:''}"   autocomplete="off" class="layui-input">
				  		</td>
				  		<td>
				  			<i class="layui-icon removeParam" style="font-size: 15px; color: #1E9FFF;">&#x1006;</i>
				  		</td>
				  	</tr>
				  </c:forEach>
	  		</c:when>
	  		<c:otherwise><!-- �޸Ĵ�VO������ -->
		 		<c:forEach var="extendAttrs" items="${obj.extendAttrs}" varStatus="status">
				  <tr>
				  		<td>
				  			 <input type="text" name="attrName" name="attrName" required value="${!empty obj? extendAttrs.attrName:''}" lay-verify="required"  autocomplete="off" class="layui-input">
				  		</td>
				  		<td>
				  			<input type="text" name="attrCName" name="attrCName" required value="${!empty obj? extendAttrs.attrCName:''}" lay-verify="required"  autocomplete="off" class="layui-input">
				  		</td>
				  		<td>
						 	<select lay-filter="paramType" lay-verify="required" name="type"  class="paramType">
					       		 <option value="" >��ѡ��</option>
					       		 <c:choose>     
					       		 	<c:when test="${!empty extendAttrs.type && extendAttrs.type=='int'}"> 
								  		 <option value="int"  selected = "selected">����</option>
								  		 <option value="string" >�ַ���</option>
							       		 <option value="longtext" >���ı�</option>
							       		 <option value="date" >����</option>
							       		 <option value="array" >����</option>
								   </c:when>     
			 		       		 	<c:when test="${!empty extendAttrs.type && extendAttrs.type=='string'}"> 
								  		 <option value="int"  >����</option>
								  		 <option value="string" selected = "selected">�ַ���</option>
							       		 <option value="longtext" >���ı�</option>
							       		 <option value="date" >����</option>
							       		 <option value="array" >����</option>
								   </c:when>     
					       		 	<c:when test="${!empty extendAttrs.type && extendAttrs.type=='longtext'}"> 
								  		 <option value="int"  >����</option>
								  		 <option value="string" >�ַ���</option>
							       		 <option value="longtext" selected = "selected">���ı�</option>
							       		 <option value="date" >����</option>
							       		 <option value="array" >����</option>
								   </c:when>     
					       		 	<c:when test="${!empty extendAttrs.type && extendAttrs.type=='date'}"> 
								  		 <option value="int" >����</option>
								  		 <option value="string" >�ַ���</option>
							       		 <option value="longtext" >���ı�</option>
							       		 <option value="date" selected = "selected">����</option>
							       		 <option value="array" >����</option>
								   </c:when>     
					       		 	<c:when test="${!empty extendAttrs.type && extendAttrs.type=='array'}"> 
								  		 <option value="int"  >����</option>
								  		 <option value="string" >�ַ���</option>
							       		 <option value="longtext" >���ı�</option>
							       		 <option value="date" >����</option>
							       		 <option value="array" selected = "selected">����</option>
								   </c:when>   
								   <c:otherwise>
										 <option value="int" >����</option>
							       		 <option value="string" >�ַ���</option>
							       		 <option value="longtext" >���ı�</option>
							       		 <option value="date" >����</option>
							       		 <option value="array" >����</option>
								   </c:otherwise>  
					       		</c:choose>
					      	 </select>
				  		</td>
				  		<td>
				  			 <c:choose>     
					       		 <c:when test="${!empty extendAttrs.type && extendAttrs.type=='date'}"> 
					       			 <input class="layui-input" name="value"  onclick="layui.laydate({elem: this, festival: true,istime: true,format:'YYYY-MM-DD'})" value="${!empty obj? extendAttrs.value:''}" >
					       		 </c:when>
					       		 <c:otherwise>
					       		 	<input type="text" name="value" name="title"  value="${!empty obj? extendAttrs.value:''}"   autocomplete="off" class="layui-input">
					       		 </c:otherwise>
					       	</c:choose>
				  		</td>
				  		<td>
				  			<i class="layui-icon removeParam" style="font-size: 15px; color: #1E9FFF;">&#x1006;</i>
				  		</td>
				  	</tr>
			  </c:forEach>
	  		</c:otherwise>
	  	</c:choose>

		</tbody>
	  </table>

	<blockquote class="layui-elem-quote">������ǩ <span class="layui-btn layui-btn-small" style="margin-left:10px" id="resetTag">���´��ǩ</span></blockquote>
	<div id="tagDiv">
	<c:forEach var="tag" items="${obj.tag}" varStatus="status">
		<c:choose>     
		 	<c:when test="${ tag.key!=null && tag.key=='domains' && tag.value != '[]'}"> 
		 	��������  <c:set var="tagList" value="${tag.value}" /> 
					<c:forEach var="tagVal" items="${tagList}" varStatus="status">
						<span class="layui-btn layui-btn-small domains" parents="${tagVal.parentNamesStr}" name="${tagVal.type}" value="${tagVal.similarity}" style="margin:10px;">${tagVal.content}</span><i class="layui-icon removeTag" style="font-size: 15px; color: #1E9FFF;">&#x1006;</i>
					</c:forEach><br>
	 		</c:when>
		 	<c:when test="${ tag.key!=null && tag.key=='acts' && tag.value != '[]'}"> 
		 	������Ϊ��  <c:set var="tagList" value="${tag.value}" /> 
					<c:forEach var="tagVal" items="${tagList}" varStatus="status">
						<span class="layui-btn layui-btn-small acts" name="${tagVal.type}" value="${tagVal.similarity}" style="margin:10px;">${tagVal.content}</span><i class="layui-icon removeTag" style="font-size: 15px; color: #1E9FFF;">&#x1006;</i>
					</c:forEach><br>
	 		</c:when>
		 	<c:when test="${ tag.key!=null && tag.key=='organdpersons' && tag.value != '[]'}"> 
		 	�������壺  <c:set var="tagList" value="${tag.value}" /> 
					<c:forEach var="tagVal" items="${tagList}" varStatus="status">
						<span class="layui-btn layui-btn-small organdpersons" name="${tagVal.type}" value="${tagVal.similarity}" style="margin:10px;">${tagVal.content}</span><i class="layui-icon removeTag" style="font-size: 15px; color: #1E9FFF;">&#x1006;</i>
					</c:forEach><br>
	 		</c:when>
		 	<c:when test="${ tag.key!=null && tag.key=='objects' && tag.value != '[]'}"> 
		 	�������壺  <c:set var="tagList" value="${tag.value}" /> 
					<c:forEach var="tagVal" items="${tagList}" varStatus="status">
						<span class="layui-btn layui-btn-small objects" style="margin:10px;" parents="${tagVal.parentNamesStr}" name="${tagVal.type}" value="${tagVal.similarity}">${tagVal.content}</span><i class="layui-icon removeTag" style="font-size: 15px; color: #1E9FFF;">&#x1006;</i>
					</c:forEach><br>
	 		</c:when>
		 	<c:when test="${ tag.key!=null && tag.key=='spaces' && tag.value != '[]'}"> 
		 	�����ռ䣺  <c:set var="tagList" value="${tag.value}" /> 
					<c:forEach var="tagVal" items="${tagList}" varStatus="status">
						<span class="layui-btn layui-btn-small spaces" style="margin:10px;" parents="${tagVal.parentNamesStr}" name="${tagVal.type}" value="${tagVal.similarity}">${tagVal.content}</span><i class="layui-icon removeTag" style="font-size: 15px; color: #1E9FFF;">&#x1006;</i>
					</c:forEach><br>
	 		</c:when>
		 	<c:when test="${ tag.key!=null && tag.key=='times' && tag.value != '[]'}"> 
		 	����ʱ��:  <c:set var="tagList" value="${tag.value}" /> 
					<c:forEach var="tagVal" items="${tagList}" varStatus="status">
						<span class="layui-btn layui-btn-small times" style="margin:10px;" parents="${tagVal.parentNamesStr}" name="${tagVal.type}" value="${tagVal.similarity}">${tagVal.content}</span><i class="layui-icon removeTag" style="font-size: 15px; color: #1E9FFF;">&#x1006;</i>
					</c:forEach><br>
	 		</c:when>
		 	<c:when test="${ tag.key!=null && tag.key=='keywords' && tag.value != '[]'}"> 
		 	�����ؼ��ʣ�  <c:set var="tagList" value="${tag.value}" /> 
					<c:forEach var="tagVal" items="${tagList}" varStatus="status">
						<span class="layui-btn layui-btn-small keywords" style="margin:10px;" parents="${tagVal.parentNamesStr}" name="${tagVal.type}"value="${tagVal.similarity}">${tagVal.content}</span><i class="layui-icon removeTag" style="font-size: 15px; color: #1E9FFF;">&#x1006;</i>
					</c:forEach><br>
	 		</c:when>
		 	<c:when test="${ tag.key!=null && tag.key=='hierarchy' && tag.value != '[]'}"> 
		 	���������㼶��  <c:set var="tagList" value="${tag.value}" /> 
					<c:forEach var="tagVal" items="${tagList}" varStatus="status">
						<span class="layui-btn layui-btn-small hierarchy" style="margin:10px;"parents="${tagVal.parentNamesStr}"  name="${tagVal.type}" value="${tagVal.similarity}">${tagVal.content}</span><i class="layui-icon removeTag" style="font-size: 15px; color: #1E9FFF;">&#x1006;</i>
					</c:forEach><br>
	 		</c:when>
		</c:choose>	
	</c:forEach>
	</div>
	  <center>
			<button class="layui-btn layui-btn layui-btn-small foot" lay-submit="" lay-filter="save" id="save" >ȷ��</button>
			<button class="layui-btn layui-btn-danger layui-btn-small foot" id="cancel" >ȡ��</button>
	  </center>
	</form> 
	</div>
	
    <div class="layui-tab-item">
		<script id="content" name="content" type="text/plain" style="height:500px;width:100%"></script>
		<input id="htmlContent" type="hidden" value='${obj.htmlContent}'> 
		<input id="pureContent" type="hidden" value='${obj.content}'> 
		<input id="oldTitle" type="hidden" value='${obj.title}'> 
	</div>
	<div class="layui-tab-item">
		<form class="layui-form" action="#" >
			<span class="layui-btn  layui-btn-normal btn layui-btn-small" id="upload">�ϴ�</span>
			<span class="layui-btn layui-btn-danger btn layui-btn-small" id="delFile">ɾ��</span>
			<span class="layui-btn  layui-btn-warm btn layui-btn-small" id="download">��������</span>
		</form>
	<table class="layui-table" lay-skin="line">
	  <colgroup>
	  	<col width="30">
	    <col width="200">
	    <col width="70">
	    <col width="70">
	    <col width="100">
	    <col width="150">
	    <col>
	  </colgroup>
	  <thead>
	    <tr>
	     <th><!-- <input type="checkbox"> --></th><th>����</th><th>����</th><th>��ʽ</th><th>��С</th>
	     <th>�ϴ�ʱ��</th><th>�����</th>
	    </tr> 
	  </thead>
	  <tbody id="fileInfoList">
	  <c:if test="${!empty obj && !empty obj.fileInfos}">
	  	<c:forEach var = "fileInfo" items="${obj.fileInfos}">
	  	<tr>
	  		<td><input type="checkbox" id="${fileInfo.id}" name="fileId"></td>
	  		<td>${fileInfo.title}</td>
	  		<td>
	  			${fileInfo.fileType == 1? '����':'����'}
	  		</td>
	  		<td>${fileInfo.fileExt}</td>
	  		<td><fmt:formatNumber value="${fileInfo.fileSize/1024}" pattern="#,###.#" />KB</td>
	  		<td><fmt:formatDate value="${fileInfo.createDate}" pattern="yyyy-MM-dd HH:mm" type="date"/></td>
	  		<td><c:forEach var="word" items="${fileInfo.keywords}" varStatus="index">
	  			<c:choose>
	  				<c:when test="${index.last}">
	  					${word}
	  				</c:when>
	  				<c:otherwise>
	  					${word};
	  				</c:otherwise>
	  			</c:choose>
	  			</c:forEach>
	  		</td>
  		</tr>
	  	</c:forEach>
	  </c:if>
	  </tbody>
	  </table>
	</div>
	<form id="downloadForm" enctype="multipart/form-data" method="post">
		<input type="hidden"  name="arr" id="downloadData"/>
	</form>
  </div>
</div>
</body>
<script>
var ajaxSave = '/iamp/resource/save.do';
var ajaxGetDomain = '/iamp/resource/getDomain.do';
var ajaxGetConcepts = '/iamp/resource/getConcepts.do';
var layer;
var ue;
var jsonTag = new Object();
var tagCount = 0;
var fileExt;
var pureContent;
var oldTitle;
$(function(){
	fileExt = '${fileExt}';
	if(fileExt == "link"){
		//�������ĺ͸���tab��
		$(".resourceDiv").hide();
		$(".attachmentDiv").hide();
	}
	$("#publishDate2").hide();
	$("#changeDateDiv").on("click",function(){
		var display = $("#publishDate").css('display');
		if(display == 'none'){
			$("#changeDateDiv").html("������д");
			$("#publishDate").show();
			$("#publishDate2").hide();
		}else{
			$("#changeDateDiv").html("�ֶ���д");
			$("#publishDate").hide();
			$("#publishDate2").show();
		}
	});
	
	/* ʵ�����༭�� */ 
	
	ue = UE.getEditor('content', {
	toolbar: [
	['fullscreen', 'source', 'undo', 'redo', 'bold', 'italic', 
	'underline','fontborder', 'backcolor', 'fontsize', 'fontfamily', 
	'justifyleft', 'justifyright','justifycenter', 'justifyjustify', 
	'strikethrough','superscript', 'subscript', 'removeformat',
	'formatmatch','autotypeset', 'blockquote', 'pasteplain', '|',
	'forecolor', 'backcolor','insertorderedlist', 'insertunorderedlist', 
	'selectall', 'cleardoc', 'link', 'unlink','emotion', 'help']
	]
	});
	
 	 var htmlContent = $("#htmlContent").val();
	if(htmlContent != null && htmlContent !=""){
	 	ue.ready(function() {//�༭����ʼ������ٸ�ֵ
	 		ue.execCommand('insertHtml', htmlContent);  //��ֵ��UEditor
		});
	}
	/*layUI��ز��� ��Щ��Ҫ��������ʹ��  */
	layui.use(['form','element','layer','laydate'], function(){
		  var laydate = layui.laydate;
		  var start = {
			format: 'YYYY-MM-DD'	  
		    ,min: '1900-01-01 00:00:00'
		    ,max: '2100-06-16 23:59:59'
		    ,istoday: true
		    ,choose: function(datas){
		      end.min = datas; //��ʼ��ѡ�ú����ý����յ���С����
		      end.start = datas //�������յĳ�ʼֵ�趨Ϊ��ʼ��
		    }
		  };
		  document.getElementById('publishDate').onclick = function(){
			    start.elem = this;
			    laydate(start);
			  }
		  
		var element = layui.element();
		var form = layui.form();
		var layer = layui.layer;
		window.element = element;
		window.form = form;
		window.layer = layer;
		//�ύ
		form.on('submit(save)', function(data){
			submit();
			return false;
	  	});
		
		//��չ����  ������������ �ı�Ĭ��ֵ�Ŀ�
		form.on('select(paramType)', function(data){
			if (data.value == 'date') {
				var td = $(this).closest('td').next();
				td.empty();
				var input = $('<input class="layui-input" name="value" class="paramDate"  onclick="layui.laydate({elem: this, festival: true,istime: true,format:&apos;YYYY-MM-DD&apos;})" style="display:inline;width:65%;"><span class="layui-btn layui-btn-danger btn layui-btn-small changeDateDiv" >�ֶ���д</span>');
				td.append(input);
			}else{
				var td = $(this).closest('td').next();
				td.empty();
				var input = $('<input type="text" name="value" name="title"  autocomplete="off" class="layui-input">');
				td.append(input);
			} 
			form.render();
		});  
	});
	
	$("#paramTable").on("click",".changeDateDiv",function(){
		var td = $(this).closest('td');
		td.empty();
		var key = $(this).html();
		if(key=="�ֶ���д"){
			var input =  $('<input class="layui-input paaramDate" name="value" type="text" style="display:inline;width:65%;"><span class="layui-btn layui-btn-danger btn layui-btn-small changeDateDiv">������д</span>');
			td.append(input);
		}else{
			var input = $('<input class="layui-input  paramDate" name="value"  onclick="layui.laydate({elem: this, festival: true,istime: true,format:&apos;YYYY-MM-DD&apos;})" style="display:inline;width:65%;"><span class="layui-btn layui-btn-danger btn layui-btn-small changeDateDiv" >�ֶ���д</span>');
			td.append(input);	
		}
		form.render();
	});
	
	//������д�����ȡ����
	$("#title").on("blur",function(){
		var title = $("#title").val();
		$.post(
			ajaxGetDomain,
			{
				"domainName":title
			},function(data){
				$("#belongDomainList").val(data.result);
			},"json"
		);
	});
});

//����
function submit(){
	var oldContent = $("#pureContent").val();
	var paramNum = $("#paramTable").find("input[name='attrCName']").length;
	var arr = [];
	for(var i=0; paramNum>i; i++){
		var param = new Object();
		param.attrName = $(".removeParam").closest('td').prev().prev().prev().prev().find("input[name='attrName']")[i].value;
		param.attrCName = $(".removeParam").closest('td').prev().prev().prev().find("input[name='attrCName']")[i].value;
		param.value = $(".removeParam").closest('td').prev().find("input[name='value']")[i].value;
		param.type = $(".removeParam").closest('td').prev().prev().find(".paramType")[i].value;
		arr.push(param);
	}
	var arr = JSON.stringify(arr);
	var id = $("#resourceId").val();
	var catalogNum = $("#catalogNum").val();
	var catalogId = $("#catalogId").val();
	var libNum = '${libNum}';
	var issuedNum = $("#issuedNum").val();
	var title = $("#title").val();
	oldTitle = $("#oldTitle").val();
	var version = $("#version").val();
	var publisher = $("#publisher").val();
	var display = $("#publishDate").css('display');
	var publishDate = "";
	if(display == 'none'){
		publishDate = $("#publishDate2").val();
	}else{
		 publishDate = $("#publishDate").val();	
	}
	var belongDomainList = '['+$("#belongDomainList").val()+"]";
	var source = $("#source").val();
	var granularity = $("#granularity").val();
	var genreList = $("#genreList").val();
	var validTime = $("#validTime").val();
	var status	= $("input[name=status]:checked").val();
	var authority	= $("input[name=authority]:checked").val();
	var domainConfirmStatus	= $("input[name=domainConfirmStatus]:checked").val();
	var abstractText = $("#abstractText").val();
	var originalAddress = $("#originalAddress").val();
	var content =ue.getContent();
	var tagInfo = "";
	if(tagCount != 0){
		//���´����ǩ��
		tagInfo = JSON.stringify(jsonTag);
	}
	var classify = $("#classify").val();
	var index = layer.load(); 
		$.post(
		ajaxSave,
		{
			"id":id,
			"classify":classify,
			"libNum":libNum,
			"catalogId":catalogId,
			"catalogNum":catalogNum,
			"version":version,
			"issuedNum":issuedNum,
			"title":title,
			"publisher":publisher,
			"publishDate":publishDate,
			"belongDomain":belongDomainList,
			"source":source,
			"granularity":granularity,
			"genre":genreList,
			"validTime":validTime,
			"status":status,
			"abstractText":abstractText,
			"originalAddress":originalAddress,
			"attr":arr,
			"tag":tagInfo,
			"content":content,
			"authority":authority,
			"domainConfirmStatus":domainConfirmStatus,
			"oldContent":oldContent,
			"oldTitle":oldTitle
		},function(data){
			layer.close(index);
			if(data.result == 'success'){
			      layer.open({
			          type: 1
			          ,title:"����"
			          ,content: '<div style="padding: 20px 100px;">����ɹ�!</div>'
			          ,btn: '�ر�'
			          ,btnAlign: 'c' //��ť����
			          ,shade: 0 //����ʾ����
			          ,yes: function(){
			        	closeCurrentWin();
			        	parentRefresh();
			          }
			        });
			  
			}else{
				error("����ʧ�ܣ�");
			}
		},"json"
	) 
};

	//�����
	$("#genreList").on("click",function(){
		var layer = window.layer;
		layer.open({
			title:"���",
			maxmin: true,
			type: 2, 
			area: ['355px', '500px'],
			btnAlign: 'c',
			content:"/iamp/resource/getGenre.do?rootName="+encodeURI(encodeURI("���")),
			btn: ['ȷ��', 'ȡ��'],
			yes: function(index, layero){
			    //��ť����ťһ���Ļص�
			    var treeObj = $(layero).find("iframe")[0].contentWindow.treeObj;
			    var nodes = treeObj.getSelectedNodes()[0].name;
			    var conceptNames ="";
			    if(typeof(nodes) != 'undefined' && nodes != ""){
			    	conceptNames = nodes; 
			    };
		    	$.post(
			    		ajaxGetConcepts,
			    		{
			    			"names":conceptNames
			    		},
			    		function(data){
			    			var paramNum = $("#paramTable").find("input[name='attrCName']").length;
			    			var arr = [];
			    			for(var i=0; paramNum>i; i++){
			    				var param  = $(".removeParam").closest('td').prev().prev().prev().find("input[name='attrCName']")[i].value;
			    				arr.push(param);
			    			}
			    			
			    			for(var i=0; data.length>i; i++){
			    				if(!isContainText(arr,data[i].attrCName)){
					    			var tr = $(addParam());
					    			tr.find("input[name='attrName']").val(data[i].attrName);	
					    			tr.find("input[name='attrCName']").val(data[i].attrCName);
					    			tr.find("li[lay-value="+ data[i].type +"]").click();
					    			tr.find('option[value="' + data[i].type + '"]').attr(
											"selected", "selected");
					    			tr.find("input[name='value']").val(data[i].value);
					    			$("#paramTable tbody").append(tr);
					    			//������Ⱦ
					    			form.render();
			    				}
			    			}
			    		},"json"
			    	);
			    $("#genreList").val(conceptNames);
			    layer.close(index);
			 },
		  	success: function(layero, index){
			    var body = layer.getChildFrame('body', index);
			    var iframeWin = window[layero.find('iframe')[0]['name']]; //�õ�iframeҳ�Ĵ��ڶ���ִ��iframeҳ�ķ�����iframeWin.method();
					window.child = iframeWin;
			    }
		}); 
	  });
  
	//�������������
	$("#belongDomainList").on("click",function(){
		var layer = window.layer;
		layer.open({
			title:"����",
			maxmin: true,
			type: 2, 
			area: ['355px', '500px'],
			btnAlign: 'c',
			content:"/iamp/resource/getTree.do?rootName="+encodeURI(encodeURI("����")),
			btn: ['ȷ��', 'ȡ��'],
			yes: function(index, layero){
			    //��ť����ťһ���Ļص�
			    var treeObj = $(layero).find("iframe")[0].contentWindow.treeObj;
			    var nodes = treeObj.getCheckedNodes(true);
			    var genres ="";
			    for(var i=0; nodes.length>i; i++){
                    if(i==0) {
                    	genres = genres + nodes[i].name;
                    } else {
                        genres = genres + ";"+nodes[i].name;
                    }
			    }
			    $("#belongDomainList").val(genres);
			    layer.close(index);
			 },
		  	success: function(layero, index){
			    var body = layer.getChildFrame('body', index);
			    var iframeWin = window[layero.find('iframe')[0]['name']]; //�õ�iframeҳ�Ĵ��ڶ���ִ��iframeҳ�ķ�����iframeWin.method();
					window.child = iframeWin;
			    }
		}); 
	});
	
	$("#upload").on("click",function(){
		$('#uploadFile').first().trigger("click");
 		var layer = window.layer;
 		var resourceId = $('#resourceId').val();
 		if(resourceId == '') {
 			showMsg('����','�뱣����Դ���ϴ�����');
			return;
 		}
 		var libNum = '${libNum}';
		layer.open({
			title:"�ϴ��ļ�",
			maxmin: true,
			type: 2, 
			area: ['800px', '500px'],
			content:"/iamp/resource/attachmentUpload.do?resourceId="+resourceId+"&libNum="+libNum
		});  
	});
	
	//���´��ǩ
	$("#resetTag").on("click",function(){
		var id = $("#resourceId").val();
		if(id !=null && id != ""){
			var content =ue.getContent();
			var title = $("#title").val();
			if(content =="" && title ==""){
				var data = "����д�������Դ���ݣ�";
				error(data);
				return false;
			}
			$("#tagDiv").empty();
			var index = layer.load();
			tagCount =1;
			$.post(
				"/iamp/resource/resetTag",
				{
					"title":title,
					"content":content
				},function(data){
					layer.close(index);
					if(data.status =="success"){
						var domains = new Array();
						var acts = new Array();
						var organdpersons = new Array();
						var objects = new Array();
						var spaces = new Array();
						var times = new Array();
						var keywords = new Array();
						var hierarchy = new Array();
						var data = data.data;
						for(var key in data){
							if(key =='domains'){
								var arr = data[key];
								if(arr.length>0){
									$("#tagDiv").append("�������");
								}
								domains = appendTag(arr,domains,"domains");
							}else if(key == "acts"){
								var arr = data[key];
								if(arr.length>0){
									$("#tagDiv").append("�����Ϊ��");
								}
								acts = appendTag(arr,acts,"acts");
							}else if(key == 'hierarchy'){
								var arr = data[key];
								if(arr.length>0){
									$("#tagDiv").append("��������㼶��");
								}
								hierarchy = appendTag(arr,hierarchy,"hierarchy");
							}else if(key == 'keywords' ){
								var arr = data[key];
								if(arr.length>0){
									$("#tagDiv").append("��عؼ��ʣ�");
								}
								keywords = appendTag(arr,keywords,"keywords");
							}else if(key == 'objects'){
								var arr = data[key];
								if(arr.length>0){
									$("#tagDiv").append("��ؿ��壺");
								}
								objects = appendTag(arr,objects,"objects");
							}else if(key == 'organdpersons'){
								var arr = data[key];
								if(arr.length>0){
									$("#tagDiv").append("������壺");
								}
								organdpersons = appendTag(arr,organdpersons,"organdpersons");
							}else if(key == 'spaces'){
								var arr = data[key];
								if(arr.length>0){
									$("#tagDiv").append("��ؿռ䣺");
								}
								spaces = appendTag(arr,spaces,"spaces");
							}else if(key == 'times'){
								var arr = data[key];
								if(arr.length>0){
									$("#tagDiv").append("���ʱ�䣺");
								}
								times = appendTag(arr,times,"times");
							}else{}
						}
						jsonTag.domains = domains;
						jsonTag.acts = acts;
						jsonTag.organdpersons = organdpersons;
						jsonTag.objects = objects;
						jsonTag.spaces = spaces;
						jsonTag.times = times;
						jsonTag.keywords = keywords;
						jsonTag.hierarchy = hierarchy;
					}
				},"json"
			);
		}else{
			var data = "ֻ�б�������Ϣ��Դ�������´��ǩ��";
			error(data);
		}
	});
	
	/*===================�����ຯ��==================================================  */
	
	function setTag(){
		var tag = '<span class="layui-btn layui-btn-small tagSpan" style="margin:10px;"></span><i class="layui-icon removeTag" style="font-size: 15px; color: #1E9FFF;">&#x1006;</i>';
		return tag;
	}

	function appendTag(arr,jsonArr,type){
		var t=0;
		for(var i=0; arr.length>i; i++){
			if(arr[i].content != ""){
				var tag = $(setTag());
				$(tag[0]).html(arr[i].content);
				$(tag[0]).attr("value",arr[i].similarity);
				$(tag[0]).attr("name",arr[i].type);
				if(typeof(arr[i].parentNames) != "undefined"){
					$(tag[0]).attr("parents",arr[i].parentNames);	
				}else{
					$(tag[0]).attr("parents","");
				}
				$(tag[0]).addClass(type);
				var obj = new Object();
				obj.content = arr[i].content;
				obj.similarity = arr[i].similarity;
				if(typeof(arr[i].type) =='undefined'){
					obj.type="";
				}else{
					obj.type =arr[i].type;
				}
				if(!(typeof(arr[i].parentNames) =='undefined')){
					obj.parentNames =arr[i].parentNames;
				}
				jsonArr.push(obj);
				$("#tagDiv").append(tag);
				t++;
			}
		}
		if(t != 0){
			$("#tagDiv").append("<br>");	
		}
		return jsonArr;
	}
	
	// �鿴url
	$("#look").on("click",function(){
		var url = $("#originalAddress").val();
		if(url != ""){
			window.open(url);
		}else{
			error("��ַ����Ϊ��");
		}
	});
	
	// ��ȡ���رյ�ǰ����
	$("#cancel").on("click",function(){
		closeCurrentWin();
	});

	//ɾ����ǩ����Ҫ��¼�������ʱ����Ҫ���棻
	$("#tagDiv").on("click",".removeTag",function(){
		var thisTag = $(this);
		layer.confirm('ȷ��ɾ����������?', {
			  btn: ['ȷ��', 'ȡ��'] //�������޸���ť
			  ,btnAlign: 'c', yes: function(index, layero){
				  layer.close(layer.index);
					tagCount =1;
					thisTag.prev().remove();
					thisTag.remove();
					var arrDm = new Array();
					var arrAc = new Array();
					var arrOp = new Array();
					var arrObj = new Array();
					var arrSp = new Array();
					var arrTs = new Array();
					var arrKw = new Array();
					var arrHy = new Array();
					var domains = $("#tagDiv").find(".domains");
					var acts = $("#tagDiv").find(".acts");
					var organdpersons = $("#tagDiv").find(".organdpersons");
					var objects = $("#tagDiv").find(".objects");
					var spaces = $("#tagDiv").find(".spaces");
					var times = $("#tagDiv").find(".times");
					var keywords = $("#tagDiv").find(".keywords");
					var hierarchy = $("#tagDiv").find(".hierarchy");
					domains = appendTagArr(arrDm,domains);
			 		acts = appendTagArr(arrAc,acts);
			 		organdpersons = appendTagArr(arrOp,organdpersons);
					objects = appendTagArr(arrObj,objects);
					spaces = appendTagArr(arrSp,spaces);
					times = appendTagArr(arrTs,times);
					keywords = appendTagArr(arrKw,keywords);
					hierarchy = appendTagArr(arrHy,hierarchy); 
					jsonTag.domains = domains;
			 		jsonTag.acts = acts;
			 		jsonTag.organdpersons = organdpersons;
					jsonTag.objects = objects;
					jsonTag.spaces = spaces;
					jsonTag.times = times;
					jsonTag.keywords = keywords;
					jsonTag.hierarchy = hierarchy; 
					console.log(jsonTag);  
			  }
		});
	});
	function appendTagArr(arr,tags){
		if(tags!="" &&tags.length>0){		
			for(var i=0; tags.length>i; i++){
				var obj = new Object();
				obj.content = tags[i].innerHTML;
				obj.similarity = tags[i].getAttribute("value");
				obj.type = tags[i].getAttribute("name");
				if(typeof(tags[i].getAttribute("parents"))!="undefined" && tags[i].getAttribute("parents") != ""){
					var tagArr = tags[i].getAttribute("parents").split(",");
					var array = new Array();
					for(var i=0; tagArr.length>i; i++){
						array.push(tagArr[i]);
					}
					obj.parentNames = array;	
				}else{
					obj.parentNames =new Array();
				}
				arr.push(obj);
			}
		}
		return arr;
	}
	
	function addParam(){
		var tr ='<tr><td><input type="text" name="attrName" name="title" required value="" lay-verify="required"  autocomplete="off" class="layui-input"></td><td><input type="text" name="attrCName" name="title" required value="" lay-verify="required"  autocomplete="off" class="layui-input"></td>'
			+'<td><select lay-filter="paramType" lay-verify="required" name="type" class="paramType" ><option value="int" >����</option><option value="string" >�ַ���</option>'
	       	+'<option value="longtext" >���ı�</option><option value="date" >����</option><option value="array" >����</option></select>'
	      	+'</td><td><input type="text" name="value" name="title" required value=""   autocomplete="off" class="layui-input">'
			+'</td><td><i class="layui-icon removeParam" style="font-size: 15px; color: #1E9FFF;">&#x1006;</i></td></tr>';
		return tr;
	}
	//��Ӳ���
	$("#addParam").on("click",function(){
		var tr = $(addParam());
		$("#paramTable tbody").append(tr);
		//������Ⱦ
		form.render();
	});
	//���ٲ���
	$("#paramTable").on("click",".removeParam",function(){
		$(this).closest("tr").remove();
	});
	
	//��������
	$("#download").on("click",function(){
		var  checks = $('input:checkbox[name=fileId]:checked');
		if(checks.length > 0) {
			var arr = new Array();
			for(var i=0; checks.length>i; i++){
				var obj = new Object();
				var ext = $(checks[i]).closest('td').next().next().next().html();
				var name= $(checks[i]).closest('td').next().html();
				var id = checks[i].id;
				obj.ext=ext;
				obj.id = id;
				obj.name=name;
				arr.push(obj);
			}
			arr = JSON.stringify(arr);
			$("#downloadForm").attr("action","/iamp/resource/download");
			$("#downloadData").val(arr);
			$("#downloadForm").ajaxSubmit({
				 contentType: "application/x-www-form-urlencoded; charset=utf-8"
			});
			
			var form=$("<form>");//����һ��form��
			form.attr("style","display:none");
			form.attr("method","post");
			form.attr("action","/iamp/resource/download");
			var input1=$("<input>");
			input1.attr("type","hidden");
			input1.attr("name","arr");
			input1.attr("value",arr);
			form.append(input1);
			$("body").append(form);//����������web��
			form.submit();
			return true;
		} else {
			var layer = window.layer;
			layer.open({
	          type: 1
	          ,title:"����"
	          ,content: '<div style="padding: 20px 100px;">��ѡ������!</div>'
	          ,btn: '�ر�'
	          ,btnAlign: 'c' //��ť����
	          ,shade: 0 //����ʾ����
	          ,yes: function(){
	        	  layer.closeAll();
	          }
	        });	
		}
	});
	
	//ɾ���ļ�
	$('#delFile').on('click',function() {
		var  checks = $('input:checkbox[name=fileId]:checked');
		if(checks.length > 0) {
		var layer = window.layer;
		layer.confirm('ȷ��ɾ����������?', {
				  btn: ['ȷ��', 'ȡ��'] //�������޸���ť
				  ,btnAlign: 'c'
				  ,yes: function(index, layero){
				  			 var fileId = checks.eq(0).attr('id');
							 $.ajax({
						                type: "GET",
						                url:'/iamp/resource/delFile',
						                data:{fileId:fileId,
						                	  libNum:"${libNum}",
						                	  resourceId:$('#resourceId').val()
						                },// ���formid
						                async: false,
						                error: function(request) {
						                    alert("Connection error");
						                },
						                dataType:"json",
						                success: function(data) {
						                	if(data.result) {
						                	var layer = window.layer;
						                		layer.open({
										          type: 1
										          ,title:'����'
										          ,content: '<div style="padding: 20px 100px;">'+data.msg+'</div>'
										          ,btn: '�ر�'
										          ,btnAlign: 'c' //��ť����
										          ,shade: 0 //����ʾ����
										          ,yes: function(){
										        	  layer.closeAll();
										        	  $('#'+fileId).closest('tr').remove();
										          }
										        });	
						                	} else {
						                		showMsg('����',data.msg);
												return;
						                	}
						                }
						            });
				  
				  }})
			} else {
				var layer = window.layer;
				layer.open({
		          type: 1
		          ,title:"����"
		          ,content: '<div style="padding: 20px 100px;">��ѡ������!</div>'
		          ,btn: '�ر�'
		          ,btnAlign: 'c' //��ť����
		          ,shade: 0 //����ʾ����
		          ,yes: function(){
		        	  layer.closeAll();
		          }
		        });	
			}
	})
	
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
	
	function loadFileInfo(fileInfo) {
		var fileType = fileInfo.fileType == 1?'����':"����";
		var tr = '<tr><td><input type="checkbox" name="fileId" id="'+fileInfo.id+'"></td>'+
	  		'<td>'+fileInfo.title+'</td>'+
	  		'<td>'+fileType+'</td>'+
	  		'<td>'+fileInfo.fileExt+'</td>'+
	  		'<td>'+(fileInfo.fileSize)+'KB</td>'+
	  		'<td>'+fileInfo.createDate+'</td>'+
	  		'<td>'+fileInfo.keywords+'</td></tr>'
	  		$('#fileInfoList').append($(tr));
	}

	function error(data){
	      layer.open({
	          type: 1
	          ,title:"����"
	          ,content: '<div style="padding: 20px 100px;">'+data+'</div>'
	          ,btn: '�ر�'
	          ,btnAlign: 'c' //��ť����
	          ,shade: 0 //����ʾ����
	          ,yes: function(){
	        	  layer.closeAll();
	          }
	        });
	}
	
	//ˢ�¸��ര��
	function parentRefresh(){
		var targetIframe = "xxzywh";
		window.parent.loadChildRenIframe(targetIframe);
	}
	
	//�رյ�ǰ����
	function closeCurrentWin(){
	   var index = parent.layer.getFrameIndex(window.name); //�ȵõ���ǰiframe�������
	   parent.layer.close(index);
	}
	
	//�������Ƿ����
	function isContainText(arr, text) {
		if (arr.length == 0 || arr == null)
			return false;
		for ( var i in arr) {
			if (arr[i] == text)
				return true;
		}
		return false;
	}
</script>
</html>