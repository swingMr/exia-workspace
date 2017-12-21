<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>新建/修改资源库</title>
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
<script src="/iamp/js/infoBasic.json"></script>
<style type="text/css">
.foot{
	text-align :center;margin:auto;left:0;right:0
}
.tagWord{
    margin:5px;
}
</style>
<body>	
<div class="layui-tab" lay-filter="tab">
  <ul class="layui-tab-title">
    <li class="layui-this">基本信息</li>
    <li lay-id="script" id="definitionDiv">属性定义</li>
    <li lay-id="script" id="configration">接入配置</li>
  </ul>
  <div class="layui-tab-content">
    <div class="layui-tab-item layui-show">
 	<form class="layui-form" action="">
	 	<table lay-skin="nob" class="layui-table">
		  <colgroup>
		    <col width="150">
		    <col width="200">
		    <col>
		  </colgroup>
			 <tr>
			  	<td>
					<label class="layui-form-label"><span style="color:red">*</span>编号：</label>
				    <div class="layui-input-block">
				      <input type="hidden" name="id" value="${!empty obj? obj.id:''}">
				      <input type="text" id="libNum" name="title" required  value="${!empty obj? obj.libNum:''}" lay-verify="required" placeholder="请填写资源库编号" autocomplete="off" class="layui-input">
				    </div>
		    	</td>
			  	<td>
					<label class="layui-form-label"><span style="color:red">*</span>名称：</label>
				    <div class="layui-input-block">
				      <input type="text" id="libName" name="title" required value="${!empty obj? obj.libName:''}" lay-verify="required" placeholder="请填写资源库名称" autocomplete="off" class="layui-input">
				    </div>
		    	</td>
	    	</tr>
			<tr >
			  	<td  >
					<div class="layui-form-item">
					  <label class="layui-form-label"><span style="color:red">*</span>类型：</label>
					  <div class="layui-input-block">
					    <input type="radio" lay-filter="rad" name="type" value="1" title="内置" checked>
					    <input type="radio" lay-filter="rad" name="type" value="2" title="扩展">
					    <input type="radio"  lay-filter="rad" name="type" value="3" title="外部接入" >
					  </div>
					</div>
		    	</td>
				<td>
				 	<select lay-filter="serviceType" lay-verify="required" id="adapter"  >
			       		 <option value="test" >选择适配器</option>
			      	 </select>
				</td>
		    </tr>
			<tr >
			  	<td >
				  <div class="layui-form-item">
					<label class="layui-form-label"><span style="color:red" class="colRequire">*</span>集合名称：</label>
				    <div class="layui-input-block">
				      <input type="text" id="collectionName" name="title"  value="${!empty obj? obj.collectionName:''}" placeholder="请填写集合名称" autocomplete="off" class="layui-input">
				    </div>
		    	</td>
		    	<td>
					<label class="layui-form-label"><span style="color:red">*</span>显示顺序：</label>
				    <div class="layui-input-block">
				      <input type="text" id="displayOrder" name="title" required value="${!empty obj? obj.displayOrder:''}" lay-verify="required" placeholder="请填写顺序" autocomplete="off" class="layui-input">
				    </div>
				</td>
		    </tr>
		    <tr>
		    	<td colspan="2">
		    		<label class="layui-form-label"><span style="color:red">*</span>资源类型：</label>
				    <div class="layui-input-block">
			      		<select lay-filter="resType" lay-verify="required" id="resType"  >
				       		 <option value="1" >文档</option>
						   	 <option value="2">图片</option>
						   	 <option value="3">视频</option>
						   	 <option value="4">音频</option>
						   	 <option value="5">地图</option>
						   	 <option value="6">人物</option>
						   	 <option value="7">法人</option>
				      	 </select>
				    </div>
		    	</td>
		    </tr>
		  <c:choose>  					  
			<c:when test="${!empty obj && obj.creatorName != null }"> 
				<tr >
				  	<td >
						<label class="layui-form-label">创建人：</label><span class="layui-form-label" style="width:20%;text-align:left">${!empty obj? obj.creatorName:''}</span>
			    	</td>
				  	<td >
						<label class="layui-form-label">创建时间：</label>
					    <span class="layui-form-label" style="width:50%;text-align:left"><fmt:formatDate value="${obj.createDate}"   pattern="yyyy-MM-dd HH:mm" type="date" dateStyle="long" /></span>
			    	</td>
			    </tr>
			 </c:when>     
		 </c:choose>
			<tr >
			  	<td colspan="2">
				  <div class="layui-form-item layui-form-text">
				    <label class="layui-form-label">备注：</label>
				    <div class="layui-input-block">
				      <textarea name="desc" id="remark" value="${!empty obj? obj.remark:''}"  placeholder="请输入内容" class="layui-textarea">${!empty obj? obj.remark:''}</textarea>
				    </div>
				  </div><input type="hidden" name="arr">
	  	    	</td>
		    </tr>
	    </table>
	  <center>
	  	<div class="foot">
			<button class="layui-btn layui-btn layui-btn-small " lay-submit="" lay-filter="save" id="save" >确定</button>
			<button class="layui-btn layui-btn-danger layui-btn-small " id="cancel" >取消</button>
	  	</div>
	  </center>
	</form> 
	</div>
	
    <div class="layui-tab-item">
	  <div class="layui-form-item layui-form-text">
	     <blockquote class="layui-elem-quote">扩展属性</blockquote>
	  <form class="layui-form" action="">
 	<table class="layui-table" lay-skin="line" id="paramTable" >
	  <colgroup>
	    <col width="150">
	    <col width="200">
	    <col width="130">
	    <col>
	  </colgroup>
	  <thead>
	    <tr>
	    <th>属性名</th><th >中文名称</th><th >数据类型</th>
	     <th >必填否</th><th >值</th><th><i class="layui-icon" id="addParam" style="font-size: 30px;color: #009688;">&#xe608;</i></th>
	    </tr> 
	  </thead>
	  <tbody style="overflow:auto;">
	  <c:forEach var="list" items="${obj.extendAttrs}" varStatus="status">
	  	<tr>
	  		<td>
	  			<input type="text" name="attrName" name="title" required value="${!empty obj? list.attrName:''}" lay-verify="required"  autocomplete="off" class="layui-input">
			</td>
	  		<td>
	  			<input type="text" name="attrCName" name="title" required value="${!empty obj? list.attrCName:''}" lay-verify="required"  autocomplete="off" class="layui-input">
	  		</td>
	  		<td>
			 	<select lay-filter="type" lay-verify="required" name="type"  class="paramType">
		       		 <option value="" >请选择</option>
		       		 <c:choose>     
		       		 	<c:when test="${!empty list.type && list.type=='int'}"> 
					  		 <option value="int"  selected = "selected">数字</option>
					  		 <option value="string" >字符串</option>
				       		 <option value="longtext" >长文本</option>
				       		 <option value="date" >日期</option>
				       		 <option value="array" >数组</option>
					   </c:when>     
 		       		 	<c:when test="${!empty list.type && list.type=='string'}"> 
					  		 <option value="int"  >数字</option>
					  		 <option value="string" selected = "selected">字符串</option>
				       		 <option value="longtext" >长文本</option>
				       		 <option value="date" >日期</option>
				       		 <option value="array" >数组</option>
					   </c:when>     
		       		 	<c:when test="${!empty list.type && list.type=='longText'}"> 
					  		 <option value="int"  >数字</option>
					  		 <option value="string" >字符串</option>
				       		 <option value="longtext" selected = "selected">长文本</option>
				       		 <option value="date" >日期</option>
				       		 <option value="array" >数组</option>
					   </c:when>     
		       		 	<c:when test="${!empty list.type && list.type=='date'}"> 
					  		 <option value="int" >数字</option>
					  		 <option value="string" >字符串</option>
				       		 <option value="longtext" >长文本</option>
				       		 <option value="date" selected = "selected">日期</option>
				       		 <option value="array" >数组</option>
					   </c:when>     
		       		 	<c:when test="${!empty list.type && list.type=='array'}"> 
					  		 <option value="int"  >数字</option>
					  		 <option value="string" >字符串</option>
				       		 <option value="longtext" >长文本</option>
				       		 <option value="date" >日期</option>
				       		 <option value="array" selected = "selected">数组</option>
					   </c:when>   
					   <c:otherwise>
							 <option value="int" >int</option>
				       		 <option value="string" >String</option>
				       		 <option value="longtext" >longText</option>
				       		 <option value="date" >date</option>
				       		 <option value="array" >array</option>
					   </c:otherwise>  
		       		</c:choose>
		      	 </select>
	  		</td>
	  		<td>
	  			<c:choose>  					  
					   <c:when test="${!empty list && list.require=='true'}"> 
					   		<input type="checkbox" name="" title="必填" lay-skin="primary" checked>
					   </c:when>     
					   <c:otherwise>
					   		<input type="checkbox" name="" title="必填" lay-skin="primary" >
					   </c:otherwise>  
				</c:choose> 
	  		</td>
	  		<td>
	  			<input type="text" name="value" name="title" required value="${!empty obj? list.value:''}" lay-verify="required"  autocomplete="off" class="layui-input">
	  		</td>
	  		<td>
	  			<i class="layui-icon removeParam" style="font-size: 15px; color: #1E9FFF;">&#x1006;</i>
	  		</td>
	  	</tr>
	  </c:forEach>
	  </tbody>
	</table>
		 <blockquote class="layui-elem-quote">基础属性</blockquote>
		 <div class="infoBasic"></div>
		  <center>
			<div class="foot">
				<button class="layui-btn layui-btn layui-btn-small" lay-submit="" lay-filter="save" id="save" >确定</button>
				<button class="layui-btn layui-btn-danger layui-btn-small" id="cancel" >取消</button>
			</div>
	  	  </center>
	</form>
	  </div>
	</div>
  </div>
