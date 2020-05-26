/**  
* @Title: ManualDesignView.java  
* @Package views  
* @Description: TODO(用一句话描述该文件做什么)  
* @author Binver131  
* @date 2020年5月14日  
* @version V1.0  
*/  
package views;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;

import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.nebula.widgets.grid.Grid;
import org.eclipse.nebula.widgets.grid.GridColumn;
import org.eclipse.nebula.widgets.grid.GridColumnGroup;
import org.eclipse.nebula.widgets.grid.GridEditor;
import org.eclipse.nebula.widgets.grid.GridItem;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Item;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.part.ViewPart;

import console.ConsoleHandler;
import entity.DataBase;
import entity.Requirement;
import entity.TestCase;
import entity.Variables;
import jdbc.ConnectHelper;

/**  
* @ClassName: ManualDesignView  
* @Description: TODO(手动设计界面视图)  
* @author Binver131  
* @date 2020年5月14日    
*/
public class ManualDesignView extends ViewPart {
	public static final String ID = "Auto-TestCase-generate-tool.ManualDesignView";
	
	private Label requireID ;
	private Grid grid;
	private GridColumnGroup outputColumnGroup;
	private GridColumnGroup inputColumnGroup;
	private GridColumnGroup preColumnGroup;
	private GridColumn IDColumn;
	private GridColumn evaColumn;
	private Font font = new Font(Display.getDefault(), "宋体", 15, SWT.NONE);
	private Requirement requirement;
	private Label requireNum;
	private GridEditor gridEditor;
	private ArrayList<GridEditor> editors=new ArrayList<GridEditor>();
	private ArrayList<Control> controls=new ArrayList<Control>(); 
	private int i;
	private DataBase initDataBase() {
		DataBase root = ConnectHelper.getDataBaseInstance();		
		return root;
	}

	private HashMap<Integer,String> rowtestcase=new HashMap<Integer, String>(); 

