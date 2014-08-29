package it.sampleTaxes.util;

import java.math.BigDecimal;

/**
 * The rounding rules for sales tax are that
 * for a tax rate of n%, a shelf price of p contains (np/100 rounded
 * up to the nearest 0.05) amount of sales tax.
 *
 * @author mnova
 */
public abstract class TaxUtil {

    public static final BigDecimal CONSTANT = BigDecimal.valueOf(20);

    public static BigDecimal round(final BigDecimal value) {

        // make sure we have only two decimal digits.

        BigDecimal v = value.setScale(2, BigDecimal.ROUND_HALF_EVEN);

        // now we want to apply the scale to nearest 0.05
        // we will do "v * 20" if we have some decimal
        // it means the number is not rounded to 5 so we have
        // to eliminate the decimal digits again and round
        // to the nearest.
        // we will do "v * 100" to eliminate the decimal digits
        // then "v / 5" to see how many "5" we have, if we have
        // some decimal it means the number is not rounded to 5
        // so we have to eliminate the decimal digits again
        // and round to the nearest. At this point we have the
        // correct number of "5" so we can do "v * 5" again
        // and "v / 100" to get the rounded number.
        // The equations above can be simplified by noting that
        // "v * 100 / 5 = v * 20" and "v * 5 / 100 = v / 20"
        // in this way we will skip two operations.

        // so "v * 20".
        v = v.multiply(CONSTANT);

        // make sure there are no decimals.
        // round up as per given rule.
        v = v.setScale(0, BigDecimal.ROUND_UP);

        // convert back to the same scale as before.
        v = v.divide(CONSTANT);

        // we should have the same amount of decimals as before.
        // we will apply a scale just in case.
        v = v.setScale(2, BigDecimal.ROUND_HALF_EVEN);

        return v;

    }

}
