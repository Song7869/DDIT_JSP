package kr.or.ddit.member.service;

import java.util.List;

import kr.or.ddit.common.servlet.PKNotFoundException;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.MemberVO;

/**
 * 회원 관리를 위한 Business Logic Layer
 */
public interface MemberService {
	/**
	 * 회원 가입
	 * @param member
	 * @return PKDUPLICATED : 아이디 중복, OK : 가입 성공, FAIL : 가입 실패
	 */
	public ServiceResult createMember(MemberVO member);
	
	public List<MemberVO> retrieveMemberList();
	
	/**
	 * 회원 정보 탐색
	 * @param memId
	 * @return 존재하지 않는다면, {@link PKNotFoundException} 발생(unchecked)
	 */
	public MemberVO retrieveMember(String memId);
	
	/**
	 * 회원 정보 수정
	 * @param member
	 * @return 존재하지 않는다면, {@link PKNotFoundException} 발생(unchecked), INVALIDPASSWORD, OK, FAIL
	 */
	public ServiceResult modifyMember(MemberVO member);
	
	/**
	 * 회원 탈퇴
	 * @param member
	 * @return 존재하지 않는다면, {@link PKNotFoundException} 발생(unchecked), INVALIDPASSWORD, OK, FAIL
	 */
	public ServiceResult removeMember(MemberVO member);
}
