package com.icodeTestAssignment.services;

import org.apache.poi.ss.usermodel.*;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ExcelService {
	public Workbook generateExcel(Map<String, String> templateData) {
		Workbook workbook = new XSSFWorkbook();
		Sheet sheet = workbook.createSheet("Data");
		int rowNum = 0;
		for (Map.Entry<String, String> entry : templateData.entrySet()) {
			Row row = sheet.createRow(rowNum++);
			Cell keyCell = row.createCell(0);
			keyCell.setCellValue(entry.getKey());
			Cell valueCell = row.createCell(1);
			valueCell.setCellValue(entry.getValue());
		}

		return workbook;
	}
}
