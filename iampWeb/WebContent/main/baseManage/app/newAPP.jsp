<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>�½�APP</title>
<script type="text/javascript" src="/iamp/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="/iamp/js/layui-v2/layui.js"></script>
<link rel="stylesheet" href="/iamp/js/layui-v2/css/layui.css" media="all" />

<style>
    body{margin: 30px;}
</style>
</head>
<body>
	<form class="layui-form" action="">
	  <div class="layui-form-item">
	    <label class="layui-form-label"><font style="color: #FF5722">*</font>Ӧ�����ƣ�</label>
	    <div class="layui-input-inline" style="width: 300px">
	      <input type="text" name="title" required  lay-verify="required" placeholder="" autocomplete="off" class="layui-input">
	    </div>
	    <label class="layui-form-label"><font style="color: #FF5722">*</font>Ӧ�ô��룺</label>
	    <div class="layui-input-inline" style="width: 300px">
	      <input type="text" name="title" required  lay-verify="required" placeholder="" autocomplete="off" class="layui-input">
	    </div>
	  </div>
	  <div class="layui-form-item">
	    <label class="layui-form-label"><font style="color: #FF5722">*</font>Ӧ�����룺</label>
	    <div class="layui-input-inline" style="width: 300px">
	      <input type="password" name="password" required lay-verify="required" placeholder="����������" autocomplete="off" class="layui-input">
	    </div>
	    <label class="layui-form-label">����ʱ�䣺</label>
	   <div class="layui-input-inline" style="width: 300px">
	      <select name="time" lay-verify="required">
	        <option value=""></option>
	        <option value="0">6Сʱ</option>
	        <option value="1">һ��</option>
	        <option value="2">����</option>
	        <option value="3">����</option>
	        <option value="4">30��</option>
	      </select>
	    </div>
	  </div>
	  <div class="layui-form-item">
	    <label class="layui-form-label">ʹ�õ�λ��</label>
	    <div class="layui-input-inline" style="width: 300px">
	      <input type="text" name="title" placeholder="" autocomplete="off" class="layui-input">
	    </div>
	    <label class="layui-form-label">token�ַ���</label>
	    <div class="layui-input-inline" style="width: 300px">
	      <label class="layui-form-label" style="text-align:left;min-width:100px">������︴��</label>
	    </div>
	  </div>
	  
	  <div class="layui-form-item layui-form-text">
	    <label class="layui-form-label">����Ip��</label>
	    <div class="layui-input-block"  style="width: 720px">
	      <textarea name="desc" placeholder="��;�ָ�" class="layui-textarea"></textarea>
	    </div>
	  </div>
	  <div class="layui-form-item layui-form-text">
	    <label class="layui-form-label">��ע��</label>
	    <div class="layui-input-block" style="width: 720px">
	      <textarea name="desc" placeholder="��;�ָ�" class="layui-textarea"></textarea>
	    </div>
	  </div>
	</form>
<script>
//Demo
layui.use('form', function(){
  var form = layui.form();
  
  /* //�����ύ
  form.on('submit(formDemo)', function(data){
    layer.msg(JSON.stringify(data.field));
    return false;
  }); */
});
</script>
</body>
</html>