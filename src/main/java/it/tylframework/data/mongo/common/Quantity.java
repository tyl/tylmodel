/*
 *  Licensed to the Apache Software Foundation (ASF) under one
 *  * or more contributor license agreements.  See the NOTICE file
 *  * distributed with this work for additional information
 *  * regarding copyright ownership.  The ASF licenses this file
 *  * to you under the Apache License, Version 2.0 (the
 *  * "License"); you may not use this file except in compliance
 *  * with the License.  You may obtain a copy of the License at
 *  * <p/>
 *  * http://www.apache.org/licenses/LICENSE-2.0
 *  * <p/>
 *  * Unless required by applicable law or agreed to in writing,
 *  * software distributed under the License is distributed on an
 *  * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  * KIND, either express or implied.  See the License for the
 *  * specific language governing permissions and limitations
 *  * under the License.
 */

package it.tylframework.data.mongo.common;

/*
 * Quantity <p/> User: marco Date: 5-ago-2006 Time: 17.47.16 <p/> Copyright
 * 2005,2006,2007 Marco Pancotti - TYL-project.org Licensed under the Apache
 * License, Version 2.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at <p/>
 * http://www.apache.org/licenses/LICENSE-2.0 <p/> Unless required by applicable
 * law or agreed to in writing, software distributed under the License is
 * distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.math.RoundingMode;

/**
 * Class for the management of the Quantity values, defined through a
 * <I>quantity</I> and an <I>unit of measure.</I> To avoid rounding problems,
 * the quantity is managed with a BigDecimal. The unit of measure is a normal
 * String, but the application should set this value only with identificators of
 * the Unit class (see TylBasics). As TylCore is a stand-alone package, the unit
 * of measure does not depend from any other package, but the suggested
 * normalization gives the possibility to calculate a conversion factor using
 * the appropriate methods (see TylBasics).
 */
public class Quantity implements Serializable {

    /**
     * ﻿ *
     * ﻿
     */
    private BigDecimal quantity;
    private String unitOfMeasure;

    /**
     * the standard constructor
     */
    public Quantity() {
    }

    /**
     * The quantity BigDecimal is set with a MathContext object having a
     * precision setting matching the IEEE 754R Decimal128 format, 16 digits,
     * and a rounding mode of HALF_EVEN, the IEEE 754R default.
     *
     * @param val the value as String
     * @param unitOfMeasure the unit of measure
     */
    public Quantity(String val, String unitOfMeasure) {
        this.quantity = new BigDecimal(val, MathContext.DECIMAL128);
        this.unitOfMeasure = unitOfMeasure;
    }

    /**
     * The quantity BigDecimal can be set with any MathContext value.
     *
     * @param val the quantity as String
     * @param mathContext the MaxContext desired
     * @param unitOfMeasure the unit of measure
     * @see MathContext
     */
    public Quantity(String val, MathContext mathContext, String unitOfMeasure) {
        this.quantity = new BigDecimal(val, mathContext);
        this.unitOfMeasure = unitOfMeasure;
    }

    /**
     * the quantity BigDecimal uses a MathContext defined through the precision
     * and roundingMode parameters
     *
     * @param val the quantity as String
     * @param precision the precision of the MathContext
     * @param roundingMode the roundingMode of the MathContext
     * @param unitOfMeasure the unit of measure
     */
    public Quantity(String val, int precision, RoundingMode roundingMode, String unitOfMeasure) {
        this.quantity = new BigDecimal(val, new MathContext(precision, roundingMode));
        this.unitOfMeasure = unitOfMeasure;
    }

    /**
     * The quantity BigDecimal is set with a MathContext object having a
     * precision setting matching the IEEE 754R Decimal128 format, 16 digits,
     * and a rounding mode of HALF_EVEN, the IEEE 754R default.
     *
     * @param val the value as BigInteger
     * @param unitOfMeasure the unit of measure
     */
    public Quantity(BigInteger val, String unitOfMeasure) {
        this.quantity = new BigDecimal(val, MathContext.DECIMAL128);
        this.unitOfMeasure = unitOfMeasure;
    }

