package handlers;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.ui.PlatformUI;

import bean.Model;
import bean.Requirement;
import bean.RowRequirement;
import bean.Testcase;
import bean.Variable;
import console.ConsoleHandler;

public class ExportModelHandler extends AbstractHandler{

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		ISelection selection =  PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getSelection();
		  if(selection!= null && selection instanceof IStructuredSelection){
		   IStructuredSelection is = (IStructuredSelection)selection;
		   if(is.getFirstElement() instanceof Model) {
		    Iterator<Model> it = is.iterator();
		    while (it.hasNext()) {
		     Model model = (Model) it.next();
		     //System.out.println(model.getModelIdentifier());
		      FileDialog fd=new FileDialog(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(),SWT.SAVE);
			  fd.setFilterPath(System.getProperty("JAVA.HOME"));
			  fd.setFilterExtensions(new String[]{"*.xls","*.*"});
			  fd.setFilterNames(new String[]{"Excel 工作簿(*.xls)","All Files(*.*)"});
			  String file=fd.open();
			  ConsoleHandler.info("导出:"+file);
			  
			  HSSFWorkbook wb = new HSSFWorkbook();
				//创建HSSFSheet对象
			  Sheet sheet = wb.createSheet("TestCase");
				
				CellStyle centerStyle=wb.createCellStyle();
				CellRangeAddress rg0_0 = new CellRangeAddress(0,0,(short)0,(short)7);
				CellRangeAddress rg1_1 = new CellRangeAddress(1,1,(short)0,(short)7);
				CellRangeAddress rg2_2 = new CellRangeAddress(2,2,(short)1,(short)4);
				CellRangeAddress rg3_3 = new CellRangeAddress(3,3,(short)1,(short)4);
				CellRangeAddress rg4_4 = new CellRangeAddress(4,4,(short)1,(short)4);
				CellRangeAddress rg5_5 = new CellRangeAddress(5,5,(short)1,(short)4);
				sheet.addMergedRegion(rg0_0);
				sheet.addMergedRegion(rg1_1);
				sheet.addMergedRegion(rg2_2);
				sheet.addMergedRegion(rg3_3);
				sheet.addMergedRegion(rg4_4);
				sheet.addMergedRegion(rg5_5);
				//cellstyle1格式是居中加粗
				CellStyle cellstyle1 = wb.createCellStyle();
				cellstyle1.setAlignment(HorizontalAlignment.CENTER);
				
				//cellstyle2是加粗
				CellStyle cellstyle2 = wb.createCellStyle();

				Font font = wb.createFont();
				font.setBold(true);
				cellstyle1.setFont(font);
				cellstyle2.setFont(font);
				//创建HSSFRow对象
				//第一行
				Row row1 = sheet.createRow(0);
				//创建HSSFCell对象
				Cell cell1_1=row1.createCell(0);
				cell1_1.setCellStyle(cellstyle1);
				//设置单元格的值
				cell1_1.setCellValue("COPYRIGHT 2014 - 2018 (C) SAVIC PROPRIETARY INFORMATION\r\n" + 
						"SOFTWARE TEST SPECIFICATION");
				//第2行
				Row row2 = sheet.createRow(1);
				Cell cell2_1=row2.createCell(0);
				cell2_1.setCellStyle(cellstyle1);
				cell2_1.setCellValue("PART III: TEST CASES");
				//第三行
				Row row3 = sheet.createRow(2);
				Cell cell3_1=row3.createCell(0);
				cell3_1.setCellStyle(cellstyle2);
				cell3_1.setCellValue("SCRIPT NAME");
				Cell cell3_2=row3.createCell(1);
				cell3_2.setCellValue("AUX_UTC_HLR1");
				//第四行
				Row row4 = sheet.createRow(3);
				Cell cell4_1=row4.createCell(0);
				cell4_1.setCellStyle(cellstyle2);
				cell4_1.setCellValue("HEADER");	
				//第五行
				Row row5 = sheet.createRow(4);
				Cell cell5_1=row5.createCell(1);
				cell5_1.setCellValue("Test AUX Universal Time Coordinated function.");	
				//第六行
				Row row6 = sheet.createRow(5);
				Cell cell6_1=row6.createCell(0);
				cell6_1.setCellStyle(cellstyle2);
				cell6_1.setCellValue("END OF HEADER");
				//第八行
				Row row8 = sheet.createRow(7);
				Cell cell8_1=row8.createCell(0);
				Cell cell8_2=row8.createCell(1);
				cell8_1.setCellStyle(cellstyle2);
				cell8_1.setCellValue("START OF TEST CASE");
				cell8_2.setCellValue("INITIALISATION");
				//第九行
				Row row9 = sheet.createRow(8);
				Cell cell9_1=row9.createCell(0);
				Cell cell9_3=row9.createCell(2);
				cell9_1.setCellStyle(cellstyle2);
				cell9_1.setCellValue("START OF TEST PROMPT");
				cell9_3.setCellValue("This is the initialisation test case.");
				//第十行
				Row row10 = sheet.createRow(9);
				Cell cell10_3=row10.createCell(2);
				cell10_3.setCellValue("It will be run at the beginning of the test to initialize corresponding parameters to normal status.");
				//第十一行
				Row row11 = sheet.createRow(10);
				Cell cell11_1=row11.createCell(0);
				cell11_1.setCellStyle(cellstyle2);
				cell11_1.setCellValue("END OF TEST PROMPT");
				//第十三行
				Row row13 = sheet.createRow(12);
				Cell cell13_1 = row13.createCell(0);
				cell13_1.setCellStyle(cellstyle2);
				cell13_1.setCellValue("START OF TEST SCRIPT");
				//第十四行
				Row row14 = sheet.createRow(13);
				Cell cell14_1 = row14.createCell(0);
				Cell cell14_2 = row14.createCell(1);
				Cell cell14_3 = row14.createCell(2);
				cell14_1.setCellStyle(cellstyle2);
				cell14_1.setCellValue("1");
				cell14_2.setCellValue("#INITIALISE_SCRIPT");
				cell14_3.setCellValue("AUX_INIT");
				//第十五行
				Row row15 = sheet.createRow(14);
				Cell cell15_1 = row15.createCell(0);
				Cell cell15_2 = row15.createCell(1);
				Cell cell15_3 = row15.createCell(2);
				Cell cell15_4 = row15.createCell(3);
				cell15_1.setCellStyle(cellstyle2);
				cell15_1.setCellValue("2");
				cell15_2.setCellValue("CALL_FUNCTION");
				cell15_3.setCellValue("AUX_INIT");
				cell15_4.setCellValue("AUX_INIT_FUNC");
				//十六行
				Row row16 = sheet.createRow(15);
				Cell cell16_1 = row16.createCell(0);
				cell16_1.setCellStyle(cellstyle2);
				cell16_1.setCellValue("END OF TEST SCRIPT");
				//十七行
				Row row17 = sheet.createRow(16);
				Cell cell17_1 = row17.createCell(0);
				cell17_1.setCellStyle(cellstyle2);
				cell17_1.setCellValue("END OF TEST CASE");
				int rowCount = 16;
				for(RowRequirement rowRequirement:model.getChildren().values()) {
					for(Requirement requirement: rowRequirement.getRequirement()) {
						for(Testcase testcase:requirement.getTestcaseMap().values()) {
							rowCount= rowCount+2;
							Row rowt1 = sheet.createRow(rowCount);
							Cell cellt1_0 = rowt1.createCell(0);
							Cell cellt1_1 = rowt1.createCell(1);
							cellt1_0.setCellStyle(cellstyle2);
							cellt1_0.setCellValue("START OF TEST CASE");
							cellt1_1.setCellValue(testcase.getTestcaseID());
							
							Row rowt2 = sheet.createRow(++rowCount);
							Cell cellt2_0 = rowt2.createCell(0);
							Cell cellt2_1 = rowt2.createCell(1);
							Cell cellt2_2 = rowt2.createCell(2);
							cellt2_0.setCellStyle(cellstyle2);
							cellt2_0.setCellValue("START OF TEST PROMPT");
							cellt2_1.setCellValue("TESTING:");
							cellt2_2.setCellValue("这里填，这条测试向量的具体描述暂时没做考虑");
							
							rowCount=rowCount+2;
							Row rowt3 = sheet.createRow(rowCount);
							Cell cellt3_1 = rowt3.createCell(1);
							Cell cellt3_2 = rowt3.createCell(2);
							cellt3_1.setCellValue("REQUIREMENTS TESTED:");
							cellt3_2.setCellValue(requirement.getRequirementIdentifier());
							
							rowCount=rowCount+2;
							Row rowt4 = sheet.createRow(rowCount);
							Cell cellt4_1 = rowt4.createCell(1);
							cellt4_1.setCellValue("ACTION:");
							rowCount=rowCount+1;
							for(Entry<Variable, String> condition:testcase.getTestcaseConditionMap().entrySet()) {
								Row rowCondition = sheet.createRow(++rowCount);
								Cell cellCondition1 = rowCondition.createCell(1);
								Cell cellCondition2 = rowCondition.createCell(2);
								cellCondition1.setCellValue("ENSURE:");
								cellCondition2.setCellValue(condition.getKey().getVariableName()+" = "+condition.getValue());
							}
							
							Row rowt5 = sheet.createRow(++rowCount);
							Cell cellt5_0 = rowt5.createCell(0);
							cellt5_0.setCellStyle(cellstyle2);
							cellt5_0.setCellValue("END OF TEST PROMPT");
							
							rowCount=rowCount+2;
							Row rowt6 = sheet.createRow(rowCount);
							Cell cellt6_0 = rowt6.createCell(0);
							cellt6_0.setCellStyle(cellstyle2);
							cellt6_0.setCellValue("START OF TEST SCRIPT");
							
							Row rowt7 = sheet.createRow(++rowCount);
							Cell cellt7_0 = rowt7.createCell(0);
							Cell cellt7_1 = rowt7.createCell(1);
							Cell cellt7_2 = rowt7.createCell(2);
							int countTestScript = 1;
							cellt7_0.setCellStyle(cellstyle2);
							cellt7_0.setCellValue(countTestScript);
							cellt7_1.setCellValue("COMMENT_PYTHON");
							cellt7_2.setCellValue("描述输入变量，暂时没考虑");
							
							for(Entry<Variable, String> input:testcase.getTestcaseInputMap().entrySet()) {
								countTestScript++;
								Row rowInput = sheet.createRow(++rowCount);
								Cell cellInput0 = rowInput.createCell(0);
								Cell cellInput1 = rowInput.createCell(1);
								Cell cellInput2 = rowInput.createCell(2);
								Cell cellInput3 = rowInput.createCell(3);
								cellInput0.setCellStyle(cellstyle2);
								cellInput0.setCellValue(countTestScript);
								cellInput1.setCellValue("SET");
								cellInput2.setCellValue(input.getKey().getVariableName());
								cellInput3.setCellValue(input.getValue());
							}
							for(Entry<Variable, String> output:testcase.getTestcaseOutputMap().entrySet()) {
								countTestScript++;
								Row rowOutput = sheet.createRow(++rowCount);
								Cell cellOutput0 = rowOutput.createCell(0);
								Cell cellOutput1 = rowOutput.createCell(1);
								Cell cellOutput2 = rowOutput.createCell(2);
								cellOutput0.setCellStyle(cellstyle2);
								cellOutput0.setCellValue(countTestScript);
								cellOutput1.setCellValue("VERIFY");
								cellOutput2.setCellValue(output.getKey().getVariableName()+" = "+output.getValue());
							}
							Row rowt8 = sheet.createRow(++rowCount);
							Cell cellt8_0 = rowt8.createCell(0);
							cellt8_0.setCellStyle(cellstyle2);
							cellt8_0.setCellValue("END OF TEST SCRIPT");
							
							Row rowt9 = sheet.createRow(++rowCount);
							Cell cellt9_0 = rowt9.createCell(0);
							cellt9_0.setCellStyle(cellstyle2);
							cellt9_0.setCellValue("END OF TEST CASE");
						}
					}
				}
				rowCount=rowCount+2;
				Row rowlast = sheet.createRow(rowCount);
				Cell celllast = rowlast.createCell(0);
				celllast.setCellStyle(cellstyle2);
				celllast.setCellValue("END OF ALL TESTS");
				
				FileOutputStream output;
				try {
					output = new FileOutputStream(file);
					wb.write(output);
					output.flush();
					output.close();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
				
		    }
		  }
		}
		return null;
	}
}
