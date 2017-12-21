<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>服务定义</title>
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
		<span class="layui-btn  layui-btn-normal btn layui-btn-small" id="add">新建</span>
		<span class="layui-btn  layui-btn-warm btn layui-btn-small" id="update">修改</span>
		<span class="layui-btn layui-btn-danger btn layui-btn-small" id="del">删除</span>
		<span class="layui-btn btn layui-btn-small" id="testService">测试服务</span>
			<div style="float:right;position:relative;"><span class="layui-btn btn layui-btn-small" style="margin-right:10px;" id="search">查询</span></div>
			<div style="float:right;position:relative;"><span class="layui-btn btn layui-btn-small" id="typeManagement" >分类管理</span></div>
			<div style="float:right;position:relative;" class="btn"> 
			  <label class="layui-form-label">所属分类：</label>
			    <div class="layui-input-block" style="width:130px">
			      <select name="serviceType" id="serviceType" lay-verify="required" >
			     	 <option value="all">请选择</option>
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
				<label class="layui-form-label">中文名称：</label>
			    <div class="layui-input-block">
			      <input type="text" style="width:100px" name="searchCname" value="${searchCname}" required  lay-verify="required" placeholder="输入服务名称" autocomplete="off" class="layui-input">
			    </div>
	    	</div>
			<div style="float:right;position:relative;" class="btn"> 
				<label class="layui-form-label">服务名称：</label>
			    <div class="layui-input-block">
			      <input type="text" name="searchName"  style="width:100px" value="${searchName}" required  lay-verify="required" placeholder="输入中文名称" autocomplete="off" class="layui-input">
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
	     <th></th><th>服务名称</th><th>中文名称</th><th >服务类型</th>
	     <th >所属分类</th><th >累积访问次数</th><th >最近访问时间</th>
	     <th >创建人</th><th >创建时间</th><th>备注</th>
	    </tr> 
	  </thead>
	  <tbody>
	  <c:forEach var="list" items="${pageInfo.list}" varStatus="status">
	  	<tr>
	  		<td><input type="checkbox" name="serviceId" value="${list.serviceId}"></td><td>${list.serviceName}</td> <td>${list.serviceCname}</td> 
	  		<td>
	  			<c:choose>  					  
					   <c:when test="${!empty list && list.serviceType==1}"> 
					   		http服务
					   </c:when>     
					   <c:otherwise>
					   		python服务
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
		    ,pages: '${pages}' //总页数
		    ,groups: 5 //连续显示分页数
		    ,curr : '${currentPage}'
		    ,jump: function(obj, first){
		        if(!first){
		          layer.msg('第 '+ obj.curr +' 页');
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

	//测试服务
	$("#testService").on("click",function(){
		var  checks = $('input:checkbox[name=serviceId]:checked');
		if(checks.length == 1) {
			var serviceId = checks[0].value;
			var title ='测试服务';
			var url =ajaxTest+'?serviceId='+serviceId;
			window.parent.detailWin(title,url);
		}else if(checks.length == 0){
			warning("请选择数据!");
		}else{
			warning("请选择一条数据!");
		}
	});

	//删除功能
	$("#del").on("click",function(){
		var layer = window.layer;
		var  checks = $('input:checkbox[name=serviceId]:checked');
		if(checks.length <= 0){
			warning("请选择数据!");
			return;
		}
		layer.confirm('确定删除该数据吗?', {
		  btn: ['确定', '取消'] //可以无限个按钮
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
								warning("删除成功!");
							}	
						},"json"
					);
		  }
		})
	});
	
	//修改功能
	$("#update").on("click",function(){
		var  checks = $('input:checkbox[name=serviceId]:checked');
		if(checks.length == 1) {
			var serviceId = checks[0].value;
 			var title ="修改服务定义";
 			var url ="/iamp/service/update.do?serviceId="+serviceId;
 			window.parent.detailWin(title,url);
		}else if(checks.length == 0){
			warning("请选择数据!");
		}else{
			warning("请选择一条数据!");
		}
	});
	
	//用于新建成功后刷新
	function refresh(){
		location.href = mainUrl;
	}

	//查询提交
	$("#search").on("click",function(){
		$(".layui-form").submit();
	});

	//新建
	$("#add").on("click",function(){
		var title ='新建服务定义';
		var url ="/iamp/service/update.do";
		window.parent.detailWin(title,url);
	});
	
	//通用提示框
	function warning(data){
		var data = data;
		var layer = window.layer;
		layer.open({
          type: 1
          ,title:"提醒"
          ,content: '<div style="padding: 20px 100px;">'+data+'</div>'
          ,btn: '关闭'
          ,btnAlign: 'c' //按钮居中
          ,shade: 0 //不显示遮罩
          ,yes: function(){
	      		var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
	    		parent.layer.close(index);
	      		refresh();
          }
        });
	}
	
	//新建
	$("#typeManagement").on("click",function(){
		var data = new Object();
	 	data.title ='服务分类';
		data.href ="/iamp/serviceType/list.do";
		data.name="fwfl"
		var data = JSON.stringify(data);
		window.parent.addTab(data);
	});
</script>
</html>