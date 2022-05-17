package member.controller;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.dto.Member;
import member.model.service.MemberService;

/**
 * Servlet implementation class MemberUpdateServlet
 */
@WebServlet("/member/memberUpdate")
public class MemberUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberService memberService = new MemberService();

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			// 1. 인코딩 처리 
			request.setCharacterEncoding("utf-8");
			
			// 2. 사용자 입력값 처리 (자바변수에 옮겨담기)
			String memberId = request.getParameter("memberId");
			String memberName = request.getParameter("memberName");
			String gender = request.getParameter("gender");
			String email = request.getParameter("email");
			String phone = request.getParameter("phone");
			String address = request.getParameter("address");
			
			// birthday는 java.sql.Date이므로 후처리가 필요함
			String _birthday = request.getParameter("birthday");
			Date birthday = null;
			if(_birthday != null && !"".equals(_birthday)) {
				birthday = Date.valueOf(_birthday);
			}
			
			// hobby는 배열이므로 하나의 문자열로 바꾸는 후처리가 필요함.
			String[] _hobby = request.getParameterValues("hobby");
			String hobby = null;
			if(_hobby != null) {
				hobby = String.join(",", _hobby);
			}
			
			Member member = 
					new Member(memberId, null, memberName, null, gender, 
							birthday, email, phone, address, hobby, null);
			System.out.println("member@MemberUpdateServlet = " + member);
			
			// 3. 업무로직 (service에 updateMember요청)
			int result = memberService.updateMember(member);
			String msg = "회원정보를 성공적으로 수정했습니다.";
			
			// 세션 정보 갱신
			Member updateMember = memberService.findByMemberId(memberId);
			
			// 4. redirect - msg는 session에 저장
			request.getSession().setAttribute("msg", msg);  // redirect 후에 꺼내서 출력이 됨.
			response.sendRedirect(request.getContextPath() + "/member/memberView");
			// 위와 같이 하면 index페이지가 아니라 현재 회원정보페이지로 redirect 되는 것.
			
		} catch(Exception e) {
			e.printStackTrace();
			throw e;
		} 
	}

}
