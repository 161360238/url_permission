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
 * �û������֤������
 * 
 * @author ����
 * 
 */
public class LoginInterceptor implements HandlerInterceptor {

	// ��handlerִ��֮ǰִ��
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		// �õ������url
		String url = request.getRequestURI();
		// �ж��Ƿ��ǹ�����ַ
		List<String> open_urls = ResourcesUtil
				.gekeyList("/config/anonymousURL");
		for (String open_url : open_urls) {
			if (url.indexOf(open_url) >= 0) {
				return true;
			}
		}
		// �ж��û�����Ƿ���sessio��
		HttpSession session = request.getSession();
		ActiveUser activeUser = (ActiveUser) session.getAttribute("activeUser");
		if (activeUser != null) {
			return true;
		}
		// ִ�е��������أ���ת����½ҳ�棬�û����������֤
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
