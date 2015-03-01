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
@Data
@RequiredArgsConstructor
public class TelecomAddress {
    @Id
    private ObjectId id;

    private String countryTelCode;
    private String nationalDirectDialingPrefix;
    private String areaCode;
    private String number;
    private String extension;
    private TelecomPhysicalType physicalType;
    private TelecomAddressPurpose purpose;

    @DBRef(lazy = true)
    private GeographicAddress relatedGeographicAddress;

    public String getFormattedTelecomNumber(){
        return countryTelCode+"("+nationalDirectDialingPrefix+areaCode+")"+number+"-"+extension;
    }
}
