<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>��Ա�˺ŷ���</title>
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
		  <legend typeVal="base.member">2.��Ա�˺ŷ���</legend>
		</fieldset>
		
		<!--  -->
		<span class="method">2.1 ��ȡ��ע��Ա�嵥</span>
		<div style="float:right;position:relative;"><span class="layui-btn btn layui-btn-small" style="margin-right:10px;margin-bottom: 3px" onclick="debug(this)" num="0">���е���</span></div>
		<table class="layui-table"  lay-skin="line" style="width:100%">
		<thead>
			<tr><td class="titleName">����·��</td><td colspan="3">/ExIAServer/services/base/member/appmember/list</td></tr>
		</thead>
			<tr><td class="titleName">���÷�ʽ</td><td colspan="3">HTTP����</td></tr>
			<tr><td class="titleName">��������</td><td colspan="3">��ȡ��ǰӦ�ù�ע�Ļ�Ա�嵥</td></tr>
			<tr><td class="titleName" rowspan="4">������</td><td>������</td><td>��������</td><td>����˵��</td></tr>
			<tr><td>extoken</td><td>String</td><td>[�����ṩ]���ʷ��������</td></tr>
			<tr><td>page</td><td>Int</td><td>[��ѡ��]��ǰҳ�룬Ĭ��Ϊ1��</td></tr>
			<tr><td>pageSize</td><td>int</td><td>[��ѡ��]��ҳ��С��Ĭ��Ϊ50</td></tr>
			<tr><td class="titleName">����ֵ</td>
				<td colspan="3">{<br>
				 "status":1,#�������ֵ <br>
				 "msg":"",    #�쳣����<br>
				 "data":   #���ؽ��<br>
				{<br>
				  "num": 627,<br>
				  "page": 1,<br>
				  "pageSize": 10,<br>
				  "informations": <br>
				  [<br>
				   {<br>
				    "menberId":"��ԱID",<br>
				    "appMenberId":"��Ա��עID",<br>
				    "memberAccount":"��Ա�ʺ�",# appcode\menberAccount<br>
				    " memberName":"��Ա����",<br>
				    "qqNum":"�󶨵�QQ����",<br>
				    "emailAddress":"�󶨵������ַ",<br>
				    "wechatNum":"�󶨵�΢�ź�",<br>
				    "phoneNumber":"�󶨵��ֻ�����",<br>
				    "remark":"��ע",<br>
				    "groupName":[����������]<br>
				  },<br>
				  {<br>
				    "menberId":"��ԱID",<br>
				    "appMenberId":"��Ա��עID",<br>
				    "memberAccount":"��Ա�ʺ�",<br>
				    " memberName":"��Ա����",<br>
				    "qqNum":"�󶨵�QQ����",<br>
				    "emailAddress":"�󶨵������ַ",<br>
				    "wechatNum":"�󶨵�΢�ź�",<br>
				    "phoneNumber":"�󶨵��ֻ�����",<br>
				    "remark":"��ע",<br>
				    "groupName":[����������]<br>
				  }<br>
				  ]<br>
				 }<br>

				}<br>
				</td>
			</tr>
		</table><br>
		
		<!--  -->
		<span class="method">2.2   ע���Ա��Ϣ</span>
		<div style="float:right;position:relative;"><span class="layui-btn btn layui-btn-small" style="margin-right:10px;margin-bottom: 3px" onclick="debug(this)" num="1">���е���</span></div>
		<table class="layui-table"  lay-skin="line" style="width:100%">
		<thead>
			<tr><td class="titleName">����·��</td><td colspan="3">/ExIAServer/services/base/member/register</td></tr>
		</thead>
			<tr><td class="titleName">���÷�ʽ</td><td colspan="3">HTTP����</td></tr>
			<tr><td class="titleName">��������</td><td colspan="3">ע���Ա��Ϣ�����й�ע��ǰӦ��</td></tr>
			<tr><td class="titleName" rowspan="12">������</td><td>������</td><td>��������</td><td>����˵��</td></tr>
			<tr><td>extoken</td><td>String</td><td>[�����ṩ]���ʷ��������</td></tr>
			<tr><td>memberAccount</td><td>String</td><td>[�����ṩ]��Ա�˺�,��ʽΪappCode\memberAccount��memberAccount</td></tr>
			<tr><td>memberName</td><td>String</td><td>[�����ṩ]��Ա����</td></tr>
			<tr><td>qqNum</td><td>String</td><td>[��ѡ��] �󶨵�QQ��</td></tr>
			<tr><td>emailAddress</td><td>String</td><td>[��ѡ��] �󶨵ĵ�������</td></tr>
			<tr><td>wechatNum</td><td>String</td><td>[��ѡ��] �󶨵�΢�ź�</td></tr>
			<tr><td>phoneNumber</td><td>String</td><td>[��ѡ��] �󶨵��ֻ�����</td></tr>
			<tr><td>remark</td><td>String</td><td>[��ѡ��] ��ע</td></tr>
			<tr><td>memberPsw</td><td>String</td><td>[��ѡ��] ��¼���룬Ĭ��Ϊ��ǰ���ڣ�yyyMMdd��</td></tr>
			<tr><td>extendAttribute</td><td>String</td><td>[��ѡ��] json�ַ���</td></tr>
			<tr><td>domains</td><td>String</td><td>[��ѡ��] ��ע����json�ַ�����</td></tr>
			<tr><td class="titleName">����ֵ</td>
				<td colspan="3">{<br>
				"status":1,#�������ֵ��-1���ʻ��Ѿ�����<br>
				 "msg":"",    #�쳣����<br>
				 "data":   #���ؽ��<br>
				  {<br>
				    "menberId":"ע��ɹ��Ļ�ԱID",<br>
				    "appMenberId":"��Ա��עID"<br>
				  }<br>
				}<br>
				</td>
			</tr>
		</table><br>
		
		<!--  -->
		<span class="method">2.3   ��ȡָ����Ա��Ϣ</span>
		<div style="float:right;position:relative;"><span class="layui-btn btn layui-btn-small" style="margin-right:10px;margin-bottom: 3px" onclick="debug(this)" num="2">���е���</span></div>
		<table class="layui-table"  lay-skin="line" style="width:100%">
		<thead>
			<tr><td class="titleName">����·��</td><td colspan="3">/ExIAServer/services/base/member/appmember/</td></tr>
		</thead>
			<tr><td class="titleName">���÷�ʽ</td><td colspan="3">HTTP����</td></tr>
			<tr><td class="titleName">��������</td><td colspan="3">���ݻ�Ա�ʺţ���ȡ��ǰAPP����ע�Ļ�Ա��Ϣ</td></tr>
			<tr><td class="titleName" rowspan="4">������</td><td>������</td><td>��������</td><td>����˵��</td></tr>
			<tr><td>extoken</td><td>String</td><td>[�����ṩ]���ʷ��������</td></tr>
			<tr><td>idType</td><td>String</td><td>[�����ṩ] ���ID���ͣ�memberId--��ԱID��appMemberId--��Ա��עID��memberAccount--��Ա��¼�ʺš�phoneNumber--��Ա�󶨵��ֻ��š�qqNum--��Ա�󶨵�QQ���롢emailAddress--��Ա�󶨵������ַ��wechatNum--��Ա�󶨵�΢�ź�</td></tr>
			<tr><td>loginId</td><td>String</td><td>[�����ṩ] ���ID�����磺��ԱID����Ա��עID����Ա��¼�ʺš���Ա�󶨵��ֻ��š���Ա�󶨵�QQ���롢��Ա�󶨵������ַ����Ա�󶨵�΢�źš�</td></tr>
			<tr><td class="titleName">����ֵ</td>
				<td colspan="3">{<br>
				  "status":1,#�������ֵ��-2���ʻ�������,-9999:��ǰӦ����Ȩ��ȡ�û�Ա��Ϣ<br>
				 "msg":"",    #�쳣����<br>
				 "data":   #���ؽ��<br>
				  {<br>
				    "menberId":"��ԱID",<br>
				    "appMenberId":"��Ա��עID",<br>
				    "memberAccount":"��Ա�ʺ�",<br>
				    " memberName":"��Ա����",<br>
				    "qqNum":"�󶨵�QQ����",<br>
				    "emailAddress":"�󶨵������ַ",<br>
				    "wechatNum":"�󶨵�΢�ź�",<br>
				    "phoneNumber":"�󶨵��ֻ�����",<br>
				    "remark":"��ע",<br>
				"groupName":[����������],<br>
				"remark":"��չ����"<br>
				"domains":["��ע����"],<br>
				"tags":{  #�û���ǩ<br>
				  type1:[{"word":��ǩ��,"hot":�ȶ�/�����,"updateDate":�������ʱ��yyyy-MM-dd HH:mm}...],<br>
				type2:[ {"word":��ǩ��,"hot":�ȶ�/�����,"updateDate":�������ʱ��yyyy-MM-dd HH:mm}...],<br>
				��<br>
				}<br>
				  }<br>

				}<br>
				</td>
			</tr>
		</table><br>
		
		<!--  -->
		<span class="method">2.4   ��֤��Ա����</span>
		<div style="float:right;position:relative;"><span class="layui-btn btn layui-btn-small" style="margin-right:10px;margin-bottom: 3px" onclick="debug(this)" num="3">���е���</span></div>
		<table class="layui-table"  lay-skin="line" style="width:100%">
		<thead>
			<tr><td class="titleName">����·��</td><td colspan="3">/ExIAServer/services/base/member/login</td></tr>
		</thead>
			<tr><td class="titleName">���÷�ʽ</td><td colspan="3">HTTP����</td></tr>
			<tr><td class="titleName">��������</td><td colspan="3">��ָ֤����Ա��¼����</td></tr>
			<tr><td class="titleName" rowspan="4">������</td><td>������</td><td>��������</td><td>����˵��</td></tr>
			<tr><td>extoken</td><td>String</td><td>[�����ṩ]���ʷ��������</td></tr>
			<tr><td>memberAccount</td><td>String</td><td>[�����ṩ]��Ա�˺�,��ʽΪappCode\memberAccount��memberAccount</td></tr>
			<tr><td>qqNum</td><td>String</td><td>[��ѡ��] �󶨵�QQ��</td></tr>
			<tr><td>emailAddress</td><td>String</td><td>[��ѡ��] �󶨵ĵ�������</td></tr>
			<tr><td>wechatNum</td><td>String</td><td>[��ѡ��] �󶨵�΢�ź�</td></tr>
			<tr><td>phoneNumber</td><td>String</td><td>[��ѡ��] �󶨵��ֻ�����</td></tr>
			<tr><td>remark</td><td>String</td><td>[��ѡ��] ��ע</td></tr>
			<tr><td>memberPsw</td><td>String</td><td>[��ѡ��] ��¼���룬Ĭ��Ϊ��ǰ���ڣ�yyyMMdd��</td></tr>
			<tr><td>extendAttribute</td><td>String</td><td>[��ѡ��] json�ַ���</td></tr>
			<tr><td>domains</td><td>String</td><td>[��ѡ��] ��ע����json�ַ�����</td></tr>
			<tr><td class="titleName">����ֵ</td>
				<td colspan="3">{<br>
				  "status":1,#�������ֵ��-2���ʻ�������,-9999:��ǰӦ����Ȩ��ȡ�û�Ա��Ϣ -1 �������<br>
				 "msg":"",  #�쳣����<br>
				 "data":   #���ؽ��<br>
				  {<br>
				    "menberId":"��ԱID",<br>
				    "memberAccount":"��Ա�ʺ�",<br>
				    " memberName":"��Ա����"<br>
				  }<br>

				}<br>
				</td>
			</tr>
		</table><br>
		
		<!--  -->
		<span class="method">2.5 �޸Ļ�Ա��Ϣ</span>
		<div style="float:right;position:relative;"><span class="layui-btn btn layui-btn-small" style="margin-right:10px;margin-bottom: 3px" onclick="debug(this)" num="4">���е���</span></div>
		<table class="layui-table"  lay-skin="line" style="width:100%">
		<thead>
			<tr><td class="titleName">����·��</td><td colspan="3">/ExIAServer/services/base/member/update</td></tr>
		</thead>
			<tr><td class="titleName">���÷�ʽ</td><td colspan="3">HTTP����</td></tr>
			<tr><td class="titleName">��������</td><td colspan="3">ע���Ա��Ϣ�����й�ע��ǰӦ��</td></tr>
			<tr><td class="titleName" rowspan="4">������</td><td>������</td><td>��������</td><td>����˵��</td></tr>
			<tr><td>extoken</td><td>String</td><td>[�����ṩ]���ʷ��������</td></tr>
			<tr><td>memberAccount</td><td>String</td><td>[�����ṩ]��Ա�˺�</td></tr>
			<tr><td>memberPsw</td><td>String</td><td>[�����ṩ]��Ա����</td></tr>
			<tr><td class="titleName">����ֵ</td>
				<td colspan="3">{<br>
				  "status":1,#�������ֵ��-1���ʻ��Ѿ�����<br>
 				  "msg":"",    #�쳣����<br>

				}<br>
				</td>
			</tr>
		</table><br>
		
		<!--  -->
		<span class="method">2.6   �޸Ļ�Ա����</span>
		<div style="float:right;position:relative;"><span class="layui-btn btn layui-btn-small" style="margin-right:10px;margin-bottom: 3px" onclick="debug(this)" num="5">���е���</span></div>
		<table class="layui-table"  lay-skin="line" style="width:100%">
		<thead>
			<tr><td class="titleName">����·��</td><td colspan="3">/ExIAServer/services/base/member/modifyMemberPsw</td></tr>
		</thead>
			<tr><td class="titleName">���÷�ʽ</td><td colspan="3">HTTP����</td></tr>
			<tr><td class="titleName">��������</td><td colspan="3">ע���Ա��Ϣ�����й�ע��ǰӦ��</td></tr>
			<tr><td class="titleName" rowspan="4">������</td><td>������</td><td>��������</td><td>����˵��</td></tr>
			<tr><td>extoken</td><td>String</td><td>[�����ṩ]���ʷ��������</td></tr>
			<tr><td>memberAccount</td><td>String</td><td>[�����ṩ]��Ա�˺�</td></tr>
			<tr><td>memberPsw</td><td>String</td><td>[��ѡ��] ������</td></tr>
			<tr><td>newMemberPsw</td><td>String</td><td>[��ѡ��] ������</td></tr>
			<tr><td class="titleName">����ֵ</td>
				<td colspan="3">{<br>
				   "status":1, �����޸ĳɹ�!��-2��������� -9999:token��Ч<br>
				 "msg":"",    #�쳣����<br>
				"data":   #���ؽ��<br>
				  {<br>
				    "menberId":"��ԱID",<br>
				    "memberAccount":"��Ա�ʺ�",<br>
				    " memberName":"��Ա����"<br>
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