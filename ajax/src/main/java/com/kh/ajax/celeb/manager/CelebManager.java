package com.kh.ajax.celeb.manager;

import java.util.ArrayList;
import java.util.List;

import com.kh.ajax.celeb.dto.Celeb;
import com.kh.ajax.celeb.dto.CelebType;

/**
 * singleton
 * 	- 프로그램 운영 중 단 하나의 객체만 사용하는 것.
 *
 */
public class CelebManager {
	private static CelebManager instance; // 현재 타입의 객체
	private List<Celeb> celebList = new ArrayList<>();
	
	/**
	 * 생성자가 private이므로 외부에서는 객체 생성 불가
	 */
	private CelebManager(){
		celebList.add(new Celeb(1, "daft punk", CelebType.SINGER, "daftpunk.jpg"));
		celebList.add(new Celeb(2, "hwang", CelebType.COMEDIAN, "hwang.jpg"));
		celebList.add(new Celeb(3, "김고은", CelebType.ACTOR, "goeun.jpg"));
		celebList.add(new Celeb(4, "영케이", CelebType.SINGER, "youngk.jpg"));
		celebList.add(new Celeb(5, "박보검", CelebType.ACTOR, "bogum.jpg"));		
	}
	
	public static CelebManager getInstance() {		
		if(instance == null) 
			instance = new CelebManager();
		
		return instance;
	}

	public List<Celeb> getCelebList() {
		return celebList;
	}
	
	
	
}
