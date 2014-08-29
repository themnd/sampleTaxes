package it.sampleTaxes.processor;

import static com.google.common.base.Preconditions.checkNotNull;

import it.sampleTaxes.orders.Order;
import it.sampleTaxes.orders.ProductOrder;
import it.sampleTaxes.products.Product;
import it.sampleTaxes.receipts.Purchase;
import it.sampleTaxes.receipts.Receipt;
import it.sampleTaxes.taxes.Tax;

import java.math.BigDecimal;
import java.util.List;

import com.google.common.collect.Lists;

/**
 * Process an order.
 *
 * @author mnova
 */
public class OrderProcessor implements Processor {
    
    private final List<Tax> taxes;

    public OrderProcessor() {
        this.taxes = Lists.newArrayList();
    }

    /**
     * Add a Tax processor.
     *
     * @param tax
     */
    public void addTax(final Tax tax) {
        this.taxes.add(tax);
    }

    @Override
    public Receipt apply(final Order order) {

        checkNotNull(order);

        BigDecimal total = BigDecimal.valueOf(0);
        BigDecimal taxes = BigDecimal.valueOf(0);

        final Receipt receipt = new Receipt();

        for (final ProductOrder productOrder : order.getProducts()) {
            final Product product = productOrder.getProduct();
            final BigDecimal productTax = calculateTaxes(product);
            final Purchase purchase = new Purchase(productOrder, productTax);
            receipt.addPurchase(purchase);

            final BigDecimal cost = product.getPrice().multiply(BigDecimal.valueOf(productOrder.getQuantity()));
            final BigDecimal salesTax = productTax.multiply(BigDecimal.valueOf(productOrder.getQuantity()));
            total = total.add(cost).add(salesTax).setScale(2, BigDecimal.ROUND_HALF_UP);
            taxes = taxes.add(salesTax).setScale(2, BigDecimal.ROUND_HALF_UP);
        }

        receipt.setTotalCost(total);
        receipt.setTotalTaxes(taxes);

        return receipt;
    }

    private BigDecimal calculateTaxes(final Product product) {
        BigDecimal productTax = BigDecimal.valueOf(0);
        for (final Tax tax : taxes) {
            productTax = productTax.add(tax.apply(product));
        }
        return productTax;
    }
}
