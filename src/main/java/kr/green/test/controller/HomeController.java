package kr.green.test.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.green.test.dao.MemberDAO;
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
	public String loginGET(Model model) {
		logger.info("로그인페이지 실행");
		return "login";
	}
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String loginPOST(MemberVO mVo) {
		logger.info("로그인 진행중");
		if(memberService.login(mVo))
			return "redirect:/";
		else
			return "redirect:/login";
	}
	
}
