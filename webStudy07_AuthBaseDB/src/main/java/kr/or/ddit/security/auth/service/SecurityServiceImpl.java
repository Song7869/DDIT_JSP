package kr.or.ddit.security.auth.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.security.auth.ReloadableFilterInvocationSecurityMetadataSource;
import kr.or.ddit.security.auth.dao.AuthorityDAO;
import kr.or.ddit.security.auth.dao.ResourceDAO;
import kr.or.ddit.security.auth.vo.AuthorityVO;
import kr.or.ddit.security.auth.vo.ResourceVO;

@Service
public class SecurityServiceImpl implements SecurityService {
	@Inject
	private AuthorityDAO authDAO;

	@Inject
	private ResourceDAO resDAO;
	
	@Inject
	private ReloadableFilterInvocationSecurityMetadataSource filterInvocationSecurityMetadataSource;
	
	@Override
	public List<AuthorityVO> retrieveAllAuthorities() {
		return authDAO.selectAllAuthorities();
	}

	@Override
	public List<ResourceVO> retrieveAllResources() {
		return resDAO.selectAllResources();
	}
	
	@Override
	public List<ResourceVO> retrieveResourceListForAuthority(AuthorityVO authority) {
		return resDAO.selectResourceListForAuthority(authority);
	}
	
	@Transactional
	@Override
	public ServiceResult updateResourceRole(AuthorityVO authority) {
		int cnt = resDAO.deleteResourceRole(authority);
		String[] resources = authority.getResourceId();
		if(resources!=null && resources.length > 0) {
			cnt += resDAO.insertResourceRole(authority);
		}
		ServiceResult result = ServiceResult.FAILED;
		if(cnt > 0) {
			result = ServiceResult.OK;
			// 역할별 접근제어 설정이 변경되면, 설정 데이터 캐싱 메타데이터를 리로딩해야 함.
			filterInvocationSecurityMetadataSource.reload();
		}
		return result;
	}

}
