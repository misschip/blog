package com.cos.blog.dto;

import com.cos.blog.model.Reply;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReplyResponseDto {
	private Reply reply;	// Reply 테이블
	private String username;	// Users 테이블
	private String userProfile;	// Users 테이블
	
}
