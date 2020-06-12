	function replyWrite(boardId, userId) {
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
				renderReplyList(result);
				$("#reply__write__form").val("");
			}
			
		}).fail(function(error){
			alert("댓글 작성 실패");
		});
	}	
			// 2. ajax 재호출 findall()
			// 3. reply__list를 찾아서 내부에 채워주기
			
			
		function renderReplyList(replyDtos){
			for(var replyDto of replyDtos){
				$("#reply__list").append(makeReplyItem(replyDto));
			}
		}
			
			
		function makeReplyItem(replyDto){
			var replyItem = `<li class="media">`;
			if(replyDto.userProfile == null){
				replyItem += `<img src="/blog/images/userProfile.png" class="img-circle">`;	
			}else{
				replyItem += `<img src="${replyDto.userProfile}" class="img-circle">`;
			}

			replyItem += `<div class="media-body">`;
			replyItem += `<strong class="text-primary">${replyDto.username}</strong>`;
			replyItem += `<p>${replyDto.reply.content}</p>`;
			replyItem += `</div>`;
			replyItem += `</li>`;
			return replyItem;
		}	
			

		

