<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="<%= request.getContextPath() %>/js/jquery-3.6.0.js"></script>
<style>
table {border : 1px solid #000; border-collapse: collapse; margin: 10px 0;}
th, td {border : 1px solid #000; text-align: center; padding: 3px; }
table img {width: 100px;}
</style>
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
		</thead><tbody>
		</tbody>
	</table>
	<script>
	/**
	 * csv comma separated value
	 * 
	 */
	btn2.addEventListener('click', (e) => {
		$.ajax({
			url : "<%= request.getContextPath() %>/jquery/csv",
			method : "GET",
			dataType : "text", 
			success(response){
				console.log(response);
				const celebStrs = response.split("\r\n"); // 개행문자 단위로 잘라냄
				const tbody = document.querySelector("#tbl-celeb tbody");
				tbody.innerHTML = "";  // 두 번 실행했을 때 길어지지 않도록 tbody 초기화
				
				celebStrs.forEach((celebStr) => { 
					if(celebStr === '') return; // 마지막 ''
					// -> celebStr이 빈문자열이라면 더이상 진행하지 마라
					const celeb = celebStr.split(",");
					// console.log(celeb); 
					const tr = document.createElement("tr");
					const tdNo = document.createElement("td");
					tdNo.append(celeb[0]);
					const tdName = document.createElement("td");
					tdName.append(celeb[1]);
					const tdType = document.createElement("td");
					const select = document.createElement("select");
					// ACTOR, SINGER, MODEL, COMEDIAN, ENTERTAINER;
					const option1 = document.createElement("option")
					option1.innerHTML = "ACTOR";
					const option2 = document.createElement("option")
					option2.innerHTML = "SINGER";
					const option3 = document.createElement("option")
					option3.innerHTML = "MODEL";
					const option4 = document.createElement("option")
					option4.innerHTML = "COMEDIAN";
					const option5 = document.createElement("option")
					option5.innerHTML = "ENTERTAINER";
					select.append(option1, option2, option3, option4, option5);
					select.value = celeb[2];
					// select 값 변경 하지 못하게 하는 방법
					// 방법 1
					// select.disabled = "disabled";
					// 방법 2
					select.dataset.value = celeb[2]; // dataset에 속성으로 원래값들을 담아두고
					select.onchange = function(){
						this.value = this.dataset.value;
						// 값이 바뀔려고 하면 이전 값들로 되돌려놓는다.
					};
					tdType.append(select);
					const tdProfile = document.createElement("td");
					const img = document.createElement("img");
					img.src = `<%= request.getContextPath() %>/images/\${celeb[3]}`
					tdProfile.append(img);
					tr.append(tdNo, tdName, tdType, tdProfile);
					tbody.append(tr);
				});
			},
			error(xhr, textStatus, err){
				console.log("error : ", xhr, textStatus, err);
			}
			
		});
	});
	
	
	</script>
	
</body>
</html>