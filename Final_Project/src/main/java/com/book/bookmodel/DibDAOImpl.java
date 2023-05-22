package com.book.bookmodel;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.book.model.DibsDTO;

@Repository
public class DibDAOImpl implements DibDAO{
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	@Override
	public int checkDibs(int memberNo) {

		return this.sqlSession.selectOne("DibCheck", memberNo);
	}
	
@Override
public int insertDibs(int memberNo) {
	// TODO Auto-generated method stub
	return 0;
}
	
}	
