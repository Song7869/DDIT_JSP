package kr.or.ddit.sample.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;

import kr.or.ddit.sample.dao.SampleDAO_Mysql;
import kr.or.ddit.sample.dao.SampleDAO_Oracle;
import kr.or.ddit.sample.service.SampleService;
import kr.or.ddit.sample.service.SampleServiceImpl;

@Configuration
@Lazy
public class JavaConfiguration {
	
	@Bean(initMethod="init", destroyMethod="destroy")
	public SampleDAO_Oracle sampleDAO_Oracle() {
		return new SampleDAO_Oracle();
	}
	
	@Bean
	public SampleDAO_Mysql sampleDAO_Mysql() {
		return new SampleDAO_Mysql();
	}
	
	@Bean
	public SampleService sampleService1() {
		return new SampleServiceImpl(sampleDAO_Oracle());
	}
	
	@Bean("sampleService2")
	@Scope("prototype")
	public SampleService sampleService2(SampleDAO_Mysql dao) {
		SampleServiceImpl service2 = new SampleServiceImpl();
		service2.setDaoaa(dao);
		return service2;
	}
}
















