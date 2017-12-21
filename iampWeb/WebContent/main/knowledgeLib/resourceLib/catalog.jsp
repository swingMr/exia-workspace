<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="
text/html; charset=GBK">
<title>�½�/�޸���Դ</title>
</head> 
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="format-detection" content="telephone=no">
<link rel="stylesheet" href="/iamp/js/layui/css/layui.css" media="all" />
<link rel="stylesheet" href="/iamp/js/layui/css/global.css" media="all">
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
 	<form class="layui-form" action="">
	 	<table lay-skin="nob" class="layui-table">
		  <colgroup>
		    <col width="150">
		    <col width="200">
		    <col>
		  </colgroup>
			 <tr>
			  	<td colspan="2">
					<label class="layui-form-label">�ϼ�Ŀ¼��</label>
				    <div class="layui-input-block">
				      <span class="layui-form-label" style="width:20%;text-align:left">/�����ļ�/�й�������ȫ��������</span>
				    </div>
		    	</td>
	    	</tr>
	    	<tr>
	    		<td>
					<label class="layui-form-label">Ŀ¼��ţ�</label>
				    <div class="layui-input-block">
				      <input type="text" id="serviceCname" name="title" required value="${!empty obj? obj.serviceCname:''}" lay-verify="required" placeholder="��������������" autocomplete="off" class="layui-input">
				    </div>
		    	</td>
	    		<td>
					<label class="layui-form-label">��ʾ���ƣ�</label>
				    <div class="layui-input-block">
				      <input type="text" id="serviceCname" name="title" required value="${!empty obj? obj.serviceCname:''}" lay-verify="required" placeholder="��������������" autocomplete="off" class="layui-input">
				    </div>
		    	</td>
	    	</tr>
			<tr >
			  	<td  >
					<div class="layui-form-item">
					  <label class="layui-form-label"><span style="color:red">*</span>Ĭ����ʾ���ͣ�</label>
					  <div class="layui-input-block">
					    <input type="radio" name="sex" value="��" title="�б�">
					    <input type="radio" name="sex" value="Ů" title="��Ƭ" checked>
					  </div>
					</div>
		    	</td>
				<td>
					<label class="layui-form-label">�����ˣ�</label>
				    <div class="layui-input-block">
						<span class="layui-form-label" style="width:20%;text-align:left">ϵͳ����Ա</span>	
				    </div>
				</td>
		    </tr>
			<tr >
			  	<td colspan="2">
				  <div class="layui-form-item layui-form-text">
				    <label class="layui-form-label">��ע��</label>
				    <div class="layui-input-block">
				      <textarea name="desc" id="remark" value="${!empty obj? obj.remark:''}"  placeholder="����������" class="layui-textarea">${!empty obj? obj.remark:''}</textarea>
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
var ajaxSave = '/iamp/service/save.do'
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
		/* ���ݷ��������ж� �Ƿ���ʾά���ű���ť */
		form.on('select(serviceType)', function(data){
			 var val =  data.value;
			if(val == "2"){
				$("#script").show();
			}else{
				$("#script").hide();
			}
		});
		//ˢ��ѡ���
		if(form !=null ){
			var inputList = '${obj.serviceInput}';
			if(inputList != ""){
				$("#paramTable tbody").empty();
				inputList = JSON.parse(inputList);
				for(var i=0; inputList.length>i; i++){
					console.log(inputList);
					var obj = new Object();
					var tr  = $(addParam());
					tr.find("input[name='paramName']").val(inputList[i].paramName);
					tr.find("input[name='paramCname']").val(inputList[i].paramCnName);
					tr.find('option[value="' + inputList[i].paramDataType + '"]').attr("selected", "selected");
					$("#paramTable tbody").append(tr);
					window.form.render('select');
				}
			}
		}
		//�ύ
		form.on('submit(save)', function(data){
			submit();
			return false;
	  	});
	});
})

	// ��ȡ���رյ�ǰ����
	$("#cancel").on("click",function(){
		closeCurrentWin();
	});

	function addParam(){
		var tr ='<tr><td><input class="layui-input-block" lay-verify="required" name="paramName" style="margin-left:0px;"  value=""></td>'+
		  	'<td><input class="layui-input-block" lay-verify="required" name="paramCname" style="margin-left:0px;"  value=""></td>'+
		  	'<td><select class="paramType" id="paramType" lay-verify="required" ><option value="string">�ַ���</option>'+
		    '<option value="boolean">������</option><option value="num">����</option><option value="arr">����</option>'+
		    '<option value="date">����</option></select></td><td><i class="layui-icon removeParam" style="font-size: 30px; color: #1E9FFF;">&#x1006;</i></td></tr>';
		return tr;
	}

	//��Ӳ���
	$("#addParam").on("click",function(){
		var tr = $(addParam());
		$("#paramTable tbody").append(tr);
		//ˢ��selectѡ�����Ⱦ
		window.form.render('select');
	});
	//���ٲ���
	$("#paramTable").on("click",".removeParam",function(){
		$(this).closest("tr").remove();
	});

	//����
	function submit(){
		var paramNum = $("#paramTable").find("input[name='paramName']").length;
		var arr = [];
		for(var i=0; paramNum>i; i++){
			var param = new Object();
			param.paramName = $(".removeParam").closest('td').prev().prev().prev().find("input[name='paramName']")[i].value;
			param.paramCnName = $(".removeParam").closest('td').prev().prev().find("input[name='paramCname']")[i].value;
			param.paramDataType = $(".removeParam").closest('td').prev().find(".paramType")[i].value;
			arr.push(param);
		}
		var serviceId = $("input[name='serviceId']").val();
		var serviceName = $("#serviceName").val();
		var serviceCname = $("#serviceCname").val();
		var serviceType = $("#serviceType").val();
		var classify = $("#classify").val();
		var accessPath = $("#accessPath").val();
		var remark = $("#remark").val();
		var pythonScipt = window.editor.getValue();
		var arr = JSON.stringify(arr);
		if(serviceType =="2" && pythonScipt == ""){
			error("�ű����ݲ���Ϊ�գ�");
			return 
		}
		$("input[name='arr']").val(arr);
		$.post(
			ajaxSave,
			{
				"serviceId":serviceId,
				"serviceName":serviceName,
				"serviceCname":serviceCname,
				"serviceType":serviceType,
				"classify":classify,
				"accessPath":accessPath,
				"remark":remark,
				"pythonScipt":pythonScipt,
				"arr":arr
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
	
	$("#scriptDiv").on("click",function(){
 		var oldText = window.editor.getValue();
		window.editor.setValue(oldText); 
	});
	
	/* �����ά���ű�����ת���ű�������� */
	$("#script").on("click",function(){
 		var oldText = window.editor.getValue();
		window.editor.setValue(oldText); 
		element = window.element;
		element.tabChange('tab', "script");
	});
	
	function parentRefresh(){
		var targetIframe = "fwdy";
		window.parent.loadIframe(targetIframe);
	}
	
	function closeCurrentWin(){
	   var index = parent.layer.getFrameIndex(window.name); //�ȵõ���ǰiframe�������
	   parent.layer.close(index);
	}
</script>
</html>