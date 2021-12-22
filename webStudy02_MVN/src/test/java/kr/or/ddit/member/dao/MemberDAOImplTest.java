package kr.or.ddit.member.dao;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Test;

import kr.or.ddit.vo.MemberVO;

public class MemberDAOImplTest {

	MemberDAO dao = new MemberDAOImpl();
	
	@Test
	public void testSelectMemberForAuth() {
		fail("Not yet implemented");
	}

	@Test
	public void testInsertMember() {
		fail("Not yet implemented");
	}

	@Test
	public void testSelectMemberList() {
		List<MemberVO> memList = dao.selectMemberList();
		assertNotNull(memList);
		assertNotEquals(0, memList.size());
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
