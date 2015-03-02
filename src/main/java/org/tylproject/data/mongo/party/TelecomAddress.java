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

package org.tylproject.data.mongo.party;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by mp on 01/01/15.
 */
//TODO Gestire come da libro una stringa il cui ritorno sia strutturato (getFormattedTelecomAddress)

/**
 * A number that can contact a telephone, a mobile phone, a fax, a pager or other telephonic device.
 * The International Telecommunication Union (www.itu.int) provides standards for TelecomAssresses. Each address is
 * made up of the following parts:
 * +cc (p)aaa nnnnnnn ext. xxx
 * cc = country code
 * p = National Direct Dialing prefix (NDD)
 * aaa = area code
 * nnnnnnn = number
 * xxx = extension
 */
@Data
@RequiredArgsConstructor
public class TelecomAddress {
    @Id
    private ObjectId id;

    /**
     * The number to dial a particulr country
     */
    private String countryTelCode;

    /**
     * The prefix to use within a country between different cities or areas. Usually it is dropped when
     * dialing from outside the country, but not always (i.e. Italy). In thes cases omit the
     * nationalDirectDialingPrefix an put it at the beginning of  the area code, that is always used,
     * even from abroad
     */
    private String nationalDirectDialingPrefix;

    /**
     * The code for an area or a city. It includes the nationalDirectDialingPrefix when not omitted
     * iin case of call from abroad (i.e. calling Italy)
     */
    private String areaCode;

    /**
     * The teleephone number. Length defined by national standards
     */
    private String number;

    /**
     * the extension accessible via the number
     */
    private String extension;

    /**
     * Indicates if it is a fixed phone, a mobile, a fax, a pager or any other type of device
     */
    private TelecomPhysicalType physicalType;

    /**
     * Indicates the purpose of the number. It can can be Business, Home, Personal, etc.
     */
    private TelecomAddressPurpose purpose;

    /**
     * The Goographic address related to the telecom address, if required
     */
    @DBRef(lazy = true)
    private GeographicAddress relatedGeographicAddress;

    public String getFormattedTelecomNumber(){
        return "+"+countryTelCode+" ("+nationalDirectDialingPrefix+")"+areaCode+" "+number+"-"+extension;
    }
}
