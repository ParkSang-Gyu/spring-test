package kr.green.test.dao;

import java.util.ArrayList;

import kr.green.test.pagination.Criteria;
import kr.green.test.vo.BoardVO;

public interface BoardDAO {

	ArrayList<BoardVO> getBoardList(Criteria cri);

	BoardVO getBoard(Integer num);

	void updateViews(Integer num);

	void updateBoard(BoardVO tmp);

	void boardRegister(BoardVO bVo);

	void deleteBoard(Integer num);

	int getTotalCount(Criteria cri);
	
}
