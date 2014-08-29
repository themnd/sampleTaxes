package it.sampleTaxes.orders;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import it.sampleTaxes.products.Product;

/**
 * ProductOrder
 * 28/08/14 on 23:12
 *
 * @author mnova
 */
public class ProductOrder {

    private final int quantity;
    private final Product product;

    public ProductOrder(final int quantity, final Product product) {

        checkArgument(quantity > 0);
        checkNotNull(product);

        this.quantity = quantity;
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public Product getProduct() {
        return product;
    }
}
