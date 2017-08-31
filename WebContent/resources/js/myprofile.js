function validateEmail()
{
var emailReg = /^([\w-\.]+@([\w-]+\.)+[\w-]{2,4})?$/;
var email = $.trim($("#loginForm\\:email").val());
if(email ==''){
$("#profileForm\\:useremailMsg").text("Please enter email");
$("#profileForm\\:useremailMsg").attr("class", "ui-message-error ui-widget ui-corner-all ui-message-error-detail");
document.getElementById("update_button").disabled = true;

return;
}
if( emailReg.test( email ) ) {
//$("#registrationForm\\:useremailMsg").text("Valid Email");
//$("#registrationForm\\:useremailMsg").attr("class", "ui-messages-info ui-widget ui-corner-all ui-messages-info-summary");
	$("#profileForm\\:useremailMsg").text("");
	$("#profileForm\\:useremailMsg").attr("class", "");
	
	document.getElementById("update_button").disabled = false;

} else {
$("#profileForm\\:useremailMsg").text("Invalid Email");
$("#profileForm\\:useremailMsg").attr("class", "ui-message-error ui-widget ui-corner-all ui-message-error-detail");
document.getElementById("update_button").disabled = true;

}
}
