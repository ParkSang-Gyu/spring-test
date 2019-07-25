package kr.green.test.service;

import javax.mail.internet.MimeMessage;

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
	public boolean signup(MemberVO mVo) {
		//예외처리
		if(mVo == null)
			return false;
		mVo.setName("");
		// 기존에 해당 아이디가 있는지 체크
		// 있으면 false를 반환하고 종료
		// 없으면 회원가입 진행
		if(memberDao.getMember(mVo.getId()) != null)
			return false;
		//회원가입창에서 입력받은 암호를 암호화시킴
		String encodePw = passwordEncoder.encode(mVo.getPw());
		//회원 정보의 비밀번호를 암호화된 비밀번호로 변경
		mVo.setPw(encodePw);
		memberDao.signup(mVo);
		return true;
	}

	@Override
	public MemberVO login(MemberVO mVo) {
		//예외처리
		if(mVo == null)
			return null;
		// DAO에게 입력한 id(mVo.getId())와 일치하는 회원 정보를 가져오게 시켜서 객체를 만들어 저장
		MemberVO oVo = memberDao.getMember(mVo.getId());
		// 저장된 회원정보가 없으면 컨트롤러에게 회원 아니라고 알려주고
		if(oVo == null)
			return null;
		// 있으면 가져온 회원정보 비밀번호(oVo.getPw())와 입력한 비밀번호(mVo.getPw()))를 비교하여
		if(passwordEncoder.matches(mVo.getPw(),oVo.getPw()))
			// 같으면(참이면) 컨트롤러에게 회원이라고 알려주고
			return oVo;
		// 다르면 아니라고 알려준다
		return null;
	}

	@Override
	public boolean memberModify(MemberVO mVo, String oldPw) {
		//예외처리
		if(mVo == null)
			return false;
		MemberVO oVo = memberDao.getMember(mVo.getId());
		if(oVo == null)
			return false;
		if(oVo.getPw().equals(oldPw)) {
			String encodePw = passwordEncoder.encode(mVo.getPw());
			mVo.setPw(encodePw);
			memberDao.memberModify(mVo);
		}	
		// if(memberDao.getMember(mVo.getId()).getPw().equals(oldPw){
		//	memberDao.memberModify(mVo);
		// }
		return false;
	}

	@Override
	public boolean memberConfirm(String id) {
		if(memberDao.getMember(id) == null)
			return false;
		return true;
	}

	@Override
	public String getVal(String id) {
		String [] arr = id.split("=");
		if(arr.length == 2)
			return arr[1];
		else
			return "";
	}

	@Override
	public boolean checkMember(String id, String email) {
		MemberVO user = memberDao.getMember(id);
		if(user != null && user.getEmail().equals(email))
			return true;
		return false;
	}

	@Override
	public String createPw() {
		//문자열에 저장
		String str = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
		//비밀번호는 처음에 공백으로 초기화
		String pw = "";
		//비밀번호가 8자리이므로 반복문을 8번 반복
		for(int i=0;i<8;i++) {
			//정수 변수에 랜덤한 수를 뽑아 저장(0부터  61까지)
			int r = (int)(Math.random()*62);
			pw += str.charAt(r);
		}
		return pw;
	}

	@Override
	public void modify(String id, String email, String newPw) {
		MemberVO user = memberDao.getMember(id);
		if(user == null)
			return ;
		if(!user.getEmail().equals(email))
			return ;
		String encodePw = passwordEncoder.encode(newPw);
		user.setPw(encodePw);
		memberDao.memberModify(user);
		
	}

	@Override
	public void sendMail(String email, String title, String contents) {
		String setfrom = "englandmf11@naver.com";

	    try {
	        MimeMessage message = mailSender.createMimeMessage();
	        MimeMessageHelper messageHelper 
	            = new MimeMessageHelper(message, true, "UTF-8");

	        messageHelper.setFrom(setfrom);  // 보내는사람 생략하거나 하면 정상작동을 안함
	        messageHelper.setTo(email);     // 받는사람 이메일
	        messageHelper.setSubject(title); // 메일제목은 생략이 가능하다
	        messageHelper.setText(contents);  // 메일 내용

	        mailSender.send(message);
	    } catch(Exception e){
	        System.out.println(e);
	    }
		
	}

	

	
}
