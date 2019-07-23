package kr.green.test.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.green.test.pagination.Criteria;
import kr.green.test.pagination.PageMaker;
import kr.green.test.service.BoardService;
import kr.green.test.vo.BoardVO;
import kr.green.test.vo.MemberVO;

@Controller
public class BoardController {

private static final Logger logger = LoggerFactory.getLogger(BoardController.class);

	@Autowired
	BoardService boardService;
	// @RequestMapping(value="/board/list" 에서 value="/board/list 는 URI주소를 이렇게 하겠다는 의미
	@RequestMapping(value="/board/list", method = RequestMethod.GET)
	public String boardListGET(Model model,Criteria cri) {
		logger.info("게시판 페이지 실행");
		// 일반 객체에는 게시글 한개의 정보만 담을 수 있기 때문에 전체를 가져오기 위해서 ArrayList에 담는다
		ArrayList<BoardVO> boardList = boardService.getBoardList(cri);
		PageMaker pm = new PageMaker();
		pm.setCriteria(cri);
		pm.setDisplayPageNum(5);
		int totalCount = boardService.getTotalCount(cri);
		pm.setTotalCount(totalCount);
		model.addAttribute("pageMaker",pm);
		model.addAttribute("list",boardList);
		System.out.println();
		// return "board/list";는 views폴더에 있는 board폴더의 list.jsp파일을 열겠다는 의미
		return "board/list";
	}
	@RequestMapping(value="/board/display", method = RequestMethod.GET)
	public String boardDisplayGET(Model model,Integer num) {
		logger.info("게시글 페이지 실행");
		BoardVO bVo = boardService.getBoard(num);
		model.addAttribute("board",bVo);
		return "board/display";
	}
	@RequestMapping(value = "/board/modify", method = RequestMethod.GET)
	public String boardModifyGet(Model model,Integer num,HttpServletRequest request) {
		BoardVO bVo = boardService.getBoard(num);
		model.addAttribute("board",bVo);
		MemberVO user = (MemberVO)request.getSession().getAttribute("user");
		if(!user.getId().equals(bVo.getWriter()))
			return "board/list";
		return "board/modify"; 
	}
//	@RequestMapping(value = "/modify", method = RequestMethod.POST)
//	public String boardModifyPost(BoardVO bVo, HttpServletRequest request) {
//		HttpSession s = request.getSession();
//		MemberVO user = (MemberVO)s.getAttribute("user");
//		// 내 코드
//		if (boardService.boardModify(bVo))
//			return "redirect:/board/display";
//		else
//			return "redirect:board/modify";
//	}
	@RequestMapping(value = "/board/modify", method = RequestMethod.POST)
	public String boardModifyPost(Model model,BoardVO bVo,HttpServletRequest request) {
		boardService.updateBoard(bVo,request);
		model.addAttribute("num",bVo.getNum());
		return "redirect:/board/modify";
	}
	@RequestMapping(value = "/board/register", method = RequestMethod.GET)
	public String boardModifyGet(Model model) {
		
		return "board/register";
	}
	@RequestMapping(value = "/board/register", method = RequestMethod.POST)
	public String boardRegisterPost(BoardVO bVo) {
		if(boardService.boardRegister(bVo))
			return "redirect:/board/list";
		else
			return "redirect:/board/register";
	}
	@RequestMapping(value="delete", method=RequestMethod.GET)
	public String boardDeleteGet(Integer num,HttpServletRequest r) {
		if(boardService.isWriter(num,r)) {
			boardService.deleteBoard(num);
		}
		return "redirect:/board/list";
	}
}
