package autotestcasegeneratetool;

import java.util.ArrayList;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Link;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.nebula.widgets.grid.*;


/**
 * 
 * @author 汪文轩
 * 
 * 这里是右边的表格界面
 *
 */
public class View extends ViewPart implements ISelectionListener{

	public static final String ID = "Auto-TestCase-generate-tool.view";

	/**
	 * The text control that's displaying the content of the email message.
	 */
	private Label requireID ;
	private Grid grid;
	@Override
	public void createPartControl(Composite parent) {
		Composite top = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout();
		layout.marginHeight = 2;
		layout.marginWidth = 2;
		top.setLayout(layout);
		// top banner
		Composite banner = new Composite(top, SWT.NONE);
		banner.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_FILL, GridData.VERTICAL_ALIGN_BEGINNING, true, false));
		layout = new GridLayout();
		layout.marginHeight = 5;
		layout.marginWidth = 10;
		layout.numColumns = 2;
		banner.setLayout(layout);
		getSite().getPage().addSelectionListener((ISelectionListener) this);
		
		
		
		// setup bold font
		Font boldFont = JFaceResources.getFontRegistry().getBold(JFaceResources.DEFAULT_FONT);    
		
		Label l = new Label(banner, SWT.NONE);
		l.setText("需求:");
		l.setFont(boldFont);
		l.setLayoutData(new GridData(SWT.BEGINNING, SWT.BEGINNING, false, false));
		
		requireID = new Label(banner, SWT.WRAP);
		requireID.setText("");
		requireID.setLayoutData(new GridData(SWT.BEGINNING, SWT.BEGINNING, true, false));
		
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
	
	//每个变量的信息
		class VarInformation{
			public String varName;
			public int varType;
			public int varGroup;
			public VarInformation() {
				
			}
            public VarInformation(int varGroup,String varName,int varType) {
            	this.varGroup=varGroup;
            	this.varName=varName;
            	this.varType=varType;
				
			}
			
		}
		
		//各类型变量对应的编号
		String[] groupList= {
				"需求标识_0",
				"先决条件_1",
				"输入变量_2",
				"预期准则_3",
				"评价标准_4"
				
		};
		
		//各类型变量对应的编号
		String[] typeList= {
				"short_0",
				"int_1",
				"long_2",
				"double_3",
				"float_4",
				"boolean_5",
				"enum_WAIT_6.6",
				"enum_EQUAL_6.7"
		};
		//每条测试用例所包含的信息
		ArrayList<VarInformation> varlist=new ArrayList<VarInformation>();
		varlist.add(new VarInformation(0,"序号",1));
		varlist.add(new VarInformation(1,"MODE",6));
		varlist.add(new VarInformation(1,"SENSOR",5));
		varlist.add(new VarInformation(1,"PROTECT_SWITCH",5));
		varlist.add(new VarInformation(2,"S_A",1));
		varlist.add(new VarInformation(2,"S_B",1));
		varlist.add(new VarInformation(2,"S_C",1));
		varlist.add(new VarInformation(3,"MAIN_WARNING",5));
		varlist.add(new VarInformation(3,"PROTECTOR",5));
		varlist.add(new VarInformation(4,"评价准则",6));
		
		//表格开始
		
		grid = new Grid(top,SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL);
		grid.setLayoutData(new GridData(GridData.FILL_BOTH));
		grid.setHeaderVisible(true);
		GridColumn column1=new GridColumn(grid, SWT.NONE);
		column1.setText("需求标识");
		column1.setWidth(100);
		GridColumnGroup columnGroup2=new GridColumnGroup(grid, SWT.NONE);
		columnGroup2.setText("先决条件");
		GridColumnGroup columnGroup3=new GridColumnGroup(grid, SWT.NONE);
		columnGroup3.setText("输入变量");
		GridColumnGroup columnGroup4=new GridColumnGroup(grid, SWT.NONE);
		columnGroup4.setText("预期结果");
		for(int i=0;i<varlist.size();i++) {
			
			if(varlist.get(i).varGroup==1) {
				GridColumn column = new GridColumn(columnGroup2,SWT.NONE);
				column.setText(varlist.get(i).varName);
				column.setWidth(200);
				
			}
			else if(varlist.get(i).varGroup==2) {
				GridColumn column = new GridColumn(columnGroup3,SWT.NONE);
				column.setText(varlist.get(i).varName);
				column.setWidth(200);
				
			}
			else if(varlist.get(i).varGroup==3) {
				GridColumn column = new GridColumn(columnGroup4,SWT.NONE);
				column.setText(varlist.get(i).varName);
				column.setWidth(200);
				
			}
			else {
				
				
			}
			
		}
		GridColumn column5=new GridColumn(grid, SWT.NONE);
		column5.setText("评价准则");
		column5.setWidth(100);
	
		
		
		//所有的测试用例
		double testVectors[][]={
				{0,6,0,0,65535,65535,65535,1,0,7},
				{0,6,0,0,65535,65535,65535,1,0,7},
				{0,6,0,0,6555,65535,65535,1,0,7},
				{0,6,0,0,65535,-65536,65535,1,0,2},
				{0,6,0,0,65535,65535,65535,1,0,4},
				{0,6,0,0,65535,65535,65535,1,1,7}
		};
		//根据变量信息输出测试用例
      ArrayList<GridItem> item=new ArrayList<GridItem>();
      for(int i=0;i<testVectors.length;i++)
      {
    	  item.add(new GridItem(grid,SWT.NONE));
      }
		for(int i=0;i<testVectors.length;i++)
		{
			for(int j=0;j<testVectors[0].length;j++) {
				if(varlist.get(j).varGroup==0) {
					item.get(i).setText(j,requireID.getText());
				}
				else if(varlist.get(j).varGroup==4) {
					if(testVectors[i][j]==7) {
						item.get(i).setText(j,"EQUAL");
					}
					else {
						
					}
				}
				else {
					if(varlist.get(j).varType==0) {
						
						item.get(i).setText(j, ""+(short)(testVectors[i][j]));
					}
					else if(varlist.get(j).varType==1) {
						
						item.get(i).setText(j, ""+(int)(testVectors[i][j]));
					}
					else if(varlist.get(j).varType==2) {
						
						item.get(i).setText(j, ""+(long)(testVectors[i][j]));
					}
					else if(varlist.get(j).varType==3) {
						
						item.get(i).setText(j, ""+(double)(testVectors[i][j]));
					}
					else if(varlist.get(j).varType==4) {
						
						item.get(i).setText(j, ""+(float)(testVectors[i][j]));
					}
					else if(varlist.get(j).varType==5) {
						
						if(testVectors[i][j]==1) {
							item.get(i).setText(j,"ON");
						}
						else if(testVectors[i][j]==0){
							item.get(i).setText(j,"OFF");
						}
						else {
							
						}
					}
					else if(varlist.get(j).varType==6) {
						if(testVectors[i][j]==6) {
						item.get(i).setText(j, "WALT");
					}
						else {
							
						}}
					else {
						
					}

				}
			}
		}
		
	
	
	}

	@Override
	public void addPartPropertyListener(IPropertyChangeListener listener) {
		super.addPartPropertyListener(listener);
	}

	@Override
	public void setFocus() {
		return;
	}

	@Override
	public void selectionChanged(IWorkbenchPart part, ISelection selection) {
		ConsoleHandler.info("detetct change\n");
		if(selection!= null){
			IStructuredSelection is = (IStructuredSelection)selection;
			requireID.setText(is.toString());
			 GridItem[] items = grid.getItems();
			for (GridItem item : items) {
				item.setText(0, is.toString());
			}
			 
			
		}
	}
}
