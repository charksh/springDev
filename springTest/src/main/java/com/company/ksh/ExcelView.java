package com.company.ksh;

import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.web.servlet.view.document.AbstractExcelView;

public class ExcelView extends AbstractExcelView {
	
	@Override
	protected void buildExcelDocument(Map<String,Object> model,
									  HSSFWorkbook workbook, 
									  HttpServletRequest request, 
									  HttpServletResponse response) throws Exception {
		
		// Controller 에서 Setting(혹은 DB에서 가져온값) 한 값
		ArrayList<String> list = (ArrayList<String>)model.get("excelList");
		
		String excelFileName = "codetest.xls";
		
		Sheet sheet = workbook.createSheet("Sheet1");
		Row row = sheet.createRow(0);
		
		row.createCell(0).setCellValue("Name");
		row.createCell(1).setCellValue("Phone");
		row.createCell(2).setCellValue("Address");
		
		row = sheet.createRow(1);
		row.createCell(0).setCellValue(list.get(0));
		row.createCell(1).setCellValue(list.get(1));
		row.createCell(2).setCellValue(list.get(2));			
		
		response.setHeader("Content-Disposition", "attachment; filename=" + excelFileName);
		response.setContentType("application/vnd.ms-excel");

		/* 서버에 저장할 때만 사용함
		try (FileOutputStream outs = new FileOutputStream(Paths.get(excelFileName).toString())) {
			workbook.write(outs);			
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		*/
		
	}
}
