package com.kh.student.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.kh.common.AbstractController;
import com.kh.student.model.service.StudentService;

public class StudentTotalCountController extends AbstractController {

	private StudentService studentService;	
	
	public StudentTotalCountController(StudentService studentService) {
		this.studentService = studentService;
	}
	
	@Override
	public String doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 업무로직
		int totalCount = studentService.getTotalCount();
		System.out.println("StudentTotalCountController@totalCount = " + totalCount);
		
		// 응답작성 - 비동기 json 응답을 직접 작성한다.
		response.setContentType("application/json;charset=utf-8");
		Map<String, Object> map = new HashMap<>();
		map.put("totalCount", totalCount);
		new Gson().toJson(map, response.getWriter());
		
		return null;
	}

}
