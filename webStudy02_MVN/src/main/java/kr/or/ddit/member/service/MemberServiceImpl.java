package kr.or.ddit.member.service;

import java.util.List;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.member.dao.MemberDAO;
import kr.or.ddit.member.dao.MemberDAOImpl;
import kr.or.ddit.vo.MemberVO;

public class MemberServiceImpl implements MemberService {
	
	private MemberDAO dao = new MemberDAOImpl();

	@Override
	public ServiceResult createMember(MemberVO member) {
		ServiceResult res = null;
		
		if(dao.selectMember(member.getMemId())==null) {
			int rowcnt = dao.insertMember(member);
			if(rowcnt>0) {
				res = ServiceResult.OK;
			}
			else {
				res = ServiceResult.FAILED;
			}
		}
		else {
			res = ServiceResult.PKDUPLICATED;
		}
		
		return res;
		

		
	}

	@Override
	public List<MemberVO> retrieveMemberList() {
		return dao.selectMemberList();
	}

	@Override
	public MemberVO retrieveMember(String memId) {
		MemberVO member = dao.selectMember(memId);
		if(member == null) {
			
		}
			
		return member;
	}

	@Override
	public ServiceResult modifyMember(MemberVO member) {
		ServiceResult res = null;
		
		if(dao.selectMember(member.getMemId())!=null) {
			int rowcnt = dao.updateMember(member);
			if(rowcnt>0) {
				res = ServiceResult.OK;
			}
			else {
				res = ServiceResult.FAILED;
			}
		}
		
		return res;
	}

	@Override
	public ServiceResult removeMember(MemberVO member) {
		// TODO Auto-generated method stub
		return null;
	}

}
