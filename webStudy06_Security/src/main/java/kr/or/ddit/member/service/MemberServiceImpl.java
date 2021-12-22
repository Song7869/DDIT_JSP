package kr.or.ddit.member.service;

import java.security.AuthProvider;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.inject.Inject;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import kr.or.ddit.common.PKNotFoundException;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.member.dao.MemberDAO;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PagingVO;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MemberServiceImpl implements MemberService {
	@Inject
	private MemberDAO dao;
/*	@Inject
	private AuthenticateService authService;*/
	
	@Inject
	private PasswordEncoder encoder;
	
	@Resource(name="daoAuthenticationProvider")
	private AuthenticationProvider provider;
	
	@PostConstruct
	public void init() {
		log.info("주입된 dao 객체 : {}", dao.getClass());
	}

	private void encryptPassword(MemberVO member) {
		member.setMemPass(encoder.encode(member.getMemPass()));
	}
	
	@Override
	public ServiceResult createMember(MemberVO member) {
		ServiceResult result = null;
		if(dao.selectMember(member.getMemId())==null) {
			
			encryptPassword(member);
			
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
		ServiceResult result = null;
//		ServiceResult result = authService.authenticated(new MemberVO(member.getMemId(), member.getMemPass()));
		try {
			provider.authenticate(new UsernamePasswordAuthenticationToken(member.getMemId(), member.getMemPass()));
			int rowcnt = dao.updateMember(member);
			if(rowcnt > 0) {
				result = ServiceResult.OK;
			}else {
				result = ServiceResult.FAILED;
			}
		}catch (AuthenticationException e) {
			result = ServiceResult.INVALIDPASSWORD;
		}
		
		return result;
	}
	
	@Override
	public ServiceResult removeMember(MemberVO member) {
//		ServiceResult result = authService.authenticated(new MemberVO(member.getMemId(), member.getMemPass()));
		ServiceResult result = null;
		try {
			provider.authenticate(new UsernamePasswordAuthenticationToken(member.getMemId(), member.getMemPass()));
			int rowcnt = dao.deleteMember(member.getMemId());
			if(rowcnt > 0) {
				result = ServiceResult.OK;
			}else {
				result = ServiceResult.FAILED;
			}
		}catch (AuthenticationException e) {
			result = ServiceResult.INVALIDPASSWORD;
		}
		
		return result;
	}

}









