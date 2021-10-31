package br.com.felipe.mongodb.entity;

import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "books")
public record Book(@BsonId ObjectId id, String title, String detail, Double price, Author author, Attachment attachment) {

}

