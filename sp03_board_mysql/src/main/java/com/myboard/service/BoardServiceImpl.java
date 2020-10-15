package com.myboard.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.myboard.dao.BoardDAO;
import com.myboard.dto.BoardDTO;
import com.myboard.dto.BoardLikeDTO;
import com.myboard.dto.PageDTO;

@Service
public class BoardServiceImpl implements BoardService{

	@Resource
	private BoardDAO bdao;
	
	@Resource
	private ReplyService rservice;
	
	@Resource
	private BFileService fservice;
	
	@Override
	public List<BoardDTO> selectList(PageDTO pdto) throws Exception {
		//------pdto 구하기----------
		//전체게시물수
		int totCnt = bdao.totolCount(pdto);
		//전체페이지수
		int totPage = totCnt / pdto.getPerPage();
		if ((totCnt % pdto.getPerPage()) != 0 ) totPage = totPage + 1;
		pdto.setTotPage(totPage);
		
		//현재페이지
		int curPage = pdto.getCurPage();
		//시작번호(mysql은 0번 인덱스부터)
		int startNo = (curPage-1) * pdto.getPerPage();
		pdto.setStartNo(startNo);
		//끝번호
		int endNo = startNo + pdto.getPerPage() -1;
		pdto.setEndNo(endNo);
		//시작페이지
		int startPage = curPage - ((curPage-1) % pdto.getPerBlock());
		pdto.setStartPage(startPage);
		//끝페이지
		int endPage = startPage + pdto.getPerBlock()-1;
		if (endPage > totPage) endPage = totPage;
		pdto.setEndPage(endPage);
		
		// 전체조회
		return bdao.selectList(pdto);
	}

	@Override
	public Map<String, Object> selectOne(int bnum) throws Exception {
		Map<String, Object> map = new HashMap<>();
		
		// 게시물 한건조회
		map.put("board", bdao.selectOne(bnum));
		//파일 리스트 조회
		map.put("flist",fservice.selectList(bnum));
		
		return map;
		
	}
	
	//트랜잭션 처리
	@Transactional
	@Override
	public int insert(BoardDTO bdto, List<MultipartFile> bfiles) throws Exception {
		// 게시물 추가
		bdao.insert(bdto);
		int bnum = bdto.getBnum(); //게시물 번호
		//파일 저장
		List<String> filenameList = fservice.fileUpload(bfiles);
		//파일 추가
		fservice.insert(bnum, filenameList);
		return bnum;
	}

	@Override
	public int update(BoardDTO bdto, List<Integer> fnumList,List<MultipartFile> bfiles) throws Exception {
		// 게시물 수정
		bdao.update(bdto);
		int bnum = bdto.getBnum();
		//파일 삭제
		fservice.delete_part(bnum,fnumList);
		//파일 저장
		List<String> filenameList = fservice.fileUpload(bfiles);
		//파일 추가
		fservice.insert(bnum, filenameList);
		return 0;
	}

	@Transactional
	@Override
	public int delete(int bnum) throws Exception {
		//주의 : 자식테이블 삭제후 부모테이블 삭제(foreign key관계)
		//좋아요, 싫어요 삭제
		bdao.likedelete(bnum);
		//파일 삭제
		fservice.delete(bnum);
		//댓글 삭제
		rservice.deleteAll(bnum);
		// 게시물 삭제
		bdao.delete(bnum);
		return 0;
	}

	@Override
	public int readcnt_update(int bnum) throws Exception {
		// 조회수 +1
		return bdao.readcnt_update(bnum);
	}

	@Override
	public int replycntUp_update(int bnum) throws Exception {
		// 댓글수 +1
		return bdao.replycntUp_update(bnum);
	}

	@Override
	public int replycntDown_update(int bnum) throws Exception {
		// 댓글수 -1
		return bdao.replycntDown_update(bnum);
	}

