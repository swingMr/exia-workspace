<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>资源入库监控</title>
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
		<span class="layui-btn  layui-btn-normal btn layui-btn-small" id="monitorSetting">监控设置</span>
		<span class="layui-btn  layui-btn-warm btn layui-btn-small" id="dealWith">设成已处理</span>
		<span class="layui-btn  layui-btn-danger btn layui-btn-small" id="ignore">设成忽略</span>
<div style="float:right;position:relative;"><span class="layui-btn btn layui-btn-small" style="margin-right:10px;" id="search">查询</span></div>
	    <div style="float:right;position:relative;"> 
			<label class="layui-form-label">关键字：</label>
		      <input type="text" style="width:100px" name="searchAttr" id="searchAttr" value="${searchAttr}" required  lay-verify="required" placeholder="请输入关键词" autocomplete="off" class="layui-input">
	   	</div>
	    <div style="float:right;position:relative;"> 
	    	<label class="layui-form-label">关联概念：</label>
		      <input type="text" style="width:120px" id="searchKeyword" name="searchKeyword" value="${searchKeyword}" required  lay-verify="required" placeholder="请输入关联概念" autocomplete="off" class="layui-input">
	   	</div>
	   	<div style="float:right;position:relative;"> 
		  <label class="layui-form-label">状态：</label>
		    <div class="layui-input-block" style="width:85px">
		      <select name="type" id="type" lay-filter="type">
		      <option value="0" ${!empty type && type == '0'?'selected':'' }>未处理</option>
		      	<option value="all" >所有</option>
		     	 <option value="1" ${!empty type && type == '1'?'selected':'' }>已处理</option>
		     	 <option value="-1" ${!empty type && type == '-1'?'selected':'' }>已忽略</option>
		      </select>
		    </div>
    	</div>
	   	<div style="float:right;position:relative;" > 
		  <label class="layui-form-label">资源库：</label>
		    <div class="layui-input-block" style="width:85px">
		      <select name="dataBase" id="dataBase" lay-filter="type">
		     	 <option value="all" >所有</option>
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
	     <th><input type="checkbox" class="checkIds"></th><th >关联概念</th><th >资源库</th><th >资源标题</th>
	     <th>归属领域</th><th >入库时间</th><th>状态</th>
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
			     			 未处理
			     	</c:when>
			     	<c:when test="${list.monitoredStatus==1}">
			     			 已处理
			     	</c:when>
			     	<c:otherwise>
			     			已忽略
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
		    ,pages: '${pages}' //总页数
		    ,groups: 5 //连续显示分页数
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
	
	//浏览界面
	$(".showView").on("click",function(){
		var libNum  = $(this).attr("id");
		var id = $(this).attr("name");
		var title ="查看信息资源";
		var url ="/iamp/resource/showDetail?id="+id+"&libNum="+libNum;
		window.parent.detailWin(title,url);
	});
	
	//全选功能；
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
 		window.parent.detailWin("监控设置",url);
 	});
 	

	//设置成已忽略功能
	$("#ignore").on("click",function(){
		var layer = window.layer;
		var  checks = $('input:checkbox[name=id]:checked');
		if(checks.length <= 0){
			var data = new Object();
			data.console ="请选择数据!"; 
			warning(data);
			return;
		}
		var ids = new Array();
		for(var i=0; checks.length>i; i++){
			var id = checks[i].value;
			ids.push(id);
		}
		var ids = JSON.stringify(ids);
		layer.confirm('确定将该数据设成已忽略吗?', {
		  btn: ['确定', '取消'] //可以无限个按钮
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
								data.console='设置 成功！';
								warning(data);
							}	
						},"json"
					); 
		      }
		})
	});
 	
	//设置成已处理功能
	$("#dealWith").on("click",function(){
		var layer = window.layer;
		var  checks = $('input:checkbox[name=id]:checked');
		if(checks.length <= 0){
			var data = new Object();
			data.console ="请选择数据!"; 
			warning(data);
			return;
		}
		var ids = new Array();
		for(var i=0; checks.length>i; i++){
			var id = checks[i].value;
			ids.push(id);
		}
		var ids = JSON.stringify(ids);
		layer.confirm('确定将该数据设成已处理吗?', {
		  btn: ['确定', '取消'] //可以无限个按钮
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
								data.console='设置 成功！';
								warning(data);
							}	
						},"json"
					); 
		      }
		})
	});
	
	//用于新建成功后刷新
	function refresh(){
		location.href = mainUrl;
	}

	//通用提示框
	function warning(data){
		var data = data;
		var layer = window.layer;
		layer.open({
          type: 1
          ,title:"提醒"
          ,content: '<div style="padding: 20px 100px;">'+data.console+'</div>'
          ,btn: '关闭'
          ,btnAlign: 'c' //按钮居中
          ,shade: 0 //不显示遮罩
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