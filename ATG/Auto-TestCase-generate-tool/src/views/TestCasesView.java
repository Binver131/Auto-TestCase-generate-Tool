package views;


import java.util.Date;

import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.resource.ResourceManager;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.viewers.ColumnLayoutData;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.accessibility.AccessibleActionListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Resource;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.part.ViewPart;

import console.ConsoleHandler;
import bean.Requirement;
import bean.Testcase;
import bean.Variable;

import org.eclipse.nebula.jface.gridviewer.GridColumnLayout;
import org.eclipse.nebula.widgets.grid.*;


/**
 * 
 * @author ������
 * 
 * �������ұߵı�����
 *
 */
public class TestCasesView extends ViewPart implements ISelectionListener{

	public static final String ID = "Auto-TestCase-generate-tool.testcasesView";

	/**
	 * The text control that's displaying the content of the email message.
	 */
	private Label requireID ;
	private Grid grid;
	private GridColumnGroup outputColumnGroup;
	private GridColumnGroup inputColumnGroup;
	private GridColumnGroup preColumnGroup;
	private GridColumn IDColumn;
	private GridColumn evaColumn;
	private Font font = new Font(Display.getDefault(), "����", 15, SWT.NONE);
	

	private Label requireNum;
	@SuppressWarnings("deprecation")
	@Override
	public void createPartControl(Composite parent) {
		//ConnectHelper.connectHelper();
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
		l.setText("����:");
		l.setFont(boldFont);
		l.setLayoutData(new GridData(SWT.BEGINNING, SWT.BEGINNING, false, false));
		
		requireID = new Label(banner, SWT.WRAP);
		requireID.setText("                            ");
		requireID.setLayoutData(new GridData(SWT.BEGINNING, SWT.BEGINNING, true, false));
		
		l = new Label(banner, SWT.NONE);
		l.setText("����:");
		l.setFont(boldFont);
		l.setLayoutData(new GridData(SWT.BEGINNING, SWT.BEGINNING, false, false));
    
		requireNum = new Label(banner, SWT.WRAP);
		requireNum.setText("         ");
		requireNum.setLayoutData(new GridData(SWT.BEGINNING, SWT.BEGINNING, true, false));
    
		l = new Label(banner, SWT.NONE);
		l.setText("Date:");
		l.setFont(boldFont);
		l.setLayoutData(new GridData(SWT.BEGINNING, SWT.BEGINNING, false, false));
		
		l = new Label(banner, SWT.WRAP);
		l.setText(new Date().toLocaleString());
		l.setLayoutData(new GridData(SWT.BEGINNING, SWT.BEGINNING, true, false));

		
		
		//���ʼ
		
		grid = new Grid(top,SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL);
		grid.setLayoutData(new GridData(GridData.FILL_BOTH));
		grid.setHeaderVisible(true);
		grid.setFont(font);
		grid.setAutoHeight(false);
		grid.setAutoWidth(false);
		
		IDColumn=new GridColumn(grid, SWT.NONE);
		IDColumn.setText("������ʶ");
		IDColumn.setWidth(100);
		IDColumn.setHeaderFont(font);
		preColumnGroup=new GridColumnGroup(grid, SWT.NONE);
		preColumnGroup.setText("�Ⱦ�����");
		GridColumn pre = new GridColumn(preColumnGroup, SWT.NONE);
		pre.setText(" ");
		pre.setWidth(100);
		inputColumnGroup=new GridColumnGroup(grid, SWT.NONE);
		inputColumnGroup.setText("�������");
		
		GridColumn in = new GridColumn(inputColumnGroup, SWT.NONE);
		in.setText(" ");
		in.setWidth(100);
		
		outputColumnGroup=new GridColumnGroup(grid, SWT.NONE);
		outputColumnGroup.setText("Ԥ�ڽ��");
		GridColumn out = new GridColumn(outputColumnGroup, SWT.NONE);
		out.setText(" ");
		out.setWidth(100);
		
		evaColumn = new GridColumn(grid, SWT.NONE);
		evaColumn.setText("����׼��");
		evaColumn.setHeaderFont(font);
		evaColumn.setWidth(100);
		grid.setVisible(false);
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
		if(selection!= null){
			IStructuredSelection is = (IStructuredSelection)selection;
			
			
			if(is.getFirstElement() instanceof Requirement) {
				
				
				Requirement requirement = (Requirement)is.getFirstElement();
				requireID.setText(requirement.getRequirementName());
				requireNum.setText(requirement.getTestcaseMap().values().size()+"");
				
				grid.clearItems();
				for(GridColumn colum: preColumnGroup.getColumns()) {
					colum.dispose();
				}
				for(GridColumn colum: inputColumnGroup.getColumns()) {
					colum.dispose();
				}

				for(GridColumn colum: outputColumnGroup.getColumns()) {
					colum.dispose();
				}
				grid.setVisible(true);
				ConsoleHandler.info("detetct change\n");
				if(!requirement.getRequirementCondition().equals("")) {
					for (Variable Var : requirement.conditionVars()) {
						GridColumn in = new GridColumn(preColumnGroup, SWT.NONE);
						
						in.setText(Var.getVariableName());
						in.setHeaderFont(font);
						in.setWidth((grid.getParent().getBounds().width-200)/3/requirement.conditionVars().size());
						//in.setWidth(Var.getVariableName().length()*10);
					}
				}
				if(!requirement.getRequirementInput().equals("")) {
					for (Variable Var : requirement.inputVars()) {
						GridColumn in = new GridColumn(inputColumnGroup, SWT.NONE);
						in.setText(Var.getVariableName());
						in.setHeaderFont(font);
						in.setWidth((grid.getParent().getBounds().width-200)/3/requirement.inputVars().size());
						//in.setWidth(Var.getVariableName().length()*10);
					}
				}
				if(!requirement.getRequirementOutput().equals("")) {
					for (Variable Var : requirement.outputVars()) {
						GridColumn in = new GridColumn(outputColumnGroup, SWT.NONE);
						in.setText(Var.getVariableName());
						in.setHeaderFont(font);
						in.setWidth((grid.getParent().getBounds().width-200)/3/requirement.outputVars().size());
						//in.setWidth(Var.getVariableName().length()*10);
					}
				}
				int rownumber=1;
				for(Testcase testcase:requirement.getTestcaseMap().values()) {
					int columnCount = 1;
					
					GridItem item = new GridItem(grid, SWT.NONE);
					//�����б���ɫ
					if(rownumber%2==0) {
						item.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_GRAY));
						rownumber++;
					}
					else {
						rownumber++;
					}
					
					item.setText(testcase.getTestcaseID()+"");
					item.setText(columnCount++,testcase.getTestcaseEvaluate());
					
					for(String var:testcase.getTestcaseCondition().split(",")) {
						
						item.setText(columnCount++,var);
					}
					
					for(String var:testcase.getTestcaseInput().split(",")) {
						
						item.setText(columnCount++,var);
					}
					
					for(String var:testcase.getTestcaseOutput().split(",")) {
						
						item.setText(columnCount++,var);
					}
					
				}
				
				

			}else {
				grid.setVisible(false);
			}
			
			
		}
	}

	
	
	
}
