package cn.wj.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FirstAction {

	//ȥϵͳ��ҳ
	@RequestMapping("/first")
	public String first(Model model){
		return "/first";
	}
	//��ӭҳ
	@RequestMapping("/welcome")
	public String welcome(Model model){
		return "/welcome";
	}
}
