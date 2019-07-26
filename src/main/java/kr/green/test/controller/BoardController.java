package kr.green.test.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.green.test.pagination.Criteria;
import kr.green.test.pagination.PageMaker;
import kr.green.test.service.BoardService;
import kr.green.test.vo.BoardVO;

@Controller
public class BoardController {
	@Autowired
	BoardService boardService;
	
	@RequestMapping(value="/board/list",method=RequestMethod.GET)
	public String boardListGet(Model model,Criteria cri) {
		//게시판정보를 현재 페이지를 기준으로 가져오기
		ArrayList<BoardVO> list = boardService.getBoardList(cri);
		PageMaker pm = new PageMaker();
		pm.setCriteria(cri);
		pm.setDisplayPageNum(2);
		int totalCount = boardService.getCountBoardList(cri);
		pm.setTotalCount(totalCount);

		model.addAttribute("list", list);
		model.addAttribute("pageMaker",pm);
		return "board/list";
	}
	@RequestMapping(value="/board/display",method=RequestMethod.GET)
	public String boardDisplayGet(Model model, Integer num, Criteria cri) {
		BoardVO board = boardService.getBoard(num);
		
		model.addAttribute("board", board);
		model.addAttribute("cri", cri);
		return "board/display";
	}
	@RequestMapping(value="/board/register",method=RequestMethod.GET)
	public String boardRegisterGet() {
		return "board/register";
	}
	@RequestMapping(value="/board/register",method=RequestMethod.POST)
	public String boardRegisterPost(BoardVO bVo) {
		boardService.registerBoard(bVo);
		return "redirect:/board/list";
	}
	@RequestMapping(value="/board/modify",method=RequestMethod.GET)
	public String boardModifyGet(Model model,Integer num, HttpServletRequest r) {
		if(!boardService.isWriter(r, num)) {
			return "redirect:/board/list";
		}
		BoardVO board = boardService.getBoard(num);
		model.addAttribute("board", board);
		return "board/modify";
	}
	@RequestMapping(value="/board/modify",method=RequestMethod.POST)
	public String boardModifyPost(Model model,BoardVO bVo) {
		boardService.modifyBoard(bVo);		
		model.addAttribute("num",bVo.getNum());
		return "redirect:/board/display";
	}
	@RequestMapping(value="/board/delete",method=RequestMethod.GET)
	public String boardDeleteGet(Model model,Integer num,HttpServletRequest r) {
		if(boardService.isWriter(r, num)) {
			boardService.deleteBoard(num);
		}
		return "redirect:/board/list";
	}
}