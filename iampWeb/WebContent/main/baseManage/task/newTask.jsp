<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>新建/修改定时任务</title>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="format-detection" content="telephone=no">
<script type="text/javascript" src="/iamp/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="/iamp/js/layui-v2/layui.js"></script>
<link rel="stylesheet" href="/iamp/js/layui-v2/css/layui.css" media="all" />
<!-- <link rel="stylesheet" href="/iamp/js/layui/css/global.css" media="all"> -->

<style>
body {
	margin: 30px;
}
.biaoqian {
	float: left;
	display: block;
	padding: 0px 6px;
	font-size: 15px;
	margin: 10px 0px 4px 4px;
	background-color: #eeeeee;
}
.layui-input-block {
	margin-left: 130px
}
.layui-form-label {
	width: 100px;
}
</style>
</head>
<body>
	<form class="layui-form" id="form">
		<input type="hidden" name="paramArr" id="paramArr" value=""/>
		<input type="hidden" value="${!empty pythonServiceId? pythonServiceId:''}" name="pythonServiceId" id="pythonServiceId">
		<input type="hidden" value="${!empty pythonServicePath? pythonServicePath:''}" name="pythonServicePath" id="pythonServicePath">
	  <div class="layui-form-item"  style="margin-left: 27px;">
	    <label class="layui-form-label"><font style="color: #FF5722">*</font>任务名称：</label>
	    <div class="layui-input-block">
	      <input type="hidden" name="taskId" value="${!empty task.taskId? task.taskId:''}">
	      <input type="text" name="taskName" required  lay-verify="required" class="layui-input" value="${!empty task.taskName? task.taskName:''}">
	    </div>
	  </div>
	  <div class="layui-form-item"  style="margin-left: 27px;">
	  	<div class="layui-inline">
	  		<label class="layui-form-label"><font style="color: #FF5722">*</font>执行器：</label>
		    <div class="layui-input-inline">
			      <select lay-filter="taskType" lay-verify="required" id="taskType" name="taskType">
			        <c:choose>  					  
					   <c:when test="${!empty task.taskType && task.taskType != '0'}"> 
					  		 <option value="${task.taskType}"><c:if test="${task.taskType=='1'}">本地定时任务</c:if><c:if test="${task.taskType=='2'}">python定时任务</c:if><c:if test="${task.taskType=='3'}">远程定时任务</c:if></option>
					   </c:when>     
					   <c:otherwise>
					   		<option value="1">本地定时任务</option>
			        		<option value="2">python定时任务</option>
			        		<option value="3">远程定时任务</option>
					   </c:otherwise>  
					</c:choose> 	        	
			   </select>
		    </div>
		     <div class="layui-inline">
		    	<c:choose>
		    		<c:when test="${!empty task.accessPath}">
		    			<input style="width:520px" type="text" name="accessPath" readonly="readonly" required  lay-verify="required" class="layui-input" value="${task.accessPath}">
		    		</c:when>
		    		<c:otherwise>
		    			<input style="width:520px" id="accessPath" type="text" name="accessPath" required  lay-verify="required" placeholder="请输入类路径(如com.test.....)" class="layui-input" value="${!empty task.accessPath? task.accessPath:''}">
		    		</c:otherwise>
		    	</c:choose>
		    </div>
	  	</div>
	   
	  </div>
	  <div class="layui-form-item" style="margin-left: 27px;">
	    <label class="layui-form-label">任务分组：</label>
	    <div class="layui-input-block">
	    	<c:choose>
	    		<c:when test="${!empty task.taskGroup}">
	    			<input type="text" name="taskGroup" readonly="readonly"  autocomplete="off" class="layui-input" value="${!empty task.taskGroup? task.taskGroup:''}">
	    		</c:when>
	    		<c:otherwise>
	    			<input type="text" name="taskGroup"  autocomplete="off" class="layui-input" value="${!empty task.taskGroup? task.taskGroup:''}">
	    		</c:otherwise>
	    	</c:choose>
	    </div>
	  </div>
	  <%-- <div class="layui-form-item"  style="margin-left: 27px;">
	    <label class="layui-form-label" style=""><font style="color: #FF5722">*</font>触发器名称：</label>
	    <div class="layui-input-block">
	    	<c:choose>
	    		<c:when test="${!empty task.triggerName}">
	    			<input type="text" name="triggerName" readonly="readonly" required lay-verify="required" autocomplete="off" class="layui-input" value="${!empty task.triggerName? task.triggerName:''}">
	    		</c:when>
	    		<c:otherwise>
	    			<input type="text" name="triggerName" required lay-verify="required" autocomplete="off" class="layui-input" value="${!empty task.triggerName? task.triggerName:''}">
	    		</c:otherwise>
	    	</c:choose>
	    </div>
	  </div>
	  <div class="layui-form-item"  style="margin-left: 27px;">
	    <label class="layui-form-label">触发器组名：</label>
	    <div class="layui-input-block">
	    	<c:choose>
	    		<c:when test="${!empty task.triggerGroup}">
	    			<input type="text" name="triggerGroup" readonly="readonly"  autocomplete="off" class="layui-input" value="${!empty task.triggerGroup? task.triggerGroup:''}">
	    		</c:when>
	    		<c:otherwise>
	    			<input type="text" name="triggerGroup" autocomplete="off" class="layui-input" value="${!empty task.triggerGroup? task.triggerGroup:''}">
	    		</c:otherwise>
	    	</c:choose>
	    </div>
	  </div> --%>
	  <div class="layui-form-item"  style="margin-left: 27px;">
	 	<div class="layui-inline">
		    <label class="layui-form-label"><font style="color: #FF5722">*</font>开始时间：</label>
		    <div class="layui-input-inline">
		      <input class="layui-input" id="startTime" name="startTime" required lay-verify="required" placeholder="请选择日期" value="${!empty task.startTime? task.startTime:''}">
		    </div>
	    </div>
	    <div class="layui-inline">
		    <label class="layui-form-label">结束时间：</label>
		    <div class="layui-input-inline">
		      <input class="layui-input" name="endTime" id="endTime"  placeholder="请选择日期" value="${!empty task.endTime? task.endTime:''}">
		    </div>
		</div>
	  </div>
	  <div class="layui-form-item" style="margin-bottom: 0px">
	    <label class="layui-form-label" style="width: 160px">
	    	<input type="radio" name="timeSet" value="1" title="时间规则配置" lay-filter="encrypt" checked>
	    	<input type="hidden" name="timeSetHidden" id="timeSetHidden" value="${!empty task.taskTime? task.taskTime:''}">
	    </label>
	  </div>
	  <div class="layui-form-item" id="timeSetting">
	  	<table class="layui-table" lay-skin="nob">
		  	<colgroup>
			    <col width="140">
			    <col width="200">
			    <col>
		  	</colgroup>
		  	 <tbody>
			    <tr>
			      <td rowspan="4" align="right"><font style="color: #FF5722">*</font>执行频率：<input type="hidden" id="frequencyInput" value="daily"></td>
			      <td><input type="radio" name="frequency" value="once" title="一次" lay-filter="frequency"></td>
			      <td><div class="layui-inline"><input class="layui-input" placeholder="请选择日期" id="onceDate" style="display: none"><div class="layui-inline"></td>
			    </tr>
			    <tr>
			      <td><input type="radio" name="frequency" value="daily" title="每日" lay-filter="frequency" checked></td>
			      <td></td>
			    </tr>
			    <tr>
			      <td><input type="radio" name="frequency" value="weekly" title="每周" lay-filter="frequency"></td>
			      <td>
			      	<input type="checkbox" name="weekCheckbox" value="1" title="周日" lay-skin="primary"> 
		      		<input type="checkbox" name="weekCheckbox" value="2" title="周一" lay-skin="primary" id="weeklyDate" >
					<input type="checkbox" name="weekCheckbox" value="3" title="周二" lay-skin="primary"> 
					<input type="checkbox" name="weekCheckbox" value="4" title="周三" lay-skin="primary"> 
					<input type="checkbox" name="weekCheckbox" value="5" title="周四" lay-skin="primary"> 
					<input type="checkbox" name="weekCheckbox" value="6" title="周五" lay-skin="primary"> 
					<input type="checkbox" name="weekCheckbox" value="7" title="周六" lay-skin="primary"> 
				  </td>
			    </tr>
			    <tr>
			      <td><input type="radio" name="frequency" value="perMonth" title="每月" lay-filter="frequency"></td>
			      <td>
			      <div class="layui-inline">
			      	<select name="weekSelect" id="weekSelect" lay-verify="">
						<option value="1">第一周</option>
						<option value="2">第二周</option>
						<option value="3">第三周</option>
						<option value="4">第四周</option>
						<option value="5">最后一周</option>
					</select> 
					</div>	
					<div class="layui-inline">
					<select name="daySelect" id="daySelect" lay-verify="">
						<option value="2">周一</option>
						<option value="3">周二</option>
						<option value="4">周三</option>
						<option value="5">周四</option>
						<option value="6">周五</option>
						<option value="7">周六</option>
						<option value="1">周日</option>
					</select> 
					</div>
				  </td>
			    </tr>
			  	</tbody>
		 	</table>
		 	<table class="layui-table" lay-skin="nob" id="repeatTable">
			  	<tbody>
			    <tr>
			    	<td align="center" style="width:160px">重复任务间隔：</td>
			    	<td colspan="2"> 
			    		<div class="layui-inline">
					      <input type="text" id="timeValue" autocomplete="off" class="layui-input">
					    </div>
					    <div class="layui-inline">
							<select name="timeSelect" id="timeSelect" lay-verify="">
								<option value="hour">小时</option>
								<option value="min">分钟</option>
								<option value="second">秒</option>
							</select> 
						</div>
			    	</td>
			    </tr>
			    <tr>
			    	<td align="center"  style="width:160px">备注：</td>
			    	<td colspan="2"> <textarea name="remarkOne" class="layui-textarea">${!empty task.remark? task.remark:''}</textarea></td>
			    </tr>
			</tbody>
	  	</table>
	  </div>
	  <div class="layui-form-item" style="margin-bottom: 0px">
	    <label class="layui-form-label" style="width: 187px">
	    	<input type="radio" name="timeSet" value="2" title="手动输入时间规则" lay-filter="encrypt" >
	    </label>
	  </div>
	   <div id="inputTimeSet" style="display: none;">
		  <div class="layui-form-item">
		  	<table class="layui-table" lay-skin="nob" >
		  		<colgroup>
				    <col width="140">
				    <col >
			  	</colgroup>
			  	 <tr>
			    	<td align="right"><font style="color: #FF5722">*</font>时间规则：</td>
			    	<td><input type="text" name="taskTime" required autocomplete="off" class="layui-input" value="${!empty task.taskTime? task.taskTime:''}" id="timeInput" ></td>
			    </tr>
			    <tr>
			    	<td align="right">备注：</td>
			    	<td> <textarea name="remarkTwo" class="layui-textarea">${!empty task.remark? task.remark:''}</textarea></td>
			    </tr>
		  	</table>
		  </div>
	  </div>
	  
	  <c:if test="${empty task.taskId}">
	  	 <table class="layui-table" id="paramTable">
			  <colgroup>
			    <col width="150">
			    <col width="200">
			    <col>
			  </colgroup>
			  <thead>
			    <tr><th style="text-align:center;"><span style="color:red">*</span>参数名</th>
			    <th style="text-align:center;">参数类型</th>
			    <th style="text-align:center;"><span style="color:red">*</span>参数值</th>
			    <th style="width:10px;"><i class="layui-icon" id="addParam" style="font-size: 20px;color: #009688;cursor:pointer;">&#xe608;</i></th></tr> 
			  </thead>
			  <thbody id="paramTbody">
			  	<tr>
			  	<td><input class="layui-input-block"  name="paramName" style="margin-left:0px;"  value=""></td>
			  	<td>
		  		<select lay-filter="paramType" class="paramType">
			        <option value="string">字符串</option>
			        <option value="number">数字</option>
			        <option value="date">日期</option>
				  </select>
			  	</td>
			  	<td><input class="layui-input-block" name="paramCname" style="margin-left:0px;width:100%;" value=""></td>
			  	<td><i class="layui-icon removeParam" style="font-size: 20px; color: #1E9FFF;cursor:pointer;">&#x1006;</i> </td>
			  	</tr>
			  </thbody>
		</table> 
	  </c:if>
	
	  
	  
	  <center>
	  	<button class="layui-btn layui-btn-normal" lay-submit lay-filter="save">确认</button>
		<span class="layui-btn layui-btn-danger layui-btn-normal"  id="cancel">取消</span>
	  </center>
	</form>
	
