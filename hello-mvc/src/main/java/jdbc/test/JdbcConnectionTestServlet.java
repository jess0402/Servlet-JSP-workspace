package jdbc.test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class JdbcConnectionTestServlet
 */
@WebServlet("/test/dbConnection")
public class JdbcConnectionTestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	String driverClass = "oracle.jdbc.OracleDriver"; // 드라이버 클래스명
	String url = "jdbc:oracle:thin:@localhost:1521:xe"; // 접속할 db서버주소 db접속프로토콜@ip:port:sid
	String user = "web";
	String password = "web";
	
	/**
	 * DQL
	 * 	- select
	 */
	// DQL을 실행하기 위한 형식 
	public void test() {
		// 반납해야하는 것 위에다 선언
		Connection conn = null;
		PreparedStatement pstmt= null;
		ResultSet rset = null;
		String sql = "select * from member";
		
		// 1. jdbc driver class 등록
		try {
			Class.forName(driverClass);
			System.out.println("> driver class 등록 완료!");
			
			// 2. Connection객체 생성 (url, user, password)
			conn = DriverManager.getConnection(url, user, password);
			System.out.println("> Connection객체 생성 성공!");
			
			// 3. Statement 객체(쿼리실행객체) 생성
			pstmt = conn.prepareStatement(sql);
			System.out.println("> Statement 객체 생성 성공!");
			
			// 4. Statement 실행(실행할 때 sql전달) - ResultSet객체 반환
			rset = pstmt.executeQuery();
			System.out.println("> Statement 실행 및 ResultSet 반환 성공!");
			// 여기까지 하면 결과를 받은 것
			
			// 5. ResultSet을 1행씩 열람
			while(rset.next()) { // 다음행이 존재하면 true 리턴
				String id = rset.getString("member_id"); // getString("컬럼명")
				String name = rset.getString("member_name");
				Date birthday = rset.getDate("birthday");
				System.out.printf("%s\t%s\t%s%n", id, name, birthday);
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			// 6. 자원반납 후 끝남
			// 객체가 생성된 역순으로 반납해야 함.
			try {
				rset.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				pstmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}	
			System.out.println("> 자원반납 성공!");
		}
		
	
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// db 연결테스트
		test();
		
		response.setContentType("text/plain; charset=utf-8");
		response.getWriter().append("db 연결 테스트 - 서버콘솔을 확인하세요!");
	}

}
