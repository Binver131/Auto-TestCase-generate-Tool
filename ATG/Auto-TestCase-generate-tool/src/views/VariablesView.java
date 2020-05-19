package views;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Link;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.part.ViewPart;

import console.ConsoleHandler;
import entity.Requirement;
import entity.Type;
import entity.Variables;
import jdbc.ConnectHelper;


/**
 * 
 * @author 汪文轩
 * 
 * 这里是右边的表格界面
 *
 */
public class VariablesView extends ViewPart implements ISelectionListener{

	public static final String ID = "Auto-TestCase-generate-tool.VariablesView";
	private TreeItem input;
	private TreeItem preCon;
	private TreeItem output;
	private Label requireID ;
	private Requirement root;
	private Tree tree;
	
	class ViewContentProvider implements IStructuredContentProvider, ITreeContentProvider{

		@Override
		public void inputChanged(Viewer v, Object oldInput, Object newInput) {
		}

		@Override
		public void dispose() {
		}

		@Override
		public Object[] getElements(Object parent) {
			return getChildren(parent);
		}

		@Override
		public Object getParent(Object child) {
			if (child instanceof Variables) {
				return ((Variables)child).getParent();
			}
			if (child instanceof String) {
				return root;
			}
			return null;
		}

		@Override
		public Object[] getChildren(Object parent) {
			if (parent instanceof String) {
				if(parent.equals("Pre")) {
					root.getPreConVars();
				}else if(parent.equals("Input")) {
					root.getInputVars();
				}else if(parent.equals("Output")) {
					root.getOutputVars();
				}
			}
			if (parent instanceof Requirement) {
				return new String[]{"Pre","Input","Output"};
			}
			return new Object[0];
		}

		@Override
		public boolean hasChildren(Object parent) {
			if (parent instanceof Requirement)
				return true;
			if (parent instanceof String) {
				if(parent.equals("Pre")) {
					root.hasVars(1);
				}else if(parent.equals("Input")) {
					root.hasVars(2);
				}else if(parent.equals("Output")) {
					root.hasVars(3);
				}
				
			}
				
			return false;
		}

	}

	class ViewLabelProvider extends LabelProvider {

		@Override
		public String getText(Object obj) {
			return obj.toString();
		}

	
	}
	
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
		
		tree = new Tree(top, SWT.SINGLE);
		tree.setHeaderVisible(true);
		tree.setLinesVisible(true);
        tree.setLayoutData(new GridData(GridData.FILL_BOTH));
        
		TreeColumn nameColumn = new TreeColumn(tree, SWT.NONE);
		nameColumn.setWidth(200);
		nameColumn.setText("Name");
		
		TreeColumn typeColumn = new TreeColumn(tree, SWT.NONE);
		typeColumn.setWidth(100);
		typeColumn.setText("type");
		
		TreeColumn sizeColumn = new TreeColumn(tree, SWT.NONE);
		sizeColumn.setWidth(100);
		sizeColumn.setText("size");
		
		TreeColumn rangeColumn = new TreeColumn(tree, SWT.NONE);
		rangeColumn.setWidth(200);
		rangeColumn.setText("range");
		
		input = new TreeItem(tree, SWT.NONE);
		input.setText(new String[] {"Input","","",""});
		
		preCon = new TreeItem(tree, SWT.NONE);
		preCon.setText(new String[] {"Pre-Condition","","",""});
		
		output = new TreeItem(tree, SWT.NONE);
		output.setText(new String[] {"output","","",""});
		
		TreeItem i1 = new TreeItem(input, SWT.NONE);
		i1.setText(new String[] {"S_A","INT","4","[-65536,65535]"});
		
		TreeItem i2 = new TreeItem(input, SWT.NONE);
		i2.setText(new String[] {"S_B","INT","4","[-65536,65535]"});
		
		TreeItem i3 = new TreeItem(input, SWT.NONE);
		i3.setText(new String[] {"S_C","INT","4","[-65536,65535]"});
		
		TreeItem o1 = new TreeItem(output, SWT.NONE);
		o1.setText(new String[] {"MAIN_WARING","Enum","4","[OFF,ON]"});
		
		TreeItem o2 = new TreeItem(output, SWT.NONE);
		o2.setText(new String[] {"PROTECTOR","Enum","4","[OFF,ON]"});
		
		TreeItem p1 = new TreeItem(preCon, SWT.NONE);
		p1.setText(new String[] {"MODE","Enum","4","[WAIT,WARD,OFF]"});
		TreeItem p2 = new TreeItem(preCon, SWT.NONE);
		p2.setText(new String[] {"SENSOR","Enum","4","[OFF,ON]"});
		TreeItem p3 = new TreeItem(preCon, SWT.NONE);
		p3.setText(new String[] {"PROTECTOR_SWITCH","Enum","4","[OFF,ON]"});
	
		output.setExpanded(true);
		input.setExpanded(true);
		preCon.setExpanded(true);
//		
		getSite().getPage().addSelectionListener((ISelectionListener) this);
	
	}


 



	@Override
	public void addPartPropertyListener(IPropertyChangeListener listener) {
		// TODO Auto-generated method stub
		super.addPartPropertyListener(listener);
	}

	@Override
	public void selectionChanged(IWorkbenchPart part, ISelection selection) {
		if(selection!= null){
			IStructuredSelection is = (IStructuredSelection)selection;
			if(is.getFirstElement() instanceof Requirement) {
				
				root = (Requirement)is.getFirstElement();
				ConsoleHandler.info(root.getRequirementId());
				requireID.setText(root.getRequirementName());
				
				input.removeAll();
				preCon.removeAll();
				output.removeAll();
				
				for (Variables Var : root.getInputVars()) {
					TreeItem in = new TreeItem(input, SWT.NONE);
					String tp="";
					String sz="";
					String range="";
					for(Type type:ConnectHelper.typeList) {
						if(Var.getVariablesTypeID()==type.getTypeID()) {
							tp=type.getTypename();
							sz=type.getSizeString();
							range=type.getTyperange();
							break;
						}
					}
					in.setText(new String[] {Var.getVariablesName(),tp,sz,range});
				}
				
				for (Variables Var : root.getOutputVars()) {
					TreeItem in = new TreeItem(output, SWT.NONE);			
					String tp="";
					String sz="";
					String range="";
					for(Type type:ConnectHelper.typeList) {
						if(Var.getVariablesTypeID()==type.getTypeID()) {
							tp=type.getTypename();
							sz=type.getSizeString();
							range=type.getTyperange();
							break;
						}
					}
					in.setText(new String[] {Var.getVariablesName(),tp,sz,range});
				
				}
				
				for (Variables Var : root.getPreConVars()) {
					TreeItem in = new TreeItem(preCon, SWT.NONE);
					String tp="";
					String sz="";
					String range="";
					for(Type type:ConnectHelper.typeList) {
						if(Var.getVariablesTypeID()==type.getTypeID()) {
							tp=type.getTypename();
							sz=type.getSizeString();
							range=type.getTyperange();
							break;
						}
					}
					in.setText(new String[] {Var.getVariablesName(),tp,sz,range});
				
				}
				
			}
		}
	}
	@Override
	public void setFocus() {
		return;
	}
}
