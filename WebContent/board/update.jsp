<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../include/nav.jsp"%>


<%@ include file="../include/authentication.jsp"%>

<!-- id를 넘기는 두가지 방법
	1. form action에 id값으로 넘기는 방법
	2. hidden 필드를 통해 넘기기 (아래에서 id값을 이 방식으로 넘긴다)
-->

<div class="container">
	<form action="/blog/board?cmd=updateProc" method="POST">

		<input type="hidden" value="${dto.board.id}" name="id"/>	
			
		<div class="form-group">
			<label for="title">Title:</label>
			<input value="${dto.board.title}" type="text" class="form-control" placeholder="title" id="title" name="title" required>
		</div>

		<div class="form-group">
			<label for="content">Content:</label>
			<textarea class="form-control" rows="5" name="content" id="summernote" required>
				${dto.board.content}
			</textarea>
		</div>

		<button type="submit" class="btn btn-primary">수정하기</button>
	</form>
</div>


<script>
$(document).ready(function() {
    $('#summernote').summernote({
    	tabsize : 2,
		height : 300
    });
});
</script>



<%@ include file="../include/footer.jsp"%>