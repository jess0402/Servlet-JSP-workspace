package com.kh.student.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.kh.student.model.dto.Student;

public class StudentDaoImpl implements StudentDao {

	@Override
	public Student selectOne(SqlSession sqlSession, int no) {
		// sqlSession.selectOne(statement, parameter)
		// statement:String - (mapper의 namespace).(쿼리태그의 id값)
		// parameter - 전달할 값, 딱 하나만 전달 가능. 만약 값 여러개를 전달하고 싶으면 vo나 dto나 Map등에 담아서 전달해야함.
		return sqlSession.selectOne("student.selectOne", no);
	}
	
	@Override
	public Map<String, Object> selectOneMap(SqlSession sqlSession, int no) {
		// 결과 레코드가 하나면 계속 selectOne
		return sqlSession.selectOne("student.selectOneMap", no);
	}

	@Override
	public int insertStudent(SqlSession sqlSession, Student student) {
		return sqlSession.insert("student.insertStudent", student);
	}

	@Override
	public int insertStudent(SqlSession sqlSession, Map<String, Object> studentMap) {
		return sqlSession.insert("student.insertStudentMap", studentMap);
	}

	@Override
	public int getTotalCount(SqlSession sqlSession) {
		return sqlSession.selectOne("student.getTotalCount");
	}

	@Override
	public int updateStudent(SqlSession sqlSession, Student student) {
		return sqlSession.update("student.updateStudent", student);
	}

	@Override
	public int deleteStudent(SqlSession sqlSession, int no) {
		return sqlSession.update("student.deleteStudent", no);
	}

	@Override
	public List<Student> selectStudentList(SqlSession sqlSession) {
		// 여러건 조회이므로 selectList
		return sqlSession.selectList("student.selectStudentList");
	}
	
	@Override
	public List<Map<String, Object>> selectStudentMapList(SqlSession sqlSession) {
		return sqlSession.selectList("student.selectStudentMapList");
	}
}
