package com.gzhc365.baidutts;

import java.io.IOException;

import com.gzhc365.utils.ConnUtil;
import com.gzhc365.utils.DemoException;
import com.gzhc365.utils.TokenHolder;

public class BaiduTtsMain {

	public static void main(String[] args) throws IOException, DemoException {
		String  soundPathHttp=(new BaiduTtsMain()).createSoundPathHttp("hello");
		
		// BaiduPlayVoice.playAudio(soundPath);
	}

	// 填写网页上申请的appkey 如 $apiKey="g8eBUMSokVB1BHGmgxxxxxx"
	public final String appKey = "ZDx17zGZhuNj5IFfG5CtFm1r";
	// 填写网页上申请的APP SECRET 如 $secretKey="94dc99566550d87f8fa8ece112xxxxx"
	public final String secretKey = "F9sZPtqpYl7Z375kYmh7rRWjRSKwcjfY";
	// text 的内容为"欢迎使用百度语音合成"的urlencode,utf-8 编码
	// 可以百度搜索"urlencode"
	// public final String text = "情人节快乐";
	// 发音人选择, 0为普通女声，1为普通男生，3为情感合成-度逍遥，4为情感合成-度丫丫，默认为普通女声
	public final int per = 0;
	// 语速，取值0-15，默认为5中语速
	public final int spd = 5;
	// 音调，取值0-15，默认为5中语调
	public final int pit = 5;
	// 音量，取值0-9，默认为5中音量
	public final int vol = 5;

	// 下载的文件格式, 3：mp3(default) 4： pcm-16k 5： pcm-8k 6. wav
	public final int aue = 6;

	public final String url = "http://tsn.baidu.com/text2audio"; // 可以使用https

	public String cuid = "1234567JAVA";

	public String  createSoundPathHttp(String mqStr) throws IOException, DemoException {
		
		TokenHolder holder = new TokenHolder(appKey, secretKey,TokenHolder.ASR_SCOPE);
		holder.refresh();
		String token = holder.getToken();
		// 此处2次urlencode， 确保特殊字符被正确编码
		String params = "tex=" + ConnUtil.urlEncode(ConnUtil.urlEncode(mqStr));
		params += "&per=" + per;
		params += "&spd=" + spd;
		params += "&pit=" + pit;
		params += "&vol=" + vol;
		params += "&cuid=" + cuid;
		params += "&tok=" + token;
		params += "&aue=" + aue;
		params += "&lan=zh&ctp=1";
		String  soundPathHttp=(url + "?" + params);
		return   soundPathHttp;
	}

	// 下载的文件格式, 3：mp3(default) 4： pcm-16k 5： pcm-8k 6. wav
	private String getFormat(int aue) {
		String[] formats = { "mp3", "pcm", "pcm", "wav" };
		return formats[aue - 3];
	}
}