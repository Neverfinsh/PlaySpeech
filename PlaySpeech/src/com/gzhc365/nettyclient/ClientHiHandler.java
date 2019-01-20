package com.gzhc365.nettyclient;

import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelHandler;

import com.gzhc365.play.playVoice;

public class ClientHiHandler extends SimpleChannelHandler {
    public ClientHiHandler() {
        super();
    }

    // 客户端接受消息;
    @Override
    public void messageReceived(ChannelHandlerContext ctx, MessageEvent event) throws Exception {
        String mgs = (String) event.getMessage();
        System.out.println(mgs); 
        playVoice.dealOrderMqMes(mgs);
        super.messageReceived(ctx, event);
    }
    @Override
    public void channelOpen(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {

        super.channelOpen(ctx, e);
    }

    @Override
    public void channelDisconnected(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
        super.channelDisconnected(ctx, e);
    }

    @Override
    public void channelClosed(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
        super.channelClosed(ctx, e);

    }
}