<script>
$(function(){
	cronTrans();
});
var saveUrl = "/iamp/task/create.do";
var layer;
var form;
layui.use(['layer','laypage','form','element','laydate'], function(){
	form = layui.form;
	var element = layui.element;
	layer = layui.layer;
	window.layer = layer;
	var laydate = layui.laydate;
	  laydate.render({
	    elem: '#startTime' //指定元素
	    ,type: 'datetime'
	    ,format: 'yyyy-MM-dd HH:mm:ss' //可任意组合
	  });
	  laydate.render({
	    elem: '#endTime' //指定元素
	    ,type: 'datetime'
	    ,format: 'yyyy-MM-dd HH:mm:ss' //可任意组合
	  });
	  laydate.render({
	    elem: '#onceDate' //指定元素
	    ,type: 'datetime'
	    ,format: 'yyyy-MM-dd HH:mm:ss' //可任意组合
	  });
	  
	// 保存按钮
	form.on('submit(save)', function (data) {
		submit(saveUrl);
		return false;
	});
	
	// 取消按钮
	$("#cancel").on("click",function(){
		closeCurrentWin();
	});
	
	
	
	function addParam(){
		var tr ='<tr><td><input class="layui-input-block" lay-verify="required" name="paramName" style="margin-left:0px;"  value=""></td>'+
		  	'<td><select class="paramType" id="paramType" lay-verify="required" ><option value="string">字符串</option>'+
		    '<option value="boolean">布尔型</option><option value="num">数字</option><option value="arr">数组</option>'+
		    '<option value="date">日期</option></select></td>'+
		    '<td><input class="layui-input-block" lay-verify="required" name="paramCname" style="margin-left:0px;width:100%;"  value=""></td>'+
		    '<td><i class="layui-icon removeParam" style="font-size: 20px; color: #1E9FFF;cursor:pointer;">&#x1006;</i></td></tr>';
		return tr;
	}

	//添加参数
	$("#addParam").on("click",function(){
		var tr = $(addParam());
		$("#paramTable tbody").append(tr);
		//刷新select选择框渲染
		window.form.render('select');
	});
	//减少参数
	$("#paramTable").on("click",".removeParam",function(){
		$(this).closest("tr").remove();
	});
	
	//扩展属性  根据数据类型 改变默认值的框
		form.on('select(paramType)', function(data){
			if (data.value == 'date') {
				var td = $(this).closest('td').next();
				td.empty();
				var input = $('<input  type="text" class="layui-input"  id="paramdate1">');
				td.append(input);
			}else{
				var td = $(this).closest('td').next();
				td.empty();
				var input = $('<input type="text" name="value" name="title"  autocomplete="off" class="layui-input">');
				td.append(input);
			} 
			form.render();
		}); 
	
	form.on('radio(encrypt)', function(data){
	  if(data.value==1){
		  $("#inputTimeSet").hide();
		  $("#timeSetting").show();
		  $("#timeSetting").find("[required]").attr("lay-verify","required");
		  $("#inputTimeSet").find("[required]").removeAttr("lay-verify");
		}else if(data.value==2){
		  $("#timeSetting").hide();
		  $("#inputTimeSet").show();
		  $("#inputTimeSet").find("[required]").attr("lay-verify","required");
		  $("#timeSetting").find("[required]").removeAttr("lay-verify");
		}

	});
	
	form.on('select(taskType)', function(data){
		$("input[name='accessPath']").attr('placeholder','');
	  if(data.value==2){
		  $('#accessPath').bind("click",function(){
		  	layer.open({
			  type: 2,
			  title: '选择python服务',
	  		  shade: false,
	          area: ['700px', '500px'],
			  content: '/iamp/service/pythonServiceList.do',
			  btn: ['确定', '取消'],
			  yes: function(index, layero){
			     var body = layer.getChildFrame('body', index);
			     var iframeHtml = layer.getChildFrame('body');
			     var selectedService = iframeHtml.find("#serviceTable").find("input[type='checkbox']:checked");
			     var pythonServiceId = "";
			     var pythonServicePath = "";
			     var pythonServiceName = "";
			     if(selectedService.length > 0){
			     	$("input[name='accessPath']").val('');
			     	selectedService.each(function(){
			     		var path = $(this).closest("tr").find("input[name='pythonPath']").val();
			     		var id = $(this).closest("tr").find("input[name='serviceId']").val();
			     		var name = $(this).closest("tr").find("td").eq(1).text();
			     		if(pythonServiceId){
			     			pythonServiceId += ";";
			     		}
			     		if(id){
			     			pythonServiceId += id;
			     		}
			     		if(pythonServiceName){
			     			pythonServiceName += ";";
			     		}
			     		if(name){
			     			pythonServiceName += name;
			     		}
			     		if(pythonServicePath){
			     			pythonServicePath += ";";
			     		}
			     		if(path){
			     			pythonServicePath += path;
			     		}
			     	});
			     	$("#pythonServiceId").val(pythonServiceId);
			     	$("#pythonServicePath").val(pythonServicePath);
			     	$("input[name='accessPath']").val(pythonServiceName);
			     	layer.close(index);
			     }else{
			     	layer.open({
					  content: '请选择服务！'
					});
			     }
			  },
			  btn2: function(index, layero){
			    layer.close(index);
			  }
		});     
		  });
	  }else{
	  		$("input[name='accessPath']").val('');
	  		if(data.value==1){
	  			$("input[name='accessPath']").attr('placeholder','请输入类路径(如com.test.....)');
	  		}else if(data.value==3){
	  			$("input[name='accessPath']").attr('placeholder','请输入远程服务请求路径');
	  		}
	  		
			$('#accessPath').unbind("click"); //移除click
	  }
	});  

	form.on('radio(frequency)',function(data){
		$("#frequencyInput").val(data.value);
		if(data.value=='once'){
		 	$("#onceDate").attr("lay-verify","required");
			$("#onceDate").css("display", "block");
		 	/* $("#startTime").removeAttr("lay-verify"); */
		 	$("input[name='weekCheckbox']").removeAttr("checked");
		 	$("#repeatTable").hide();
		}else if(data.value=='daily'){
			$("#onceDate").removeAttr("lay-verify");
			$("#onceDate").css("display", "none");
			/* $("#startTime").attr("lay-verify","required"); */
			$("input[name='weekCheckbox']").removeAttr("checked");
			$("#repeatTable").show();
		}else if(data.value=='weekly'){
			/* $("#startTime").attr("lay-verify","required"); */
			$("#weeklyDate").attr("checked","");
			$("#onceDate").removeAttr("lay-verify");
			$("#onceDate").css("display", "none");
			$("#repeatTable").show();
		}else{
			/* $("#startTime").attr("lay-verify","required"); */
			$("#onceDate").removeAttr("lay-verify");
			$("#onceDate").css("display", "none");
			$("input[name='weekCheckbox']").removeAttr("checked");
			$("#repeatTable").show();
		}
		form.render();
	})
	
});

