package com.koreait.java.quiz02;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource("quiz04.xml")
public class BeanConfig {

	@Bean
	public Gun gun2() {
		return new Gun("k3", 25);
	}
	
	@Bean
	public Soldier soldier2() {
		
	Map<String, String> army = new HashMap<String, String>();
	army.put("name", "흑골");
	army.put("address", " 강원도 철원군");
	
	return new Soldier("김상사", gun2(),army);
	}
	
}
