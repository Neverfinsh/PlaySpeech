package com.gzhc365.nettyclient;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.timeout.IdleStateHandler;

import com.gzhc365.constants.SpeechConstants;

public class Client {
	
	private static final Charset UTF_8 = Charset.forName("utf-8");
	
	public  void connect(String shopIdJsonStr) {

		EventLoopGroup  workerGroup = new NioEventLoopGroup();//1 is OK  
		final Bootstrap bootstrap = new Bootstrap();  
        try {  
            
            bootstrap.group(workerGroup)  
                    .channel(NioSocketChannel.class) //create SocketChannel transport  
                    .option(ChannelOption.CONNECT_TIMEOUT_MILLIS,10000)  
                    .handler(new ChannelInitializer<SocketChannel>() {  
                        @Override  
                        protected void initChannel(SocketChannel ch) throws Exception {  
                        	
                            ch.pipeline().addLast(new LengthFieldBasedFrameDecoder(10240, 0, 2, 0, 2))  
                                    .addLast(new StringDecoder(UTF_8))  
                                    .addLast(new LengthFieldPrepender(2))  
                                    .addLast(new StringEncoder(UTF_8))  
                                    .addLast(new IdleStateHandler(0,4,0, TimeUnit.SECONDS))
                                    .addLast(new ClientHandler(shopIdJsonStr,bootstrap));//the same as ServerBootstrap 
                                 // .addLast(new HeartBeatClientHandler());
                                   
                        }  
                    });  
            //keep the connection with server，and blocking until closed!   
            ChannelFuture future = bootstrap.connect(new InetSocketAddress(SpeechConstants.CONN_SERVER_WEBSPEECH, SpeechConstants.CONN_SERVER_WEBSPEECH_POORT)).sync();
            //future.channel().closeFuture().sync();
        }catch (Exception e) {
			
		}finally{
			//workerGroup.shutdownGracefully();
		}
	}
	
}