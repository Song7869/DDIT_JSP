package kr.or.ddit.member.service;

import java.util.List;

import kr.or.ddit.common.PKNotFoundException;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PagingVO;

/**
 * 회원 관리를 위한 Business Logic Layer
 *
 */
public interface MemberService {
	/**
	 * 회원 가입
	 * @param member
	 * @return PKDUPLICATED - 아이디 중복, OK - 가입 성공, FAIL - 가입 실패
	 */
	public ServiceResult createMember(MemberVO member);
	public List<MemberVO> retrieveMemberList(PagingVO pagingVO);
	/**
	 * 회원 정보 상세 조회
	 * @param memId
	 * @return 존재하지 않는다면, {@link PKNotFoundException} 발생
	 */
	public MemberVO retrieveMember(String memId);
	/**
	 * 회원 정보 수정
	 * @param member
	 * @return 존재하지 않는다면, {@link PKNotFoundException} 발생, INVALIDPASSWORD, OK , FAIL
	 */
	public ServiceResult modifyMember(MemberVO member); 
	/**
	 * 회원 탈퇴
	 * @param member
	 * @return 존재하지 않는다면, Custom exception 발생, INVALIDPASSWORD, OK , FAIL
	 */
	public ServiceResult removeMember(MemberVO member);
}


















