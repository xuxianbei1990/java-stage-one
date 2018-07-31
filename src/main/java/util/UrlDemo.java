package util;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class UrlDemo {

	public static void main(String[] args) {
		try {
			URL url = new URL("http://www.runoob.com/index.html?language=cn&dd=cc&xm=adk#j2se");
			System.out.println("URL 为：" + url.toString());
			System.out.println("协议为：" + url.getProtocol());
			System.out.println("验证信息：" + url.getAuthority());
			System.out.println("文件名及请求参数：" + url.getFile());
			System.out.println("主机名：" + url.getHost());
			System.out.println("路径：" + url.getPath());
			System.out.println("端口：" + url.getPort());
			System.out.println("默认端口：" + url.getDefaultPort());
			System.out.println("请求参数：" + url.getQuery());
			System.out.println("定位位置：" + url.getRef());
			Map<String, String> map = DecodeUrlQuery(url.getQuery());
			System.out.println("请求参数：" + map.toString());
			System.out.println("请求参数：" + map.get("dd"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 解析参数
	private static Map<String, String> DecodeUrlQuery(String params) {
		String[] strs = params.split("&");
		Map<String, String> map = new HashMap<String, String>();
		for (String str : strs) {
			String[] strs1 = str.split("=");
			map.put(strs1[0], strs1[1]);
		}
		return map;
	}

}
