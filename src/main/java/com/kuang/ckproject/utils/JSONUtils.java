package com.kuang.ckproject.utils;

import java.util.HashMap;

import net.sf.json.JSONObject;

/**
 * @Author: kuangxiang
 * @Description: 将map转成json
 * @Date: Create in 9:32 2019/6/10
 * @Modified By:
 */
public class JSONUtils {
	public static String beanToJson(HashMap<String, Object> map ) {
		JSONObject json = JSONObject.fromObject(map);
		return json.toString();
	}
}
