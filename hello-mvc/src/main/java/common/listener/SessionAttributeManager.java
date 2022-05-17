package common.listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

/**
 * Application Lifecycle Listener implementation class SessionAttributeManager
 *
 */
@WebListener
public class SessionAttributeManager implements HttpSessionAttributeListener {

    /**
     * Default constructor. 
     */
    public SessionAttributeManager() {
    	
    }

	/**
     * @see HttpSessionAttributeListener#attributeAdded(HttpSessionBindingEvent)
     */
    public void attributeAdded(HttpSessionBindingEvent se)  { 
    	String name = se.getName();  // 현재 추가된 속성
    	Object value = se.getValue();
    	System.out.println("> 세션 속성 추가! " + name + " = " + value);
    	
    	// 로그인한 사용자에 대한 별도의 처리
    	// 여기에서 아래와 같이 작성하면 됨. 
    	if("loginMember".equals(name)) {
    		// 사용자 로그인 내역 관리
    	}
    }

	/**
     * @see HttpSessionAttributeListener#attributeRemoved(HttpSessionBindingEvent)
     */
    public void attributeRemoved(HttpSessionBindingEvent se)  { 
    	String name = se.getName();  
    	Object value = se.getValue();
    	System.out.println("> 세션 속성 제거! " + name + " = " + value);
    	
    	// 로그아웃에 대한 별도의 처리
    	// 여기에서 아래와 같이 작성하면 됨. 
//    	if("loginMember".equals(name)) {
//    		
//    	}
    }

	/**
     * @see HttpSessionAttributeListener#attributeReplaced(HttpSessionBindingEvent)
     */
    public void attributeReplaced(HttpSessionBindingEvent se)  { 
    	String name = se.getName();  // 현재 추가된 속성
    	Object value = se.getValue();
    	System.out.println("> 세션 속성 대체! " + name + " = " + value);
    }
	
}
