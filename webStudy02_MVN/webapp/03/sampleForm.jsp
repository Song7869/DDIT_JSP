<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h4>입력 양식</h4>
		<form action="<%=request.getContextPath()%>/sampleProcess.do" method="get">
			<pre>
				textParam(r) : <input type="text" name="textParam" required/>
				numberParam(r) : <input type="number" name="numberParam" required/>
				hiddenParam : <input type="hidden" name="hiddenParam" value="hiddenText"/>
				
				label : <label><input type="radio" name="radioParam"" value="radio1"/>radio1</label><label><input type="radio" name="radioParam" value="radio2"/>radio2</label>
				
				<input type="checkbox" name="checkParam" value="check1"/>check1
				<input type="checkbox" name="checkParam" value="check2"/>check2
				<input type="checkbox" name="checkParam" value="check3"/>check3
				<input type="checkbox" name="checkParam" value="check4"/>check4
				
				<select name="singleSelect" required>
					<option value>선	택</option>
					<option>text1</option>
					<option>text2</option>
					<option>text3</option>
				</select>
				
				<select name="multiSelect" multiple size="10">
					<option>text1</option>
					<option>text2</option>
					<option>text3</option>
				</select>
				
				<input type="button" value="버튼"/>
				<input type="submit" value="전송"/>
				<input type="reset" value="취소"/>
			</pre>
		</form>
</body>
</html>