</div>
</body>
<script>
var ajaxSave = '/iamp/resourceLib/save.do';
var layer;
$(function(){	
	for(var i=0; infoBasic.length>i; i++){
		var tag = "<span class='layui-btn layui-btn-small layui-btn-danger tagWord' >"+infoBasic[i].attrCName+"</span>";
		$(".infoBasic").append($(tag));
	}
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
			submit();
			return false;
	  	});
		
		form.on('radio(rad)', function(data){
			  console.log(data.value); //被点击的radio的value值
			   if(data.value == "3"){
			  		$(".colRequire").hide();
		  	   }else{
		 	 	    $(".colRequire").show();
		  	   };
		});
	});
	
})

	// 点取消关闭当前窗口
	$("#cancel").on("click",function(){
		closeCurrentWin();
	});

	//添加参数；
	function addParam(){
		var tr ='<tr><td><input type="text" name="attrName" name="title" required value="" lay-verify="required"  autocomplete="off" class="layui-input"></td>'
		+'<td><input type="text" name="attrCName" name="title" required value="" lay-verify="required"  autocomplete="off" class="layui-input"></td>'
		+'<td><select lay-filter="type" lay-verify="required" name="type" class="paramType" ><option value="int" >数字</option><option value="String" >字符串</option>'
       	+'<option value="longText" >长文本</option><option value="date" >日期</option><option value="array" >数组</option></select>'
      	+'</td><td><input type="checkbox" name="" title="必填" lay-skin="primary" ></td>'
		+'<td><input type="text" name="value" name="title" required value="" lay-verify="required"  autocomplete="off" class="layui-input">'
		+'</td><td><i class="layui-icon removeParam" style="font-size: 15px; color: #1E9FFF;">&#x1006;</i></td></tr>';
		return tr;
	}

	//添加参数
	$("#paramTable").on("click","#addParam",function(){
		var tr = $(addParam());
		$("#paramTable tbody").append(tr);
		//更新渲染
		form.render();
	});
	//减少参数
	$("#paramTable").on("click",".removeParam",function(){
		$(this).closest("tr").remove();
	});
	//保存
	function submit(){
		var paramNum = $("#paramTable").find(".removeParam").length;
		var arr = [];
		for(var i=0; paramNum>i; i++){
			var param = new Object();
			param.attrName = $(".removeParam").closest('td').prev().prev().prev().prev().prev().find("input[name='attrName']")[i].value;
			param.attrCName = $(".removeParam").closest('td').prev().prev().prev().prev().find("input[name='attrCName']")[i].value;
			param.type = $(".removeParam").closest('td').prev().prev().prev().find(".paramType")[i].value;
 			var require = typeof($(".removeParam").closest('td').prev().prev().find("input[type=checkbox]:checked")[i]);
 			if(require =='undefined'){
 				param.require = false;
 			}else{
 				param.require = true;
 			}
			param.value = $(".removeParam").closest('td').prev().find("input[name='value']")[i].value;
			arr.push(param);
		}
		var id = $("input[name='id']").val();
		var libNum = $("#libNum").val();
		var libName = $("#libName").val();
		var type = $("input[name='type']:checked").val();
		var collectionName = $("#collectionName").val();
		if(type != "3"){
			if(collectionName == ""){
				error("集合名称不能为空！");
				return;
			}
		}
		var adapter = $("#adapter").val();
		var resType = $("#resType").val();
		var displayOrder = $("#displayOrder").val();
		var remark = $("#remark").val();
		
		var arr = JSON.stringify(arr);
		console.log(arr);
		$("input[name='arr']").val(arr);
 		$.post(
			ajaxSave,
			{
				"id":id,
				"libNum":libNum,
				"libName":libName,
				"type":type,
				"adapter":adapter,
				"resType":resType,
				"collectionName":collectionName,
				"displayOrder":displayOrder,
				"remark":remark,
				"attr":arr
			},function(data){
				console.log(data);
				if(data.result == 'success'){
				      layer.open({
				          type: 1
				          ,title:"提醒"
				          ,content: '<div style="padding: 20px 100px;">操作成功!</div>'
				          ,btn: '关闭'
				          ,btnAlign: 'c' //按钮居中
				          ,shade: 0 //不显示遮罩
				          ,yes: function(){
				        	closeCurrentWin();
				    		parentRefresh();
				          }
				        });
				}else{
					error("操作失败！");
				}
			},"json"
		) 
	};

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
	
	function parentRefresh(){
		var targetIframe = "xxzykdyqy";
		window.parent.loadIframe(targetIframe);
	}
	
	function closeCurrentWin(){
	   var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
	   parent.layer.close(index);
	}
</script>
</html>