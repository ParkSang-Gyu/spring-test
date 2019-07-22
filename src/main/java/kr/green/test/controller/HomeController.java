package kr.green.test.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.green.test.service.MemberService;
import kr.green.test.vo.MemberVO;

@Controller
public class HomeController {
	
private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	MemberService memberService;
	
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model) {
		logger.info("메인페이지 실행");
		return "home";
	}
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginGet(Model model) {
		logger.info("로그인페이지 실행");
		return "login";
	}
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String loginPost(Model model,MemberVO mVo) {
		logger.info("로그인 진행중");
		
		MemberVO user = memberService.login(mVo);
		if(user != null) {
			model.addAttribute("user",user);
			return "redirect:/";
		}	
		else
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
	public String logout(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.removeAttribute("user");
		return "redirect:/";
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
}
