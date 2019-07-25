package kr.green.test.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.green.test.vo.MemberVO;
import kr.green.test.service.MemberService;

@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@Autowired
	MemberService memberService;
	@Autowired
	private JavaMailSender mailSender;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model) {
		logger.info("메인페이지 실행");
		
		// 사용자에게 home.jsp를 보내준다
		return "home";
	}
	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String signupGet(Model model) {
		logger.info("회원가입페이지 실행");
		
		return "signup";
	}
	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public String signupPOST(MemberVO mVo) {
		logger.info("회원가입 진행중");
		// 회원가입이 진행되어야 함
		if (memberService.signup(mVo))
			return "redirect:/";
		else
			return "redirect:/signup";
	}
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String signinGet(Model model) {
		logger.info("로그인페이지 실행");
		
		return "login";
	}
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String signinPOST(Model model,MemberVO mVo) {
		logger.info("로그인 진행중");
		MemberVO user = memberService.login(mVo);
		// 로그인이 진행되어야 함
		if (user != null) {
			model.addAttribute("user",user);
			return "redirect:/";
		}
		return "redirect:/login";
	}
	@RequestMapping(value = "/member/modify", method = RequestMethod.GET)
	public String memberModifyGet(Model model) {
		logger.info("회원정보수정페이지 실행");
		
		return "member/modify";
	}
	@RequestMapping(value = "/member/modify", method = RequestMethod.POST)
	public String memberModifyPOST(MemberVO mVo,String oldPw) {
		logger.info("회원정보수정 진행중");
		// 회원가입이 진행되어야 함
		if (memberService.memberModify(mVo,oldPw))
			return "redirect:/";
		else
			return "redirect:/member/modify";
	}
	@RequestMapping(value = "/logout")
	public String signout(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.removeAttribute("user");
		return "redirect:/";
	}
	@RequestMapping(value ="/dup")
	@ResponseBody
	public Map<Object, Object> idcheck(@RequestBody String id){

	    Map<Object, Object> map = new HashMap<Object, Object>();
	    //변수 id에 저장된 아이디가 회원 아이디인지 아닌지 확인하여 isMember 변수에 담아 보낸다
	    boolean isMember = memberService.memberConfirm(id);
	    map.put("isMember", isMember);
	    return map;
	}
	@RequestMapping(value = "/mail/mailForm")
	public String mailForm() {

	    return "mail";
	}
	@RequestMapping(value = "/mail/mailSending")
	public String mailSending(HttpServletRequest request) {
		
	    String setfrom = "stajun@naver.com";         
	    String tomail  = request.getParameter("tomail");     // 받는 사람 이메일
	    String title   = request.getParameter("title");      // 제목
	    String content = request.getParameter("content");    // 내용

	    try {
	        MimeMessage message = mailSender.createMimeMessage();
	        MimeMessageHelper messageHelper 
	            = new MimeMessageHelper(message, true, "UTF-8");

	        messageHelper.setFrom(setfrom);  // 보내는사람 생략하거나 하면 정상작동을 안함
	        messageHelper.setTo(tomail);     // 받는사람 이메일
	        messageHelper.setSubject(title); // 메일제목은 생략이 가능하다
	        messageHelper.setText(content);  // 메일 내용

	        mailSender.send(message);
	    } catch(Exception e){
	        System.out.println(e);
	    }

	    return "redirect:/mail/mailForm";
	}
	@RequestMapping(value = "password/find")
	public String find() {
		logger.info("비밀번호찾기 페이지 실행");

		return "member/find";
	}
	@RequestMapping(value ="/checkemail")
	@ResponseBody
	public Map<Object, Object> emailcheck(@RequestBody String str){

	    Map<Object, Object> map = new HashMap<Object, Object>();
	    String [] arr = new String[2];
	    arr = str.split("&");
	    String id = arr[0];
	    String email = "";
	    try {
			email=URLDecoder.decode(arr[1],"UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    id = memberService.getVal(id);
	    email = memberService.getVal(email);
	    boolean isOk = memberService.checkMember(id,email);
	    map.put("isOk",isOk);
	    return map;
	}
	@RequestMapping(value = "password/send")
	public String passwordSend(String id,String email) {
		logger.info("비밀번호찾기 페이지 실행");
		//비밀번호 생성
		String newPw = memberService.createPw();
		//생성된 비밀번호 DB에 저장
		memberService.modify(id,email,newPw);
		//이메일 발송
		String title = "변경된 비밀번호입니다.";
		String contents = "변경된 비밀번호입니다.\n" + newPw + "\n";
		memberService.sendMail(email,title,contents);
		return "send";
	}
}
