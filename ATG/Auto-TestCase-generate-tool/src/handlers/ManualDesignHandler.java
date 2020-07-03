
/**  
* @Title: ManualDesignHandler.java  
* @Package handlers  
* @Description: TODO(鐢ㄤ竴鍙ヨ瘽鎻忚堪璇ユ枃浠跺仛浠�涔�)  
* @author Binver131  
* @date 2020骞�5鏈�14鏃�  
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

import bean.Requirement;
import console.ConsoleHandler;

import views.ManualDesignView;
import views.TestCasesView;

/**
 * @ClassName: ManualDesignHandler
 * @Description: TODO(杩欓噷鐢ㄤ竴鍙ヨ瘽鎻忚堪杩欎釜绫荤殑浣滅敤)
 * @author Binver131
 * @date 2020骞�5鏈�14鏃�
 */
public class ManualDesignHandler extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		Requirement requirement = null;
		ISelection selection = HandlerUtil.getActiveWorkbenchWindow(event).getActivePage().getSelection();		
		IStructuredSelection is = (IStructuredSelection) selection;
		if (is.getFirstElement() instanceof Requirement) {
			requirement = (Requirement) is.getFirstElement();
			ConsoleHandler.info(requirement.getRequirementName() + "鏂板缓娴嬭瘯鐢ㄤ緥");
			System.out.println(requirement.getRequirementName() + "鏂板缓娴嬭瘯鐢ㄤ緥");
		}
		// 鏄剧ず鐣岄潰
		IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
		IWorkbenchPage page = window.getActivePage();
		page.hideView(page.findView(TestCasesView.ID));
		try {
			page.showView(ManualDesignView.ID);
		} catch (PartInitException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ConsoleHandler.info("鏄剧ず缂栬緫瑙嗗浘");
		// 鑾峰彇瀵硅薄

		// 浼犲弬
		((ManualDesignView) page.findView(ManualDesignView.ID)).init(requirement);

		return null;
	}

}