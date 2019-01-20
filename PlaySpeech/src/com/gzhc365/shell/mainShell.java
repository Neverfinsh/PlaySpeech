package com.gzhc365.shell;

import java.net.InetSocketAddress;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import net.sf.json.JSONObject;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.wb.swt.SWTResourceManager;
import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.socket.nio.NioClientSocketChannelFactory;
import org.jboss.netty.handler.codec.string.StringDecoder;
import org.jboss.netty.handler.codec.string.StringEncoder;

import com.gzhc365.nettyclient.ClientHiHandler;


public class mainShell {
	
	public static  Shell shell;   // 定义一个shell 窗口对象;
	
	/**1 打开窗口  **/
	public static void  openMainWindown(String shopInfoJson){
		loginShell.colseWindown();
		mainShell  windowMain = new mainShell();
		windowMain.open(shopInfoJson);
	}
	
	/** 打开窗口**/
	public  static void open(String shopInfoJson) {
		Display display = Display.getDefault();
	//	conSpeechServer();		
		createContents(shopInfoJson);
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		
	}
	
	
	/** 构建页面要数**/
	protected static  void createContents(String  shopInfoJson) {
		//  对字符串进行解析，查出页面要数
		JSONObject  shopJson=JSONObject.fromObject(shopInfoJson);
		String shopName=shopJson.getString("shopname");
		shell = new Shell();
		shell.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		shell.setSize(630, 490);
		shell.setText("海鹚语音助手");
		shell.setLayout(new GridLayout(4, false));
		Image image = new Image(Display.getCurrent(),"image/favicon.png");
		
		shell.setImage(image);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		
		Label lblNewLabel_1 = new Label(shell, SWT.CENTER);
		lblNewLabel_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		GridData gd_lblNewLabel_1 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_lblNewLabel_1.widthHint = 417;
		lblNewLabel_1.setLayoutData(gd_lblNewLabel_1);
		lblNewLabel_1.setText("海鹚商户小助手");
		
		Label lblNewLabel = new Label(shell, SWT.SHADOW_IN);
		lblNewLabel.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		GridData gd_lblNewLabel = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_lblNewLabel.widthHint = 118;
		lblNewLabel.setLayoutData(gd_lblNewLabel);
		lblNewLabel.setText("商户名字");
		
		Label label_2 = new Label(shell, SWT.SEPARATOR | SWT.VERTICAL);
		GridData gd_label_2 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 12);
		gd_label_2.heightHint = 218;
		label_2.setLayoutData(gd_label_2);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		
		Label label_1 = new Label(shell, SWT.SEPARATOR | SWT.HORIZONTAL);
		GridData gd_label_1 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_label_1.widthHint = 122;
		label_1.setLayoutData(gd_label_1);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		
		Label label = new Label(shell, SWT.NONE);
		Image image1 = new Image(Display.getCurrent(),"image/u1032.png");
		//label.setImage(SWTResourceManager.getImage("C:\\Users\\liuwu\\Desktop\\u1032.png"));
	    label.setImage(image1);
		label.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		
		Label lblHizpay = new Label(shell, SWT.SHADOW_IN | SWT.CENTER);
		GridData gd_lblHizpay = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_lblHizpay.widthHint = 379;
		lblHizpay.setLayoutData(gd_lblHizpay);
		lblHizpay.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
	//	lblHizpay.setImage(SWTResourceManager.getImage("C:\\Users\\liuwu\\Desktop\\logo.png"));
		Image image2 = new Image(Display.getCurrent(),"image/logo.png");
		lblHizpay.setImage(image2);
		lblHizpay.setText("HizPay语音助手接受支付订单中..........");
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		
		Label lblNewLabel_2 = new Label(shell, SWT.NONE);
		GridData gd_lblNewLabel_2 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_lblNewLabel_2.widthHint = 358;
		lblNewLabel_2.setLayoutData(gd_lblNewLabel_2);
		lblNewLabel_2.setAlignment(SWT.CENTER);
      //lblNewLabel_2.setImage(SWTResourceManager.getImage("D:\\LocalGit\\PlaySpeech\\image\\u1017.png"));
		Image image3 = new Image(Display.getCurrent(),"image/u1017.png");
		lblNewLabel_2.setImage(image3);
		lblNewLabel_2.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		Button btnNewButtonColse = new Button(shell, SWT.CENTER);
		btnNewButtonColse.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		GridData gd_btnNewButtonColse = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_btnNewButtonColse.widthHint = 101;
		btnNewButtonColse.setLayoutData(gd_btnNewButtonColse);
		btnNewButtonColse.setText("退出");
		btnNewButtonColse.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
     			loginShell.colseWindown();
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				
			}
		});
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);;

	}

}
