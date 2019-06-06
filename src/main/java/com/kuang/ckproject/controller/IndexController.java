package com.kuang.ckproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: kuangxiang
 * @Description:
 * @Date: Create in 8:50 2019/6/4
 * @Modified By:
 */
@Controller
public class IndexController {

	@RequestMapping("/index.htm")
	public String index() {
		return "index";
	}
}
