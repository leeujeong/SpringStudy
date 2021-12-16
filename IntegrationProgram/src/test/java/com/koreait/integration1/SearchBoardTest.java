package com.koreait.integration1;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;

public class SearchBoardTest {

	@Test
	public void 검색어_테스트() {
		try {
			String apiURL = "http://localhost:9090/integration1/search/findBoard?column=TITLE&query=영화";
			URL url = new URL(apiURL);
			HttpURLConnection con = (HttpURLConnection)url.openConnection();
			BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			StringBuilder sb = new StringBuilder();
			String line = "";
			
			while((line = br.readLine())!=null) {
				sb.append(line);
			}
			JSONObject obj = new JSONObject(sb.toString());
			JSONArray arr = obj.getJSONArray("list");
			for(int i =0; i<arr.length(); i++) {
				JSONObject o = arr.getJSONObject(i);
				System.out.println("번호 :" + o.getLong("no"));
				System.out.println("제목 :" + o.getString("title"));
				System.out.println("내용 :" + o.getString("content"));
				System.out.println("작성일 :" + o.getString("regDate"));
			}
			System.out.println();
			sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	


}
