package com.koreait.java.quiz01;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

	@Bean(name="cal")	//여기서도 bean의 이름을 줄 수 있어요.
	public Calculator cal() {	//그럼 메소드 이름은 안써요 (다른 이름 사용해도 상관없어)
		return new Calculator();
	}
	
	@Bean(name="gugudan")
	public Gugudan gugudan() {
		Gugudan g = new Gugudan();
		g.setBegin(2);
		g.setEnd(6);
		g.setCalculator(cal());
		return g;
	}
}
