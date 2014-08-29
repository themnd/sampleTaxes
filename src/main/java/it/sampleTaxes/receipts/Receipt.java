package it.sampleTaxes.receipts;

import static com.google.common.base.Preconditions.checkNotNull;

import java.math.BigDecimal;
import java.util.List;

import com.google.common.collect.Lists;

/**
 * Receipt for an order.
 *
 * @author mnova
 */
public class Receipt {

    private final List<Purchase> purchaseList;
    private BigDecimal totalCost;
    private BigDecimal totalTaxes;

    public Receipt() {
        this.purchaseList = Lists.newArrayList();
        this.totalCost = BigDecimal.valueOf(0);
        this.totalTaxes = BigDecimal.valueOf(0);
    }

    /**
     * Add a purchase to the receipt.
     *
     * @param purchase
     */
    public void addPurchase(final Purchase purchase) {
        this.purchaseList.add(checkNotNull(purchase));
    }

    /**
     * Return the list of all the purchases.
     *
     * @return
     */
    public List<Purchase> getPurchaseList() {
        return purchaseList;
    }

    /**
     * Grand total cost (i.e. product cost + taxes).
     *
     * @return
     */
    public BigDecimal getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(final BigDecimal totalCost) {
        this.totalCost = totalCost;
    }

    /**
     * Total taxes payed.
     *
     * @return
     */
    public BigDecimal getTotalTaxes() {
        return totalTaxes;
    }

    public void setTotalTaxes(final BigDecimal totalTaxes) {
        this.totalTaxes = totalTaxes;
    }
}
