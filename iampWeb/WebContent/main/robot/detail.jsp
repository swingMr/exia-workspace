<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>�½�/�޸Ļ�����</title>
</head> 
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="format-detection" content="telephone=no">
<link rel="stylesheet" href="/iamp/js/layui/css/layui.css" media="all" />
<link rel="stylesheet" href="/iamp/js/layui/css/global.css" media="all">
<link type="text/css" href="/iamp/js/codemirror-5.2/lib/codemirror.css" rel="stylesheet" >
<link type="text/css" href="/iamp/js/codemirror-5.2/addon/hint/show-hint.css" rel="stylesheet"></link>
<script src="/iamp/js/codemirror-5.2/lib/codemirror.js"></script>
<script src="/iamp/js/codemirror-5.2/mode/python/python.js"></script>
<script src="/iamp/js/codemirror-5.2/addon/hint/show-hint.js"></script>
<script type="text/javascript" src="/iamp/js/layui/layui.js"></script>
<script src="/iamp/js/jquery-1.7.2.min.js"></script>
<style type="text/css">
.foot{
	position:relative;
	bottom:5px;  
	overflow:hidden;
}
</style>

<body>	
<div class="layui-tab" lay-filter="tab">
 	<form class="layui-form" action="">
 	<table lay-skin="nob" class="layui-table">
	  <colgroup>
	  	<col>
	    <col>
	    <col >
	  </colgroup>
		 <tr>
		  	<td >
				<label class="layui-form-label"><span style="color:red">*</span>����Ӧ�ã�</label>
			    <div class="layui-input-block">
			      <input type="hidden" name="robotId" value="${!empty obj? obj.robotId:''}">
			      <input type="hidden" name="appName" id="appName" value="${!empty obj? obj.appName:''}">
			      <select lay-filter="appCode" lay-verify="required" id="appCode" >
			      <c:forEach var="app" items="${appList}" varStatus="status">
			       	<c:choose>
	       			  <c:when test="${ obj.appName !=null && obj.appName == app.appName && obj.appCode == app.appCode}"> 
					   		<option value="${app.appCode}" selected>${app.appName}</option>
					   </c:when>
				        <c:otherwise>
				        	<option value="${app.appCode}" >${app.appName}</option>
				        </c:otherwise>
			        </c:choose>
				  </c:forEach>	        	
			      </select> 
			    </div>
	    	</td>
		  	<td colspan="3">
				<label class="layui-form-label"><span style="color:red">*</span>��ţ�</label>
			    <div class="layui-input-block">
			      <input type="text" id="robotCode" name="robotCode" required value="${!empty obj? obj.robotCode:''}" lay-verify="required" placeholder="��������" autocomplete="off" class="layui-input">
			    </div>
	    	</td>
    	</tr>
		<tr >
		  	<td>
			  <div class="layui-form-item">
			    <label class="layui-form-label">�ǳƣ�</label>
			    <div class="layui-input-block">
			     <input type="text" id="robotName" name="robotName" required value="${!empty obj? obj.robotName:''}" lay-verify="required" placeholder="�������ǳ�" autocomplete="off" class="layui-input">
			    </div>
			  </div>
	    	</td>
	    	<td style="width:25%">
			  <div class="layui-form-item">
			    <label class="layui-form-label">����ʱ�䣺</label>
			    <div class="layui-input-block" style="width:100px" >
			     <select lay-filter="serviceType" lay-verify="required" id="startTime" name="startTime">
			        <option value="00" >0��</option><option value="01" >1��</option>
			        <option value="02" >2��</option><option value="03" >3��</option>
			        <option value="04" >4��</option><option value="05" >5��</option>
			        <option value="06" >6��</option><option value="07" >7��</option>
			        <option value="08" >8��</option><option value="09" >9��</option>
					<option value="10" >10��</option><option value="11" >11��</option>
			        <option value="12" >12��</option><option value="13" >13��</option>
			        <option value="14" >14��</option><option value="15" >15��</option>
			        <option value="16" >16��</option><option value="17" >17��</option>
			        <option value="18" >18��</option><option value="19" >19��</option>
			        <option value="20" >20��</option><option value="21" >21��</option>
			        <option value="22" >22��</option><option value="23" >23��</option>
			        <option value="24" >24��</option>	        	
			      </select>
			    </div>
			  </div>
		   </td>
		 <td style="width:10px">����</td>
		 <td style="width:25%">
		  <div class="layui-form-item" style="width:100px">
		     <select lay-filter="serviceType" lay-verify="required" id="endTime" name="endTime" >
		        <option value="00" >0��</option><option value="01" >1��</option>
		        <option value="02" >2��</option><option value="03" >3��</option>
		        <option value="04" >4��</option><option value="05" >5��</option>
		        <option value="06" >6��</option><option value="07" >7��</option>
		        <option value="08" >8��</option><option value="09" >9��</option>
				<option value="10" >10��</option><option value="11" >11��</option>
		        <option value="12" >12��</option><option value="13" >13��</option>
		        <option value="14" >14��</option><option value="15" >15��</option>
		        <option value="16" >16��</option><option value="17" >17��</option>
		        <option value="18" >18��</option><option value="19" >19��</option>
		        <option value="20" >20��</option><option value="21" >21��</option>
		        <option value="22" >22��</option><option value="23" >23��</option>
		        <option value="24" >24��</option>	        	
		      </select>
		  </div>
    	</td>
	    </tr>
		<tr >
		  	<td colspan="4">
			  <div class="layui-form-item layui-form-text">
			    <label class="layui-form-label">��ӭ�ʣ�</label>
			    <div class="layui-input-block">
			      <textarea style="height:220px;" id="serviceGreeting" name="serviceGreeting" value="${!empty obj? obj.serviceGreeting:''}"  placeholder="����������" class="layui-textarea">${!empty obj? obj.serviceGreeting:''}</textarea>
			    </div>
			  </div>
  	    	</td>
	    </tr>
    </table>
	  <center>
			<button class="layui-btn layui-btn layui-btn-small foot" lay-submit="" lay-filter="save" id="save" >ȷ��</button>
			<button class="layui-btn layui-btn-danger layui-btn-small foot" id="cancel" >ȡ��</button>
	  </center>
	</form> 
