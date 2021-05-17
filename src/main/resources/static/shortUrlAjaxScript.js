$(document).ready(function() {
	$("button").click(function() {
		$.ajax({
			type : 'POST',
			url : 'http://localhost:8080/shortenUrl',
			data : $("#fullUrl").val(),
			contentType : "application/json; charset=utf-8",
			success : function(data) {
				$("#shortUrl").text(data);
			}
		});
	});
});