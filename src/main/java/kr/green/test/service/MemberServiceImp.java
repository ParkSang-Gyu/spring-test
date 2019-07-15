package kr.green.test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.green.test.dao.MemberDAO;
import kr.green.test.vo.MemberVO;

@Service
public class MemberServiceImp implements MemberService{

	@Autowired
	MemberDAO memberDao;

	@Override
	public MemberVO login(MemberVO mVo) {
		MemberVO mVo1 = memberDao.getMember(mVo.getId());
		if(mVo1 == null)
			return null;
		if(mVo1.getPw().equals(mVo.getPw()))
			return mVo1;
		return null;
	}
}
