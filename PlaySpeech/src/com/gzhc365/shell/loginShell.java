package com.gzhc365.shell;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import net.sf.json.JSONObject;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
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

import com.gzhc365.constants.SpeechConstants;
import com.gzhc365.nettyclient.ClientHiHandler;

public class loginShell {

	public static Shell loginShell; // login 登陆
	public static Text userAccountText; // 用户名
	public static Text passWordText; // 用户密码
	private Label userAccountLabel;
	private Label passWordLabel;
	private Label lblNewLabel_1;
	private Label lblNewLabel_2;

	/**   打开窗口的main方法 **/
	 public static void main(String[] args) {
	 try {
	 loginShell window = new loginShell();  
	 window.open();
	 } catch (Exception e) {
	 e.printStackTrace();
	 }
	 }

	/** 关闭窗口 **/
	public static void colseWindown() {
		loginShell.close();
	}

	/** 打开窗口 **/
	public void open() throws Exception {
		Display display = Display.getDefault();
		createContents(display);
		loginShell.open();
		loginShell.layout();
		while (!loginShell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/** 界面布局： 类似于HTML **/
	protected void createContents(Display display) {
		
		loginShell = new Shell(display, SWT.SHELL_TRIM ^ SWT.MAX);
		loginShell.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		loginShell.setSize(472, 554);
		loginShell.setText("门店用户登陆");
		Image image = new Image(Display.getCurrent(),"image/favicon.png");
		loginShell.setImage(image);
		loginShell.setLayout(new GridLayout(3, false));
		new Label(loginShell, SWT.NONE);
		new Label(loginShell, SWT.NONE);
		new Label(loginShell, SWT.NONE);
		new Label(loginShell, SWT.NONE);
		new Label(loginShell, SWT.NONE);

		lblNewLabel_1 = new Label(loginShell, SWT.NONE);
		Image logoImage = new Image(Display.getCurrent(),"image/logo.png");
		lblNewLabel_1.setImage(logoImage);
		
		new Label(loginShell, SWT.NONE);
		new Label(loginShell, SWT.NONE);

		Label lblNewLabel = new Label(loginShell, SWT.NONE);
		lblNewLabel.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblNewLabel.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false,
				false, 1, 1));
		lblNewLabel.setText("请使用门店账号登陆：");
		new Label(loginShell, SWT.NONE);

		userAccountLabel = new Label(loginShell, SWT.NONE);
		userAccountLabel.setBackground(SWTResourceManager
				.getColor(SWT.COLOR_WHITE));
		userAccountLabel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER,
				false, false, 1, 1));
		userAccountLabel.setText("门店账号");

		userAccountText = new Text(loginShell, SWT.BORDER);
		userAccountText.setBackground(SWTResourceManager
				.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
		GridData gd_userAccountText = new GridData(SWT.LEFT, SWT.CENTER, true,
				false, 1, 1);
		gd_userAccountText.widthHint = 318;
		userAccountText.setLayoutData(gd_userAccountText);
		userAccountText.setToolTipText("");
		new Label(loginShell, SWT.NONE);

		passWordLabel = new Label(loginShell, SWT.PASSWORD);
		passWordLabel.setBackground(SWTResourceManager
				.getColor(SWT.COLOR_WHITE));
		passWordLabel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false,
				false, 1, 1));
		passWordLabel.setText("密码");

		passWordText = new Text(loginShell, SWT.BORDER | SWT.PASSWORD);
		GridData gd_passWordText = new GridData(SWT.LEFT, SWT.CENTER, true,
				false, 1, 1);
		gd_passWordText.widthHint = 320;
		passWordText.setLayoutData(gd_passWordText);
		new Label(loginShell, SWT.NONE);
		new Label(loginShell, SWT.NONE);
		// 提醒语
		lblNewLabel_2 = new Label(loginShell, SWT.NONE);
		lblNewLabel_2.setText("提醒语");
		new Label(loginShell, SWT.NONE);
		new Label(loginShell, SWT.NONE);
		//
		Button btnNewButton = new Button(loginShell, SWT.NONE);
		GridData gd_btnNewButton = new GridData(SWT.LEFT, SWT.CENTER, false,
				false, 1, 1);
		gd_btnNewButton.widthHint = 330;
		btnNewButton.setLayoutData(gd_btnNewButton);
		btnNewButton.setText("登陆");
		btnNewButton.addSelectionListener(new LoginClick());
	}

	// 登陆的操作
	private static final class LoginClick extends SelectionAdapter {
		public void widgetSelected(SelectionEvent e) {
			String shopInfoJsonStr = null;
			try {
				String username = userAccountText.getText(); // 获取用户的名字
				String password = passWordText.getText(); // 获取用户的秘密
				shopInfoJsonStr = userLogin(username, password); // 登陆
				// 登陆失败; 弹框
				if (shopInfoJsonStr == null) {
					return;
				}
				JSONObject shopshopInfoJson = JSONObject
						.fromObject(shopInfoJsonStr);
				String shopId = shopshopInfoJson.getString("hisId");
				// 2 发socket消息:上 传自己的shopId //连接服务端 ;
				conSpeechServer(shopId);
				// 打开主界面
				mainShell.openMainWindown(shopInfoJsonStr);
				colseWindown();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}

   /**  用户登陆  **/
	public static String userLogin(String username, String password)
			throws Exception {
		String url_str = "http://localhost:8180/api/speech/login?";//
		URL url = new URL(url_str);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.connect();
		try {
			StringBuffer requestParam = new StringBuffer();
			requestParam.append("account=").append(username)
				    	.append("&password=").append(password);
			String response = getData(url_str, requestParam.toString());
			JSONObject resultJson = JSONObject.fromObject(response);
			String shopInfoJson = resultJson.get("data").toString();
			System.out.println("data :" + shopInfoJson);
			return shopInfoJson;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**  web-speech 接口**/
	public static String getData(String url, String request)
			throws UnsupportedEncodingException, IOException {
		String charset = "UTF-8";
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		HttpURLConnection connect = (HttpURLConnection) (new URL(url)
				.openConnection());
		connect.setRequestMethod("POST");
		connect.setDoOutput(true);
		connect.setConnectTimeout(1000 * 10);
		connect.setReadTimeout(1000 * 80);
		connect.setRequestProperty("ContentType",
				"application/x-www-form-urlencoded"); // 采用通用的URL百分号编码的数据MIME类型来传参和设置请求头
		connect.setDoInput(true);
		// 连接
		connect.connect();
		// 发送数据
		connect.getOutputStream().write(request.getBytes(charset));
		// 接收数据
		int responseCode = connect.getResponseCode();
		if (responseCode == HttpURLConnection.HTTP_OK) {
			InputStream in = connect.getInputStream();
			byte[] data = new byte[1024];
			int len = 0;
			while ((len = in.read(data, 0, data.length)) != -1) {
				outStream.write(data, 0, len);
			}
			in.close();
		}
		// 关闭连接
		connect.disconnect();
		String response = outStream.toString("UTF-8");
		return response;
	}
    /**  连接Netty的服务端 v**/ 
	public static boolean conSpeechServer(String shopId) {
		ClientBootstrap bootstrap = new ClientBootstrap();
		ExecutorService boss = Executors.newCachedThreadPool();
		ExecutorService work = Executors.newCachedThreadPool();
		bootstrap.setFactory(new NioClientSocketChannelFactory(boss, work));

		bootstrap.setPipelineFactory(new ChannelPipelineFactory() {
			public ChannelPipeline getPipeline() throws Exception {
				ChannelPipeline pipelinea = Channels.pipeline();
				pipelinea.addLast("encoder", new StringEncoder());
				pipelinea.addLast("decoder", new StringDecoder());
				pipelinea.addLast("hiClientHandler", new ClientHiHandler());
				return pipelinea;
			}
		});
		// 连接服务器端
		ChannelFuture channelFuture = bootstrap.connect(new InetSocketAddress(
				SpeechConstants.CONN_SERVER_WEBSPEECH, SpeechConstants.CONN_SERVER_WEBSPEECH_POORT));
		Channel channel = channelFuture.getChannel();
		channel.write(shopId);         
		return true;
	}
}
