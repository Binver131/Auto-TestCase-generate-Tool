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
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.part.ViewPart;

import console.ConsoleHandler;
import entity.Model;
import entity.Requirement;
import entity.RowRequirement;
import entity.Variables;

/**
 * 
 * @ClassName: VariablesView
 * @Description: TODO(原始需求信息显示界面)
 * @author Binver131
 * @date 2020年5月19日
 */
public class RowReqInfoView extends ViewPart implements ISelectionListener {

	public static final String ID = "Auto-TestCase-generate-tool.RowReqInfoView";
	private Text name;
	private Text content;
	private Label nameLabel;
	private Label contentL;

	@Override
	public void createPartControl(Composite parent) {
		Composite top = new Composite(parent, SWT.NONE);

		GridLayout layout = new GridLayout();
		layout.marginHeight = 40;
		layout.marginWidth = 50;
		layout.numColumns = 4;

		top.setLayout(layout);

		GridData nameLabelgd = new GridData(SWT.BEGINNING, SWT.BEGINNING, false, false);
		nameLabel = new Label(top, SWT.NONE);
		nameLabel.setText("模型名称：");
		nameLabel.setLayoutData(nameLabelgd);

		GridData nameGd = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		nameGd.horizontalSpan = 3;
		name = new Text(top, SWT.SINGLE | SWT.LEAD | SWT.BORDER | SWT.READ_ONLY);
		name.setText("");
		name.setLayoutData(nameGd);
//		
		GridData contentLabelGd = new GridData(SWT.BEGINNING, SWT.BEGINNING, false, false);
		contentL = new Label(top, SWT.NONE);
		contentL.setText("模型描述：");
		contentL.setLayoutData(contentLabelGd);

		GridData contentGd = new GridData(GridData.FILL_BOTH);
		contentGd.verticalSpan = 3;
		contentGd.horizontalSpan = 3;
		content = new Text(top, SWT.WRAP | SWT.V_SCROLL | SWT.BORDER | SWT.READ_ONLY);
		content.setText("");
		content.setLayoutData(contentGd);

		getSite().getPage().addSelectionListener((ISelectionListener) this);

	}

	@Override
	public void addPartPropertyListener(IPropertyChangeListener listener) {
		// TODO Auto-generated method stub
		super.addPartPropertyListener(listener);
	}

	@Override
	public void selectionChanged(IWorkbenchPart part, ISelection selection) {
		if (selection != null && selection instanceof IStructuredSelection) {
			IStructuredSelection is = (IStructuredSelection) selection;
			if (is.getFirstElement() instanceof RowRequirement) {
				nameLabel.setText("原始需求:");
				contentL.setText("需求内容");
				name.setText(((RowRequirement) is.getFirstElement()).getName());
				content.setText(((RowRequirement) is.getFirstElement()).getContent());
			}
			if (is.getFirstElement() instanceof Model) {
				nameLabel.setText("模型名称:");
				contentL.setText("模型描述");
				name.setText(((Model) is.getFirstElement()).getName());
				content.setText(((Model) is.getFirstElement()).getText());
			}
			if (is.getFirstElement() instanceof Requirement) {
				nameLabel.setText("规范需求:");
				contentL.setText("需求描述");
				name.setText(((Requirement) is.getFirstElement()).getRequirementName());
				content.setText(((Requirement) is.getFirstElement()).getRequirementText());
			}
		}
	}

	@Override
	public void setFocus() {
		return;
	}
}
