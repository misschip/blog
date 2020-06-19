package com.cos.blog.action.kakao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cos.blog.action.Action;
import com.cos.blog.dto.KakaoProfile;
import com.cos.blog.model.OAuthToken;
import com.cos.blog.model.Users;
import com.cos.blog.repository.UsersRepository;
import com.cos.blog.util.Script;
import com.google.gson.Gson;

public class KakaoCallbackAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		////////////////////////////////////////////////////////////////////////
		//////////////// [1] 카카오 로그인 /////////////////////////////////////////
		////////////////   인증코드 받기 및 사용자 토큰 받기 /////////////////////////////
		//// https://developers.kakao.com/docs/latest/ko/kakaologin/rest-api ///
		////////////////////////////////////////////////////////////////////////
		
		
		// 아래 code, grant_type, client_id, redirect_uri 4개 변수에 대해서는 아래 링크
		// https://developers.kakao.com/docs/latest/ko/kakaologin/rest-api
		// REST API - 사용자 토큰 받기 - Request - Parameter 테이블 참조할 것!
		String code = request.getParameter("code");	// 카카오 로그인 클릭(인증코드 받기 요청)으로 얻은 인증 코드
		// System.out.println("카카오 인증 완료 : 코드값 ");
		// System.out.println(code);
		String grant_type = "authorization_code";	// 고정값
		String client_id = "fd8427c16b609d2587da1dd88736a545";	// 앱 생성시 발급받은 REST API 키
		String redirect_uri = "http://localhost:8000/blog/oauth/kakao?cmd=callback";
		
		Script.outText(code, response);	//
		
		// POST 요청, Content-type: application/x-www-form-urlencoded;charset=utf-8
		// 아래 endpoint 주소에 관해서는 링크 참조
		// https://developers.kakao.com/docs/latest/ko/kakaologin/rest-api
		// REST API - 사용자 토큰받기 - Request - URL
		String endpoint = "https://kauth.kakao.com/oauth/token";
		// 맨 위 4개 변수는 위 Request 요청시에 필요한 파라메터임!
		
		URL url = new URL(endpoint);
		
		String bodyData = "";	// POST 요청시 보낼 body 문자열
		bodyData += "grant_type=authorization_code&";
		bodyData += "client_id=fd8427c16b609d2587da1dd88736a545&";
		bodyData += "redirect_uri=http://localhost:8000/blog/oauth/kakao?cmd=callback&";
		bodyData += "code="+code;
		

		// Stream 연결
		HttpsURLConnection conn = (HttpsURLConnection)url.openConnection();
		// http header 값 넣기
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
		conn.setDoOutput(true);
		
		// request 하기
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream(), "UTF-8"));
		bw.write(bodyData);
		bw.flush();
		
		
		BufferedReader br = new BufferedReader(
				new InputStreamReader(conn.getInputStream(), "UTF-8"));
		String input = "";
		StringBuilder sb = new StringBuilder();
		while((input = br.readLine()) != null) {
			sb.append(input);
		}
		System.out.println("sb.toString() : " + sb.toString());
		// 바로 위에서 카카오 인증 서버로부터 보내져온 데이터는 JSON 타입이고 다음의 정보를 포함하고 있다.
		// {
		// "access_token" : "eZ-B2Ixxxxx 자체 블라인드 처리 xxxxx",	// 제일 중요한 정보
		// "token_type":"bearer",		// 고정값
		// "refresh_token":"_XfWOWCkxxxx 자체 블라인드 처리 xxxxx",
		// "expires_in":21599,		// 초단위(대략 6시간)
		// "scope":"account_email profile",
		// "refresh_token_expires_in":5183999	// 초단위
		// }
		
		// Gson으로 파싱
		Gson gson = new Gson();
		OAuthToken oAuthToken = gson.fromJson(sb.toString(), OAuthToken.class);

		
		////////////////////////////////////////////////////////////////////////
		///////////////// [2] 사용자 관리 /////////////////////////////////////////
		///////////////   Access Token 사용한 사용자 정보 요청 ////////////////////////
		//// https://developers.kakao.com/docs/latest/ko/user-mgmt/rest-api ///
		////////////////////////////////////////////////////////////////////////
		
		// Profile 받기 (Resource Server)
		// endpoint2 출처는 https://developers.kakao.com/docs/latest/ko/user-mgmt/rest-api 참조
		// URL: Access Token 사용
		String endpoint2 = "https://kapi.kakao.com/v2/user/me";
		URL url2 = new URL(endpoint2);

		HttpsURLConnection conn2 = (HttpsURLConnection)url2.openConnection();
		// http header 값 넣기
		// Bearer와 토큰값 사이에 스페이스 한칸!
		conn2.setRequestProperty("Authorization", "Bearer "+oAuthToken.getAccess_token());
		conn2.setDoOutput(true);
		
		// request 하기
		BufferedReader br2 = new BufferedReader(
				new InputStreamReader(conn2.getInputStream(), "UTF-8"));
		String input2 = "";
		StringBuilder sb2 = new StringBuilder();
		while((input2 = br2.readLine()) != null) {
			sb2.append(input2);
		}
		System.out.println("sb2.toString() : " + sb2.toString());
		// 바로 위에서 카카오의 리소스 API 서버로부터 보내져온 데이터는 JSON 타입이고 다음의 정보를 포함하고 있다. 
		// {
		//	"id":13xxxxxxx57,
		//	"connected_at":"2020-06-18T02:33:36Z",
		//	"properties":
		//		{
		//		"nickname":"박x주",
		//		"profile_image":"http://k.kakaocdn.net/dn/t2xxx/img_640x640.jpg",
		//		"thumbnail_image":"http://k.kakaocdn.net/dn/t2e0M/btqxxxkk/img_110x110.jpg"
		//		},
		//	"kakao_account":
		//		{
		//		"profile_needs_agreement":false,
		//		"profile":
		//			{
		//			"nickname":"박x주",
		//			"thumbnail_image_url":"http://k.kakaocdn.net/dn/t2e0M/btqxxxk/img_110x110.jpg",
		//			"profile_image_url":"http://k.kakaocdn.net/dn/t2e0M/btqxxxkk/img_640x640.jpg"
		//			},
		//		"has_email":true,
		//		"email_needs_agreement":false,
		//		"is_email_valid":true,
		//		"is_email_verified":true,
		//		"email":"missxxx@hanmxx.net"
		//		}
		//	}
		
		
		// Gson으로 파싱
		Gson gson2 = new Gson();
		KakaoProfile kakaoProfile = gson2.fromJson(sb2.toString(), KakaoProfile.class);
		
		// 
		if(kakaoProfile.getKakao_account().isHas_email() == true) {
			System.out.println("회원가입 진행하기");
			UsersRepository usersRepository = 
					UsersRepository.getInstance();
			String username = kakaoProfile.getKakao_account().getEmail();
			username += "_kakao";
			Users user = Users.builder()
					.username(username)
					.email(kakaoProfile.getKakao_account().getEmail())
					.build();
			int result = usersRepository.findByUsername(username);

			if(result != 1) {
				usersRepository.save(user);
			}
			HttpSession session = request.getSession();
			session.setAttribute("principal", user);

			Script.href("카카오 로그인 완료", "/blog/index.jsp", response);
		}else {
			System.out.println("회원가입 창으로 이동하여 email 사용자로 부터 받기");
		}

		
	}	// execute() 메서드 종료

}
