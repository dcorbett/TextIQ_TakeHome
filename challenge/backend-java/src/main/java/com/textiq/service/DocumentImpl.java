package com.textiq.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.textiq.entity.DocumentEntity;
import com.textiq.entity.DocumentProcessEntity;
import com.textiq.repository.DocumentRepository;
import org.skife.jdbi.v2.DBI;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.List;

public class DocumentImpl implements DocumentService {
    DocumentRepository repo;

    public DocumentImpl(DBI dbi) {
        repo = new DocumentRepository(dbi);
    }

    public DocumentEntity get(int documentId){
        return repo.retrieve(documentId);
    }
    public List<DocumentEntity> get(int page, int size){
        return repo.retrieve(page, size);
    }
    public List<DocumentEntity> find(String query, int page, int size){
        return repo.find(query,page, size);
    }

    public void processDocuments() throws IOException, NoSuchAlgorithmException {
        File folder = new File("./CORPUS");
        File[] listOfFiles = folder.listFiles();
        ObjectMapper mapper = new ObjectMapper();

        if(!folder.exists()) return;
        System.out.format("Starting processing of %d files", listOfFiles.length);

        for (int i = 0; i < listOfFiles.length; i++) {
            if (!listOfFiles[i].isFile()) continue;
                String fileName = listOfFiles[i].getName();
            try (
                    InputStream is = Files.newInputStream(Paths.get("./CORPUS/"+fileName));
                )
                {
                    List<DocumentProcessEntity> docs = mapper.readValue(is, TypeFactory.defaultInstance().constructCollectionType(List.class, DocumentProcessEntity.class));
                    for(DocumentEntity doc : docs){
                        repo.create(doc);
                    }
                    System.out.format("Upload complete for file: %s Count: %d", fileName, docs.size());
                }
        }


    }
}
