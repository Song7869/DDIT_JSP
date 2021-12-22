<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>03/requestDesc.jsp</title>
</head>
<body>
<h4>HttpServletRequest(request)</h4>
<pre>
	: 클라이언트와 해당 클라이언트로부터 발생한 요청에 대한 모든 정보를 캡슐화한 객체.
	Http 프로토콜의 요청 패키징 방식
	1. Request Line(서버의 자원과 자원을 대상으로 한 행위) : URL, Method
		Http Method : 요청의 목적(행위)과 패키징 방식 표현
		Get(Read)
		Post(Create)
		Put(Update)/Patch
		Delete(Delete)
		Option : preflight 요청을 통해 현재 요청의 메소드 지원여부를 확인.
		Head : 응답데이터의 body가 없는 메타 정보만을 받고싶을때.
		Trace : 서버를 디버깅.
		
		/member/memberInsert.do, /member/memberUpdate.do
		/member(GET), /member/a001(GET)
		/member(POST), /member/a001(PUT), /member/a001(DELETE)
		/member(DELETE) - RESTful URI
		
	2. Request Header : request 에 대한 meta data
	3. Request Body(Content Body, Message Body) : only POST
	
	GET 방식에서 서버로 데이터를 전송하기 위한 구조 : query String의 형태로 Request Line을 통해 전달
	Query String 구조
		URL?queryString(sections...)
			param_name=param_value&param_name=param_value 
	IETF 에서 정의한 RFC2396 규약에 따라
	클라이언트와 서버사이에 문자열이 전송될때, 
	해당 문자열은 URL encoding(Percent encoding) 방식으로 인코딩되어 전송됨.		
</pre>
</body>
</html>












