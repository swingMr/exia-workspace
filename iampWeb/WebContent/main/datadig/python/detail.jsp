<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>新建/修改服务定义</title>
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
<script type="text/javascript">
$(function(){
	window.editor = CodeMirror.fromTextArea(document.getElementById("pythonScipt"),{
	    lineNumbers: true,//是否显示行号
	  	lineWrapping:true, //是否强制换行
	    mode: "python",
        extraKeys: {  
            "Ctrl": "autocomplete"  
        }
	});
    editor.on('change', function() {  
       // editor.showHint(); //满足自动触发自动联想功能  
    });
}); 
</script>
<body>	
<div class="layui-tab" lay-filter="tab"> 
  <ul class="layui-tab-title">
    <li class="layui-this">基本信息</li>
    <li lay-id="script" id="scriptDiv">脚本内容</li>
  </ul>
  <div class="layui-tab-content">
    <div class="layui-tab-item layui-show">
	 	<form class="layui-form" action="" id="form">
	 	<table lay-skin="nob" class="layui-table">
		  <colgroup>
		    <col width="150">
		    <col width="200">
		    <col>
		  </colgroup>
			 <tr>
			  	<td>
					<label class="layui-form-label"><span style="color:red">*</span>程序名称：</label>
				    <div class="layui-input-block">
				    	<input type="hidden" name="arr">
				      <input type="hidden" name="programId" value="${!empty obj? obj.programId:''}">
				      <input type="text" id="programName" name="" value="${!empty obj? obj.programName:''}" lay-verify="required" placeholder="请输入服务名称" autocomplete="off" class="layui-input">
				    </div>
		    	</td>
			  	<td>
					<label class="layui-form-label"><span style="color:red">*</span>中文名称：</label>
				    <div class="layui-input-block">
				      <input type="text" id="programCName" name="title" required value="${!empty obj? obj.programCName:''}" lay-verify="required" placeholder="请输入中文名称" autocomplete="off" class="layui-input">
				    </div>
		    	</td>
	    	</tr>
			<tr >
			  	<td>
				  <div class="layui-form-item">
				    <label class="layui-form-label"><span style="color:red">*</span>归属分组：</label>
				    <div class="layui-input-block">
				      <select lay-filter="typeName" lay-verify="required" id="typeName" >
				        <option value="" >请选择</option>	      	        
				         <c:forEach var="list" items="${list}" varStatus="status">
					      	   <c:choose>  					  
							   <c:when test="${!empty obj && obj.typeName== list.typeName}"> 
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
		    	</td>
		    	<td>
					<label class="layui-form-label"><span style="color:red">*</span>关键词：</label>
				    <div class="layui-input-block">
				      <input type="text" id="keyword" name="title" required value="${!empty obj? obj.keyWord:''}" lay-verify="required" placeholder="请输入关键词" autocomplete="off" class="layui-input">
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
	 	<table class="layui-table" id="paramTable">
		  <colgroup>
		    <col width="150">
		    <col width="200">
		    <col>
		  </colgroup>
		  <thead>
		    <tr><th style="text-align:center;";><span style="color:red">*</span>参数名</th><th style="text-align:center;";><span style="color:red">*</span>参数中文名</th><th style="text-align:center;";>参数类型</th><th style="text-align:center;";>默认值</th><th style="width:10px;"><i class="layui-icon" id="addParam" style="font-size: 30px;color: #009688;">&#xe608;</i></th></tr> 
		  </thead>
		  <thbody id="paramTbody">
		  	<tr>
		  	<td><input class="layui-input-block"  lay-verify="required" name="paramName" style="margin-left:0px;"  value=""></td>
		  	<td><input class="layui-input-block " lay-verify="required" name="paramCname" style="margin-left:0px;" value=""></td>
		  	<td>
	  		<select class="paramType"  lay-verify="required" >
		        <option value="string">字符串</option>
		        <option value="boolean">布尔型</option>
		        <option value="num">数字</option>
		        <option value="arr">数组</option>
		        <option value="date">日期</option>
			  </select>
		  	</td>
		  	<td><input class="layui-input-block " lay-verify="required" name="paramVal" style="margin-left:0px;" value="1"></td>
		  	<td><i class="layui-icon removeParam" style="font-size: 30px; color: #1E9FFF;">&#x1006;</i> </td>
		  	</tr>
		  </thbody>
		  </table>
		<center>
			<button class="layui-btn layui-btn layui-btn-small foot" lay-submit="" lay-filter="save" id="save" >确定</button>
			<button class="layui-btn layui-btn-danger layui-btn-small foot" id="cancel" >取消</button>
		</center>
		</form>
	</div>
	  <div class="layui-tab-item">
		  <div class="layui-form-item layui-form-text">
		     <textarea id="pythonScipt" name="pythonScipt" placeholder="请输入内容" class="layui-textarea" style="height:300px">${content}</textarea>
		  </div>
	  </div>
  </div>
  
