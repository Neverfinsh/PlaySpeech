package com.gzhc365.nettyclient;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Calendar;

import net.sf.json.JSONObject;

import com.gzhc365.play.PlayVoice;

public class ClientHandler extends SimpleChannelInboundHandler<String> {

	private  String msg;
	
	public ClientHandler(String msg) {
		super();
		this.msg = msg;
	}



		@Override  
	    public void channelRead0(ChannelHandlerContext ctx, String message) throws Exception { 
		    System.out.println("开始读数据");
			JSONObject json = JSONObject.fromObject(message);
		
		if ("heartBeat".equals(json.getString("tradeType"))) {
			JSONObject reqJson = new JSONObject();
			reqJson.put("tradeType", "heartBeat");
			reqJson.put("timeStamp", Calendar.getInstance().getTimeInMillis());
			ctx.writeAndFlush(reqJson.toString());
		}
		if ("login".equals(json.getString("tradeType"))) {
			ctx.writeAndFlush(msg);
		}
		if ("order".equals(json.getString("tradeType"))) {
			System.out.println("Test:订 单数据："+json.toString());
			PlayVoice.dealOrderMqMes(json.toString());
		}
	}
	
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		System.out.println("进入active。。。。");
		ByteBuf encoded = ctx.alloc().buffer(4 * msg.length());
		encoded.writeBytes(msg.getBytes());
		ctx.write(encoded);
		ctx.flush();
	   
	}


	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
		// Close the connection when an exception is raised.
		cause.printStackTrace();
		ctx.close();
	}

}
