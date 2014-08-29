package it.sampleTaxes.outout;

import static com.google.common.base.Preconditions.checkNotNull;

import it.sampleTaxes.orders.ProductOrder;
import it.sampleTaxes.products.Product;
import it.sampleTaxes.receipts.Purchase;
import it.sampleTaxes.receipts.Receipt;

import java.util.List;

/**
 * Dumper of the receipt, it will dump t
 *
 * @author mnova
 */
public class ReceiptDumper {

    private final Receipt receipt;

    public ReceiptDumper(final Receipt receipt) {
        this.receipt = checkNotNull(receipt);
    }

    public void dump() {

        final List<Purchase> purchaseList = receipt.getPurchaseList();

        final StringBuilder sb = new StringBuilder();
        for (final Purchase purchase : purchaseList) {

            final ProductOrder productOrder = purchase.getProduct();
            final Product product = productOrder.getProduct();

            sb.append(productOrder.getQuantity());
            sb.append(" ");
            if (product.isImported()) {
                sb.append("imported ");
            }
            sb.append(product.getName());
            sb.append(" [");
            sb.append(product.getType().name());
            sb.append("] ");
            sb.append(purchase.getTotal());

            sb.append("\n");
        }
        sb.append("Sales taxes: ");
        sb.append(receipt.getTotalTaxes());
        sb.append("\n");
        sb.append("Total cost: ");
        sb.append(receipt.getTotalCost());
        sb.append("\n");

        System.out.println(sb.toString());
    }

}
