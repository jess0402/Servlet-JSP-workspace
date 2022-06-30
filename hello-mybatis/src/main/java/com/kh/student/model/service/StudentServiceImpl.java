package com.kh.student.model.service;

import org.apache.ibatis.session.SqlSession;
import static com.kh.common.SqlSessionTemplate.getSqlSession;

import java.util.Map;

import com.kh.student.model.dao.StudentDao;
import com.kh.student.model.dto.Student;

public class StudentServiceImpl implements StudentService {
	
	private StudentDao studentDao;

	public StudentServiceImpl(StudentDao studentDao) {
		super();
		this.studentDao = studentDao;
	}

	@Override
	public Student selectOne(int no) {
		Student student = null;
		
		try(SqlSession sqlSession = getSqlSession()){
			student = studentDao.selectOne(sqlSession, no);
		} // 예외처리가 없기 때문에 catch절 안써줘도 됨. close 자동으로 하도록 하기 위해 try안에 넣은 것.
		
		return student;
	}
	
	@Override
	public Map<String, Object> selectOneMap(int no) {
		Map<String, Object> student = null;
		
		try(SqlSession sqlSession = getSqlSession()){
			student = studentDao.selectOneMap(sqlSession, no);
		} // 예외처리가 없기 때문에 catch절 안써줘도 됨. close 자동으로 하도록 하기 위해 try안에 넣은 것.
		
		return student;
	}

	@Override
	public int insertStudent(Student student) { // DML 처리 템플릿
		int result = 0;
		SqlSession sqlSession = getSqlSession();
		
		try {
			result = studentDao.insertStudent(sqlSession, student);
			sqlSession.commit();
		} catch(Exception e) {
			sqlSession.rollback();
			throw e;
		} finally {
			sqlSession.close();
		}
		
		return result;
	}

	@Override
	public int insertStudent(Map<String, Object> studentMap) {
		int result = 0;
		SqlSession sqlSession = getSqlSession();
		
		try {
			result = studentDao.insertStudent(sqlSession, studentMap);
			sqlSession.commit();
		} catch(Exception e) {
			sqlSession.rollback();
			throw e;
		} finally {
			sqlSession.close();
		}
		
		return result;
	}

	@Override
	public int getTotalCount() {
		int totalCount = 0;
		try(SqlSession sqlSession = getSqlSession()){
			totalCount = studentDao.getTotalCount(sqlSession);
		}
		
		return totalCount;
	}

	@Override
	public int updateStudent(Student student) {
		int result = 0;
		SqlSession sqlSession = getSqlSession();
		
		try {
			result = studentDao.updateStudent(sqlSession, student);
			sqlSession.commit();
		} catch(Exception e) {
			sqlSession.rollback();
			throw e;
		} finally {
			sqlSession.close();
		}
		
		return result;
	}

	@Override
	public int deleteStudent(int no) {
		int result = 0;
		SqlSession sqlSession = getSqlSession();
		
		try {
			result = studentDao.deleteStudent(sqlSession, no);
			sqlSession.commit();
		} catch(Exception e) {
			sqlSession.rollback();
			throw e;
		} finally {
			sqlSession.close();
		}
		
		return result;
	}


	
	
	
}
