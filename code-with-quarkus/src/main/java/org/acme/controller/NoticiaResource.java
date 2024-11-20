package org.acme.controller;

import org.acme.model.Noticia;
import org.acme.repository.NoticiaRepository;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;

import java.util.List;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/noticias")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class NoticiaResource {

    @Inject
    NoticiaRepository noticiaRepository;

    @POST
    @Transactional
    public Response cadastrarNoticia(Noticia noticia) {
        noticiaRepository.persist(noticia);
        return Response.status(Response.Status.CREATED).entity(noticia).build();
    }

    @GET
    public List<Noticia> listarNoticiasNaoEnviadas() {
        return noticiaRepository.listarNaoEnviadas();
    }
}
