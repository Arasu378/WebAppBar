function validateupdatebar(){
	var barname = $.trim($("#form2\\:bar_NameUpdate").val());
	if(barname==''){
		$("#form2\\:barnameUpdate").text("Please enter bar name");
		$("#form2\\:barnameUpdate").attr("class", "ui-message-error ui-widget ui-corner-all ui-message-error-detail");
		document.getElementById("button_updateBar").disabled = true;
	}else{
		$("#form2\\:barnameUpdate").text("");
		$("#form2\\:barnameUpdate").attr("class", "");
		document.getElementById("button_updateBar").disabled = false;
	}

}
function insertbar(){
	var barname = $.trim($("#form_dialog\\:bar_Name").val());
	if(barname==''){
		$("#form_dialog\\:barnameinsert").text("Please enter bar name");
		$("#form_dialog\\:barnameinsert").attr("class", "ui-message-error ui-widget ui-corner-all ui-message-error-detail");
		document.getElementById("insertbar_new").disabled = true;
	}else{
		$("#form_dialog\\:barnameinsert").text("Please enter bar name");
		$("#form_dialog\\:barnameinsert").attr("class", "ui-message-error ui-widget ui-corner-all ui-message-error-detail");
		document.getElementById("insertbar_new").disabled = false;
	}

}