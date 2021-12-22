<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%!
	int num = 0;
	int result =0;
	
	private int factorial(int num){
		result = num;
		for(int i = num-1; i > 0; i--){
			result *= i;
		}
	return result;
	
	}
	

%>
    
<%

String check = request.getParameter("operand")==null ? "" : request.getParameter("operand");
if(check!=""){
	num = Integer.parseInt(request.getParameter("operand"));
	if(num < 1){
		%>너무 작은 수를 입력했습니다.<%		
		return;
	}
	if(num > 10){
		%>너무 큰 수를 입력했습니다.<%
		return;
	}
	factorial(num);
	
}

%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>04/factorial.jsp</title>
</head>

<script type="text/javascript">

function err(){
	
}

</script>

<body>
	<form>
		<input type="number" name="operand" min="1" max="10"/>
		<input type="submit" value="!" />
		<a>=<%= result %></a>
	</form>
</body>
</html>