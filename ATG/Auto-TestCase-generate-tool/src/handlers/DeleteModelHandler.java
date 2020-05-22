package handlers;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;

import console.ConsoleHandler;
import views.NavigationView;


public class DeleteModelHandler extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		ConsoleHandler.info("�h��ģ��");
		//TODO �Ĕ���ģ�� database�e���Ƴ���ģ��  ���Ĕ������Єh����ģ�� ���yԇ����
		
		//ˢ�½���
		IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
		IWorkbenchPage page = window.getActivePage();
		((NavigationView)page.findView(NavigationView.ID)).refresh();
		return null;
	}

	

}
