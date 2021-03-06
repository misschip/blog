#### JSP 모델2 블로그 프로젝트

## 오라클 사용자 생성
``` sql
alter session set "_ORACLE_SCRIPT"=true;  
CREATE USER cos IDENTIFIED BY bitc5600;

GRANT CREATE SESSION TO cos;
GRANT CREATE TABLESPACE TO cos;
GRANT CREATE TABLE TO cos;
GRANT CREATE SEQUENCE TO cos;

alter user cos default tablespace users quota unlimited on users;
```

## 테이블
```sql
CREATE TABLE users(
	id number  primary key,
    username varchar2(100) not null unique,
    password varchar2(100) not null,
    email varchar2(100) not null,
    address varchar2(100) not null,
    userProfile varchar2(200) default '/blog/images/userProfile.png',
    userRole varchar2(20),
    createDate timestamp
) ;

CREATE TABLE board(
	id number primary key,
    userId number,
    title varchar2(100) not null,
    content clob,
    readCount number default 0,
    createDate timestamp,
    foreign key (userId) references users (id)
);

CREATE TABLE reply(
	id number  primary key,
    userId number,
    boardId number,
    content varchar2(300) not null,
    createDate timestamp,
    foreign key (userId) references users (id) on delete set null,  // users(id)가 삭제될 때 userId를 null로
    foreign key (boardId) references board (id) on delete cascade   // board(id) 글이 삭제될 때 reply 글도  삭제되도록
);
```

## 시퀀스
```sql
CREATE SEQUENCE USERS_SEQ 
    START WITH 1
    INCREMENT BY 1;
    
CREATE SEQUENCE BOARD_SEQ
    START WITH 1
    INCREMENT BY 1;
    
CREATE SEQUENCE REPLY_SEQ
    START WITH 1
    INCREMENT BY 1;
```

## 페이징 쿼리
```sql
SELECT /*+ INDEX_DESC(BOARD SYS_C007619)*/id,
userId, title, content, readCount, createDate
FROM board
OFFSET 0 ROWS FETCH NEXT 3 ROWS ONLY;
```

```sql
-- 3개만 갖고 오는 명령 (참조용)
-- Oracle
select * from board
order by id desc
offset 0 rows fetch next 3 rows only;
-- MySql
SELECT * FROM board LIMIT 3 OFFSET 0;
```


## 카카오 로그인 API 활용
[OAuth 2.0](OAuth_README.md)


## 상세보기 댓글 디자인

- css/styles.css
```css
.comment-wrapper .media-list {
	max-height: 450px;
	overflow: auto;
}
.comment-wrapper .media-list .media img {
	width: 64px;
	height: 64px;
	border: 2px solid #e5e7e8;
	border-radius: 30px;
	margin-right: 25px;
}
.comment-wrapper .media-list .media {
	border-bottom: 1px dashed #efefef;
	margin-bottom: 25px;
}
```

- include/nav.jsp
```jsp
<link href="/blog/css/styles.css" rel="stylesheet">
```

- board/detail.jsp
```jsp
<hr />
	<!-- 댓글 박스 -->
	<div class="row bootstrap snippets">
		<div class="col-md-12">
			<div class="comment-wrapper">
				<div class="panel panel-info">
					<div class="panel-heading m-2"><b>Comment</b></div>
					<div class="panel-body">
						<textarea class="form-control" placeholder="write a comment..." rows="3"></textarea>
						<br>
						<button type="button" class="btn btn-primary pull-right">댓글쓰기</button>
						<div class="clearfix"></div>
						<hr />
						<!-- 댓글 리스트 시작-->
						<ul class="media-list">
						
							<c:forEach begin="1" end="10">
							<!-- 댓글 아이템 -->
							<li class="media">	
								<img src="https://bootdey.com/img/Content/user_1.jpg" alt="" class="img-circle">		
								<div class="media-body">
									<strong class="text-primary">@MartinoMont</strong>
									<p>
										Lorem ipsum dolor sit amet, consectetur adipiscing elit. Lorem ipsum dolor sit amet.
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
```