package com.gzhc365.shell;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

import com.gzhc365.constants.SpeechConstants;
import com.gzhc365.nettyclient.Client;
import com.gzhc365.utils.NetStateUtils;
import com.gzhc365.web.SpeechWebAPI;

public class LoginShell {

	public static Shell loginShell;       // login 登陆
	public static Text userAccountText;   // 用户名
	public static Text passWordText;      // 用户密码
	public static Label userAccountLabel;
	public static Label passWordLabel;
	public static Label lblNewLabel_1;
	public static Label lblNewLabel_2;
	public static Button btnNewButton;
	
	/*************************  Shell 的基础的方法  ********************/
	
	/** 打开窗口的main方法 **/
	public static void main(String[] args) {
		try {
			LoginShell window = new LoginShell();
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

	/*************************界面布局： 类似于HTML ********************/
	
	public void createContents(Display display) {

		loginShell = new Shell(display, SWT.SHELL_TRIM ^ SWT.MAX);
		loginShell.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		loginShell.setSize(472, 554);
		loginShell.setText("门店用户登陆");
		Image image = new Image(Display.getCurrent(), "image/favicon.png");
		loginShell.setImage(image);
		loginShell.setLayout(new GridLayout(3, false));
		new Label(loginShell, SWT.NONE);
		new Label(loginShell, SWT.NONE);
		new Label(loginShell, SWT.NONE);
		new Label(loginShell, SWT.NONE);
		new Label(loginShell, SWT.NONE);

		lblNewLabel_1 = new Label(loginShell, SWT.NONE);
		Image logoImage = new Image(Display.getCurrent(), "image/logo.png");
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
		GridData gd_passWordText = new GridData(SWT.LEFT, SWT.CENTER, true,false, 1, 1);
		gd_passWordText.widthHint = 320;
		passWordText.setLayoutData(gd_passWordText);
		new Label(loginShell, SWT.NONE);
		new Label(loginShell, SWT.NONE);
		lblNewLabel_2 = new Label(loginShell, SWT.NONE);
		new Label(loginShell, SWT.NONE);
		new Label(loginShell, SWT.NONE);
		 btnNewButton = new Button(loginShell, SWT.NONE);
		GridData gd_btnNewButton = new GridData(SWT.LEFT, SWT.CENTER, false,
				false, 1, 1);
		gd_btnNewButton.widthHint = 330;
		btnNewButton.setLayoutData(gd_btnNewButton);
		btnNewButton.setText("登陆");
		btnNewButton.addSelectionListener(new LoginClick());
	}

	/*******************   窗口的事件     *************/

	/** 用户登陆 : 点击事件 **/
	public static class LoginClick extends SelectionAdapter {
		public void widgetSelected(SelectionEvent e) {
			String shopInfoJsonStr = null;
			try {
				String username = userAccountText.getText();
				String password = passWordText.getText();
				if(username==""||username.length()==0 ){
					MessageDialog("账号不能为空");
					setDefault();
					return;
				}
				
				if(password==""||password.length()==0 ){
					MessageDialog("密码不能为空");
					setDefault();
					return;
				}
				btnNewButton.setText("登陆中.....");
				// 本地网络
 				boolean localNetStatus =  NetStateUtils.isConnect(SpeechConstants.CONN_INTERNET_);   
				if(!localNetStatus){
					MessageDialog("本地网络出现问题,请检查网络是否连接!");
					setDefault();
					return ;
				}
				try {
					shopInfoJsonStr = SpeechWebAPI.userLogin(username, password);
				} catch (Exception e2) {
					MessageDialog("系统在维护升级中，请及时联系海鹚小助手");
					setDefault();
				}
				if (shopInfoJsonStr == null) {
					return;
				}
				JSONObject resultJson = JSONObject.fromObject(shopInfoJsonStr);
				// 登陆失败; 提醒语
				if (!StringUtils.equals(resultJson.get("code").toString(), "0")) {
					String loginerrMsg = resultJson.get("msg").toString();
					lblNewLabel_2.setText(loginerrMsg);
					MessageDialog(loginerrMsg);
					setDefault();
					return;
				}
				String shopInfoJson = resultJson.get("data").toString();
				JSONObject shopDataJson = JSONObject.fromObject(shopInfoJson);
			    JSONObject connJson=new JSONObject();
			    connJson.put("hisId", shopDataJson.getLong("hisId"));
			    connJson.put("tradeType", "login");
			    Client client=new Client();
			    client.connect(connJson.toString());
				// 打开主界面
			    MainShell.openMainWindown(shopDataJson.toString());
				colseWindown();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}

	 
	public static void MessageDialog(String errMsg) {
		MessageBox dialog = new MessageBox(loginShell, SWT.OK
				| SWT.ICON_INFORMATION);
		dialog.setText("登陆");
		dialog.setMessage(errMsg);
		dialog.open();
	}
	/**  设置默认 **/
	public static void setDefault() {
		userAccountText.setText("");
		passWordText.setText("");
		btnNewButton.setText("登陆");
	}
	
	
	
}
