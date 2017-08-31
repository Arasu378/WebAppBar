function validateEmail()
{
var emailReg = /^([\w-\.]+@([\w-]+\.)+[\w-]{2,4})?$/;
var email = $.trim($("#loginForm\\:email").val());
if(email ==''){
$("#registrationForm\\:useremailMsg").text("Please enter email");
$("#registrationForm\\:useremailMsg").attr("class", "ui-message-error ui-widget ui-corner-all ui-message-error-detail");
document.getElementById("register_button").disabled = true;

return;
}
if( emailReg.test( email ) ) {
//$("#registrationForm\\:useremailMsg").text("Valid Email");
//$("#registrationForm\\:useremailMsg").attr("class", "ui-messages-info ui-widget ui-corner-all ui-messages-info-summary");
	$("#registrationForm\\:useremailMsg").text("");
	$("#registrationForm\\:useremailMsg").attr("class", "");
	
	document.getElementById("register_button").disabled = false;

} else {
$("#registrationForm\\:useremailMsg").text("Invalid Email");
$("#registrationForm\\:useremailMsg").attr("class", "ui-message-error ui-widget ui-corner-all ui-message-error-detail");
document.getElementById("register_button").disabled = true;

}
}
