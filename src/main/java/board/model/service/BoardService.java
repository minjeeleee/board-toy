package board.model.service;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import board.model.dao.BoardDao;
import board.model.dto.BoardDTO;
import common.db.JDBCTemplate;
import common.exception.DataAccessException;
import common.file.FileDTO;


public class BoardService {

	private JDBCTemplate template = JDBCTemplate.getInstance();
	private BoardDao boardDao =  new BoardDao();
	
	public List<BoardDTO> selectBoardList() {
		
		Connection conn = template.getConnection();
		List<BoardDTO> boardList = null;
		try {
			boardList = boardDao.selectBoardList(conn);
			
		}finally {
			template.close(conn);	
		}
		return boardList;
	}
	
	public Map<String, Object> selectBoardDetail(int bdIdx){
		Connection conn = template.getConnection();
		Map<String, Object> boardDetail = new HashMap<String, Object>();		
		
		try {
			boardDao.updateViews(bdIdx, conn);
			BoardDTO board = boardDao.selectBoardDetail(bdIdx,conn);
			List<FileDTO> files = boardDao.selectFileDTOs(bdIdx,conn);
			boardDetail.put("board", board);
			boardDetail.put("files", files);
			
			template.commit(conn);
		}catch (DataAccessException e) {
			template.rollback(conn);
			throw e;
		}finally {
			template.close(conn);	
		}
		return boardDetail;
		
	}
	
	public int insertBoard(BoardDTO board, List<FileDTO> fileDTOs){
		Connection conn = template.getConnection();
		int res =0;
		try {
			res = boardDao.insertBoard(board, conn);
			
			for (FileDTO fileDTO : fileDTOs) {
				boardDao.insertFile(fileDTO,conn);
			}
			template.commit(conn);
		}catch (DataAccessException e) {
			template.rollback(conn);
			throw e;
		}finally {
			template.close(conn);	
		}	
		return res;
	}
	
	public int updateBoard(BoardDTO board, List<FileDTO> fileDTOs){
		Connection conn = template.getConnection();
		int res =0;
		try {
			res = boardDao.updateBoard(board, conn);
			
			boardDao.deleteFile(board.getBdIdx(),conn);
			for (FileDTO fileDTO : fileDTOs) {
				boardDao.updateFile(fileDTO,conn);
			}
			template.commit(conn);
		}catch (DataAccessException e) {
			template.rollback(conn);
			throw e;
		}finally {
			template.close(conn);	
		}	
		
		return res;
	}
	
	public int deleteBoard(int bdIdx){
		Connection conn = template.getConnection();
		int res =0;
		try {
			res = boardDao.deleteBoard(bdIdx, conn);
			template.commit(conn);
		}catch (DataAccessException e) {
			template.rollback(conn);
			throw e;
		}finally {
			template.close(conn);	
		}	
		
		return res;
	}
}
