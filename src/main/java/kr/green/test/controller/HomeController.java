package kr.green.test.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.green.test.service.BoardService;
import kr.green.test.service.MemberService;
import kr.green.test.vo.MemberVO;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	MemberService memberService;
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String homeGet() {
		logger.info("메인 페이지");
		
		return "home";
	}
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public String homePost(Model model, String id, String pw) {
		logger.info("로그인 진행 중");
		MemberVO user = memberService.signin(id,pw); 
		if( user != null) {
			logger.info("로그인 성공");
			model.addAttribute("user",user);
			return "redirect:/board/list";
		}else {
			logger.info("로그인 실패");
			return "redirect:/";
		}		
	}
	@RequestMapping(value = "/signout", method = RequestMethod.GET)
	public String signoutGet(HttpServletRequest r) {
		logger.info("로그아웃");
		r.getSession().removeAttribute("user");
		return "redirect:/";
	}
	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String signupGet() {
		logger.info("회원가입");
		
		return "signup";
	}
	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public String signupPost(MemberVO mVo) {
		logger.info("회원가입 진행중");
		memberService.signup(mVo);
		return "redirect:/";
	}
	
	@RequestMapping(value ="/dup")
	@ResponseBody
	public Map<Object, Object> idcheck(@RequestBody String id){
	    Map<Object, Object> map = new HashMap<Object, Object>();
	   
	    boolean isMember = memberService.isMember(id);
	    map.put("isMember", isMember);
	    return map;
	}
	
	@RequestMapping(value = "/password/find", method = RequestMethod.GET)
	public String passwordFindGet() {
		logger.info("비밀번호 찾기 페이지");
		
		return "find";
	}
	
	@RequestMapping(value ="/checkemail")
	@ResponseBody
	public Map<Object, Object> emailcheck(@RequestBody String str){
	    Map<Object, Object> map = new HashMap<Object, Object>();
	    try {
			str = URLDecoder.decode(str,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    String [] tmpStr = str.split("&");
	    String id = memberService.getVal(tmpStr[0]);
	    String email = memberService.getVal(tmpStr[1]);
	    boolean isMember = memberService.isMember(id,email);
	    map.put("isMember",isMember);
	    return map;
	}
	@RequestMapping(value = "/password/send", method = RequestMethod.POST)
	public String passwordSendPost(String id, String email) {
		logger.info("비밀번호 생성하여 메일보내기");
		String str ="0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String newPw = "";
		while(newPw.length() < 8) {
			int index = (int)(Math.random()*str.length());
			newPw += str.charAt(index);
		}
		String title ="변경된 비밀번호입니다.";
		String contents ="새 비밀번호입니다.\n"+newPw;
		memberService.sendEmail(title,contents,email);
		memberService.modify(id,newPw);
		return "redirect:/";
	}
	@RequestMapping(value = "/member/modify", method = RequestMethod.GET)
	public String memberModifyGet(Model model,Boolean success) {
		// Boolean은 래퍼 클래스이고 null값을 저장할 수 있다.
		// boolean은 자료형이고 null값을 저장할 수 없다.
		logger.info("회원 정보 수정 페이지");
		model.addAttribute("success",success);
		return "member/modify";
	}
	@RequestMapping(value = "/member/modify", method = RequestMethod.POST)
	public String memberModifyPost(MemberVO user, String oldPw,HttpServletRequest r,Model model) {
		logger.info("회원 정보 수정 중");
		System.out.println(user);
		System.out.println(oldPw);
		MemberVO nUser = memberService.modify(user, oldPw);
		boolean t = memberService.updateUserToSession(r,nUser);
		model.addAttribute("success",t);
		return "redirect:/member/modify";
	}
}