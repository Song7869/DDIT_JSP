<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<form id="authForm" action="${cPath }/security" method="post">
	<c:set var="selectedAuthority" value="${requestScope['org.springframework.web.servlet.View.pathVariables']['authority'] }"/>
	<div class="row gx-5 m-3">
		<div class="col">
		<select name="authority" id="roleTag" class="form-select" required>
			<option value>역할 선택</option>
			<c:forEach items="${authorities }" var="roleVO">
				<option value="${roleVO.authority }" ${roleVO eq selectedAuthority ? "selected":"" }>${roleVO.description}[${roleVO.roleName }]</option>
			</c:forEach>
		</select>
		</div>
		<div class="col">
		<input type="submit" class="btn btn-primary col" value="저장" />
		</div>
	</div>
	<c:forEach items="${resourceList }" var="resource" varStatus="vs">
		<c:if test="${resource.level le 2 }">
			<c:if test="${vs.count ge 2 }"></div></c:if>
			<div class="row m-3">
			<h5>${resource.description }</h5>
		</c:if>
		<div class="form-check col">
			  <label class="form-check-label" title="${resource.resourcePattern }" data-bs-toggle="tooltip" data-bs-placement="top">
				<input name="resourceId" id="${resource.resourceId }" class="form-check-input" type="checkbox" value="${resource.resourceId }" 
					${not empty selectedAuthority and fn:contains(resource.authorities, selectedAuthority)?"checked":"" }
				>
		    	${resource.description }
			  </label>
		</div>
		<c:if test="${ vs.last }"></div></c:if>
	</c:forEach>
</form>
<script type="text/javascript">
	$("#roleTag").on("change", function(){
		location.href="${cPath}/security/"+$(this).val();
	});
	<c:if test="${not empty selectedAuthority}">
		$.ajax({
			dataType:"json"
			, success:function(resList){
				$(resList).each(function(idx, res){
					$("#"+res.resourceId).prop({
						disabled:true
						,checked:true
					});
				});
			}
			, error:function(resp){
				console.log(resp);
			}
		})		
	</c:if>
</script>