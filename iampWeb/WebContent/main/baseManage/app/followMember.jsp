<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>应用管理</title>
</head>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="format-detection" content="telephone=no">
<link rel="stylesheet" href="/iamp/js/layui-v2/css/layui.css" media="all" />
<script type="text/javascript" src="/iamp/js/jquery-1.7.2.min.js"></script>
<style type="text/css">
	.btn{
		margin-top:10px;
		margin-left:10px;
	}
</style>
<body>
	<form class="layui-form" id="form" action="/iamp/app/list.do" method="post">
		<span class="layui-btn  layui-btn-normal btn layui-btn-small" id="add">新建</span>
		<span class="layui-btn  layui-btn-warm btn layui-btn-small" id="import">导入</span>
		<span class="layui-btn layui-btn-danger btn layui-btn-small" id="del">剔除</span>
		<!-- <span class="layui-btn  layui-btn-warm btn layui-btn-small" id="update">分组维护</span> -->
		<div style="float:right;position:relative;"><span class="layui-btn btn layui-btn-small" style="margin-right:10px;" id="query">查询</span></div>
    	<div style="float:right;position:relative;" class="btn"> 
			<label class="layui-form-label">所属分组：</label>
		    <div class="layui-input-block">
			      <input id="appGroup" type="text" name="appGroup"  placeholder="" autocomplete="off" class="layui-input">  
		    </div> 
    	</div>
	</form>
 	<table class="layui-table" lay-skin="line">
	  <colgroup>
	  	<col width="50">
	    <col>
	    <col width="150">
	    <col width="150">
	    <col width="120">
	    <col width="120">
	    <col width="120">
	    <col width="120">
	    <col width="150">
	  </colgroup>
	  <thead>
	    <tr>
	     <th><input type="checkbox" id="checkAll" name="checkAll"></th>
	     <th>会员账号</th><th>会员姓名</th><th>邮箱</th>
	     <th>QQ号</th><th>微信号</th><th>电话号码</th>
	     <th>归属分组</th><th>关注时间</th>
	    </tr> 
	  </thead>
	  <tbody id="tbody">
	  </tbody>
	</table> 
	<div id="page" style="position:absolute;right:50px;"></div>
</body>

