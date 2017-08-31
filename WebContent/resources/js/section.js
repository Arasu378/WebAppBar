function validateupdatesection(){
	var barname = $.trim($("#form2\\:section_NameUpdate").val());
	if(barname==''){
		$("#form2\\:sectionnameUpdate").text("Please enter Section name");
		$("#form2\\:sectionnameUpdate").attr("class", "ui-message-error ui-widget ui-corner-all ui-message-error-detail");
		document.getElementById("button_updateSection").disabled = true;
	}else{
		$("#form2\\:sectionnameUpdate").text("");
		$("#form2\\:sectionnameUpdate").attr("class", "");
		document.getElementById("button_updateSection").disabled = false;
	}

}
function insertsection(){
	var barname = $.trim($("#form_dialog\\:section_Name").val());
	if(barname==''){
		$("#form_dialog\\:sectionnameinsert").text("Please enter section name");
		$("#form_dialog\\:sectionnameinsert").attr("class", "ui-message-error ui-widget ui-corner-all ui-message-error-detail");
		document.getElementById("insertsection_new").disabled = true;
	}else{
		$("#form_dialog\\:sectionnameinsert").text("Please enter bar name");
		$("#form_dialog\\:sectionnameinsert").attr("class", "ui-message-error ui-widget ui-corner-all ui-message-error-detail");
		document.getElementById("insertsection_new").disabled = false;
	}

}