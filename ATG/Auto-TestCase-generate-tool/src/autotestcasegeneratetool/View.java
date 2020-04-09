package autotestcasegeneratetool;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Link;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IPropertyListener;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.nebula.widgets.grid.*;


/**
 * 
 * @author 汪文轩
 * 
 * 这里是右边的表格界面
 *
 */
public class View extends ViewPart {

	public static final String ID = "Auto-TestCase-generate-tool.view";

	/**
	 * The text control that's displaying the content of the email message.
	 */
	public static String requirementID = "R1.1";
	
	@Override
	public void createPartControl(Composite parent) {
		this.setTitle(requirementID);
		
		Composite top = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout();
		layout.marginHeight = 0;
		layout.marginWidth = 0;
		top.setLayout(layout);
		// top banner
		Composite banner = new Composite(top, SWT.NONE);
		banner.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_FILL, GridData.VERTICAL_ALIGN_BEGINNING, true, false));
		layout = new GridLayout();
		layout.marginHeight = 5;
		layout.marginWidth = 10;
		layout.numColumns = 2;
		banner.setLayout(layout);
		
		// setup bold font
		Font boldFont = JFaceResources.getFontRegistry().getBold(JFaceResources.DEFAULT_FONT);    
		
		Label l = new Label(banner, SWT.NONE);
		l.setText("需求:");
		l.setFont(boldFont);
		l.setLayoutData(new GridData(SWT.BEGINNING, SWT.BEGINNING, false, false));
		
		l = new Label(banner, SWT.WRAP);
		l.setText("需求"+requirementID+"测试用例");
		l.setLayoutData(new GridData(SWT.BEGINNING, SWT.BEGINNING, true, false));
		
		l = new Label(banner, SWT.NONE);
		l.setText("数量:");
		l.setFont(boldFont);
		l.setLayoutData(new GridData(SWT.BEGINNING, SWT.BEGINNING, false, false));
    
		final Link link = new Link(banner, SWT.NONE);
		link.setText("3");
		link.addSelectionListener(new SelectionAdapter() {    
			public void widgetSelected(SelectionEvent e) {
				MessageDialog.openInformation(getSite().getShell(), "Not Implemented", "Imagine the address book or a new message being created now.");
			}    
		});
		link.setLayoutData(new GridData(SWT.BEGINNING, SWT.BEGINNING, true, false));
    
		l = new Label(banner, SWT.NONE);
		l.setText("Date:");
		l.setFont(boldFont);
		l.setLayoutData(new GridData(SWT.BEGINNING, SWT.BEGINNING, false, false));
		
		l = new Label(banner, SWT.WRAP);
		l.setText("2020-04-09 10:34:23");
		l.setLayoutData(new GridData(SWT.BEGINNING, SWT.BEGINNING, true, false));
		
		
		//表格开始
		Grid grid = new Grid(top,SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL);
	    grid.setHeaderVisible(true);
	    GridColumn column1 = new GridColumn(grid,SWT.NONE);
	    column1.setText("需求标识");
	    column1.setWidth(100);
	    
	    //表格组
	    GridColumnGroup columnGroup2 = new GridColumnGroup(grid,SWT.NONE);
	    columnGroup2.setText("先决条件");
	    GridColumn column2_1 = new GridColumn(columnGroup2,SWT.NONE);
	    column2_1.setText("MODE");
	    column2_1.setWidth(100);
	    GridColumn column2_2 = new GridColumn(columnGroup2,SWT.NONE);
	    column2_2.setText("SENSOR");
	    column2_2.setWidth(100);
	    GridColumn column2_3 = new GridColumn(columnGroup2,SWT.NONE);
	    column2_3.setText("PROTECT_SWITCH");
	    column2_3.setWidth(200);
	    
	    GridColumnGroup columnGroup3 = new GridColumnGroup(grid,SWT.NONE);
	    columnGroup3.setText("输入变量");
	    GridColumn column3_1 = new GridColumn(columnGroup3,SWT.NONE);
	    column3_1.setText("S_A");
	    column3_1.setWidth(100);
	    GridColumn column3_2 = new GridColumn(columnGroup3,SWT.NONE);
	    column3_2.setText("S_B");
	    column3_2.setWidth(100);
	    GridColumn column3_3 = new GridColumn(columnGroup3,SWT.NONE);
	    column3_3.setText("S_C");
	    column3_3.setWidth(100);
	    
	    GridColumnGroup columnGroup4 = new GridColumnGroup(grid,SWT.NONE);
	    columnGroup4.setText("预期结果");
	    GridColumn column4_1 = new GridColumn(columnGroup4,SWT.NONE);
	    column4_1.setText("MAIN_WARING");
	    column4_1.setWidth(150);
	    GridColumn column4_2 = new GridColumn(columnGroup4,SWT.NONE);
	    column4_2.setText("PROTECTOR");
	    column4_2.setWidth(150);

	    GridColumn column5 = new GridColumn(grid,SWT.NONE);
	    column5.setText("评价准则");
	    column5.setWidth(100);
	    
	    
	    //表中内容
	    GridItem item1 = new GridItem(grid,SWT.NONE);
	    item1.setText("R1.1");
	    item1.setText(1,"WAIT");
	    item1.setText(2, "OFF");
	    item1.setText(3, "OFF");
	    item1.setText(4,"65535");
	    item1.setText(5, "65535");
	    item1.setText(6, "65535");
	    item1.setText(7,"ON");
	    item1.setText(8, "OFF");
	    item1.setText(9, "EQUAL");
	    GridItem item2 = new GridItem(grid,SWT.NONE);
	    item2.setText("R1.1");
	    item2.setText(1,"WAIT");
	    item2.setText(2, "ON");
	    item2.setText(3, "ON");
	    item2.setText(4,"65535");
	    item2.setText(5, "65535");
	    item2.setText(6, "65535");
	    item2.setText(7,"OFF");
	    item2.setText(8, "OFF");
	    item2.setText(9, "EQUAL");
	    GridItem item3 = new GridItem(grid,SWT.NONE);
	    item3.setText("R1.1");
	    item3.setText(1,"WAIT");
	    item3.setText(2, "ON");
	    item3.setText(3, "ON");
	    item3.setText(4,"0");
	    item3.setText(5, "0");
	    item3.setText(6, "0");
	    item3.setText(7,"ON");
	    item3.setText(8, "ON");
	    item3.setText(9, "EQUAL");
		
	
	}

	@Override
	public void setFocus() {
		return;
	}
}