<script type="text/javascript" src="/iamp/js/layui-v2/layui.js"></script>
<script>
    var pages ;
    var pageNo ;
    var appGroup ;
    var laypage;
    var count;
		
	var layer;
	layui.use(['layer','laypage','form','element'], function(){
		var form = layui.form;
		var element = layui.element;
		layer = layui.layer;
		laypage = layui.laypage; 
		getMembers(1);
	});
	var appId = '${appId}';
	function getMembers(pageNo,appGroup){
		$('#checkAll').removeAttr('checked',''); //清除全选
		var data = {};
		if(appGroup!=""){
			data.appGroup = appGroup;
		}
		data.pageNo = pageNo;
		$.ajax({
	        type: "POST",
	        url:'/iamp/app/members/'+appId,
	        data:data,
	        async: false,
	        error: function(request) {
	            alert("Connection error");
	        },
	        dataType : "json",
	        success: function(data) {
	            if(data){
	            	$('#tbody').empty();
	            	var tbody;
	            	var members = data.members;
	            	for(var i=0;i<members.length;i++){
	            		var json =  members[i] ;
						var tr ='<tr><td><input type="checkbox" appId="'+json.appId+'" memberId="'+json.memberId+'" name="memberId"></td><td>'+json.memberAccount+'</td><td>'+json.memberName+'</td><td>'+json.emailAddress
									+'</td><td>'+json.qqNum+'</td><td>'+json.wechatNum+'</td><td>'+json.phoneNum+'</td><td>'+json.appGroup+'</td><td>'+json.followTime+'</td></tr>'; 
						tbody+=tr;
	                }
	            	$('#tbody').append(tbody);
	            	pages = data.pages;
	            	pageNo = pageNo;
	            	count = data.count;
	            	if(laypage!=null){
		        		laypage.render({
						    elem: 'page' //注意，这里的 test1 是 ID，不用加 # 号
						    ,count:count//数据总数 //从服务端得到
						    ,limit:10
						    ,groups: 5
						    ,curr : pageNo
						    ,pages: pages
						    ,jump: function(obj, first){
						        if(!first){
						          layer.msg('第 '+ obj.curr +' 页');
						          getMembers(obj.curr,appGroup);
						        }
						      }
						  });
		            }
	        	            	
	            }
	        }
	    });
	}
	
	$('#checkAll').on('change',function() {
		if($(this).attr('checked') == 'checked') {
			$('input[name="memberId"]').attr('checked','');
		} else {
			$('input[name="memberId"]').removeAttr('checked','');
		}
	})
	
	$("#del").on("click",function(){
		var  checks = $('input:checkbox[name=memberId]:checked');
		if(checks.length > 0) {
			var layer = window.layer;
			layer.confirm('确定删除该数据吗?', {
					  btn: ['确定', '取消'] 
					  ,btnAlign: 'c'
					  ,yes: function(index, layero){
						  		var appIds = "";
						  		var memberIds = "";
						  		for(var n=0;n<checks.length;n++){
									appIds+=checks.eq(n).attr('appId');
									appIds+=';';
									memberIds+=checks.eq(n).attr('memberId');
									memberIds+=';';
							  	}
								$.ajax({
						                type: "post",
						                url:'/iamp/app/memDel',
						                data:{"appIds":appIds,"memberIds":memberIds},// 你的formid
						                async: false,
						                dataType:"json",
						                success: function(data) {
						                	if(data.result) {
						                	var layer = window.layer;
						                		layer.open({
										          type: 1
										          ,title:'提醒'
										          ,content: '<div style="padding: 20px 100px;">'+data.msg+'</div>'
										          ,btn: '关闭'
										          ,btnAlign: 'c' //按钮居中
										          ,shade: 0 //不显示遮罩
										          ,yes: function(){
										        	  layer.closeAll();
										        	  getMembers(pageNo,appGroup);
										          }
										        });	
						                	} else {
						                		showMsg('提醒',data.msg);
												return;
						                	}
						                }
						            });
					  
					  }})
	            
		} else {
			var layer = window.layer;
			layer.open({
	          type: 1
	          ,title:"提醒"
	          ,content: '<div style="padding: 20px 100px;">请选择数据!</div>'
	          ,btn: '关闭'
	          ,btnAlign: 'c' //按钮居中
	          ,shade: 0 //不显示遮罩
	          ,yes: function(){
	        	  layer.closeAll();
	          }
	        });	
		}
	});

	function showMsg(title, msg) {
		layer.open({
          type: 1
          ,title:title
          ,content: '<div style="padding: 20px 100px;">'+msg+'</div>'
          ,btn: '关闭'
          ,btnAlign: 'c' //按钮居中
          ,shade: 0 //不显示遮罩
          ,yes: function(){
        	  layer.closeAll();
          }
        });	
	}

	$("#add").on("click",function(){
		var title="添加会员";
		var url = "/iamp/member/create.do?appId="+appId;
		/* window.parent.detailWin(title,url); */
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

		/* var layer = window.layer;
		layer.open({
			title:'新建应用',
			type: 2, 
			area: ['1000px', '580px'],
			content:"/iamp/app/create.do"
		});  */
	});

	//用于新建成功后刷新
	function refresh(){
		location.reload();
	}

	$("#query").on("click",function(){
		appGroup = $("#appGroup").val();
		getMembers(pageNo,appGroup);
	});

	$("#import").on("click",function(){
		var title="添加用户";
		var url = "/iamp/app/memberList/"+appId;
		/* window.parent.detailWin(title,url); */
		var layer = window.layer;
		layer.open({
			title:title,
			maxmin: true,
			type: 2, 
			area: ['1000px', '480px'],
			content:url,
		  	success: function(layero, index){
			    var body = layer.getChildFrame('body', index);
			    var iframeWin = window[layero.find('iframe')[0]['name']]; //得到iframe页的窗口对象，执行iframe页的方法：iframeWin.method();
				window.child = iframeWin;
			    }
		}); 
	});
	
</script>
</html>