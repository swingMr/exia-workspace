<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>Ӧ�ù���</title>
</head>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="format-detection" content="telephone=no">
<link rel="stylesheet" href="/iamp/js/layui/css/layui.css" media="all" />
<link rel="stylesheet" href="/iamp/js/layui/css/global.css" media="all">
<link rel="stylesheet" href="/iamp/js/ztree/css/demo.css" type="text/css">
<link rel="stylesheet" href="/iamp/js/ztree/css/zTreeStyle/zTreeStyle.css" type="text/css">
<script type="text/javascript" src="/iamp/js/ztree/js/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="/iamp/js/ztree/js/jquery.ztree.core.js"></script>
<body>	

<script>
	var setting = {	};

		var zNodes =[
			{ name:"���ڵ�1 - չ��", open:true,
				children: [
					{ name:"���ڵ�11 - �۵�",
						children: [
							{ name:"Ҷ�ӽڵ�111"},
							{ name:"Ҷ�ӽڵ�112"},
							{ name:"Ҷ�ӽڵ�113"},
							{ name:"Ҷ�ӽڵ�114"}
						]},
					{ name:"���ڵ�12 - �۵�",
						children: [
							{ name:"Ҷ�ӽڵ�121"},
							{ name:"Ҷ�ӽڵ�122"},
							{ name:"Ҷ�ӽڵ�123"},
							{ name:"Ҷ�ӽڵ�124"}
						]},
					{ name:"���ڵ�13 - û���ӽڵ�", isParent:true}
				]},
			{ name:"���ڵ�2 - �۵�",
				children: [
					{ name:"���ڵ�21 - չ��", open:true,
						children: [
							{ name:"Ҷ�ӽڵ�211"},
							{ name:"Ҷ�ӽڵ�212"},
							{ name:"Ҷ�ӽڵ�213"},
							{ name:"Ҷ�ӽڵ�214"}
						]},
					{ name:"���ڵ�22 - �۵�",
						children: [
							{ name:"Ҷ�ӽڵ�221"},
							{ name:"Ҷ�ӽڵ�222"},
							{ name:"Ҷ�ӽڵ�223"},
							{ name:"Ҷ�ӽڵ�224"}
						]},
					{ name:"���ڵ�23 - �۵�",
						children: [
							{ name:"Ҷ�ӽڵ�231"},
							{ name:"Ҷ�ӽڵ�232"},
							{ name:"Ҷ�ӽڵ�233"},
							{ name:"Ҷ�ӽڵ�234"}
						]}
				]},
			{ name:"���ڵ�3 - û���ӽڵ�", isParent:true}

		];

		$(document).ready(function(){
			$.fn.zTree.init($("#treeDemo"), setting, zNodes);
		});

</script>
<ul id="treeDemo" class="ztree"></ul>

</body>

</html>