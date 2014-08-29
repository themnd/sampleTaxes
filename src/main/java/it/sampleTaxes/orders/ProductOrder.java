package it.sampleTaxes.orders;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import it.sampleTaxes.products.Product;

/**
 * A product order.
 *
 * @author mnova
 */
public class ProductOrder {

    private final int quantity;
    private final Product product;

    /**
     * Constructor.
     *
     * @param quantity the number of products, must be positive.
     * @param product a not null product.
     */
    public ProductOrder(final int quantity, final Product product) {

        checkArgument(quantity > 0);
        checkNotNull(product);

        this.quantity = quantity;
        this.product = product;
    }

    /**
     * Return the quantity.
     *
     * @return a positive number.
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Return the product.
     *
     * @return a not null product.
     */
    public Product getProduct() {
        return product;
    }
}
