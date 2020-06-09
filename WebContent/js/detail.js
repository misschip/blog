function deleteById(boardId){

	$.ajax({
		type: "POST",
		url: `/blog/board?cmd=delete&id=${boardId}`,	// 결국 이렇게 되면 무늬만 POST 방식이지 실질적으로는 GET 방식인 듯!
		dataType: "text"
	}).done(function(result){
		console.log(result);
		if(result == 1){
			alert("삭제 성공");
			location.href="/blog/index.jsp";
		}else{
			alert("삭제 실패");
		}
	}).fail(function(error){
		console.log(error);
		console.log(error.responseText);
		console.log(error.status);
		alert("서버 오류");
	});
}