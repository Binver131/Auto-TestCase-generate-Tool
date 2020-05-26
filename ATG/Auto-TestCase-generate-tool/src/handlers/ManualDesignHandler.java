
/**  
* @Title: ManualDesignHandler.java  
* @Package handlers  
* @Description: TODO(用一句话描述该文件做什么)  
* @author Binver131  
* @date 2020年5月14日  
* @version V1.0  
*/
package handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.ui.part.ViewPart;

import console.ConsoleHandler;
import entity.Requirement;
import views.ManualDesignView;
import views.TestCasesView;

/**
 * @ClassName: ManualDesignHandler
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author Binver131
 * @date 2020年5月14日
 */
public class ManualDesignHandler extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		Requirement requirement = null;
		ISelection selection = HandlerUtil.getActiveWorkbenchWindow(event).getActivePage().getSelection();		
		IStructuredSelection is = (IStructuredSelection) selection;
		if (is.getFirstElement() instanceof Requirement) {
			requirement = (Requirement) is.getFirstElement();
			ConsoleHandler.info(requirement.getRequirementName() + "新建测试用例");
			System.out.println(requirement.getRequirementName() + "新建测试用例");
		}
		// 显示界面
		IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
		IWorkbenchPage page = window.getActivePage();
		page.hideView(page.findView(TestCasesView.ID));
		try {
			page.showView(ManualDesignView.ID);
		} catch (PartInitException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ConsoleHandler.info("显示编辑视图");
		// 获取对象

		// 传参
		((ManualDesignView) page.findView(ManualDesignView.ID)).init(requirement);

		return null;
	}

}