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
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.tylproject.data.mongo.common.MlText;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by mp on 05/01/15.
 */
@Document(collection = "par_partyrelationshiptype")
@TypeAlias("party.PartyRelationshipType")
@Data
@RequiredArgsConstructor
public class PartyRelationshipType {
    @Id
    private String id;

    @Indexed(unique=true)
    @NonNull
    private String code;

    private MlText name;
    private MlText description;

    @Indexed
    private MlText predicateLeftToRight;

    @Indexed
    private MlText predicateRightToLeft;

    private Set<PartyRoleType> allowableLeftRoles = new HashSet<PartyRoleType>();
    private Set<PartyRoleType> allowableRightRoles = new HashSet<PartyRoleType>();
    private Set<PartyDiscriminator> allowableLeftDiscriminators = new HashSet<PartyDiscriminator>();
    private Set<PartyDiscriminator> allowableRightDiscriminators = new HashSet<PartyDiscriminator>();

}
