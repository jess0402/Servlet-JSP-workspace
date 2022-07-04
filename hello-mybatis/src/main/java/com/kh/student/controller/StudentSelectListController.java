package com.kh.student.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.common.AbstractController;
import com.kh.student.model.dto.Student;
import com.kh.student.model.service.StudentService;

public class StudentSelectListController extends AbstractController {
	
	private StudentService studentService;
	
	public StudentSelectListController(StudentService studentService) {
		super();
		this.studentService = studentService;
	}
	
	@Override
	public String doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 업무로직 - 전체학생 조회
		
		// 방법1. dto로 처리
		List<Student> studentList = studentService.selectStudentList();
		System.out.println("StudentSelectListController@studentList = " + studentList);
		
		// 방법2. map으로 처리
		// Map의 String = 컬럼명, Object = 컬럼값. Map 하나가 Student 하나와 똑같다 생각하면 됨.
		// 즉 현재 존재하는 학생수만큼 Map이 만들어지고, List에 담길 것. 
		List<Map<String, Object>> studentMapList = studentService.selectStudentMapList();
		System.out.println("StudentSelectListController@studentMapList = " + studentMapList);
		
		// view(jsp)단 전달
		request.setAttribute("studentList", studentList);
		request.setAttribute("studentMapList", studentMapList);
		

		return "student/selectList";
	}
	
}
