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
		Row row = sheet.createRow(0);
		int col=0;
		for (Map.Entry<String, String> entry : templateData.entrySet()) {
			Cell keyCell = row.createCell(col++);
			keyCell.setCellValue(entry.getKey());
		}

		return workbook;
	}
}
