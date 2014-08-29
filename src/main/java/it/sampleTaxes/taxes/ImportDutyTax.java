package it.sampleTaxes.taxes;

import static com.google.common.base.Preconditions.checkNotNull;

import it.sampleTaxes.products.Product;
import it.sampleTaxes.util.TaxUtil;

import java.math.BigDecimal;

/**
 * Import duty is an additional sales tax applicable on
 * all imported goods at a rate of 5%, with no exemptions
 *
 * @author mnova
 */
public class ImportDutyTax implements Tax {

    @Override
    public String getName() {
        return "import duty";
    }

    @Override
    public BigDecimal apply(final Product product) {

        checkNotNull(product);

        if (product.isImported()) {
            final BigDecimal price = checkNotNull(product.getPrice());
            final BigDecimal tax = price.multiply(BigDecimal.valueOf(0.05));

            return TaxUtil.round(tax);
        } else {

            return BigDecimal.valueOf(0);
        }
    }
}
