<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %> 
<nav class="navbar navbar-dark sticky-top bg-dark flex-md-nowrap p-0 shadow">
  <a  class="nav-link"  class="navbar-brand col-md-3 col-lg-2 mr-0 px-3" href="${pageContext.request.contextPath }">Company name</a>
  <button class="navbar-toggler position-absolute d-md-none collapsed" type="button" data-toggle="collapse" data-target="#sidebarMenu" aria-controls="sidebarMenu" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>   
	<ul class="nav px-3">
		<security:authorize url="/member/memberList.do">
			<li  class="nav-item text-nowrap">
				<a  class="nav-link"  href="${cPath }/member/memberList.do">회원관리</a>
			</li>
		</security:authorize>
		<security:authorize url="/prod/prodList.do">
			<li  class="nav-item text-nowrap">
				<a  class="nav-link"  href="${cPath }/prod/prodList.do">상품관리</a>
			</li>
		</security:authorize>
		<security:authorize url="/buyer/buyerList.do">
			<li  class="nav-item text-nowrap">
				<a  class="nav-link"  href="${cPath }/buyer/buyerList.do">거래처관리</a>
			</li>
		</security:authorize>
		<security:authorize url="/board/boardList.do">
			<li  class="nav-item text-nowrap">
				<a  class="nav-link"  href="${cPath }/board/boardList.do">게시판</a>
			</li>
		</security:authorize>
		<security:authorize url="/emp/employeeList.do">
			<li  class="nav-item text-nowrap">
				<a  class="nav-link"  href="${cPath }/emp/employeeList.do">Employee(DataTable)</a>
			</li>
		</security:authorize>
		<security:authorize url="/emp/employeeList_FT.do">
			<li  class="nav-item text-nowrap">
				<a  class="nav-link"  href="${cPath }/emp/employeeList_FT.do">Employee(FancyTree)</a>
			</li>
		</security:authorize>
		<security:authorize url="/formtag/test">
			<li  class="nav-item text-nowrap">
				<a  class="nav-link"  href="${cPath }/formtag/test">Spring form taglib</a>
			</li>
		</security:authorize>
		<security:authorize url="/security">
	        <li  class="nav-item text-nowrap">
	          <a  class="nav-link"  href="${cPath }/security" tabindex="-1" aria-disabled="true">접근제어설정</a>
	        </li>
	    </security:authorize>
	</ul>
  <ul class="nav px-3">
  	<security:authorize access="!isFullyAuthenticated()">
	    <li class="nav-item text-nowrap">
	      <a class="nav-link" href="${pageContext.request.contextPath }/login/loginForm.jsp">Sign in</a>
	    </li>
    </security:authorize>
  	<security:authorize access="isFullyAuthenticated()">
	  	<security:authentication property="principal.authMember" var="authMember"/>
		<security:authentication property="principal.authorities" var="authorities"/>
	    <li class="nav-item text-nowrap">
		  <a class="nav-link" href="${pageContext.request.contextPath }/mypage.do">${authMember.memName }${authorities }</a>
		</li>
	    <li class="nav-item text-nowrap">
	      <a class="nav-link" href="${pageContext.request.contextPath }/login/logout.do">Sign out</a>
	    </li>
    </security:authorize>
  </ul>
</nav>
