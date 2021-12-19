package board.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import board.model.dto.BoardDTO;
import common.db.JDBCTemplate;
import common.exception.DataAccessException;
import common.file.FileDTO;

public class BoardDao {

	JDBCTemplate template = JDBCTemplate.getInstance();
	
	public List<BoardDTO> selectBoardList(Connection conn){
		List<BoardDTO> boardList = new ArrayList<BoardDTO>();
		PreparedStatement pstm = null;
		ResultSet rset = null;
		
		String query = "select * from board";
		try {
			pstm = conn.prepareStatement(query);
			rset = pstm.executeQuery();
			
			while (rset.next()) {
				boardList.add(convertRowToBoard(rset));
			}
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}finally {
			template.close(rset, pstm);
		}
		return boardList;
	}
	
	public BoardDTO selectBoardDetail(int bdIdx,Connection conn){
		BoardDTO board = new BoardDTO();
		PreparedStatement pstm = null;
		ResultSet rset = null;
		
		String query = "select * from board where bd_idx=?";
		try {
			pstm = conn.prepareStatement(query);
			pstm.setInt(1, bdIdx);
			rset = pstm.executeQuery();
			
			if (rset.next()) {
				board = convertRowToBoard(rset);
			}
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}finally {
			template.close(rset, pstm);
		}
		return board;
	}
	
	public int insertBoard(BoardDTO board,Connection conn){
	
		int res = 0;
		PreparedStatement pstm = null;
		
		String query = "insert into board values(sc_bd_idx.nextval,?,sysdate,?,?,0,0,?)";
		try {
			pstm = conn.prepareStatement(query);
			pstm.setString(1, board.getWriter());
			pstm.setString(2, board.getTitle());
			pstm.setString(3, board.getContent());
			pstm.setInt(4, board.getPassword());
			res = pstm.executeUpdate();
			
			
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}finally {
			template.close(pstm);
		}
		return res;
	}
	
	public int updateBoard(BoardDTO board,Connection conn){
		
		int res = 0;
		PreparedStatement pstm = null;
		
		String query = "update  board set title=?, content=? where bd_idx=?";
		try {
			pstm = conn.prepareStatement(query);
			pstm.setString(1, board.getTitle());
			pstm.setString(2, board.getContent());
			pstm.setInt(3, board.getBdIdx());
			res = pstm.executeUpdate();
			
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}finally {
			template.close(pstm);
		}
		return res;
	}
	
	public int deleteBoard(int bdIdx,Connection conn){
		
		int res = 0;
		PreparedStatement pstm = null;
		
		String query = "update  board set isDel=1 where bd_idx=?";
		try {
			pstm = conn.prepareStatement(query);
			pstm.setInt(1, bdIdx);
			res = pstm.executeUpdate();
			
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}finally {
			template.close(pstm);
		}
		return res;
	}
	
	public int updateViews(int bdIdx,Connection conn){
		
		int res = 0;
		PreparedStatement pstm = null;
		
		String query = "update  board set view=view+1 where bd_idx=?";
		try {
			pstm = conn.prepareStatement(query);
			pstm.setInt(1, bdIdx);
			res = pstm.executeUpdate();
			
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}finally {
			template.close(pstm);
		}
		return res;
	}
	
	private BoardDTO convertRowToBoard(ResultSet rset) throws SQLException {
		BoardDTO board = new BoardDTO();
		board.setBdIdx(rset.getInt("bd_idx"));
		board.setContent(rset.getString("content"));
		board.setIsDel(rset.getInt("is_del"));
		board.setPassword(rset.getInt("password"));
		board.setRegDate(rset.getDate("reg_date"));
		board.setTitle(rset.getString("title"));
		board.setView(rset.getInt("view"));
		board.setWriter(rset.getString("writer"));
		return board;
	}

	public void insertFile(FileDTO fileDTO, Connection conn) {
		String sql = "insert into file_info (fl_idx,bd_idx,origin_file_name,rename_file_name,save_path) "
				+ "values(sc_fl_idx.nextval,sc_bd_idx.currval,?,?,?)";
		PreparedStatement pstm = null;
		
		try {
			pstm = conn.prepareStatement(sql);
			
			pstm.setString(1, fileDTO.getOriginFileName());
			pstm.setString(2, fileDTO.getRenameFileName());
			pstm.setString(3, fileDTO.getSavePath());
			pstm.executeUpdate();
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}finally {
			template.close(pstm);
		}
		
	}
	public void deleteFile(int bdIdx, Connection conn) {
		int res = 0;
		PreparedStatement pstm = null;
		
		String query = "delete  FROM file_info  where bd_idx=?";
		try {
			pstm = conn.prepareStatement(query);
			pstm.setInt(1, bdIdx);
			res = pstm.executeUpdate();
			
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}finally {
			template.close(pstm);
		}		
	}

	public List<FileDTO> selectFileDTOs(int bdIdx, Connection conn) {
		String sql = "select fl_idx, bd_idx, origin_file_name, rename_file_name"
				+ ", save_path, reg_date"
				+ " from file_info where bd_idx = ?";
		PreparedStatement pstm = null;
		ResultSet rset = null;
		List<FileDTO> files = new ArrayList<FileDTO>();
		
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, bdIdx);
			rset = pstm.executeQuery();
			
			while(rset.next()) {
				FileDTO  fileDTO = new FileDTO();
				fileDTO.setFlIdx(rset.getInt("fl_idx"));
				fileDTO.setBdIdx(rset.getInt("bd_idx"));
				fileDTO.setOriginFileName(rset.getString("origin_file_name"));
				fileDTO.setRenameFileName(rset.getString("rename_file_name"));
				fileDTO.setSavePath(rset.getString("save_path"));
				fileDTO.setRegDate(rset.getDate("reg_date"));
				files.add(fileDTO);
			}
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}finally {
			template.close(rset, pstm);
		}
		
		return files;
	}

	

}
