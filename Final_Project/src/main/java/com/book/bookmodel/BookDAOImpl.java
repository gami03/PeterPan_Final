package com.book.bookmodel;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.book.model.BookDTO;
import com.book.model.NewRelBookDTO;
import com.book.model.ReviewDTO;
import com.book.model.ReviewPercentDTO;

@Repository
public class BookDAOImpl implements BookDAO {

	@Autowired
	private SqlSessionTemplate sqlSession;

	@Override
	public BookDTO getBookDetail(int bookNo) {
		
		return this.sqlSession.selectOne("detail", bookNo);
	}
	
	@Override
	public List<ReviewDTO> getReviewCont(int bookNo) {
		 
		return this.sqlSession.selectList("reviewCont", bookNo);
	}
	
	@Override
	public ReviewPercentDTO getPercentReview(int bookNo) {
		 
		return this.sqlSession.selectOne("percentLike", bookNo);
	}
	
	@Override
	public List<BookDTO> getbooklist() {
		
	return this.sqlSession.selectList("main_all");
	}
	
	@Override
	public List<BookDTO> getbestlist() {
		
	return this.sqlSession.selectList("best_all");
	}

	@Override
	public List<NewRelBookDTO> getNewRelBook() {
		 
		return this.sqlSession.selectList("newRelBook");
	}
	
	@Override
	public List<BookDTO> getSameGenre(int bookNo) {
		
		return this.sqlSession.selectList("sameGenre",bookNo);
	}
}
