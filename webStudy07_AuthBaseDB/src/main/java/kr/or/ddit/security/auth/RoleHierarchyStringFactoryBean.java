package kr.or.ddit.security.auth;

import java.util.List;

import javax.inject.Inject;

import org.springframework.beans.factory.FactoryBean;

import kr.or.ddit.security.auth.dao.AuthorityDAO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RoleHierarchyStringFactoryBean implements FactoryBean<String>{
	
	@Inject
	private AuthorityDAO authorityDAO;

	@Override
	public String getObject() throws Exception {
		List<String> hierarchy = authorityDAO.roleHierarchy();
		String hierarchyString = hierarchy.stream().reduce((ele1, ele2)->{return ele1+"\n"+ele2;}).get();
		log.info("{}", hierarchyString);
		return hierarchyString;
	}

	@Override
	public Class<?> getObjectType() {
		return String.class;
	}

	@Override
	public boolean isSingleton() {
		return false;
	}
	
		
}
