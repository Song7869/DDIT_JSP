package kr.or.ddit.vo;

import org.springframework.beans.factory.FactoryBean;

public class StringArrayFactoryBean implements FactoryBean<String[]>{
	//팩토리 빈에서는 아래 getObject의 리턴값으로 나오는 (String[])이 Bean으로 등록된다.
	
	//배열을 만드는 부분
	@Override
	public String[] getObject() throws Exception {
		return new String[] {"element1", "element2"};
	}

	//이제부터 내가 등록하려는게 string[] 타입이다.
	@Override
	public Class<?> getObjectType() {
		return String[].class;
	}

	//여기서는 스코프를 지정해 줄것이다. //false : prototype
	@Override
	public boolean isSingleton() {
		return true;
	}
	
}
