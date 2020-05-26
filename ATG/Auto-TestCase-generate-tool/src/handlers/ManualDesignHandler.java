/**  
* @Title: ManualDesignHandler.java  
* @Package handlers  
* @Description: TODO(��һ�仰�������ļ���ʲô)  
* @author Binver131  
* @date 2020��5��14��  
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
 * @Description: TODO(������һ�仰��������������)
 * @author Binver131
 * @date 2020��5��14��
 */
public class ManualDesignHandler extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		Requirement requirement = null;
		ISelection selection = HandlerUtil.getActiveWorkbenchWindow(event).getActivePage().getSelection();		
		IStructuredSelection is = (IStructuredSelection) selection;
		if (is.getFirstElement() instanceof Requirement) {
			requirement = (Requirement) is.getFirstElement();
			ConsoleHandler.info(requirement.getRequirementName() + "�½���������");
			System.out.println(requirement.getRequirementName() + "�½���������");
		}
		// ��ʾ����
		IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
		IWorkbenchPage page = window.getActivePage();
		page.hideView(page.findView(TestCasesView.ID));
		try {
			page.showView(ManualDesignView.ID);
		} catch (PartInitException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ConsoleHandler.info("��ʾ�༭��ͼ");
		// ��ȡ����

		// ����
		((ManualDesignView) page.findView(ManualDesignView.ID)).init(requirement);

		return null;
	}

}
