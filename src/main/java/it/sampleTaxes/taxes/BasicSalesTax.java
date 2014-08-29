package it.sampleTaxes.taxes;

import static com.google.common.base.Preconditions.checkNotNull;

import it.sampleTaxes.products.ItemType;
import it.sampleTaxes.products.Product;
import it.sampleTaxes.util.TaxUtil;

import java.math.BigDecimal;

/**
 * Basic sales tax is applicable at a rate of 10% on all goods, except books,
 * food, and medical products that are exempt
 *
 * @author mnova
 */
public class BasicSalesTax implements Tax {

    @Override
    public String getName() {

        return "Basic Sales";
    }

    @Override
    public BigDecimal apply(final Product product) {

        checkNotNull(product);

        final ItemType type = product.getType();
        switch (type) {
            case BOOK:
                // fall through
            case FOOD:
                // fall through
            case MEDICAL:
            {
                return BigDecimal.valueOf(0);
            }

            default:
            {
                final BigDecimal price = checkNotNull(product.getPrice());
                final BigDecimal tax = price.multiply(BigDecimal.valueOf(0.10));
                return TaxUtil.round(tax);
            }
        }
    }

}
