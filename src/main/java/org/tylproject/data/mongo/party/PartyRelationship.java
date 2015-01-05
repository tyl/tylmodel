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
import org.joda.time.DateTime;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.tylproject.data.mongo.basics.FreezeReason;
import org.tylproject.data.mongo.common.Footprint;
import org.tylproject.data.mongo.common.MlText;

// TODO - completare il Javadoc della classe
/**
 * Most of the prperties are self-explanatory.
 *
 * A Relationship must be read in a "direction": left-to-right or right-to-left. The PartyRelationshipType descibes
 * the predicate that can be used reading the relationship in a sense or in the other. In istance:
 * - the ralationship between an employee and a company can be expressed by two predicates "is employed in" and "has as employee";
 * - the relationship between ......
 * The concept of "qualifier" (left_qualifier_role_type, etc.)
 * must be explained.
 *
 * A relationship between two PartyRole is usually due to the PartyRelationshipType and
 * valid in a specific period of time (described by valid_from and valid_true properties). Sometimes it must be
 * further explained or, as we say, "qualified" by one of the following facts:
 * - the PartyRoleType of the RoleType at the right of the relationship has a specific value (in instance: "Consultant" or "Owner")
 * - the PartyRoleType of the RoleType at the left of the relationship has a specific value (in instance: "Vendor" or "Bank")
 * - the Party at the right of the relationship is a specific Party (in instance: "John Doe")
 * - the Party at the left of the relationship is a specific Party (in instance: "Tyl Consulting")
 *
 *
 * Created by mp on 05/01/15.
 */
/*
 TODO - Fare in modo che i set di left e right Party e PartyRole siano condizionati dai constraints
 */
@Document(collection = "par_partyrelationship")
@TypeAlias("party.PartyRelationship")
@Data
@RequiredArgsConstructor
@EqualsAndHashCode(callSuper=true)
public class PartyRelationship extends Footprint {

    /**
     * The status (canceled/not canceled) of the relationship;
     */
    private boolean canceled;

    /**
     * The status (freezed/not freezed) of the relationship;
     */
    private boolean freezed;

    /**
     * The reason why the relationship is freezed;
     */
    private FreezeReason freeze_reason;

    /**
     * The type of the relationship. See the related Document;
     */
    private PartyRelationshipType party_relationship_type;

    /**
     * The PartyRole at the left side of the relationship
     */
    @DBRef(lazy=true)
    private PartyRole left_party_role;

    /**
     * The code of the Party at the left side of the relationship
     */
    private String left_party_code;

    /**
     * The PartyRole at the right side of the relationship
     */
    @DBRef(lazy=true)
    private PartyRole right_party_role;

    /**
     * The code of the Party at the right side of the relationship
     */
    private String right_party_code;

    /**
     * The starting datetime of the relationship
     */
    private DateTime from_date;

    /**
     * The ending datetime of the relationship
     */
    private DateTime thru_date;

    /**
     * The PartyRelationship that further qualifies the relationship
     */
    private PartyRelationshipQualifier partyRelationshipQualifier;

    /**
     * The specific identifier of the relationship
     */
    @Indexed
    private PartyIdentifier identifier_in_relationship;
}
