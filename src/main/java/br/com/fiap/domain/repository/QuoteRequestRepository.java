package br.com.fiap.domain.repository;

import br.com.fiap.domain.entity.QuoteRequest;
import jakarta.persistence.EntityManager;

import java.util.List;

public class QuoteRequestRepository {
    private final EntityManager entityManager;

    public QuoteRequestRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void save(QuoteRequest quoteRequest) {
        entityManager.getTransaction().begin();
        entityManager.persist(quoteRequest);
        entityManager.getTransaction().commit();
    }

    public void update(QuoteRequest quoteRequest) {
        entityManager.getTransaction().begin();
        entityManager.merge(quoteRequest);
        entityManager.getTransaction().commit();
    }

    public void delete(QuoteRequest quoteRequest) {
        entityManager.getTransaction().begin();
        entityManager.remove(quoteRequest);
        entityManager.getTransaction().commit();
    }

    public List<QuoteRequest> findAll() {
        return entityManager.createQuery("SELECT q FROM QuoteRequest q", QuoteRequest.class).getResultList();
    }

    public QuoteRequest findById(Long id) {
        return entityManager.find(QuoteRequest.class, id);
    }
}
