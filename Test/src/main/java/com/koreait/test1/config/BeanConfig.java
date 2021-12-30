package com.koreait.test1.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import com.koreait.test1.service.DeleteBoardService;
import com.koreait.test1.service.InsertBoardService;
import com.koreait.test1.service.SelectBoardListService;
import com.koreait.test1.service.SelectBoardViewService;
import com.koreait.test1.service.UpdateBoardService;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
public class BeanConfig {
	@Bean
	public HikariConfig hikariConfig() {
		HikariConfig hikariConfig = new HikariConfig();
		hikariConfig.setDriverClassName("oracle.jdbc.OracleDriver");
		hikariConfig.setJdbcUrl("jdbc:oracle:thin:@localhost:1521:xe");
		hikariConfig.setUsername("SCOTT");
		hikariConfig.setPassword("1111");
		return hikariConfig;
	}
	@Bean(destroyMethod="close")
	public HikariDataSource hikariDataSource() {
		return new HikariDataSource(hikariConfig());
	}
	@Bean
	public SqlSessionFactory sqlSessionFactory() throws Exception {
		SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
		sqlSessionFactory.setDataSource(hikariDataSource());
		sqlSessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/*.xml"));
		return sqlSessionFactory.getObject();
	}
	@Bean
	public SqlSessionTemplate sqlSession() throws Exception {
		return new SqlSessionTemplate(sqlSessionFactory());
	}
	@Bean
	public SelectBoardListService listService() {
		return new SelectBoardListService();
	}
	@Bean
	public SelectBoardViewService viewService() {
		return new SelectBoardViewService();
	}
	@Bean
	public InsertBoardService insertService() {
		return new InsertBoardService();
	}
	@Bean
	public UpdateBoardService updateService() {
		return new UpdateBoardService();
	}
	@Bean
	public DeleteBoardService deleteService() {
		return new DeleteBoardService();
	}
}
