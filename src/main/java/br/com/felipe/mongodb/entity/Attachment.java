package br.com.felipe.mongodb.entity;

public record Attachment(String name, String contentType, long length, byte[] image) {
}
