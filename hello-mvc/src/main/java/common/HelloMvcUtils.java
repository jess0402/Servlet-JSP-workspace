package common;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.List;
import java.util.Base64.Encoder;

import javax.servlet.http.HttpServletResponse;

import board.model.dto.Attachment;

public class HelloMvcUtils {

	/**
	 * 단방향 알고리즘 중 우리가 쓸 수 있는 것.
	 * 	- SHA256
	 * 	- SHA512
	 * 	- SHA1 또는 MD5는 사용하지 말것.
	 * 
	 * @param password
	 * @param salt 
	 * @return
	 */
	public static String encrypt(String password, String salt) {
		
		// 1. 암호화 Hasing
		MessageDigest md = null;
		byte[] encrypted = null;
		try {
			md = MessageDigest.getInstance("SHA-512");
			byte[] input = password.getBytes("utf-8");
			byte[] saltBytes = salt.getBytes("utf-8");
			md.update(saltBytes);   // salt 값으로 MessageDigest 객체 갱신
			encrypted = md.digest(input); // MessageDigest 객체에 raw password 전달 및 hasing
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		// 2. 인코딩 (단순문자변환)
		Encoder encoder = Base64.getEncoder();
		
		
		return encoder.encodeToString(encrypted);
	}

	/**
	 * 
	 * @param cPage
	 * @param numPerPage (10으로 고정)
	 * @param totalContents (현재: 115)
	 * @param url /mvc/admin/memberList
	 * @return
	 * 
	 * 1 2 3 4 5
	 * 6 7 8 9 10
	 * 11 12
	 * 
	 * prev 없음 
	 * 
	 * pageNo 영역
	 * <span class='cPage'>1</span>
	 * <a href='/mvc/admin/memberList?cPage=2'>2</a>
	 * <a href='/mvc/admin/memberList?cPage=3'>3</a>
	 * <a href='/mvc/admin/memberList?cPage=4'>4</a>
	 * <a href='/mvc/admin/memberList?cPage=5'>5</a>
	 * 
	 * next 영역
	 * <a href='/mvc/admin/memberList?cPage=6'>next</a>
	 */
	public static String getPagebar(int cPage, int numPerPage, int totalContents, String url) {
		StringBuilder pagebar = new StringBuilder();
		int totalPages = (int) Math.ceil((double) totalContents / numPerPage);  // 전체 페이지수
		int pagebarSize = 5;
		// pagebarSize는 maximum 5이다.
		// prev 1 2 3 4 5 next -> prev 6 7 8 9 10 next -> prev 11 12 next
		int pagebarStart = (cPage - 1) / pagebarSize * pagebarSize + 1; // 1, 6, 11
		int pagebarEnd = pagebarStart + pagebarSize - 1; // 5, 10, 15
		int pageNo = pagebarStart;
		
		url += "?cPage=";
		
		// 이전 prev
		if(pageNo == 1) {
			// prev 버튼 비활성화
		}
		else {
			// prev 버튼 활성화
			pagebar.append("<a href='" + url + (pageNo - 1) + "'>prev</a>");
			pagebar.append("\n");
		}
		
		// 번호
		while(pageNo <= pagebarEnd && pageNo <= totalPages) {
			if(pageNo == cPage) {
				// 현재페이지인 경우
				pagebar.append("<span class='cPage'>" + pageNo + "</span>");
				pagebar.append("\n");
			}
			else {
				// 현재페이지가 아닌 경우(링크필요)
				pagebar.append("<a href='" + url + pageNo + "'>" + pageNo +"</a>");
				pagebar.append("\n");
			}
			pageNo++;
		}
		
		// 다음 next
		if(pageNo > totalPages) {
			// 이동할 필요 X
		}
		else {
			pagebar.append("<a href='" + url + pageNo + "'>next</a>");
			pagebar.append("\n");
		}
		
		return pagebar.toString();
	}
	
	public static void fileDownload(HttpServletResponse response, String saveDirectory, String originalFilename,
			String renamedFilename) throws UnsupportedEncodingException, IOException, FileNotFoundException {
		// 헤더작성
		response.setContentType("application/octet-stream");  // 응답데이터 타입
		// Content-Disposition 첨부파일인 경우, 다운로드(Save as)처리 명시
		String resFilename = new String(originalFilename.getBytes("utf-8"), "iso-8859-1");  // tomcat 기본 인코딩
		response.setHeader("Content-Disposition", "attachment;filename=" + resFilename);
		System.out.println("resFilename = " + resFilename);
		
		// html로 리다이렉트 하는 것 X jsp 위임 X
		// 실제 파일을 읽어서(input) 응답 메세지에 출력(output)(쓴다)
		File file = new File(saveDirectory, renamedFilename);
		// 기본스트림 - 대상과 연결
		// 보조스트림 - 기본스트림과 연결, 보조스트림을 제어
		try(
				BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
				BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream());
		){
			byte[] buffer = new byte[8192];
			int len = 0;  // 읽어낸 byte 수
			while((len = bis.read(buffer)) != -1) { // 읽어낸 개수가 양수라면
				bos.write(buffer, 0, len);  // buffer의 0번지부터 len(읽은 개수)만치 출력
			}
		}
	}
	
	
}
