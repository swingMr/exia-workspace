<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>�������</title>
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
<body>	
 	<form class="layui-form" action="">
 	<table lay-skin="nob" class="layui-table">
	  <colgroup>
	    <col width="150">
	    <col width="200">
	    <col>
	  </colgroup>
		 <tr>
		  	<td>
				<label class="layui-form-label"><span style="color:red">*</span>�������ƣ�</label>
			    <div class="layui-input-block">
			      <input type="hidden" id="typeId" name="typeId" value="${!empty obj? obj.typeId:''}">
			      <input type="text" id="typeName" name="title" required  value="${!empty obj? obj.typeName:''}" lay-verify="required" placeholder="�������������" autocomplete="off" class="layui-input">
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
<script type="text/javascript" src="/iamp/js/layui/layui.js"></script>
<script>
var ajaxSave = '/iamp/serviceType/save.do'
var layer;
	function submit(){
		var typeName = $("#typeName").val();
		var typeId = $("#typeId").val();
		$.post(
			ajaxSave,
			{
				"typeId":typeId,
				"typeName":typeName
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
				        	  closeCurrWin();
				    	   	  parentRefresh();
				          }
				        });
				}else{
					error("����ʧ��!");
				}
			},"json"
		)
	};

	
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
		
		form.on('submit(save)', function(data){
			submit();
			return false;
	  	});
	});
	
	function parentRefresh(){
		window.parent.loadIframe("fwfl");
	}
	
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
	
	// ��ȡ���رյ�ǰ����
	$("#cancel").on("click",function(){
		closeCurrWin();
	});
	
	function closeCurrWin(){
  		var index = parent.layer.getFrameIndex(window.name); //�ȵõ���ǰiframe�������
  		parent.layer.close(index);
	}
</script>
</html>