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
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.tylproject.data.mongo.basics.FreezeReason;
import org.tylproject.data.mongo.common.Footprint;

import java.util.ArrayList;

/**
 * Created by mp on 01/01/15.
 */
@Document(collection = "par_party")
@TypeAlias("par_Party")
@Data
@RequiredArgsConstructor
/*
@CompoundIndexes({
        @CompoundIndex(name = "party_identifier", def = "{'identifier_type': 1, 'identifier': 1}")
})

*/
public class Party extends Footprint{
    private Boolean freezed=false;

    private FreezeReason freeze_reason;
    private boolean canceled=false;

    @Indexed
    private String code;

    @Indexed
    private String short_name;

    private ArrayList<GeographicAddress> geografic_address=new ArrayList<GeographicAddress>();
    private ArrayList<TelecomAddress> telecom_address=new ArrayList<TelecomAddress>();
    private ArrayList<WebAddress> web_address=new ArrayList<WebAddress>();
    private ArrayList<EmailAddress> email_address=new ArrayList<EmailAddress>();

    @Indexed
    private ArrayList<PartyIdentifier> party_identifier=new ArrayList<PartyIdentifier>();
}