function closeCurrentWin(){
   var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
   parent.layer.close(index);
}

//具体转换为Cron表达式
function toCron(){
	var val =  $("#frequencyInput").val(); 
	if(val=='once'){
		var onceTime = $('#onceDate').val();
		var onceTimeObj = new Date(onceTime); 
		$("#timeSetHidden").val(cronGenerate(onceTimeObj));
	}else if(val =='daily'){
		var startTime = $('#startTime').val();
		var startTimeObj = new Date(startTime);
		var cronArr = cronDailyRepeact(cronGenerate(startTimeObj));
		cronArr[3] = "*";
		cronArr[4] = "*";
		cronArr[6] = "*";
		$("#timeSetHidden").val(cronArr.join(" "));
	}else if(val == 'weekly'){
		var startTime = $('#startTime').val();
		var startTimeObj = new Date(startTime);
		var cronArr = cronDailyRepeact(cronGenerate(startTimeObj));//重复操作
		var week ="";
		$('input[name="weekCheckbox"]:checked').each(function(){ 
			week+=$(this).val();
			week+=",";
		}); 
		cronArr[3] = "?";
		cronArr[4] = "*";
		cronArr[5] = week.substring(0,week.length-1);
		cronArr[6] = "*";
		$("#timeSetHidden").val(cronArr.join(" "));
	}else if(val == 'perMonth'){
		var startTime = $('#startTime').val();
		var startTimeObj = new Date(startTime);
		var cronArr = cronDailyRepeact(cronGenerate(startTimeObj));//重复操作
		var day ="";
		if($("#weekSelect").val()==5){
			cronArr[5]=$('#daySelect').val();
			cronArr[5]+="L";
		}else{
			cronArr[5]=$("#weekSelect").val();
			cronArr[5]+="#";
			cronArr[5]+=$('#daySelect').val();
		}
		cronArr[3] = "?";
		cronArr[4] = "*";
		cronArr[6] = "*";
		$("#timeSetHidden").val(cronArr.join(" "));
	}
	console.log($("#timeSetHidden").val())
}

