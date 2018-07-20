package com.textiq.resource;

import com.textiq.service.DocumentService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

@Path("/processDocuments")
public class DocumentProcessResource {

    private DocumentService documentService;
    public DocumentProcessResource(DocumentService documentService) {
        this.documentService = documentService;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response get() throws IOException, NoSuchAlgorithmException {
        documentService.processDocuments();
        return Response.ok().build();
    }
}
