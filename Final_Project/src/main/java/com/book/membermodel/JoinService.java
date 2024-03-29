package com.book.membermodel;

import javax.servlet.http.HttpSession;

import com.book.model.MemberDTO;

public interface JoinService {
	
	int updateMailKey(MemberDTO memberDto) throws Exception;

	void join(MemberDTO memberDto) throws Exception;
	
	int sendSMS(String phone, HttpSession session) throws Exception;
	
	int checkIfAlreadyJoined(MemberDTO dto);

	MemberDTO getmemberonelist(String phone);

}
