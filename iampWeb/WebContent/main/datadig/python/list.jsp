<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>python�������</title>
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
		<span class="layui-btn btn layui-btn-small" id="execute">����</span>
		<div style="float:right;position:relative;" class="btn"> 
		    <label class="layui-form-label">�������飺</label>
		    <div class="layui-input-block" style="width:150px">
		      <select name="classify" id="classify" lay-verify="required" lay-filter="classify">
			      <option value="all">��ѡ��</option>
			    <c:forEach var="list" items="${typeList}" varStatus="status">
			     <c:choose>  					  
					   <c:when test="${!empty typeName && typeName == list.typeName}"> 
					   		 <option selected = "selected" value="${list.typeName}">${list.typeName}</option>
					   </c:when>     
					   <c:otherwise>
					   		  <option value="${list.typeName}">${list.typeName}</option>
					   </c:otherwise>  
				</c:choose> 
		      </c:forEach>
		      </select>
		    </div>
    	</div>
	</form>
 	<table class="layui-table" lay-skin="line">
	  <colgroup>
	    <col width="50">
	    <col width="150">
	    <col width="200">
	    <col width="120">
	    <col width="120">
	    <col width="150">
	    <col>
	  </colgroup>
	  <thead>
	    <tr>
	     <th></th>
	     <th>��������</th>
	     <th>��������</th>
	     <th>��������</th>
	     <th>������</th>
	     <th>����޸�ʱ��</th>
	    </tr> 
	  </thead>
	  <tbody>
	  	  <c:forEach var="list" items="${pageInfo.list}" varStatus="status">
	  	<tr>
	  		<td><input type="checkbox" name="programId" value="${list.programId}"></td>
	  		<td>${list.programName}</td> 
	  		<td>${list.programCName}</td> 
	  		<td>${list.typeName}</td>
	  		<td>${list.creatorName}</td> <td><fmt:formatDate value="${list.updateDate}"   pattern="yyyy-MM-dd HH:mm" type="date" dateStyle="long" /></td>
	  	</tr>
	  </c:forEach>
	  </tbody>
	</table> 
	<div id="page" style="position:absolute;right:50px;"></div>
</body>
<script type="text/javascript" src="/iamp/js/layui/layui.js"></script>
<script>
var mainUrl = '/iamp/python/list.do';
var ajaxDel = '/iamp/python/del.do';
var ajaxRun= '/iamp/python/execute.do';
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
		          var typeName = $("#classify").val();
		          if(typeof(typeName)=='undefined' || typeName =='all'){
		        	  typeName="";
		          }
				location.href='/iamp/python/list.do?num='+obj.curr+'&typeName='+typeName;
		        }
		      }
		  }); 
		  
		  form.on('select(classify)', function(data){
			  console.log(data.value); //�õ���ѡ�е�ֵ
		  	  var typeName = data.value;
			  if(typeName =="all"){
				  typeName ="";
			  }
		  	  location.href='/iamp/python/list.do?typeName='+typeName;
		  });
});

	//���г���
	$("#execute").on("click",function(){
		var  checks = $('input:checkbox[name=programId]:checked');
		if(checks.length == 1) {
			var programId = checks[0].value;
			var title ='���г���';
			var url =ajaxRun+'?programId='+programId;
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
		var  checks = $('input:checkbox[name=programId]:checked');
		if(checks.length <= 0) {
			warning("��ѡ������");
		}
		layer.confirm('ȷ��ɾ���ó�����?', {
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
						      layer.open({
						          type: 1
						          ,title:"����"
						          ,content: '<div style="padding: 20px 100px;">ɾ���ɹ�!</div>'
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
					},"json"
				);
			}
		});
	});
	
	//�޸Ĺ���
	$("#update").on("click",function(){
		var  checks = $('input:checkbox[name=programId]:checked');
		if(checks.length == 1) {
			var programId = checks[0].value;
 			var title ="�޸ķ�����";
 			var url ="/iamp/python/update.do?programId="+programId;
 			window.parent.detailWin(title,url);
		}else if(checks.length == 0){
			warning("��ѡ������");
		}else{
			warning("��ѡ��һ������");
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
		var title ='�½�Python����';
		var url ="/iamp/python/update.do";
		window.parent.detailWin(title,url);
	});
	
	function detailWin(title,url){
		var layer = window.layer;
		layer.open({
			title:title,
			maxmin: true,
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
	
	//�½�
	$("#typeManagement").on("click",function(){
		var data = new Object();
	 	data.title ='�������';
		data.href ="/iamp/serviceType/list.do";
		data.id="targetFrame"
		var data = JSON.stringify(data);
		window.parent.addTab(data);
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
        	  layer.closeAll();
          }
        });
	}
</script>
</html>