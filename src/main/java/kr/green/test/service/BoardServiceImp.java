package kr.green.test.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.green.test.dao.BoardDAO;
import kr.green.test.vo.BoardVO;
import kr.green.test.vo.MemberVO;

@Service
public class BoardServiceImp implements BoardService{

	@Autowired
	BoardDAO boardDao;

	@Override
	public ArrayList<BoardVO> getBoardList() {
		
		return boardDao.getBoardList();
	}

	@Override
	public BoardVO getBoard(Integer num) {
		if(num == null)
			return null;
		return boardDao.getBoard(num);
	}
	@Override
	public void updateViews(Integer num) {
		//boardDao.updateViews(num);
		BoardVO tmp = boardDao.getBoard(num);
		if(tmp != null) {
			int oldViews = tmp.getViews();
			tmp.setViews(oldViews + 1);
			boardDao.updateBoard(tmp);
		}
		
	}
//	내 코드
//	@Override
//	public boolean boardModify(BoardVO bVo) {
//		if(bVo == null)
//			return false;
//		else boardDao.updateBoard(bVo);
//		return true;
//	}

	@Override
	public void updateBoard(BoardVO bVo, HttpServletRequest request) {
		MemberVO user = (MemberVO)request.getSession().getAttribute("user");
		if(user == null || bVo == null)
			return ;
		if(bVo.getWriter() != null && bVo.getWriter().equals(user.getId()));
			boardDao.updateBoard(bVo);
		return;
	}

	@Override
	public boolean boardRegister(BoardVO bVo) {
		boardDao.boardRegister(bVo);
		return true;
	}

	@Override
	public void deleteBoard(Integer num) {
		if(num == null || num <= 0) return ;
		boardDao.deleteBoard(num);
	}

	@Override
	public boolean isWriter(Integer num,HttpServletRequest r) {
		BoardVO board = boardDao.getBoard(num);
		MemberVO user = (MemberVO)r.getSession().getAttribute("user");
		if(board != null && user != null && board.getWriter().equals(user.getId())) {
			return true;
		}
		return false;
	}
}
