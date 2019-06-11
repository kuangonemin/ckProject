package com.kuang.ckproject.utils;

import java.util.HashMap;
import java.util.Map;

import org.springframework.util.StringUtils;


/**
 * @Author: kuangxiang
 * @Description: 返回Json格式的信息给CKeditor
 * @Date: Create in 9:06 2019/6/10
 * @Modified By:
 */
public class FileResponse extends HashMap<String, Object> {
	Map<String,Object> msgMap = new HashMap<>();
	public String error(int code, String msg){
		FileResponse result = new FileResponse();
		msgMap.put("message",msg);
		result.put("uploaded",code);
		result.put("error",msgMap);
		return JSONUtils.beanToJson(result);
	}
	public String success(int code, String fileName,String url,String msg){
		FileResponse result = new FileResponse();
		if(!StringUtils.isEmpty(msg)){
			msgMap.put("message",msg);
			result.put("error",msgMap);
		}
		result.put("uploaded",code);
		result.put("fileName",fileName);
		result.put("url",url);
		return JSONUtils.beanToJson(result);
	}
}
