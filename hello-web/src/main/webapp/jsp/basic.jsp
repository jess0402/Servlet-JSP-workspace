<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ page import="java.util.*" %>
<% 
	// jsp는 동적으로 컴파일되므로, tomcat을 재시작할 필요가 없다ㅣ
	// scriptlet - 자바영역
	int sum = 0;
	for(int i = 1; i <= 100; i++)
		sum += i;
	System.out.println("sum@server-side = " + sum);
	
	// 서버 사이드 시각 출력
	long millis = System.currentTimeMillis();  // 현재 시스템 시각을 밀리초로 반환
	System.out.println("time@server-side = " + millis);
	
	int n = 100;
	
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>jsp기본</title>
</head>
<body>
	<h1>jsp기본</h1>
	
	<h2>server | client</h2>
	<p id="server-side-sum"><%= sum %></p>
	<p id="client-side-sum"></p>
	<br />
	<p id="server-side-time"><%= millis %></p>
	<p id="client-side-time"></p>
	<br />
	<p id="server-to-client"></p>
	
	<script>
		const clientSideSum = document.querySelector("#client-side-sum");
		let sum = 0;
		for(let i = 1; i <= 100; i++)
			sum += i;
		clientSideSum.innerHTML = sum;
		console.log("sum@client-side = ", sum);
		
		// 클라이언트 사이드 시각 출력 
		const clientTime = document.querySelector("#client-side-time");
		clientTime.innerHTML = new Date().getTime();
		
		// 서버단 처리값을 자바스크립트에서 활용하기 
		// 자바(서버단)에서 만들어진 값이 먼저 생성되어져서 자스에서 활용할 수 있는 것. 반대의 경우는 안됨.
		const k = 100 + <%= n %>;
		document.querySelector("#server-to-client").innerHTML = k;
	</script>	
	
	<h2>주석</h2>
	<%-- jsp주석 : servlet 변환과정에서 처리되지 않는다. 즉 클라이언트에 전달되지 않는다. --%>
	<!-- html주석 : 클라이언트에 전달이 된다. -->
	
	<h2>분기처리</h2>
<%
	String type = request.getParameter("type");
	System.out.println("type = " + type);
%>

<%
	if("abc".equals(type)){
%>
		<p>abcdefghijklmn</p>
<%
	} else if("가나다".equals(type)){
%>
		<p>가나다라마바사</p>
<%
	} else if("123".equals(type)){
%>
		<p>1234567</p>
<%
	} else{
%>
		<p>타입이 지정되지 않았습니다.</p>
<%
	}
%>

	<h2>반복처리</h2>
<%
	List<String> fruits = Arrays.asList("사과", "바나나", "아보카도", "키위");
%>

	<ul>
<%
	for(String fruit : fruits) {
%>
		<li><%= fruit %></li>
<%
	}
%>
	</ul>
	
	<h2>@실습문제</h2>	
<%
	if("abc".equals(type) || "가나다".equals(type)){
		String value = request.getParameter("value");
		
		if(value != null){
			String[] values = value.split(",");
%>
			<ul>
<%
			for(String val : values){
%>
				<li><%= val %></li>
<%
			}	
%>
			<ul>
<%
		}
		
	}
%>

	
</body>
</html>




