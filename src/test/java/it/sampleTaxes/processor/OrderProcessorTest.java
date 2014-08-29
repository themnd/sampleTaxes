package it.sampleTaxes.processor;

import it.sampleTaxes.orders.Order;
import it.sampleTaxes.outout.ReceiptDumper;
import it.sampleTaxes.products.ItemType;
import it.sampleTaxes.products.Product;
import it.sampleTaxes.receipts.Purchase;
import it.sampleTaxes.receipts.Receipt;
import it.sampleTaxes.taxes.BasicSalesTax;
import it.sampleTaxes.taxes.ImportDutyTax;

import java.math.BigDecimal;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class OrderProcessorTest {

    private OrderProcessor orderProcessor;
    
    @Before
    public void before()
    {
        orderProcessor = new OrderProcessor();
        orderProcessor.addTax(new BasicSalesTax());
        orderProcessor.addTax(new ImportDutyTax());
    }
    
    @Test
    public void testInput1() {

        final Product p1 = new Product("book", ItemType.BOOK, bd("12.49"));
        final Product p2 = new Product("music CD", ItemType.OTHER, bd("14.99"));
        final Product p3 = new Product("chocolate bar", ItemType.FOOD, bd("0.85"));

        final Order order = new Order();
        order.addProduct(p1);
        order.addProduct(p2);
        order.addProduct(p3);

        final Receipt receipt = orderProcessor.apply(order);

        new ReceiptDumper(receipt).dump();

        Assert.assertNotNull(receipt);
        Assert.assertNotNull(receipt.getPurchaseList());
        Assert.assertEquals(3, receipt.getPurchaseList().size());

        {
            final Purchase pu = receipt.getPurchaseList().get(0);
            Assert.assertEquals(bd("12.49"), pu.getTotal());
        }
        {
            final Purchase pu = receipt.getPurchaseList().get(1);
            Assert.assertEquals(bd("16.49"), pu.getTotal());
        }
        {
            final Purchase pu = receipt.getPurchaseList().get(2);
            Assert.assertEquals(bd("0.85"), pu.getTotal());
        }

        Assert.assertEquals(bd("1.50"), receipt.getTotalTaxes());
        Assert.assertEquals(bd("29.83"), receipt.getTotalCost());
    }

    @Test
    public void testInput2() {

        final Product p1 = new Product("box of chocolates", ItemType.FOOD, bd("10.00"), true);
        final Product p2 = new Product("bottle of perfume", ItemType.OTHER, bd("47.50"), true);

        final Order order = new Order();
        order.addProduct(p1);
        order.addProduct(p2);

        final Receipt receipt = orderProcessor.apply(order);

        new ReceiptDumper(receipt).dump();

        Assert.assertNotNull(receipt);
        Assert.assertNotNull(receipt.getPurchaseList());
        Assert.assertEquals(2, receipt.getPurchaseList().size());

        {
            final Purchase pu = receipt.getPurchaseList().get(0);
            Assert.assertEquals(bd("10.50"), pu.getTotal());
        }
        {
            final Purchase pu = receipt.getPurchaseList().get(1);
            Assert.assertEquals(bd("54.65"), pu.getTotal());
        }

        Assert.assertEquals(bd("7.65"), receipt.getTotalTaxes());
        Assert.assertEquals(bd("65.15"), receipt.getTotalCost());
    }

    @Test
    public void testInput3() {

        final Product p1 = new Product("bottle of perfume", ItemType.OTHER, bd("27.99"), true);
        final Product p2 = new Product("bottle of perfume", ItemType.OTHER, bd("18.99"));
        final Product p3 = new Product("packet of headache pills", ItemType.MEDICAL, bd("9.75"));
        final Product p4 = new Product("box of chocolates", ItemType.FOOD, bd("11.25"), true);

        final Order order = new Order();
        order.addProduct(p1);
        order.addProduct(p2);
        order.addProduct(p3);
        order.addProduct(p4);

        final Receipt receipt = orderProcessor.apply(order);

        new ReceiptDumper(receipt).dump();

        Assert.assertNotNull(receipt);
        Assert.assertNotNull(receipt.getPurchaseList());
        Assert.assertEquals(4, receipt.getPurchaseList().size());

        {
            final Purchase pu = receipt.getPurchaseList().get(0);
            Assert.assertEquals(bd("32.19"), pu.getTotal());
        }
        {
            final Purchase pu = receipt.getPurchaseList().get(1);
            Assert.assertEquals(bd("20.89"), pu.getTotal());
        }
        {
            final Purchase pu = receipt.getPurchaseList().get(2);
            Assert.assertEquals(bd("9.75"), pu.getTotal());
        }
        {
            final Purchase pu = receipt.getPurchaseList().get(3);
            Assert.assertEquals(bd("11.85"), pu.getTotal());
        }

        Assert.assertEquals(bd("6.70"), receipt.getTotalTaxes());
        Assert.assertEquals(bd("74.68"), receipt.getTotalCost());
    }

    private BigDecimal bd(final String value) {
        return new BigDecimal(value);
    }

}
