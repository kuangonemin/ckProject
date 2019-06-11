package com.kuang.ckproject.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.kuang.ckproject.utils.FileResponse;
import com.kuang.ckproject.utils.FileUtils;

/**
 * @Author: kuangxiang
 * @Description:
 * @Date: Create in 8:56 2019/6/10
 * @Modified By:
 */
@Controller
public class CKUploadController {

	/*
	 * 图片命名格式
	 */
	private static final String DEFAULT_SUB_FOLDER_FORMAT_AUTO = "yyyyMMddHHmmss";

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	/* * 上传图片文件夹 */
	private static final String UPLOAD_PATH = "D:/userfiles/images/";

	@RequestMapping("/uploadImg.htm")
	public void uplodaImg(HttpServletRequest request, HttpServletResponse response) {
		FileResponse fileResponse = new FileResponse();
		try {

			CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
			//判断 request 是否有文件上传,即多部分请求
			MultipartFile file = null;
			if(multipartResolver.isMultipart(request)) {
				//转换成多部分request
				MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
				file =  multiRequest.getFile("upload");
			}
			/*MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			CommonsMultipartFile file = (CommonsMultipartFile) multipartRequest
					.getFile("upload");*/
			PrintWriter out = response.getWriter();
			logger.info("fileSize: " + file.getSize());
			// 图片大小不超过2M
			if (file.getSize() > 1024 * 2048) {
				String error = fileResponse.error(0, "图片大小超过2M");
				out.println(error);
				return;
			}
			String proName = request.getContextPath();
			String path = UPLOAD_PATH;
			String fileName = file.getOriginalFilename();
			String uploadContentType = file.getContentType();
			String expandedName = "";
			if (uploadContentType.equals("image/pjpeg") || uploadContentType.equals("image/jpeg")) {
				expandedName = ".jpg";
			} else if (uploadContentType.equals("image/png") || uploadContentType.equals("image/x-png")) {
				expandedName = ".png";
			} else if (uploadContentType.equals("image/gif")) {
				expandedName = ".gif";
			} else if (uploadContentType.equals("image/bmp")) {
				expandedName = ".bmp";
			} else {
				String error = fileResponse.error(0, "文件格式不正确（必须为.jpg/.gif/.bmp/.png文件）");
				out.println(error);
				return;
			}
			DateFormat df = new SimpleDateFormat(DEFAULT_SUB_FOLDER_FORMAT_AUTO);
			fileName = df.format(new Date()) + expandedName;
			FileUtils.uploadFile(file.getBytes(), path, fileName);
			String success = fileResponse.success(1, fileName, proName + "/userfiles/images/" + fileName, null);
			out.println(success);
			return;
		} catch (Exception e) {
			e.printStackTrace();
			String error = fileResponse.error(0, "系统异常");
			try {
				response.getWriter().println(error);
				return;
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
}




