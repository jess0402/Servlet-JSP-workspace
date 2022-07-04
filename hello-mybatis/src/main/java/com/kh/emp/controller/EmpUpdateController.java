package com.kh.emp.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.common.AbstractController;
import com.kh.emp.model.service.EmpService;

public class EmpUpdateController extends AbstractController {
	private EmpService empService;
	
	public EmpUpdateController(EmpService empService) {
		this.empService = empService;
	}
	
	@Override
	public String doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 사용자 입력값 처리 - empId
		String empId = request.getParameter("empId");
		System.out.println("empId = " + empId);
		
		// 2. 업무로직 selectOne
		Map<String, Object> emp = empService.selectOne(empId);
		System.out.println("emp = " + emp);
		
		// select-option 태그용 job, dept 정보
		request.setAttribute("jobList", empService.selectJobList());
		request.setAttribute("deptList", empService.selectDeptList());
		
		// 3. view단 데이터 전송
		request.setAttribute("emp", emp);
		return "emp/empUpdate";
	}
	
	@Override
	public String doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// 1. 사용자 입력값 처리
		String empId = request.getParameter("empId");
		String jobCode = request.getParameter("jobCode");
		String deptCode = request.getParameter("deptCode");
		Map<String, Object> param = new HashMap<>();
		param.put("empId", empId);
		param.put("jobCode", jobCode);
		param.put("deptCode", deptCode);
		System.out.println("param = " + param);
		
		// 2. 업무로직
		int result = empService.updateEmp(param);
		return "redirect:/emp/empUpdate.do?empId=" + empId;
	}

}
