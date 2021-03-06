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

package org.tylproject.data.mongo.basics;

import lombok.EqualsAndHashCode;
import org.tylproject.data.mongo.common.Footprint;
import org.tylproject.data.mongo.common.Quantity;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

/**
 * Created with IntelliJ IDEA.
 * User: marco
 * Date: 08/12/14
 * Time: 14:55
 */
@Document(collection="bas_conversionfactor")
@TypeAlias("bas_ConversionFactor")
@Data
@RequiredArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ConversionFactor extends Footprint {
    @NonNull
    private Unit from;

    @NonNull
    private Unit to;

    @NonNull
    BigDecimal conversionFactor;

    /**
     * Convert a quantity from a Unit to another Unit multiplying the value of the quantity by the conversion_factor and assigning
     * the "to" unit of measure to the returning quantity
     *
     * @param quantity
     * @return the converted Quantity
     */

    public Quantity convert(Quantity quantity){
         return quantity.multiply(conversionFactor,to.getCode());
    }

    /**
     * Convert a quantity from a Unit to another Unit dividing the value of the quantity by the conversion_factor and assigning
     * the "from" unit of measure to the returning quantity
     *
     * @param quantity
     * @return the converted Quantity
     */
    public Quantity reverseConvert(Quantity quantity){
        return quantity.divide(conversionFactor, from.getCode());
    }
}
