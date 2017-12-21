<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>��Դ�ⶨ��&Ǩ��</title>
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
	<form class="layui-form" action="/iamp/resourceLib/list.do" >
		<span class="layui-btn  layui-btn-normal btn layui-btn-small" id="add">�½�</span>
		<span class="layui-btn  layui-btn-warm btn layui-btn-small" id="update">�޸�</span>
		<span class="layui-btn layui-btn-danger btn layui-btn-small" id="del">ɾ��</span>
		<span class="layui-btn btn layui-btn-small" id="input">����</span>
		<span class="layui-btn btn layui-btn-small" id="output">����</span>
		<div style="float:right;position:relative;" class="btn"> 
		  <div class="layui-form-item">
		  <label class="layui-form-label">�����ͣ�</label>
		    <div class="layui-input-block" style="width:130px">
		      <select name="type" id="type" lay-filter="type">
		     	 <option value="all" >����</option>
		     	 <option value="1" ${!empty type && type == '1'?'selected':'' }>����</option>
		     	 <option value="2" ${!empty type && type == '2'?'selected':'' }>��չ</option>
		     	 <option value="3" ${!empty type && type == '3'?'selected':'' }>�ⲿ����</option>
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
    	</div>
	</form>
 	<table class="layui-table" lay-skin="line">
	  <colgroup>
	  	<col width="50">
	    <col>
	    <col>
	    <col width="120">
	    <col width="120">
	    <col width="120">
	    <col width="120">
	    <col width="150">
	  </colgroup>
	  <thead>
	    <tr>
	     <th></th><th >��Դ����</th><th >��Դ������</th><th >������</th>
	     <th>��Դ����</th><th >��Դ����</th><th>������</th><th>����ʱ��</th>
	    </tr> 
	  </thead>
	  <tbody>
	  <c:forEach var="list" items="${list}" varStatus="status">
		  <tr>
		  	<td><input type="checkbox" name="id" value="${list.id}"></td> <td>${list.libNum}</td><td>${list.libName}</td>
		  	<td>
  			    <c:choose>     
	       		 	<c:when test="${!empty list.type && list.type=='1'}">���� </c:when>
	       		 	<c:when test="${!empty list.type && list.type=='2'}">��չ</c:when>
	       		 	<c:otherwise>�ⲿ����</c:otherwise>
       		 	</c:choose>
		  	</td>
		  	<td>
		  	  	<c:choose>     
	       		 	<c:when test="${!empty list.resType && list.resType=='1'}">�ĵ�</c:when>
	       		 	<c:when test="${!empty list.resType && list.resType=='2'}">ͼƬ</c:when>
	       		 	<c:when test="${!empty list.resType && list.resType=='3'}">��Ƶ</c:when>
	       		 	<c:when test="${!empty list.resType && list.resType=='4'}">��Ƶ</c:when>
	       		 	<c:when test="${!empty list.resType && list.resType=='5'}">��ͼ</c:when>
	       		 	<c:when test="${!empty list.resType && list.resType=='6'}">����</c:when>
	       		 	<c:when test="${!empty list.resType && list.resType=='7'}">����</c:when>
       		 	</c:choose>
		  	</td>
		  	<td><span class="count"><fmt:formatNumber value="${list.count}"  maxFractionDigits="3"/></span></td><td>${list.creatorName}</td><td><fmt:formatDate value="${list.createDate}"   pattern="yyyy-MM-dd HH:mm" type="date" dateStyle="long" /></td>
		  </tr>
	  </c:forEach>
	  <input type="hidden" name="ids">
	  </tbody>
	</table> 
	<div id="page" style="position:absolute;right:50px;"></div>
</body>
<script type="text/javascript" src="/iamp/js/layui/layui.js"></script>
<script>
var mainUrl = '/iamp/resourceLib/list.do';
var ajaxDel = '/iamp/resourceLib/del.do';
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
		          var serviceType = $("#serviceType").val();
		          if(typeof(serviceType)=='undefined' || $("#serviceType").val() == "all"){
		        	  serviceType="";
		          }
		          location.href='/iamp/resourceLib/list.do?pageNo='+obj.curr+'&type='+type;
		        }
		      }
		  }); 
		  
		  form.on('select(type)', function(data){
			  $(".layui-form").submit();
		  });
});

	//ɾ������
	$("#del").on("click",function(){
		var layer = window.layer;
		var  checks = $('input:checkbox[name=id]:checked');
		if(checks.length <= 0){
			var data = new Object();
			data.console ="��ѡ������!"; 
			warning(data);
			return;
		}
		var ids = new Array();
		for(var i=0; checks.length>i; i++){
			var val = $($(checks[i]).closest('td').next().next().next().next().next())[0].innerText;	
			if(val != 0){
				var data = new Object();
				data.console ="����ɾ����Դ��������0����Դ��!"; 
				warning(data);
				return;
			}
			var id = checks[i].value;
			ids.push(id);
		}
		var ids = JSON.stringify(ids);
 		layer.confirm('ȷ��ɾ����������?', {
		  btn: ['ȷ��', 'ȡ��'] //�������޸���ť
		  ,btnAlign: 'c', yes: function(index, layero){
					var layer = window.layer;
					$("input[name='ids']").val(ids);
					$.post(
						ajaxDel,
						{
							"ids" : ids
						},
						function(data){
							var data = data;
							if(data.result == 'success'){
								data.console='ɾ���ɹ���';
								warning(data);
							}	
						},"json"
					);
		  }
		});
	});
	
	//�޸Ĺ���
	$("#update").on("click",function(){
		var data = new Object();
		var  checks = $('input:checkbox[name=id]:checked');
		if(checks.length == 1) {
			var id = checks[0].value;
 			var title ="�޸���Ϣ��Դ��";
 			var url ="/iamp/resourceLib/showDetailView.do?id="+id;
 			window.parent.detailWin(title,url);
		}else if(checks.length == 0){
			data.console = "��ѡ������!";
			warning(data);
		}else{
			data.console = "��ѡ��һ������!";
			warning(data);
		}
	});
	
	//�����½��ɹ���ˢ��
	function refresh(){
		location.href = mainUrl;
	}
	
	//�½�
	$("#add").on("click",function(){
		var title ='�½���Ϣ��Դ��';
		var url ="/iamp/resourceLib/showDetailView.do";
		window.parent.detailWin(title,url);
	});
	
	//ͨ����ʾ��
	function warning(data){
		var data = data;
		var layer = window.layer;
		layer.open({
          type: 1
          ,title:"����"
          ,content: '<div style="padding: 20px 100px;">'+data.console+'</div>'
          ,btn: '�ر�'
          ,btnAlign: 'c' //��ť����
          ,shade: 0 //����ʾ����
          ,yes: function(){
        	  layer.close(layer.index);
	    		if(typeof(data.result) !='undefined' && data.result == 'success'){
	      			refresh();
	    		}
          }
        });
	}
	
</script>
</html>