</body>
<script>
var ajaxSave = '/iamp/python/save.do'
var layer;
$(function(){	
	/*layUI相关操作 有些需要依赖才能使用  */
	layui.use(['form','element','layer'], function(){
		var element = layui.element();
		var form = layui.form();
		var layer = layui.layer;
		window.element = element;
		window.form = form;
		window.layer = layer;

		if(form !=null ){
			var inputList = '${obj.programInput}';
			if(inputList != ""){
				$("#paramTable tbody").empty();
				inputList = JSON.parse(inputList);
				for(var i=0; inputList.length>i; i++){
					console.log(inputList);
					var obj = new Object();
					var tr  = $(addParam());
					tr.find("input[name='paramName']").val(inputList[i].paramName);
					tr.find("input[name='paramCname']").val(inputList[i].paramCnName);
					tr.find("input[name='paramVal']").val(inputList[i].paramValue);
					tr.find('option[value="' + inputList[i].paramDataType + '"]').attr("selected", "selected");
					$("#paramTable tbody").append(tr);
					window.form.render('select');
				}
			}
		}
		
		form.on('submit(save)', function(data){
			submit();
			return false;
	  	});
	});
})

// 点取消关闭当前窗口
$("#cancel").on("click",function(){
	closeCurrentWin();
});

function submit(){
	var programName = $("#programName").val();
	var keyword = $("#keyword").val();
	var programCName = $("#programCName").val();
	var paramNum = $("#paramTable").find("input[name='paramName']").length;
	var arr = [];
	for(var i=0; paramNum>i; i++){
		var param = new Object();
		param.paramName = $(".removeParam").closest('td').prev().prev().prev().prev().find("input[name='paramName']")[i].value;
		param.paramCnName = $(".removeParam").closest('td').prev().prev().prev().find("input[name='paramCname']")[i].value;
		param.paramDataType = $(".removeParam").closest('td').prev().prev().find(".paramType")[i].value;
		param.paramValue = $(".removeParam").closest('td').prev().find("input[name='paramVal']")[i].value;
		arr.push(param);
	}
	var programId = $("input[name='programId']").val();
	var typeName = $("#typeName").val();
	var remark = $("#remark").val();
	var pythonScipt =  window.editor.getValue();
	if(pythonScipt == ""){
	      layer.open({
	          type: 1
	          ,title:"提醒"
	          ,content: '<div style="padding: 20px 100px;">脚本内容不能为空</div>'
	          ,btn: '关闭'
	          ,btnAlign: 'c' //按钮居中
	          ,shade: 0 //不显示遮罩
	          ,yes: function(){
	            layer.closeAll();
	          }
	        });
		return 
	}
	var arr = JSON.stringify(arr);
	$("input[name='arr']").val(arr); 
	var data = {"programId":programId,
			"programName":programName,
			"programCName":programCName,
			"typeName":typeName,
			"remark":remark,
			"keyword":keyword,
			"pythonScipt":pythonScipt,
			"arr":arr};
		$.ajax({
            type: "POST",
            url:ajaxSave,
            data:data,// 你的formid
            async: false,
            error: function(request) {
                alert("出现异常");
            },
            dataType:"json",
            success: function(data) {
            	if(data.result =='success') {
            		layer.open({
			          type: 1
			          ,title:'提醒'
			          ,content: '<div style="padding: 20px 100px;">操作成功</div>'
			          ,btn: '关闭'
			          ,btnAlign: 'c' //按钮居中
			          ,shade: 0 //不显示遮罩
			          ,yes: function(){
			        	closeCurrentWin();
			    		parentRefresh();
			          }
			        });	
            	} else {
            		showMsg('提醒',data.msg);
					return;
            	}
            }
        });
};

	function addParam(){
		var tr ='<tr><td><input class="layui-input-block" name="paramName" style="margin-left:0px;"  value=""></td>'+
		  	'<td><input class="layui-input-block" name="paramCname" style="margin-left:0px;"  value=""></td>'+
		  	'<td><select class="paramType" id="paramType" lay-verify="required" ><option value="string">字符串</option>'+
		    '<option value="boolean">布尔型</option><option value="num">数字</option><option value="arr">数组</option>'+
		    '<option value="date">日期</option></select></td><td><input class="layui-input-block " lay-verify="required" name="paramVal" style="margin-left:0px;" value="1"></td>'+
		    '<td><i class="layui-icon removeParam" style="font-size: 30px; color: #1E9FFF;">&#x1006;</i></td></tr>';
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
	function parentRefresh(){
		var frameName ='pythoncxgl';
		window.parent.loadIframe(frameName);
	}
	
	function closeCurrentWin(){
	   var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
	   parent.layer.close(index);
	}
</script>
</html>