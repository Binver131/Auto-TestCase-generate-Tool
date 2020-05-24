package handlers;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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
import entity.Requirement;
import entity.RowRequirement;
import entity.Type;
import entity.Variables;
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
			String modelname = modelNode.item(0).getAttributes().getNamedItem("Name").getTextContent();
			String modelID = modelNode.item(0).getAttributes().getNamedItem("Version").getTextContent();
			String modeltext = document.getElementsByTagName("modelDiscription").item(0).getFirstChild().getNodeValue();
			String modelClass = modelNode.item(0).getAttributes().getNamedItem("class").getTextContent();
			Model model = new Model(modelID);
			model.setName(modelname);
			model.setText(modeltext);
			model.setModelClass(modelClass);
			
			if(!ConnectHelper.existModel(model)) {
				ConsoleHandler.error("模型已存在");
				return null;
			}
			String key = ConnectHelper.addModel(model);
			
			
//			//解析类型
//			NodeList typenode = document.getElementsByTagName("type");
//			for(int i=0;i<typenode.getLength();i++) {
//				Type type= new Type();
//				String typename = typenode.item(i).getAttributes().getNamedItem("name").getTextContent();
//				String basetype = document.getElementsByTagName("baseType").item(i).getFirstChild().getNodeValue();
//				String typerange = document.getElementsByTagName("range").item(i).getFirstChild().getNodeValue();
//				  
//				
//				if(basetype.equals("Enumerated")) {
//					typerange= "["+typerange+"]";
//				}else {
//					//一共有八个基础类型，需要按类型对自定义类型的范围字符串处理
//				}
//				type.setTypename(basetype);
//				type.setTyperange(typerange);
//				//长度最好也根据不同的基础类型改变
//				type.setSizeString("4");
//				//这里返回的类型id还没有和变量关联
//				ConnectHelper.insertType(type, key);
//			}
			
			//解析原始需求
			Map<String, String> rowmap = new HashMap<String, String>();
			NodeList rowrequirementnode = document.getElementsByTagName("requirement");
			for(int i=0;i<rowrequirementnode.getLength();i++) {
				String rowrequirementname = rowrequirementnode.item(i).getAttributes().getNamedItem("name").getTextContent();
				String moderowrequirementtext = document.getElementsByTagName("content").item(i).getFirstChild().getNodeValue();
				RowRequirement rowRequirement = new RowRequirement();
				rowRequirement.setName(rowrequirementname);
				rowRequirement.setContent(moderowrequirementtext);
				rowRequirement.setParent(model);
				rowmap.put(ConnectHelper.addRowRequirement(rowRequirement,key), rowrequirementnode.item(i).getAttributes().getNamedItem("ID").getTextContent());
			}
			
			//解析需求
			NodeList requirementnode = document.getElementsByTagName("standardRequirement");
			for(int i=0;i<requirementnode.getLength();i++) {
				Requirement requirement = new Requirement();
				String requirementid = requirementnode.item(i).getAttributes().getNamedItem("ID").getTextContent();
				String requirementname = requirementnode.item(i).getAttributes().getNamedItem("name").getTextContent();
				String requirementtextString = "";
				
				NodeList variableinputnode = document.getElementsByTagName("inputs");
				NodeList variableoutputnode = document.getElementsByTagName("outputs");
				
				for(int j=0,k=variableinputnode.item(i).getChildNodes().getLength();j<k;j++) {
					if(variableinputnode.item(i).getChildNodes().item(j).getNodeName().equals("input")) {
						Variables variables = new Variables();
						String variableinputname =  variableinputnode.item(i).getChildNodes().item(j).getFirstChild().getNodeValue();
						
						//解析类型
						//类型的名称暂时没有用到
						String typename = variableinputnode.item(i).getChildNodes().item(j).getAttributes().getNamedItem("type").getTextContent();
						String basetype = variableinputnode.item(i).getChildNodes().item(j).getAttributes().getNamedItem("baseType").getTextContent();
						String typerange = variableinputnode.item(i).getChildNodes().item(j).getAttributes().getNamedItem("range").getTextContent();

						if(basetype.equals("Enumerated")) {
							typerange= "["+typerange+"]";
						}else if(basetype.equals("boolean")){
							typerange= "["+typerange+"]";
						}else {
							//一共有八个基础类型，需要按类型对自定义类型的范围字符串处理
							//Boolean Integer Float Double Enumerated String Char Unsigned
						}
						Type type= new Type();
						type.setTypename(typename);
						type.setTyperange(typerange);
						type.setBasetypename(basetype);
						type.setSizeString("4");			
						String variableinputtype = ConnectHelper.addType(type, key);
						variables.setVariablesName(variableinputname);
						variables.setVariablesTypeID(variableinputtype);
						variables.setVariablesID(ConnectHelper.addVariables(variables, key));
						requirement.addInputVar(variables);
					}
				}
				
				for(int j=0,k=variableoutputnode.item(i).getChildNodes().getLength();j<k;j++) {
					if(variableoutputnode.item(i).getChildNodes().item(j).getNodeName().equals("output")) {
						Variables variables = new Variables();
						String variableoutputname =  variableoutputnode.item(i).getChildNodes().item(j).getFirstChild().getNodeValue();
						String typename = variableoutputnode.item(i).getChildNodes().item(j).getAttributes().getNamedItem("type").getTextContent();
						String basetype = variableoutputnode.item(i).getChildNodes().item(j).getAttributes().getNamedItem("baseType").getTextContent();
						String typerange = variableoutputnode.item(i).getChildNodes().item(j).getAttributes().getNamedItem("range").getTextContent();

						if(basetype.equals("Enumerated")) {
							typerange= "["+typerange+"]";
						}else if(basetype.equals("boolean")){
							typerange= "["+typerange+"]";
						}else {
							//一共有八个基础类型，需要按类型对自定义类型的范围字符串处理
							//Boolean Integer Float Double Enumerated String Char Unsigned
						}
						Type type= new Type();
						type.setTypename(typename);
						type.setTyperange(typerange);
						type.setBasetypename(basetype);
						type.setSizeString("4");			
						String variableoutputtype = ConnectHelper.addType(type, key);
						variables.setVariablesName(variableoutputname);
						variables.setVariablesTypeID(variableoutputtype);
						variables.setVariablesID(ConnectHelper.addVariables(variables, key));
						requirement.addOutputVar(variables);
					}
				}
				
				String requirementrow = requirementnode.item(i).getAttributes().getNamedItem("relateReq").getTextContent();
				String rowID = null;
				for (Map.Entry<String, String> entry : rowmap.entrySet()) {	
				   if(entry.getValue().equals(requirementrow)) {
					    rowID = entry.getKey();
				   }
				}
				requirement.setRequirementId(requirementid);
				requirement.setRequirementName(requirementname);
				requirement.setRequirementText(requirementtextString);
				ConnectHelper.addRequirement(requirement, key, rowID);

			}
			
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