    /**
     * The quantity BigInteger can be set with any MathContext value.
     *
     * @param val the quantity as BigInteger
     * @param mathContext the MaxContext desired
     * @param unitOfMeasure the unit of measure
     * @see MathContext
     */
    public Quantity(BigInteger val, MathContext mathContext, String unitOfMeasure) {
        this.quantity = new BigDecimal(val, mathContext);
        this.unitOfMeasure = unitOfMeasure;
    }

    /**
     * the quantity BigDecimal uses a MathContext defined through the precision
     * and roundingMode parameters
     *
     * @param val the quantity as BigInteger
     * @param precision the precision of the MathContext
     * @param roundingMode the roundingMode of the MathContext
     * @param unitOfMeasure the unit of measure
     */
    public Quantity(BigInteger val, int precision, RoundingMode roundingMode, String unitOfMeasure) {
        this.quantity = new BigDecimal(val, new MathContext(precision, roundingMode));
        this.unitOfMeasure = unitOfMeasure;
    }

    /**
     * The quantity BigDecimal is set directly
     *
     * @param val the value as BigDecimal
     * @param unitOfMeasure the unit of measure
     */
    public Quantity(BigDecimal val, String unitOfMeasure) {
        this.quantity = val;
        this.unitOfMeasure = unitOfMeasure;
    }

    /**
     * The quantity is set using a BigInteger and a scale. with a MathContext
     * object having a precision setting matching the IEEE 754R Decimal128
     * format, 16 digits, and a rounding mode of HALF_EVEN, the IEEE 754R
     * default.
     */
    public Quantity(BigInteger unscaledval, int scale, String unitOfMeasure) {
        this.quantity = new BigDecimal(unscaledval, scale, MathContext.DECIMAL128);
        this.unitOfMeasure = unitOfMeasure;
    }

    /**
     * The quantity is set using a BigInteger and can be set with any
     * MathContext value.
     */
    public Quantity(BigInteger unscaledval, int scale, MathContext mathContext, String unitOfMeasure) {
        this.quantity = new BigDecimal(unscaledval, scale, mathContext);
        this.unitOfMeasure = unitOfMeasure;
    }

    /**
     * The quantity is set using a BigInteger and a scale uses a MathContext
     * defined through the precision and roundingMode parameters
     */
    public Quantity(BigInteger unscaledval, int scale, int precision, RoundingMode roundingMode, String unitOfMeasure) {
        this.quantity = new BigDecimal(unscaledval, scale, new MathContext(precision, roundingMode));
        this.unitOfMeasure = unitOfMeasure;
    }

    /**
     * The quantity is set using a double with a MathContext object having a
     * precision setting matching the IEEE 754R Decimal128 format, 16 digits,
     * and a rounding mode of HALF_EVEN, the IEEE 754R default.
     */
    public Quantity(double val, String unitOfMeasure) {
        this.quantity = new BigDecimal(val, MathContext.DECIMAL128);
        this.unitOfMeasure = unitOfMeasure;
    }

    /**
     * The quantity is set using a double and can be set with any MathContext
     * value.
     */
    public Quantity(double val, MathContext mathContext, String unitOfMeasure) {
        this.quantity = new BigDecimal(val, mathContext);
        this.unitOfMeasure = unitOfMeasure;
    }

    /**
     * The quantity is set using a double and uses a MathContext defined through
     * the precision and roundingMode parameters
     */
    public Quantity(double val, int precision, RoundingMode roundingMode, String unitOfMeasure) {
        this.quantity = new BigDecimal(val, new MathContext(precision, roundingMode));
        this.unitOfMeasure = unitOfMeasure;
    }

    /**
     * return a new Quantity with a value set via the quantity parameter, a
     * scale of 10 and a FLOOR RoundingMode
     */
    public Quantity newQuantity(BigDecimal qta) {
        qta.setScale(10, RoundingMode.FLOOR);
        return new Quantity(qta, unitOfMeasure);
    }

