<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>  
<!doctype html>
<html>
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
    <meta name="generator" content="Hugo 0.80.0">
    <title>Dashboard Template · Bootstrap v4.6</title>

 	<tiles:insertAttribute name="preScript" />
 	
	<meta name="theme-color" content="#563d7c">


    <style>
      .bd-placeholder-img {
        font-size: 1.125rem;
        text-anchor: middle;
        -webkit-user-select: none;
        -moz-user-select: none;
        -ms-user-select: none;
        user-select: none;
      }

      @media (min-width: 768px) {
        .bd-placeholder-img-lg {
          font-size: 3.5rem;
        }
      }
    </style>
<c:if test="${not empty message }">
	<script type="text/javascript">
		alert("${message }");
		<c:remove var="message" scope="session" />
	</script>
</c:if>
  </head>
  <body class="vh-100">
    <div class="d-flex flex-column h-100">
		<header>
			<tiles:insertAttribute name="header" />
		</header>
	
		<div class="container-fluid flex-grow-1 overflow-auto">
		  <div class="row">
		  	<tiles:insertAttribute name="left" />
		
		    <main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-md-4">
				<tiles:insertAttribute name="content" />
		    </main>
		  </div>
		</div>
		<tiles:insertAttribute name="footer" />
	</div>
	<tiles:insertAttribute name="postScript" />
  </body>
</html>
 