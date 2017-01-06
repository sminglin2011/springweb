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
			<label class="form-label col-xs-6">Customer Name：${model.customer.name}</label>
			<div class="formControls col-xs-1">
				<input type="hidden" id="id" name="id" value="${model.billContact.id}">
				<input type="hidden" id="customerId" name="customerId" value="${model.customer.id}">
			</div>
		</div>
		<h4>Bill Information</h4>
		<div class="line"></div>
		<p/>
		<div class="row cl">
			<label class="form-label col-xs-3">Bill Attention：</label>
			<div class="formControls col-xs-3">
				<input type="text" class="input-text" placeholder="Bill Attention" name="billAttention" id="billAttention" 
					datatype="*3-50" ignore="ignore">
			</div>
			<label class="form-label col-xs-3">Bill Email：</label>
			<div class="formControls col-xs-3">
				<input type="text" class="input-text" placeholder="Bill Email" name="billEmail" id="billEmail" 
				datatype="e" ignore="ignore">
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-3">Bill Mobile：</label>
			<div class="formControls col-xs-3">
				<input type="text" class="input-text" placeholder="Bill Mobile" name="billMobile" id="billMobile" 
					datatype="n" ignore="ignore">
			</div>
			<label class="form-label col-xs-3">Bill Telephone：</label>
			<div class="formControls col-xs-3">
				<input type="text" class="input-text" placeholder="Bill Telephone" name="billTelephone" id="billTelephone" 
					datatype="n" ignore="ignore">
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-3">Bill PostCode：</label>
			<div class="formControls col-xs-3">
				<input type="text" class="input-text" placeholder="Bill PostCode" name="billPostcode" id="billPostcode" 
					datatype="n6-6" ignore="ignore" value="${model.billContact.billPostcode}">
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-3">Bill Address：</label>
			<div class="formControls col-xs-6">
				<input type="text" class="input-text" placeholder="Bill Address1" name="billAddress1" id="billAddress1" 
					datatype="*3-50" ignore="ignore">
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-3"></label>
			<div class="formControls col-xs-6">
				<input type="text" class="input-text" placeholder="Bill Address2" name="billAddress2" id="billAddress2">
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-3"></label>
			<div class="formControls col-xs-6">
				<input type="text" class="input-text" placeholder="Bill Address3" name="billAddress3" id="billAddress3">
			</div>
		</div>
		<div class="row cl">
			<div class="col-xs-10 col-xs-offset-2">
				<button name="" id="" class="btn btn-success" type="submit">
					<i class="Hui-iconfont">&#xe632;</i> 保存
				</button>
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
	/*$("#form-billContact").Validform({
		tiptype:3,
		beforeSubmit:function(curform){
			//在验证成功后，表单提交前执行的函数，curform参数是当前表单对象。
			
			var param = $(curform).serialize();
			console.log("ddd",JSON.stringify(param)); //serializeArray
			$.ajax({
			    url:"saveCustomerBillContact.htm",
			    type:"post",
			    dataType:"json",
			    data:JSON.stringify(param),
			    dataType:"json",
			    contentType:"application/json",
			    success:function(data){
			    	console.log(data, "succcess");
			    },error:function(data){
			    	console.log(data, "error");
			    }
			});
			//这里明确return false的话表单将不会提交;	
			return false;
		},
	}); */
	
	$("#form-billContact").Validform({
		//tiptype:3,
		ajaxPost:true,
		callback:function(data){
			console.log("log", data.status);
			/*var index = parent.layer.getFrameIndex(window.name);
			parent.location.replace(parent.location.href)
			parent.layer.close(index);*/
		}
	});
});

</script>
</html>