</body>
<script>
var ajaxSave = '/iamp/robotDef/save.do'
var layer;
$(function(){	
	/*layUI��ز��� ��Щ��Ҫ��������ʹ��  */
	layui.use(['form','element','layer'], function(){
		var element = layui.element();
		var form = layui.form();
		var layer = layui.layer;
		window.element = element;
		window.form = form;
		window.layer = layer;
		//�ύ
		form.on('submit(save)', function(data){
			submit();
			return false;
	  	});
		
		var start = '${obj.startTime}';
		var end ='${obj.endTime}';
		if(start != null && start !=""){
			$("#startTime").val(start);
		}
		if(end != null && end !=""){
			$("#endTime").val(end);
		}
		form.render('select');
	});
});
 

	// ��ȡ���رյ�ǰ����
	$("#cancel").on("click",function(){
		closeCurrentWin();
	});

	//����
	function submit(){
		var appName = $("#appCode").find("option:selected").text();
		$("#appName").val(appName);
		var robotCode = $("#robotCode").val();
		var robotId = $("input[name='robotId']").val();
		var appName = $("#appName").val();
		var appCode = $("#appCode").val();
		var robotName = $("#robotName").val();
		var startTime = $("#startTime").val();
		var endTime = $("#endTime").val();
		var serviceGreeting = $("#serviceGreeting").val();
 		$.post(
			ajaxSave,
			{
				"robotId":robotId,
				"robotCode":robotCode,
				"appName":appName,
				"appCode":appCode,
				"robotName":robotName,
				"startTime":startTime,
				"endTime":endTime,
				"serviceGreeting":serviceGreeting
			},function(data){
				if(data.result == 'success'){
				      layer.open({
				          type: 1
				          ,title:"����"
				          ,content: '<div style="padding: 20px 100px;">�����ɹ�!</div>'
				          ,btn: '�ر�'
				          ,btnAlign: 'c' //��ť����
				          ,shade: 0 //����ʾ����
				          ,yes: function(){
				        	closeCurrentWin();
				    		parentRefresh();
				          }
				        });
				}else{
					error("����ʧ�ܣ�");
				}
			},"json"
		) 
	};

	function error(data){
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
	
	function parentRefresh(){
		var targetIframe = "jqrdy";
		window.parent.loadIframe(targetIframe);
	}
	
	function closeCurrentWin(){
	   var index = parent.layer.getFrameIndex(window.name); //�ȵõ���ǰiframe�������
	   parent.layer.close(index);
	}
</script>
</html>