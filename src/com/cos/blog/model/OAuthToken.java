package com.cos.blog.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * https://developers.kakao.com/docs/latest/ko/kakaologin/rest-api
 * REST API - 사용자 토큰 받기 - Response - Key
 * 아래 OAuthToken 클래스의 필드는 위 링크의 Response - Key의 테이블에 나오는 것을 그대로 가져온 것!
 * 이 필드들에 관한 좀 더 일반적인 원리에 대해서는 아래 링크 참조
 * https://www.oauth.com/oauth2-servers/access-tokens/access-token-response/
 * 
 * 카카오서버로부터 아래 6개 값을 JSON 타입으로 받아오면 Gson을 이용하여 OAuthToken 객체를 생성하게 된다.
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OAuthToken {
	private String access_token;
	private String token_type;	// 토큰 타입은 "bearer"로 고정
	private String refresh_token;	// access_token에 만료시간이 있는 경우 refresh_token으로 새로운 access_token을 발급받을 수 있다.
	private int expires_in;		// 만료시간 (초)
	private String scope;		// 인증된 사용자의 정보 조회 권한 범위(email, profile 등 여러개일 경우 공백으로 구분)
	private int refresh_token_expires_in;

}