package kr.or.ddit.member.service;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.member.dao.MemberDAO;
import kr.or.ddit.member.dao.MemberDAOImpl;
import kr.or.ddit.vo.MemberVO;

public class AuthenticateServiceImpl implements AuthenticateService {
	// 결합력 발생.
	private MemberDAO dao = new MemberDAOImpl();
	
	@Override
	public ServiceResult authenticated(MemberVO input) {
		ServiceResult result = null;
		MemberVO saved =  dao.selectMemberForAuth(input.getMemId());
		if(saved==null) {
			result = ServiceResult.NOTEXTIST;
		}
		else {
			String inputPass = input.getMemPass();
			String savedPass = saved.getMemPass();
			if(savedPass.equals(inputPass)) {
				result = ServiceResult.OK;
				try {
					BeanUtils.copyProperties(input, saved);
				} catch (IllegalAccessException | InvocationTargetException e) {
					throw new RuntimeException(e);
				}
				//빈유틸은 불확실성을 바탕으로 하기 때문에 에러가 난다.-> try, catch
			}
			else {
				result = ServiceResult.INVALIDPASSWORD;
			}
		}
		return result;
	}

}
