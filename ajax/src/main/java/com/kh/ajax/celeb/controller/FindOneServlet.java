package com.kh.ajax.celeb.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.kh.ajax.celeb.dto.Celeb;
import com.kh.ajax.celeb.manager.CelebManager;

/**
 * Servlet implementation class FindOneServlet
 */
@WebServlet("/celeb/findOne")
public class FindOneServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 사용자 입력값 처리
		int no = Integer.parseInt(request.getParameter("no"));
		
		// 2. 업무로직
		CelebManager manager = CelebManager.getInstance();
		Celeb selectedCeleb = manager.getCelebList().get(no-1);
		
		// 업무로직 - 강사님 코드
//		List<Celeb> celebList = CelebManager.getInstance().getCelebList();
//		Celeb celeb = null;
//		for(Celeb c : celebList) {
//			if(c.getNo() == no)
//				celeb = c;
//		}
		
		// 3. 응답처리 - json으로 변환해서 출력
		response.setContentType("application/json; charset=utf-8");
		Gson gson = new Gson();
		String jsonStr = gson.toJson(selectedCeleb);
		System.out.println("jsonStr = " + jsonStr);
		response.getWriter().append(jsonStr);
		
		// 응답처리 - 강사님 코드
//		response.setContentType("application/json; charset=utf-8");
//		response.getWriter().append(new Gson().toJson(celeb));  -- 1번
//		new Gson().toJson(celeb, response.getWriter()); -- 2번
		// 1번과 2번은 같은 코드임. 둘 중 하나만 쓰면 됨.
		
	}

}
