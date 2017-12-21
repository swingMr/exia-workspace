<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>�ɼ��������</title>
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
		<span class="layui-btn  layui-btn-normal btn layui-btn-small" id="add">�½�</span>
		<span class="layui-btn  layui-btn-warm btn layui-btn-small" id="update">�޸�</span>
		<span class="layui-btn layui-btn-danger btn layui-btn-small" id="del">ɾ��</span>
		<span class="layui-btn  layui-btn-normal btn layui-btn-small" id="run">����</span>
		<span class="layui-btn  layui-btn-warm btn layui-btn-small" id="start">����</span>
		<span class="layui-btn btn layui-btn-small" id="pause">��ͣ</span>
		<span class="layui-btn layui-btn-danger btn layui-btn-small" id="stop">ֹͣ</span>
		<div style="float:right;position:relative;"><span class="layui-btn btn layui-btn-small" style="margin-right:10px;" id="query">��ѯ</span></div>
    	<div style="float:right;position:relative;" class="btn"> 
			<label class="layui-form-label">�������ƣ�</label>
		    <div class="layui-input-block">
		    	<input type="text" name="taskName" style="width:200px" lay-verify="required" placeholder="��������������" autocomplete="off" class="layui-input" value="${!empty taskName ? taskName : ''}"> 
		    </div>
    	</div>
    	<div style="float:right;position:relative;" class="btn"> 
				<label class="layui-form-label">�������ͣ�</label>
				<div class="layui-input-block" style="width:150px">
			      	<select id="taskType" name="taskType" lay-verify="required">
			      		<c:choose>
		       			  <c:when test="${taskType=='1'}"> 
						   		<option value="all">����</option>
				        		<option value="1" selected>���ض�ʱ����</option>
				        		<option value="2">python��ʱ����</option>
				        		<option value="3">Զ�̶�ʱ����</option>
						   </c:when>
						   <c:when test="${taskType=='2'}"> 
						   		<option value="all">����</option>
				        		<option value="1">���ض�ʱ����</option>
				        		<option value="2" selected>python��ʱ����</option>
				        		<option value="3">Զ�̶�ʱ����</option>
						   </c:when>
						   <c:when test="${taskType=='3'}"> 
						   		<option value="all">����</option>
				        		<option value="1">���ض�ʱ����</option>
				        		<option value="2">python��ʱ����</option>
				        		<option value="3" selected>Զ�̶�ʱ����</option>
						   </c:when>  
						   <c:otherwise>
		       			 		<option value="all">����</option>
				        		<option value="1">���ض�ʱ����</option>
				        		<option value="2">python��ʱ����</option>
				        		<option value="3">Զ�̶�ʱ����</option>
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
	     <th>��������</th><th>��������</th><th>ִ����</th><th>����ʱ��</th>
	     <th>����״̬</th><th>����</th>
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
		  		 		���ض�ʱ����
		   			</c:if>
		  		 	<c:if test="${task.taskType=='2'}"> 
		  		 		python��ʱ����
		   			</c:if>
		   			<c:if test="${task.taskType=='3'}"> 
		  		 		Զ�̶�ʱ����
		   			</c:if>
		  		</td>
		  		<td>${task.accessPath}</td>
		  		<td>
		  			<fmt:formatDate value="${task.createDate}" pattern="yyyy-MM-dd HH:mm" type="date"/>
		  		</td>
		  		<td name="status">
		  			<c:if test="${task.status==0}">ֹͣ</c:if>
		  			<c:if test="${task.status==1}">����</c:if>
		  			<c:if test="${task.status==-1}">��ֹ</c:if>
		  			<c:if test="${task.status==2}">��ͣ</c:if>
		  		</td>
		  		<td><i class="layui-icon viewlog" style="font-size: 20px; color: #009688;cursor:pointer" title="�鿴������־">&#xe611;</i></td>
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
		    /* ,pages:'${page.pages}' //��ҳ�� */
		    ,count:'${page.total}'
			,limit:10
		    ,groups: 5 //������ʾ��ҳ��
		    ,curr : '${page.pageNum}'
		    ,jump: function(obj, first){
		    	if(!first){
		          layer.msg('�� '+ obj.curr +' ҳ');
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
	 	data.title ='�½���ʱ����';
		data.href =url;
		data.name="xjdsrw";
		data.id="xjdsrw";
		var data = JSON.stringify(data);
		window.parent.addTab(data);
	});
	
	
	$("#add").on("click",function(){
		var url = "/iamp/task/newTask.do";
	 	var title ='�½���ʱ����';
		window.parent.detailWin(title,url);
	});
	
	$("#update").on("click",function(){
		var checks = $('input:checkbox[name=id]:checked');
		var taskId = "";
		if(checks.length == 1) {
			taskId = checks.eq(0).attr('id');
		}else if(checks.length == 0){
			layer.msg("��ѡ��");
			return ;
		}else{
			layer.msg("�뵥ѡ��");
			return ;
		}
		var url = "/iamp/task/newTask.do?taskId="+taskId;
		var title ='�޸Ķ�ʱ����';
		window.parent.detailWin(title,url);
	});

	$('#query').on('click',function() {
		$('#form').submit();
	});


	// ����
	$("#run").on("click",function(){
		var checks = $('input:checkbox[name=id]:checked');
		var taskIds="";
		var deleteTask = '0';
		if(checks.length == 0){
			layer.msg("��ѡ�����ݣ�");
			return;
		}
		checks.each(function(){
			var p = $(this).closest("tr").find("input[name='taskStatus']").val();
			if(p == '-1'){
				deleteTask = '';
				showMsg('����','�ѽ�ֹ������������������');
				return false;
			}else if(p == '1'){
				deleteTask = '';
				showMsg('����','�����е�����������������');
				return false;
			}else{
				$(this).closest("tr").find("td[name='status']").text("����");
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
			          ,title:'����'
			          ,content: '<div style="padding: 20px 100px;">'+data+'</div>'
			          ,btn: '�ر�'
			          ,btnAlign: 'c' //��ť����
			          ,shade: 0 //����ʾ����
			          ,yes: function(){
			        	  layer.closeAll();
			        	  window.location.href='/iamp/task/list.do';
			          }
			        });
			}
			});
		}
			
	});
	// ����
	$("#start").on("click",function(){
		var checks = $('input:checkbox[name=id]:checked');
		var taskIds="";
		var deleteTask = "0";
		if(checks.length == 0){
			layer.msg("��ѡ�����ݣ�");
			return ;
		}
		checks.each(function(){
			var p = $(this).closest("tr").find("input[name='taskStatus']").val();
			if(p == '-1'){
				deleteTask = '';
				showMsg('����','�ѽ�ֹ������������������');
				return false;
			}else if(p == '1'){
				deleteTask = '';
				showMsg('����','�����е�����������������');
				return false;
			}else{
				$(this).closest("tr").find("td[name='status']").text("����");
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
			          ,title:'����'
			          ,content: '<div style="padding: 20px 100px;">'+data+'</div>'
			          ,btn: '�ر�'
			          ,btnAlign: 'c' //��ť����
			          ,shade: 0 //����ʾ����
			          ,yes: function(){
			        	  layer.closeAll();
			        	  window.location.href='/iamp/task/list.do';
			          }
			        });
			}
			})
		}
		
		
	});
	
	// ��ͣ
	$("#pause").on("click",function(){
		var checks = $('input:checkbox[name=id]:checked');
		var taskIds="";
		if(checks.length == 0){
			layer.msg("��ѡ�����ݣ�");
			return ;
		}
		checks.each(function(){
			$(this).closest("tr").find("td[name='status']").text("��ͣ")
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
			          ,title:'����'
			          ,content: '<div style="padding: 20px 100px;">'+data+'</div>'
			          ,btn: '�ر�'
			          ,btnAlign: 'c' //��ť����
			          ,shade: 0 //����ʾ����
			          ,yes: function(){
			        	  layer.closeAll();
			        	  window.location.href='/iamp/task/list.do';
			          }
			        });
			}

		})
	});
	
	// ֹͣ
	$("#stop").on("click",function(){
		var checks = $('input:checkbox[name=id]:checked');
		var taskIds="";
		if(checks.length == 0){
			layer.msg("��ѡ�����ݣ�");
			return ;
		}
		checks.each(function(){
			$(this).closest("tr").find("td[name='status']").text("ֹͣ");
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
			          ,title:'����'
			          ,content: '<div style="padding: 20px 100px;">'+data+'</div>'
			          ,btn: '�ر�'
			          ,btnAlign: 'c' //��ť����
			          ,shade: 0 //����ʾ����
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
			layer.msg("��ѡ��");
			return ;
		}
		checks.each(function(){
			var p = $(this).closest("tr").find("input[name='taskStatus']").val();
			if(p=='0' || p == '-1'){
				$(this).closest("tr").find("td[name='status']").text("��ֹ");
				taskIds+=$(this).attr('id');
				taskIds+=";";
			}else{
				status+=p;
				status+=";";
			}
			
		});
		var layer = window.layer;
		layer.confirm('ȷ��ɾ����Щ������?', {
				  btn: ['ȷ��', 'ȡ��'] //�������޸���ť
				  ,btnAlign: 'c'
				  ,yes: function(index, layero){
				  		var statusArr = status.split(";");
				  		if(status){
				  			showMsg('����','���л���ͣ״̬�Ĳ���ɾ����������ѡ��');
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
								          ,title:'����'
								          ,content: '<div style="padding: 20px 100px;">��ɾ��'+data+'����</div>'
								          ,btn: '�ر�'
								          ,btnAlign: 'c' //��ť����
								          ,shade: 0 //����ʾ����
								          ,yes: function(){
								        	  layer.closeAll();
											  window.location.href='/iamp/task/list.do';
								          }
								        });	
				               	} else {
				               		showMsg('����','ɾ��ʧ�ܣ�');
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
	 	data.title ='��ʱ������־';
		data.href =url;
		data.name="dsrwrz"
		data.id="dsrwrz"
		var data = JSON.stringify(data);
		window.parent.addTab(data);
		
	});
	
	//�����½��ɹ���ˢ��
	function refresh(){
		location.href = "/iamp/task/list.do";
	}
	
	function showMsg(title, msg) {
		layer.open({
          type: 1
          ,title:title
          ,content: '<div style="padding: 20px 100px;">'+msg+'</div>'
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