<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>服务分类</title>
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
		<span class="layui-btn  layui-btn-normal btn layui-btn-small" id="add">新建</span>
		<span class="layui-btn  layui-btn-warm btn layui-btn-small" id="update">修改</span>
		<span class="layui-btn layui-btn-danger btn layui-btn-small" id="del">删除</span>
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
	     <th style="width:1%;"></th><th style="width:60%;">分类名称</th><th >创建人</th><th>创建时间</th>
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
		    ,pages: '${pages}' //总页数
		    ,groups: 5 //连续显示分页数
		    ,curr : '${currentPage}'
		    ,jump: function(obj, first){
		        if(!first){
		          layer.msg('第 '+ obj.curr +' 页');
		          location.href='/iamp/serviceType/list.do?num='+obj.curr;
		        }
		      }
		  }); 
});

	//删除功能
	$("#del").on("click",function(){
		var  checks = $('input:checkbox[name=typeId]:checked');
		if(checks.length <= 0){
			warning("请选择数据!");
			return;
		}
		layer.confirm('确定删除该数据吗?', {
			  btn: ['确定', '取消'] //可以无限个按钮
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
						          ,title:"提醒"
						          ,content: '<div style="padding: 20px 100px;">删除成功!</div>'
						          ,btn: '关闭'
						          ,btnAlign: 'c' //按钮居中
						          ,shade: 0 //不显示遮罩
						          ,yes: function(){
						      		var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
						      		refresh();
						          }
						        });
						}	
					},"json");  
			  }
		});
	});
	
	//修改功能
	$("#update").on("click",function(){
		var  checks = $('input:checkbox[name=typeId]:checked');
		if(checks.length == 1) {
			var typeId = checks[0].value;
 			var title ="修改服务分类";
 			var url ="/iamp/serviceType/update.do?typeId="+typeId;
 			window.parent.detailWins(title,url,"1000px","200px");
		}else if(checks.length == 0){
			warning("请选择数据!");
		}else{
			warning("请选择一条数据!");
		}
	});
	//用于新建成功后刷新
	function refresh(targetName){
		location.href = mainUrl;
		window.parent.loadIframe(targetName);
	}

	function refreshCurrentDiv(){
		alert(mainUrl)
		location.href = mainUrl;
	}
	
	//查询提交
	$("#search").on("click",function(){
		$(".layui-form").submit();
	});

	//新建
	$("#add").on("click",function(){
		var title ='分类管理';
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
			    var iframeWin = window[layero.find('iframe')[0]['name']]; //得到iframe页的窗口对象，执行iframe页的方法：iframeWin.method();
					window.child = iframeWin;
			    }
		}); 
	}
	
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
        	  layer.closeAll();
          }
        });
	}
	
</script>
</html>