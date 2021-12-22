<%@page import="java.util.Locale"%>
<%@page import="java.util.Calendar"%>
<%@page import="static java.util.Calendar.*"%> <!-- 캘린더가 가지고 있는 static 멤버들을 전부 import => static import => Calendar.을 안 써도 됨 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- <meta http-equiv="Refresh" content="5;url=https://www.naver.com"> --> <!-- 아래 client side -->
<title>06/autoRequest.jsp</title>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/jquery-3.6.0.min.js"></script>
</head>
<body>
<h4>헤더를 이용한 페이지 갱신(동기 요청)</h4>
<%
   Locale[] locales = Locale.getAvailableLocales();
%>
<select id="language">
   <%
      for(Locale tmp : locales){
         String text = tmp.getDisplayLanguage(tmp);
         if(text == null || text.isEmpty()) continue;
         String languageTag = tmp.toLanguageTag();
         String selected = tmp.equals(request.getLocale()) ? "selected" : "";;
   %>
         <option value="<%=languageTag%>" <%=selected %>><%=text %></option>
   <% 
      }
   %>
</select>
<h4>현재 서버의 시간 : <span id="serverTime"></span></h4>
<h4>현재 클라이언트의 시간 : <span id="clientTime"></span></h4>
<!-- <h4><span id="timer"></span>초 뒤에 네이버로 이동</h4> -->
<pre>
   auto request 방법 
   1. server side : Refresh 응답 헤더 
   <%-- 
      response.setIntHeader("Refresh", 1);   // 1초마다 갱신을 해라 => 1초마다 페이지 전체가 새로고침 된다. => 동기요청 
   --%> 
   
   2. client side 
      1) html : meta 태그의 http-equiv 사용 (Refresh) (위에 meta에서 사용) 
      2) javascript : scheduling funciton 사용, location.reload()(refresh역할 => 페이지 갱신) 
</pre>
<script type="text/javascript">
   let clientTime = $("#clientTime");
   let serverTime = $("#serverTime");
   let language = $("#language");
   
   setInterval(function(){
      clientTime.text(new Date());
      $.ajax({ 
         url : "<%=request.getContextPath() %>/getServerTime.do",
         data : {
            language:language.val()
         },
         dataType : "text", 
         success : function(resp) { 
            serverTime.text(resp);
         },
         error : function(xhr, errorResp, error) { // xhr => 요청을 보낼때의 모든 정보를 가지고 있음
            console.log(xhr);
            console.log(errorResp);
            console.log(error);
         }
      });
      
   }, 1000);

//    const TIMERAREA = document.getElementById("timer");
//    const TIMEINIT = 5; // 값이 1씩 줄어야하므로 변수로 다시 받아야함
//    let timeVal = TIMEINIT;
   
//    setInterval(()=>{ 
//       TIMERAREA.innerHTML = --timeVal; 
//    }, 1000);   // 1초마다 setInterval 

//    setTimeout(() => { 
//       location.reload(true);   // ()에 true를 넣으면 reload 할 수 없는 상황에서도 강제 새로고침 
//    }, 1000);
   
</script>
</body>
</html>