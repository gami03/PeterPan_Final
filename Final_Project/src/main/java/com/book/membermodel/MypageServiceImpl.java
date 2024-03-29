package com.book.membermodel;

import java.util.List;

import javax.mail.MessagingException;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.book.member.MailHandler;
import com.book.member.Signature;
import com.book.member.TempKey;
import com.book.model.BoardDTO;
import com.book.model.BookDTO;
import com.book.model.CouponDTO;
import com.book.model.MemberDTO;
import com.book.model.PurchaseDTO;

@Service
public class MypageServiceImpl implements MypageService{
	
	@Autowired
	private MypageDAO dao;
	
	@Autowired
    JavaMailSender mailSender;
	
	@Override
	public List<BookDTO> getRecommendbookRandom() {
		return this.dao.getRecommendbookRandom();
	}

	@Override
	public String sendEmail(String email, HttpSession session) throws Exception {
		//랜덤 문자열을 생성해서 mail_key 컬럼에 넣어주기
        String mail_key = new TempKey().getKey(6,false); //랜덤키 길이 설정
        
        // 인증을 위한  인증 코드 이메일 발송
        MailHandler sendMail = new MailHandler(mailSender);
        sendMail.setSubject("[피터팬 인증메일 입니다.]"); //메일제목
        sendMail.setText(
        	    "<h1>피터팬 메일인증</h1>" +
        	    "<br>피터팬에 오신것을 환영합니다!" +
        	    "<br>아래 [인증 코드]를 입력창에 입력해주세요." +
        	    "<br>인증코드 [" + mail_key + "]");
        sendMail.setFrom("pit230615@gmail.com", "피터팬");
        sendMail.setTo(email);
        sendMail.send();
        
        session.setAttribute("mail_key", mail_key);
        
        return mail_key;
	}

	@Override
	public int memberInfoModify(MemberDTO dto) {
		return this.dao.memberInfoModify(dto);
	}

	@Override
	public int memberdelete(MemberDTO dto) {
		return this.dao.memberdelete(dto);
	}

	@Override
	public List<PurchaseDTO> purchaseList(String memberId) {
		return this.dao.purchasList(memberId);
	}

	@Override
	public List<BoardDTO> qnaList(int memberNo) {
		return this.dao.qnaList(memberNo);
	}

	@Override
	public List<BoardDTO> noAnswerqnaList(int memberNo) {
		return this.dao.noAnswerqnaList(memberNo);
	}

	@Override
	public List<BoardDTO> answerOkqnaList(int memberNo) {
		return this.dao.answerOkqnaList(memberNo);
	}

	@Override
	public List<CouponDTO> getcoupon(String memberId) {
		return this.dao.getcoupon(memberId);
	}

	@Override
	public int qnaDelete(int boardNo) {
		return this.dao.qnaDelete(boardNo);
	}
	
	@Override
	public void qnaAnswerDelete(int boardNo) {
		this.dao.qnaAnswerDelete(boardNo);
	}
	
	@Override
	public int qnaAnwerCount(int boardNo) {
		return this.dao.qnaAnswerCount(boardNo);
	}
	
	@Override
	public BoardDTO getBoardDtoAtboardNo(int boardNo) {
		return this.dao.getBoardDtoAtboardNo(boardNo);
	}
	
	@Override
	public int updateQna(BoardDTO dto) {
		return this.dao.updateQna(dto);
	}
	
}
