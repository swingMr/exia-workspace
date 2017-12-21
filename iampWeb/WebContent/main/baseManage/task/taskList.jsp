<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>采集任务管理</title>
</head>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="format-detection" content="telephone=no">
<link rel="stylesheet" href="/iamp/js/layui-v2/css/layui.css" media="all" />
<link rel="stylesheet" href="/iamp/js/layui-v2/css/global.css" media="all">
<script src="/iamp/js/jquery-1.7.2.min.js"></script>
<style type="text/css">
	.btn{
		margin-top:10px;
		margin-left:10px;
	}
	
</style>
<body>
	<form class="layui-form" id="form" action="/iamp/task/list.do" method="post">
		<input type="hidden" name="pageNo" value="${page.pageNum}">
		<input type="hidden" name="pageSize" value="${page.pageSize}">
		<span class="layui-btn  layui-btn-normal btn layui-btn-small" id="add">新建</span>
		<span class="layui-btn  layui-btn-warm btn layui-btn-small" id="update">修改</span>
		<span class="layui-btn layui-btn-danger btn layui-btn-small" id="del">删除</span>
		<span class="layui-btn  layui-btn-normal btn layui-btn-small" id="run">启动</span>
		<span class="layui-btn  layui-btn-warm btn layui-btn-small" id="start">继续</span>
		<span class="layui-btn btn layui-btn-small" id="pause">暂停</span>
		<span class="layui-btn layui-btn-danger btn layui-btn-small" id="stop">停止</span>
		<div style="float:right;position:relative;"><span class="layui-btn btn layui-btn-small" style="margin-right:10px;" id="query">查询</span></div>
    	<div style="float:right;position:relative;" class="btn"> 
			<label class="layui-form-label">任务名称：</label>
		    <div class="layui-input-block">
		    	<input type="text" name="taskName" style="width:200px" lay-verify="required" placeholder="请输入任务名称" autocomplete="off" class="layui-input" value="${!empty taskName ? taskName : ''}"> 
		    </div>
    	</div>
    	<div style="float:right;position:relative;" class="btn"> 
				<label class="layui-form-label">任务类型：</label>
				<div class="layui-input-block" style="width:150px">
			      	<select id="taskType" name="taskType" lay-verify="required">
			      		<c:choose>
		       			  <c:when test="${taskType=='1'}"> 
						   		<option value="all">所有</option>
				        		<option value="1" selected>本地定时任务</option>
				        		<option value="2">python定时任务</option>
				        		<option value="3">远程定时任务</option>
						   </c:when>
						   <c:when test="${taskType=='2'}"> 
						   		<option value="all">所有</option>
				        		<option value="1">本地定时任务</option>
				        		<option value="2" selected>python定时任务</option>
				        		<option value="3">远程定时任务</option>
						   </c:when>
						   <c:when test="${taskType=='3'}"> 
						   		<option value="all">所有</option>
				        		<option value="1">本地定时任务</option>
				        		<option value="2">python定时任务</option>
				        		<option value="3" selected>远程定时任务</option>
						   </c:when>  
						   <c:otherwise>
		       			 		<option value="all">所有</option>
				        		<option value="1">本地定时任务</option>
				        		<option value="2">python定时任务</option>
				        		<option value="3">远程定时任务</option>
		       			  </c:otherwise>
		       			</c:choose>
				      </select>
			    </div>
	    	</div>
	</form>
 	<table class="layui-table" lay-skin="line" id="taskTable">
	  <colgroup>
	  	<col width="40">
	    <col width="200">
	    <col width="120">
	    <col>
	    <col width="150">
	    <col width="90">
	    <col width="90">
	  </colgroup>
	  <thead>
	    <tr>
	     <th><input type="checkbox" id="checkAll" name="checkAll"></th>
	     <th>任务名称</th><th>任务类型</th><th>执行器</th><th>创建时间</th>
	     <th>任务状态</th><th>操作</th>
	    </tr> 
	  </thead>
	  <tbody>
	  	<c:if test="${!empty page.list}">
		  <c:forEach var="task" items="${page.list}" varStatus="status">
		  	<tr>
		  		<input type="hidden" name="taskStatus" value="${task.status}"/>
		  		<td><input type="checkbox" id="${task.taskId}" name="id"></td>
		  		<td>${task.taskName}</td>
		  		<td>
		  			<c:if test="${task.taskType=='1'}"> 
		  		 		本地定时任务
		   			</c:if>
		  		 	<c:if test="${task.taskType=='2'}"> 
		  		 		python定时任务
		   			</c:if>
		   			<c:if test="${task.taskType=='3'}"> 
		  		 		远程定时任务
		   			</c:if>
		  		</td>
		  		<td>${task.accessPath}</td>
		  		<td>
		  			<fmt:formatDate value="${task.createDate}" pattern="yyyy-MM-dd HH:mm" type="date"/>
		  		</td>
		  		<td name="status">
		  			<c:if test="${task.status==0}">停止</c:if>
		  			<c:if test="${task.status==1}">运行</c:if>
		  			<c:if test="${task.status==-1}">禁止</c:if>
		  			<c:if test="${task.status==2}">暂停</c:if>
		  		</td>
		  		<td><i class="layui-icon viewlog" style="font-size: 20px; color: #009688;cursor:pointer" title="查看任务日志">&#xe611;</i></td>
		  		<!-- <td>
		  			<div class="layui-btn-group">
					  <button class="layui-btn layui-btn-primary layui-btn-small">
					    <i class="layui-icon">&#xe652;</i>
					  </button>
					  <button class="layui-btn layui-btn-primary layui-btn-small">
					    <i class="layui-icon">&#xe651;</i>
					  </button>
					  <button class="layui-btn layui-btn-primary layui-btn-small">
					    <i class="layui-icon">&#xe60f;</i>
					  </button>
					  <button class="layui-btn layui-btn-primary layui-btn-small">
					    <i class="layui-icon">&#xe615;</i>
					  </button>
					</div>
		  		</td> -->
			  	</tr>
			</c:forEach>
		  </c:if>
	  </tbody>
	</table> 
	<div id="page" style="position:absolute;right:50px;"></div>