//将瞬间转成cron表达式
function cronGenerate(dataObj){
	var cron ="0 ";
	cron += dataObj.getMinutes();
	cron += " ";
	cron += dataObj.getHours();
	cron += " ";
	cron += dataObj.getDate();
	cron += " ";
	cron += (dataObj.getMonth()+1);
	cron += " ? ";
	cron += dataObj.getFullYear();
	return cron;
}

//重复操作
function cronDailyRepeact(cron){
	var timeSelect = $("#timeSelect").val();//获取重复任务的时间单位
	var timeValue = $('#timeValue').val();//获取重复任务的时间值
	var cronArr = cron.split(" ");//转换为数组，便于操作
	if (timeValue) {
		if(timeSelect=="hour"){
			cronArr[2] +="/";
			cronArr[2] +=timeValue;
		}else if (timeSelect=="min"){
			cronArr[1] +="/";
			cronArr[1] +=timeValue;
			cronArr[2] ="*";
		}else if (timeSelect=="second"){
			cronArr[0] +="/";
			cronArr[0] +=timeValue;
			cronArr[2] ="*";
			cronArr[1] ="*";
		}
	}
	return cronArr;
}

// 提交
function submit(url){
	var paramNum = $("#paramTable").find("input[name='paramName']");
	var arr = [];
	paramNum.each(function(){
		var param = new Object();
		var name = $(this).val();
		var value =  $(this).closest('tr').find('td').eq(2).find("input[name='paramCname']").val();
		if(name && value){
			param.name = name;
			param.value= value;
			param.dataType = $(this).closest('tr').find('td').eq(1).find(".paramType").val();
			arr.push(param);
		}
	})
	var paramArr = JSON.stringify(arr);
	$("#paramArr").val(paramArr);
	toCron();
	$.ajax({
        type: "POST",
        url:url,
        data:$('#form').serialize(),// 你的formid
        async: false,
        error: function(request) {
            alert("出现异常");
        },
        dataType:"text",
        success: function(data) {
          	if(data) {
          		layer.open({
		          type: 1
		          ,title:'提醒'
		          ,content: '<div style="padding: 20px 100px;">'+data+'</div>'
		          ,btn: '关闭'
		          ,btnAlign: 'c' //按钮居中
		          ,shade: 0 //不显示遮罩
		          ,yes: function(){
		        	  layer.closeAll();
		        	  closeCurrentWin();
		        	  var iframeNAme = 'dsrwgl';
					  window.parent.loadIframe(iframeNAme);
		          }
		        });	
              	} else {
              		showMsg('提醒',data);
					return;
              	}
              }
      });
}

