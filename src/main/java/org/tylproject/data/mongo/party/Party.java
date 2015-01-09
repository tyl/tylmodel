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
import lombok.EqualsAndHashCode;
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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by mp on 01/01/15.
 */
@Document(collection = "par_party")
@TypeAlias("party.Party")
@Data
@RequiredArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class Party extends Footprint{
    private Boolean frozen=false;

    private FreezeReason freezeReason;
    private boolean canceled=false;

    @Indexed(sparse = true)
    private String code;

    @Indexed(unique = true, sparse = true)
    private String shortName;

    private List<GeographicAddress> geographicAddress=new ArrayList<GeographicAddress>();
    private List<TelecomAddress> telecomAddress=new ArrayList<TelecomAddress>();
    private List<WebAddress> webAddress=new ArrayList<WebAddress>();
    private List<EmailAddress> emailAddress=new ArrayList<EmailAddress>();

    @Indexed(sparse = true)
    private Set<PartyIdentifier> partyIdentifier=new HashSet<PartyIdentifier>();
    private PartyDiscriminator discriminator;
    private Person person;
    private Organization organization;
}
