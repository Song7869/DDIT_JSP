<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="/includee/preScript.jsp" />
 <link href="${pageContext.request.contextPath }/resources/js/fancytree/skin-win8/ui.fancytree.css" rel="stylesheet">
 <script src="${pageContext.request.contextPath }/resources/js/fancytree/jquery.fancytree-all-deps.min.js"></script>
 <script src="${pageContext.request.contextPath }/resources/js/fancytree/jquery.fancytree-all.min.js"></script>
</head>
<body>
  <div id="empTree">
    
  </div>
  <div id="deptTree">
  
  </div>
  <script type="text/javascript">
  	$("#deptTree").fancytree({
  		source :{
  			url:"${pageContext.request.contextPath }/emp/deptList.do",
  			cache:false
  		}
  	});
  	$("#empTree").fancytree({
	  		 source: {
	  			 url:"${pageContext.request.contextPath }/emp/employeeList_FT.do",
	  			 cache:false
	  		 },
	  		lazyLoad: function(event, data){
	  	      var node = data.node;
	  	      // Load child nodes via Ajax GET /getTreeData?mode=children&parent=1234
	  	      data.result = {
	  	    	url:"${pageContext.request.contextPath }/emp/employeeList_FT.do",
	  	        data: {manager: node.key},
	  	        cache: false
	  	      };
	  	  }
  	});  	
  </script>
</body>
</html>









