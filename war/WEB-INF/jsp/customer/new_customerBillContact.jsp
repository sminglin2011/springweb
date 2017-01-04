<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<%@ include file="/WEB-INF/jsp/_meta.jsp"%>
<title></title>
</head>
<body>
	<div class="page-container">
		<form action="saveCustomerBillContact.htm" method="post" class="form form-horizontal responsive" id="form-billContact">
	<div class="row cl">
		<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>用户名：</label>
		<div class="formControls col-xs-8 col-sm-6">
			<input type="text" class="input-text" placeholder="" id="username" name="username">
		</div>
	</div>
	<div class="row cl">
		<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>初始密码：</label>
		<div class="formControls col-xs-8 col-sm-6">
			<input type="password" class="input-text" autocomplete="off" placeholder="密码" id="password" name="password">
		</div>
	</div>
	<div class="row cl">
		<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>确认密码：</label>
		<div class="formControls col-xs-8 col-sm-6">
			<input type="password" class="input-text" autocomplete="off"  placeholder="确认新密码" id="password2" name="password2">
		</div>
	</div>
	<div class="row cl">
		<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>邮箱：</label>
		<div class="formControls col-xs-8 col-sm-6">
			<input type="text" class="input-text" placeholder="@" name="email" id="email">
		</div>
	</div>
	<div class="row cl">
		<div class="col-xs-8 col-sm-6 col-xs-offset-4 col-sm-offset-3">
			<input class="btn btn-primary radius" type="submit" value="&nbsp;&nbsp;提交&nbsp;&nbsp;">
		</div>
	</div>
	</form>
	</div>
</body>
<%@ include file="/WEB-INF/jsp/_footer.jsp"%>
<script type="text/javascript" src="lib/Validform/5.3.2/Validform.js"></script>
<script type="text/javascript">
$(function(){
	//方法一;
	$("#form-billContact").Validform({
		tiptype:3,
		beforeSubmit:function(curform){
			//在验证成功后，表单提交前执行的函数，curform参数是当前表单对象。
			//这里明确return false的话表单将不会提交;	
			console.log("ddd",$(curform).serialize());
			$.ajax({
				  method: "POST",
				  url: "saveCustomerBillContact.htm?"+$(curform).serialize(),
				}).done(function( data ) {
				    //alert( "Data Saved: " + msg );
				    //$(obj).parents("tr").remove();
				    console.log(data, "111111");
				    layer.msg(data);
				}).fail(function() {
				    //alert( "error" );
					layer.msg('出错!');
				 });
			return false;
		},
	}); 
});

</script>
</html>