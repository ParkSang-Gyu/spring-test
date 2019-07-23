package kr.green.test.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import kr.green.test.pagination.Criteria;
import kr.green.test.vo.BoardVO;

public interface BoardService {

	ArrayList<BoardVO> getBoardList(Criteria cri);

	BoardVO getBoard(Integer num);

	void updateViews(Integer num);
	// 내 코드
//	boolean boardModify(BoardVO bVo);

	void updateBoard(BoardVO bVo, HttpServletRequest request);

	boolean boardRegister(BoardVO bVo);

	void deleteBoard(Integer num);

	boolean isWriter(Integer num, HttpServletRequest r);

	int getTotalCount(Criteria cri);
	
}
