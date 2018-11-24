package cn.wj.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.wj.bean.ActiveUser;
import cn.wj.exception.CustomException;
import cn.wj.service.SysService;

/**
 * 登录和退出
 * @author 王杰
 *
 */
@Controller
public class LoginController {

	@Autowired
	private SysService sysService;
	
	@RequestMapping("login")
	public String login(HttpSession session,String randomcode,String usercode,String password) throws Exception{
		//从session获取正确的验证码
		String validatecode=(String) session.getAttribute("validateCode");
		
		if(!randomcode.equals(validatecode)){
			throw new CustomException("验证码错误");
		}
		ActiveUser activeUser=sysService.authenticat(usercode,password);
		session.setAttribute("activeUser", activeUser);
		return "redirect:/first.action";
	}
	//用户退出
	@RequestMapping("/logout")
	public String logout(HttpSession session)throws Exception{
		
		//session失效
		session.invalidate();
		//重定向到商品查询页面
		return "redirect:/first.action";
		
	}
}
