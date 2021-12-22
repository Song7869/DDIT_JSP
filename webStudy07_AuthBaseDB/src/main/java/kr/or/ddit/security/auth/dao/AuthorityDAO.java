package kr.or.ddit.security.auth.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import kr.or.ddit.security.auth.vo.AuthorityVO;

@Repository
public interface AuthorityDAO {
	/**
	 * 모든 역할 정보 조회
	 * @return
	 */
	public List<AuthorityVO> selectAllAuthorities();
	
	/**
	 * 역할 계층 구조 조회
	 * @return
	 */
	public List<String> roleHierarchy();
}
