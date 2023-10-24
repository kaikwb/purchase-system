package br.com.fiap.domain.repository;

import br.com.fiap.domain.entity.Product;
import jakarta.persistence.EntityManager;

import java.util.List;

public class ProductRepository {
    private final EntityManager entityManager;

    public ProductRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void save(Product product) {
        entityManager.getTransaction().begin();
        entityManager.persist(product);
        entityManager.getTransaction().commit();
    }

    public void update(Product product) {
        entityManager.getTransaction().begin();
        entityManager.merge(product);
        entityManager.getTransaction().commit();
    }

    public void delete(Product product) {
        entityManager.getTransaction().begin();
        entityManager.remove(product);
        entityManager.getTransaction().commit();
    }

    public List<Product> findAll() {
        return entityManager.createQuery("SELECT p FROM Product p", Product.class).getResultList();
    }

    public Product findById(Long id) {
        return entityManager.find(Product.class, id);
    }

    public Product findByName(String name) {
        return entityManager.createQuery("SELECT p FROM Product p WHERE p.name = :name", Product.class)
            .setParameter("name", name)
            .getSingleResult();
    }
}
