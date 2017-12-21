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
<script type="text/javascript" src="/iamp/js/layui-v2/layui.js"></script>
<script src="/iamp/js/jquery-1.7.2.min.js"></script>
<script src="/iamp/js/jquery.form.js"></script>
<style type="text/css">
	.btn{
		margin-top:10px;
		margin-left:10px;
	}
	
</style>
<body>
	<form class="layui-form" id="form" action="/iamp/userOpLog/queryList.do" method="post">
		<input type="hidden" name="pageNo" value="${page.currentPage}">
		<input type="hidden" name="pageSize" value="${page.pageSize}">
			<div style="float:right;position:relative;"><span class="layui-btn btn layui-btn-small" style="margin-right:10px;" id="query">查询</span></div>
	    	
	    	<div style="float:right;position:relative;" class="btn"> 
				<label class="layui-form-label">标题：</label>
			    <div class="layui-input-block">
			    	<input type="text" name="title"  style="width:180px"  placeholder="请输入查询内容" autocomplete="off" class="layui-input">
			    </div>
	    	</div>
	    	<div style="float:right;position:relative;" class="btn"> 
				<label class="layui-form-label">用户姓名：</label>
			    <div class="layui-input-block">
			    	<input type="text" name="userName"  style="width:120px"  placeholder="请输入查询内容" autocomplete="off" class="layui-input" value="${userName}">
			    </div>
	    	</div>
	    	<div style="float:right;position:relative;" class="btn"> 
				<label class="layui-form-label">行为：</label>
				<div class="layui-input-block" style="width:120px">
			      	<select id="opCode" name="opCode" lay-verify="required">
			      		<c:choose>
		       			  <c:when test="${opCode=='XW013'}"> 
						   		<option value="all">所有</option>
				        		<option value="XW013" selected>资源检索</option>
				        		<option value="XW014">本体识别</option>
						   </c:when>
						   <c:when test="${opCode=='XW014'}"> 
						   		<option value="all">所有</option>
				        		<option value="XW013">资源检索</option>
				        		<option value="XW014" selected>本体识别</option>
						   </c:when> 
						   <c:otherwise>
		       			 		<option value="all">所有</option>
				        		<option value="XW013">资源检索</option>
				        		<option value="XW014">本体识别</option>
		       			  </c:otherwise>
		       			</c:choose>
				      </select>
			    </div>
	    	</div>
	    	<div style="float:right;position:relative;" class="btn"> 
				<label class="layui-form-label">所在应用：</label>
				<div class="layui-input-block" style="width:160px">
			      	<select id="appCode" name="appCode" lay-verify="required">
			      		<option value="all">所有</option>
			      	<c:forEach var="app" items="${appList}" varStatus="status">
			      		<c:choose>
		       			  <c:when test="${appCode == app.appCode}"> 
						   		<option value="${app.appCode}" selected>${app.appName}</option>
						   </c:when>
						   <c:otherwise>
		       			 		<option value="${app.appCode}">${app.appName}</option>
		       			  </c:otherwise>
		       			</c:choose>
			      	</c:forEach>
				    </select>
			    </div>
	    	</div>
	</form>
 	<table class="layui-table" lay-skin="line" id="memberTable">
	  <colgroup>
	    <col width="150">
	    <col width="150">
	    <col width="150">
	    <col>
	    <col width="150">
	  </colgroup>
	  <thead>
	    <tr>
	     <th>APP名称</th><th>用户姓名</th>
	     <th>行为</th><th>行为输入内容</th><th>执行时间</th>
	    </tr> 
	  </thead>
	  <tbody>
	  	<c:forEach var="userOpLog" items="${page.rows}" varStatus="status">
		  	<tr>
		  		<input type="hidden"  name="contentDetail" value='${userOpLog.searchText}'/>
		  		<td name="appName">${userOpLog.appName}</td> <td name="userName">${userOpLog.userName}</td>
		  		<td name="opName">
		  		${userOpLog.opName}
		  		</td>
		 	 	<td name="searchText">
		 	 		<c:if test="${fn:length(userOpLog.searchText)>'80'}">  
		                 ${fn:substring(userOpLog.searchText,0,80)}......  
		            </c:if>  
		            <c:if test="${fn:length(userOpLog.searchText)<='80'}">  
		                ${userOpLog.searchText}  
		            </c:if> 
		            <i name="showContentDetail" class="layui-icon" style="margin-left:20px;cursor:pointer">&#xe7a0;</i> 
		 	 	</td>
		 	 	<td name="opDate">
		 	 	<fmt:formatDate value="${userOpLog.opDate}" pattern="yyyy-MM-dd HH:mm" type="date"/>
		 	 	</td>
		  	</tr>
		  </c:forEach> 
	  </tbody>
	</table> 
	<div id="page" style="position:absolute;right:50px;"></div>
