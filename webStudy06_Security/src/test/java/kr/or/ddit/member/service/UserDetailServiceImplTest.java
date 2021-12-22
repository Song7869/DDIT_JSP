package kr.or.ddit.member.service;

import javax.annotation.Resource;
import javax.inject.Inject;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import kr.or.ddit.TestWebAppConfiguration;
import kr.or.ddit.vo.MemberVOWrapper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(SpringRunner.class)
@TestWebAppConfiguration
public class UserDetailServiceImplTest {
	
	@Resource(name="userDetailService")
	private UserDetailsService service;
	
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testLoadUserByUsername() {
		MemberVOWrapper userDetail =  (MemberVOWrapper) service.loadUserByUsername("a001");
		log.info("authMember : {}", userDetail.getAuthMember());
	}

	@Inject
	private PasswordEncoder encoder;
	
	@Test
	public void encodePassword() {
		String plain = "java";
		String encoded = encoder.encode(plain);
		log.info("암호화된 비밀번호 : {}", encoded);
		String savedPass = "{bcrypt}$2a$10$nf.cSwljACzvpVEEHnT7POcFksWpHX5/LXxsUeYX2xCEafXf2ID7O";
		log.info("비밀번호 통과 여부 : {}", encoder.matches(plain, savedPass));
	}
}





















