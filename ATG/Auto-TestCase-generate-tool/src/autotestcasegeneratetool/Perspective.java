package autotestcasegeneratetool;

import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.console.IConsoleConstants;

import console.ConsoleFactory;
import views.ManualDesignView;
import views.NavigationView;
import views.RowReqInfoView;
import views.VariablesView;
import views.TestCasesView;

public class Perspective implements IPerspectiveFactory {

	/**
	 * The ID of the perspective as specified in the extension.
	 */
	public static final String ID = "Auto-TestCase-generate-tool.perspective";
	public static final String MidFlloderID = "MidFloder";
	@Override
	public void createInitialLayout(IPageLayout layout) {
		String editorArea = layout.getEditorArea();
		layout.setEditorAreaVisible(false);
		IFolderLayout navigationFolder = layout.createFolder("需求管理器", IPageLayout.LEFT, 0.75f, editorArea);		
		navigationFolder.addView(NavigationView.ID);
		
		
		IFolderLayout folder = layout.createFolder("MidFloder", IPageLayout.RIGHT, 0.25f, NavigationView.ID);
		folder.addPlaceholder(TestCasesView.ID);
		folder.addPlaceholder(ManualDesignView.ID);
		folder.addView(TestCasesView.ID);
		
		
		IFolderLayout MessageFolder = layout.createFolder("处理", IPageLayout.RIGHT, 0.75f, TestCasesView.ID);
		MessageFolder.addView(VariablesView.ID);

		ConsoleFactory cf = new ConsoleFactory();
		layout.addView(IConsoleConstants.ID_CONSOLE_VIEW, IPageLayout.BOTTOM,
				0.70f, TestCasesView.ID);

		cf.openConsole();
		
		layout.addView(RowReqInfoView.ID, IPageLayout.BOTTOM, 0.70f, NavigationView.ID);
	}
}
