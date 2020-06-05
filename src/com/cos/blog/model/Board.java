package com.cos.blog.model;

import java.sql.Timestamp;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Board {
	private int id;
	private int userId;
	private String title;
	private String content;
	private int readCount;
	private Timestamp createDate;
	
	
	public String getTitle() {
		// 자바스크립트를 이용한 XSS 공격을 방지하기 위한 장치임
		// content 부분은 summernote가 자체적으로 처리해 주고 있으므로 title 부분만
		// 꺼내올 때 <, >를 바꾸어 주는 것!
		// 실전에서는 XSS 공격을 막기 위한 라이브러리를 적용해 주는 게 편리함!
		// title = title.replaceAll("<", "&lt;").replaceAll(">", "&gt;");
		return title;
	}
}
