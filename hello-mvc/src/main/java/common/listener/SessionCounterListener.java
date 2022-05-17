package common.listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Application Lifecycle Listener implementation class SessionCounterListener
 *
 */
//@WebListener
public class SessionCounterListener implements HttpSessionListener {
	
	// 접속하고 있는 사용자 수 체크
	private static int activeSessions;
	
    /**
     * Default constructor. 
     */
    public SessionCounterListener() { // 생성자
    	
    }

	/**
     * @see HttpSessionListener#sessionCreated(HttpSessionEvent)
     * 	- 세션이 생성됐을 때 할 일 
     */
    public void sessionCreated(HttpSessionEvent se)  { 
    	activeSessions++;
    	System.out.println("> 세션생성! 접속사용자수 : " + activeSessions);
    }

	/**
     * @see HttpSessionListener#sessionDestroyed(HttpSessionEvent)
     * 	- 세션이 끝났을 때 할 일
     */
    public void sessionDestroyed(HttpSessionEvent se)  { 
    	if(activeSessions > 0) 
    		activeSessions--;
    	System.out.println("> 세션폐기! 접속사용자수 : " + activeSessions);
    }
	
}
