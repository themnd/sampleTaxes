package it.sampleTaxes.taxes;

import it.sampleTaxes.products.Product;

import java.math.BigDecimal;

/**
 * Tax
 *
 * @author mnova
 */
public interface Tax {

    /**
     * Name of the tax.
     *
     * @return a not null String.
     */
    String getName();

    /**
     * Apply the tax of the good.
     *
     * @param product a not null product.
     *
     * @return the tax value.
     */
    BigDecimal apply(final Product product);

}
