package com.textiq.repository;

import com.textiq.entity.DocumentEntity;
import org.skife.jdbi.v2.DBI;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DocumentRepository {
    private final DBI dbi;
    public DocumentRepository(DBI dbi) {
        this.dbi = dbi;
    }

    public void create(DocumentEntity document){
        try{
            this.dbi.useHandle((handle) -> {
                if(handle.select("SELECT id FROM document where id = ?", document.getId()).size() == 0){
                    handle.execute(
                            "INSERT INTO document(id, title, body) VALUES (?,?,?)",
                            document.getId(),
                            document.getTitle(),
                            document.getBody()
                    );
                }
            });
        } catch(Exception e){
            //We expect double values
        }

        for (String token: document.getTokens()) {
            try{
                this.dbi.useHandle((handle) -> {

                    if(handle.select("SELECT id FROM token where value = ?", token).size() == 0) {
                        handle.execute(
                                "INSERT INTO token(value) VALUES (?)",
                                token
                        );
                    }

                    handle.execute("INSERT INTO token_document(token_id, document_id) VALUES ((SELECT id FROM token WHERE value = ?),?)",
                            token,
                            document.getId()
                    );
                });
            } catch (Exception e){
                //Its quicker to fail and continue
            }
        }
    }

    public List<DocumentEntity> retrieve(int page, int size){
        return this.dbi.withHandle((handle) -> {
            List<DocumentEntity> documentEntities = new ArrayList<>();
            List<Map<String, Object>> rs = handle.select("SELECT id, title, body FROM document LIMIT ? OFFSET ?",size, (size * page));

            for(Map<String, Object> map : rs){
                Integer id = (Integer) map.get("id");
                String title = (String) map.get("title");
                String body = (String) map.get("body");
                //body = truncate(body, 100);
                documentEntities.add(new DocumentEntity(id,title,body));
            }
            return documentEntities;
        });
    }

    public DocumentEntity retrieve(int documentId){
        return this.dbi.withHandle((handle) -> {
            List<Map<String, Object>> rs = handle.select("SELECT id, title, body FROM document WHERE id = ?", documentId);
            if(rs.isEmpty()) return null;
            Integer id = (Integer) rs.get(0).get("id");
            String title = (String) rs.get(0).get("title");
            String body = (String) rs.get(0).get("body");
            return new DocumentEntity(id, title,body);
        });
    }

    public List<DocumentEntity> find(String query, int page, int size){
        if(query.isEmpty()) return retrieve(page, size);
        return this.dbi.withHandle((handle) -> {
            List<DocumentEntity> documentEntities = new ArrayList<>();
            List<Map<String, Object>> rs = handle.select(
                    "SELECT id, title, body FROM document " +
                            "WHERE id IN (" +
                                "SELECT document_id FROM token_document " +
                                "WHERE token_id IN(" +
                                    "SELECT id FROM token " +
                                    "WHERE value = ?" +
                                ")" +
                            ") " +
                            "LIMIT ? OFFSET ?",
                    query,
                    size,
                    (size * page)
            );

            for(Map<String, Object> map : rs){
                Integer id = (Integer) map.get("id");
                String title = (String) map.get("title");
                String body = (String) map.get("body");
                documentEntities.add(new DocumentEntity(id,title,body));
            }
            return documentEntities;
        });
    }

    private String truncate(String str, int size){
        if(str == null) return "";
        if (str.length() < size) return str;
        return str.substring(0, size);
    }
}
