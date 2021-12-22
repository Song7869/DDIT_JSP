package kr.or.ddit.member.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.vo.MemberVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@WebServlet("/member/memberInsert.do")
public class MemberInsertServlet extends HttpServlet{
	
	private MemberService service = new MemberServiceImpl();
	
	@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//-----------------------------------------------반복패턴
			String viewName = "member/memberForm";
			
			String prefix = "/WEB-INF/views/";
			String suffix = ".jsp";
			
			
			req.getRequestDispatcher(prefix+viewName+suffix).forward(req, resp);
		//-----------------------------------------------			
		}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		
		//1. 파라미터 확보 -> MemberVO -> BeanUtils
		MemberVO mvo = new MemberVO();
		
		Map<String, String[]> parameterMap = req.getParameterMap();
		try {
			BeanUtils.populate(mvo, parameterMap);
		} catch (IllegalAccessException | InvocationTargetException e) {
			throw new ServletException(e);
		}
		
		
		//2. 검증 (필수 : DB의 notnull) DB의 스키마에 따른 검증 룰
		Map<String, String> errors = new LinkedHashMap<>();
		req.setAttribute("errors", errors);
		boolean valid = validate(mvo, errors);// 콜가이 레퍼런스? -> mvo를 validate 하다가 에러가 나면 errors에 들어감
		String viewName = null;
		String message = null;
		
		if(valid) {
			//3-1 통과성공 : 로직 사용(등록-create member) 3가지 상황에 따라 리턴해줌
			ServiceResult sr = service.createMember(mvo);

			switch(sr) {
			case PKDUPLICATED :
				//pk중복 : memberform -> 기존데이터 + 메시지 전달.
					log("pk중복");
					message = "가입 실패.. 중복된 ID 입니다.";					
					viewName = "member/memberForm"; //논리적인 뷰네임
				break;
				
			case OK :
				//ok : 웰컴페이지로 이동 -> 하나의 커멘드가 완전히 끝났으므로 redirect
					log("성공");
					viewName = "redirect:/";
					
					String prefix = "/webStudy02_MVN/";
					String suffix = ".jsp";	
				break;
				
			default :
				//fail : memberform으로
					viewName = "member/memberForm"; //논리적인 뷰네임
					message = "서버 오류, 잠시후에 다시 시도해주세요";
			}
			
		}
		else {
			//3-2불통 : memberform으로 이동 이미 입력한 값은 유지시켜주며 이동.(에러발생시켜주지말고, 검증결과 메시지 : 어디서 문제인지만 알려줘)
			viewName = "member/memberForm"; //논리적인 뷰네임
			
		}
		
		req.setAttribute("member", mvo);
		req.setAttribute("message", message);
		
		if(viewName.startsWith("redirect:")) {			
			viewName = viewName.substring("redirect:".length());
			resp.sendRedirect(req.getContextPath()+viewName);
		}
		else {
			String prefix = "/WEB-INF/views/";
			String suffix = ".jsp";
			req.getRequestDispatcher(prefix+viewName+suffix).forward(req, resp);
		}
	
	}

	private boolean validate(MemberVO member, Map<String, String> errors) {
		boolean valid = true;
		if (StringUtils.isBlank(member.getMemId())) {
			valid = false;
			errors.put("memId", "회원아이디가(이) 누락되었습니다.");
		}
		if (StringUtils.isBlank(member.getMemPass())) {
			valid = false;
			errors.put("memPass", "비밀번호가(이) 누락되었습니다.");
		}
		if (StringUtils.isBlank(member.getMemName())) {
			valid = false;
			errors.put("memName", "회원명가(이) 누락되었습니다.");
		}
		if (StringUtils.isBlank(member.getMemRegno1())) {
			valid = false;
			errors.put("memRegno1", "주민번호1가(이) 누락되었습니다.");
		}
		if (StringUtils.isBlank(member.getMemRegno2())) {
			valid = false;
			errors.put("memRegno2", "주민번호2가(이) 누락되었습니다.");
		}
		if (StringUtils.isBlank(member.getMemZip())) {
			valid = false;
			errors.put("memZip", "우편번호가(이) 누락되었습니다.");
		}
		if (StringUtils.isBlank(member.getMemAdd1())) {
			valid = false;
			errors.put("memAdd1", "주소1가(이) 누락되었습니다.");
		}
		if (StringUtils.isBlank(member.getMemAdd2())) {
			valid = false;
			errors.put("memAdd2", "주소2가(이) 누락되었습니다.");
		}
		if (StringUtils.isBlank(member.getMemHometel())) {
			valid = false;
			errors.put("memHometel", "집전화번호가(이) 누락되었습니다.");
		}
		if (StringUtils.isBlank(member.getMemComtel())) {
			valid = false;
			errors.put("memComtel", "회사번호가(이) 누락되었습니다.");
		}
		if (StringUtils.isBlank(member.getMemMail())) {
			valid = false;
			errors.put("memMail", "메일가(이) 누락되었습니다.");
		}
		return valid;
	}

}



