function cronTrans(){
	var cron = "${!empty task.taskTime? task.taskTime:''}";
	if(cron!=""){
		var cronArr = cron.split(" ");
		if(cronArr[6]!="*"){
			var onceMoment = cronArr[6]+"-"+cronArr[4]+"-"+cronArr[3]+" "+cronArr[2]+":"+cronArr[1];
			$('#onceDate').val(onceMoment);
			$("#onceDate").css("display", "block");
			$("#frequencyInput").val("once");
			$('input[name="frequency"][value="once"]').attr("checked","");
			$("#repeatTable").hide();
		}else if (cronArr[5]=="?"){
			cronToRepeact(cronArr);
		}else if (cronArr[5].indexOf(",") >= 0){
			var arr = cronArr[5].split(",");
			$("#frequencyInput").val("weekly");
			$('input[name="frequency"][value="weekly"]').attr("checked","");
			$.each(arr,function(index,value){
				console.log(value);
				$('input[name="weekCheckbox"][value="'+value+'"]').attr("checked","");
			});
			cronToRepeact(cronArr);
		}else{
			var arr = cronArr[5].split("#");
			$("#frequencyInput").val("perMonth");
			$('input[name="frequency"][value="perMonth"]').attr("checked","");
			$("#weekSelect").val(arr[0]);
			$("#daySelect").val(arr[0]);
			cronToRepeact(cronArr);
		}
	}
}

