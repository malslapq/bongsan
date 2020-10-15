package com.myboard.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.parser.ParseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.myboard.dto.BoardDTO;
import com.myboard.dto.PageDTO;
import com.myboard.service.BFileService;
import com.myboard.service.BoardService;

@Controller
@RequestMapping("/board")
@SessionAttributes("pdto") 
public class BoardController {

	@Resource 
	private BoardService bservice;
	
	@Resource 
	private BFileService fservice;
	
	//메인 폼으로 이동
	@RequestMapping("/")
	public String listMove(PageDTO pdto, Model model) throws Exception {
		//@SessionAttributes("pdto") 생성
		model.addAttribute("pdto", pdto);
		return "board/main";
	}
	
	//조회
	//@ModelAttribute("pdto") : view까지 정보 전달
	//@ModelAttribute 는 @SessionAttributes의 값 세팅
	@RequestMapping("/list")
	public void boardList(@ModelAttribute("pdto") PageDTO pdto, Model model) throws Exception {
		List<BoardDTO> blist = bservice.selectList(pdto);
		model.addAttribute("blist", blist);
	}

	
	// like dislike insert,update
	@RequestMapping(value = "/{bnum}", method = RequestMethod.GET)
	public ResponseEntity<BoardDTO>  likeupdate(@PathVariable("bnum") int bnum, HttpSession session) throws Exception {
		String userid = (String)session.getAttribute("userid");
		bservice.likeupdate(bnum, userid);
		Map<String, Object> map = bservice.selectOne(bnum);
		BoardDTO bdto = (BoardDTO) map.get("board");
		return new ResponseEntity<BoardDTO>(bdto, HttpStatus.OK);
	}
	@RequestMapping(value = "/{bnum}", method = RequestMethod.POST)
	public ResponseEntity<BoardDTO> dislikeupdate(@PathVariable("bnum") int bnum, HttpSession session) throws Exception {
		String userid = (String)session.getAttribute("userid");
		bservice.dislikeupdate(bnum, userid);
		Map<String, Object> map = bservice.selectOne(bnum);
		BoardDTO bdto = (BoardDTO) map.get("board");
		return new ResponseEntity<BoardDTO>(bdto, HttpStatus.OK);
	}
	
	//추가폼으로
	@RequestMapping(value="/add", method = RequestMethod.GET)
	public void boardAdd(Model model, HttpSession session) throws Exception {
		String userid = (String) session.getAttribute("userid");
		model.addAttribute("userid", userid);
	}
	
	//추가
	@RequestMapping(value="/add", method = RequestMethod.POST)
	public String boardAdd(BoardDTO bdto, List<MultipartFile> bfiles, RedirectAttributes rattr) throws Exception {
		bservice.insert(bdto, bfiles);
		rattr.addFlashAttribute("msg", "추가완료");
		return "redirect:/board/list";		
	}
	
	//한건조회후 상세페이지로 이동
	@RequestMapping("/detail")
	public void boardDetail(int bnum, Model model, HttpSession session) throws Exception {
		//조회수 +1
		bservice.readcnt_update(bnum);
		Map<String, Object> resultMap = bservice.selectOne(bnum);
		model.addAttribute("board", resultMap.get("board"));
		model.addAttribute("flist", resultMap.get("flist"));
		model.addAttribute("userid", session.getAttribute("userid"));
	}
	
	//삭제
	@RequestMapping("/delete")
	public String boardDelete(int bnum, Model model, RedirectAttributes rattr) throws Exception {
		bservice.delete(bnum);
		rattr.addFlashAttribute("msg", "삭제완료");
		return "redirect:/board/list";
	}
	
	//수정폼으로
	@RequestMapping(value="/modify", method = RequestMethod.GET)
	public void boardModify(int bnum, Model model) throws Exception {
		Map<String, Object> resultMap = bservice.selectOne(bnum);
		model.addAttribute("board", resultMap.get("board"));
		model.addAttribute("flist", resultMap.get("flist"));
	}
	
	//수정
	@RequestMapping(value="/modify", method = RequestMethod.POST)
	public String boardModify(BoardDTO bdto,
			@RequestParam(value="fnum", required = false) List<Integer> fnumList,
			List<MultipartFile> bfiles,
			RedirectAttributes rattr) throws Exception {
		bservice.update(bdto,fnumList,bfiles );
		rattr.addFlashAttribute("msg", "수정완료");
		rattr.addAttribute("bnum", bdto.getBnum());
		return "redirect:/board/list";
	}
	
	//파일 다운로드
	@RequestMapping("/filedownload")
	public void fileDownload(String filename, HttpServletResponse response) throws Exception {
		fservice.fileDownload(filename, response);
	}
	
	//세션 삭제(@SessionAttributes("pdto"))
	@RequestMapping("/sessionDelete")
	public String sessionDelete(SessionStatus status) {
		//세션을 지운다
		status.setComplete();
		return "redirect:/board/";
	}
	
	@RequestMapping(value="/company", method = RequestMethod.GET)
	public void companyform() throws Exception {}
	
	@RequestMapping("/mapgeo")
	@ResponseBody
	public Map<String, Double> geocodingFind(@RequestParam String addr) throws IOException, ParseException {
		
		Map<String, Double> resultmap = bservice.getGeocoding(addr);
		return resultmap;
	}
}
