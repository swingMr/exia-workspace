<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>新建APP</title>
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
	    <label class="layui-form-label"><font style="color: #FF5722">*</font>应用名称：</label>
	    <div class="layui-input-inline" style="width: 300px">
	      <input type="text" name="title" required  lay-verify="required" placeholder="" autocomplete="off" class="layui-input">
	    </div>
	    <label class="layui-form-label"><font style="color: #FF5722">*</font>应用代码：</label>
	    <div class="layui-input-inline" style="width: 300px">
	      <input type="text" name="title" required  lay-verify="required" placeholder="" autocomplete="off" class="layui-input">
	    </div>
	  </div>
	  <div class="layui-form-item">
	    <label class="layui-form-label"><font style="color: #FF5722">*</font>应用密码：</label>
	    <div class="layui-input-inline" style="width: 300px">
	      <input type="password" name="password" required lay-verify="required" placeholder="请输入密码" autocomplete="off" class="layui-input">
	    </div>
	    <label class="layui-form-label">过期时间：</label>
	   <div class="layui-input-inline" style="width: 300px">
	      <select name="time" lay-verify="required">
	        <option value=""></option>
	        <option value="0">6小时</option>
	        <option value="1">一天</option>
	        <option value="2">两天</option>
	        <option value="3">七天</option>
	        <option value="4">30天</option>
	      </select>
	    </div>
	  </div>
	  <div class="layui-form-item">
	    <label class="layui-form-label">使用单位：</label>
	    <div class="layui-input-inline" style="width: 300px">
	      <input type="text" name="title" placeholder="" autocomplete="off" class="layui-input">
	    </div>
	    <label class="layui-form-label">token字符：</label>
	    <div class="layui-input-inline" style="width: 300px">
	      <label class="layui-form-label" style="text-align:left;min-width:100px">点击这里复制</label>
	    </div>
	  </div>
	  
	  <div class="layui-form-item layui-form-text">
	    <label class="layui-form-label">限制Ip：</label>
	    <div class="layui-input-block"  style="width: 720px">
	      <textarea name="desc" placeholder="以;分割" class="layui-textarea"></textarea>
	    </div>
	  </div>
	  <div class="layui-form-item layui-form-text">
	    <label class="layui-form-label">备注：</label>
	    <div class="layui-input-block" style="width: 720px">
	      <textarea name="desc" placeholder="以;分割" class="layui-textarea"></textarea>
	    </div>
	  </div>
	</form>
<script>
//Demo
layui.use('form', function(){
  var form = layui.form();
  
  /* //监听提交
  form.on('submit(formDemo)', function(data){
    layer.msg(JSON.stringify(data.field));
    return false;
  }); */
});
</script>
</body>
</html>