function validateEmail()
{
var emailReg = /^([\w-\.]+@([\w-]+\.)+[\w-]{2,4})?$/;
var email = $.trim($("#loginForm\\:email").val());
if(email ==''){
$("#loginForm\\:useremailMsg").text("Please enter email");
$("#loginForm\\:useremailMsg").attr("class", "ui-message-error ui-widget ui-corner-all ui-message-error-detail");
document.getElementById("register_button").disabled = true;

return;
}
if( emailReg.test( email ) ) {
//$("#registrationForm\\:useremailMsg").text("Valid Email");
//$("#registrationForm\\:useremailMsg").attr("class", "ui-messages-info ui-widget ui-corner-all ui-messages-info-summary");
	$("#loginForm\\:useremailMsg").text("");
	$("#loginForm\\:useremailMsg").attr("class", "");
	
	document.getElementById("register_button").disabled = false;

} else {
$("#loginForm\\:useremailMsg").text("Invalid Email");
$("#loginForm\\:useremailMsg").attr("class", "ui-message-error ui-widget ui-corner-all ui-message-error-detail");
document.getElementById("register_button").disabled = true;

}
}
function validatepassword(){
	var pass=$.trim($("#loginForm\\:password").val());
	if(pass==''){
		$("#loginForm\\:userpasswordMsg").text("please enter password");
		$("#loginForm\\:userpasswordMsg").attr("class", "ui-message-error ui-widget ui-corner-all ui-message-error-detail");
		document.getElementById("register_button").disabled = true;

	}else{
		$("#loginForm\\:userpasswordMsg").text("");
		$("#loginForm\\:userpasswordMsg").attr("class", "");
		document.getElementById("register_button").disabled = false;


	}
}
