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
import org.eclipse.jdt.ui.wizards.NewAnnotationWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import console.ConsoleHandler;
import bean.Model;
import bean.Requirement;
import bean.RowRequirement;
import bean.Type;
import bean.Variable;
import jdbc.ConnectHelp;
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
		  ConsoleHandler.info("����:"+path.getPath());
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
			Model model = new Model();
			model.setModelIdentifier(modelID);
			model.setModelName(modelname);
			model.setModelContent(modeltext);
			model.setModelClass(modelClass);
			
			if(!ConnectHelp.existModel(model)) {
				ConsoleHandler.error("ģ���Ѵ���");
				return null;
			}
			ConnectHelp.insertModel(model);
			
			//����ԭʼ����
			NodeList rowrequirementnode = document.getElementsByTagName("requirement");
			for(int i=0;i<rowrequirementnode.getLength();i++) {
				String rowrequirementname = rowrequirementnode.item(i).getAttributes().getNamedItem("name").getTextContent();
				String rowrequirementIdentifier = rowrequirementnode.item(i).getAttributes().getNamedItem("ID").getTextContent();
				String moderowrequirementtext = document.getElementsByTagName("content").item(i).getFirstChild().getNodeValue();
				RowRequirement rowRequirement = new RowRequirement();
				rowRequirement.setRowRequirementName(rowrequirementname);
				rowRequirement.setRowRequirementIdentifier(rowrequirementIdentifier);
				rowRequirement.setRowRequirementContent(moderowrequirementtext);
				rowRequirement.setParent(model);
				rowRequirement.setModelID(model.getModelID());
			    ConnectHelp.insertRowRequirement(rowRequirement);
				model.addRowRequirementMap(rowRequirement);
			}
			
			//��������
			NodeList requirementnode = document.getElementsByTagName("standardRequirement");
			NodeList variableinputnode = document.getElementsByTagName("inputs");
			NodeList variableoutputnode = document.getElementsByTagName("outputs");
			for(int i=0;i<requirementnode.getLength();i++) {
				Requirement requirement = new Requirement();
				String requirementid = requirementnode.item(i).getAttributes().getNamedItem("ID").getTextContent();
				String requirementname = requirementnode.item(i).getAttributes().getNamedItem("name").getTextContent();
				String requirementtextString = "";
				String requirementConditionString="";
				String requirementInputString="";
				String requirementOutputString="";
				for(int j=0,k=variableinputnode.item(i).getChildNodes().getLength();j<k;j++) {
					if(variableinputnode.item(i).getChildNodes().item(j).getNodeName().equals("input")) {
						Variable variables = new Variable();
						String variableinputname = variableinputnode.item(i).getChildNodes().item(j).getFirstChild().getNodeValue();
						
						//��������
						//���͵�������ʱû���õ�
						String typename = variableinputnode.item(i).getChildNodes().item(j).getAttributes().getNamedItem("type").getTextContent();
						String basetype = variableinputnode.item(i).getChildNodes().item(j).getAttributes().getNamedItem("baseType").getTextContent();
						String typerange = variableinputnode.item(i).getChildNodes().item(j).getAttributes().getNamedItem("range").getTextContent();

						if(basetype.equals("Enumerated")) {
							typerange= typerange;
						}else if(basetype.equals("boolean")){
							typerange= typerange;
						}else {
							//һ���а˸��������ͣ���Ҫ�����Ͷ��Զ������͵ķ�Χ�ַ�������
							//Boolean Integer Float Double Enumerated String Char Unsigned
						}
						Type type= new Type();
						type.setTypeName(typename);
						type.setTypeRange(typerange);
						type.setTypeRowName(basetype);
						type.setTypeSize(4);
						type.setModelID(model.getModelID());
						if(ConnectHelp.existType(model, type)!=null) {
							variables.setTypeID(ConnectHelp.existType(model, type).getTypeID());
							variables.setVariableType(ConnectHelp.existType(model, type));
						}else {
							ConnectHelp.insertType(type);
							model.addTypeMap(type);
							variables.setTypeID(type.getTypeID());
							variables.setVariableType(type);
						}
						variables.setVariableName(variableinputname);
						variables.setModelID(model.getModelID());
						if(ConnectHelp.existVariable(model,variables)!=null) {
							requirementInputString=requirementInputString+ ConnectHelp.existVariable(model,variables).getVariableID()+",";
						}else {
							ConnectHelp.insertVariable(variables);
							model.addVariableMap(variables);
							requirementInputString = requirementInputString+variables.getVariableID()+",";
						}
					}
				}
				for(int j=0,k=variableoutputnode.item(i).getChildNodes().getLength();j<k;j++) {
					if(variableoutputnode.item(i).getChildNodes().item(j).getNodeName().equals("output")) {
						Variable variables = new Variable();
						String variableoutputname =  variableoutputnode.item(i).getChildNodes().item(j).getFirstChild().getNodeValue();
						String typename = variableoutputnode.item(i).getChildNodes().item(j).getAttributes().getNamedItem("type").getTextContent();
						String basetype = variableoutputnode.item(i).getChildNodes().item(j).getAttributes().getNamedItem("baseType").getTextContent();
						String typerange = variableoutputnode.item(i).getChildNodes().item(j).getAttributes().getNamedItem("range").getTextContent();
						if(basetype.equals("Enumerated")) {
							typerange= typerange;
						}else if(basetype.equals("boolean")){
							typerange= typerange;
						}else {
							//һ���а˸��������ͣ���Ҫ�����Ͷ��Զ������͵ķ�Χ�ַ�������
							//Boolean Integer Float Double Enumerated String Char Unsigned
						}
						Type type= new Type();
						type.setTypeName(typename);
						type.setTypeRange(typerange);
						type.setTypeRowName(basetype);
						type.setTypeSize(4);
						type.setModelID(model.getModelID());
						if(ConnectHelp.existType(model, type)!=null) {
							variables.setTypeID(ConnectHelp.existType(model, type).getTypeID());
							variables.setVariableType(ConnectHelp.existType(model, type));
						}else {
							ConnectHelp.insertType(type);
							model.addTypeMap(type);
							variables.setTypeID(type.getTypeID());
							variables.setVariableType(type);
						}
						variables.setVariableName(variableoutputname);
						variables.setModelID(model.getModelID());
						if(ConnectHelp.existVariable(model,variables)!=null) {
							requirementOutputString=requirementOutputString+ConnectHelp.existVariable(model,variables).getVariableID()+",";
						}else {
							ConnectHelp.insertVariable(variables);
							model.addVariableMap(variables);
							requirementOutputString = requirementOutputString+variables.getVariableID()+",";
						}
					}
				}

				requirement.setRequirementCondition(requirementConditionString);
				requirement.setRequirementContent(requirementtextString);
				requirement.setRequirementInput(requirementInputString);
				requirement.setRequirementIdentifier(requirementid);
				requirement.setRequirementOutput(requirementOutputString);
				requirement.setRequirementName(requirementname);
				requirement.setModelID(model.getModelID());
				String requirementrow = requirementnode.item(i).getAttributes().getNamedItem("relateReq").getTextContent();
				
				model.getChildren().get(requirementrow).addRequirementMap(requirement);
				
				for(RowRequirement temp:model.getRowRequirement()) {
					if(temp.getRowRequirementIdentifier().equals(requirementrow)) {
						requirement.setParent(temp);
						requirement.setRowRequirementID(temp.getRowRequirementID());
						temp.addRequirementMap(requirement);
					}
				}
				ConnectHelp.insertRequirement(requirement);
			}
			ConnectHelp.database.addModelMap(model);
			IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
			IWorkbenchPage page = window.getActivePage();
			((NavigationView)page.findView(NavigationView.ID)).refresh();
			
			
			NodeList rowReqList = document.getElementsByTagName("requirement");
			NodeList stdReqList = document.getElementsByTagName("standardRequirement");
			ConsoleHandler.info("ԭʼ������Ŀ��:"+rowReqList.getLength());
			ConsoleHandler.info("�淶��������Ŀ��:"+stdReqList.getLength());
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
}
