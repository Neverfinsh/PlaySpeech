package com.gzhc365.login;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

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

public class login {

	public static Shell loginShell; // login 登陆
	public static Text userNameText; // 用户名
	public static Text passWordText; // 用户密码
	public static Text voliCodeText;
	public static Label lblNewLabel_1;

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			login window = new login();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 关闭窗口
	 */
	public static void colseWindown() {
		loginShell.close();
	}

	/**
	 * 打开窗口
	 * 
	 * @throws Exception
	 */
	public void open() throws Exception {
		Display display = Display.getDefault();
		getVlidateCodeImage();
		createContents(display);
		loginShell.open();
		loginShell.layout();
		//getContent();
		while (!loginShell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents(Display display) {
		loginShell = new Shell(display,SWT.SHELL_TRIM ^ SWT.MAX);
		
		loginShell.setImage(SWTResourceManager
				.getImage("C:\\Users\\liuwu\\Desktop\\favicon.png"));
		loginShell.setSize(410, 500);
		loginShell.setText("门店用户登陆");
		loginShell.setImage(display.getSystemImage(SWT.ICON_WORKING));  
		 
		loginShell.setLayout(new GridLayout(3, false));
		new Label(loginShell, SWT.NONE);
		new Label(loginShell, SWT.NONE);
		new Label(loginShell, SWT.NONE);
		new Label(loginShell, SWT.NONE);
		new Label(loginShell, SWT.NONE);
		new Label(loginShell, SWT.NONE);
		new Label(loginShell, SWT.NONE);
		new Label(loginShell, SWT.NONE);

		Label lblNewLabel = new Label(loginShell, SWT.NONE);
		lblNewLabel.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false,
				false, 1, 1));
		lblNewLabel.setText("请使用门店账号登陆：");
		new Label(loginShell, SWT.NONE);
		new Label(loginShell, SWT.NONE);

		userNameText = new Text(loginShell, SWT.BORDER);
		userNameText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true,
				false, 1, 1));
		userNameText.setToolTipText("");
		new Label(loginShell, SWT.NONE);
		new Label(loginShell, SWT.NONE);

		passWordText = new Text(loginShell, SWT.BORDER);
		passWordText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true,
				false, 1, 1));
		new Label(loginShell, SWT.NONE);
		new Label(loginShell, SWT.NONE);

		lblNewLabel_1 = new Label(loginShell, SWT.NONE);
		lblNewLabel_1.setText("验证码图片");
		lblNewLabel_1.setImage(new Image(Display.getDefault(), "d:\\test.jpeg"));

		new Label(loginShell, SWT.NONE);
		new Label(loginShell, SWT.NONE);

		voliCodeText = new Text(loginShell, SWT.BORDER);
		voliCodeText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		new Label(loginShell, SWT.NONE);
		new Label(loginShell, SWT.NONE);

		Label lblNewLabel_2 = new Label(loginShell, SWT.NONE);
		lblNewLabel_2.setText("登陆错误提示语");
		new Label(loginShell, SWT.NONE);
		new Label(loginShell, SWT.NONE);

		Button btnNewButton = new Button(loginShell, SWT.NONE);
		GridData gd_btnNewButton = new GridData(SWT.LEFT, SWT.CENTER, false,
				false, 1, 1);
		gd_btnNewButton.widthHint = 322;
		btnNewButton.setLayoutData(gd_btnNewButton);
		btnNewButton.setText("登陆");
		btnNewButton.addSelectionListener(new MyClick());
		new Label(loginShell, SWT.NONE);
		new Label(loginShell, SWT.NONE);
		new Label(loginShell, SWT.NONE);
		new Label(loginShell, SWT.NONE);
		new Label(loginShell, SWT.NONE);
		Label lblNewLabel_3 = new Label(loginShell, SWT.NONE);
		lblNewLabel_3.setText("如果忘记密码，请联系管理员重置密码");
	}

	// 登陆的操作
	
	private static final class MyClick extends SelectionAdapter {
		public void widgetSelected(SelectionEvent e) {
			try {
				//getContent();	
				
				String username = userNameText.getText();    // 获取用户的名字
				String password = passWordText.getText();     // 获取用户的秘密
				String  validateCode= voliCodeText.getText(); //  获取验证码
				userLogin(username,password,validateCode);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			main.openMainWindown();
			colseWindown();
		}
	}

   /*
	* 获取文本框的内容
	*/
	
	public static void getContent() throws Exception {
		
		String username = userNameText.getText(); // 获取用户的名字
		String password = passWordText.getText(); // 获取用户的秘密
		System.out.println("username:" + username);
		System.out.println("passworl:" + password);
		System.out.println("项目路径" + System.getProperty("user.dir"));
		String url_str = "https://ssmch.hizpay.com/api/validate_code";// 获取用户认证的帐号URL
		URL url = new URL(url_str);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.connect();
		int code = connection.getResponseCode();
		if (code == 404) {
			throw new Exception("认证无效，找不到此次认证的会话信息！");
		}
		if (code == 500) {
			throw new Exception("认证服务器发生内部错误！");
		}
		if (code != 200) {
			throw new Exception("发生其它错误，认证服务器返回 " + code);
		}
		String imagePath=System.getProperty("user.dir")+"\\image"+"validateCode.jpeg";
		InputStream is = connection.getInputStream();
		byte[] response = new byte[is.available()];
		byte[] buf = new byte[1024];// 避免空间浪费
		FileOutputStream fout = new FileOutputStream("d:\\test.jpeg");
//		FileOutputStream fout = new FileOutputStream(imagePath);
		int len = 0;
		while ((len = is.read(buf)) != -1) {
			fout.write(buf, 0, len);

		}
		fout.close();

	}

	/**
	 * @throws Exception
	 * 
	 */
	public static  void userLogin(String username, String password, String valicode)
			throws Exception {

		String url_str = "http://mch.hizpay.com/api/ysmerchant/login";//
		URL url = new URL(url_str);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.connect();
		try {
			StringBuffer requestParam = new StringBuffer();
			requestParam.append("validate_code=").append(valicode)
					.append("&username=").append(username)
					.append("&password=").append(password);
			System.out.println("requestsTR:" + requestParam.toString());
			System.out.println("requestsTR:" + url_str);
			String response = getData(url_str, requestParam.toString());
			System.out.println("json 字符串" + response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

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

	/**
	 *  获取验证码图片
	 * @throws Exception 
	 */
	 public void getVlidateCodeImage() throws Exception{
		 
		 String url_str = "https://mch.hizpay.com/api/validate_code";// 获取用户认证的帐号URL
			URL url = new URL(url_str);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.connect();
			int code = connection.getResponseCode();
			if (code == 404) {
				throw new Exception("认证无效，找不到此次认证的会话信息！");
			}
			if (code == 500) {
				throw new Exception("认证服务器发生内部错误！");
			}
			if (code != 200) {
				throw new Exception("发生其它错误，认证服务器返回 " + code);
			}
			String imagePath=System.getProperty("user.dir")+"\\image"+"validateCode.jpeg";
			InputStream is = connection.getInputStream();
			byte[] response = new byte[is.available()];
			byte[] buf = new byte[1024];// 避免空间浪费
			FileOutputStream fout = new FileOutputStream("d:\\test.jpeg");
//			FileOutputStream fout = new FileOutputStream(imagePath);
			int len = 0;
			while ((len = is.read(buf)) != -1) {
				fout.write(buf, 0, len);

			}
			fout.close();
		 
	 }
	
	
	
	/**
	 * 
	 * @param b
	 * @param tagSrc
	 * @throws Exception
	 */
	static void buff2Image(byte[] b, String tagSrc) throws Exception {
		FileOutputStream fout = new FileOutputStream(tagSrc);
		// 将字节写入文件
		fout.write(b);
		fout.close();
	}

}
