package org.acme.service;

import org.acme.model.Cliente;
import org.acme.model.Noticia;
import org.acme.repository.ClienteRepository;
import org.acme.repository.NoticiaRepository;

import io.quarkus.mailer.Mailer;
import io.quarkus.mailer.Mail; 

import java.time.LocalDate;
import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class EmailService {

    @Inject
    Mailer mailer;  

    @Inject
    ClienteRepository clienteRepository;

    @Inject
    NoticiaRepository noticiaRepository;

    private static final String EMAIL_SENDER = "seu-email@gmail.com";

    public void enviarEmails() {
        List<Cliente> clientes = clienteRepository.listarTodos();
        List<Noticia> noticias = noticiaRepository.listarNaoEnviadas();

        for (Cliente cliente : clientes) {
            String corpoEmail = gerarCorpoEmail(cliente, noticias);

            try {
                enviarEmail(cliente.getEmail(), "Notícias do dia!", corpoEmail);
            } catch (Exception e) {
                e.printStackTrace(); 
            }
        }
    }

    private void enviarEmail(String to, String subject, String body) {
        Mail email = Mail.withText(to, subject, body);  
        mailer.send(email);
    }

    private String gerarCorpoEmail(Cliente cliente, List<Noticia> noticias) {
        StringBuilder corpoEmail = new StringBuilder();

        corpoEmail.append("Bom dia ").append(cliente.getNome()).append("!\n\n");

        if (cliente.getNascimento() != null && cliente.getNascimento().getDayOfMonth() == LocalDate.now().getDayOfMonth()
                && cliente.getNascimento().getMonthValue() == LocalDate.now().getMonthValue()) {
            corpoEmail.append("Feliz aniversário!\n\n");
        }

        corpoEmail.append("Aqui estão as notícias de hoje:\n\n");

        for (Noticia noticia : noticias) {
            corpoEmail.append("- ").append(noticia.getTitulo());
            if (noticia.getLink() != null) {
                corpoEmail.append(" (").append(noticia.getLink()).append(")");
            }
            corpoEmail.append("\n").append(noticia.getDescricao()).append("\n\n");
            noticia.setEnviada(true);
        }
        return corpoEmail.toString();
    }
}
