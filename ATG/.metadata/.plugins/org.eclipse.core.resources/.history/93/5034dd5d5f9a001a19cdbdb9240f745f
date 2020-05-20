package handlers;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import console.ConsoleHandler;
import entity.Model;
import jdbc.ConnectHelper;
import views.NavigationView;

public class importHandler extends AbstractHandler{

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		// TODO Auto-generated method stub
		
		FileDialog fd=new FileDialog(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(),SWT.OPEN);
		  fd.setFilterPath(System.getProperty("JAVA.HOME"));
		  fd.setFilterExtensions(new String[]{"*.xml","*.*"});
		  fd.setFilterNames(new String[]{"XML Files(*.XML)","All Files(*.*)"});
		  String file=fd.open();
		  File path=new File(file);
		  if(file!=null){
		  ConsoleHandler.info("导入:"+path.getPath());
		  }
		  DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		  try {
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document document = db.parse("file:///"+path.getPath());
			
			NodeList modelNode = document.getElementsByTagName("VRMmodel");
			String name = modelNode.item(0).getAttributes().getNamedItem("Name").getTextContent();
			String ID = modelNode.item(0).getAttributes().getNamedItem("Version").getTextContent();
			String text = document.getElementsByTagName("modelDiscription").item(0).getFirstChild().getNodeValue();
			String modelClass = modelNode.item(0).getAttributes().getNamedItem("class").getTextContent();
			Model model = new Model(ID);
			model.setName(name);
			model.setText(text);
			model.setModelClass(modelClass);
			
			ConnectHelper.addModel(model);
			
			IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
			IWorkbenchPage page = window.getActivePage();
			((NavigationView)page.findView(NavigationView.ID)).refresh();
			
			
			NodeList rowReqList = document.getElementsByTagName("requirement");
			NodeList stdReqList = document.getElementsByTagName("standardRequirement");
			ConsoleHandler.info("原始需求条目数:"+rowReqList.getLength());
			ConsoleHandler.info("规范后需求条目数:"+stdReqList.getLength());
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
}
