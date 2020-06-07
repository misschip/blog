package com.cos.blog.dto;


public class ResponseDto<T> {
	int status;	// 200인 경우만 성공. HTTP 응답 코드
	T data;
	
	
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	
}