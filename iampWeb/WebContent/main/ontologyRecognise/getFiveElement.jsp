<%@ page language="java" contentType="text/html;charset=GBK"
    pageEncoding="GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>选择类对话框</title>
<script src="/iamp/js/jquery-1.7.2.min.js"></script>
<script language="javascript" type="text/javascript" src="/iamp/js/easyui-1.5/jquery.easyui.min.js" charset="GBK"></script>
<script language="JavaScript" type="text/JavaScript" src="/iamp/js/easyui-1.5/easyloader.js"></script>
<script language="JavaScript" type="text/JavaScript" src="/iamp/js/easyui-1.5/locale/easyui-lang-zh_CN.js"></script>
<link rel="stylesheet" type="text/css" href="/iamp/js/easyui-1.5/themes/bootstrap/easyui.css" id="easyuiTheme">
<link rel="stylesheet" type="text/css" href="/iamp/js/easyui-1.5/themes/icon.css" >
<style type="text/css">
.panel {
  overflow: hidden;
  font-size: 12px;
  text-align: left;
}
.menu-text,
.menu-text span {
  font-size: 12px;
  }
</style>
</head>
<body>
    <!-- 页面布局开始 -->       
	    <div id="cc" class="easyui-layout" data-options="fit:true">
		    <!-- 页面布局--中间 -->   
		    <div data-options="region:'left'" style="padding:1px;">
		    	<ul id="tt" class="easyui-tree"  data-options="onClick:clsTreeOnClick"></ul>
		    </div>
		    <!-- <div data-options="region:'center'" style="padding:1px;">
		    	<iframe name="getFiveElement" id="getFiveElement" scrolling="auto" style="width:100%;height:100%" frameborder="0"></iframe>
		    </div> -->
		</div>
	<!-- 页面布局结束 -->   
     
</body>  
<script type="text/javascript">
$(function(){
	var domainId = "${domainId}";
	var url = "/iamp/ontologyRule/getFiveElement?id="+domainId;
	$('#tt').tree({
		checkbox: false,  
		url: url,  
		animate:true, 
		onBeforeExpand: function(node){
			$('#tt').tree('options').url ="/iamp/ontologyRule/getChildDomain?id="+node.id+"&belongDomainId="+node.domainId;
		} 
	});
});

	
	function clsTreeOnClick(node)
	{
		$("#getFiveElement").attr("src","/iamp/ontologyRecognise/getFiveElement.jsp?domainId="+node.id);
		/* if(node.id.indexOf(";") < 0){
			$.messager.alert("操作提示", "领域节点不能选择","error");
			return false;
		} */
		/* var domainId = node.id;
		var url = "/iamp/ontologyRule/getFiveElement?id="+domainId;
		$('#clsTree').tree({
			checkbox: false,  
			url: url,  
			animate:true, 
			onBeforeExpand: function(node){
				$('#clsTree').tree('options').url ="/iamp/ontologyRule/getChildDomain?id="+node.id+"&belongDomainId="+node.domainId;
			} 
		}); */
	}

</script>
</html>