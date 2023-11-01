package com.icodeTestAssignment.controllers;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Map;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.icodeTestAssignment.entities.ApiResponse;
import com.icodeTestAssignment.services.ExcelService;
import com.icodeTestAssignment.services.TemplateDataSaver;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/templates/")
public class TemplateController {

	@Autowired
	private ExcelService excelService;

	@Autowired
	TemplateDataSaver templateDataSaver;

	@PostMapping("/generateExcel")
	public ResponseEntity<ByteArrayResource> generateExcelFile(@RequestBody Map<String, String> templateData) {
		Workbook workbook = excelService.generateExcel(templateData);
		ByteArrayResource resource;

		try {
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			workbook.write(outputStream);
			resource = new ByteArrayResource(outputStream.toByteArray());
		} catch (IOException e) {
			return ResponseEntity.status(500).body(null);
		}

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		headers.setContentDispositionFormData("attachment", "data.xlsx");

		return ResponseEntity.ok().headers(headers).body(resource);
	}

	@PostMapping("/uploadExcel")
	public ResponseEntity<ApiResponse> uploadTemplateData(@RequestBody Map<String, String> templateData) {
		System.out.println("upload!!!!!!!!");
		templateDataSaver.save(templateData);
		return ResponseEntity.ok(new ApiResponse("Data Successfully Saved !!"));
	}
}
