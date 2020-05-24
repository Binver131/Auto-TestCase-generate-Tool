package handlers;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;

import console.ConsoleHandler;
import jdbc.ConnectHelper;
import views.NavigationView;


public class DeleteModelHandler extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		ConsoleHandler.info("刪除模型");
		//TODO 從數據模型 database裡面移除此模型  ，從數據庫中刪除此模型 及測試用例
		
		ConnectHelper.removeVariable("4");
		ConnectHelper.remodeType("4");
		ConnectHelper.removeRequirement("4");
		ConnectHelper.removeRowRequirement("4");
		ConnectHelper.removeModel("4");
		
		//刷新界面
		IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
		IWorkbenchPage page = window.getActivePage();
		((NavigationView)page.findView(NavigationView.ID)).refresh();
		return null;
	}

	

}
