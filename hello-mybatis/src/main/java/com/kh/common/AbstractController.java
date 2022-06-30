package com.kh.common;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 추상클래스를 만드느 경우
 * - 1개 이상의 추상메서드를 가진 경우
 * - new 연산자 호출을 방지할 목적으로 abstract class 생성 가능
 */
public abstract class AbstractController {
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @return viewName:String -> forwaring할 jsp경로 또는 redirect할 location 값을 반환해줌
	 * @throws ServletException
	 * @throws IOException
	 * 
	 * 자식클래스에서 오버라이드해서 사용할 것. 
	 * 만약 오버라이드하지 않고 호출하면, MethodNotAllowedException이 발생하게 됨. 
	 * (오버라이드 안하면 물려받은 메서드를 그냥 사용하게 됨)
	 */
	public String doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		throw new MethodNotAllowedException("GET");
	}
	
	public String doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		throw new MethodNotAllowedException("POST");
	
	}
}
