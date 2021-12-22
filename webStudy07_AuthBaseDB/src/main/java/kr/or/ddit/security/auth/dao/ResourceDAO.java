package kr.or.ddit.security.auth.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import kr.or.ddit.security.auth.vo.AuthorityVO;
import kr.or.ddit.security.auth.vo.ResourceVO;

@Repository
public interface ResourceDAO {
	/**
	 * 전체 자원(메뉴) 조회
	 * @return
	 */
	public List<ResourceVO> selectAllResources();
	
	/**
	 * FilterInvocationMetadataSource 에 의해 사용될 자원에 대한 접근제어 설정 조회
	 * @return
	 */
	public List<ResourceVO> selectAllSortedResources();
	
	/**
	 * 역할별 접근 가능한 자원의 목록 조회
	 * @param authority
	 * @return
	 */
	public List<ResourceVO> selectResourceListForAuthority(AuthorityVO authority);
	
	/**
	 * 역할별 접근 가능한 자원을 갱신하기 전 기존 설정 제거
	 * @param authority
	 * @return
	 */
	public int deleteResourceRole(AuthorityVO authority);
	
	/**
	 * 역할별 접근 가능한 자원을 갱신
	 * @param authority
	 * @return
	 */
	public int insertResourceRole(AuthorityVO authority);
}
