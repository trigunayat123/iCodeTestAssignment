package com.icodeTestAssignment.services;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

@Service
public class MongoDBService {

	private MongoClient mongoClient;
	private MongoDatabase database;
	private MongoCollection<Document> collection;

	public MongoDBService() {
		mongoClient = new MongoClient("localhost", 27017);
		database = mongoClient.getDatabase("icodeTest");
	}

	public void saveToMongoDB(Document document) {
		collection = database.getCollection("templateData");
		collection.insertOne(document);
	}

	public Map<String, String> templateMappingProvider() {
		collection = database.getCollection("templateMappings");
		Map<String, String> templateMappings = new HashMap();

		for (Document document : collection.find()) {
			document.remove("_id");

			for (Map.Entry<String, Object> entry : document.entrySet()) {
				templateMappings.put(entry.getKey(), String.valueOf(entry.getValue()));
			}
		}
		return templateMappings;
	}

	public void closeMongoDBConnection() {
		mongoClient.close();
	}
}
