package com.cos.blog.dto;

import com.cos.blog.model.Board;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DetailResponseDto {
	private Board board;
	private String username;	// Users 객체로 해도 되지만 공간의 낭비가 되므로 상황에 따라 필요한 만큼의 정보만 넣어서 전달하도록!
}
