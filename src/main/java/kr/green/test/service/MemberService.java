package kr.green.test.service;

import kr.green.test.vo.MemberVO;

public interface MemberService {
	public boolean signup(MemberVO mVo);
	MemberVO login(MemberVO mVo);
	public boolean memberModify(MemberVO mVo, String oldPw);
	public boolean memberConfirm(String id);
	public void getMember(MemberVO mVo,String id);
}
