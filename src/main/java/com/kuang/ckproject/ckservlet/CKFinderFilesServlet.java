package com.kuang.ckproject.ckservlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.FileSystemException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.util.UriUtils;
import org.springframework.web.util.WebUtils;

import com.kuang.ckproject.config.Global;


/**
 * 查看CK上传的图片
 *
 * @author wenin819@gmail.com
 */
public class CKFinderFilesServlet extends HttpServlet {

	private static final long serialVersionUID = 4595639013502930224L;
	private static final String userfilesPath = "/userfiles/";
	private Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * 直接访问CKFinder上传的文件
	 *
	 * @param req
	 * @param resp
	 */
	public void getFile(HttpServletRequest req, HttpServletResponse resp) {
		String filepath = req.getRequestURI();
		int index = filepath.indexOf(userfilesPath);
		if (index > 0) {
			filepath = filepath.substring(index + userfilesPath.length());
		}
		try {
			filepath = UriUtils.decode(filepath, "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			logger.error(String.format("解释图片文件路径失败，URL地址为%s", filepath), e1);
		}
		File file = null;
		if (req.getContextPath().equals("")) {
			file = new File(Global.getCkBaseDir() + filepath);
		} else {
			file = new File(Global.getCkBaseDir() + userfilesPath + filepath);
		}

		Exception exception = null;

		try {
			if (!file.getCanonicalPath().startsWith(new File("D:/").getAbsolutePath())) {
				exception = new FileSystemException("请查看正确的文件路径");
				return;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			FileCopyUtils.copy(new FileInputStream(file), resp.getOutputStream());
			resp.setHeader("Content-Type", "application/octet-stream");
			return;
		} catch (FileNotFoundException e) {
			exception = new FileNotFoundException("请求的文件不存在");
			if (logger.isWarnEnabled()) {
				logger.warn(String.format("请求的文件%s不存在", file), e);
			}
		} catch (IOException e) {
			exception = new IOException("输出文件出错，请联系管理员", e);
			if (logger.isErrorEnabled()) {
				logger.error(String.format("输出文件%s出错", file), e);
			}
		}
		try {
			if (null != exception) {
				WebUtils.exposeErrorRequestAttributes(req, exception, "cKFinderFilesServlet");
			}
			req.getRequestDispatcher("/WEB-INF/views/error/500.jsp").forward(req, resp);
		} catch (Exception e) {
			logger.error("跳转500网页出错", e);
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		getFile(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		getFile(req, resp);
	}
}
