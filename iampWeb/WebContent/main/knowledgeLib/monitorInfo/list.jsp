<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>��Դ�����</title>
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
	<form class="layui-form" action="/iamp/monitoringInfo/list.do" >
		<span class="layui-btn  layui-btn-normal btn layui-btn-small" id="monitorSetting">�������</span>
		<span class="layui-btn  layui-btn-warm btn layui-btn-small" id="dealWith">����Ѵ���</span>
		<span class="layui-btn  layui-btn-danger btn layui-btn-small" id="ignore">��ɺ���</span>
<div style="float:right;position:relative;"><span class="layui-btn btn layui-btn-small" style="margin-right:10px;" id="search">��ѯ</span></div>
	    <div style="float:right;position:relative;"> 
			<label class="layui-form-label">�ؼ��֣�</label>
		      <input type="text" style="width:100px" name="searchAttr" id="searchAttr" value="${searchAttr}" required  lay-verify="required" placeholder="������ؼ���" autocomplete="off" class="layui-input">
	   	</div>
	    <div style="float:right;position:relative;"> 
	    	<label class="layui-form-label">�������</label>
		      <input type="text" style="width:120px" id="searchKeyword" name="searchKeyword" value="${searchKeyword}" required  lay-verify="required" placeholder="�������������" autocomplete="off" class="layui-input">
	   	</div>
	   	<div style="float:right;position:relative;"> 
		  <label class="layui-form-label">״̬��</label>
		    <div class="layui-input-block" style="width:85px">
		      <select name="type" id="type" lay-filter="type">
		      <option value="0" ${!empty type && type == '0'?'selected':'' }>δ����</option>
		      	<option value="all" >����</option>
		     	 <option value="1" ${!empty type && type == '1'?'selected':'' }>�Ѵ���</option>
		     	 <option value="-1" ${!empty type && type == '-1'?'selected':'' }>�Ѻ���</option>
		      </select>
		    </div>
    	</div>
	   	<div style="float:right;position:relative;" > 
		  <label class="layui-form-label">��Դ�⣺</label>
		    <div class="layui-input-block" style="width:85px">
		      <select name="dataBase" id="dataBase" lay-filter="type">
		     	 <option value="all" >����</option>
				<c:forEach var="libList" items="${libList}" varStatus="status">
					<option value="${libList.libNum}" ${!empty dataBase && dataBase == libList.libNum ?'selected':'' }>${libList.libName}</option>
				</c:forEach>
		      </select>
		    </div>
    	</div>
	</form>
 	<table class="layui-table" lay-skin="line" id="table">
	  <colgroup>
	  	<col width="40">
	    <col width="200">
	    <col width="120">
	    <col >
	    <col width="200">
	    <col width="145">
	    <col width="80">
	  </colgroup>
	  <thead>
	    <tr>
	     <th><input type="checkbox" class="checkIds"></th><th >��������</th><th >��Դ��</th><th >��Դ����</th>
	     <th>��������</th><th >���ʱ��</th><th>״̬</th>
	    </tr> 
	  </thead>
	  <tbody>
	  <c:forEach var="list" items="${list}" varStatus="status" >
	  	<tr>
	  		<td><input type="checkbox" name="id" value="${list.id}"></td>
		  	<td>${list.conceptStr}</td>
	  		<td>${list.libName}</td>
	  		<td><a class="showView" href="#" name="${list.sourceId}" id="${list.libNum}">${list.title}</a></td>
	  		<td>${list.domainStr}</td>
			<td><fmt:formatDate value="${list.intoLibDate}"   pattern="yyyy-MM-dd HH:mm" type="date" dateStyle="long" /></td>
	  		<td>
			  	<c:choose>
			     	<c:when test="${list.monitoredStatus==0}">
			     			 δ����
			     	</c:when>
			     	<c:when test="${list.monitoredStatus==1}">
			     			 �Ѵ���
			     	</c:when>
			     	<c:otherwise>
			     			�Ѻ���
			     	</c:otherwise>
			     </c:choose>
			</td>
	  	</tr>
	  </c:forEach>
	  <input type="hidden" name="ids">
	  </tbody>
	</table> 
	<div id="page" style="position:absolute;right:50px;"></div>
</body>
<script type="text/javascript" src="/iamp/js/layui/layui.js"></script>
<script>
var mainUrl = '/iamp/monitoringInfo/list';
var DEALWITH_DATA = '/iamp/monitoringInfo/dealWithData';
var GET_ONE = "/iamp/monitoringSetting/getOne";
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
		          var dataBase = $("#dataBase").val();
		          var type = $("#type").val();
		          var searchKeyword = $("#searchKeyword").val();
		          location.href='/iamp/monitoringInfo/list.do?pageNo='+obj.curr+'&type='+type+'&dataBase='+dataBase+'&searchKeyword='+searchKeyword;
		        }
		      }
		  }); 
		  
		  form.on('select(type)', function(data){
			  $(".layui-form").submit();
		  });
		  
		  $("#search").on("click",function(){
			  $(".layui-form").submit();
		  });
});
	
	//�������
	$(".showView").on("click",function(){
		var libNum  = $(this).attr("id");
		var id = $(this).attr("name");
		var title ="�鿴��Ϣ��Դ";
		var url ="/iamp/resource/showDetail?id="+id+"&libNum="+libNum;
		window.parent.detailWin(title,url);
	});
	
	//ȫѡ���ܣ�
	$("#table").on("click",".checkIds",function(){
        $("input[name='id']").each(function() {  
            $(this).attr("checked", true); 
        }); 
        $(this).addClass("removeCheck");
        $(this).removeClass("checkIds");
	});
	$("#table").on("click",".removeCheck",function(){
        $("input[name='id']").each(function() {  
            $(this).attr("checked", false); 
        }); 
        $(this).addClass("checkIds");
        $(this).removeClass("removeCheck");
	});

 	$("#monitorSetting").on("click",function(){
 		var url = GET_ONE;
 		window.parent.detailWin("�������",url);
 	});
 	

	//���ó��Ѻ��Թ���
	$("#ignore").on("click",function(){
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
			var id = checks[i].value;
			ids.push(id);
		}
		var ids = JSON.stringify(ids);
		layer.confirm('ȷ��������������Ѻ�����?', {
		  btn: ['ȷ��', 'ȡ��'] //�������޸���ť
		  ,btnAlign: 'c', yes: function(index, layero){
					var layer = window.layer;
					$("input[name='ids']").val(ids);
 					$.post(
 							DEALWITH_DATA,
						{
							"ids" : ids,
							"ignore":"ignore"
						},
						function(data){
							var data = data;
							if(data.result == 'success'){
								data.console='���� �ɹ���';
								warning(data);
							}	
						},"json"
					); 
		      }
		})
	});
 	
	//���ó��Ѵ�����
	$("#dealWith").on("click",function(){
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
			var id = checks[i].value;
			ids.push(id);
		}
		var ids = JSON.stringify(ids);
		layer.confirm('ȷ��������������Ѵ�����?', {
		  btn: ['ȷ��', 'ȡ��'] //�������޸���ť
		  ,btnAlign: 'c', yes: function(index, layero){
					var layer = window.layer;
					$("input[name='ids']").val(ids);
 					$.post(
 							DEALWITH_DATA,
						{
							"ids" : ids
						},
						function(data){
							var data = data;
							if(data.result == 'success'){
								data.console='���� �ɹ���';
								warning(data);
							}	
						},"json"
					); 
		      }
		})
	});
	
	//�����½��ɹ���ˢ��
	function refresh(){
		location.href = mainUrl;
	}

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