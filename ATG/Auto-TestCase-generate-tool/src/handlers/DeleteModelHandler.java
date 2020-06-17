package handlers;
import java.util.Iterator;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;

import console.ConsoleHandler;
import bean.Model;
import jdbc.ConnectHelp;
import views.NavigationView;


public class DeleteModelHandler extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		ConsoleHandler.info("�h��ģ��");
		//TODO �Ĕ���ģ�� database�e���Ƴ���ģ��  ���Ĕ������Єh����ģ�� ���yԇ����
		
		ISelection selection =  PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getSelection();
		  if(selection!= null && selection instanceof IStructuredSelection){
		   IStructuredSelection is = (IStructuredSelection)selection;
		   
		   
		   if(is.getFirstElement() instanceof Model) {
		    Iterator<Model> it = is.iterator();
		    while (it.hasNext()) {
		     Model model = (Model) it.next();
		     //ConnectHelper.removeModel(model.getDbId());
		     ConnectHelp.deleteModel(model);
		     ConnectHelp.deleteRowRequirement(model.getModelID());
		     ConnectHelp.deleteRequirement(model.getModelID());
		     ConnectHelp.deleteType(model.getModelID());
		     ConnectHelp.deleteVariable(model.getModelID());
		     ConsoleHandler.info("The Model "+model.toString()+" will be deleted");
		    }
		   }
		  }
		
		//ˢ�½���
		IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
		IWorkbenchPage page = window.getActivePage();
		((NavigationView)page.findView(NavigationView.ID)).refresh();
		return null;
	}

	

}
