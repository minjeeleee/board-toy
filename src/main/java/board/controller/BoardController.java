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
			
		default:
		}
	}


	private void boardModify(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		FileUtil util = new FileUtil();
		MultiPartParams params = util.fileUpload(request);
		
		BoardDTO board = new BoardDTO();
		board.setBdIdx(Integer.parseInt(params.getParameter("bdIdx")));
		board.setContent(params.getParameter("content"));
		board.setTitle(params.getParameter("title"));
		
		List<FileDTO> fileDTOs = params.getFilesInfo();
		for (FileDTO fileDTO : fileDTOs) {
			fileDTO.setBdIdx(Integer.parseInt(params.getParameter("bdIdx")));
		}
		System.out.println(board);
		System.out.println(fileDTOs);
		boardService.updateBoard(board,fileDTOs);
		request.setAttribute("msg","수정 되었습니다");
	    request.setAttribute("url", "/");
	    request.getRequestDispatcher("/common/result").forward(request, response);
	}

	private void boardDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		FileUtil util = new FileUtil();
		MultiPartParams params = util.fileUpload(request);
		boardService.deleteBoard(Integer.parseInt(params.getParameter("bdIdx")));
		request.setAttribute("msg","삭제 되었습니다");
	    request.setAttribute("url", "/");
	    request.getRequestDispatcher("/common/result").forward(request, response);
		
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
		board.setPassword(params.getParameter("password"));
		board.setTitle(params.getParameter("title"));
		board.setWriter(params.getParameter("writer"));
		
		List<FileDTO> fileDTOs = params.getFilesInfo();
		
		boardService.insertBoard(board, fileDTOs);
		response.sendRedirect("/board/board-list");
	}

	private void boardForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/board/board-form").forward(request, response);
	}

	private void boardList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nowPage = request.getParameter("page");
		int page = 1;
		if(nowPage != null && !nowPage.equals("")) {
			page = Integer.parseInt(nowPage);
		}
		Map<String,Object> boardList = boardService.selectBoardList(page);
		request.setAttribute("boardList", boardList);
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
