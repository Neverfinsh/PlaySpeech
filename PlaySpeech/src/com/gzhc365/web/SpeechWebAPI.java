package com.gzhc365.web;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

import net.sf.json.JSONObject;

import com.gzhc365.constants.SpeechConstants;

public class SpeechWebAPI {

	/** 用户登陆 **/
	public static String userLogin(String username, String password)
			throws Exception {
		URL url = new URL(SpeechConstants.WEBSPEECH_API_LOGIN);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.connect();
//		int code = connection.getResponseCode();
//		if (code == 404) {
//			return null;
//		}
//		if (code == 500) {
//			return null;
//		}
//		if (code != 200) {
//			return null;
//		}
		try {
			StringBuffer requestParam = new StringBuffer();
			requestParam.append("account=").append(username)
					.append("&password=").append(password);
			String response = getData(SpeechConstants.WEBSPEECH_API_LOGIN,
					requestParam.toString());
			return response;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/** web-speech 接口 **/
	public static String getData(String url, String request)
			throws UnsupportedEncodingException, IOException {
		String charset = "UTF-8";
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		HttpURLConnection connect = (HttpURLConnection) (new URL(url)
				.openConnection());
		connect.setRequestMethod("POST");
		connect.setDoOutput(true);
		connect.setConnectTimeout(1000 * 10);
		connect.setReadTimeout(1000 * 80);
		connect.setRequestProperty("ContentType",
				"application/x-www-form-urlencoded"); // 采用通用的URL百分号编码的数据MIME类型来传参和设置请求头
		connect.setDoInput(true);
		// 连接
		connect.connect();
		// 发送数据
		connect.getOutputStream().write(request.getBytes(charset));
		// 接收数据
		int responseCode = connect.getResponseCode();
		if (responseCode == HttpURLConnection.HTTP_OK) {
			InputStream in = connect.getInputStream();
			byte[] data = new byte[1024];
			int len = 0;
			while ((len = in.read(data, 0, data.length)) != -1) {
				outStream.write(data, 0, len);
			}
			in.close();
		}
		// 关闭连接
		connect.disconnect();
		String response = outStream.toString("UTF-8");
		return response;
	}
}
