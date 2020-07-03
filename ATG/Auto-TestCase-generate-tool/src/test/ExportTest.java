package test;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;


public class ExportTest {
	public static void Test() throws IOException {
		


		HSSFWorkbook wb = new HSSFWorkbook();
		//创建HSSFSheet对象
		HSSFSheet sheet = wb.createSheet("TestCase");
		
		HSSFCellStyle centerStyle=wb.createCellStyle();
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

		HSSFFont font = wb.createFont();
		font.setBold(true);
		cellstyle1.setFont(font);
		cellstyle2.setFont(font);
		//创建HSSFRow对象
		//第一行
		HSSFRow row1 = sheet.createRow(0);
		//创建HSSFCell对象
		HSSFCell cell1_1=row1.createCell(0);
		cell1_1.setCellStyle(cellstyle1);
		//设置单元格的值
		cell1_1.setCellValue("COPYRIGHT 2014 - 2018 (C) SAVIC PROPRIETARY INFORMATION\r\n" + 
				"SOFTWARE TEST SPECIFICATION");
		//第2行
		HSSFRow row2 = sheet.createRow(1);
		HSSFCell cell2_1=row2.createCell(0);
		cell2_1.setCellStyle(cellstyle1);
		cell2_1.setCellValue("PART III: TEST CASES");
		//第三行
		HSSFRow row3 = sheet.createRow(2);
		HSSFCell cell3_1=row3.createCell(0);
		cell3_1.setCellStyle(cellstyle2);
		cell3_1.setCellValue("SCRIPT NAME");
		HSSFCell cell3_2=row3.createCell(1);
		cell3_2.setCellValue("AUX_UTC_HLR1");
		//第四行
		HSSFRow row4 = sheet.createRow(3);
		HSSFCell cell4_1=row4.createCell(0);
		cell4_1.setCellStyle(cellstyle2);
		cell4_1.setCellValue("HEADER");	
		//第五行
		HSSFRow row5 = sheet.createRow(4);
		HSSFCell cell5_1=row5.createCell(1);
		cell5_1.setCellValue("Test AUX Universal Time Coordinated function.");	
		//第六行
		HSSFRow row6 = sheet.createRow(5);
		HSSFCell cell6_1=row6.createCell(0);
		cell6_1.setCellStyle(cellstyle2);
		cell6_1.setCellValue("END OF HEADER");
		//第八行
		HSSFRow row8 = sheet.createRow(7);
		HSSFCell cell8_1=row8.createCell(0);
		HSSFCell cell8_2=row8.createCell(1);
		cell8_1.setCellStyle(cellstyle2);
		cell8_1.setCellValue("START OF TEST CASE");
		cell8_2.setCellValue("INITIALISATION");
		//第九行
		HSSFRow row9 = sheet.createRow(8);
		HSSFCell cell9_1=row9.createCell(0);
		HSSFCell cell9_3=row9.createCell(2);
		cell9_1.setCellStyle(cellstyle2);
		cell9_1.setCellValue("START OF TEST PROMPT");
		cell9_3.setCellValue("This is the initialisation test case.");
		//第十行
		HSSFRow row10 = sheet.createRow(9);
		HSSFCell cell10_3=row10.createCell(2);
		cell10_3.setCellValue("It will be run at the beginning of the test to initialize corresponding parameters to normal status.");
		//第十一行
		HSSFRow row11 = sheet.createRow(10);
		HSSFCell cell11_1=row11.createCell(0);
		cell11_1.setCellStyle(cellstyle2);
		cell11_1.setCellValue("END OF TEST PROMPT");
		//第十三行
		HSSFRow row13 = sheet.createRow(12);
		HSSFCell cell13_1 = row13.createCell(0);
		cell13_1.setCellStyle(cellstyle2);
		cell13_1.setCellValue("START OF TEST SCRIPT");
		//第十四行
		HSSFRow row14 = sheet.createRow(13);
		HSSFCell cell14_1 = row14.createCell(0);
		HSSFCell cell14_2 = row14.createCell(1);
		HSSFCell cell14_3 = row14.createCell(2);
		cell14_1.setCellStyle(cellstyle2);
		cell14_1.setCellValue("1");
		cell14_2.setCellValue("#INITIALISE_SCRIPT");
		cell14_3.setCellValue("AUX_INIT");
		//第十五行
		HSSFRow row15 = sheet.createRow(14);
		HSSFCell cell15_1 = row15.createCell(0);
		HSSFCell cell15_2 = row15.createCell(1);
		HSSFCell cell15_3 = row15.createCell(2);
		HSSFCell cell15_4 = row15.createCell(3);
		cell15_1.setCellStyle(cellstyle2);
		cell15_1.setCellValue("2");
		cell15_2.setCellValue("CALL_FUNCTION");
		cell15_3.setCellValue("AUX_INIT");
		cell15_4.setCellValue("AUX_INIT_FUNC");
		//十六行
		HSSFRow row16 = sheet.createRow(15);
		HSSFCell cell16_1 = row16.createCell(0);
		cell16_1.setCellStyle(cellstyle2);
		cell16_1.setCellValue("END OF TEST SCRIPT");
		//十七行
		HSSFRow row17 = sheet.createRow(16);
		HSSFCell cell17_1 = row17.createCell(0);
		cell17_1.setCellStyle(cellstyle2);
		cell17_1.setCellValue("END OF TEST CASE");
		
		FileOutputStream output=new FileOutputStream("d:\\workbook.xls");	
		wb.write(output);
		output.flush();
	}
	public static void main(String args[]) throws IOException {
		ExportTest.Test();
	}
}
