package board.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.dto.BoardDTO;
import board.model.service.BoardService;
import common.file.FileDTO;
import common.file.FileUtil;
import common.file.MultiPartParams;

/**
 * Servlet implementation class BoardController
 */
@WebServlet("/board/*")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BoardService boardService = new BoardService();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String[] uriArr = request.getRequestURI().split("/");
		switch (uriArr[uriArr.length-1]) {
		
		case "board-list": 
			boardList(request,response);
			break;
		case "board-form": 
			boardForm(request,response);
			break;
		case "upload": 
			uplodad(request,response);
			break;
		case "board-detail": 
			boardDetail(request,response);
			break;
		case "board-delete": 
			boardDelete(request,response);
			break;
		case "board-modify": 
			boardModify(request,response);
			break;
		case "modify": 
			modify(request,response);
			break;
			
		default:
		}
	}


	private void modify(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		FileUtil util = new FileUtil();
		MultiPartParams params = util.fileUpload(request);
		
		BoardDTO board = new BoardDTO();
		board.setBdIdx(Integer.parseInt(request.getParameter("bdIdx")));
		board.setContent(params.getParameter("content"));
		board.setTitle(params.getParameter("title"));
		board.setWriter(params.getParameter("writer"));
		
		List<FileDTO> fileDTOs = params.getFilesInfo();
		
		boardService.updateBoard(board,fileDTOs);
		
	}

	private void boardModify(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/board/board-modify").forward(request, response);
	}

	private void boardDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boardService.deleteBoard(Integer.parseInt(request.getParameter("bdIdx")));
		response.sendRedirect("/board/board-list");
		
	}

	private void boardDetail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int bdIdx = Integer.parseInt(request.getParameter("bdIdx"));
		
		Map<String,Object> boardDetail = boardService.selectBoardDetail(bdIdx);
		
		request.setAttribute("boardDetail", boardDetail);
		request.getRequestDispatcher("/board/board-detail").forward(request, response);
		
	}
	
	private void uplodad(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		FileUtil util = new FileUtil();
		MultiPartParams params = util.fileUpload(request);
		
		BoardDTO board = new BoardDTO();
		board.setContent(params.getParameter("content"));
		board.setPassword(Integer.parseInt(params.getParameter("password")));
		board.setTitle(params.getParameter("title"));
		board.setWriter(params.getParameter("writer"));
		
		List<FileDTO> fileDTOs = params.getFilesInfo();
		boardService.insertBoard(board, fileDTOs);
		response.sendRedirect("/");
	}

	private void boardForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/board/board-form").forward(request, response);
	}

	private void boardList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("boardList", boardService.selectBoardList());
		request.getRequestDispatcher("/board/board-list").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
