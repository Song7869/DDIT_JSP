package kr.or.ddit.member.service;

import java.lang.reflect.InvocationTargetException;

import javax.inject.Inject;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Service;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.member.dao.MemberDAO;
import kr.or.ddit.util.CryptoUtils;
import kr.or.ddit.vo.MemberVO;

@Service
public class AuthenticateServiceImpl implements AuthenticateService {
	// 결합력 발생.
	@Inject
	private MemberDAO dao;

	@Override
	public ServiceResult authenticated(MemberVO input) {
		ServiceResult result = null;
		MemberVO saved = dao.selectMemberForAuth(input.getMemId());
		if(saved==null) {
			result = ServiceResult.NOTEXIST;
		}else {
			String inputPass = input.getMemPass();
			inputPass = CryptoUtils.sha512EncryptBase64(inputPass);
			String savedPass = saved.getMemPass();
			if(savedPass.equals(inputPass)) {
				result = ServiceResult.OK;
				try {
					BeanUtils.copyProperties(input, saved);
				} catch (IllegalAccessException | InvocationTargetException e) {
					throw new RuntimeException(e);
				}
			}else {
				result = ServiceResult.INVALIDPASSWORD;
			}
		}
		return result;
	}

}











