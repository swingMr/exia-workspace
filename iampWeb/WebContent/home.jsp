<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="GBK">
	<title>京华智慧化应用支撑平台</title>
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<meta name="apple-mobile-web-app-status-bar-style" content="black">
	<meta name="apple-mobile-web-app-capable" content="yes">
	<meta name="format-detection" content="telephone=no">
	<link rel="stylesheet" href="js/layui-v2/css/layui.css" media="all" />
	<link rel="stylesheet" href="js/layui-v2/css/global.css" media="all">
	<script src="/iamp/js/jquery-1.7.2.min.js"></script>
	<style type='text/css'>
.sys-title {
    display: inline-block;
    line-height: 60px; 
    color: #fff;
    font-size: 22px;
    letter-spacing: 6px;
    cursor: pointer;
}
</style>
  <!-- 注意：如果你直接复制所有代码到本地，上述css路径需要改成你本地的 -->
</head>
<body id="homeBody">
	<div class="layui-layout layui-layout-admin" style="border-bottom: solid 5px #1aa094;">
	    <!-- 头部区域（可配合layui已有的水平导航） -->
		<div class="layui-header header header-demo" style="background-color: #23262E">
			<div class="layui-main">
   				<span class="sys-title" style="margin-left:10px;">  支撑平台|管理控制台</span>
				<div class="admin-side-toggle" style="margin-left:100px;" >
					<i class="layui-icon" aria-hidden="true">&#xe647;</i>
				</div>
				<ul class="layui-nav admin-header-item">
					<li class="layui-nav-item">
						<a href="javascript:;" data-type="tabAdd">文档</a>
					</li> 
					<li class="layui-nav-item">
						<a href="javascript:;">示例</a>
					</li>
					<li class="layui-nav-item"> 
						<a href="javascript:;" class="admin-header-user">
							<span>beginner</span>
						</a>
						<dl class="layui-nav-child">
							<dd>
								<a href="javascript:;"><i class="fa fa-user-circle" aria-hidden="true"></i> 个人信息</a>
							</dd>
							<dd>
								<a href="javascript:;"><i class="fa fa-gear" aria-hidden="true"></i> 设置</a>
							</dd>
							<dd id="lock">
								<a href="javascript:;">
									<i class="fa fa-lock" aria-hidden="true" style="padding-right: 3px;padding-left: 1px;"></i> 锁屏 (Alt+L)
								</a>
							</dd>
							<dd>
								<a href="javascript:void(0)" id="logout"><i class="fa fa-sign-out" aria-hidden="true"></i> 注销</a>
							</dd>
						</dl>
					</li>
				</ul>
				<ul class="layui-nav admin-header-item-mobile">
					<li class="layui-nav-item">
						<a href="login.html"><i class="fa fa-sign-out" aria-hidden="true"></i> 注销</a>
					</li>
				</ul>
			</div>
		</div>
		  <!-- 左侧导航区域（可配合layui已有的垂直导航,内容写在了datas/nav.js） -->
		<div class="layui-side layui-bg-black" id="admin-side" style="margin-top:-10px;">
			<div class="layui-side-scroll" id="admin-navbar-side" lay-filter="side"></div>
		</div>
		  <!-- 内容主体区域 -->
		<div class="layui-body" style="bottom: 0;border-left: solid 2px #1AA094;margin-top:-10px;" id="admin-body">
				<!-- 选项卡部分 -->
			<div class="layui-tab admin-nav-card layui-tab-brief" lay-filter="admin-tab" >
				<ul class="layui-tab-title">
					<li class="layui-this">
						<i class="fa fa-dashboard" aria-hidden="true"></i>
						<cite>控制面板</cite>
					</li>
				</ul>
				<!-- 主体内容部分 -->
				<div class="layui-tab-content" style="min-height: 150px; padding: 5px 0 0 0;" id="mainFrame">
					<div class="layui-tab-item layui-show">
						<!-- <iframe src="main.html"></iframe> -->
					</div>
				</div>
			</div>
		</div>
		<!-- 底部主体区域 -->
		<div class="layui-footer footer footer-demo" id="admin-footer">
			<div class="layui-main">
				<p>2016 &copy;
					版权所有 @2016-2026 京华信息科技股份有限公司 保留所有权利
				</p>
			</div>
		</div>
		<div class="site-tree-mobile layui-hide">
			<i class="layui-icon">&#xe602;</i>
		</div>
		<div class="site-mobile-shade"></div>
		

		
<script type="text/javascript" src="js/layui-v2/layui.js"></script>
<script type="text/javascript" src="menu/nav.js"></script>
<script src="js/layui-v2/index.js"></script>
<script>
		layui.use(['layer','element','tab'], function() {
			var $ = layui.jquery;
				var layer = layui.layer;
				window.layer = layer;
				element = layui.element;
				tab = layui.tab({
					elem: '.admin-nav-card' //设置选项卡容器
						,
					contextMenu:true
				});
				window.tab = tab;
				
		});
		

		function detailWins(title,url,width,height){
			var layer = window.layer;
			layer.open({
				title:title,
				maxmin: true,
				type: 2, 
				area: [width, height],
				content:url,
			  	success: function(layero, index){
				    var body = layer.getChildFrame('body', index);
				    var iframeWin = window[layero.find('iframe')[0]['name']]; //得到iframe页的窗口对象，执行iframe页的方法：iframeWin.method();
						window.child = iframeWin;
				    }
			}); 			
		}
		function detailWin(title,url){
			var layer = window.layer;
			layer.open({
				title:title,
				maxmin: true,
				type: 2, 
				area: ['1000px', '580px'],
				content:url,
			  	success: function(layero, index){
				    var body = layer.getChildFrame('body', index);
				    var iframeWin = window[layero.find('iframe')[0]['name']]; //得到iframe页的窗口对象，执行iframe页的方法：iframeWin.method();
						window.child = iframeWin;
				    }
			}); 
		}
		
		function loadIframe(targetIframe){
			document.getElementsByName(targetIframe)[0].contentWindow.refresh();
		}
		
		function loadChildRenIframe(targetIframe){
			document.getElementsByName(targetIframe)[0].contentWindow.reload();
		}
		
		function getAttrVal(targetIframe,data){
			document.getElementsByName(targetIframe)[0].contentWindow.getAttrVal(data);
		}
		
		$('#logout').on('click',function() {
			$.ajax({
                type: "POST",
                url:'/iamp/logout',
                data:{},// 
                async: false,
                error: function(request) {
                    alert("出现异常");
                },
                dataType:"json",
                success: function(data) {
                	if(data.result) {
                		window.location.href='/iamp/login.jsp';
                	} else {
                		alert(data.msg);
						return;
                	}
                }
            });
		})
		
		function addTab(data){
			var data = JSON.parse(data);
			window.tab.tabAdd(data);
		}
		
		function deleteTab(){
			var id = $("#admin-body").find("li[class='layui-this']").attr("lay-id");
			element.tabDelete("admin-tab",id);
		}
		
</script>
	</div>
</body>
</html>