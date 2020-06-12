<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!-- https://www.w3schools.com/bootstrap4/bootstrap_containers.asp 에서
	My First Bootstrap Page 예제의 코드를 일부 활용함 -->

<%@ include file="../include/nav.jsp"%>


<div class="container">
	<button class="btn btn-secondary" onclick="history.back()">뒤로가기</button>
	<!-- dto 객체를 들고 수정 페이지로 이동하려고 하면 이 자리에 java 코드가 들어가야 하고 RequestDispatcher로 이동해야
		또 화면 보고 있는 순간에 글이 수정되어 있을 수도 있으므로 동기화를 위해 글의 id값을 갖고 이동하는 게 정석임!
		그쪽에서 id값으로 db에 접속하여 최신값을 조회하는 방식으로! -->
	<c:if test="${sessionScope.principal.id == detailDto.boardDto.board.userId}">	<!-- 로그인 사용자와 글 작성자가 동일한 경우 -->
		<a href="/blog/board?cmd=update&id=${detailDto.boardDto.board.id}" class="btn btn-warning">수정</a>
		<!--  아래 삭제는 GET 말고 POST 방식으로 처리해 봄 -->
		<button class="btn btn-danger" onclick="deleteById(${detailDto.boardDto.board.id})">삭제</button>
	</c:if>
	<br />
	<br />

	<h6>
		작성자 : <i>${detailDto.boardDto.username}</i> 조회수 : <i>${detailDto.boardDto.board.readCount}</i>
	</h6>
	<br />

	<h3>
		<b>${detailDto.boardDto.board.title}</b>
	</h3>

	<div class="form-group">
		<div class="container p-3 my-3 border">${detailDto.boardDto.board.content}</div>
	</div>


<hr />
	
	<!-- 댓글 박스 -->
	<div class="row bootstrap snippets">
		<div class="col-md-12">
			<div class="comment-wrapper">
				<div class="panel panel-info">
					<div class="panel-heading m-2"><b>Comment</b></div>
					<div class="panel-body">
						
						<textarea id="reply__write__form" class="form-control" placeholder="write a comment..." rows="3"></textarea>
						<br>
						<button onclick="replyWrite(${detailDto.boardDto.board.id}, ${sessionScope.principal.id})" type="button" class="btn btn-primary pull-right">댓글쓰기</button>
						<div class="clearfix"></div>
						<hr />
						<!-- 댓글 리스트 시작-->
						<ul id="reply__list" class="media-list">
						
							<c:forEach var="replyDto" items="${detailDto.replyDtos}">
							<!-- 댓글 아이템 -->
							<li class="media">
								<img onerror="this.src='/blog/images/userProfile.png'" src="${replyDto.userProfile}" alt="" class="img-circle">		
								<div class="media-body">
									<strong class="text-primary">${replyDto.username}</strong>
									<p>
										${replyDto.reply.content}
									</p>
								</div>
							</li>
							</c:forEach>
						</ul>
						<!-- 댓글 리스트 끝-->
					</div>
				</div>
			</div>

		</div>
	</div>
	<!-- 댓글 박스 끝 -->

<script>

</script>

</div>




<script src="/blog/js/reply.js"></script>
<script src="/blog/js/detail.js"></script>

<%@ include file="../include/footer.jsp"%>