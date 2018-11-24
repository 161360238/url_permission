package cn.wj.controller.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import cn.wj.bean.ActiveUser;
import cn.wj.bean.SysPermission;
import cn.wj.util.ResourcesUtil;

public class PermissionInterceptor implements HandlerInterceptor{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object arg2) throws Exception {
		String url=request.getRequestURI(); //得到请求的url
		//判断是否是公开地址
		List<String> open_urls=ResourcesUtil.gekeyList("/config/anonymousURL");
		for(String open_url:open_urls){
			if(url.indexOf(open_url)>=0){
				return true;
			}
		}
		//判断是否是登录后的·访问地址
		List<String> common_urls=ResourcesUtil.gekeyList("/config/commonURL");
		for(String common_url:common_urls){
			if(url.indexOf(common_url)>=0){
				return true;
			}
		}
		//获取session
		HttpSession session=request.getSession();
		ActiveUser activeUser=(ActiveUser) session.getAttribute("activeUser");
		List<SysPermission > sysPermissions=activeUser.getPermissions();
		for(SysPermission sysPermission:sysPermissions){
			String permission_url=sysPermission.getUrl();
			if(url.indexOf(permission_url)>=0){
				return true;
			}
		}
		//执行到这里拦截，跳转到无权访问的提示页面
		request.getRequestDispatcher("/WEB-INF/jsp/refuse.jsp").forward(request, response);
		
		//如果返回false表示拦截不继续执行handler，如果返回true表示放行
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