	@Override
	public Map<String, Double> getGeocoding(String addr) throws IOException, ParseException {
		//리턴객체 생성
		Map<String, Double> map = new HashMap<String, Double>();
//		System.out.println("getGeocoding:"+addr);
		addr = URLEncoder.encode(addr, "utf-8");
		//Geocoding api호출
		String api = "https://naveropenapi.apigw.ntruss.com/map-geocode/v2/geocode?query="+addr;
		URL url = new URL(api);
		HttpURLConnection http = (HttpURLConnection) url.openConnection();
		http.setRequestProperty("Content-Type", "application/json");
		http.setRequestProperty("X-NCP-APIGW-API-KEY-ID", "qsul5hbdjr");
		http.setRequestProperty("X-NCP-APIGW-API-KEY", "YyZ6xgGFLdM2Wyd0UWkPg5pOEiGt07PwYM3ZIl56");
		http.setRequestMethod("GET");
		http.connect();
		BufferedReader br =
				new BufferedReader(new InputStreamReader(http.getInputStream(), "utf-8"));
		
		String rdData;
		while ((rdData = br.readLine())!= null ) {
			//System.out.println(rdData);
			JSONObject object = (JSONObject)new JSONParser().parse(rdData);
			JSONArray array = (JSONArray)object.get("addresses");
			for(int i = 0; i < array.size(); i++) {
				object = (JSONObject)array.get(i);
//				String roadAddress = (String) object.get("roadAddress");
				// x = 경도, y = 위도
				double x = Float.parseFloat((String) object.get("x"));
				double y = Float.parseFloat((String) object.get("y"));
//				System.out.println("도로명 : "+roadAddress+", 위도 : "+y+", 경도 :"+x);
				map.put("x", x);
				map.put("y", y);
			}
		}
		return map;
	}

	@Transactional
	@Override
	public void likeupdate(int bnum, String userid) throws Exception {
		BoardLikeDTO ldto = new BoardLikeDTO();
		BoardDTO bdto = new BoardDTO();
		bdto.setBnum(bnum);
		ldto.setBnum(bnum);
		ldto.setUserid(userid);
		ldto.setLikecnt(1);
		//테이블에 데이터가 없을 경우
		if (bdao.likecheck(ldto) == null) {
			//boardlike 테이블 
			bdao.likeinsert(ldto);
			bdto.setLikecnt(1);
			//board 테이블
			bdao.boardLcntupdate(bdto);
		}
		else {
			//테이블에 데이터가 있을 경우
			ldto = bdao.likecheck(ldto);
			if (ldto.getLikecnt() == 1) {
				bdto.setLikecnt(-1);
				ldto.setLikecnt(0);
			}
			else {
				bdto.setLikecnt(1);
				ldto.setLikecnt(1);
			}
			bdao.boardLcntupdate(bdto);
			bdao.likeupdate(ldto);
		}
	}
	
	@Transactional
	@Override
	public void dislikeupdate(int bnum, String userid) throws Exception {
		BoardLikeDTO ldto = new BoardLikeDTO();
		BoardDTO bdto = new BoardDTO();
		bdto.setBnum(bnum);
		ldto.setBnum(bnum);
		ldto.setUserid(userid);
		ldto.setDislikecnt(1);
		if (bdao.likecheck(ldto) == null) {
			bdao.dislikeinsert(ldto);
			bdto.setDislike(1);
			bdao.boardDlcntupdate(bdto);
		}
		else {
			ldto = bdao.likecheck(ldto);
			if (ldto.getDislikecnt() == 1) {
				ldto.setDislikecnt(0);
				bdto.setDislike(-1);
			}
			else {
				ldto.setDislikecnt(1);
				bdto.setDislike(1);
			}
			bdao.boardDlcntupdate(bdto);
			bdao.dislikeupdate(ldto);
		}
	}

	@Override
	public BoardLikeDTO likecheck(int bnum, String userid) throws Exception {
		BoardLikeDTO ldto = new BoardLikeDTO();
		ldto.setBnum(bnum);
		ldto.setUserid(userid);
		return bdao.likecheck(ldto);
	}

}