    /**
     * Check if the unit of measure is equal to the unit of measure of another
     * Quantity
     */
    private void assertSameMetricAs(Quantity arg) {
        assert (arg != null);
        assert (unitOfMeasure.equals(arg.getUnitOfMeasure()));
    }

    /**
     * Returns the unit of measure of the Quantity If the basic Tyl module is
     * implemented, a Unit tale is managed, and a validation can be done about
     * the consistency of the unit of measure used for Quantities instances
     */
    public String getUnitOfMeasure() {
        return unitOfMeasure;
    }

    /**
     * Sets the unit of measure of the Quantity
     */
    public void setUnitOfMeasure(String unit) {
        unitOfMeasure = unit;
    }

    /**
     * Returns the BigDecimal representing the quantity
     */
    public java.math.BigDecimal getQuantity() {
        return quantity;
    }

    /**
     * Set the BigDecimal representing the quantity
     */
    public void setQuantity(BigDecimal qta) {
        quantity = qta;
    }

    /**
     * Add two quantities
     */
    public Quantity add(Quantity quantity) {
        assertSameMetricAs(quantity);
        return new Quantity(this.getQuantity().add(quantity.getQuantity()), unitOfMeasure);
    }

    /**
     * Subtract two quantities
     */
    public Quantity subtract(Quantity quantity) {
        assertSameMetricAs(quantity);
        return new Quantity(this.getQuantity().subtract(quantity.getQuantity()), unitOfMeasure);

    }

    /**
     * Multiply a Quantity
     */
    public Quantity multiply(BigDecimal multiplier) {
        return new Quantity(quantity.multiply(multiplier), unitOfMeasure);
    }

    /**
     * Multiply a Quantity with a specific MathContext
     */
    public Quantity multiply(BigDecimal multiplier, MathContext mathContext) {
        return new Quantity(quantity.multiply(multiplier, mathContext), unitOfMeasure);
    }

    /**
     * Multiply a Quantity with a specific precision and RoundingMode
     */
    public Quantity multiply(BigDecimal multiplier, int precision, RoundingMode roundingMode) {
        return new Quantity(quantity.multiply(multiplier, new MathContext(precision, roundingMode)), unitOfMeasure);
    }

    /**
     * Multiply a Quantity by another Quantity
     */
    public Quantity multiply(Quantity multiplier, String unit) {
        return new Quantity(quantity.multiply(multiplier.getQuantity()), unit);
    }

    /**
     * Divide a Quantity
     */
    public Quantity divide(BigDecimal divisor) {
        return new Quantity(quantity.divide(divisor), unitOfMeasure);
    }

    /**
     * Divide a Quantity with a specific MathContext
     */
    public Quantity divide(BigDecimal divisor, MathContext mathContext) {
        return new Quantity(quantity.divide(divisor, mathContext), unitOfMeasure);
    }

    /**
     * Divide a Quantity with a specific precision and RoundingMode
     */
    public Quantity divide(BigDecimal divisor, int precision, RoundingMode roundingMode) {
        return new Quantity(quantity.divide(divisor, new MathContext(precision, roundingMode)), unitOfMeasure);
    }

    /**
     * Divide a Quantity by another Quantity
     */
    public Quantity divide(Quantity divisor, String unit) {
        return new Quantity(quantity.divide(divisor.getQuantity()), unit);
    }

