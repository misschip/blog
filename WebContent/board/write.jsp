<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!-- include summernote css/js 시작 -->

<!-- include summernote css/js 끝 -->

<%@ include file="../include/nav.jsp"%>



<%@ include file="../include/authentication.jsp"%>



<!--  아래 코드 출처는 https://www.w3schools.com/bootstrap4/bootstrap_forms.asp
		Bootstrap 4 Stacked Form
	그리고 https://www.w3schools.com/bootstrap4/bootstrap_forms_inputs.asp
		Bootstrap Textarea
		
		summernote 부분 코드 출처는 https://summernote.org/getting-started/#installation
		-->

<div class="container">
	<form action="/blog/board?cmd=writeProc" method="POST">

		<div class="form-group">
			<label for="title">Title:</label> <input type="text" class="form-control" placeholder="title" id="title" name="title" required>
		</div>

		<div class="form-group">
			<label for="content">Content:</label>
			<textarea class="form-control" rows="5" name="content" id="summernote" required></textarea>
		</div>

		<button type="submit" class="btn btn-primary">글쓰기 등록</button>
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