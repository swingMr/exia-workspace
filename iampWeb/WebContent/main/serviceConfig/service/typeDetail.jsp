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
				<label class="layui-form-label"><span style="color:red">*</span>类型名称：</label>
			    <div class="layui-input-block">
			      <input type="hidden" id="typeId" name="typeId" value="${!empty obj? obj.typeId:''}">
			      <input type="text" id="typeName" name="title" required  value="${!empty obj? obj.typeName:''}" lay-verify="required" placeholder="请输入服务名称" autocomplete="off" class="layui-input">
			    </div>
	    	</td>
    	</tr>
    </table>
    	  <center>
			<button class="layui-btn layui-btn layui-btn-small foot" lay-submit="" lay-filter="save" id="save" >确定</button>
			<button class="layui-btn layui-btn-danger layui-btn-small foot" id="cancel" >取消</button>
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
				          ,title:"提醒"
				          ,content: '<div style="padding: 20px 100px;">操作成功!</div>'
				          ,btn: '关闭'
				          ,btnAlign: 'c' //按钮居中
				          ,shade: 0 //不显示遮罩
				          ,yes: function(){
				        	  closeCurrWin();
				    	   	  parentRefresh();
				          }
				        });
				}else{
					error("操作失败!");
				}
			},"json"
		)
	};

	
	/*layUI相关操作 有些需要依赖才能使用  */
	layui.use(['form','element','layer'], function(){
		var element = layui.element();
		var form = layui.form();
		var layer = layui.layer;
		window.element = element;
		window.form = form;
		window.layer = layer;
		/* 根据服务类型判断 是否显示维护脚本按钮 */
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
	
	// 点取消关闭当前窗口
	$("#cancel").on("click",function(){
		closeCurrWin();
	});
	
	function closeCurrWin(){
  		var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
  		parent.layer.close(index);
	}
</script>
</html>