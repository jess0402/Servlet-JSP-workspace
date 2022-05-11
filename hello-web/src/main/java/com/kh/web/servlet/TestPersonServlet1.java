package com.kh.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;  // tomcat이 제공
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 일반 클래스는 웹 요청을 처리할 수 없어서 extends HttpServlet 해줘야 한다.
 * 
 * Object
 * 	- GenericServlet
 * 		- HttpServlet 
 * 			- TestPersonServlet1
 * 
 * 사용자의 GET 방식 - doGet
 * 	- HttpServletRequest = req -> 사용자 요청 관련 정보를 가진 객체
 * 	- HttpServletResponse = res -> 응답을 준비하는 객체
 * 
 */
public class TestPersonServlet1 extends HttpServlet{
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		// 이 부분에 사용자 요청에 대한 응답처리를 작성하면 됨.
		System.out.println("GET /testPerson1.do 요청!");
		
		// 1. 사용자 입력값 처리
		// http://localhost:9090/web/testPerson1.do?name=장은성&color=빨강&animal=강아지&food=짜장면&food=짬뽕&food=탕수육
		// queryString ?name=장은성&color=빨강&animal=강아지&food=짜장면&food=짬뽕&food=탕수육
		String name = req.getParameter("name");
		String color = req.getParameter("color");
		String animal = req.getParameter("animal");
		String[] foods = req.getParameterValues("food");
		
		System.out.println("name = " + name);
		System.out.println("color = " + color);
		System.out.println("animal = " + animal);
		System.out.println("foods = " + foods != null ? Arrays.toString(foods) : "[]");
		
		// 2. 응답메세지 html
		// 응답메세지 MIME 타입
		resp.setContentType("text/html; charset=utf-8");
		//응답출력객체(character기반)
		PrintWriter out = resp.getWriter();
		out.println("<!doctype html");
		out.println("<html>");
		out.println("<head>");
		out.println("	<meta charset='utf-8'");
		out.println("	<title>개인취향검사결과 - GET</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("	<h1>개인취향검사결과 - GET</h1>");
		out.println("	<p>이름 : " + name + "</p>");
		out.println("	<p>선호컬러 : " + color + "</p>");
		out.println("	<p>선호동물 : " + animal + "</p>");
		out.println("	<p>선호음식 : ");
		for(String food : foods)
			out.println("<span>" + food + "</span>");
		out.println("</p>");
		out.println("</body>");
		out.println("</html>");
	}
}
