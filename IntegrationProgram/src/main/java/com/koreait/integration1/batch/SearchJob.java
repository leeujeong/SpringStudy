package com.koreait.integration1.batch;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.json.JSONArray;
import org.json.JSONObject;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class SearchJob implements Job {

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		
	
		try {
			String[] titles = {"코미디", "공포", "멜로", "드라마", "SF"};
			String title = URLEncoder.encode(titles[(int)(Math.random() * titles.length)], "UTF-8");
			
			String apiURL = "http://localhost:9090/integration1/search/findBoard?column=TITLE&query=" + title;
			URL url = new URL(apiURL);
			HttpURLConnection con = (HttpURLConnection)url.openConnection();
			BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			StringBuilder sb = new StringBuilder();
			String line= "";
			
			while((line = br.readLine()) != null) {
				sb.append(line);
			}
			JSONObject obj = new JSONObject(sb.toString());
			JSONArray arr = obj.getJSONArray("list");
				
			System.out.println("status: "+ obj.getInt("status"));
			System.out.println("message:" + obj.getString("message"));
			arr = obj.getJSONArray("list");
			for(int i =0; i<arr.length(); i++) {
				JSONObject o = arr.getJSONObject(i);
				System.out.println("번호 :" + o.getInt("no"));
				System.out.println("제목 :" + o.getString("title"));
				System.out.println("내용 :" + o.getString("content"));
			}
			
			
			if(line == null) {
				File file =new File("error.txt");
				BufferedWriter bw = new BufferedWriter((new FileWriter(file)));	
				System.out.println("파일 생성");
				
				for(int j =0; j<arr.length(); j++) {
					JSONObject o1 = arr.getJSONObject(j);
					bw.write("번호 :" + o1.getLong("no"));
					bw.write("제목 :" + o1.getString("title"));
					bw.write("내용 :" + o1.getString("content"));
					bw.write("작성일 :" + o1.getString("regDate"));
					bw.close();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();

		}

	}
}
