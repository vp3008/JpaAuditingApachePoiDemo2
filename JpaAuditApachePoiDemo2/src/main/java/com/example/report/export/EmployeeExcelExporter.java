package com.example.report.export;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.example.report.dto.EmployeeTo;

public class EmployeeExcelExporter {
	private XSSFWorkbook workbook;
	private XSSFSheet sheet;
	
	
	public EmployeeExcelExporter(List<EmployeeTo> listEmployees) {
		super();
		this.listEmployees = listEmployees;
		workbook = new XSSFWorkbook();
		sheet = workbook.createSheet("Employees");
	}

	private List<EmployeeTo> listEmployees;
	
	private void writeHeaderRow() {
		Row row = sheet.createRow(0);
		
		CellStyle style = workbook.createCellStyle();
		XSSFFont font = workbook.createFont();
		font.setBold(true);
		font.setFontHeight(10);
		style.setFont(font);
		style.setAlignment(HorizontalAlignment.CENTER);
		style.setBorderBottom(BorderStyle.THIN);
		style.setBorderLeft(BorderStyle.THIN);
		style.setBorderRight(BorderStyle.THIN);
		style.setBorderTop(BorderStyle.THIN);
		style.setFillForegroundColor(IndexedColors.AQUA.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

		
		Cell cell = row.createCell(0);
		cell.setCellValue("Employee-Id");
		cell.setCellStyle(style);
		
		cell = row.createCell(1);
		cell.setCellValue("Name");
		cell.setCellStyle(style);
		
		cell = row.createCell(2);
		cell.setCellValue("Designation");
		cell.setCellStyle(style);
		
		cell = row.createCell(3);
		cell.setCellValue("Experience");
		cell.setCellStyle(style);
		
		cell = row.createCell(4);
		cell.setCellValue("Active");
		cell.setCellStyle(style);
	}
	private void writeDataRows() {
		int rowCount = 1;
		
		CellStyle style = workbook.createCellStyle();
		XSSFFont font = workbook.createFont();
		
		font.setFontHeight(10);
		style.setFont(font);
		style.setAlignment(HorizontalAlignment.CENTER);
		style.setBorderBottom(BorderStyle.THIN);
		style.setBorderLeft(BorderStyle.THIN);
		style.setBorderRight(BorderStyle.THIN);
		style.setBorderTop(BorderStyle.THIN);
		
		for(EmployeeTo employee : listEmployees) {
			
			Row row = sheet.createRow(rowCount++);
			
			Cell cell = row.createCell(0);
			cell.setCellValue(employee.getEmp_id());
			cell.setCellStyle(style);
			
			cell = row.createCell(1);
			cell.setCellValue(employee.getName());
			cell.setCellStyle(style);
			
			cell = row.createCell(2);
			cell.setCellValue(employee.getDesignation());
			cell.setCellStyle(style);
			
			cell = row.createCell(3);
			cell.setCellValue(employee.getExperience());
			cell.setCellStyle(style);
			
			cell = row.createCell(4);
			cell.setCellValue(employee.getIsActive());
			cell.setCellStyle(style);
			
		}
		for(int i =0;i<5;i++){
			sheet.autoSizeColumn(i);
		}
	}
	
	public void export(HttpServletResponse response) throws IOException{
		writeHeaderRow();
		writeDataRows();
		ServletOutputStream  outputStream = response.getOutputStream();
		workbook.write(outputStream);
		workbook.close();
		outputStream.close();
	}
}
