$(document).ready(function() {
	 bindData();
});

function bindData() {
	$('[data-log_btn_message]').each(function() {
		$(this).off("click");
		$(this).on("click", handleData);
	});
}
function handleData(e) {
	var caller = e.target;
	var id = $(caller).attr('data-log_btn_message');

	var postData = JSON.stringify({
		"id": id
	});
	ajaxMethodCall(postData, "/ajax/getLog/", getLog);
}
function getLog(data) {
	console.log(data);
	$("[data-log_message="+data.id+"]").val(data.message);
	$("[data-log_exception="+data.id+"]").val(data.exception);
	$("[data-log_data_detail="+data.id+"]").val(data.data_detail);
}
function ajaxMethodCall(postData, ajaxUrl, successFunction) {

	$.ajax({
		type : "POST",
		url : ajaxUrl,
		data : postData,
		success : successFunction,
		error : function(jqXHR, exception) {
			if (jqXHR.status === 0) {
				console.error('Not connect.\n Verify Network.');
			} else if (jqXHR.status == 404) {
				console.error('Requested page not found. [404]');
			} else if (jqXHR.status == 500) {
				console.error('Internal Server Error [500].');
			} else if (exception === 'parsererror') {
				console.error('Requested JSON parse failed.');
			} else if (exception === 'timeout') {
				console.error('Time out error.');
			} else if (exception === 'abort') {
				console.error('Ajax request aborted.');
			} else {
				console.error('Uncaught Error.\n' + jqXHR.responseText);
			}
		},
		contentType : "application/json",
		dataType : "json"
	});
}