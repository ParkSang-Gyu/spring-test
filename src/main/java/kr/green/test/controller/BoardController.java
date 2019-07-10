package kr.green.test.controller;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.green.test.dao.BoardDAO;
import kr.green.test.service.BoardService;
import kr.green.test.vo.BoardVO;

@Controller
public class BoardController {

private static final Logger logger = LoggerFactory.getLogger(BoardController.class);

	@Autowired
	BoardService boardService;
	// @RequestMapping(value="/board/list" 에서 value="/board/list 는 URI주소를 이렇게 하겠다는 의미
	@RequestMapping(value="/board/list", method = RequestMethod.GET)
	public String boardListGET(Model model) {
		logger.info("게시판 페이지 실행");
		// 일반 객체에는 게시글 한개의 정보만 담을 수 있기 때문에 전체를 가져오기 위해서 ArrayList에 담는다
		ArrayList<BoardVO> boardList = boardService.getBoardList();
		model.addAttribute("list",boardList);
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
}
