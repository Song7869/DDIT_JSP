<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/jquery-3.6.0.min.js"></script>
</head>
<body>
<form name="testForm" action="${pageContext.request.contextPath }/marshallingTest.do" method="post">
	<input type="text" name="p1" placeholder="p1"/>
	<input type="text" name="p1" placeholder="p1"/>
	<input type="number" name="p2" placeholder="p2"/>
	<input type="number" name="p2" placeholder="p2"/>
	<select name="p3">
		<option>o1</option>
		<option>o2</option>
	</select>
	<input type="submit" value="전송" />
</form>
<script type="text/javascript">
	let nameSet = new Set();
	$(document.testForm).on("submit", function(event){
		event.preventDefault();
		let form = this;
		let url = this.action;
		let method = this.method;
// 		let data = $(this).serialize();
// 		let data = {
// 			p1:['text', 'text'],
// 			p2:[23,34],
// 			p3:'o2'		
// 		}
		let data = {};
		
		for(let name of nameSet){
			let children = $(form).find('[name="'+name+'"]');
			let value = null;
			if(children.length>1){
				value = [];
				$(children).each(function(index, child){
					if(child.type=='number'){
						value.push( parseInt($(this).val()) );
					}else{
						value.push( $(this).val() );
					}
				});
			}else{
				value = $(children).val();
			}
			data[name] = value;
		}
		console.log(data);
		let jsonData = JSON.stringify(data);
		$.ajax({
			url : url,
			data : jsonData,
			method : "post",
			contentType : "application/json;charset=UTF-8"
// 			dataType : "json",
// 			success : function(resp) {

// 			},
// 			error : function(xhr, errorResp, error) {
// 				console.log(xhr);
// 				console.log(errorResp);
// 				console.log(error);
// 			}
		});
		
// 		let data = $(this).serialize();
// 		console.log(data);
// p1=text&p1=text&p2=23&p2=34&p3=o2
// 	let data = {
// 		p1:['text', 'text'],
// 		p2:[23,34],
// 		p3:'o2'		
// 	}
// 	let jsonData = JSON.stringify(data);
// 	console.log(jsonData);
// p1=text&p2=3&p3=o1
// 		let data = {
// 			p1:"text",
// 			p2:3,
// 			p3:"o1"
// 		}
		return false;
	}).find(":input[name]").each(function(index, input){
		nameSet.add(input.name);
		$(input).prop("required", true);
	});





// 	let obj = {
// 		prop1:23,
// 		prop2:"text"
// 	};
// 	// dot notation
// 	obj.prop3 = "text3";
// 	// 연상배열(연관배열) 구조
// 	obj['prop4'] = "text4";
// 	console.log(obj);
	
// 	for(let name in obj){
// 		let propValue = obj[name];
// 		console.log(name + " : " + propValue);
// 	}
	
// 	let array = ['a'];
// 	array.push('b');
// 	console.log(array.length);
// 	for(let index in array){
// 		console.log(index);
// 	}
// 	for(let element of array){
// 		console.log(element);
// 	}
	
// 	let testSet = new Set();
// 	let testMap = new Map();
// 	testSet.add('a');
// 	testMap.set('key1', 'value1');
// 	for(let tmp of testSet){
// 		console.log(tmp);
// 	}
// 	for(let tmp of testMap){
// 		console.log(tmp);
// 	}
</script>
</body>
</html>











