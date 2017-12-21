<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
<link rel="stylesheet" href="/iamp/js/layui-v2/css/global.css" media="all">
<script src="/iamp/js/jquery-1.7.2.min.js"></script>
<script src="/iamp/js/jquery.form.js"></script>
<style type="text/css">
	.btn{
		margin-top:10px;
		margin-left:10px;
	}
	
	.layui-form-label {
	    float: left;
	    display: block;
	    padding: 0px 0px;
	    width: 80px;
	    font-weight: 400;
	    text-align: right;
	}
</style>
<body>
	<form class="layui-form" id="form" action="/iamp/member/list.do" method="post">
		<input type="hidden" name="pageNo" value="${page.pageNum}">
		<input type="hidden" name="pageSize" value="${page.pageSize}">
		<div class="layui-btn-group btn" style="float:left;position:relative;">
		   <span class="layui-btn layui-btn-normal layui-btn-small" id="importexcel" title="从excel导入"><i class="layui-icon">&#xe62d;</i></span>
	       <span class="layui-btn layui-btn-normal layui-btn-small" id="exportexcel" title="导出excel"><i class="layui-icon">&#xe624;</i></span>
		</div>
		<!-- <span class="layui-btn  layui-btn-normal btn layui-btn-small" id="add">新建</span> -->
		<span class="layui-btn  layui-btn-warm btn layui-btn-small" id="update">修改</span>
		<span class="layui-btn layui-btn-danger btn layui-btn-small" id="del">删除</span>
		<span class="layui-btn btn layui-btn-small" id="domainHander">领域维护</span>
			<div style="float:right;position:relative;"><span class="layui-btn btn layui-btn-small" style="margin-right:10px;" id="query">查询</span></div>
	    	<div style="float:right;position:relative;" class="btn"> 
				<label class="layui-form-label">
					<select name="selectOpt" lay-verify="required">
				        <option value="memberAccount">账号</option>
				        <option value="memberName">姓名</option>
				        <option value="emailAddress">邮箱</option>
				        <option value="qqNum">qq号</option>
				        <option value="wechatNum">微信号</option>
				        <option value="phoneNum">手机号码</option>
				      </select>
				</label>
			    <div class="layui-input-block">
			    	<input type="text" name="queryCondition"  style="width:200px"  placeholder="请输入查询内容" autocomplete="off" class="layui-input" value="${queryCondition}">
			    </div>
	    	</div>
	</form>
 	<table class="layui-table" lay-skin="line" id="memberTable">
	  <colgroup>
	  	<col width="50">
	    <col width="120">
	    <col width="120">
	    <col width="120">
	    <col width="120">
	    <col width="120">
	    <col width="120">
	    <col width="150">
	    <col width="120">
	    <col>
	  </colgroup>
	  <thead>
	    <tr>
	     <th><input type="checkbox" id="checkAll" name="checkAll"></th>
	     <th>会员账号</th><th>会员姓名</th>
	     <th>绑定邮箱</th><th>绑定QQ号</th><th>绑定微信号</th>
	     <th>电话号码</th><th>创建时间</th><th>关注应用</th>
	    </tr> 
	  </thead>
	  <tbody>
	  	<c:forEach var="member" items="${pageList}" varStatus="status">
		  	<tr>
		  		<td><input type="checkbox" id="${member.memberId}" name="memberId"></td>
		  		<td name="memberAccount">${member.memberAccount}</td> <td name="memberName">${member.memberName}</td>
		  		<td name="emailAddress">
		  		${member.emailAddress}
		  		</td>
		 	 	<td name="qqNum">${member.qqNum}</td>
		 	 	<td name="wechatNum">
		 	 	${member.wechatNum}
		 	 	</td><td name="phoneNum">
		 	 	${member.phoneNum}
		 	 	</td><td name="createDate">${member.createDate}</td><td name="appNames">${member.appNames}</td>
		  	</tr>
		  </c:forEach>
	  </tbody>
	</table> 
	<div id="page" style="position:absolute;right:50px;"></div>
	<form class="layui-form" id="exportForm" action="/iamp/member/exportMembers.do" method="post">
		<input type="hidden" name="selectedData" id="selectedData" value="">
	</form>
