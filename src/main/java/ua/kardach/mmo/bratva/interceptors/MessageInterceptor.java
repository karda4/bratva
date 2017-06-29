package ua.kardach.mmo.bratva.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import ua.kardach.mmo.bratva.util.Message;

public class MessageInterceptor extends HandlerInterceptorAdapter{
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		Message message = (Message)request.getSession().getAttribute("message");
		if(message == null){
			message = new Message();
			request.getSession().setAttribute("message", message);
		}
		return true;
	}
}