</body>
<script type="text/javascript" src="/iamp/js/layui-v2/layui.js"></script>
<script>
var layer;
layui.use(['layer','laypage','form','element'], function(){
	var form = layui.form;
	var element = layui.element;
	  layer = layui.layer;
	  window.layer = layer;
	  var laypage = layui.laypage; 
	  laypage.render({
		    elem: 'page'//id
		    /* ,pages:'${page.pages}' //分页数 */
		    ,count:'${page.total}'
			,limit:10
		    ,groups: 5 //连续显示分页数
		    ,curr : '${page.pageNum}'
		    ,jump: function(obj, first){
		    	if(!first){
		          layer.msg('第 '+ obj.curr +' 页');
		          $('input[name="pageNo"]').val(obj.curr);
		          $('form').submit();
		        }
		      }
		  }); 
});
	
	$('input[name="checkAll"]').change(function(){
		var selectList = $("#taskTable").find("input[type='checkbox']");
		if(this.checked){
			selectList.each(function(){
				 $(this).prop("checked",'true');
			 });
		}else{
			selectList.each(function(){
				$(this).removeAttr("checked");
			 });
		}
	});
	
	$("#add1").on("click",function(){
		var url = "/iamp/task/newTask.do";
		var data = new Object();
	 	data.title ='新建定时任务';
		data.href =url;
		data.name="xjdsrw";
		data.id="xjdsrw";
		var data = JSON.stringify(data);
		window.parent.addTab(data);
	});
	
	
	$("#add").on("click",function(){
		var url = "/iamp/task/newTask.do";
	 	var title ='新建定时任务';
		window.parent.detailWin(title,url);
	});
	
	$("#update").on("click",function(){
		var checks = $('input:checkbox[name=id]:checked');
		var taskId = "";
		if(checks.length == 1) {
			taskId = checks.eq(0).attr('id');
		}else if(checks.length == 0){
			layer.msg("请选择！");
			return ;
		}else{
			layer.msg("请单选！");
			return ;
		}
		var url = "/iamp/task/newTask.do?taskId="+taskId;
		var title ='修改定时任务';
		window.parent.detailWin(title,url);
	});

	$('#query').on('click',function() {
		$('#form').submit();
	});


	// 开启
	$("#run").on("click",function(){
		var checks = $('input:checkbox[name=id]:checked');
		var taskIds="";
		var deleteTask = '0';
		if(checks.length == 0){
			layer.msg("请选择数据！");
			return;
		}
		checks.each(function(){
			var p = $(this).closest("tr").find("input[name='taskStatus']").val();
			if(p == '-1'){
				deleteTask = '';
				showMsg('提醒','已禁止的任务不能重新启动！');
				return false;
			}else if(p == '1'){
				deleteTask = '';
				showMsg('提醒','已运行的任务不能重新启动！');
				return false;
			}else{
				$(this).closest("tr").find("td[name='status']").text("运行");
				taskIds+=$(this).attr('id');
				taskIds+=";";
			}
		});
		if(deleteTask){
			$.ajax({
			type:"post",
			url:"/iamp/task/runTask.do",
			data:{"taskIds":taskIds},
			dataType:"text",
			success:function(data){
				layer.open({
			          type: 1
			          ,title:'提醒'
			          ,content: '<div style="padding: 20px 100px;">'+data+'</div>'
			          ,btn: '关闭'
			          ,btnAlign: 'c' //按钮居中
			          ,shade: 0 //不显示遮罩
			          ,yes: function(){
			        	  layer.closeAll();
			        	  window.location.href='/iamp/task/list.do';
			          }
			        });
			}
			});
		}
			
	});
	// 继续
	$("#start").on("click",function(){
		var checks = $('input:checkbox[name=id]:checked');
		var taskIds="";
		var deleteTask = "0";
		if(checks.length == 0){
			layer.msg("请选择数据！");
			return ;
		}
		checks.each(function(){
			var p = $(this).closest("tr").find("input[name='taskStatus']").val();
			if(p == '-1'){
				deleteTask = '';
				showMsg('提醒','已禁止的任务不能重新启动！');
				return false;
			}else if(p == '1'){
				deleteTask = '';
				showMsg('提醒','已运行的任务不能重新启动！');
				return false;
			}else{
				$(this).closest("tr").find("td[name='status']").text("运行");
				taskIds+=$(this).attr('id');
				taskIds+=";";
			}
		});
		if(deleteTask){
			$.ajax({
			type:"post",
			url:"/iamp/task/startTask.do",
			data:{"taskIds":taskIds},
			dataType:"text",
			success:function(data){
				layer.open({
			          type: 1
			          ,title:'提醒'
			          ,content: '<div style="padding: 20px 100px;">'+data+'</div>'
			          ,btn: '关闭'
			          ,btnAlign: 'c' //按钮居中
			          ,shade: 0 //不显示遮罩
			          ,yes: function(){
			        	  layer.closeAll();
			        	  window.location.href='/iamp/task/list.do';
			          }
			        });
			}
			})
		}
		
		
	});
	
	// 暂停
	$("#pause").on("click",function(){
		var checks = $('input:checkbox[name=id]:checked');
		var taskIds="";
		if(checks.length == 0){
			layer.msg("请选择数据！");
			return ;
		}
		checks.each(function(){
			$(this).closest("tr").find("td[name='status']").text("暂停")
			taskIds+=$(this).attr('id');
			taskIds+=";";
		});
		$.ajax({
			type:"post",
			url:"/iamp/task/stopTask.do",
			data:{"taskIds":taskIds},
			dataType:"text",
			success:function(data){
				layer.open({
			          type: 1
			          ,title:'提醒'
			          ,content: '<div style="padding: 20px 100px;">'+data+'</div>'
			          ,btn: '关闭'
			          ,btnAlign: 'c' //按钮居中
			          ,shade: 0 //不显示遮罩
			          ,yes: function(){
			        	  layer.closeAll();
			        	  window.location.href='/iamp/task/list.do';
			          }
			        });
			}

		})
	});
	
	// 停止
	$("#stop").on("click",function(){
		var checks = $('input:checkbox[name=id]:checked');
		var taskIds="";
		if(checks.length == 0){
			layer.msg("请选择数据！");
			return ;
		}
		checks.each(function(){
			$(this).closest("tr").find("td[name='status']").text("停止");
			taskIds+=$(this).attr('id');
			taskIds+=";";
		});
		$.ajax({
			type:"post",
			url:"/iamp/task/shutdownTask.do",
			data:{"taskIds":taskIds},
			dataType:"text",
			success:function(data){
				layer.open({
			          type: 1
			          ,title:'提醒'
			          ,content: '<div style="padding: 20px 100px;">'+data+'</div>'
			          ,btn: '关闭'
			          ,btnAlign: 'c' //按钮居中
			          ,shade: 0 //不显示遮罩
			          ,yes: function(){
			        	  layer.closeAll();
			        	  window.location.href='/iamp/task/list.do';
			          }
			        });
			}
		})
	});
	
	$("#del").on("click",function(){
		var checks = $('input:checkbox[name=id]:checked');
		var taskIds="";
		var status="";
		if(checks.length == 0){
			layer.msg("请选择！");
			return ;
		}
		checks.each(function(){
			var p = $(this).closest("tr").find("input[name='taskStatus']").val();
			if(p=='0' || p == '-1'){
				$(this).closest("tr").find("td[name='status']").text("禁止");
				taskIds+=$(this).attr('id');
				taskIds+=";";
			}else{
				status+=p;
				status+=";";
			}
			
		});
		var layer = window.layer;
		layer.confirm('确定删除这些数据吗?', {
				  btn: ['确定', '取消'] //可以无限个按钮
				  ,btnAlign: 'c'
				  ,yes: function(index, layero){
				  		var statusArr = status.split(";");
				  		if(status){
				  			showMsg('提醒','运行或暂停状态的不能删除，请重新选择！');
				  		}else{
				  			$.ajax({
							type:"post",
							url:"/iamp/task/delete.do",
							data:{"taskIds":taskIds},
							dataType:"json",
							success:function(data){
								if(data) {
				                	var layer = window.layer;
				                		layer.open({
								          type: 1
								          ,title:'提醒'
								          ,content: '<div style="padding: 20px 100px;">共删除'+data+'个！</div>'
								          ,btn: '关闭'
								          ,btnAlign: 'c' //按钮居中
								          ,shade: 0 //不显示遮罩
								          ,yes: function(){
								        	  layer.closeAll();
											  window.location.href='/iamp/task/list.do';
								          }
								        });	
				               	} else {
				               		showMsg('提醒','删除失败！');
									return;
				               	}
							}
						});
				  		}
				  }})
	});
	
	$(".viewlog").on("click",function(){
		var taskId = $(this).closest("tr").find("input[name='id']").attr("id");
		var url = "/iamp/task/viewTaskLog.do?taskId="+taskId;
		var data = new Object();
	 	data.title ='定时任务日志';
		data.href =url;
		data.name="dsrwrz"
		data.id="dsrwrz"
		var data = JSON.stringify(data);
		window.parent.addTab(data);
		
	});
	
	//用于新建成功后刷新
	function refresh(){
		location.href = "/iamp/task/list.do";
	}
	
	function showMsg(title, msg) {
		layer.open({
          type: 1
          ,title:title
          ,content: '<div style="padding: 20px 100px;">'+msg+'</div>'
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