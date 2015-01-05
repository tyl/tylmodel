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
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by mp on 05/01/15.
 */
@Document(collection = "par_partyrelationshipqualifier")
@TypeAlias("party.PartyRelationshipQualifier")
@Data
@RequiredArgsConstructor
public class PartyRelationshipQualifier {

    @Id
    private String id;

    /**
     * The status (canceled/not canceled) of the relationship qualifier;
     */
    private boolean canceled;

    /**
     * The status (freezed/not freezed) of the relationship qualifier;
     */
    private boolean freezed;

    /**
     * code of the Party that qualifies the relationship if at the left side of the relationship
     */
    private String left_qualifier_party_code;

    /**
     * Party that qualifies the relationship if at the left side of the relationship
     */
    @DBRef(lazy=true)
    private Party left_qualifier_party;

    /**
     * Party code that qualifies the relationship if at the right side of the relationship
     */
    private String right_qualifier_party_code;

    /**
     * Party that qualifies the relationship if at the right side of the relationship
     */
    @DBRef(lazy=true)
    private Party right_qualifier_party;

    /**
     * The PartyRoleType code that qualifies the relationship if at the left side of the relationship
     */
    private String left_qualifier_role_type_code;

    /**
     * The PartyRoleType that qualifies the relationship if at the left side of the relationship
     */
    @DBRef(lazy=true)
    private PartyRoleType left_qualifier_role_type;

    /**
     * The PartyRoleType code that qualifies the relationship if at the right side of the relationship
     */
    private String right_qualifier_role_type_code;

    /**
     * The PartyRoleType that qualifies the relationship if at the riigh side of the relationship
     */
    @DBRef(lazy=true)
    private PartyRoleType right_qualifier_role_type;

    /**
     * The PartyRelationshipTypeCode that further qualifies the relationship
     */
    private String relationship_type_qualifier_code;

    /**
     * The PartyRelationshipType that further qualifies the relationship
     */
    @DBRef(lazy = true)
    private PartyRelationshipType relationship_type_qualifier;
}
