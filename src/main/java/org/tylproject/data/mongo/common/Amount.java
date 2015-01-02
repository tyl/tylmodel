/*
 * Copyright 2015 Tyl Consulting s.a.s.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.tylproject.data.mongo.common;

/**
 * Created with IntelliJ IDEA.
 * User: marco
 * Date: 11/12/14
 * Time: 21:46
 */

import org.tylproject.data.mongo.common.Exceptions.BadCurrencyException;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Currency;
import java.util.Locale;


/**
 *
 * @author marco
 */
public class Amount implements Serializable {

    private static final long serialVersionUID = -6843954475340594882L;
    private long amount;
    private Currency currency;
    /**
     * instance of NumberFormat
     *
     * @see NumberFormat
     */
    public static NumberFormat nf = NumberFormat.getInstance();

    static {
        if (nf instanceof DecimalFormat) {
            DecimalFormat format = (DecimalFormat) nf;
            format.applyPattern("#,##0.00");
        }
    }

    /**
     * standard constructor
     */
    public Amount() {
    }
    /**
     * the local currency (JVM established)
     */
    public static Currency LOCAL_CURRENCY = Currency.getInstance(Locale.getDefault());
    private static final int[] cents = new int[]{1, 10, 100, 1000};

    /**
     * Creates a new amount of the provided amount and currency passed as
     * String.
     * <p/>
     * Example:
     * <code>new Amount( 12, "EUR" )</code>
     * </p>
     *
     * @param amount the amount of the Amount.
     * @param currency the currency
     * @throws org.tylproject.data.mongo.common.Exceptions.BadCurrencyException the provided currency was not recognized
     */
    public Amount(long amount, String currency) throws BadCurrencyException {
        try {
            this.currency = Currency.getInstance(currency);
        } catch (Exception e) {
            throw new BadCurrencyException("Currency " + currency + " is not valid");
        }
        this.amount = amount * centFactor();
    }

    /**
     * Creates a new amount of the provided amount and currency.
     * <p/>
     * Example:
     * <code>new Amount( 12, Currency.getInstance("EUR") )</code>
     * </p>
     *
     * @param amount the amount of the Amount.
     * @param currency Currency Amount is to be measured in.
     */
    public Amount(long amount, Currency currency) {
        this.currency = currency;
        this.amount = amount * centFactor();
    }

    /**
     * Creates a new Amount of the provided amount and currency.
     * <p/>
     * User defined rounding mode is used.
     *
     * @param amount the provided amount
     * @param currency the provided currency
     * @param roundingMode the RoundingMode used
     */
    public Amount(BigDecimal amount, Currency currency, RoundingMode roundingMode) {
        this.currency = currency;
        amount = amount.movePointRight(currency.getDefaultFractionDigits());
        amount = amount.setScale(0, roundingMode);
        this.amount = amount.longValue();

    }

    /**
     * Creates a new amount of the provided amount and currency.
     * <p/>
     * Example:
     * <code>new Amount( 1.48, Currency.getInstance("EUR") )</code>
     * </p>
     *
     * @param amount Amount of Amount.
     * @param currency Currency Amount is to be measured in.
     */
    public Amount(double amount, Currency currency) {
        this.currency = currency;
        this.amount = Math.round(amount * centFactor());

    }

    /**
     * Creates a new amount of the provided amount and currency.
     * <p/>
     * Example:
     * <code>new Amount( 1.48, "EUR" )</code>
     * </p>
     *
     * @param amount Amount of Amount.
     * @param currency Currency Amount is to be measured in.
     * @throws BadCurrencyException the provided currency was not recognized
     */
    public Amount(double amount, String currency) throws BadCurrencyException {
        try {
            this.currency = Currency.getInstance(currency);
        } catch (Exception e) {
            throw new BadCurrencyException("Currency " + currency + " is not valid");
        }
        this.currency = Currency.getInstance(currency);
        this.amount = Math.round(amount * centFactor());

    }

    /**
     * gets the amount as long. It comes multiplied by 100, 1000, etc, depending
     * on the number of decimals used by the currency
     *
     * @return the amount
     */
    public long getAmount() {
        return amount;
    }

