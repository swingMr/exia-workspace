<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>新建/修改资源</title>
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
<script src="/iamp/js/jquery.form.js"></script>
<style type="text/css">
.foot{
	position:relative;
	bottom:5px;  
	overflow:hidden;
}
</style>
<body>	
 	<form class="layui-form" id="form" action="/iamp/resourceCatalog/create.do" method="POST">
	 	<table lay-skin="nob" class="layui-table">
		  <colgroup>
		    <col width="150">
		    <col width="200">
		    <col>
		  </colgroup>
			 <tr>
			  	<td >
					<label class="layui-form-label">上级目录：</label>
				    <div class="layui-input-block">
				      <input type="hidden" id="parentCateLogNum" name="parentCateLogNum"/>
				      <input type="hidden" id="parentCateLogName" name="parentCateLogName"/>
				      <input type="hidden" id="parentCateLogId" name="parentCateLogId"/>
				      <input type="hidden" id="displayModeVal" name="displayModeVal" value="${displayMode}"/>
				      <span class="layui-form-label" id="parentCateLog" style="width:200px;text-align:left">无</span>
				    </div>
		    	</td>
		    	<td>
					<label class="layui-form-label">资源库：</label>
				    <div class="layui-input-block">
				      <input type="hidden" id="resourceLibNum" name="resourceLibNum"/>
				      <input type="hidden" id="resourceLibName" name="resourceLibName"/>
				      <span class="layui-form-label" id="resourceLib" style="width:200px;text-align:left">测试库</span>
				    </div>
		    	</td>
	    	</tr>
	    	<tr>
	    		<td>
					<label class="layui-form-label">目录Id：</label>
				    <div class="layui-input-block">
				      <span class="layui-form-label" id="catelogId" name="catelogId"  value=""  autocomplete="off" class="layui-input" style="text-align:left">
				    </div>
		    	</td>
	    		<td>
					<label class="layui-form-label"><span style="color:red">*</span>目录编号：</label>
				    <div class="layui-input-block">
				      <input type="text" id="cateLogNum" name="cateLogNum" required value="" lay-verify="required" placeholder="请输入目录编号" autocomplete="off" class="layui-input">
				    </div>
		    	</td>
	    	</tr>
			<tr >
	    		<td>
					<label class="layui-form-label"><span style="color:red">*</span>显示名称：</label>
				    <div class="layui-input-block">
				      <input type="text" id="cateLogName" name="cateLogName" required value="" lay-verify="required" placeholder="请输入中文名称" autocomplete="off" class="layui-input">
				    </div>
		    	</td>
			  	<td>
					<label class="layui-form-label"><span style="color:red">*</span>显示顺序：</label>
				    <div class="layui-input-block">
				    	<input type="text" id="displayOrder" name="displayOrder"required lay-verify="required|number"  lay-verify="required" placeholder="请输入显示顺序" autocomplete="off" class="layui-input" value="0">
				    </div>
		    	</td>
		    </tr>
			<tr >
			  	<td colspan="2">
			  		<label class="layui-form-label"><span style="color:red">*</span>“默认显示类型：</label>
					  <div class="layui-input-block">
					    <input type="radio" name="sex" value="0" title="列表">
					    <input type="radio" name="sex" value="1" title="卡片">
					 </div>  
			  	</td>
			</tr>		    
			<tr >
			  	<td colspan="2">
				  <div class="layui-form-item layui-form-text">
				    <label class="layui-form-label">备注：</label>
				    <div class="layui-input-block">
				      <textarea name="desc" id="remark" value="${!empty obj? obj.remark:''}"  placeholder="请输入内容" class="layui-textarea">${!empty obj? obj.remark:''}</textarea>
				    </div>
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
<script>
var ajaxSave = '/iamp/resourceCatalog/create.do';
var layer;
$(function(){
	var displayModeVal = $("#displayModeVal").val();
	
	/*layUI相关操作 有些需要依赖才能使用  */
	layui.use(['form','element','layer'], function(){
		var element = layui.element();
		var form = layui.form();
		var layer = layui.layer;
		window.element = element;
		window.form = form;
		window.layer = layer;
		//提交
		form.on('submit(save)', function(data){
			$("#form").ajaxSubmit({
			     		dataType: "text",/*设置返回值类型为文本*/
	                    success: function (data) {
	                    	layer.open({
						          type: 1
						          ,title:'提醒'
						          ,content: '<div style="padding: 20px 100px;">'+data+'</div>'
						          ,btn: '关闭'
						          ,btnAlign: 'c' //按钮居中
						          ,shade: 0 //不显示遮罩
						          ,yes: function(){
						        	 closeCurrentWin();
									 var treeObj = parent.treeObj;
									 var treeNode = parent.treeNode1;
									 var catelogId = $("#catelogId").val();
									 if(catelogId){
									 	treeObj.reAsyncChildNodes(treeNode.getParentNode(), "refresh");
									 }else{
									 	treeObj.reAsyncChildNodes(treeNode, "refresh");
									 }
						          }
						        });	
	                    }
	             	});
			return false;
	  	});
	  	
	});
	
	if(displayModeVal == "1"){
		$("input[name='sex']").eq("1").attr("checked","checked");
	}else{
		$("input[name='sex']").eq("0").attr("checked","checked");
	} 
})

	// 点取消关闭当前窗口
	$("#cancel").on("click",function(){
		closeCurrentWin();
	});
	
	function closeCurrentWin(){
	   var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
	   parent.layer.close(index);
	}
</script>
</html>