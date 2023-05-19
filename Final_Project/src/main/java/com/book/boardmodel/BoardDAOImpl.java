package com.book.boardmodel;

import java.util.List;

import javax.inject.Inject;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.book.model.BookDTO;

@Repository
public class BoardDAOImpl implements BoardDAO{
	
	@Inject
	private SqlSessionTemplate sqlSession;

	@Override
	public List<BookDTO> getBoardMainList() {
		return this.sqlSession.selectList("all");
	}

}