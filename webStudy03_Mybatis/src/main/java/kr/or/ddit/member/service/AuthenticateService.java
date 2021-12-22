package kr.or.ddit.member.service;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.MemberVO;

public interface AuthenticateService {
	/**
	 * 
	 * @param input 인증에 필요한 아이디와 비번.
	 * @return OK-인증성공, NOTEXIST-아이디 없음. INVALIDPASSWORD-비번 오류
	 */
	public ServiceResult authenticated(MemberVO input);
}
