package com.kh.student.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.common.AbstractController;
import com.kh.student.model.dto.Student;
import com.kh.student.model.service.StudentService;

/**
 *  /mybatis/student/studentEnroll.do
 *  -> 위와 같이 요청이 들어오면 StudentEnrollController가 처리를 할 것임. 
 *  
 *  GET /mybatis/student/studentEnroll.do -> StudentEnrollController
 *  POST /mybatis/student/studentEnroll.do -> StudentEnrollController
 *
 */
public class StudentEnrollController extends AbstractController {
	private StudentService studentService;
	
	public StudentEnrollController(StudentService studentService) {
		super();
		this.studentService = studentService;
	}

	@Override
	public String doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println(studentService);
		return "student/studentEnroll";
	}
	
	@Override
	public String doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// db insert 관련
		
		// 1. 사용자 입력값 처리
		String name = request.getParameter("name");
		String tel = request.getParameter("tel");
		Student student = new Student(0, name, tel, null, null, null);
		
		System.out.println("StudentEnrollController@student = " + student);
		
		// 2. 업무로직
		int result = studentService.insertStudent(student);

		// 사용자피드백
		request.getSession().setAttribute("msg", "학생정보 등록 성공");
		
		return "redirect:/student/studentEnroll.do";
	}
}
