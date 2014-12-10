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

package it.tylframework.data.mongo.basics;

import it.tylframework.data.mongo.common.Footprint;
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
@TypeAlias("bas_conversionfactor")
@Data
@RequiredArgsConstructor
public class ConversionFactor extends Footprint {
    @NonNull
    private Unit from;

    @NonNull
    private Unit to;

    @NonNull
    BigDecimal conversion_factor;

    public BigDecimal convert(BigDecimal quantity){
         return quantity.multiply(conversion_factor);
    }

    public BigDecimal reverseConvert(BigDecimal quantity){
        return quantity.divide(conversion_factor);
    }
}