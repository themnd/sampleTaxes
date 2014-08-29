package it.sampleTaxes.products;

import static com.google.common.base.Preconditions.checkNotNull;

import java.math.BigDecimal;

/**
 * A simple product.
 *
 * @author mnova
 */
public class Product {

    private final String name;
    private final ItemType type;
    private final BigDecimal price;
    private final boolean imported;

    /**
     * Create a "NOT IMPORTED" product.
     *
     * @param name
     * @param type
     * @param price
     */
    public Product(final String name, final ItemType type, final BigDecimal price) {

        this.name = checkNotNull(name);
        this.type = checkNotNull(type);
        this.price = checkNotNull(price);
        this.imported = false;

    }

    /**
     * Can specify if the product is imported.
     *
     * @param name
     * @param type
     * @param price
     * @param imported
     */
    public Product(final String name, final ItemType type, final BigDecimal price, final boolean imported) {

        this.name = checkNotNull(name);
        this.type = checkNotNull(type);
        this.price = checkNotNull(price);
        this.imported = imported;

    }

    public String getName() {
        return name;
    }

    public ItemType getType() {
        return type;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public boolean isImported() {
        return imported;
    }
}
