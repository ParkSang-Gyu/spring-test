package kr.green.test.service;

import javax.servlet.http.HttpServletRequest;

import kr.green.test.vo.MemberVO;

public interface MemberService {

	MemberVO signin(String id, String pw);

	void signup(MemberVO mVo);

	boolean isMember(String id);

	String getVal(String string);

	boolean isMember(String id, String email);

	void sendEmail(String title, String contents, String email);

	void modify(String id, String newPw);

	MemberVO modify(MemberVO user, String oldPw);

	boolean updateUserToSession(HttpServletRequest r, MemberVO nUser);
	
	
}
