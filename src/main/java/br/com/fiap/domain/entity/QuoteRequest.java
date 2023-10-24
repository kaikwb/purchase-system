package br.com.fiap.domain.entity;

import jakarta.persistence.*;

import java.util.List;
import java.util.Map;

@Entity
@Table(name = "quote_requests")
public class QuoteRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cotations_seq")
    @SequenceGenerator(name = "cotations_seq", sequenceName = "cotations_seq")
    @Column(name = "id")
    private Long id;

    @Column(name = "status", nullable = false)
    private String status;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(
        name = "user_requester_id",
        referencedColumnName = "id",
        foreignKey = @ForeignKey(name = "fk_cotations_user_requester"),
        nullable = false
    )
    private User userRequester;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
        name = "quote_requests_products",
        joinColumns = @JoinColumn(name = "quote_request_id"),
        inverseJoinColumns = @JoinColumn(name = "product_id"),
        foreignKey = @ForeignKey(name = "fk_quote_requests_products_quote_request"),
        inverseForeignKey = @ForeignKey(name = "fk_quote_requests_products_product")
    )
    private List<Product> products;

    @ElementCollection
    @CollectionTable(
        name = "quote_requests_product_quantities",
        joinColumns = @JoinColumn(name = "quote_request_id")
    )
    @MapKeyJoinColumn(name = "product_id")
    @Column(name = "quantity")
    private Map<Product, Integer> productQuantity;

    public QuoteRequest() {
    }

    public QuoteRequest(String status, User userRequester, List<Product> products, Map<Product, Integer> productQuantity) {
        this(null, status, userRequester, products, productQuantity);
    }

    public QuoteRequest(Long id, String status, User userRequester, List<Product> products, Map<Product, Integer> productQuantity) {
        this.id = id;
        this.status = status;
        this.userRequester = userRequester;
        this.products = products;
        this.productQuantity = productQuantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public User getUserRequester() {
        return userRequester;
    }

    public void setUserRequester(User userRequester) {
        this.userRequester = userRequester;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
