package kr.or.ddit.member.service;

import java.util.List;

import kr.or.ddit.common.PKNotFoundException;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.member.dao.MemberDAO;
import kr.or.ddit.member.dao.MemberDAOImpl;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PagingVO;

public class MemberServiceImpl implements MemberService {
	private MemberDAO dao = new MemberDAOImpl();
	private AuthenticateService authService = new AuthenticateServiceImpl();

	@Override
	public ServiceResult createMember(MemberVO member) {
		ServiceResult result = null;
		if(dao.selectMember(member.getMemId())==null) {
			int rowcnt = dao.insertMember(member);
			if(rowcnt>0) {
				result = ServiceResult.OK;
			}else {
				result = ServiceResult.FAILED;
			}
		}else {
			result = ServiceResult.PKDUPLICATED;
		}
		return result;
	}

	@Override
	public List<MemberVO> retrieveMemberList(PagingVO pagingVO) {
		int totalRecord = dao.selectTotalRecord(pagingVO);
		pagingVO.setTotalRecord(totalRecord);
		return dao.selectMemberList(pagingVO);
	}

	@Override
	public MemberVO retrieveMember(String memId) {
		MemberVO member = dao.selectMember(memId);
		if(member==null)
			throw new PKNotFoundException(memId+"에 해당하는 회원 없음.");
		return member;
	}

	@Override
	public ServiceResult modifyMember(MemberVO member) {
		ServiceResult result = authService.authenticated(new MemberVO(member.getMemId(), member.getMemPass()));
		if(ServiceResult.OK.equals(result)) { // 인증 성공
			int rowcnt = dao.updateMember(member);
			if(rowcnt > 0) {
				result = ServiceResult.OK;
			}else {
				result = ServiceResult.FAILED;
			}
		}else if(ServiceResult.NOTEXIST.equals(result)){
			throw new PKNotFoundException(member.getMemId() + "해당 회원이 없음.");
		}
		return result;
	}
	
	@Override
	public ServiceResult removeMember(MemberVO member) {
		ServiceResult result = authService.authenticated(new MemberVO(member.getMemId(), member.getMemPass()));
		if(ServiceResult.OK.equals(result)) { // 인증 성공
			int rowcnt = dao.deleteMember(member.getMemId());
			if(rowcnt > 0) {
				result = ServiceResult.OK;
			}else {
				result = ServiceResult.FAILED;
			}
		}else if(ServiceResult.NOTEXIST.equals(result)){
			throw new PKNotFoundException(member.getMemId() + "해당 회원이 없음.");
		}
		return result;
	}

}









