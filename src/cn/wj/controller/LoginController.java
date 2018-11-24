package cn.wj.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.wj.bean.ActiveUser;
import cn.wj.exception.CustomException;
import cn.wj.service.SysService;

/**
 * ��¼���˳�
 * @author ����
 *
 */
@Controller
public class LoginController {

	@Autowired
	private SysService sysService;
	
	@RequestMapping("login")
	public String login(HttpSession session,String randomcode,String usercode,String password) throws Exception{
		//��session��ȡ��ȷ����֤��
		String validatecode=(String) session.getAttribute("validateCode");
		
		if(!randomcode.equals(validatecode)){
			throw new CustomException("��֤�����");
		}
		ActiveUser activeUser=sysService.authenticat(usercode,password);
		session.setAttribute("activeUser", activeUser);
		return "redirect:/first.action";
	}
	//�û��˳�
	@RequestMapping("/logout")
	public String logout(HttpSession session)throws Exception{
		
		//sessionʧЧ
		session.invalidate();
		//�ض�����Ʒ��ѯҳ��
		return "redirect:/first.action";
		
	}
}
