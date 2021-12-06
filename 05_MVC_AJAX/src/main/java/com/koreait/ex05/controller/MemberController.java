package com.koreait.ex05.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.koreait.ex05.domain.Member;

@Controller
public class MemberController {

	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	//ajax 처리 할때는 view를 반환하지 않는다.
	//ajax는 데이터를 반환한다.
	//text/plain, text/xml, application/json 등
	
	@ResponseBody
	@GetMapping(value="member/v1.do",produces="text/plain; charset=UTF-8")
	public String v1(@RequestParam ("id") String id) {
		String result = "안녕? 만나서 반가워 " + id;
		return result;
	}
	
	@ResponseBody
	@GetMapping(value="member/v2.do",
				produces="application/json; charset=UTF-8")
	public Member v2(Member member) {
		return member;	//반환되는 bean은 jackson data-bind에 의해서 json으로 변환됨
	}
	
	@ResponseBody
	@GetMapping(value="member/v3.do", produces="application/json; charset=UTF-8")
	public Map<String , String> v3(HttpServletRequest request){
		Map<String, String> resultMap = new HashMap<String, String>();
		resultMap.put("id", request.getParameter("id"));
		resultMap.put("pw", request.getParameter("pw"));
		return resultMap;
	}
	
	@PostMapping(value="member/v4.do", produces="application/json; charset=UTF-8")
	public Map<String , Object> v4 (@RequestBody Member member){
		Map<String , Object> map = new HashMap<String, Object>();
		map.put("id", member.getId());
		map.put("pw", member.getPw());
		map.put("addOn", 1000);
		return map;
	}
	
	
}
