package com.gzhc365.test;

import java.net.URL;

import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

public class testSound {
	public static void main(String[] args) {
		String path = "http://tsn.baidu.com/text2audio?tex=%25E5%25BE%25AE%25E4%25BF%25A1%25E5%2588%25B0%25E8%25B4%25A61000%25E5%2585%2583&per=0&spd=5&pit=5&vol=5&cuid=1234567JAVA&tok=24.fac33d9721c6b8cbbef682c9ef15c082.2592000.1552652245.282335-15546887&aue=6&lan=zh&ctp=1";
		URL url;
		try {
			url = new URL(path);
			AudioStream as = new AudioStream(url.openStream());
			AudioPlayer.player.start(as);// 用静态成员player.start播放音乐
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
