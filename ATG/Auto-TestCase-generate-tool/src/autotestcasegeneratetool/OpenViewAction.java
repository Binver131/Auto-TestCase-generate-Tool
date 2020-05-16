package autotestcasegeneratetool;

import java.net.URL;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.*;
import org.osgi.framework.Bundle;

import auto_testcase_generate_tool.Activator;


public class OpenViewAction extends Action {
	
	private final IWorkbenchWindow window;
	private int instanceNum = 0;
	private final String viewId;
	
	public OpenViewAction(IWorkbenchWindow window, String label, String viewId) {
		this.window = window;
		this.viewId = viewId;
        setText(label);
        // The id is used to refer to the action in a menu or toolbar
		setId(ICommandIds.CMD_OPEN);
        // Associate the action with a pre-defined command, to allow key bindings.
		setActionDefinitionId(ICommandIds.CMD_OPEN);
        Bundle bundle = Platform.getBundle(Activator.PLUGIN_ID);
        URL fileURL = bundle.getEntry("/icons/sample2.gif");
        setImageDescriptor(ImageDescriptor.createFromURL(fileURL));
	}
	
	@Override
	public void run() {
		if(window != null) {	
			try {
				window.getActivePage().showView(viewId);
			} catch (PartInitException e) {
				MessageDialog.openError(window.getShell(), "Error", "Error opening view:" + e.getMessage());
			}
		}
	}
}
