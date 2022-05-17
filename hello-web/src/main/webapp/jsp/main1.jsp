<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/jsp/header.jsp" %>
<!-- header와 footer를 main1과 main2가 공유할 수 있음 -->

		<h1>main1</h1>
		<p><%= name %>님, 반갑습니다.</p>
		<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Nihil omnis aspernatur dolor et dolorem eos eaque minus. Omnis dolorum officia illum autem molestias facilis officiis beatae quae qui esse fugiat.</p>

		<script>
			const title = document.querySelector("header h1").innerHTML;
			alert(title);
		</script>
		
<%@ include file="/jsp/footer.jsp" %>
