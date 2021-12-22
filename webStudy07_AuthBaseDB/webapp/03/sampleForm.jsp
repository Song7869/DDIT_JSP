<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>03/sampleForm.jsp</title>
</head>
<body>
<h4>입력 양식</h4>
<form action="${pageContext.request.contextPath }/sampleProcess.do" method="get">
<pre>
	<input type="text"  name="textParam" required/>
	<input type="number"  name="numberParam" required/>
	<input type="hidden"  name="hiddenParam" value="hiddenText"/>
	<label><input type="radio" name="radioParam" value="radio1"/>Radio1</label> <label><input type="radio" name="radioParam"  value="radio2"/>Radio2</label>
	<input type="checkbox" name="checkParam" value="check1"/>Check1
	<input type="checkbox" name="checkParam" value="check2"/>Check2
	<input type="checkbox" name="checkParam" value="check3"/>Check3
	<select name="singleSelect" required>
		<option value>선택</option>
		<option>text1</option>
		<option>text2</option>
		<option>text3</option>
	</select>
	<select name="multiSelect" multiple size="10">
		<option>text1</option>
		<option>text2</option>
		<option>text3</option>
	</select>
	<input type="button" value="버튼" />
	<input type="submit"  value="전송"/>
	<input type="reset"  value="취소"/>
</pre>
</form>
</body>
</html>