</body>
<script type="text/javascript" src="/iamp/js/layui-v2/layui.js"></script>
<script>
var mainUrl = '/iamp/member/list.do';
var layer;
	layui.use(['layer','laypage','form','element'], function(){
		var form = layui.form;
		var element = layui.element;
		  layer = layui.layer;
		  window.layer = layer;
		  var laypage = layui.laypage; 
			  laypage.render({
			     elem: 'page' //注意，这里的 test1 是 ID，不用加 # 号
			    ,count:'${page.total}'//数据总数 //从服务端得到
			    ,limit:10
			    ,groups: 5
			    ,curr : '${page.pageNum}'
			    ,pages: '${page.pages}'
			    ,jump: function(obj, first){
			        if(!first){
			          layer.msg('第 '+ obj.curr +' 页');
			          $('input[name="pageNo"]').val(obj.curr);
			          $('#form').submit();
			          //location.href='/iamp/app/list.do?pageNo='+obj.curr;
			        }
			      }
			  }); 
	});
	
	$("#add").on("click",function(){
		var title="新建会员";
		var url = "/iamp/member/create.do";
		window.parent.detailWin(title,url);
		/* var layer = window.layer;
		layer.open({
			title:'新建应用',
			type: 2, 
			area: ['1000px', '580px'],
			content:"/iamp/app/create.do"
		});  */
	});
	
	$("#update").on("click",function(){
		var  checks = $('input:checkbox[name=memberId]:checked');
		if(checks.length > 0) {
			var memberId = checks.eq(0).attr('id');
			var title="修改会员";
			var url = "/iamp/member/create.do?memberId="+memberId;
			window.parent.detailWin(title,url);
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
	
	//用于新建成功后刷新
	function refresh(){
		location.href = mainUrl;
	}
	
	$("#del").on("click",function(){
	var  checks = $('input:checkbox[name=memberId]:checked');
	if(checks.length > 0) {
		var memberIds = [];
		checks.each(function(){
			memberIds.push($(this).attr("id"));
		});
		var layer = window.layer;
		layer.confirm('确定删除该数据吗?', {
				  btn: ['确定', '取消'] //可以无限个按钮
				  ,btnAlign: 'c'
				  ,yes: function(index, layero){
							$.ajax({
						                type: "GET",
						                url:'/iamp/member/delete.do',
						                data:{"memberId":JSON.stringify(memberIds)},// 你的formid
						                async: false,
						                error: function(request) {
						                    alert("Connection error");
						                },
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
													  window.location.href='/iamp/member/list.do';
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
	$('#query').on('click',function() {
		$('#form').submit();
	})
	
	$(function(){
		var selectOpt = '${selectOpt}';
		$('option[value="'+selectOpt+'"]').attr("selected",true);
	})
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
	
	$("#importexcel").on("click",function(){
		layer.open({
		  type: 2,
		  title: '从Excel导入',
  		  shade: false,
          area: ['600px', '300px'],
		  content: '/iamp/main/baseManage/memberManage/importFromExcel.jsp',
		  btn: ['确定', '取消'],
		  yes: function(index, layero){
		     var loadingEleId="logdingView";
		     var body = layer.getChildFrame('body', index);
		     var iframeHtml = layer.getChildFrame('body');
		     var _PageHeight = $(layero).find("iframe")[0].contentWindow._PageHeight;
		     var _LoadingTop = $(layero).find("iframe")[0].contentWindow._LoadingTop;
		     var _LoadingLeft = $(layero).find("iframe")[0].contentWindow._LoadingLeft;
		     var loadingHtml = '<div id="'+loadingEleId+'" style="position:absolute;left:0;width:100%;height:300px;top:0;background:rgba(0,0,0,0.55);opacity:0.8;filter:alpha(opacity=80);z-index:10000;"><div style="position: absolute; cursor1: wait; left: ' + _LoadingLeft + 'px; top:' + _LoadingTop + 'px; width: auto; height: 57px; line-height: 57px; padding-left: 50px; padding-right: 5px; background: #fff url(/iamp/image/loading.gif) no-repeat scroll 5px 10px; border: 2px solid #95B8E7; color: #696969; font-family:\'Microsoft YaHei\';">正在处理....</div></div>';
		     var belongName = iframeHtml.find("#form").find("#belongDomain").val();
		     var file = iframeHtml.find("#form").find("#file").val();
		     $("#layui-layer1").append(loadingHtml);//弹出正在加载框 
		     if(belongName){
		     	if(file.length != 0){
		     		var fileExt = file.split(".")[1];
		     		//如果是txt文件,提交到txt处理方法
		     		if(fileExt.indexOf("txt") != -1){
		     			 iframeHtml.find("#form").attr("action","/iamp/member/createMembers.do");
		     		}
		     		iframeHtml.find("#form").ajaxSubmit({
			     		dataType: "text",/*设置返回值类型为文本*/
	                    success: function (data) {
	                    	$("#layui-layer1").find('#'+loadingEleId).remove();
	                    	if(data == '0' || data == 0){
	                    		layer.msg('导入失败,请检查文档格式是否正确!', {
								  icon: 1,
								  time: 2000 //2秒关闭（如果不配置，默认是3秒）
								}, function(){
								  layer.close(index);
								  window.location.reload();
								});
	                    	}else{
	                    		  layer.msg('导入成功,共新增会员'+data+'个!', {
								  icon: 1,
								  time: 2000 //2秒关闭（如果不配置，默认是3秒）
								}, function(){
								  layer.close(index);
								  window.location.reload();
								});
	                    	}
	                    	
	                    },
	             	});
		     	}else{
		     		layer.open({
					  content: '请选择上传文件！'
					});
		     	}
		     }else{
		     	layer.open({
				  content: '领域不能为空！'
				});
		     }
		  },
		  btn2: function(index, layero){
		    layer.close(index);
		  }
		});
	});
	
	$("#exportexcel").on("click",function(){
		var selected = $("#memberTable").find('input:checkbox[name="memberId"]:checked');
		var arr = [];
		if(selected.length > 0){
			selected.each(function(){
				var obj = {};
				var memberId = $(this).closest("tr").find("td[name='id']").text().trim();
				var memberAccount = $(this).closest("tr").find("td[name='memberAccount']").text().trim();
				var memberName = $(this).closest("tr").find("td[name='memberName']").text().trim();
				var emailAddress = $(this).closest("tr").find("td[name='emailAddress']").text().trim();
				var qqNum = $(this).closest("tr").find("td[name='qqNum']").text().trim();
				var wechatNum = $(this).closest("tr").find("td[name='wechatNum']").text().trim();
				var phoneNum = $(this).closest("tr").find("td[name='phoneNum']").text().trim();
				var createDate = $(this).closest("tr").find("td[name='createDate']").text().trim();
				var remark = $(this).closest("tr").find("td[name='remark']").text().trim();
				obj.memberId = memberId;
				obj.memberAccount = memberAccount;
				obj.memberName = memberName;
				obj.emailAddress = emailAddress;
				obj.qqNum = qqNum;
				obj.wechatNum = wechatNum;
				obj.phoneNum = phoneNum;
				obj.createDate = createDate;
				obj.remark = remark;
				arr.push(obj);
			});
			$("#selectedData").val(JSON.stringify(arr));
			$("#exportForm").submit();
		}else{
			layer.open({
			  content: '请选择需要导出的信息！'
			});
		} 
	});
	
	$('input[name="checkAll"]').change(function(){
		var selectList = $("#memberTable").find("input[name='memberId']");
		if(this.checked){
			selectList.each(function(){
				 $(this).prop("checked",'true');
			 });
		}else{
			selectList.each(function(){
				$(this).removeAttr("checked");
			 });
		}
	});
	
	/* $("#domainHander").on("click",function(){
	     var selectList = $("#memberTable").find('input:checkbox[name="memberId"]:checked');
	     if(selectList.length > 0 && selectList.length == 1){
	     	var memberIds = "";
	     	var memberName = "";
			 selectList.each(function(){
				 var memberId = $(this).attr("id");
				 memberName = $(this).closest("tr").find("td").eq(2).text();
				 if(memberIds){
				 	memberIds += ";";
				 }
				 memberIds += memberId;
			 });
			layer.open({
			  type: 2,
			  title: memberName+'的关注领域',
	  		  shade: false,
	          area: ['800px', '650px'],
			  content: '/iamp/userDomain/domainList.do?memberId='+memberIds+'&memberName='+memberName,
			  btnAlign: 'c',
			  btn: ['确定', '取消'],
			  yes: function(index, layero){
			     var body = layer.getChildFrame('body', index);
			     var iframeHtml = layer.getChildFrame('body');
			     var domainNameTr = iframeHtml.find("#domainList").find("tr");
			     var userDomainIds = iframeHtml.find("#userDomainIds").val();
			     var domainNames = "";
			     domainNameTr.each(function(){
			     	var domainName = $(this).find("td").eq(1).text();
			     	var userDomainId = $(this).find("input[name='userDomainId']").attr("id");
			     	if(domainNames){
			     		domainNames += ";";
			     	}
			     	domainNames += domainName;
			     	if(userDomainIds){
			     		userDomainIds += ";";
			     	}
			     	if(userDomainId){
			     		userDomainIds += userDomainId;
			     	}
			     });
			     if(domainNames){
			     	$.ajax({
			     		url : "/iamp/userDomain/create.do",
			     		type : "post",
			     		data : {"domainNames":domainNames,"memberIds":memberIds,"userDomainIds":userDomainIds},
			     		dataType : "text",
			     		success : function(data){
			     			if(data == '0' || data == 0){
	                    		layer.msg('修改失败,请检查操作是否正确!', {
								  icon: 1,
								  time: 2000 //2秒关闭（如果不配置，默认是3秒）
								}, function(){
								  layer.close(index);
								  window.location.reload();
								});
	                    	}else{
	                    		  layer.msg('修改成功,共修改'+data+'个!', {
								  icon: 1,
								  time: 2000 //2秒关闭（如果不配置，默认是3秒）
								}, function(){
								  layer.close(index);
								  window.location.reload();
								});
	                    	}
			     		}
			     	});
		     		
			     }else{
			     	layer.open({
					  content: '领域不能为空！'
					});
					return;
			     }
			  },
			  btn2: function(index, layero){
			    layer.close(index);
			  }
			});
	     }else if(selectList.length <= 0){
	     	layer.open({
			  content: '请选择要修改的数据！'
			});
	     }else{
	     	layer.open({
			  content: '一次只能修改一条数据！'
			});
	     }
	     
	}); */
	
	$("#domainHander").on("click",function(){
		 var selectList = $("#memberTable").find('input:checkbox[name="memberId"]:checked');
	     if(selectList.length > 0 && selectList.length == 1){
	     	var memberIds = "";
	     	var memberName = "";
			 selectList.each(function(){
				 var memberId = $(this).attr("id");
				 memberName = $(this).closest("tr").find("td").eq(2).text();
				 if(memberIds){
				 	memberIds += ";";
				 }
				 memberIds += memberId;
			 });
			 
			var url = '/iamp/userDomain/domainList.do?memberId='+memberIds+'&memberName='+memberName;
			var data = new Object();
		 	data.title =memberName+'的关注领域';
			data.href =url;
			data.name="lywh"
			var data = JSON.stringify(data);
			window.parent.addTab(data);
		}else if(selectList.length <= 0){
	     	layer.open({
			  content: '请选择要修改的数据！'
			});
	     }else{
	     	layer.open({
			  content: '一次只能修改一条数据！'
			});
	     }	 
	});
	
</script>
</html>