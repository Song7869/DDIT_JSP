package kr.or.ddit.member.service;

import java.lang.reflect.InvocationTargetException;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.beanutils.BeanUtils;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.member.dao.MemberDAO;
import kr.or.ddit.member.dao.MemberDAOImpl;
import kr.or.ddit.util.CryptoUtils;
import kr.or.ddit.vo.MemberVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AuthenticateServiceImpl implements AuthenticateService {
	// 결합력 발생.
	private MemberDAO dao = new MemberDAOImpl();

	@Override
	public ServiceResult authenticated(MemberVO input) {
		ServiceResult result = null;
		MemberVO saved = dao.selectMemberForAuth(input.getMemId());
		if(saved==null) {
			result = ServiceResult.NOTEXIST;
		}else {
			String inputPass = input.getMemPass();
			String savedPass = saved.getMemPass();
			//------------------------------------------------------------
				inputPass = CryptoUtils.sha512Encryptic(inputPass);
			//------------------------------------------------------------
			log.info("비번1 : {}", inputPass);
			log.info("비번2 : {}", savedPass);
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











