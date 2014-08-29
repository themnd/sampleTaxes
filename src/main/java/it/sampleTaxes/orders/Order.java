package it.sampleTaxes.orders;

import it.sampleTaxes.products.Product;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.google.common.collect.Lists;

/**
 * Contains a list of products orders.
 *
 * @author mnova
 */
public class Order {

    /**
     * an unique id.
     */
    private final String id;

    /**
     * Date (and time) of the order.
     */
    private final Date date;

    /**
     * List of products.
     */
    private final List<ProductOrder> products;

    /**
     * Constructor
     */
    public Order() {
        
        this.id = UUID.randomUUID().toString();
        this.date = new Date();
        this.products = Lists.newArrayList();

    }

    /**
     * The unique id.
     *
     * @return a not null String.
     */
    public String getId() {
        return id;
    }

    /**
     * The list of products.
     *
     * @return a not null list.
     */
    public List<ProductOrder> getProducts() {
        return products;
    }

    /**
     * Add a new product to the list with a quantity of 1.
     *
     * @param product a not null product.
     */
    public void addProduct(final Product product) {
        addProduct(product, 1);
    }

    /**
     * Add a new product to the list.
     *
     * @param product a not null product.
     * @param quantity a quantity > 0.
     */
    public void addProduct(final Product product, final int quantity) {
        final ProductOrder productOrder = new ProductOrder(quantity, product);
        products.add(productOrder);
    }
}
