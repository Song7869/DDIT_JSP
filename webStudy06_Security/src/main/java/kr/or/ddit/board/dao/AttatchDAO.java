package kr.or.ddit.board.dao;

import org.springframework.stereotype.Repository;

import kr.or.ddit.vo.AttatchVO;
import kr.or.ddit.vo.BoardVO;

/**
 * Attatch 테이블을 대상으로 한 CRD
 *
 */
@Repository
public interface AttatchDAO {
	public int insertAttatches(BoardVO board);
	/**
	 * 다운로드시 사용할 하나의  첨부파일에 대한 메타데이터 조회
	 * @param attNo
	 * @return
	 */
	public AttatchVO selectAttatch(int attNo);
	public int deleteAttatches(BoardVO board);
}
