package com.koreait.ex00;

import java.io.IOException;
import java.util.UUID;
import javax.servlet.http.HttpSession;

import org.springframework.util.StringUtils;

import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.oauth.OAuth20Service;
import com.github.scribejava.core.oauth.OAuthService;
import com.github.scribejava.core.model.Verb;

//인증 요청문을 구성해줌
public class NaverLoginBO {
/* 인증 요청문을 구성하는 파라미터*/
//client_id : 애플리케이션 등록후 발급받은 클라이언트 아이디
//response_type: 인증과정에 대한 구분값.code로 값이 고정
//redirect_uri: 네이버 로그인 인증의 결과를 전달받을 콜백URL(URL 인코딩), 애플리케이션을 등록할때 Callback
//state: 애플리케이션이 생성한 상태 토큰
	
private final static String clientId = "C5Iy6W_eEOMMicDgc_n2";
private final static String clientPw = "XlzJHuydWS";
private final static String redirectUri = "http://211.63.89.90:8090/login_project/callback";
private final static String sessionState = "oauth_state";
/*프로필 조회 api url*/
private final static String profileApiUrl = "https://openapi.naver.com/v1/nid/me";

	/*네이버 아이디로 인증 url 생성 Method*/
public String getAutorizationUrl(HttpSession session) {
	String state = generateRandonString();	//세션 유효성검증을 위하여 난수 생성
	setSession(session,state);
	/*Scribe에서 제공하는 인증 URL 생성 기능을 이용하여 네아로 인증 URL 생성*/
	OAuth20Service oauthService = new ServiceBuilder()
	.apiKey(clientId)
	.apiSecret(clientPw)
	.callback(redirectUri)
	.state(state)
	.build(NaverLoginApi.instance());
	
	return oauthService.getAuthorizationUrl();
	
}
/*네이버아이디로 Callback처리 및 AccessToken획득 Method*/
public OAuth2AccessToken getAccessToken(HttpSession session, String code, String state)throws IOException{
	/*Callback으로 전달 받은 세션검증용 난수값과 세션에 저장되어있는 값이 일치하는지 확인*/
	String sessionState = getSession(session);
	if(StringUtils.pathEquals(sessionState, state)) {
		OAuth20Service auuthService = new ServiceBuilder()
				.apiKey(clientId)
				.apiSecret(clientPw)
				.callback(redirectUri)
				.state(state)
				.build(NaverLoginApi.instance());
		
		OAuth2AccessToken access Token = OAuthService.getAccessToken(code);
		return accessToken;
	}
	return null;
}
	private String generateRandomString() {
		return UUID.randomUUID().toString();
	}
	private void setSessiont(HttpSession session, String state) {
		session.setAttribute(sessionState, state);
	}
	private String getSession(HttpSession session) {
		return (String)session.getAttribute(sessionState);
	}
	public String getUserProfile(OAuth2AccessToken oauthToken)throws IOException{
		OAuth20Service oauthService = new ServiceBuilder()
				.apiKey(clientId)
				.apiSecret(clientId)
				.apiSecret(clientPw)
				.callback(redirectUri).build(NaverLoginApi.instance());
		
		OAuthRequest request = new OAuthRequest(Verb.GET, profile_api_url,oauthService);
		oauthService.signRequest(oauthToken, request);
		Response response = request.send();
		return response.getBody();
		
	}

}