</body>
<script>
	var formatJson = function(json, options) {
		var reg = null,
			formatted = '',
			pad = 0,
			PADDING = '    '; // one can also use '\t' or a different number of spaces
	 
		// optional settings
		options = options || {};
		// remove newline where '{' or '[' follows ':'
		options.newlineAfterColonIfBeforeBraceOrBracket = (options.newlineAfterColonIfBeforeBraceOrBracket === true) ? true : false;
		// use a space after a colon
		options.spaceAfterColon = (options.spaceAfterColon === false) ? false : true;
	 
		// begin formatting...
		if (typeof json !== 'string') {
			// make sure we start with the JSON as a string
			json = JSON.stringify(json);
		} else {
			// is already a string, so parse and re-stringify in order to remove extra whitespace
			json = JSON.parse(json);
			json = JSON.stringify(json);
		}
	 
		// add newline before and after curly braces
		reg = /([\{\}])/g;
		json = json.replace(reg, '\r\n$1\r\n');
	 
		// add newline before and after square brackets
		reg = /([\[\]])/g;
		json = json.replace(reg, '\r\n$1\r\n');
	 
		// add newline after comma
		reg = /(\,)/g;
		json = json.replace(reg, '$1\r\n');
	 
		// remove multiple newlines
		reg = /(\r\n\r\n)/g;
		json = json.replace(reg, '\r\n');
	 
		// remove newlines before commas
		reg = /\r\n\,/g;
		json = json.replace(reg, ',');
	 
		// optional formatting...
		if (!options.newlineAfterColonIfBeforeBraceOrBracket) {			
			reg = /\:\r\n\{/g;
			json = json.replace(reg, ':{');
			reg = /\:\r\n\[/g;
			json = json.replace(reg, ':[');
		}
		if (options.spaceAfterColon) {			
			reg = /\:/g;
			json = json.replace(reg, ':');
		}
	 
		$.each(json.split('\r\n'), function(index, node) {
			var i = 0, indent = 0, padding = '';
			if (node.match(/\{$/) || node.match(/\[$/)) {
				indent = 1;
			} else if (node.match(/\}/) || node.match(/\]/)) {
				if (pad !== 0)pad -= 1;
			} else {
				indent = 0;
			}
			
			for (i = 0; i < pad; i++) {
				padding += PADDING;
			}
			
			formatted += padding + node + '\r\n';
			pad += indent;
		});
		return formatted;
	};


	var layer;
	layui.use(['layer','laypage','form','element'], function(){
		var form = layui.form;
		var element = layui.element;
		  layer = layui.layer;
		  window.layer = layer;
		  var laypage = layui.laypage; 
			  laypage.render({
			     elem: 'page' //注意，这里的 test1 是 ID，不用加 # 号
			    ,count:'${page.totalCount}'//数据总数 //从服务端得到
			    ,limit:10
			    ,groups: 5
			    ,curr : '${page.currentPage}'
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
	
	
	$("i[name='showContentDetail']").on("click",function(){
		var content = $(this).closest("tr").find("input[name='contentDetail']").val();
		var content1 = formatJson(content); 
		layer.open({
	          type: 1
	          ,title:"提醒"
	          ,area: ['600px', '500px']
	          ,content: '<div style="padding: 5px 5px;"><div valign="top"><pre id="codeViewer" class="layui-code">'+content1+'</pre></div></div>'
	          ,btn: '关闭'
	          ,btnAlign: 'c' //按钮居中
	          ,shade: 0 //不显示遮罩
	          ,yes: function(){
	        	  layer.closeAll();
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
		$('#form').attr('action','/iamp/userOpLog/queryList.do');
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
	

	
</script>
</html>