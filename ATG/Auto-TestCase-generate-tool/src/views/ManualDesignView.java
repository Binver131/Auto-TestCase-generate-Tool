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
import java.awt.Window;
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
import org.eclipse.swt.browser.WindowEvent;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.internal.win32.WINDOWPLACEMENT;
import org.eclipse.swt.internal.win32.WINDOWPOS;
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
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.application.WorkbenchWindowAdvisor;
import org.eclipse.ui.internal.WorkbenchWindow;
import org.eclipse.ui.part.ViewPart;

import bean.DataBase;
import bean.Model;
import bean.Requirement;
import bean.Testcase;
import bean.Variable;
import console.ConsoleHandler;
import jdbc.ConnectHelp;


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
	private Model model;
	private HashMap<String, Variable> variableHashMap;
	private Label requireNum;
	private GridEditor gridEditor;
	private ArrayList<GridEditor> editors=new ArrayList<GridEditor>();
	private ArrayList<Control> controls=new ArrayList<Control>(); 
	private int i;
	private DataBase initDataBase() {
		DataBase root = ConnectHelp.getDataBaseInstance();		
		return root;
	}

	private HashMap<Integer,String> rowtestcase=new HashMap<Integer, String>(); 
	private HashMap<Variable,String>testcaseConditionMap; //<变量名称，变量取值>
	private HashMap<Variable,String>testcaseInputMap;
	private HashMap<Variable,String>testcaseOutputMap;
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
		IDColumn.setWidth(50);
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
		evaColumn.setWidth(50);
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

						
						if(model.getVariableMap().get(grid.getColumn(i).getText())!=null) {
							
							if(strss.contains(model.getVariableMap().get(grid.getColumn(i).getText()).getVariableType().getTypeRowName())){
								String rowrange= model.getVariableMap().get(grid.getColumn(i).getText()).getVariableType().getTypeRange();
//								rowrange=remove(rowrange, "[", 1);
//								rowrange=remove(rowrange, "]", 1);
								String[] range =rowrange.split(",");
								Combo combo=new Combo(grid,SWT.READ_ONLY);
								
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
						else if(grid.getColumn(i).getText().equals("评价准则")){
							Combo combo=new Combo(grid,SWT.READ_ONLY);
							combo.add("equal");
							combo.add("more than");
							combo.add("less than");
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
							Text text=new Text(grid, SWT.NONE);
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
				boolean flag=false;
				Testcase testCase=new Testcase();
				String testcaseCondition="";	
				String testcaseInput="";			
				String testcaseOutput="";		
				String intString="int,short,long,byte";
				String floatString="float,double";
				
				
				//condition、input、output、
				for(i=0;i<grid.getColumnCount();i++) {
					
					if(model.getVariableMap().get(grid.getColumn(i).getText())!=null) {
						if(intString.contains(model.getVariableMap().get(grid.getColumn(i).getText()).getVariableType().getTypeRowName())) {
							
							String rowrange = model.getVariableMap().get(grid.getColumn(i).getText()).getVariableType().getTypeRange();
							String[] rangestring=rowrange.split(","); 
							int[] range= {Integer.valueOf(rangestring[0]),Integer.valueOf(rangestring[1])};
							if(!isintNumber(rowtestcase.get(i))) {
								ConsoleHandler.error(grid.getColumn(i).getText()+"类型错误");
								
								flag=false;
								Display display = Display.getDefault();
								Shell shell = new Shell(display, SWT.CLOSE | SWT.TITLE | SWT.ON_TOP);
								MessageBox mb = new MessageBox(shell, SWT.ICON_WARNING | SWT.OK);
							     mb.setText("警告");
							     mb.setMessage(grid.getColumn(i).getText()+"类型错误");
							     mb.open();
								break;
							}
							else if(Integer.valueOf(rowtestcase.get(i))<=range[0]||Integer.valueOf(rowtestcase.get(i))>=range[1]){
								ConsoleHandler.error(grid.getColumn(i).getText()+"超出范围");
								System.out.println(range[0]+" "+range[1]);
								ConsoleHandler.error(rangestring[0]);
								ConsoleHandler.error(rangestring[1]);
								flag=false;
								Display display = Display.getDefault();
								Shell shell = new Shell(display, SWT.CLOSE | SWT.TITLE | SWT.ON_TOP);
								MessageBox mb = new MessageBox(shell, SWT.ICON_WARNING | SWT.OK);
							     mb.setText("警告");
							     mb.setMessage(grid.getColumn(i).getText()+"超出范围");
							     mb.open();
								break;
							}else {
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
								flag=true;
							}
						}
						else if(floatString.contains(model.getVariableMap().get(grid.getColumn(i).getText()).getVariableType().getTypeRowName())) {
							String rowrange = model.getVariableMap().get(grid.getColumn(i).getText()).getVariableType().getTypeRange();
							String[] rangestring=rowrange.split(","); 
							double[] range= {Double.parseDouble(rangestring[0]),Double.parseDouble(rangestring[1])};
							if(!isfloatNumber(rowtestcase.get(i))) {
								ConsoleHandler.error(grid.getColumn(i).getText()+"类型错误");
								flag=false;
								Display display = Display.getDefault();
								Shell shell = new Shell(display, SWT.CLOSE | SWT.TITLE | SWT.ON_TOP);
								MessageBox mb = new MessageBox(shell, SWT.ICON_WARNING | SWT.OK);
							     mb.setText("警告");
							     mb.setMessage(grid.getColumn(i).getText()+"类型错误");
							     mb.open();
								break;
							}
							else if(Double.parseDouble(rowtestcase.get(i))<=range[0]||Double.parseDouble(rowtestcase.get(i))>=range[1]){
								ConsoleHandler.error(grid.getColumn(i).getText()+"超出范围");
								flag=false;
								Display display = Display.getDefault();
								Shell shell = new Shell(display, SWT.CLOSE | SWT.TITLE | SWT.ON_TOP);
								MessageBox mb = new MessageBox(shell, SWT.ICON_WARNING | SWT.OK);
							     mb.setText("警告");
							     mb.setMessage(grid.getColumn(i).getText()+"超出范围");
							     mb.open();
								break;
							}else {
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
								flag=true;
							}
						}
						else {
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
							flag=true;
						}

					}
					}
					
				
				
				
				//去掉第一个逗号
				if(testcaseCondition!="") {
				testcaseCondition=remove(testcaseCondition, ",", 1);
				}
				if(testcaseInput!="") {
				testcaseInput=remove(testcaseInput, ",", 1);
				}
				if(testcaseOutput!="") {
			    testcaseOutput=remove(testcaseOutput, ",", 1);
				}
				
				
				//没有问题就执行插入操作
				if(flag==true) {
					testCase.setTestcaseInput(testcaseInput);
					testCase.setTestcaseOutput(testcaseOutput);
					testCase.setTestcaseCondition(testcaseCondition);
					testCase.setTestcaseType("manmade");
					testCase.setRequirementID(requirement.getRequirementID());

			
					
					//插入数据库
					try {
						ConnectHelp.insertTestcase(testCase);
						ConsoleHandler.info("插入数据库成功");
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					//requirement.addTestcaseMap(testCase);
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
					ConsoleHandler.error("输入不合法");
				}
			}
				else {
					ConsoleHandler.error("还有未填写的项");
				}
				
			}
		});
		
		
		
		
		
	}

	
	public void init(Requirement requirement) {
		this.requirement = requirement;
		model=requirement.getParent().getParent();
		
		
		
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
	
		
		if(!requirement.getRequirementCondition().equals("")) {
			for (Variable Var : requirement.conditionVars()) {
				GridColumn in = new GridColumn(preColumnGroup, SWT.NONE);
				in.setText(Var.getVariableName());
				in.setHeaderFont(font);
				//in.setWidth((grid.getParent().getBounds().width-100)/3/requirement.conditionVars().size());
				in.setWidth(Var.getVariableName().length()*30);
			}
		}
		if(!requirement.getRequirementInput().equals("")) {
			for (Variable Var : requirement.inputVars()) {
				GridColumn in = new GridColumn(inputColumnGroup, SWT.NONE);
				in.setText(Var.getVariableName());
				in.setHeaderFont(font);
				//in.setWidth((grid.getParent().getBounds().width-100)/3/requirement.inputVars().size());
				in.setWidth(Var.getVariableName().length()*30);
			}
		}
		if(!requirement.getRequirementOutput().equals("")) {
			for (Variable Var : requirement.outputVars()) {
				GridColumn in = new GridColumn(outputColumnGroup, SWT.NONE);
				in.setText(Var.getVariableName());
				in.setHeaderFont(font);
				//in.setWidth((grid.getParent().getBounds().width-100)/3/requirement.outputVars().size());
				in.setWidth(Var.getVariableName().length()*30);
			}
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
	public static boolean isfloatNumber(String str){
		String reg = "^[0-9]+(.[0-9]+)?$";
		return str.matches(reg);
	}
	public static boolean isintNumber(String str){
		String reg = "[0-9]*";
		return str.matches(reg);
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