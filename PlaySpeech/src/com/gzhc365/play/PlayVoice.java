package com.gzhc365.play;

import java.net.MalformedURLException;

import net.sf.json.JSONObject;

import com.gzhc365.utils.MoneyUtils;
import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;

public class PlayVoice {

	public void strat(String content, int type) { // ??
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
		String channel="";
		String channelName="";
		String money=MesgJson.get("totalPayFee").toString();
		channel=MesgJson.get("payChannel").toString();
		String reallyMoney=MoneyUtils.formatToYuan(Integer.parseInt(money));
		if(channel.contains("weixin")){
			 channelName="微信到账";
		}else{
			 channelName="支付宝到账";
		}
		new PlayVoice().strat(channelName+reallyMoney+"元", 0);
	}
	/**
	 * 
	 * @param
	 * @throws MalformedURLException
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws MalformedURLException,
			InterruptedException {
		     new PlayVoice().strat("支付宝到账101909.54元", 0);
	}

}
