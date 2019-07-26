package kr.green.test.service;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
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
	@Autowired
	private JavaMailSender mailSender;
	@Override
	public MemberVO signin(String id, String pw) {
		MemberVO user = memberDao.getMember(id);
		if(user != null && passwordEncoder.matches(pw, user.getPw())) {
			return user;
		}
		return null;
	}
	@Override
	public void signup(MemberVO mVo) {
		if(mVo == null)
			return ;
		String encPw = passwordEncoder.encode(mVo.getPw());
		mVo.setPw(encPw);
		memberDao.signup(mVo);
	}
	@Override
	public boolean isMember(String id) {
		MemberVO user = memberDao.getMember(id);
		if(user == null)
			return false;
		else
			return true;
	}
	@Override
	public String getVal(String str) {
		String [] tmpStr = str.split("=");
		//id=123=123
		//id&123
		if(tmpStr.length != 2)
			return null;
		//id=123에서 123을 원하는데 id는 0번지에 123은 1번지에 저장되기 때문에
		//1번지의 값을 리턴
		return tmpStr[1];
	}
	@Override
	public boolean isMember(String id, String email) {
		MemberVO user = memberDao.getMember(id);
		if(user != null && user.getEmail().equals(email))
			return true;
		return false;
	}
	@Override
	public void sendEmail(String title, String contents, String email) {
		System.out.println(email);
		try {
	        MimeMessage message = mailSender.createMimeMessage();
	        MimeMessageHelper messageHelper 
	            = new MimeMessageHelper(message, true, "UTF-8");
	        String setfrom ="stajun@gmail.com";
	        messageHelper.setFrom(setfrom);  // 보내는사람 생략하거나 하면 정상작동을 안함
	        messageHelper.setTo(email);     // 받는사람 이메일
	        messageHelper.setSubject(title); // 메일제목은 생략이 가능하다
	        messageHelper.setText(contents);  // 메일 내용

	        mailSender.send(message);
	    } catch(Exception e){
	        System.out.println(e);
	    }
	}
	@Override
	public void modify(String id, String newPw) {
		MemberVO user = memberDao.getMember(id);
		if(user == null)
			return ;
		String encPw = passwordEncoder.encode(newPw);
		user.setPw(encPw);
		memberDao.modify(user);	
	}
	@Override
	public MemberVO modify(MemberVO user, String oldPw) {
		if(user == null)
			return null;
		MemberVO oUser = memberDao.getMember(user.getId());
		if(!passwordEncoder.matches(oldPw, oUser.getPw()))
			return null;
		
		if(user.getPw().length()==0) {
			//새 비밀번호가 입력되지 않은 경우 기존비밀번호 정보를 가져와 새비밀번호로 설정한다.
			user.setPw(oUser.getPw());
		}else {
			//새 비밀번호가 입력된 경우 디비에 저장하기 전에 암호화를 해야한다.
			String encPw = passwordEncoder.encode(user.getPw());
			user.setPw(encPw);
		}
		memberDao.modify(user);
		return user;
	}
	@Override
	public boolean updateUserToSession(HttpServletRequest r, MemberVO nUser) {
		if(nUser == null)
			return false;
		HttpSession s = r.getSession();
		s.removeAttribute("user");//이전 회원정보 제거
		s.setAttribute("user", nUser);//새 회원 정보 추가
		return true;
	}
	
	
}
