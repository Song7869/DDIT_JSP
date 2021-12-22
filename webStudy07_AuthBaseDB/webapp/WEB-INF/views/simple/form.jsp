<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>    
	<form:form method="post" modelAttribute="simple">
		<form:input path="prop1" placeholder="prop1"/>
		<form:errors path="prop1"/>
		<form:input path="prop2"  placeholder="prop2"/>
		<form:errors path="prop2"/>
		<form:input path="prop3"  placeholder="prop3"/>
		<form:errors path="prop3"/>
		<form:button>전송</form:button>
	</form:form>