    /**
     * sets the amount
     *
     * @param amount the provided amount
     */
    public void setAmount(long amount) {
        this.amount = amount;
    }

    /**
     * gets the currency
     *
     * @return the currency
     */
    public Currency getCurrency() {
        return currency;
    }

    /**
     * sets the Currency
     *
     * @param currency the provided currency
     */
    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    /**
     * sets the Currency as String
     *
     * @param currency the provided currency
     */
    public void setCurrency(String currency) {
        this.currency = Currency.getInstance(currency);
    }

    /**
     * gets the value of the Amount as a BigDecimal
     *
     * @return the value of the Amount
     */
    public BigDecimal amount() {
        return BigDecimal.valueOf(amount, currency.getDefaultFractionDigits());
    }

    /**
     * Simple addition ensuring matched Currency.
     *
     * @param other the addend
     * @return a new Amount object.
     */
    public Amount add(Amount other) {
        assertSameCurrencyAs(other);
        return newAmount(amount + other.amount);

    }

    /**
     * Simple subtraction ensuring matched Currency.
     * <p/>
     *
     * @param other the amount to subtract
     * @return the new Amount object.
     */
    public Amount subtract(Amount other) {
        assertSameCurrencyAs(other);
        return newAmount(amount - other.amount);

    }

    /**
     * Amount multiplication with default rounding mode.
     * <p/>
     * Equivalent of
     * <code>multiply( amount, BigDecimal.ROUND_HALF_EVEN )</code>
     *
     * @param amount the multiplier
     * @return the new Amount result of the multiplication
     */
    public Amount multiply(double amount) {
        return multiply(new BigDecimal(amount));

    }

    /**
     * Amount multiplication with default rounding mode. Equivilent of
     * <code>multiply( amount, BigDecimal.ROUND_HALF_EVEN )</code>
     *
     * @param amount the multiplier
     * @return the new Amount result of the multiplication
     */
    public Amount multiply(BigDecimal amount) {
        return multiply(amount, RoundingMode.HALF_EVEN);

    }

    /**
     * Amount multiplication with user specified rounding mode.
     *
     * @param amount the multiplier
     * @param roundingMode the rounding applied
     * @return the new Amount result of the multiplication
     */
    public Amount multiply(BigDecimal amount, RoundingMode roundingMode) {
        return new Amount(amount().multiply(amount), currency, roundingMode);

    }

    /**
     * Amount division with default rounding mode.
     * <p/>
     * Equivilent of
     * <code>divide amount, BigDecimal.ROUND_HALF_EVEN )</code>
     *
     * @param divisor the divisor
     * @return the new Amount result of the division
     */
    public Amount divide(double divisor) {
        return divide(new BigDecimal(divisor));

    }

    /**
     * Amount division with default rounding mode.
     * <p/>
     * Equivilent of
     * <code>divide amount, BigDecimal.ROUND_HALF_EVEN )</code>
     *
     * @param divisor the divisor
     * @return the new Amount result of the division
     */
    public Amount divide(BigDecimal divisor) {
        return divide(divisor, RoundingMode.HALF_EVEN);

    }

    /**
     * Amount division with user specified rounding mode.
     *
     * @param divisor the divisor
     * @param roundingMode the rounding applied
     * @return the new Amount result of the division
     */
    public Amount divide(BigDecimal divisor, RoundingMode roundingMode) {
        return new Amount(amount().divide(divisor, roundingMode), currency, roundingMode);
    }

    /**
     * Allocates amount according to provided ratios.
     *
     * @param ratios the divisors
     * @return the array of quotes
     */
    public Amount[] allocate(long[] ratios) {
        long total = 0;
        for (int i = 0; i < ratios.length; i++) {
            total += ratios[i];
        }
        long remainder = amount;
        Amount[] results = new Amount[ratios.length];
        for (int i = 0; i < results.length; i++) {
            results[i] = newAmount(amount * ratios[i] / total);
            remainder -= results[i].amount;
        }
        if (remainder > 0) {
            for (int i = 0; i < remainder; i++) {
                results[i].amount++;
            }
        }
        if (remainder < 0) {
            for (int i = 0; i > remainder; i--) {
                results[i].amount--;
            }
        }
        return results;
    }

