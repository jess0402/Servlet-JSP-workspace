<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>jquery - xml</title>
<script src="<%= request.getContextPath() %>/js/jquery-3.6.0.js"></script>
<style>
table {border : 1px solid #000; border-collapse: collapse; margin: 10px 0;}
th, td {border : 1px solid #000; text-align: center; padding: 3px; }
table img {width: 100px;}
</style>
</head>
<body>
	<h1>xml</h1>
	<button id="btn1">sample</button>
	<table id="tbl-books">
		<thead>
			<tr>
				<th>분류</th>
				<th>제목</th>
				<th>저자</th>
			</tr>
		</thead>
		<tbody></tbody>
	</table>
	<script>
		btn1.addEventListener('click', (e) => {
			$.ajax({
				url : "<%= request.getContextPath() %>/jquery/sample.xml",
				method : "GET",
				dataType: "xml",
				success(doc){  // xmldocument가 넘어옴
					// 응답받은 xml을 jquery가 parsing처리 후 DOM으로 전달
					console.log(doc);
					console.dir(doc); // document
					
					const root = doc.querySelector(":root");
					console.log(root);
					
					// 사용자 속성 가져오기
					// console.log(root.myattr); // undefined로 나옴. 
					// root.attributes(NamedNodeMap타입) 속성에서 관리됨.
					console.log(root.getAttribute("myattr")); // hello로 제대로 나옴
					
					const books = [...root.children]; // Array.from(유사배열)
					// console.log("books", books);
					
					const tbody = document.querySelector('#tbl-books tbody');
					tbody.innerHTML = "";
					books.forEach((book) => {
						
						const [subject, title, author] = book.children;
						console.log(subject, title, author);
						
						tbody.innerHTML += `<tr>
							<td>\${subject.textContent}</td>
							<td>\${title.textContent}</td>
							<td>\${author.textContent}</td>
						</tr>`;
						
					});
				},
				error : console.log
			});
		});
	
	</script>
	
	<hr />
	
	<button id="btn2">celeb</button>
	<table id="tbl-celeb">
		<thead>
			<tr>
				<th>NO</th>
				<th>이름</th>
				<th>타입</th><!-- select태그 하위에 해당타입이 selected 처리 -->
				<th>프로필</th><!-- img태그처리 -->
			</tr>
		</thead>
		<tbody></tbody>
	</table>
	<script>
	btn2.addEventListener('click', () => {
		$.ajax({
			url : "<%= request.getContextPath() %>/jquery/xml",
			// dataType : "xml", // 응답데이터를 보고 자동으로 지정
			success(doc){
				console.log(doc);
				const root = doc.querySelector(":root");
				console.log(`총 \${root.getAttribute("len")}개의 데이터가 조회되었습니다.`);
				
				const celebs = [...root.children];
				document.querySelector("#tbl-celeb tbody").innerHTML = 
					celebs.reduce((html, celeb) => {
						const [name, type, profile] = celeb.children;  // iterator를 상속(진짜배열, 유사배열)
						const tr = `<tr>
							<td>\${celeb.getAttribute("no")}</td>
							<td>\${name.textContent}</td>
							<td>\${type.textContent}</td>
							<td>
								<img src="<%= request.getContextPath() %>/images/\${profile.textContent}"/>
							</td>
						</tr>`;
						return html + tr;
					}, "");
			},
			error : console.log
		});
	});
	</script>
	
	<hr />
	
	<h2>일일 박스오피스 조회</h2>
	<div><input type="date" name="targetDt" id="targetDt" /></div>
	<table id="tbl-daily-boxoffice">
		<thead>
			<tr>
				<th>순위</th>
				<th>영화제목</th>
				<th>누적관객수(만)</th>
			</tr>
		</thead>
		<tbody></tbody>
	</table>
	<script>
		/**
		* @실습문제 - 페이지로딩이 완료되면 어제날짜로 박스오피스 조회를 자동으로 처리.
		* 
		*/
		$(document).ready(function(){
			const today = new Date();
			const yesterday = new Date(today.setDate(today.getDate() - 1));
			
			const year = yesterday.getFullYear();
			const month = ("0" + (1 + yesterday.getMonth())).slice(-2);
			const day = ("0" + yesterday.getDate()).slice(-2);
			
			const date = year + month + day;
			
			$("input[name=targetDt]").val(year+"-"+month+"-"+day);
			show(date);
			
			
		});
		
		targetDt.addEventListener('change', (e) => {
			const val = e.target.value.replace(/-/g, ""); // -를 여러건 제거한다.
			console.log(val);
			
			show(val);
		});
		
		const show = (date) => {
			$.ajax({
				url : "http://www.kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.xml",
				data : {
					key : 'f6d379a954593d8e4202f3318b01c062',
					targetDt : date
				},
				success(doc){
					const root = doc.querySelector(":root");
					const movies = [...root.lastElementChild.children];
					
					const tbody = document.querySelector("#tbl-daily-boxoffice tbody");
					tbody.innerHTML = "";
					
					movies.forEach((movie) => {
						
						const rank = movie.getElementsByTagName("rank")[0].textContent;
						const movieNm = movie.getElementsByTagName("movieNm")[0].textContent;
						const audiAcc = movie.getElementsByTagName("audiAcc")[0].textContent;
						
						tbody.innerHTML += `<tr>
							<td>\${rank}</td>
							<td>\${movieNm}</td>
							<td>\${audiAcc}</td>
						</tr>`;
						
					});
					
				},
				error : console.log
			});
		}
	</script>
	
	
	<br />
	<br />
	<br />
	<br />
	<br />
	<br />
	<br />
	<br />
	<br />
	<br />
	<br />
	<br />
	<br />
	<br />
	<br />
	<br />
	<br />
	<br />
	<br />
	<br />
	
</body>
</html>