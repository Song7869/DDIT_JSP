<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<ul>
<c:forEach items="${menuList }" var="menu">
	<li>
		<a href="<c:url value='${menu.menuURL }'/>" >${menu.menuText }</a>
	</li>
</c:forEach>
</ul>
