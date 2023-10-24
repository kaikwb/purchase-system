package br.com.fiap;

import br.com.fiap.domain.entity.Product;
import br.com.fiap.domain.entity.QuoteRequest;
import br.com.fiap.domain.entity.User;
import br.com.fiap.domain.repository.QuoteRequestRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("oracle-fiap");

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        QuoteRequestRepository quoteRequestRepository = new QuoteRequestRepository(entityManager);

        QuoteRequest quoteRequest = createQuoteRequest();

        quoteRequestRepository.save(quoteRequest);

        entityManager.close();
    }

    private static QuoteRequest createQuoteRequest() {
        User user = new User("Benedito", "benedito@mail.com.br", "123456");

        Product notebook = new Product("Notebook", "Notebook Dell", 5000.0);
        Product mouse = new Product("Mouse", "Mouse Logitech", 100.0);
        Product cellphone = new Product("Cellphone", "Cellphone Samsung", 2000.0);

        List<Product> products = List.of(notebook, mouse, cellphone);
        Map<Product, Integer> quantities = Map.of(notebook, 2, mouse, 1, cellphone, 1);

        QuoteRequest quoteRequest = new QuoteRequest("PENDING", user, products, quantities);
        return quoteRequest;
    }
}