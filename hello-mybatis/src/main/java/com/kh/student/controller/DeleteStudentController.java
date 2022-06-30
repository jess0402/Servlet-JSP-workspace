package com.kh.student.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.kh.common.AbstractController;
import com.kh.student.model.service.StudentService;

public class DeleteStudentController extends AbstractController {

	private StudentService studentService;
	
	public DeleteStudentController(StudentService studentService) {
		this.studentService =  studentService;
	}
	
	@Override
	public String doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		int no = Integer.parseInt(request.getParameter("no"));
		System.out.println("DeleteStudentController@no = " + no);
		
		int result = studentService.deleteStudent(no);
		
		response.setContentType("application/json;charset=utf-8");
		String msg = "학생정보 삭제 완료!";
		new Gson().toJson(msg, response.getWriter());
		
		return null;
	}

}
