function replyDelete(replyId) {
		
		$.ajax({
			type: "post",
			url: "/blog/reply?cmd=deleteProc",
			data: "replyId=" + replyId,		// 받는 쪽에서 request.getParameter()로 받을 수 있도록!
			contentType: "application/x-www-form-urlencoded; charset=utf-8",
			dataType: "text"	
		}).done(function(result){

			if (result == 1) {
				alert("댓글 삭제 성공");
				var replyItem = $("#reply-" + replyId);
				replyItem.remove();
			} else {	// 0, -1
				alert("댓글 삭제 실패");
			}
			
		}).fail(function(error){
			alert("댓글 삭제 실패");
		});
	}



	function replyWrite(boardId, userId) {
		
		// if (typeof userId === "undefined") {
		if (userId === undefined) {
			alert("로그인이 필요합니다.");
			return;
		}
		
		var data = {
			boardId: boardId,
			userId: userId,
			content: $("#reply__write__form").val()	// textarea에 입력된 문자열을 가져옴. textarea 태그만 텍스트 부분이 val()로 가져옴
		};

		$.ajax({
			type: "post",
			url: "/blog/reply?cmd=writeProc",
			data: JSON.stringify(data),
			contentType: "application/json; charset=utf-8",
			dataType: "json"	
		}).done(function(result){
			// 정상 응답
			// 1. reply__list를 찾아서 내부를 비우기	// jQuery 함수가 있음
			// alert(result);
			if (result == -1 || result == 0) {
				alert("댓글 작성 실패");
			} else {
				alert("댓글 작성 성공");
				$("#reply__list").empty();
				console.log(result);
				renderReplyList(result, userId);
				$("#reply__write__form").val("");
			}
			
		}).fail(function(error){
			alert("댓글 작성 실패");
		});
	}	
			// 2. ajax 재호출 findall()
			// 3. reply__list를 찾아서 내부에 채워주기
			
		// 아래 함수가 받는 매개값 replyDtos는 자바 객체가 아니라 json 데이터임에 유의
		function renderReplyList(replyDtos, userId){
			for(var replyDto of replyDtos){
				$("#reply__list").append(makeReplyItem(replyDto, userId));
			}
		}
			
			
		function makeReplyItem(replyDto, userId){
			console.log(replyDto);
			
			// reply-id 추가 시작
			var replyItem = `<li id="reply-${replyDto.reply.id}" class="media">`;
			// reply-id 추가 끝
			
			if(replyDto.userProfile == null){
				replyItem += `<img src="/blog/images/userProfile.png" class="img-circle">`;	
			}else{
				replyItem += `<img src="${replyDto.userProfile}" class="img-circle">`;
			}

			replyItem += `<div class="media-body">`;
			replyItem += `<strong class="text-primary">${replyDto.username}</strong>`;
			replyItem += `<p>${replyDto.reply.content}</p>`;
			replyItem += `</div>`;
			
			
			if (replyDto.reply.userId == userId) {
				replyItem += `<div class="m-3">`;
				replyItem += `<i onclick="replyDelete(${replyDto.reply.id})" style="font-size:30px; cursor: pointer; color:dark-gray" class="material-icons">delete</i>`;
				replyItem += `</div>`;
			}
			
			replyItem += `</li>`;
			return replyItem;
		}	
			

		

