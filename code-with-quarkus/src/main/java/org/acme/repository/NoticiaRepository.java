package org.acme.repository;

import java.util.List;

import org.acme.model.Noticia;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class NoticiaRepository implements PanacheRepository<Noticia> {

    public List<Noticia> listarNaoEnviadas() {
        return list("enviada = false");
    }
}

