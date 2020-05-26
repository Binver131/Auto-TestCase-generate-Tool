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
import entity.Model;
import jdbc.ConnectHelper;
import views.NavigationView;


public class DeleteModelHandler extends AbstractHandler {

	

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
//		ConnectHelper.removeVariable("4");
//		ConnectHelper.remodeType("4");
//		ConnectHelper.removeRequirement("4");
//		ConnectHelper.removeRowRequirement("4");
		
		ISelection selection =  PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getSelection();
		  if(selection!= null && selection instanceof IStructuredSelection){
		   IStructuredSelection is = (IStructuredSelection)selection;
		   
		   
		   if(is.getFirstElement() instanceof Model) {
		    Iterator<Model> it = is.iterator();
		    while (it.hasNext()) {
		     Model model = (Model) it.next();
		     ConnectHelper.removeModel(model.getDbId());
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
