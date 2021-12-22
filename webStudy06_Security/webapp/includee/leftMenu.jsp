<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<ul>
<c:forEach items="${menuList }" var="menu">
	<security:authorize url="${menu.menuURL }">
		<li>
			<a href="<c:url value='${menu.menuURL }'/>" >${menu.menuText }</a>
		</li>
	</security:authorize>
</c:forEach>
</ul>