    /**
     * Allocates amount "evenly' into n amounts.
     *
     * @param n ratio to be applied for the even allocation
     * @return the array of quotes
     */
    public Amount[] allocate(int n) {
        Amount[] results = new Amount[n];
        Amount lowResult = newAmount(amount / n);
        Amount highResult = newAmount(lowResult.amount + (amount >= 0 ? 1 : -1));

        int remainder = Math.abs((int) amount % n);
        for (int i = 0; i < remainder; i++) {
            results[i] = highResult;
        }

        for (int i = remainder; i < n; i++) {
            results[i] = lowResult;
        }

        return results;
    }

    /**
     * the hash code of the Amount
     *
     * @return the hash code
     */
    public int hash_code() {
        return (int) (amount ^ (amount >>> 32));

    }

    /**
     * The equal implementation
     *
     * @param other
     */
    public boolean equalTo(Amount other) {
        return currency.equals(other.currency) && (amount == other.amount);

    }

    /**
     * Amount instances must have same currency and amount to be equal.
     * Comparing a Amount with an Object you must assure that the Object is a
     * type of Amount.
     *
     * @param other
     */
    public boolean equalTo(Object other) {
        return (other instanceof Amount) && equals(other);

    }

    /**
     * Comparison of Amount objects used by Comparable interface. This method
     * allows Amount to be sorted. Boolean int -1 if less than, 1 if greater
     * than and 0 if equal to other
     *
     * @param other
     */
    public int compareTo(Object other) {
        return compareTo((Amount) other);
    }

    /**
     * Comparison of Amount objects. Boolean int -1 if less than, 1 if greater
     * than and 0 if equal to other
     *
     * @param other
     */
    public int compareTo(Amount other) {
        assertSameCurrencyAs(other);
        if (amount < other.amount) {
            return -1;
        } else if (amount == other.amount) {
            return 0;
        } else {
            return 1;
        }

    }

    /**
     * Convenience implementation of greater than function.
     *
     * @param other the Amount to be confronted with
     */
    public boolean greaterThen(Amount other) {
        return (compareTo(other) > 0);

    }

    /**
     * Convenience implementation of less than function.
     *
     * @param other the Amount to be confronted with
     */
    public boolean lessThen(Amount other) {
        return (compareTo(other) < 0);

    }

    /**
     * converts the Amount in to a String
     */
    @Override
    public String toString() {
        nf.setCurrency(currency);
        nf.setMinimumFractionDigits(currency.getDefaultFractionDigits());
        nf.setMaximumFractionDigits(currency.getDefaultFractionDigits());

        return currency.toString() + " " + nf.format(amount().doubleValue());

    }

    /**
     * Convenience function produces amount in the 'local currency'.
     *
     * @param amount
     */
    public static Amount local(double amount) {
        return new Amount(amount, LOCAL_CURRENCY);
    }

    /**
     * Returns Amount object represented by provided string.
     *
     * @param str
     * @throws ParseException
     */
    public static Amount valueOf(String str) {
        Currency currency;
        Number number = null;

        currency = Currency.getInstance(str.substring(str.length() - 3));

        nf.setCurrency(currency);
        nf.setMinimumFractionDigits(currency.getDefaultFractionDigits());
        nf.setMaximumFractionDigits(currency.getDefaultFractionDigits());

        try {
            number = nf.parse((str.substring(0, str.length() - 4)));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new Amount(number.doubleValue(), currency);

    }

    /**
     * @param arg
     */
    private void assertSameCurrencyAs(Amount arg) {
        assert (arg != null);
        assert (currency.equals(arg.currency));
    }

    private int centFactor() {
        return cents[currency.getDefaultFractionDigits()];
    }

    /**
     * Used to return a new Amount in the same currency as this one with the
     * assigned value
     *
     * @param amount the assigned value
     */
    private Amount newAmount(long amount) {
        Amount newamount = new Amount();
        newamount.setAmount(amount);
        newamount.setCurrency(this.currency);
        return newamount;
    }
}

