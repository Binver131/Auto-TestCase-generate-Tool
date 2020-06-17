package views;

import java.io.IOException;
import java.security.PublicKey;

import org.eclipse.jdt.internal.corext.fix.CodeStyleFixCore.ThisQualifierVisitor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import jdbc.PropertiesChange;

public class DataBaseDisplay {
	public static void chooseDatabase() {
		Display display = Display.getDefault();
		Shell shell = new Shell(display, SWT.CLOSE | SWT.TITLE | SWT.ON_TOP);//����һ�����ڶ��󣬲�����Ϊֻ�йرհ�ť�����ܵ����ߴ磬�����
		final GridLayout gridLayout = new GridLayout();
		shell.setText("���ݿ�ѡ�񴰿�");
		shell.setSize(450, 270);//���ô��ڳߴ�
		//shell.setLayout(gridLayout);
		
		/*���ô�����ʾ����Ļ�м�*/
		Rectangle bounds = display.getPrimaryMonitor().getBounds();
	    Rectangle rect = shell.getBounds();
	    int x = bounds.x + (bounds.width - rect.width) / 2;
	    int y = bounds.y + (bounds.height - rect.height) / 2;
	    //System.out.println("x,y:"+x+","+y);
	    shell.setLocation(x, y);
	    
		/*����Group����*/
	    final Group group = new Group(shell,SWT.NONE);
		group.setText("��д���ݿ���Ϣ");
		group.setBounds(10, 5, 420, 170);
		//group.setLayout(gridLayout);
		final Label dataBaseUrl = new Label(group,SWT.NONE);
		dataBaseUrl.setText("���ݿ�URL��");
		dataBaseUrl.setBounds(30, 40, 100, 30);
		final Text dataBaseUrlText = new Text(group,SWT.BORDER);
		dataBaseUrlText.setLayoutData(new GridData(SWT.FILL,SWT.CENTER,true,false,1,1));
		dataBaseUrlText.setBounds(130, 40, 200, 25);
		final Label userName = new Label(group,SWT.NONE);
		userName.setText("�û�����");
		userName.setBounds(30, 80, 100, 30);
		final Text userNameText = new Text(group,SWT.BORDER);
		userNameText.setLayoutData(new GridData(SWT.FILL,SWT.CENTER,true,false,1,1));
		userNameText.setBounds(130, 80, 200, 25);
		
		final Label passwd = new Label(group,SWT.NONE);
		passwd.setText("���룺");
		passwd.setBounds(30, 120, 100, 30);
		final Text passwdText = new Text(group,SWT.BORDER);
		passwdText.setLayoutData(new GridData(SWT.FILL,SWT.CENTER,true,false,1,1));
		passwdText.setBounds(130, 120, 200, 25);
		
		/*Group�·���������ť*/
		final Button okButton = new Button(shell,SWT.NONE);
		okButton.setText("ȷ��");
		okButton.setBounds(50, 185, 50, 30);
		okButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent event) {
				//System.out.println("123");
				try {
					PropertiesChange.changeProperties(dataBaseUrlText.getText(),userNameText.getText(),passwdText.getText());					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				shell.close();
			}
		});
		final Button cancelButton = new Button(shell,SWT.NONE);
		cancelButton.setText("ȡ��");
		cancelButton.setBounds(320, 185, 50, 30);
		cancelButton.addSelectionListener(new SelectionAdapter() {//����ȡ����ť�ļ����¼�������û������رմ���
			public void widgetSelected(final SelectionEvent event) {
				//System.out.println("456");
				shell.close();
			}
		});
		shell.open();//�򿪴���
		//shell.layout();//���в���
		while(!shell.isDisposed()) {//����һ��ѭ��
			if(!display.readAndDispatch()) display.sleep();
		}	
	}
	public static void main(String args[]) {
		chooseDatabase();
	}
}
