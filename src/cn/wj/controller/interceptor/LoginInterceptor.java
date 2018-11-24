package cn.wj.controller.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import cn.wj.bean.ActiveUser;
import cn.wj.util.ResourcesUtil;

/**
 * 用户身份认证拦截器
 * 
 * @author 王杰
 * 
 */
public class LoginInterceptor implements HandlerInterceptor {

	// 在handler执行之前执行
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		// 得到请求的url
		String url = request.getRequestURI();
		// 判断是否是公开地址
		List<String> open_urls = ResourcesUtil
				.gekeyList("/config/anonymousURL");
		for (String open_url : open_urls) {
			if (url.indexOf(open_url) >= 0) {
				return true;
			}
		}
		// 判断用户身份是否在sessio中
		HttpSession session = request.getSession();
		ActiveUser activeUser = (ActiveUser) session.getAttribute("activeUser");
		if (activeUser != null) {
			return true;
		}
		// 执行到这里拦截，跳转到登陆页面，用户进行身份认证
		request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request,
				response);
		return false;
	}

	@Override
	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {
		// TODO Auto-generated method stub

	}

}
