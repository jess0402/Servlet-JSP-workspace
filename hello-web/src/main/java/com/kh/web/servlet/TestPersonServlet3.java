package com.kh.web.servlet;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TestPersonServlet3
 */
@WebServlet("/testPerson3.do")
public class TestPersonServlet3 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 인코딩 처리
		request.setCharacterEncoding("utf-8");
		
		// 2. 사용자 입력값 처리
		String name = request.getParameter("name");
		String color = request.getParameter("color");
		String animal = request.getParameter("animal");
		String[] foods = request.getParameterValues("food"); 
		// 복수개의 값이 넘어오면 getParameterValues 사용 		
		// 잘 넘어왔나 확인 
		System.out.println(name + ", " + color + ", " + animal + ", " + (foods != null ? Arrays.toString(foods) : null));

		// 3. 업무로직 
		String src = "/web/images/";
		
		switch(animal) {
		case "강아지" : src += "dog.png"; break;
		case "고양이" : src += "cat.png"; break;
		case "물고기" : src += "fish.png"; break;
		}
		
		// 4. 응답메세지 작성(jsp(view)에 위임) - view: 사용자가 보게 될 화면 담당
		// servlet에서 생성한 데이터는 별도로 request의 속성으로 전달
		request.setAttribute("src", src);
		RequestDispatcher reqDispatcher = request.getRequestDispatcher("/servlet/testPersonResult.jsp");
		reqDispatcher.forward(request, response);
	}

}
