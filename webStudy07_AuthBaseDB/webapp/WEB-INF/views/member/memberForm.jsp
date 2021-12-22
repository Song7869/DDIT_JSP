<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>	
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
	<form:form commandName="member" method="post" enctype="multipart/form-data" id="memberForm">
		<table>
			<tr>
				<th>회원아이디</th>
				<td><input type="text" name="memId" 
					value="${member.memId }" /> 
					<input type="button" value="아이디 중복 체크" id="checkBtn"/>
					<form:errors path="memId" element="span" cssClass="error" /></td>
			</tr>
			<tr>
				<th>비밀번호</th>
				<td><input type="text" name="memPass"  />
				<form:errors path="memPass" element="span" cssClass="error" /></td>
			</tr>
			<tr>
				<th>회원명</th>
				<td><input type="text" name="memName" 
					value="${member.memName }" /><form:errors path="memName" element="span" cssClass="error" /></td>
			</tr>
			<tr>
				<th>프로필이미지</th>
				<td>
					<input type="file" name="memImage" accept="image/*"/>
					<form:errors path="memImage" element="span" cssClass="error" />
				</td>
			</tr>
			<tr>
				<th>주민번호1</th>
				<td><input type="text" name="memRegno1" 
					value="${member.memRegno1 }" /><form:errors path="memRegno1" element="span" cssClass="error" /></td>
			</tr>
			<tr>
				<th>주민번호2</th>
				<td><input type="text" name="memRegno2" 
					value="${member.memRegno2 }" /><form:errors path="memRegno2" element="span" cssClass="error" /></td>
			</tr>
			<tr>
				<th>생일</th>
				<td><input type="date" name="memBir"
					value="${member.memBir }" /><form:errors path="memBir" element="span" cssClass="error" /></td>
			</tr>
			<tr>
				<th>우편번호</th>
				<td><input type="text" name="memZip" 
					value="${member.memZip }" /><form:errors path="memZip" element="span" cssClass="error" /></td>
			</tr>
			<tr>
				<th>주소1</th>
				<td><input type="text" name="memAdd1" 
					value="${member.memAdd1 }" /><form:errors path="memAdd1" element="span" cssClass="error" /></td>
			</tr>
			<tr>
				<th>주소2</th>
				<td><input type="text" name="memAdd2" 
					value="${member.memAdd2 }" /><form:errors path="memAdd2" element="span" cssClass="error" /></td>
			</tr>
			<tr>
				<th>집전번</th>
				<td><input type="text" name="memHometel" 
					value="${member.memHometel }" /><form:errors path="memHometel" element="span" cssClass="error" /></td>
			</tr>
			<tr>
				<th>회사전번</th>
				<td><input type="text" name="memComtel" 
					value="${member.memComtel }" /><form:errors path="memComtel" element="span" cssClass="error" /></td>
			</tr>
			<tr>
				<th>휴대폰</th>
				<td><input type="text" name="memHp"
					value="${member.memHp }" /><form:errors path="memHp" element="span" cssClass="error" /></td>
			</tr>
			<tr>
				<th>이메일</th>
				<td><input type="text" name="memMail" 
					value="${member.memMail }" /><form:errors path="memMail" element="span" cssClass="error" /></td>
			</tr>
			<tr>
				<th>직업</th>
				<td><input type="text" name="memJob"
					value="${member.memJob }" /><form:errors path="memJob" element="span" cssClass="error" /></td>
			</tr>
			<tr>
				<th>취미</th>
				<td><input type="text" name="memLike"
					value="${member.memLike }" /><form:errors path="memLike" element="span" cssClass="error" /></td>
			</tr>
			<tr>
				<th>기념일</th>
				<td><input type="text" name="memMemorial"
					value="${member.memMemorial }" /><form:errors path="memMemorial" element="span" cssClass="error" /></td>
			</tr>
			<tr>
				<th>기념일자</th>
				<td><input type="date" name="memMemorialday"
					value="${member.memMemorialday }" /><form:errors path="memMemorialday" element="span" cssClass="error" /></td>
			</tr>
			<tr>
				<th>마일리지</th>
				<td>3000</td>
			</tr>
			
			<tr>
				<td colspan="2">
					<input type="submit" value="저장" class="btn btn-success" />
					<input type="reset" value="취소" class="btn btn-danger"/>
					<security:authorize url="/member/memberList.do">
						<input type="button" value="목록으로" class="btn btn-danger linkBtn" data-gopage="${cPath }/member/memberList.do"/>
					</security:authorize>
				</td>
			</tr>
		</table>
	</form:form>
	<c:if test="${command eq 'INSERT' }">
		<script type="text/javascript" src="${cPath }/resources/js/member/memberForm.js" ></script>
	</c:if>
















