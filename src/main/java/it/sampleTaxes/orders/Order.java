package it.sampleTaxes.orders;

import it.sampleTaxes.products.Product;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.google.common.collect.Lists;

/**
 * Order
 * 28/08/14 on 22:40
 *
 * @author mnova
 */
public class Order {
    
    private final String id;
    private final Date date;
    private final List<ProductOrder> products;

    public Order() {
        
        this.id = UUID.randomUUID().toString();
        this.date = new Date();
        this.products = Lists.newArrayList();

    }

    public String getId() {
        return id;
    }

    public List<ProductOrder> getProducts() {
        return products;
    }

    public void addProduct(final Product product) {
        addProduct(product, 1);
    }

    public void addProduct(final Product product, final int quantity) {
        final ProductOrder productOrder = new ProductOrder(quantity, product);
        products.add(productOrder);
    }
}
