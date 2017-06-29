package ua.kardach.mmo.bratva.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import ua.kardach.mmo.bratva.model.User;
import ua.kardach.mmo.bratva.model.service.PersonageService;

public class PersonageRefreshInterceptor extends HandlerInterceptorAdapter{
	
	@Autowired
	private PersonageService personageService;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		User user = (User) request.getSession().getAttribute("hero");
		if (user != null) {
			personageService.refreshPersonage(user.getPersonage());
		}
		return true;
	}
}
