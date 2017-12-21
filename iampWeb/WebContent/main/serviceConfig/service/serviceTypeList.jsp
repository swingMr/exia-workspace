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
		style="text-align:center;";
	}
</style>

<body>	
	<form class="layui-form" action="/iamp/service/list.do" >
		<span class="layui-btn  layui-btn-normal btn layui-btn-small" id="add">�½�</span>
		<span class="layui-btn  layui-btn-warm btn layui-btn-small" id="update">�޸�</span>
		<span class="layui-btn layui-btn-danger btn layui-btn-small" id="del">ɾ��</span>
	</form>
 	<table class="layui-table" lay-skin="line">
	  <colgroup>
	    <col width="50">
	    <col >
	    <col width="120">
	    <col width="150">
	  </colgroup>
	  <thead>
	    <tr>
	     <th style="width:1%;"></th><th style="width:60%;">��������</th><th >������</th><th>����ʱ��</th>
	    </tr> 
	  </thead>
	  <tbody>
	  <c:forEach var="list" items="${pageInfo.list}" varStatus="status">
	  	<tr>
	  		<td><input type="checkbox" name="typeId" value="${list.typeId}"></td>
	  		<td>${list.typeName}</td>
	  		<td>${list.creatorName}</td>
	  		<td><fmt:formatDate value="${list.createDate}"   pattern="yyyy-MM-dd HH:mm" type="date" dateStyle="long" /></td> 
	  	</tr>
	  </c:forEach>
	  <input type="hidden" name="ids">
	  </tbody>
	</table> 
	<div id="page" style="position:absolute;right:50px;bottom:0;"></div>
</body>
<script type="text/javascript" src="/iamp/js/layui/layui.js"></script>
<script>
var mainUrl = '/iamp/serviceType/list.do'
var ajaxDel = '/iamp/serviceType/del.do'
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
		          location.href='/iamp/serviceType/list.do?num='+obj.curr;
		        }
		      }
		  }); 
});

	//ɾ������
	$("#del").on("click",function(){
		var  checks = $('input:checkbox[name=typeId]:checked');
		if(checks.length <= 0){
			warning("��ѡ������!");
			return;
		}
		layer.confirm('ȷ��ɾ����������?', {
			  btn: ['ȷ��', 'ȡ��'] //�������޸���ť
			  , yes: function(index, layero){
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
						      layer.open({
						          type: 1
						          ,title:"����"
						          ,content: '<div style="padding: 20px 100px;">ɾ���ɹ�!</div>'
						          ,btn: '�ر�'
						          ,btnAlign: 'c' //��ť����
						          ,shade: 0 //����ʾ����
						          ,yes: function(){
						      		var index = parent.layer.getFrameIndex(window.name); //�ȵõ���ǰiframe�������
						      		refresh();
						          }
						        });
						}	
					},"json");  
			  }
		});
	});
	
	//�޸Ĺ���
	$("#update").on("click",function(){
		var  checks = $('input:checkbox[name=typeId]:checked');
		if(checks.length == 1) {
			var typeId = checks[0].value;
 			var title ="�޸ķ������";
 			var url ="/iamp/serviceType/update.do?typeId="+typeId;
 			window.parent.detailWins(title,url,"1000px","200px");
		}else if(checks.length == 0){
			warning("��ѡ������!");
		}else{
			warning("��ѡ��һ������!");
		}
	});
	//�����½��ɹ���ˢ��
	function refresh(targetName){
		location.href = mainUrl;
		window.parent.loadIframe(targetName);
	}

	function refreshCurrentDiv(){
		alert(mainUrl)
		location.href = mainUrl;
	}
	
	//��ѯ�ύ
	$("#search").on("click",function(){
		$(".layui-form").submit();
	});

	//�½�
	$("#add").on("click",function(){
		var title ='�������';
		var url ="/iamp/main/serviceConfig/service/typeDetail.jsp";
		window.parent.detailWins(title,url,"1000px","200px");
	});
	
	function detailWin(title,url){
		var layer = window.layer;
		layer.open({
			title:title,
			type: 2, 
			area: ['1000px', '580px'],
			content:url,
		  	success: function(layero, index){
			    var body = layer.getChildFrame('body', index);
			    var iframeWin = window[layero.find('iframe')[0]['name']]; //�õ�iframeҳ�Ĵ��ڶ���ִ��iframeҳ�ķ�����iframeWin.method();
					window.child = iframeWin;
			    }
		}); 
	}
	
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
        	  layer.closeAll();
          }
        });
	}
	
</script>
</html>