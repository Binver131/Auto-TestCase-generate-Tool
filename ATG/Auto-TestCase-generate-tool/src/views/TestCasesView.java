package views;


import java.util.Date;
import java.util.Iterator;

import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.part.ViewPart;

import console.ConsoleHandler;
import entity.Requirement;
import entity.TestCase;
import entity.Variables;
import tools.FontTool;

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
	private Font headGroupFont = new Font(Display.getDefault(), "��Բ", 20, SWT.BOLD);
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
		grid.setAutoHeight(true);
		grid.setAutoWidth(true);
		
		IDColumn=new GridColumn(grid, SWT.NONE);
		IDColumn.setText("������ʶ");
		IDColumn.setWidth(200);
		IDColumn.setHeaderFont(headGroupFont);
		preColumnGroup=new GridColumnGroup(grid, SWT.NONE);
		preColumnGroup.setText("�Ⱦ�����");
		preColumnGroup.setHeaderFont(headGroupFont);
		
		inputColumnGroup=new GridColumnGroup(grid, SWT.NONE);
		inputColumnGroup.setText("�������");
		inputColumnGroup.setHeaderFont(headGroupFont);
		
		GridColumn in = new GridColumn(inputColumnGroup, SWT.NONE);
		in.setText(" ");
		in.setWidth(100);
		
		outputColumnGroup=new GridColumnGroup(grid, SWT.NONE);
		outputColumnGroup.setText("Ԥ�ڽ��");
		outputColumnGroup.setHeaderFont(headGroupFont);
		
		evaColumn = new GridColumn(grid, SWT.NONE);
		evaColumn.setText("����׼��");
		evaColumn.setHeaderFont(headGroupFont);
		evaColumn.setWidth(200);
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
		if(selection!= null && selection instanceof IStructuredSelection){
			IStructuredSelection is = (IStructuredSelection)selection;
			
			
			if(is.getFirstElement() instanceof Requirement) {
				
			
				Requirement requirement = (Requirement)is.getFirstElement();
				requireID.setText(requirement.getRequirementName());
				requireNum.setText(requirement.getTestcases().length+"");
				
				grid.clearItems();
				
				for(GridColumn colum: outputColumnGroup.getColumns()) {
					colum.dispose();
				}
				for(GridColumn colum: inputColumnGroup.getColumns()) {
					colum.dispose();
				}
				for(GridColumn colum: preColumnGroup.getColumns()) {
					colum.dispose();
				}
				grid.setVisible(true);
				ConsoleHandler.info("detetct change\n");
				
				int outTotalWidth = 0;
				int inTotalWidth = 0;
				int preTotalWidth = 0;
				for (Variables Var : requirement.getInputVars()) {
					GridColumn in = new GridColumn(inputColumnGroup, SWT.NONE);
					in.setText(Var.getVariablesName());
					in.setHeaderFont(font);
					in.setWidth(FontTool.getWidth(Var.getVariablesName(), font));
					in.setAlignment(SWT.CENTER);
					inTotalWidth+=FontTool.getWidth(Var.getVariablesName(), font);
				}
				
				for (Variables Var : requirement.getPreConVars()) {
					GridColumn in = new GridColumn(preColumnGroup, SWT.NONE);
					in.setText(Var.getVariablesName());
					in.setHeaderFont(font);
					in.setWidth(FontTool.getWidth(Var.getVariablesName(), font));
					in.setAlignment(SWT.CENTER);
					preTotalWidth+=FontTool.getWidth(Var.getVariablesName(), font);
				}
				
				for (Variables Var : requirement.getOutputVars()) {
					GridColumn in = new GridColumn(outputColumnGroup, SWT.NONE);
					in.setText(Var.getVariablesName());
					in.setHeaderFont(font);
					in.setWidth(FontTool.getWidth(Var.getVariablesName(), font));
					in.setAlignment(SWT.CENTER);
					outTotalWidth+=FontTool.getWidth(Var.getVariablesName(), font);
				}
				//���������ܿ��С������������ռ�õĿ�ȣ����������п�� 
				if(inTotalWidth != 0 && FontTool.getWidth(inputColumnGroup.getText(), headGroupFont)>inTotalWidth) {
					GridColumn[] columns = inputColumnGroup.getColumns();
					int dev = ((FontTool.getWidth(inputColumnGroup.getText(), headGroupFont)-inTotalWidth)/columns.length);
					for (GridColumn gridColumn : columns) {
						gridColumn.setWidth(gridColumn.getWidth()+dev);
					}
				}
				if(outTotalWidth != 0 && FontTool.getWidth(outputColumnGroup.getText(), headGroupFont)>outTotalWidth) {
					GridColumn[] columns = outputColumnGroup.getColumns();
					int dev = ((FontTool.getWidth(outputColumnGroup.getText(), headGroupFont)-inTotalWidth)/columns.length);
					for (GridColumn gridColumn : columns) {
						gridColumn.setWidth(gridColumn.getWidth()+dev);
					}
				}
				if( preTotalWidth!=0 &&FontTool.getWidth(preColumnGroup.getText(), headGroupFont)>preTotalWidth ) {
					GridColumn[] columns = preColumnGroup.getColumns();
					int dev = ((FontTool.getWidth(preColumnGroup.getText(), headGroupFont)-inTotalWidth)/columns.length);
					for (GridColumn gridColumn : columns) {
						gridColumn.setWidth(gridColumn.getWidth()+dev);
					}
				}
				
				IDColumn.setWidth(FontTool.getWidth(IDColumn.getText(), headGroupFont));
				evaColumn.setWidth(FontTool.getWidth(evaColumn.getText(), headGroupFont));
				
				for(TestCase testcase:requirement.getTestcases()) {
					int columnCount = 1;
					GridItem item = new GridItem(grid, SWT.NONE);
					
					item.setText(testcase.getTestcaseID()+"");
					item.setText(columnCount++,testcase.getTestcaseEvaluate());
					for(String var:testcase.getTestcaseInput().split(",")) {
						item.setText(columnCount++,var);
					}
					
					for(String var:testcase.getTestcaseCondition().split(",")) {
						
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
