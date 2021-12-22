<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<script type="text/javascript" src="//code.jquery.com/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/jquery.form.min.js"></script>
<c:if test="${not empty message }">
	<script type="text/javascript">
		alert("${message}");
	</script>
	<c:remove var="message" scope="session"/>
</c:if>
