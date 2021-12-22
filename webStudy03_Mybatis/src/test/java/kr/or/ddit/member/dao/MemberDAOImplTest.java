package kr.or.ddit.member.dao;

import static org.junit.Assert.*;

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
		List<MemberVO> memberList = dao.selectMemberList(null);
		assertNotNull(memberList);
		assertNotEquals(0, memberList.size());
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
