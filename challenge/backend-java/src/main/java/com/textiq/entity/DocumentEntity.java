package com.textiq.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class DocumentEntity {
    public DocumentEntity() {}

    public DocumentEntity(int id, String title, String body, String[] tokens) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.tokens = tokens;
    }

    public DocumentEntity(int id, String title, String body) {
        this.id = id;
        this.title = title;
        this.body = body;
    }

    @JsonProperty("id")
    private int id;

    @JsonProperty("title")
    private String title;

    @JsonProperty("body")
    private String body;

    private String[] tokens;

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    @JsonIgnore
    public String[] getTokens() {
        return tokens;
    }

}
