<%@page import="java.util.Map.Entry"%>
<%@page import="java.util.LinkedHashMap"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Enumeration"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    
 <%! public enum Brow {Edg, Whale, Chrome, Safari}; %>
 <%
 Brow Edge = Brow.Edg;
 Brow Whale = Brow.Whale;
 Brow Chrome = Brow.Chrome;
 Brow Safari = Brow.Safari;
 %>
 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>03/userAgent.jsp</title>
</head>
<body>
	<h4>User-Agent Header</h4>

<%
	String agent = request.getHeader("User-Agent");
	agent = agent.toUpperCase();
	
	String browserName = null;
	
	Map<String, String> browserMap = new LinkedHashMap<>();
	browserMap.put("EDG","엣지");
	browserMap.put("SAFARI","사파리");
	browserMap.put("CHROME","크롬");
	browserMap.put("OTHER","기타");
	
	browserName = browserMap.get("OTHER");
	
	for(Entry<String, String> entry :browserMap.entrySet()){
		String key = entry.getKey();
		String name = entry.getValue();
		if(agent.contains(key)){
			browserName = name;
			break;
		}
	}
	
	String ptrn = "당신의 브라우저는 %s 입니다!";
	String message = String.format(ptrn, browserName);
	
%>

<%=agent %>
<script type="text/javascript">

	alert("<%=message%>");


</script>




</body>
</html>










