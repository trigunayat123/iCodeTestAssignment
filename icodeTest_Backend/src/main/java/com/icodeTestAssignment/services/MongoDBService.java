package com.icodeTestAssignment.services;

import org.bson.Document;
import org.springframework.stereotype.Service;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

@Service
public class MongoDBService {

	private MongoClient mongoClient;
	private MongoDatabase database;
	private MongoCollection<Document> collection;

	public MongoDBService() {
		mongoClient = new MongoClient("localhost", 27017);
		database = mongoClient.getDatabase("icodeTest");
		collection = database.getCollection("templateData");
	}

	public void saveToMongoDB(Document document) {
		collection.insertOne(document);
	}

	public void closeMongoDBConnection() {
		mongoClient.close();
	}
}
