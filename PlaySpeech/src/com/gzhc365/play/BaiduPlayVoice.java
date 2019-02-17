package com.gzhc365.play;

import java.net.URL;

import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

public class BaiduPlayVoice {
	
	 public static  void  playAudio(String soundPath){
			URL url;
			try {
				url = new URL(soundPath);
				AudioStream as = new AudioStream(url.openStream());
				AudioPlayer.player.start(as);// 用静态成员player.start播放音乐
			} catch (Exception e) {
				e.printStackTrace();
			}
	 }
}
