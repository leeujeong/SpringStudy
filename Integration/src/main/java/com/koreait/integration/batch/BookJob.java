package com.koreait.integration.batch;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import org.json.JSONObject;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.koreait.integration.domain.Book;

public class BookJob implements Job {

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		//저자 "황순원", "이유정" 중 임의로 한명의 정보를 이용하여 도서를 검색한 결과를 출력하시오. (파일을 생성하시오)
		try {
			String apiURL = "http://localhost:9090/integration/book/findAllBook";
			URL url = new URL(apiURL);
			HttpURLConnection con = (HttpURLConnection)url.openConnection();
			BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			StringBuilder sb = new StringBuilder();
			String line= "";
			while((line = br.readLine()) != null) {
				sb.append(line);
			}
			JSONObject obj = new JSONObject(sb.toString());
			
			List<Book>list= 
			
			
			File file = new File("radom.txt");
			BufferedWriter bw = new BufferedWriter(new FileWriter(file)) {
				for(Book book : list) {
					bw.write(obj.getInt("no"));
				}
			}
					
					
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		

	}

}
