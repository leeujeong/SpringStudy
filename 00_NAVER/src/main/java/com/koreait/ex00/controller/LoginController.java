package com.koreait.ex00.controller;

import java.io.IOException;
import java.text.ParseException;

import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.scribejava.core.model.OAuth2AccessToken;
import com.koreait.ex00.NaverLoginBO;

@Controller
public class LoginController {

	private NaverLoginBO naverLoginBO;
	private String apiResult = null;
	
	@Autowired
	private void setNaverLoginBO(NaverLoginBO naverLoginBO) {
		this.naverLoginBO = naverLoginBO;
	}
	//로그인 첫 화면 요청 메소드
	@RequestMapping(value="/login",method = {RequestMethod.GET, RequestMethod.POST})
	public String login(Model model, HttpSession session) {
		//네이버 아이디로 인증 URL 을 생성하기 위하여 naverLoginBO 클래스의 getAuthorizationUrl호출
		String naverAuthUrl = naverLoginBO.getAutorizationUrl(session);
		//https://nid.naver.com/oauth2.0/authorize?response_type=code&client_id=sE***************&
		//redirect_uri=http%3A%2F%2F211.63.89.90%3A8090%2Flogin_project%2Fcallback&state=e68c269c-5ba9-4c31-85da-54c16c658125
		System.out.println("네이버:" + naverAuthUrl);
		model.addAttribute("url", naverAuthUrl);
		return "login";
	}
	
	//네이버 로그인 성공시 callback호출 메소드
	@RequestMapping(value="/callback" method= {RequestMethod.GET, RequestMethod.POST})
	public String callback(Model model, @RequestParam String code,@RequestParam String state, HttpSession session)throws IOException,ParseException{
		System.out.println("여기는 callback");
			OAuth2AccessToken oauthToken;
			oauthToken = naverLoginBO.getAccessToken(session, code, state);
			
			//1.로그인 사용자 정보를 읽어온다
			apiResult = naverLoginBO.getUserProfile(oauthToken);
			/*
			 apiResult json구조
			 {"resultcode" : "00",
			  "message" : "success",
			  "response":{"id":~~~~}
			*/
			
			//2. String 형식인 apiResult 를 json형태로 바꿈
			JSONParser parser = new JSONParser();
			Object obj = parser.parse(apiResult);
			JSONObject jsonObj = (JSONObject)obj;
			
			//3. 데이터파싱
			//Top 레벨 단계 _repsonse파싱
			JSONObject response_obj = (JSONObject)jsonObj.get("response");
			
			//response의 nickname값 파싱
			String nickname = (String)response_obj.get("nickname");
			System.out.println("nickname");
			
			//4. 파싱 닉네임 세션으로 저장
			session.setAttribute("sessionId", nickname);
			model.addAttribute("result", apiResult);
			return "login";
			}
	//로그아웃
	@RequestMapping(value="/logout", method= {RequestMethod.GET, RequestMethod.POST})
	public String logout(HttpSession session) throws IOException{
		System.out.println("여기는 logout");
		session.invalidate();
		return "redirect:index.jsp";
	}
	
	
}
