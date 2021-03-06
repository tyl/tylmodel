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
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.tylproject.data.mongo.basics.Country;

import java.lang.reflect.Array;

/**
 * Created by mp on 01/01/15.
 */
@Data
@RequiredArgsConstructor
public class GeographicAddress {
    private GeographicAddressPurpose purpose;
    private String streetName;
    private String additionalStreetName;
    private String blockName;
    private String buildingName;
    private String buildingNumber;
    private String postbox;
    private String floor;
    private String room;
    private String inHouseMail;
    private String department;
    private String markAttention;
    private String markCare;
    private String plotIdentification;
    private String citySubdivision_name;
    private String cityName;
    private String postalZone;
    private String countrySubentity;
    private String countrySubentityCode;
    private String region;
    private String district;
    private String timezoneOffset;
    // TODO - fare campo che diventa un arrey tramite Conversion
    private int pos[] = new int[2];

    @DBRef(lazy = true)
    private Country country;


}
