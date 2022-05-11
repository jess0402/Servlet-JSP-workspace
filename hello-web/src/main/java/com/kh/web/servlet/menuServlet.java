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
@WebServlet("/menu.do")
public class menuServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 인코딩 처리
		request.setCharacterEncoding("utf-8");
		
		// 2. 사용자 입력값 처리
		String mainMenu = request.getParameter("mainMenu");
		String sideMenu = request.getParameter("sideMenu");
		String drinkMenu = request.getParameter("drinkMenu"); 
		// 복수개의 값이 넘어오면 getParameterValues 사용 		
		// 잘 넘어왔나 확인 
		System.out.println(mainMenu + ", " + sideMenu + ", " + drinkMenu);

		// 3. 업무로직 
		int price = 0;
		
		switch(mainMenu) {
		case "한우버거" : price += 5000; break;
		case "밥버거" : price += 4500; break;
		case "치즈버거" : price += 4000; break;
		}
		
		switch(sideMenu) {
		case "감자튀김" : price += 1500; break;
		case "어니언링" : price += 1700; break;
		}
		
		switch(drinkMenu) {
		case "콜라" : price += 1000; break;
		case "사이다" : price += 1000; break;
		case "커피" : price += 1500; break;		
		case "밀크쉐이크" : price += 2500; break;		
		}
		
		// 4. 응답메세지 작성(jsp(view)에 위임) - view: 사용자가 보게 될 화면 담당
		// servlet에서 생성한 데이터는 별도로 request의 속성으로 전달
		request.setAttribute("price", price);
		RequestDispatcher reqDispatcher = request.getRequestDispatcher("/menu/menuEnd.jsp");
		reqDispatcher.forward(request, response);
	}

}