    /**
     * Test if two Quantity are equals
     */
    @Override
    public boolean equals(Object otherquantity) {
        if (otherquantity instanceof Quantity) {
            Quantity quantity = (Quantity) otherquantity;
            return (this.getQuantity().equals(quantity.getQuantity()) && this.getUnitOfMeasure().equals(quantity.getUnitOfMeasure()));
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + (this.quantity != null ? this.quantity.hashCode() : 0);
        hash = 29 * hash + (this.unitOfMeasure != null ? this.unitOfMeasure.hashCode() : 0);
        return hash;
    }

    /**
     * Test if two Quantity are equals
     */
    public boolean equals(Quantity otherquantity) {
        return (this.getQuantity().equals(otherquantity.getQuantity()) && this.getUnitOfMeasure().equals(otherquantity.getUnitOfMeasure()));
    }

    /**
     * Test if a Quantity is greater of another Quantity (with the same unit of
     * measure)
     */
    public boolean greaterThen(Quantity otherquantity) {
        return (this.getQuantity().compareTo(otherquantity.getQuantity()) > 0 && this.getUnitOfMeasure().equals(otherquantity.getUnitOfMeasure()));
    }

    /**
     * Test if a Quantity is greater or equal of another Quantity (with the same
     * unit of measure)
     */
    public boolean greaterEqualThen(Quantity otherquantity) {
        return (this.getQuantity().compareTo(otherquantity.getQuantity()) >= 0 && this.getUnitOfMeasure().equals(otherquantity.getUnitOfMeasure()));
    }

    /**
     * Round a Quantity with a specific precision
     */
    public Quantity round(int precision) {
        return new Quantity(this.quantity.round(new MathContext(precision, RoundingMode.HALF_EVEN)), this.unitOfMeasure);
    }

    /**
     * Round a Quantity with specific precision and RoundingMode
     */
    public Quantity round(int precision, RoundingMode roundingMode) {
        return new Quantity(this.quantity.round(new MathContext(precision, roundingMode)), this.unitOfMeasure);
    }

    /**
     * Test if a Quantity is smaller of another Quantity (with the same unit of
     * measure)
     */
    public boolean lessThen(Quantity otherquantity) {
        return (this.getQuantity().compareTo(otherquantity.getQuantity()) < 0 && this.getUnitOfMeasure().equals(otherquantity.getUnitOfMeasure()));
    }

    /**
     * Test if a Quantity is greater or equal of another Quantity (with the same
     * unit of measure)
     */
    public boolean lessEqualThen(Quantity otherquantity) {
        return (this.getQuantity().compareTo(otherquantity.getQuantity()) <= 0 && this.getUnitOfMeasure().equals(otherquantity.getUnitOfMeasure()));
    }

    /**
     * Convert a Quantity to a String
     */
    @Override
    public String toString() {
        return unitOfMeasure + " " + quantity.toString();
    }

    /**
     * Allocates quantity according to provided ratios.
     *
     * @param ratios the divisors
     * @return the array of quotes
     */
    public Quantity[] allocate(long[] ratios) {
        long total = 0;
        for (int i = 0; i < ratios.length; i++) {
            total += ratios[i];
        }
        BigDecimal remainder = quantity;
        Quantity[] results = new Quantity[ratios.length];
        for (int i = 0; i < results.length; i++) {
            results[i] = newQuantity(quantity.multiply(new BigDecimal(ratios[i])).divide(new BigDecimal(total)));
            remainder = remainder.subtract(results[i].getQuantity());
        }
        if ((remainder.compareTo(new BigDecimal(0))) > 0) {
            for (int i = 0; i < remainder.intValue(); i++) {
                results[i].getQuantity().add(new BigDecimal(1));
            }
        }
        if (remainder.compareTo(new BigDecimal(0)) < 0) {
            for (int i = 0; i > remainder.intValue(); i--) {
                results[i].getQuantity().subtract(new BigDecimal(1));
            }
        }
        return results;
    }

    /**
     * Allocates a quantity "evenly' into n amounts.
     *
     * @param n ratio to be applied for the even allocation
     * @return the array of quotes
     */
    public Quantity[] allocate(int n, int scale) {
        Quantity[] results = new Quantity[n];
        Quantity lowResult = newQuantity(quantity.divide(new BigDecimal(n), scale, RoundingMode.HALF_EVEN));
        BigDecimal tot = new BigDecimal("0");
        for (int i = 0; i < n - 1; i++) {
            results[i] = lowResult;
            tot = tot.add(lowResult.getQuantity());
        }
        results[n - 1] = new Quantity(this.quantity.subtract(tot), unitOfMeasure);
        return results;
    }

    /**
     * Apply the compareTo method to the BigDecimal quantity ﻿
     */
    public int compareTo(Quantity other) {
        assertSameMetricAs(other);
        return this.quantity.compareTo(other.getQuantity());
    }
}




