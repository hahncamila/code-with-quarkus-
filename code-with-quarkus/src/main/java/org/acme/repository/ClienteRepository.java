package org.acme.repository;

import java.util.List;

import org.acme.model.Cliente;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ClienteRepository implements PanacheRepository<Cliente> {

    public List<Cliente> listarTodos() {
        return listAll();
    }
}
