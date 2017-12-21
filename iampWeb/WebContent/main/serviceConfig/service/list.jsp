<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>������</title>
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
	<form class="layui-form" action="/iamp/service/list.do" >
		<span class="layui-btn  layui-btn-normal btn layui-btn-small" id="add">�½�</span>
		<span class="layui-btn  layui-btn-warm btn layui-btn-small" id="update">�޸�</span>
		<span class="layui-btn layui-btn-danger btn layui-btn-small" id="del">ɾ��</span>
		<span class="layui-btn btn layui-btn-small" id="testService">���Է���</span>
			<div style="float:right;position:relative;"><span class="layui-btn btn layui-btn-small" style="margin-right:10px;" id="search">��ѯ</span></div>
			<div style="float:right;position:relative;"><span class="layui-btn btn layui-btn-small" id="typeManagement" >�������</span></div>
			<div style="float:right;position:relative;" class="btn"> 
			  <label class="layui-form-label">�������ࣺ</label>
			    <div class="layui-input-block" style="width:130px">
			      <select name="serviceType" id="serviceType" lay-verify="required" >
			     	 <option value="all">��ѡ��</option>
			      <c:forEach var="list" items="${typeList}" varStatus="status">
					   <c:choose>  					  
							   <c:when test="${!empty typeId && typeId==list.typeId}"> 
							   		<option value="${list.typeId}" selected = "selected">${list.typeName}</option>
							   </c:when>     
							   <c:otherwise>
							   		<option value="${list.typeId}">${list.typeName}</option>
							   </c:otherwise>  
						</c:choose> 
			      </c:forEach>
			      </select>
			    </div>
	    	</div>
			<div style="float:right;position:relative;" class="btn"> 
				<label class="layui-form-label">�������ƣ�</label>
			    <div class="layui-input-block">
			      <input type="text" style="width:100px" name="searchCname" value="${searchCname}" required  lay-verify="required" placeholder="�����������" autocomplete="off" class="layui-input">
			    </div>
	    	</div>
			<div style="float:right;position:relative;" class="btn"> 
				<label class="layui-form-label">�������ƣ�</label>
			    <div class="layui-input-block">
			      <input type="text" name="searchName"  style="width:100px" value="${searchName}" required  lay-verify="required" placeholder="������������" autocomplete="off" class="layui-input">
			    </div>
	    	</div>
	</form>
 	<table class="layui-table" lay-skin="line">
	  <colgroup>
	    <col width="50">
	    <col width="120">
	    <col width="200">
	    <col width="120">
	    <col width="120">
	    <col width="120">
	    <col width="120">
	    <col width="120">
	    <col width="150">
	    <col>
	  </colgroup>
	  <thead>
	    <tr>
	     <th></th><th>��������</th><th>��������</th><th >��������</th>
	     <th >��������</th><th >�ۻ����ʴ���</th><th >�������ʱ��</th>
	     <th >������</th><th >����ʱ��</th><th>��ע</th>
	    </tr> 
	  </thead>
	  <tbody>
	  <c:forEach var="list" items="${pageInfo.list}" varStatus="status">
	  	<tr>
	  		<td><input type="checkbox" name="serviceId" value="${list.serviceId}"></td><td>${list.serviceName}</td> <td>${list.serviceCname}</td> 
	  		<td>
	  			<c:choose>  					  
					   <c:when test="${!empty list && list.serviceType==1}"> 
					   		http����
					   </c:when>     
					   <c:otherwise>
					   		python����
					   </c:otherwise>  
				</c:choose> 
			</td>
	  		<td>${list.typeName}</td> <td>${list.accessCount}</td> <td><fmt:formatDate value="${list.recentAccessDate}"   pattern="yyyy-MM-dd" type="date" dateStyle="long" /></td>
	 	 	<td>${list.creatorName}</td> <td><fmt:formatDate value="${list.createDate}"   pattern="yyyy-MM-dd HH:mm" type="date" dateStyle="long" /></td> <td>${list.remark}</td>
	  	</tr>
	  </c:forEach>
	  <input type="hidden" name="ids">
	  </tbody>
	</table> 
	<div id="page" style="position:absolute;right:50px;"></div>
