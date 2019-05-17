/**
 * 点击电话，用户去登陆的js
 * @param o
 */
function btnClickContact(o,lineId,carrierId,type){
	//saveClickPhone(lineId,carrierId,type);
	var mattr = $(o).attr("myattr");
	$.ajax({
		url:basePath+"road.php?action=clickphone&ajax=1&itemid="+lineId+"&carrierid="+carrierId+"&type_id="+type,
		type:"post",
		dataType:'text',
		success:function(data){
			if(data==1){
				loginClick();
			}else{
				$('.bubble-box').hide();
				if(data!=''){
					$(o).parent().next().children('.wrap,.wraps').children('.tel').html(data);
				}else{
					$(o).parent().next().children('.wrap,.wraps').children('.tel').text('400 884 6800');
				}
				$(o).parent().next().show();
			}
	}
	});
}

function login(){
	var username=$('#username').val();
	var password=$('#password1').val();
	if(username=='' || username=="请输入用户名/手机号/邮箱"){
		$('#tip_username').text('请输入用户名/手机号/邮箱').closest("tr").show();
		return false;
	}else{
		$('#tip_username').text('').parent("tr").hide();
	}
	if(password=='' || password=='请输入密码'){
		$('#tip_password').text('请输入密码').closest("tr").show();
		return false;
	}else{
		$('#tip_password').text('').closest("tr").hide();
	}
	$.ajax({
		url:basePath+"member/login.php?action=ajax_login&ajax=1",
		type:"post",
		data:'username='+username+'&password='+password,
		dataType:'json',
		success:function(data){
			if(data.status==1){
				close_loginRegister();
				$('.welcome_text').html('<p class="welcome_text"><span><a rel="nofollow" target="_blank" href="'+basePath+'member">'+username+'</a></span>，您好！<a rel="nofollow" href="'+basePath+'member/logout.php">退出</a> </p>');
				location.reload();
			}else{
				$('#tip_password').text(data.info).closest("tr").show();
			}
	}
	});
	return true;
}

function registerClick(){
	close_loginRegister();
	$("#user_session_login").css("display","block");
	$("#titleDivId").empty();
	$("#titleDivId").append("<a href='javascript:;' class='shut' onclick='close_loginRegister();'></a>用户注册");
	$("#inquiryId").hide();
	$("#pop_small_Table_table_id").hide();
	$("#registerId").show();
}

//关闭登陆框
function close_loginRegister(){
	
	$("#user_session_login").css("display","none");
	$(".pop_container").find(".inp_grey").val("");
	$("#pop_small_Table_table_id").find(".inp_grey").val("");
	$("#tip_username").html("");
	$("#tip_password").html("");
	$(".red").hide();
	$("#inquiryId").hide();
	$("#registerId").hide();
	$("#pop_small_Table_table_id").hide();
	$('#sendBtnId').attr('disabled',true);
	$('#sendBtnId').attr('class','code_get_grey');
	$("#tip_phone").closest("tr").hide();
	$("#tip_username").closest("tr").hide();
	$("#tip_password").closest("tr").hide();
	
	$("input[name='username']").val("请输入用户名/手机号/邮箱");
	$("input[name='password']").val("请输入密码");
	$("input[name='passWord1']").val("请输入密码");
	$("input[name='userPhone']").val("请输入手机号");
	$("input[name='verification']").val("请输入验证码");
	
	$("input[myattr=\"inp_grey\"]").attr("style","font-size:13px; color:#b6b6b6");
	$("input[name=\"password\"]").show();
	$("input[name=\"password\"]").next().hide();
	
}

function loginClick(){
	close_loginRegister();
	$("#user_session_login").css("display","block");
	$("#titleDivId").empty();
	$("#titleDivId").append("<a href='javascript:;' class='shut' onclick='close_loginRegister();'></a>用户登录");
	$("#inquiryId").hide();
	$("#registerId").hide();
	$("#pop_small_Table_table_id").show();
}

//发送短信按钮
function sendMessage(obj) {
	var newPhone = $("#newPhone").val();
	if (StringUtil.isNull(newPhone)) {
		$("#tip_phone").closest("tr").show();
		$("#tip_phone").html(validate.config.img_tip+validate.phone.tip);
		return false;
	}
	var verificationText =  $("#tip_phone").text();
	if(verificationText == "此号码已经注册"){
		return false;
	}
	var objPhone=$("#newPhone");
	if(check_phone(objPhone[0])==true){
		$.ajax({
			type: "POST", 
			url: basePath+"deliver/ajax.php",
			dataType : "json",
			data: 'action=getVerifyCode&mobile='+newPhone,
			success: function( data ) {
				$(obj).attr("disabled",true);
				alert(data.msg);
				$(obj).attr('class','code_get_grey');
				time(obj,60);
			}
		});
	}
}


