<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>会员账号服务</title>
</head>
 <meta name="renderer" content="webkit">
 <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
 <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
 <link rel="stylesheet" href="/ExIAServer/js/layui-v2/css/layui.css"  media="all">
 <link rel="stylesheet" href="/ExIAServer/js/layui-v2/css/layui.mobile.css"  media="all">
 <style type='text/css'>
	.titleName {
		width:100px;
		text-align: right;
	}
	.subHeand{
		margin-left: 15px;
		padding: 0 10px;
		font-size: 15px;
		font-weight: 300;"
	}
	.method{
		margin-left: 15px;
		padding: 0 10px;
		font-size: 15px;
		font-weight: 300;"
	}
 </style>
<body >
	<div style="margin-left:5px;">
		<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
		  <legend typeVal="base.member">2.会员账号服务</legend>
		</fieldset>
		
		<!--  -->
		<span class="method">2.1 获取关注会员清单</span>
		<div style="float:right;position:relative;"><span class="layui-btn btn layui-btn-small" style="margin-right:10px;margin-bottom: 3px" onclick="debug(this)" num="0">进行调试</span></div>
		<table class="layui-table"  lay-skin="line" style="width:100%">
		<thead>
			<tr><td class="titleName">方法路径</td><td colspan="3">/ExIAServer/services/base/member/appmember/list</td></tr>
		</thead>
			<tr><td class="titleName">调用方式</td><td colspan="3">HTTP请求</td></tr>
			<tr><td class="titleName">方法功能</td><td colspan="3">获取当前应用关注的会员清单</td></tr>
			<tr><td class="titleName" rowspan="4">参数：</td><td>参数名</td><td>数据类型</td><td>参数说明</td></tr>
			<tr><td>extoken</td><td>String</td><td>[必须提供]访问服务的令牌</td></tr>
			<tr><td>page</td><td>Int</td><td>[可选项]当前页码，默认为1。</td></tr>
			<tr><td>pageSize</td><td>int</td><td>[可选项]分页大小，默认为50</td></tr>
			<tr><td class="titleName">返回值</td>
				<td colspan="3">{<br>
				 "status":1,#结果代码值 <br>
				 "msg":"",    #异常数据<br>
				 "data":   #返回结果<br>
				{<br>
				  "num": 627,<br>
				  "page": 1,<br>
				  "pageSize": 10,<br>
				  "informations": <br>
				  [<br>
				   {<br>
				    "menberId":"会员ID",<br>
				    "appMenberId":"会员关注ID",<br>
				    "memberAccount":"会员帐号",# appcode\menberAccount<br>
				    " memberName":"会员姓名",<br>
				    "qqNum":"绑定的QQ号码",<br>
				    "emailAddress":"绑定的邮箱地址",<br>
				    "wechatNum":"绑定的微信号",<br>
				    "phoneNumber":"绑定的手机号码",<br>
				    "remark":"备注",<br>
				    "groupName":[归属分组名]<br>
				  },<br>
				  {<br>
				    "menberId":"会员ID",<br>
				    "appMenberId":"会员关注ID",<br>
				    "memberAccount":"会员帐号",<br>
				    " memberName":"会员姓名",<br>
				    "qqNum":"绑定的QQ号码",<br>
				    "emailAddress":"绑定的邮箱地址",<br>
				    "wechatNum":"绑定的微信号",<br>
				    "phoneNumber":"绑定的手机号码",<br>
				    "remark":"备注",<br>
				    "groupName":[归属分组名]<br>
				  }<br>
				  ]<br>
				 }<br>

				}<br>
				</td>
			</tr>
		</table><br>
		
		<!--  -->
		<span class="method">2.2   注册会员信息</span>
		<div style="float:right;position:relative;"><span class="layui-btn btn layui-btn-small" style="margin-right:10px;margin-bottom: 3px" onclick="debug(this)" num="1">进行调试</span></div>
		<table class="layui-table"  lay-skin="line" style="width:100%">
		<thead>
			<tr><td class="titleName">方法路径</td><td colspan="3">/ExIAServer/services/base/member/register</td></tr>
		</thead>
			<tr><td class="titleName">调用方式</td><td colspan="3">HTTP请求</td></tr>
			<tr><td class="titleName">方法功能</td><td colspan="3">注册会员信息并进行关注当前应用</td></tr>
			<tr><td class="titleName" rowspan="12">参数：</td><td>参数名</td><td>数据类型</td><td>参数说明</td></tr>
			<tr><td>extoken</td><td>String</td><td>[必须提供]访问服务的令牌</td></tr>
			<tr><td>memberAccount</td><td>String</td><td>[必须提供]会员账号,格式为appCode\memberAccount或memberAccount</td></tr>
			<tr><td>memberName</td><td>String</td><td>[必须提供]会员姓名</td></tr>
			<tr><td>qqNum</td><td>String</td><td>[可选项] 绑定的QQ号</td></tr>
			<tr><td>emailAddress</td><td>String</td><td>[可选项] 绑定的电子邮箱</td></tr>
			<tr><td>wechatNum</td><td>String</td><td>[可选项] 绑定的微信号</td></tr>
			<tr><td>phoneNumber</td><td>String</td><td>[可选项] 绑定的手机号码</td></tr>
			<tr><td>remark</td><td>String</td><td>[可选项] 备注</td></tr>
			<tr><td>memberPsw</td><td>String</td><td>[可选项] 登录密码，默认为当前日期：yyyMMdd。</td></tr>
			<tr><td>extendAttribute</td><td>String</td><td>[可选项] json字符串</td></tr>
			<tr><td>domains</td><td>String</td><td>[可选项] 关注领域，json字符数组</td></tr>
			<tr><td class="titleName">返回值</td>
				<td colspan="3">{<br>
				"status":1,#结果代码值，-1：帐户已经存在<br>
				 "msg":"",    #异常数据<br>
				 "data":   #返回结果<br>
				  {<br>
				    "menberId":"注册成功的会员ID",<br>
				    "appMenberId":"会员关注ID"<br>
				  }<br>
				}<br>
				</td>
			</tr>
		</table><br>
		
		<!--  -->
		<span class="method">2.3   获取指定会员信息</span>
		<div style="float:right;position:relative;"><span class="layui-btn btn layui-btn-small" style="margin-right:10px;margin-bottom: 3px" onclick="debug(this)" num="2">进行调试</span></div>
		<table class="layui-table"  lay-skin="line" style="width:100%">
		<thead>
			<tr><td class="titleName">方法路径</td><td colspan="3">/ExIAServer/services/base/member/appmember/</td></tr>
		</thead>
			<tr><td class="titleName">调用方式</td><td colspan="3">HTTP请求</td></tr>
			<tr><td class="titleName">方法功能</td><td colspan="3">根据会员帐号，获取当前APP被关注的会员信息</td></tr>
			<tr><td class="titleName" rowspan="4">参数：</td><td>参数名</td><td>数据类型</td><td>参数说明</td></tr>
			<tr><td>extoken</td><td>String</td><td>[必须提供]访问服务的令牌</td></tr>
			<tr><td>idType</td><td>String</td><td>[必须提供] 身份ID类型：memberId--会员ID、appMemberId--会员关注ID、memberAccount--会员登录帐号、phoneNumber--会员绑定的手机号、qqNum--会员绑定的QQ号码、emailAddress--会员绑定的邮箱地址、wechatNum--会员绑定的微信号</td></tr>
			<tr><td>loginId</td><td>String</td><td>[必须提供] 身份ID，比如：会员ID、会员关注ID、会员登录帐号、会员绑定的手机号、会员绑定的QQ号码、会员绑定的邮箱地址、会员绑定的微信号。</td></tr>
			<tr><td class="titleName">返回值</td>
				<td colspan="3">{<br>
				  "status":1,#结果代码值，-2：帐户不存在,-9999:当前应用无权获取该会员信息<br>
				 "msg":"",    #异常数据<br>
				 "data":   #返回结果<br>
				  {<br>
				    "menberId":"会员ID",<br>
				    "appMenberId":"会员关注ID",<br>
				    "memberAccount":"会员帐号",<br>
				    " memberName":"会员姓名",<br>
				    "qqNum":"绑定的QQ号码",<br>
				    "emailAddress":"绑定的邮箱地址",<br>
				    "wechatNum":"绑定的微信号",<br>
				    "phoneNumber":"绑定的手机号码",<br>
				    "remark":"备注",<br>
				"groupName":[归属分组名],<br>
				"remark":"扩展属性"<br>
				"domains":["关注领域"],<br>
				"tags":{  #用户标签<br>
				  type1:[{"word":标签词,"hot":热度/点击率,"updateDate":最近更新时间yyyy-MM-dd HH:mm}...],<br>
				type2:[ {"word":标签词,"hot":热度/点击率,"updateDate":最近更新时间yyyy-MM-dd HH:mm}...],<br>
				…<br>
				}<br>
				  }<br>

				}<br>
				</td>
			</tr>
		</table><br>
		
		<!--  -->
		<span class="method">2.4   验证会员密码</span>
		<div style="float:right;position:relative;"><span class="layui-btn btn layui-btn-small" style="margin-right:10px;margin-bottom: 3px" onclick="debug(this)" num="3">进行调试</span></div>
		<table class="layui-table"  lay-skin="line" style="width:100%">
		<thead>
			<tr><td class="titleName">方法路径</td><td colspan="3">/ExIAServer/services/base/member/login</td></tr>
		</thead>
			<tr><td class="titleName">调用方式</td><td colspan="3">HTTP请求</td></tr>
			<tr><td class="titleName">方法功能</td><td colspan="3">验证指定会员登录密码</td></tr>
			<tr><td class="titleName" rowspan="4">参数：</td><td>参数名</td><td>数据类型</td><td>参数说明</td></tr>
			<tr><td>extoken</td><td>String</td><td>[必须提供]访问服务的令牌</td></tr>
			<tr><td>memberAccount</td><td>String</td><td>[必须提供]会员账号,格式为appCode\memberAccount或memberAccount</td></tr>
			<tr><td>qqNum</td><td>String</td><td>[可选项] 绑定的QQ号</td></tr>
			<tr><td>emailAddress</td><td>String</td><td>[可选项] 绑定的电子邮箱</td></tr>
			<tr><td>wechatNum</td><td>String</td><td>[可选项] 绑定的微信号</td></tr>
			<tr><td>phoneNumber</td><td>String</td><td>[可选项] 绑定的手机号码</td></tr>
			<tr><td>remark</td><td>String</td><td>[可选项] 备注</td></tr>
			<tr><td>memberPsw</td><td>String</td><td>[可选项] 登录密码，默认为当前日期：yyyMMdd。</td></tr>
			<tr><td>extendAttribute</td><td>String</td><td>[可选项] json字符串</td></tr>
			<tr><td>domains</td><td>String</td><td>[可选项] 关注领域，json字符数组</td></tr>
			<tr><td class="titleName">返回值</td>
				<td colspan="3">{<br>
				  "status":1,#结果代码值，-2：帐户不存在,-9999:当前应用无权获取该会员信息 -1 密码错误<br>
				 "msg":"",  #异常数据<br>
				 "data":   #返回结果<br>
				  {<br>
				    "menberId":"会员ID",<br>
				    "memberAccount":"会员帐号",<br>
				    " memberName":"会员姓名"<br>
				  }<br>

				}<br>
				</td>
			</tr>
		</table><br>
		
		<!--  -->
		<span class="method">2.5 修改会员信息</span>
		<div style="float:right;position:relative;"><span class="layui-btn btn layui-btn-small" style="margin-right:10px;margin-bottom: 3px" onclick="debug(this)" num="4">进行调试</span></div>
		<table class="layui-table"  lay-skin="line" style="width:100%">
		<thead>
			<tr><td class="titleName">方法路径</td><td colspan="3">/ExIAServer/services/base/member/update</td></tr>
		</thead>
			<tr><td class="titleName">调用方式</td><td colspan="3">HTTP请求</td></tr>
			<tr><td class="titleName">方法功能</td><td colspan="3">注册会员信息并进行关注当前应用</td></tr>
			<tr><td class="titleName" rowspan="4">参数：</td><td>参数名</td><td>数据类型</td><td>参数说明</td></tr>
			<tr><td>extoken</td><td>String</td><td>[必须提供]访问服务的令牌</td></tr>
			<tr><td>memberAccount</td><td>String</td><td>[必须提供]会员账号</td></tr>
			<tr><td>memberPsw</td><td>String</td><td>[必须提供]会员密码</td></tr>
			<tr><td class="titleName">返回值</td>
				<td colspan="3">{<br>
				  "status":1,#结果代码值，-1：帐户已经存在<br>
 				  "msg":"",    #异常数据<br>

				}<br>
				</td>
			</tr>
		</table><br>
		
		<!--  -->
		<span class="method">2.6   修改会员密码</span>
		<div style="float:right;position:relative;"><span class="layui-btn btn layui-btn-small" style="margin-right:10px;margin-bottom: 3px" onclick="debug(this)" num="5">进行调试</span></div>
		<table class="layui-table"  lay-skin="line" style="width:100%">
		<thead>
			<tr><td class="titleName">方法路径</td><td colspan="3">/ExIAServer/services/base/member/modifyMemberPsw</td></tr>
		</thead>
			<tr><td class="titleName">调用方式</td><td colspan="3">HTTP请求</td></tr>
			<tr><td class="titleName">方法功能</td><td colspan="3">注册会员信息并进行关注当前应用</td></tr>
			<tr><td class="titleName" rowspan="4">参数：</td><td>参数名</td><td>数据类型</td><td>参数说明</td></tr>
			<tr><td>extoken</td><td>String</td><td>[必须提供]访问服务的令牌</td></tr>
			<tr><td>memberAccount</td><td>String</td><td>[必须提供]会员账号</td></tr>
			<tr><td>memberPsw</td><td>String</td><td>[可选项] 旧密码</td></tr>
			<tr><td>newMemberPsw</td><td>String</td><td>[可选项] 新密码</td></tr>
			<tr><td class="titleName">返回值</td>
				<td colspan="3">{<br>
				   "status":1, 密码修改成功!，-2：密码错误 -9999:token无效<br>
				 "msg":"",    #异常数据<br>
				"data":   #返回结果<br>
				  {<br>
				    "menberId":"会员ID",<br>
				    "memberAccount":"会员帐号",<br>
				    " memberName":"会员姓名"<br>
				  }<br>

				}<br>
				</td>
			</tr>
		</table><br>
		
	</div>
<script type="text/javascript" src="/ExIAServer/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="/ExIAServer/js/interfaces.js"></script>
</body>
</html>