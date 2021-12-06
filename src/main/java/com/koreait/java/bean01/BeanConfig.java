package com.koreait.java.bean01;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*
	@Configuration
	:안녕 난 Bean을 만드는 Java 클래스야 
	 날 이용하려면 cglib 디펜던시를  추가해야돼.
*/

@Configuration
public class BeanConfig {
	
	//메소드 1개 = Bean 1개
	//Bean을 만드는 메소드는 @Bean 애너테이션이 필요함
	
	//반환타입 : Song <bean class="Song">
	//메소드명 : mysong <bean id="mysong">
	@Bean
	public Song mySong() {
		//setter injection을 하든 constructor injection을 하든 자유!
		Song result = new Song();
		result.setTitle("hello");	//<property>
		result.setGenre("balad");
		
		return result;
	}
	
	@Bean
	public Singer mySinger() {//<bean class="singer" id="mySinger">
		return new Singer("adele",mySong());	//<constructor-arg>
	}
	
}
