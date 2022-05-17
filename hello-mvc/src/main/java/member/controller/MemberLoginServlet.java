package member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.HelloMvcUtils;
import member.model.dto.Member;
import member.model.service.MemberService;

/**
 * Servlet implementation class MemberLoginServlet
 */
@WebServlet("/member/login")
public class MemberLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberService memberService = new MemberService();

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 인코딩처리
//		request.setCharacterEncoding("utf-8");
		
		// 2. 사용자 입력값 처리
		String memberId = request.getParameter("memberId");
		String password = HelloMvcUtils.encrypt(request.getParameter("password"), memberId);
		String saveId = request.getParameter("saveId");
		System.out.println("memberId@MemberLoginServlet = " + memberId);
		System.out.println("password@MemberLoginServlet = " + password);
		System.out.println("saveId@MemberLoginServlet = " + saveId);
		
		// 3. 업무로직
		Member member = memberService.findByMemberId(memberId);
		System.out.println("member@MemberLoginServlet = " + member);

		// session 가져오기
		// getSession(create:boolean)
		// true(기본값) - 존재하면 해당 세션객체를, 존재하지 않으면 새로 생성해서 리턴
		// false - 존재하면 해당 세션객체를, 존재하지 않으면 null을 리턴
		HttpSession session = request.getSession();
		System.out.println(session.getId()); // JSESSIONID와 동일
		
		if(member != null && password.equals(member.getPassword())) {
			// 로그인 성공!
			session.setAttribute("loginMember", member); // loginMember라는 이름으로 member객체를 담아둔다.		
			
			// saveId 쿠키처리
			Cookie cookie = new Cookie("saveId", memberId);
			// 경로설정
			cookie.setPath(request.getContextPath()); // /mvc로 시작하는 경로에 이 쿠키를 사용함.
			if(saveId != null) {
				// max-age 설정이 없다면, 세션쿠키로 등록. 브라우져 종료시 폐기
				// max-age 설정을 했다면, 영속쿠키로 등록. 지정한 시각에 폐기
				cookie.setMaxAge(7 * 24 * 60 * 60);  // 초단위 일주일 후 폐기

				response.addCookie(cookie); // 응답객체에 쿠키세팅(쿠키 담아두기). Set-Cookie 헤더에 작성.
			}
			else {
				cookie.setMaxAge(0);  // MaxAge를 0으로 하면 즉시삭제되어 쿠키가 끝남.
			}
			response.addCookie(cookie);
		}
		else {
			// 로그인 실패! 
			session.setAttribute("msg", "아이디 또는 비밀번호가 일치하지 않습니다.");
		}
	
		// 4. 응답처리 (jsp 위임)
//		RequestDispatcher reqDispatcher = request.getRequestDispatcher("/index.jsp");
//		reqDispatcher.forward(request, response);
		
		// 4. 응답처리 : 리다이렉트
		response.sendRedirect(request.getContextPath() + "/");
		
	}

}
