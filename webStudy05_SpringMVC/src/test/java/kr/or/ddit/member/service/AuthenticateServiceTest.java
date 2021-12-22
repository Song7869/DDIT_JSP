package kr.or.ddit.member.service;

import static org.junit.Assert.*;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import kr.or.ddit.TestWebAppConfiguration;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.MemberVO;

@RunWith(SpringRunner.class)
@TestWebAppConfiguration
public class AuthenticateServiceTest {

	@Inject
	private AuthenticateService service;
	
	MemberVO input;
	@Before
	public void setUp() throws Exception {
		input = new MemberVO();
		input.setMemId("b001");
		input.setMemPass("java");
	}

	@Test
	public void testAuthenticated() {
		MemberVO input = new MemberVO();
		ServiceResult result = service.authenticated(input);
		assertEquals(ServiceResult.OK, result);
		assertEquals("이쁜이", input.getMemName());
	}

}
