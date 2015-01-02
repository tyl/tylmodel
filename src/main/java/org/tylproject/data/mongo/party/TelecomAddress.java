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

/**
 * Created by mp on 01/01/15.
 */
@Document(collection = "par_telecomaddress")
@TypeAlias("par_TelAddress")
@Data
@RequiredArgsConstructor
public class TelecomAddress {
    @Id
    private String id;

    private String country_tel_code;
    private String national_direct_dialing_prefix;
    private String area_code;
    private String number;
    private String extension;
    private TelPhysicalType physical_type;
    private TelecomAddressPurpose purpose;
    @DBRef(lazy = true)
    private GeographicAddress related_geographic_address;
}
