package kr.or.ddit.security.auth.service;

import java.util.List;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.security.auth.vo.AuthorityVO;
import kr.or.ddit.security.auth.vo.ResourceVO;

public interface SecurityService {
	/**
	 * 모든 역할 정보 조회
	 * @return
	 */
	public List<AuthorityVO> retrieveAllAuthorities();
	/**
	 * 전체 자원(메뉴) 조회
	 * @return
	 */
	public List<ResourceVO> retrieveAllResources();
	
	/**
	 * 역할별 접근 가능한 자원의 목록 조회
	 * @param authority
	 * @return
	 */
	public List<ResourceVO> retrieveResourceListForAuthority(AuthorityVO authority);
	
	/**
	 * 역할별 접근 가능한 자원 정보 갱신
	 * @param authority
	 * @return
	 */
	public ServiceResult updateResourceRole(AuthorityVO authority);
}