</body>
<script type="text/javascript" src="/iamp/js/layui/layui.js"></script>
<script>
var mainUrl = '/iamp/service/list.do';
var ajaxDel = '/iamp/service/del.do';
var ajaxTest= '/iamp/service/test.do';
var layer; 
layui.use(['layer','laypage','form'], function(){
	var form = layui.form();
	  var layer = layui.layer;
	  window.layer = layer;
	  var laypage = layui.laypage; 
		  laypage({
		    cont: 'page'
		    ,pages: '${pages}' //��ҳ��
		    ,groups: 5 //������ʾ��ҳ��
		    ,curr : '${currentPage}'
		    ,jump: function(obj, first){
		        if(!first){
		          layer.msg('�� '+ obj.curr +' ҳ');
		          var searchName = $("#searchName").val();
		          if(typeof(searchName)=='undefined'){
		        	  searchName="";
		          }
		          var searchCname = $("#searchCname").val();
		          if(typeof(searchCname)=='undefined'){
		        	  searchCname="";
		          }
		          var serviceType = $("#serviceType").val();
		          if(typeof(serviceType)=='undefined' || $("#serviceType").val() == "all"){
		        	  serviceType="";
		          }
		          location.href='/iamp/service/list.do?num='+obj.curr+'&searchName='+searchName+'&searchCname='+searchCname+'&serviceType='+serviceType;
		        }
		      }
		  }); 
});

	//���Է���
	$("#testService").on("click",function(){
		var  checks = $('input:checkbox[name=serviceId]:checked');
		if(checks.length == 1) {
			var serviceId = checks[0].value;
			var title ='���Է���';
			var url =ajaxTest+'?serviceId='+serviceId;
			window.parent.detailWin(title,url);
		}else if(checks.length == 0){
			warning("��ѡ������!");
		}else{
			warning("��ѡ��һ������!");
		}
	});

	//ɾ������
	$("#del").on("click",function(){
		var layer = window.layer;
		var  checks = $('input:checkbox[name=serviceId]:checked');
		if(checks.length <= 0){
			warning("��ѡ������!");
			return;
		}
		layer.confirm('ȷ��ɾ����������?', {
		  btn: ['ȷ��', 'ȡ��'] //�������޸���ť
		  ,btnAlign: 'c', yes: function(index, layero){
				var ids = new Array();
					for(var i=0; checks.length>i; i++){
						var id = checks[i].value;
						ids.push(id);
					}
					var ids = JSON.stringify(ids);
					var layer = window.layer;
					$("input[name='ids']").val(ids);
					$.post(
						ajaxDel,
						{
							"ids" : ids
						},
						function(data){
							if(data.result == 'success'){
								warning("ɾ���ɹ�!");
							}	
						},"json"
					);
		  }
		})
	});
	
	//�޸Ĺ���
	$("#update").on("click",function(){
		var  checks = $('input:checkbox[name=serviceId]:checked');
		if(checks.length == 1) {
			var serviceId = checks[0].value;
 			var title ="�޸ķ�����";
 			var url ="/iamp/service/update.do?serviceId="+serviceId;
 			window.parent.detailWin(title,url);
		}else if(checks.length == 0){
			warning("��ѡ������!");
		}else{
			warning("��ѡ��һ������!");
		}
	});
	
	//�����½��ɹ���ˢ��
	function refresh(){
		location.href = mainUrl;
	}

	//��ѯ�ύ
	$("#search").on("click",function(){
		$(".layui-form").submit();
	});

	//�½�
	$("#add").on("click",function(){
		var title ='�½�������';
		var url ="/iamp/service/update.do";
		window.parent.detailWin(title,url);
	});
	
	//ͨ����ʾ��
	function warning(data){
		var data = data;
		var layer = window.layer;
		layer.open({
          type: 1
          ,title:"����"
          ,content: '<div style="padding: 20px 100px;">'+data+'</div>'
          ,btn: '�ر�'
          ,btnAlign: 'c' //��ť����
          ,shade: 0 //����ʾ����
          ,yes: function(){
	      		var index = parent.layer.getFrameIndex(window.name); //�ȵõ���ǰiframe�������
	    		parent.layer.close(index);
	      		refresh();
          }
        });
	}
	
	//�½�
	$("#typeManagement").on("click",function(){
		var data = new Object();
	 	data.title ='�������';
		data.href ="/iamp/serviceType/list.do";
		data.name="fwfl"
		var data = JSON.stringify(data);
		window.parent.addTab(data);
	});
</script>
</html>