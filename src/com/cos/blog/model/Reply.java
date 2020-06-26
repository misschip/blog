package com.cos.blog.model;

import java.sql.Timestamp;

import com.nhncorp.lucy.security.xss.XssFilter;
import com.nhncorp.lucy.security.xss.XssPreventer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Reply {
	private int id;
	private int userId;
	private int boardId;
	private String content;
	private Timestamp createDate;
	
	// 스크립트 공격 방지 위해서 스크립트 관련 부분들을 걸러냄
	public String getContent(){
		return XssPreventer.escape(content);
	}
}
