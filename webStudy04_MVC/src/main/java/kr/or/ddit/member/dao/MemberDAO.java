package kr.or.ddit.member.dao;

import java.util.List;

import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PagingVO;

/**
 * 회원 관리를 위한 Persistence Layer
 *
 */
public interface MemberDAO {
	public MemberVO selectMemberForAuth(String memId);
	/**
	 * 회원 추가
	 * @param member
	 * @return > 0 - 성공
	 */
	public int insertMember(MemberVO member);
	
	/**
	 * 전체 레코드수 조회
	 * @param pagingVO TODO
	 * @return
	 */
	public int selectTotalRecord(PagingVO pagingVO);
	
	/**
	 * 회원 목록 조회
	 * @param pagingVO TODO
	 * @return 조회 데이터가 없다면, size()==0
	 */
	public List<MemberVO> selectMemberList(PagingVO pagingVO);
	/**
	 * @param memId
	 * @return 조건에 맞는 회원이 없으면, null 반환
	 */
	public MemberVO selectMember(String memId);
	/**
	 * @param member
	 * @return > 0 - 성공
	 */
	public int updateMember(MemberVO member);
	
	/**
	 * 회원 정보 삭제(?)
	 * @param memId
	 * @return > 0 - 성공
	 */
	public int deleteMember(String memId);
}















