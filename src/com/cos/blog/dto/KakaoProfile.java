package com.cos.blog.dto;

import lombok.Data;

/**
 * https://developers.kakao.com/docs/latest/ko/user-mgmt/rest-api
 * 사용자 관리 - REST API - 사용자 정보 요청 - Request - URL:Access Token 사용으로
 * 카카오 서버가 보내오는 JSON 데이터를 Json Parser Online에서 파싱해 보면
 * 대략 아래 KakaoProfile 클래스의 구조가 같게 된다.
 * 실제 보내오는 데이터의 key-value쌍은 이보다 더 많은데 실제로 사용할 것들만 추려 만든 게 아래임
 *
 */

@Data
public class KakaoProfile {
	private String id;
	private KakaoAccount kakao_account;

	@Data
	public class KakaoAccount {
		private Profile profile;
		private String email;
		private boolean has_email;

		@Data
		public class Profile {
			private String nickname;
			private String profile_image_url;

		}
	}

}