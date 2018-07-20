package com.textiq.resource;

import com.textiq.entity.DocumentEntity;
import com.textiq.service.DocumentService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/articles")
public class DocumentResource {

    private DocumentService documentService;
    public DocumentResource(DocumentService documentService) {
        this.documentService = documentService;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response get(@DefaultValue("0") @QueryParam("page") int page, @DefaultValue("10") @QueryParam("size") int size) {
        List<DocumentEntity> articles = documentService.get(page,size);
        return Response.ok(articles).build();
    }

    @Path("{key}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response get(@PathParam("key") int documentId) {
        DocumentEntity article = documentService.get(documentId);
        return Response.ok(article).build();
    }

    @Path("find")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response get(@DefaultValue("") @QueryParam("q") String query,@DefaultValue("0") @QueryParam("page") int page, @DefaultValue("10") @QueryParam("size") int size) {
        List<DocumentEntity> articles = documentService.find(query,page,size);
        return Response.ok(articles).build();
    }
}
