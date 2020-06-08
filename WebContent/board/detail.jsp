<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!-- https://www.w3schools.com/bootstrap4/bootstrap_containers.asp 에서
	My First Bootstrap Page 예제의 코드를 일부 활용함 -->

<%@ include file="../include/nav.jsp"%>


<div class="container">
	<button class="btn btn-secondary" onclick="history.back()">뒤로가기</button>
	<!-- dto 객체를 들고 수정 페이지로 이동하려고 하면 이 자리에 java 코드가 들어가야 하고 RequestDispatcher로 이동해야
		또 화면 보고 있는 순간에 글이 수정되어 있을 수도 있으므로 동기화를 위해 글의 id값을 갖고 이동하는 게 정석임! -->
	<c:if test="${sessionScope.principal.id == dto.board.userId}">
		<a href="/blog/board?cmd=update&id=${dto.board.id}" class="btn btn-warning">수정</a>
		<!--  아래 삭제는 GET 말고 POST 방식으로 처리해 봄 -->
		<button class="btn btn-danger" onclick="deleteById(${dto.board.id})">삭제</button>
	</c:if>
	<br />
	<br />

	<h6>
		작성자 : <i>${dto.username}</i>
	</h6>
	<br />

	<h3>
		<b>${dto.board.title}</b>
	</h3>

	<div class="form-group">
		<div class="container p-3 my-3 border">${dto.board.content}</div>
	</div>

</div>


<script>
	function deleteById(boardId){
		$.ajax({
			type: "POST",
			url: "/blog/board?cmd=delete&id="+boardId,	// 결국 이렇게 되면 무늬만 POST 방식이지 실질적으로는 GET 방식인 듯!
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
</script>

<%@ include file="../include/footer.jsp"%>