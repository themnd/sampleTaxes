package it.sampleTaxes.processor;

import it.sampleTaxes.orders.Order;
import it.sampleTaxes.receipts.Receipt;

/**
 * Processor.
 *
 * @author mnova
 */
public interface Processor {

    /**
     * Process an order.
     *
     * @param order a not null order.
     * @return a not null Receipt.
     */
    Receipt apply(final Order order);

}