function cronToRepeact(cronArr){
	if(cronArr[2].indexOf("/") >= 0){
		var arr = cronArr[2].split("/");
		$('#timeValue').val(arr[1]);
		$("#timeSelect").val("hour");
	}else if (cronArr[1].indexOf("/") >= 0){
		var arr = cronArr[1].split("/");
		$('#timeValue').val(arr[1]);
		$("#timeSelect").val("min");
	}else if (cronArr[0].indexOf("/") >= 0){
		var arr = cronArr[0].split("/");
		$('#timeValue').val(arr[1]);
		$("#timeSelect").val("second");
	}
}

$("#addSource").on("click",function(){
	var title="采集来源";
	var url = "/iamp/task/totree";
	layer.open({
		title:title,
		maxmin: true,
		type: 2, 
		area: ['900px', '480px'],
		content:url,
	  	success: function(layero, index){
		    var body = layer.getChildFrame('body', index);
		    var iframeWin = window[layero.find('iframe')[0]['name']]; //得到iframe页的窗口对象，执行iframe页的方法：iframeWin.method();
				window.child = iframeWin;
		    }
	}); 
});

function addSource(sourceIds,sourceNames){
	var sourceNameArr = sourceNames.split(";");
	var sourceIdArr = sourceIds.split(";");
	sourceNameArr.pop();
	$.each(sourceNameArr, function(i,val){      
		$('#sourceNameDiv').append('<div class="biaoqian" sourceId='+sourceIdArr[i]+'>'+val+'<i class="layui-icon" onclick="delSource(this)">&#x1006;</i></div>');
	}); 
	layer.closeAll();
}
function getSourceId(){
	var sourceIds = "";
	$('.biaoqian').each(function(){
		sourceIds+=$(this).attr('sourceId');
		sourceIds+=";";
	});
	$("#sourceId").val(sourceIds.substring(0,sourceIds.length-1));
}

function delSource(e){
	$(e).parent().remove();
}


</script>
</body>
</html>