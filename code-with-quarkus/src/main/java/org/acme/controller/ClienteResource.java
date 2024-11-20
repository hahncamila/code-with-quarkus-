package org.acme.controller;

import org.acme.model.Cliente;
import org.acme.repository.ClienteRepository;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;

import java.util.List;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/clientes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ClienteResource {

    @Inject
    ClienteRepository clienteRepository;

    @POST
    @Transactional
    public Response cadastrarCliente(@Valid Cliente cliente) {
        clienteRepository.persist(cliente);
        return Response.status(Response.Status.CREATED).entity(cliente).build();
    }

    @GET
    public List<Cliente> listarClientes() {
        return clienteRepository.listarTodos();
    }
}
