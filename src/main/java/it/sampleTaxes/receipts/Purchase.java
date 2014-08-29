package it.sampleTaxes.receipts;

import it.sampleTaxes.orders.ProductOrder;

import java.math.BigDecimal;

/**
 * Represent a product purchase.
 *
 * @author mnova
 */
public class Purchase {

    private final ProductOrder product;
    private final BigDecimal taxes;
    private final BigDecimal cost;
    private final BigDecimal total;

    public Purchase(final ProductOrder product, final BigDecimal taxes) {
        this.product = product;
        this.taxes = taxes;
        this.cost = product.getProduct().getPrice().multiply(BigDecimal.valueOf(product.getQuantity()));
        this.total = this.cost.add(this.taxes);
    }

    /**
     * The product purchased.
     *
     * @return
     */
    public ProductOrder getProduct() {
        return product;
    }

    /**
     * Tax payed for the product.
     *
     * @return
     */
    public BigDecimal getTaxes() {
        return taxes;
    }

    /**
     * Net cost of the product.
     *
     * @return
     */
    public BigDecimal getCost() {
        return cost;
    }

    /**
     * Grand total (net + taxes).
     *
     * @return
     */
    public BigDecimal getTotal() {
        return total;
    }
}
