package member.model.service;

import static common.JdbcTemplate.*;
import java.sql.Connection;

import member.model.dao.MemberDao;
import member.model.dto.Member;

/**
 * 1. connection 생성
 * 2. dao 요청(connection)
 * 3. dml인 경우 transaction 처리필요 (dql인 경우 생략)
 * 4. connection 반환
 * 5. controller로 값 반환처리
 *
 */
public class MemberService {
	
	// DQL 패턴 (트랜잭션 처리 없음)
	private MemberDao memberDao = new MemberDao();
	public Member findByMemberId(String memberId) {
		Connection conn = getConnection();
		Member member = memberDao.findByMemberId(conn, memberId);
		close(conn); // 트랜잭션 처리할 필요 없음.
		
		return member;
	}
	
	// DML 패턴 (트랜잭션 처리 있음)
	public int insertMember(Member member) {
		int result = 0;
		Connection conn = getConnection();
		try {
			result = memberDao.insertMember(conn, member);
			commit(conn);
		} catch (Exception e) {
			rollback(conn);
			throw e;  // controller에게 알려주기 위함.
		} finally {
			close(conn);			
		}
		return result;
	}
	
	// DML 패턴 (트랜잭션 처리 있음)
	public int updateMember(Member member) {
		int result = 0;
		// 1. Connection 객체 생성
		Connection conn = getConnection();
		try {
			// 2. dao 요청
			result = memberDao.updateMember(conn, member); 
					
			// 3. 트랜잭션 처리 - commit
			commit(conn);
		} catch(Exception e) {
			// 3. 트랜잭션 처리 - rollback 
			rollback(conn);
			throw e;  // controller 통보용
		} finally {
			// 4. Connection 객체 반환
			close(conn); 
		}
		return result;
	}

}
