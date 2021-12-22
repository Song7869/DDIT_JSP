<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>

 <ul> 	
 	<li>
 		<a href="${cPath }/">첫 화면</a>
 	</li>
 	<security:authorize url="/member/memberList.do">
	 	<li>
	 		<a href="${cPath }/member/memberList.do">회원 관리</a>
	 	</li>
 	</security:authorize>
 	<security:authorize url="/prod/prodList.do">
 	<li>
 		<a href="${cPath }/prod/prodList.do">상품 관리</a>
 	</li>
 	</security:authorize>
 	<security:authorize url="/board/boardList.do">
 	<li>
 		<a href="${cPath }/board/boardList.do">게시판</a>
 	</li>
 	</security:authorize>
 	<security:authorize url="/emp/employeeList.do">
 	<li>
 		<a href="${cPath }/emp/employeeList.do">사원 관리</a>
 	</li>
 	</security:authorize>
 	<security:authorize url="/emp/employeeList_FT.do">
 	<li>
 		<a href="${cPath }/emp/employeeList_FT.do">사원관리 2</a>
 	</li>
 	</security:authorize>
 </ul>