	@Override
	public void createPartControl(Composite parent) {
		// TODO Auto-generated method stub
		//ConnectHelper.connectHelper();
		//GridEditor gridEditor=new GridEditor(grid);
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

		
		
		
		
		
		
		// setup bold font
		Font boldFont = JFaceResources.getFontRegistry().getBold(JFaceResources.DEFAULT_FONT);    
		
		Label l = new Label(banner, SWT.NONE);
		l.setText("需求:");
		l.setFont(boldFont);
		l.setLayoutData(new GridData(SWT.BEGINNING, SWT.BEGINNING, false, false));
		
		requireID = new Label(banner, SWT.WRAP);
		requireID.setText("                            ");
		requireID.setLayoutData(new GridData(SWT.BEGINNING, SWT.BEGINNING, true, false));
    
		l = new Label(banner, SWT.NONE);
		l.setText("Date:");
		l.setFont(boldFont);
		l.setLayoutData(new GridData(SWT.BEGINNING, SWT.BEGINNING, false, false));
		
		l = new Label(banner, SWT.WRAP);
		l.setText(new Date().toLocaleString());
		l.setLayoutData(new GridData(SWT.BEGINNING, SWT.BEGINNING, true, false));

		
		
		//表格开始
		
		grid = new Grid(top,SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL);
		grid.setLayoutData(new GridData(GridData.FILL_BOTH));
		grid.setHeaderVisible(true);
		grid.setFont(font);
		grid.setAutoHeight(true);
		grid.setAutoWidth(true);
		
		IDColumn=new GridColumn(grid, SWT.NONE);
		IDColumn.setText("用例标识");
		IDColumn.setWidth(200);
		IDColumn.setHeaderFont(font);
		preColumnGroup=new GridColumnGroup(grid, SWT.NONE);
		preColumnGroup.setText("先决条件");
		GridColumn pre = new GridColumn(preColumnGroup, SWT.NONE);
		pre.setText(" ");
		pre.setWidth(100);
		inputColumnGroup=new GridColumnGroup(grid, SWT.NONE);
		inputColumnGroup.setText("输入变量");
		
		GridColumn in = new GridColumn(inputColumnGroup, SWT.NONE);
		in.setText(" ");
		in.setWidth(100);
		
		outputColumnGroup=new GridColumnGroup(grid, SWT.NONE);
		outputColumnGroup.setText("预期结果");
		GridColumn out = new GridColumn(outputColumnGroup, SWT.NONE);
		out.setText(" ");
		out.setWidth(100);
		
		evaColumn = new GridColumn(grid, SWT.NONE);
		evaColumn.setText("评价准则");
		evaColumn.setHeaderFont(font);
		evaColumn.setWidth(200);
		//grid.setVisible(false);
		
		gridEditor=new GridEditor(grid);
		Composite down = new Composite(top, SWT.NONE);
		down.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_FILL, GridData.VERTICAL_ALIGN_BEGINNING, true, false));
		layout = new GridLayout();
		layout.marginHeight = 5;
		layout.marginWidth = 10;
		layout.numColumns = 2;
		down.setLayout(layout);

		
		
		
		Button addButton=new Button(down, SWT.NONE);
		addButton.setText("添加");
		addButton.addListener(SWT.MouseDown,new Listener() {
			
			@Override
			public void handleEvent(Event event) {
				// TODO Auto-generated method stub
				if(grid.getItemCount()==0) {
					GridItem item =new GridItem(grid,SWT.NONE);	
					for(i=0;i<grid.getColumnCount();i++) {
						gridEditor = new GridEditor(grid);
						gridEditor.grabHorizontal = true;
						String strss="emmu,boolean,Enumerated";
						if(VariablesView.varHashMap.get(grid.getColumn(i).getText())!=null) {
							if(strss.contains(VariablesView.varHashMap.get(grid.getColumn(i).getText()).getTypeownname())){
								String rowrange= VariablesView.varHashMap.get(grid.getColumn(i).getText()).getRange();
								rowrange=remove(rowrange, "[", 1);
								rowrange=remove(rowrange, "]", 1);
								String[] range =rowrange.split(",");
								Combo combo=new Combo(grid,SWT.BORDER|SWT.READ_ONLY);
								for(int i=0;i<range.length;i++) {
									combo.add(range[i]);
								}
								gridEditor.setEditor(combo, item, i);
								
								controls.add(combo);
								combo.addListener(SWT.FocusOut, new Listener() {
								
									@Override
									public void handleEvent(Event event) {
										// TODO Auto-generated method stub
										if(combo.getText()!="") {
										rowtestcase.put(controls.indexOf(combo),combo.getText());
										}
									}
								});
							
							}
							else {
								Text text=new Text(grid, SWT.BORDER);
								gridEditor.setEditor(text, item, i);
								controls.add(text);
								text.addListener(SWT.FocusOut, new Listener() {
									
									@Override
									public void handleEvent(Event event) {
										// TODO Auto-generated method stub
										if(text.getText()!="") {
										rowtestcase.put(controls.indexOf(text),text.getText());
										}
									}
								});
							}
							
						}
						else {
							Text text=new Text(grid, SWT.BORDER);
							gridEditor.setEditor(text, item, i);
							controls.add(text);
							text.addListener(SWT.FocusOut, new Listener() {
								
								@Override
								public void handleEvent(Event event) {
									// TODO Auto-generated method stub
									if(text.getText()!="") {
										rowtestcase.put(controls.indexOf(text),text.getText());
									}
								}
							});
							
							
						}
//						Combo combo=new Combo(grid,SWT.BORDER);
//						gridEditor.setEditor(combo, item, i);
//						Text text=new Text(grid, SWT.BORDER);
//						gridEditor.setEditor(text, item, i);
						editors.add(gridEditor);
					}
					ConsoleHandler.info("已添加");
				}
				else {
					ConsoleHandler.info("还有未提交的测试用例");
								
				}
			}
		});

		Button commitButton=new Button(down, SWT.NONE);
		commitButton.setText("提交");
		commitButton.addListener(SWT.MouseDown, new Listener() {
			
			@Override
			public void handleEvent(Event event) {
				// TODO Auto-generated method stub
				System.out.println(rowtestcase.size());
				if(rowtestcase.size()==grid.getColumnCount()) {
				TestCase testCase=new TestCase();
				String testcaseCondition="";	
				String testcaseInput="";			
				String testcaseOutput="";		
				for(i=0;i<grid.getColumnCount();i++) {
					if(grid.getColumn(i).getColumnGroup()==inputColumnGroup) {
						testcaseInput=testcaseInput+","+rowtestcase.get(i);
					}
					else if(grid.getColumn(i).getColumnGroup()==outputColumnGroup) {
						testcaseOutput=testcaseOutput+","+rowtestcase.get(i);
					}
					else if(grid.getColumn(i).getColumnGroup()==preColumnGroup) {
						testcaseCondition=testcaseCondition+","+rowtestcase.get(i);
					}
					else if(grid.getColumn(i).getText()=="用例标识") {
						testCase.setTestcaseID(Integer.parseInt(rowtestcase.get(i)));
					}
					else if(grid.getColumn(i).getText()=="评价准则") {
						testCase.setTestcaseEvaluate(rowtestcase.get(i));
					}

				}
				if(testcaseCondition!="") {
				testcaseCondition=remove(testcaseCondition, ",", 1);
				}
				if(testcaseInput!="") {
				testcaseInput=remove(testcaseInput, ",", 1);
				}
				if(testcaseOutput!="") {
			    testcaseOutput=remove(testcaseOutput, ",", 1);
				}
				testCase.setTestcaseInput(testcaseInput);
				testCase.setTestcaseOutput(testcaseOutput);
				testCase.setTestcaseCondition(testcaseCondition);
				testCase.setTestcaseType("manual");
				testCase.setTestcaseRequirementID(requirement.getDbId());
				ConnectHelper.insertTestCase(testCase, requirement.getParent().getParent().getID());
				ConsoleHandler.info(testCase.toString());
				for(Control con:controls) {
					con.dispose();
				}
				controls.removeAll(controls);
				for (GridEditor edit:editors) {
					edit.dispose();
				}
				editors.removeAll(editors);
				grid.disposeAllItems();
				rowtestcase.clear();
			}
				else {
					ConsoleHandler.info("还有未填写的项");
				}
				
			}
		});
		
		
		
		
		
	}

	
	public void init(Requirement requirement) {
		this.requirement = requirement;
		//开始显示
		requireID.setText(this.requirement.getRequirementName());
		
//		grid.clearItems();
		
		for(GridColumn colum: outputColumnGroup.getColumns()) {
			colum.dispose();
		}
		for(GridColumn colum: inputColumnGroup.getColumns()) {
			colum.dispose();
		}
		for(GridColumn colum: preColumnGroup.getColumns()) {
			colum.dispose();
		}
	
		
		for (Variables Var : requirement.getInputVars()) {
			GridColumn in = new GridColumn(inputColumnGroup, SWT.NONE);
			in.setText(Var.getVariablesName());
			in.setHeaderFont(font);
			in.setWidth(Var.getVariablesName().length()*30);
		}
		
		for (Variables Var : requirement.getPreConVars()) {
			GridColumn in = new GridColumn(preColumnGroup, SWT.NONE);
			in.setText(Var.getVariablesName());
			in.setHeaderFont(font);
			
			in.setWidth(Var.getVariablesName().length()*30);
		}
		
		for (Variables Var : requirement.getOutputVars()) {
			GridColumn in = new GridColumn(outputColumnGroup, SWT.NONE);
			in.setText(Var.getVariablesName());
			in.setHeaderFont(font);
			in.setWidth(Var.getVariablesName().length()*30);
		}
		
		
	}
	
	@Override
	public void setFocus() {
		// TODO Auto-generated method stub
		return;
	}

	public Requirement getRequirement() {
		return requirement;
	}

	public void setRequirement(Requirement requirement) {
		this.requirement = requirement;
	}
	public String remove(String s,String string,int i){

		if(i==1){

		int j=s.indexOf(string);

		s=s.substring(0, j)+s.substring(j+1);

		i--;

		return s;

		}else{

		int j=s.indexOf(string);

		i--;

		return s.substring(0, j+1)+remove(s.substring(j+1), string, i);

		}

		}

}
