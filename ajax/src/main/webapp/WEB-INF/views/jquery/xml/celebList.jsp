<?xml version="1.0" encoding="UTF-8" ?>
<%@page import="com.kh.ajax.celeb.dto.Celeb"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/xml; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	List<Celeb> celebList = (List<Celeb>) request.getAttribute("celebList");
%>

<celebs len="<%= celebList.size() %>"> <!-- 부가적으로 필요한 정보가 있다면 맨 위 태그에 적어주기 -->
	<% for(Celeb celeb : celebList) { %>
	<celeb no="<%= celeb.getNo() %>">
		<name><%= celeb.getName() %></name>
		<type><%= celeb.getType() %></type>
		<profile><%= celeb.getProfile() %></profile>
	</celeb>
	<% } %>
</celebs>
