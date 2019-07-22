package kr.green.test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import kr.green.test.dao.MemberDAO;
import kr.green.test.vo.MemberVO;

@Service
public class MemberServiceImp implements MemberService{

	@Autowired
	MemberDAO memberDao;
	@Autowired
	BCryptPasswordEncoder passwordEncoder;

	@Override
	public MemberVO login(MemberVO mVo) {
		MemberVO mVo1 = memberDao.getMember(mVo.getId());
		if(mVo1 == null)
			return null;
		if(mVo1 != null && passwordEncoder.matches(mVo.getPw(), mVo1.getPw()))
			return mVo1;
		return null;
	}
	@Override
	public boolean memberModify(MemberVO mVo,String oldPw) {
		//예외처리
		if(mVo == null)
			return false;
		MemberVO oVo = memberDao.getMember(mVo.getId());
		if(oVo == null)
			return false;
		if(oVo.getPw().equals(oldPw))
			memberDao.memberModify(mVo);
		// if(memberDao.getMember(mVo.getId()).getPw().equals(oldPw){
		//	memberDao.memberModify(mVo);
		// }
		return false;
	}
	@Override
	public boolean signup(MemberVO mVo) {
		
		//예외처리
		if(mVo == null)
			return false;
		mVo.setName("");
		// 기존에 해당 아이디가 있는지 체크
		// 있으면 false를 반환하고 종료
		if(memberDao.getMember(mVo.getId()) != null)
			return false;
		// 없으면 회원가입 진행
		String encodePw = passwordEncoder.encode(mVo.getPw());
		mVo.setPw(encodePw);
		memberDao.signup(mVo);
		return true;
	}
}
