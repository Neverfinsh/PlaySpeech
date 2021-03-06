package com.gzhc365.shell;

import net.sf.json.JSONObject;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.events.ShellAdapter;
import org.eclipse.swt.events.ShellEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Tray;
import org.eclipse.swt.widgets.TrayItem;
import org.eclipse.wb.swt.SWTResourceManager;

import com.gzhc365.nettyclient.Client;


public class MainShell {
	
	public static  Shell shell;   // 定义一个shell 窗口对象;
	static Display display ;
	static MainShell  windowMain ;
	static Client client;
	
	/**1 打开窗口  **/
	public static void  openMainWindown(String shopInfoJson){
		LoginShell.colseWindown();
	    windowMain = new MainShell();
		windowMain.open(shopInfoJson);
		client=new Client();
	}
	
	/** 打开窗口**/
	public  static void open(String shopInfoJson) {
		display = Display.getDefault();
		createContents(shopInfoJson);
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		
	}
	
	// 结束释放资源
	public void releaseResource() {
		Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
			public void run() {
			}
		}));
		Runtime.getRuntime().removeShutdownHook(new Thread());
		System.exit(1);
	}

	/** 构建页面要数**/
	protected static  void createContents(String  shopInfoJson) {
		//  对字符串进行解析，查出页面要数
		JSONObject  shopJson=JSONObject.fromObject(shopInfoJson);
		String shopName=shopJson.getString("shopname");
		
		shell = new Shell(display,SWT.SHELL_TRIM ^ SWT.MAX);
		shell.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		shell.setSize(630, 490);
		shell.setText("海鹚语音助手");
		shell.setLayout(new GridLayout(4, false));
		Image image = new Image(Display.getCurrent(),"image/favicon.png");
		shell.setImage(image);
		// 添加一个退出事件
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
		lblNewLabel.setText(shopName);
		
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
		Image image2 = new Image(Display.getCurrent(),"image/logo.png");
		lblHizpay.setImage(image2);
		lblHizpay.setText("HizPay语音助手接收支付订单中..........");
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
				Display display = Display.getDefault();
     			while (!shell.isDisposed()) {
     				if (!display.readAndDispatch()) {
     				  display.sleep();
     					shell.close();
     					windowMain.releaseResource();
     					System.exit(0);
     				}
     			}
			}
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				
			}
		});
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
//		shell.addShellListener(new ShellAdapter() {
//			public void shellClosed(ShellEvent e) {
//				MessageBox messagebox = new MessageBox(shell, SWT.ICON_WARNING
//						| SWT.YES | SWT.NO );
//				messagebox.setText("退出");
//				messagebox.setMessage("您确定要退出吗?");
//				int message = messagebox.open();
//				if(message == SWT.YES){
//					System.exit(0);
//					windowMain.releaseResource();
//				}
//			}
//		});
	}

}
