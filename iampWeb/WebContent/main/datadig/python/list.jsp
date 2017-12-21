<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>python程序管理</title>
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
		<span class="layui-btn btn layui-btn-small" id="execute">运行</span>
		<div style="float:right;position:relative;" class="btn"> 
		    <label class="layui-form-label">所属分组：</label>
		    <div class="layui-input-block" style="width:150px">
		      <select name="classify" id="classify" lay-verify="required" lay-filter="classify">
			      <option value="all">请选择</option>
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
	     <th>程序名称</th>
	     <th>中文名称</th>
	     <th>所属分组</th>
	     <th>创建人</th>
	     <th>最后修改时间</th>
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
		    ,pages: '${pages}' //总页数
		    ,groups: 5 //连续显示分页数
		    ,curr : '${currentPage}'
		    ,jump: function(obj, first){
		        if(!first){
		          layer.msg('第 '+ obj.curr +' 页');
		          var typeName = $("#classify").val();
		          if(typeof(typeName)=='undefined' || typeName =='all'){
		        	  typeName="";
		          }
				location.href='/iamp/python/list.do?num='+obj.curr+'&typeName='+typeName;
		        }
		      }
		  }); 
		  
		  form.on('select(classify)', function(data){
			  console.log(data.value); //得到被选中的值
		  	  var typeName = data.value;
			  if(typeName =="all"){
				  typeName ="";
			  }
		  	  location.href='/iamp/python/list.do?typeName='+typeName;
		  });
});

	//运行程序
	$("#execute").on("click",function(){
		var  checks = $('input:checkbox[name=programId]:checked');
		if(checks.length == 1) {
			var programId = checks[0].value;
			var title ='运行程序';
			var url =ajaxRun+'?programId='+programId;
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
		var  checks = $('input:checkbox[name=programId]:checked');
		if(checks.length <= 0) {
			warning("请选择数据");
		}
		layer.confirm('确定删除该程序吗?', {
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
						      layer.open({
						          type: 1
						          ,title:"提醒"
						          ,content: '<div style="padding: 20px 100px;">删除成功!</div>'
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
					},"json"
				);
			}
		});
	});
	
	//修改功能
	$("#update").on("click",function(){
		var  checks = $('input:checkbox[name=programId]:checked');
		if(checks.length == 1) {
			var programId = checks[0].value;
 			var title ="修改服务定义";
 			var url ="/iamp/python/update.do?programId="+programId;
 			window.parent.detailWin(title,url);
		}else if(checks.length == 0){
			warning("请选择数据");
		}else{
			warning("请选择一条数据");
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
		var title ='新建Python程序';
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
			    var iframeWin = window[layero.find('iframe')[0]['name']]; //得到iframe页的窗口对象，执行iframe页的方法：iframeWin.method();
					window.child = iframeWin;
			    }
		}); 
	}
	
	//新建
	$("#typeManagement").on("click",function(){
		var data = new Object();
	 	data.title ='服务分类';
		data.href ="/iamp/serviceType/list.do";
		data.id="targetFrame"
		var data = JSON.stringify(data);
		window.parent.addTab(data);
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
        	  layer.closeAll();
          }
        });
	}
</script>
</html>