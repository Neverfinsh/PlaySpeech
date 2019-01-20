package com.gzhc365.play;

import java.net.MalformedURLException;

import net.sf.json.JSONObject;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;

public class playVoice {
	
	/*
	 * @Title: strat * @Description: 该方法的主要作用:朗读 * 
	 * @param @param content * @param @param
	 * type 设定文件 0:开始,1停止 
	 * @return 返回类型:void
	 * @throws
	 */
	public static void strat(String content, int type) { // ??
		ActiveXComponent sap = new ActiveXComponent("Sapi.SpVoice"); // Dispatch是做什么的?
		Dispatch sapo = sap.getObject();
		if (type == 0) {
			try {         // 音量 0-100
				sap.setProperty("Volume", new Variant(100)); // 语音朗读速度 -10 到 +10
				sap.setProperty("Rate", new Variant(1.3));
				Variant defalutVoice = sap.getProperty("Voice");
				Dispatch dispdefaultVoice = defalutVoice.toDispatch();
				Variant allVoices = Dispatch.call(sapo, "GetVoices");
				Dispatch dispVoices = allVoices.toDispatch();
				Dispatch setvoice = Dispatch.call(dispVoices, "Item",
						new Variant(1)).toDispatch();
				ActiveXComponent voiceActivex = new ActiveXComponent(
						dispdefaultVoice);
				ActiveXComponent setvoiceActivex = new ActiveXComponent(
						setvoice);
				Variant item = Dispatch.call(setvoiceActivex, "GetDescription"); // 执行朗读
				Dispatch.call(sapo, "Speak", new Variant(content));
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				sapo.safeRelease();
				sap.safeRelease();
			}
		} else { // 停止
			try {
				Dispatch.call(sapo, "Speak", new Variant(content), new Variant(
						2));
			} catch (Exception e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
		}
	}

	public static void dealOrderMqMes(String jsonStr) {
		JSONObject MesgJson = JSONObject.fromObject(jsonStr);
		new playVoice().strat("微信到账1009900.5元", 0);

	}
	
	/**
	 * 
	 * @param
	 * @throws MalformedURLException
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws MalformedURLException,
			InterruptedException {
		     new playVoice().strat("微信到账1009900.5元", 0);
	}

}
