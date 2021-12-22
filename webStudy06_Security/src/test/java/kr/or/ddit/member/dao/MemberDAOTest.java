package kr.or.ddit.member.dao;

import static org.junit.Assert.*;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import kr.or.ddit.vo.MemberVO;

//JUnit 과 또다른 실행 환경을 연결 할 때 쓰는 어노테이션
@RunWith(SpringRunner.class)
@ContextConfiguration("file:webapp/WEB-INF/spring/*-context.xml")
@WebAppConfiguration
@Transactional // 테스트가 끝나면 자동으로 롤백(트랜젝션 관리가 되기 때문에)
public class MemberDAOTest {

	@Inject
	private MemberDAO dao;
	
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testSelectMemberForAuth() {
		MemberVO member =  dao.selectMemberForAuth("b001");
		assertNotNull(member);
		
	}

	@Test
	public void testInsertMember() {
		fail("Not yet implemented");
	}

	@Test
	public void testSelectTotalRecord() {
		fail("Not yet implemented");
	}

	@Test
	public void testSelectMemberList() {
		fail("Not yet implemented");
	}

	@Test
	public void testSelectMember() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateMember() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteMember() {
		fail("Not yet implemented");
	}

}
