<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>jquery - html</title>
<script src="<%= request.getContextPath() %>/js/jquery-3.6.0.js"></script>
<style>
table {border : 1px solid #000; border-collapse: collapse; margin: 10px 0;}
th, td {border : 1px solid #000; text-align: center; padding: 3px; }
table img {width: 100px;}
</style>
</head>
<body>
	<h1>html</h1>
	<button id="btn1">html</button>
	<table id="tbl-celeb">
		<thead>
			<tr>
				<th>NO</th>
				<th>이름</th>
				<th>타입</th> <!-- select태그 하위에 해당타입이 selected 처리 -->
				<th>프로필</th> <!-- img태그처리 -->
			</tr>
		</thead><tbody>
		</tbody>
	</table>
	<script>
	btn1.addEventListener('click', () => {
		$.ajax({
			url: "<%= request.getContextPath() %>/jquery/html",
			method : "GET",  // 생략가능
			dataType : "html",  // 생략가능
			success(response){
				console.log(response);	
				document.querySelector("#tbl-celeb tbody").innerHTML = response;
			},
		//	error(jqxhr, textStatus, err){
		//		console.log(jqxhr, textStatus, err);
		//	}
			error : console.log  // 위 세 줄의 코드를 이렇게 쓸수도 있음.
			
		});	
	});
	
	</script>
</body>
</html>