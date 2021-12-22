<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%!

//윤년 체크
public static boolean chkYear(int year){
	return (year % 4 == 0) && (year % 100 !=0) || (year % 400 == 0);
}

//해당 달의 날짜 확인
public static int totalDay(int year, int month){
	int[] total = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
	
	// 윤년이면 2월 이 29일
	if(chkYear(year)==true){
		total[1] = 29;
	}
	
	return total[month-1];
}

// 요일체크를 위한 날짜 계산 (일요일부터 시작, 0 = 일요일)
public static int startDay(int year, int month, int day){
	// 1년 365일 (지난 년도)
	int daysum = (year-1)*365;
	// 윤년체크
	daysum += (year-1)/4 - (year-1)/100 + (year-1)/400 ;
	
	// 마지막해의 날짜 계산
	for(int i = 1; i < month; i++){
		daysum += totalDay(year, i);
	}
	
	return daysum + day;
}

// 해당 날짜의 요일을 찍는 부분
public static int chkDay(int year, int month, int day){
	return startDay(year, month, day) % 7;
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>달력 만들기</title>
</head>

<style>
	.beforeRed{color : #D8B8B8;}
	.beforeGrey{color : #B4B1B1;}
	.afterBlue{color : #BCC3E1;}
	.afterDay{color : #B4B1B1;}
	.dayhead{font-size: 2em;}
	.day{font-size: 1.5em;}
	.sun{color: red;}
	.sat{color: blue;}
	.month{
		width : 60px;
		height : 80px;
		text-align : center;
		font-size: 3.0em;
	}
</style>

<script type="text/javascript">
	function clicked(year){

		const dL = document.getElementById('dayList');
		dL.style.display='none';
		
		const dM = document.getElementById('monthList');
		dM.style.display='block';
	}
	
	function clickyear(year){
		//location.href='?year='+year;

		const dL = document.getElementById('dayList');
		dL.style.display='none';
		
		const dM = document.getElementById('monthList');
		dM.style.display='block';
		dM.load(window.location.href + " #monthList");
	
	}

	

</script>

<body>
	<h4>달력 만들기 입니다.</h4>
	<%
	Date date = new Date();
	int year = date.getYear()+1900;
	int month = date.getMonth() +1 ;
	
	
	try{
		year = Integer.parseInt(request.getParameter("year"));
		month = Integer.parseInt(request.getParameter("month"));
		
		if(month>=13){
			year++;
			month =1;
		}else if(month <=0){
			year--;
			month =12;
		}
	}catch(Exception e){
		
	}
	
	%>
	<div id="dayList">
		<tr>
			<th>
			<a id="title" onClick="clicked(<%=year %>)" ><%=year %>년 <%=month%>월 달력</a>
				<input type=button value="&#5123;" id="prevMonth" onclick="location.href='?year=<%=year%>&month=<%=month-1%>'">
				<input type=button value="&#5121;" id="nextMonth" onclick="location.href='?year=<%=year%>&month=<%=month+1%>'">
			</th>	
		</tr>
	<table border = "1">
			<tr class="dayhead">
				<td class ="sun">일</td>
				<td class ="mon">월</td>
				<td class ="tue">화</td>
				<td class ="wed">수</td>
				<td class ="thu">목</td>
				<td class ="fri">금</td>
				<td class ="sat">토</td>
			</tr>
			
			<%
			//1일의 요일
			int first = chkDay(year, month, 1);
			int end = 7-chkDay(year, month, totalDay(year, month));
			
			//1일 이전칸에 전 달의 날짜 넣기위해서...
			int start = 0;
			start = month == 1 ? totalDay(year-1, 12) - first : totalDay(year, month-1) - first;
			
			//전달의 날짜 넣는 부분
			for(int i = 1; i <= first; i++){
				if(i==1){
					out.println("<td class='beforeRed'>" + ++start + "</td>");
				}
				else{
					out.println("<td class='beforeGrey'>" + ++start + "</td>");
				}
			}

			%>
			
			
			<%
				for(int i = 1; i < totalDay(year, month)+1; i++){
					switch( chkDay(year, month, i) ){
						case 0 :
							out.println("<td class = 'sun day'>" + i + "</td>");
							break;
						case 1 :
							out.println("<td class = 'mon day'>" + i + "</td>");
							break;
						case 2 :
							out.println("<td class = 'tue day'>" + i + "</td>");
							break;
						case 3 :
							out.println("<td class = 'wed day'>" + i + "</td>");
							break;
						case 4 :
							out.println("<td class = 'thu day'>" + i + "</td>");
							break;
						case 5 :
							out.println("<td class = 'fri day'>" + i + "</td>");
							break;
						case 6 :
							out.println("<td class = 'sat day'>" + i + "</td><tr>");
							break;
					}
				}
			
			
			int fin = 0;
			
			for(int i = 1; i < end; i++){
				if(i == 6){
					out.println("<td class='afterBlue'>" + ++fin + "</td>");
				}
				else{
					out.println("<td class='afterDay'>" + ++fin + "</td>");
				}
			}

			%>
	</table>
	</div>



	<div id="monthList" style="display:none;">
		<tr>
			<th>
			<a id="title" onClick="clickedyear(<%=year %>)" ><%=year %>년</a>
				<input type=button value="&#5123;" id="prevMonth" onclick="clickyear(<%=year-1%>)">
				<input type=button value="&#5121;" id="prevMonth" onclick="clickyear(<%=year+1%>)">
			</th>	
		</tr>
		<table border="1">
		<%
				for(int i = 1; i <= 12; i++){
					if(i%4 ==0){
					%>
					<td class="month" onclick="location.href='?year=<%=year%>&month=<%=i%>'"><%=i%></td><tr>
					<%
					}
					else{
					%>
					<td class="month"  onclick="location.href='?year=<%=year%>&month=<%=i%>'"><%=i%></td>
					<%	
					}
				}
		 %>
		</table>
	</div>
	
	
	
	
</body>
</html>















