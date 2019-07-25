package kr.green.test.service;

import javax.servlet.http.HttpServletRequest;

import kr.green.test.vo.MemberVO;

public interface MemberService {
	public boolean signup(MemberVO mVo);
	public MemberVO login(MemberVO mVo);
	public boolean memberModify(MemberVO mVo, String oldPw);
	public boolean memberConfirm(String id);
	public String getVal(String id);
	public boolean checkMember(String id, String email);
	public String createPw();
	public void modify(String id, String email, String newPw);
	public void sendMail(String email, String title, String contents);
	
}
