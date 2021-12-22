package kr.or.ddit.member.dao;

import java.util.List;

import kr.or.ddit.vo.MemberVO;

/**
 * 회원관리를 위한 Persistence Layer
 * @author SYG
 */
public interface MemberDAO {
	public MemberVO selectMemberForAuth(String memId);
	
	/**
	 * 회원추가
	 * @param member
	 * @return > 0 : 성공
	 */
	public int insertMember(MemberVO member);
	
	/**
	 * 회원전체목록조회
	 * @return 조회데이터가 없다면, size()=0
	 */
	public List<MemberVO>selectMemberList();
	
	/**
	 * @param memID
	 * @return 조건에 맞는 회원이 없으면, null반환
	 */
	public MemberVO selectMember(String memID);
	
	/**
	 * @param member
	 * @return > 0 : 성공
	 */
	public int updateMember(MemberVO member);
	
	/**
	 * 회원정보삭제(?)
	 * @param memId
	 * @return > 0 : 성공
	 */
	public int deleteMember(String memId);
}