//计时
function time(o,wait) {
	if (wait == 0) {
		$(o).attr("disabled",false);
		$(o).attr('class','code_get_q');
		$(o).val("获取验证码");
		wait = 60;
	} else {
		$(o).attr("disabled",true);
		$(o).attr('class','code_get_grey');
		$(o).val(wait +"秒后重新获取");
		wait--;
		setTimeout(function() {time(o,wait);}, 1000);
	}
}

function check_verificationCode(obj){
	if(obj.value==""){
		$("#tip_verificationCode").closest("tr").show();
		$("#tip_verificationCode").show();
		$("#tip_verificationCode").html(validate.config.img_tip+"不能为空");
		return false;
		}
	else if(obj.value=="请输入验证码"){
		$("#tip_verificationCode").closest("tr").show();
		$("#tip_verificationCode").show();
		$("#tip_verificationCode").html(validate.config.img_tip+"请输入验证码");
		return false;
		}
	else{
		$(obj).attr("style","color:#000");
		$("#tip_verificationCode").closest("tr").hide();
		$("#tip_verificationCode").html("");
		return true;
		}
	}
function check_phone(obj){
	if(obj.value==""){
		$("#tip_phone").closest("tr").show();
		$("#tip_phone").show();
		$("#tip_phone").html(validate.config.img_tip+"不能为空");
		$('#sendBtnId').attr('disabled',true);
		$('#sendBtnId').attr('class','code_get_grey');
		return false;
		}
	else if(obj.value=="请输入手机号"){
		$("#tip_phone").closest("tr").show();
		$("#tip_phone").show();
		$("#tip_phone").html(validate.config.img_tip+"请输入手机号");
		$('#sendBtnId').attr('disabled',true);
		$('#sendBtnId').attr('class','code_get_grey');
		return false;
		}
	else if(!validate.phone.reg.test(obj.value)){
		$("#tip_phone").closest("tr").show();
		$("#tip_phone").show();
		$("#tip_phone").html(validate.config.img_tip+validate.phone.tip);
		$('#sendBtnId').attr('disabled',true);
		$('#sendBtnId').attr('class','code_get_grey');
		return false;
		}
	else{
		var is_ture=false;
		$.ajax({
			type: "POST", 
			url: basePath+"member/ajax.php",
			dataType : "json",
			async: false,
			data: { action : 'ckUsername', value : obj.value },
			success: function( data ) {
				if( data && data.flag ){
					is_ture=true;
				}
			}
		});
		if(is_ture==true){
			$(obj).attr("style","color:#000");
			$("#tip_phone").text('').closest("tr").hide();
			$("#tip_phone").html("");
			$('#sendBtnId').attr('disabled',false);
			$('#sendBtnId').attr('class','code_get_q');
			return true;
		}else{
			$("#tip_phone").text('此号码已经注册').closest("tr").show();
			return false;
		}
		return true;
	}
}

function registerUser(obj){
	var newPhone = $("#newPhone").val();
	var verification = $("#verification").val();
	if(!check_phone($("#newPhone")[0])&!check_verificationCode($("#verification")[0])){
		return false;
	}
	
	$(obj).attr("onclick","");
	$.ajax({
		type : "post",
		url : basePath+"member/register.php?action=ajax_register&reg=1",
		data : 'username='+newPhone+'&code='+verification,
		dataType : "json",
		success : function(data) {
			$(obj).attr("onClick","registerUser(this)");
			if (data.status ==1) {
				close_loginRegister();
				$('.welcome_text').html('<p class="welcome_text"><span><a rel="nofollow" target="_blank" href="'+basePath+'member">'+newPhone+'</a></span>，您好！<a rel="nofollow" href="'+basePath+'member/logout.php">退出</a> </p>');
				location.reload();
			} else if (data.status<0) {
				$("#tip_verificationCode").text(data.info).closest("tr").show();
			}else{
				alert("注册出现异常，请联系相关技术人员");
			}
		}
	});
}

function importPass(o){
	$(o).hide();
	$(o).next('input').show();
	$(o).next('input').val("");
	$(o).next('input').focus();
	$(o).next('input').attr("style","color:#000");
}

function clearDefaultText (el,message){
	var obj = el;
	if(typeof(el) == "string")
	obj = document.getElementByIdx_x(id);
	if(obj.value == message)
		{
		obj.value = "";
		$(obj).attr("style","color:#000");
		}
	obj.onblur = function()
	{
		if(obj.value == "")
		{  
		   obj.value = message;
		   $(obj).attr("style","font-size:13px; color:#b6b6b6");
		}
	}
}

function clearDefaultPass(el,message){
	var obj = el;
	if(obj.value == ""){  
	   $(obj).hide();
	   $(obj).val("请输入密码");
	   $(obj).prev('input').show();
	}
}

$(".show-tel").click(function(e){
	$(".show-tel").siblings().hide();
	$(this).siblings('.bubble-box').show();
});
$(".tel-box-close").click(function(e){
	$(this).parents(".bubble-box").hide();
});