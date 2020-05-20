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
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;

import console.ConsoleHandler;
import views.ManualDesignView;
import views.TestCasesView;



/**  
* @ClassName: ManualDesignHandler  
* @Description: TODO(������һ�仰��������������)  
* @author Binver131  
* @date 2020��5��14��    
*/
public class ManualDesignHandler extends AbstractHandler{

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		// TODO Auto-generated method stub
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
		return null;
	}



}