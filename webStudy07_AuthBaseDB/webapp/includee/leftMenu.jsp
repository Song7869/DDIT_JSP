<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
  <nav id="sidebarMenu" class="col-md-3 col-lg-2 d-md-block bg-light sidebar collapse">
      <div class="sidebar-sticky pt-3">
        <ul class="nav flex-column">
			<c:forEach items="${menuList }" var="menu">
				<security:authorize url="${menu.menuURL }">
					<li class="nav-item">
						<a class="nav-link" href="<c:url value='${menu.menuURL }'/>">${menu.menuText }</a>
					</li>
				</security:authorize>
			</c:forEach>
		</ul>
	</div>
</nav>