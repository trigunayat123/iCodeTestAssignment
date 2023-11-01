package com.icodeTestAssignment.services;

import java.util.Map;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TemplateDataSaver {

	@Autowired
	MongoDBService mongoDbService;

	public void save(Map<String, String> templateData) {
		if (templateData.size() == 0)
			throw new NullPointerException("Please define some fields and their values");
		for (String field : templateData.keySet()) {
			if (templateData.get(field) == null || templateData.get(field).trim().isEmpty())
				throw new NullPointerException("All fields must have valid values. Please provide values for all fields.");
		}
		Document templateDocument = new Document();
		for (String field : templateData.keySet())
			templateDocument.append(field, templateData.get(field));
		mongoDbService.saveToMongoDB(templateDocument);
	}
}
