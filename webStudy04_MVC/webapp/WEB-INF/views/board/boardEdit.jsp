<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="/includee/preScript.jsp" />
</head>
<body>
	<form method="post" enctype="multipart/form-data" id="editForm">
		<input type="hidden" name="boNo" value="${board.boNo }" />
		<input type="hidden" name="boParent" value="${board.boParent }" />
		<table>
			<tr>
				<th>글번호</th>
				<td>${board.boNo }
					<span class="error">${errors.boNo }</span></td>
			</tr>
			<tr>
				<th>제목</th>
				<td><input type="text" name="boTitle" required value="${board.boTitle }" />
					<span class="error">${errors.boTitle }</span></td>
			</tr>
			<tr>
				<th>작성자</th>
				<td><input type="text" name="boWriter" required value="${board.boWriter }" />
					<span class="error">${errors.boWriter }</span></td>
			</tr>
			<tr>
				<th>비밀번호</th>
				<td><input type="text" name="boPass" required />
				<span class="error">${errors.boPass }</span></td>
			</tr>
			<tr>
				<th>아이피</th>
				<td><input type="text" readonly name="boIp" required value="${pageContext.request.remoteAddr }" />
				<span class="error">${errors.boIp }</span></td>
			</tr>
			<tr>
				<th>이메일</th>
				<td><input type="text" name="boMail" value="${board.boMail }" />
					<span class="error">${errors.boMail }</span></td>
			</tr>
			<tr>
				<th>기존 첨부파일</th>
				<td>
					<c:forEach items="${board.attatchList }" var="atch">
						<span data-atch="${atch.attNo }">
							${atch.attFilename }
							<input type="button" value="삭제" class="atchDelBtn"/>
						</span>
					</c:forEach>
				</td>
			</tr>
			<tr>
				<th>첨부파일</th>
				<td id="fileArea">
					<div>
						<input type="file" name="boFiles" multiple />
						<input type="button" value="추가" id="plus" class="ctlBtn"/>
						<input type="button" value="제거" id="minus" class="ctlBtn"/>
					</div>
				</td>
			</tr>
			<tr>
				<th>내용</th>
				<td>
					<textarea rows="5" cols="100" name="boContent" id="boContent">${board.boContent }</textarea>
					<span class="error">${errors.boContent }</span></td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="submit" value="저장" />
					<input type="reset" value="취소" />
				</td>
			</tr>
		</table>
		
		
	</form>
	<script type="text/javascript">
		let editForm = $("#editForm");
		$(".atchDelBtn").on("click", function(){
			let span = $(this).closest("span");
			span.hide();
			let attNo = span.data("atch");
			editForm.append(
				$("<input>").attr({
					type:"text",
					name:"delAttNos"
				}).val(attNo)
			);
		});
	
		let fileArea = $("#fileArea").on("click", ".ctlBtn", function(){
			let id = this.id;
			console.log(id);
			let divTag = $(this).closest("div");
			if(id == 'plus'){
				let clone = divTag.clone();
				$(clone).find(":input[name]").val("");
				divTag.after(clone);
			}else{
				let divs = fileArea.find("div");
				if(divs.length>1)
					divTag.remove();
			}
		});
	</script>
<jsp:include page="/includee/postScript.jsp" />	
</body>
</html>










