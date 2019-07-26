package kr.green.test.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.green.test.dao.BoardDAO;
import kr.green.test.pagination.Criteria;
import kr.green.test.vo.BoardVO;
import kr.green.test.vo.MemberVO;

@Service
public class BoardServiceImp implements BoardService{

	@Autowired
	BoardDAO boardDao;

	@Override
	public ArrayList<BoardVO> getBoardList(Criteria cri) {
		return boardDao.getBoardList(cri);
	}

	@Override
	public int getCountBoardList(Criteria cri) {
		return boardDao.getCountBoardList(cri);
	}

	@Override
	public BoardVO getBoard(Integer num) {
		if(num == null || num <= 0)
			return null;
		else
			return boardDao.getBoard(num);
	}

	@Override
	public void registerBoard(BoardVO bVo) {
		boardDao.registerBoard(bVo);	
	}

	@Override
	public boolean isWriter(HttpServletRequest r, Integer num) {
		MemberVO user = (MemberVO)r.getSession().getAttribute("user");
		BoardVO board = boardDao.getBoard(num);
		if(user != null && board != null && user.getId().equals(board.getWriter())) {
			return true;
		}
		return false;
	}

	@Override
	public void modifyBoard(BoardVO bVo) {
		boardDao.modifyBoard(bVo);	
	}

	@Override
	public void deleteBoard(Integer num) {
		boardDao.deleteBoard(num);	
	}

	
}
