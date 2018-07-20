package com.textiq.service;

import com.textiq.entity.DocumentEntity;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public interface DocumentService {

    DocumentEntity get(int documentId);
    List<DocumentEntity> get(int pageId, int size);
    List<DocumentEntity> find(String query, int pageId, int size);
    void processDocuments() throws IOException, NoSuchAlgorithmException;
}
