<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="<%= request.getContextPath() %>/js/jquery-3.6.0.js"></script>
</head>
<body>
	<h1>text</h1>
	<button id="btn1">text</button>
	<script>
	btn1.addEventListener('click', (e) => {
		$.ajax({
			url : "<%= request.getContextPath() %>/jquery/text",
			method : "GET",  // 전송방식 GET(기본값) | POST | PUT | PATCH ...
			data : { // 사용자 입력값 부분. 
				// 직렬화 처리 후GET 방식일 경우 url에 추가, POST일 경우 body 부분에 작성
				q : "abcde",
				mode : 123,
				isFinal : true
			}, // 전달할 값이 있다면 data 속성에 객체 형태로 주면 됨. -> queryString으로 처리가 될 것. 
			dataType : "text", // 응답메세지의 타입 지정 -> text | html | script | json | xml 
			beforeSend(){
				// 요청전 호출메서드(무조건 호출)
				console.log("beforeSend");
			},
			success(responseText){
				// 성공시 호출
				// xhr.responseText를 후처리 후 success 메서드에 전달!
				// readyState = 4 && status = 200
				console.log("success : ", responseText);
			},
			error(jqxhr, textStatus, err){
				// 실패시 호출
				// readyState = 4 && status != 200
				console.log("error: ", jqxhr, textStatus, err);
			},
			complete(){
				// 응답후(성공이든 실패든) 반드시 실행하는 메서드(무조건 호출)
				console.log("complete");
			}
		});
	});
	</script>
	
	<button id="btn2">csv</button>
	<table id="tbl-celeb">
		<thead>
			<tr>
				<th>NO</th>
				<th>이름</th>
				<th>타입</th> <!-- select태그 하위에 해당타입이 selected 처리 -->
				<th>프로필</th> <!-- img태그처리 -->
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>0</td>
				<td>이준영</td>
				<td>
					<select name="type" id="type">
						<option value="Comedian">코미디언</option>
						<option value="Actor" selected>배우</option>
						<option value="Singer">가수</option>
						<option value="Model">모델</option>					
						<option value="Entertainer">엔터테이너</option>					
					</select>
				</td>
				<td><img src="<%= request.getContextPath() %>/images/jun.jpg" style="width:200px;"/></td>
				
			</tr>
		</tbody>
	</table>
	<script>
	/**
	* csv - comma separated value
	*/
	btn2.addEventListener('click', (e) => {
		$.ajax({
			url : "<%= request.getContextPath() %>/jquery/csv",
			method : "GET",
			dataType : "text",
			success(response){
				const celeb = response.split("\r\n");
				console.log(celeb);
				
				const arr = celeb[0].split(",");
				
				const tr = `
					<tr>
						<td>\${arr[0]}</td>
						<td>\${arr[1]}</td>
						<td><select name="type" id="type">
							<option value="COMEDIAN">코미디언</option>
							<option value="ACTOR">배우</option>
							<option value="SINGER">가수</option>
							<option value="MODEL">모델</option>					
							<option value="ENTERTAINER">엔터테이너</option>					
						</select></td>
						<td><img src="<%= request.getContextPath() %>/images/\${arr[3]}" style="width:200px;"/></td>
					</tr>`
				
				const no = `<td>\${arr[0]}</td>`;
				const name = `<td>\${arr[1]}</td>`;
				const type = 
					`<td><select id="type">
						<option value="COMEDIAN">코미디언</option>
						<option value="ACTOR">배우</option>
						<option value="SINGER">가수</option>
						<option value="MODEL">모델</option>					
						<option value="ENTERTAINER">엔터테이너</option>					
					</select></td>`;
				const img = `<td><img src="<%= request.getContextPath() %>/images/\${arr[3]}" style="width:200px;"/></td>`;
				$("tbody")
					.append(tr);
				
				$("#type")
					.val("SINGER")
					.prop("selected", true);

				
				
			},
			error(xhr, textStatus, err){
				console.log("error : ", xhr, textStatus, err);
			}
		});
	});
	
	</script>
	
</body>
</html>