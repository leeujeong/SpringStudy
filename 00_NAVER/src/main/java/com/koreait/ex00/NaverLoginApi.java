package com.koreait.ex00;

import com.github.scribejava.core.builder.api.DefaultApi20;

//Scribe library 용 Naver Login 구현체 추가
public class NaverLoginApi extends DefaultApi20{

	protected NaverLoginApi() {
		
	}
	private static class InstanceHolder{
		private static final NaverLoginApi INSATNCE = new NaverLoginApi();
	}
	
	public static NaverLoginApi instance() {
		return InstanceHolder.INSATNCE;
	}
	@Override
	public String getAccessTokenEndpoint() {
		return "https://nid.naver.com/oauth2.0/token?grant_type=authorization_code";
	}
	@Override
	public String getAuthorizationBaseUrl() {
		return "https://nid.naver.com/oauth2.0/authorize";
	}
	
}
