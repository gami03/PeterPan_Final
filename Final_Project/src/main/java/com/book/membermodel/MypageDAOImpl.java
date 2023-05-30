package com.book.membermodel;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.book.model.BookDTO;
import com.book.model.MemberDTO;

@Repository
public class MypageDAOImpl implements MypageDAO{
	
	@Autowired
	private SqlSessionTemplate sqlSession;

	@Override
	public List<BookDTO> getRecommendbookRandom() {
		return this.sqlSession.selectList("RecommendbookRandom");
	}

	@Override
	public int memberInfoModify(MemberDTO dto) {
		return this.sqlSession.update("memberInfoModify", dto);
	}

	@Override
	public int memberdelete(MemberDTO dto) {
		return this.sqlSession.delete("memberdelete", dto);
	}

}
