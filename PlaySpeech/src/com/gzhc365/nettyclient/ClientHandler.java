package com.gzhc365.nettyclient;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;

import java.net.InetSocketAddress;
import java.util.Calendar;
import java.util.Date;

import net.sf.json.JSONObject;

import com.gzhc365.constants.SpeechConstants;
import com.gzhc365.play.PlayVoice;
import com.ibm.icu.text.SimpleDateFormat;

public class ClientHandler extends SimpleChannelInboundHandler<String> {

	private  String msg;
	
   private  Bootstrap bootStrap;
	
	
	public ClientHandler(String msg, Bootstrap bootStrap) {
		super();
		this.msg = msg;
		this.bootStrap = bootStrap;
	}

		@Override  
	    public void channelRead0(ChannelHandlerContext ctx, String message) throws Exception { 
			JSONObject json = JSONObject.fromObject(message);
		
		if ("heartBeat".equals(json.getString("tradeType"))) {
			JSONObject reqJson = new JSONObject();
			reqJson.put("tradeType", "heartBeat");
			reqJson.put("timeStamp", Calendar.getInstance().getTimeInMillis());
			System.out.println("heartBeat: 来自服务端的心跳："+reqJson.toString());
			ctx.writeAndFlush(reqJson.toString());
		}
		if ("login".equals(json.getString("tradeType"))) {
			ctx.writeAndFlush(msg);
		}
		if ("order".equals(json.getString("tradeType"))) {
			System.out.println("MQ订 单数据："+json.toString());
			PlayVoice.dealOrderMqMes(json.toString());
		}
	}
	
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		System.out.println("开始向主机发送消息.....");
		ByteBuf encoded = ctx.alloc().buffer(4 * msg.length());
		encoded.writeBytes(msg.getBytes());
		ctx.write(encoded);
		ctx.flush();
	   
	}
	
	@Override
	public void userEventTriggered(ChannelHandlerContext ctx, Object evt)
			throws Exception {
		System.out.println("客户端循环心跳监测发送: " + new Date());
		if (evt instanceof IdleStateEvent) {
			IdleStateEvent event = (IdleStateEvent) evt;
			if (event.state() == IdleState.WRITER_IDLE) {
				JSONObject reqJson = new JSONObject();
				reqJson.put("tradeType", "heartBeat");
				reqJson.put("timeStamp", Calendar.getInstance().getTimeInMillis());
				ctx.writeAndFlush(reqJson.toString());
			}
		}

	}

	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		try {
			System.out.println("断线重连......");
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			bootStrap.connect(new InetSocketAddress(SpeechConstants.CONN_SERVER_WEBSPEECH, SpeechConstants.CONN_SERVER_WEBSPEECH_POORT)).sync();
		    System.out.println("重连时间...."+df.format(new Date()));
		} catch (Exception e) {
		}
	
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
		// Close the connection when an exception is raised.
		cause.printStackTrace();
		ctx.close();
	}

}
