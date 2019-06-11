package com.kuang.ckproject.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: kuangxiang
 * @Description:
 * @Date: Create in 8:56 2019/6/4
 * @Modified By:
 */
@Controller
public class CkController {

	@RequestMapping("/ck.htm")
	public String ck(HttpServletRequest request) {
		return "ck";
	}
}
