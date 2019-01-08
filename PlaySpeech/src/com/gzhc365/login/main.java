package com.gzhc365.login;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.wb.swt.SWTResourceManager;

public class main {

	public static  Shell shell;

	
	public static void  openMainWindown(){
		login.colseWindown();
		main window = new main();
		window.open();
	}
	
	/**
	 * Open the window.
	 */
	public  static void open() {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected static  void createContents() {
		shell = new Shell();
		shell.setSize(630, 490);
		shell.setText("SWT Application");
		shell.setLayout(new GridLayout(2, false));
		shell.setImage(SWTResourceManager
				.getImage("C:\\Users\\liuwu\\Desktop\\favicon.png"));
		new Label(shell, SWT.NONE);
		
		Label lblNewLabel_1 = new Label(shell, SWT.CENTER);
		GridData gd_lblNewLabel_1 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_lblNewLabel_1.widthHint = 417;
		lblNewLabel_1.setLayoutData(gd_lblNewLabel_1);
		lblNewLabel_1.setText("海鹚商户小助手");
		
		Label lblNewLabel = new Label(shell, SWT.SHADOW_IN);
		GridData gd_lblNewLabel = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_lblNewLabel.widthHint = 118;
		lblNewLabel.setLayoutData(gd_lblNewLabel);
		lblNewLabel.setText("门店名称");
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		
		Label label = new Label(shell, SWT.NONE);
		label.setText("语音播报");
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		
		Label lblHizpay = new Label(shell, SWT.SHADOW_IN | SWT.CENTER);
		lblHizpay.setImage(SWTResourceManager.getImage("C:\\Users\\liuwu\\Desktop\\logo.png"));
		lblHizpay.setText("HizPay语音助手接受支付订单中..........");
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
		
		Button btnNewButton = new Button(shell, SWT.CENTER);
		btnNewButton.setText("退出登陆");
		Button btnNewButtonColse = new Button(shell, SWT.CENTER);
		btnNewButtonColse.setText("退出");
		btnNewButtonColse.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
     			login.colseWindown();
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});;
		new Label(shell, SWT.NONE);

	}

}
