package common;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Base64.Encoder;

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
	
	
}
