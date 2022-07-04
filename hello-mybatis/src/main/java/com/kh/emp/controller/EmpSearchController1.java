package com.kh.emp.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.common.AbstractController;
import com.kh.emp.model.service.EmpService;

public class EmpSearchController1 extends AbstractController {
	private EmpService empService;
	
	public EmpSearchController1(EmpService empService) {
		this.empService = empService;
	}
	
	@Override
	public String doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 1. 사용자 입력값 처리
		String searchType = request.getParameter("searchType");
		String searchKeyword = request.getParameter("searchKeyword");

		// 2. 업무로직
		List<Map<String, Object>> list = null;
		
		if(searchType == null || searchKeyword == null) {
			list = empService.selectList();			
//			System.out.println("EmpSearchController1@empMap = " + list.get(0));
//			System.out.println("EmpSearchController1@empMap = " + list.get(1));
		}
		else {
			Map<String, Object> param = new HashMap<>(); 
			param.put("searchType", searchType);
			param.put("searchKeyword", searchKeyword);
			System.out.println("EmpSearchController1@param = " + param);	
			list = empService.search1(param);
		}	
		
		// 3. view단 데이터 전달
		request.setAttribute("list", list);
		return "emp/search1";
	}

}
