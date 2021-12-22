package kr.or.ddit.member.dao;

import static org.junit.Assert.*;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Test;

import kr.or.ddit.mvc.annotation.RequestMethod;
import kr.or.ddit.mvc.annotation.stereotype.Controller;
import kr.or.ddit.mvc.annotation.stereotype.RequestMapping;
import kr.or.ddit.vo.MemberVO;

@Controller
public class MemberDAOImplTest {
	
	@RequestMapping(value="/test.do", method=RequestMethod.POST)
	public String testHandler(HttpServletRequest req, HttpServletResponse resp) {
		return "viewName";
	}
	
	public String testHandler2(HttpServletRequest req, HttpServletResponse resp) {
		return "viewName";
	}
	
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
