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

package org.tylproject.data.mongo.party;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.joda.time.DateTime;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.tylproject.data.mongo.basics.FreezeReason;
import org.tylproject.data.mongo.common.Footprint;
import org.tylproject.data.mongo.common.MlText;

/**
 * Created with IntelliJ IDEA.
 * User: marco
 * Date: 04/01/15
 * Time: 19:43
 */
@Document(collection = "par_partyrole")
@TypeAlias("party.PartyRole")
@Data
@RequiredArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class PartyRole extends Footprint {
    /**
     * The status (canceled/not canceled) of the party role;
     */
    private boolean canceled=false;

    /**
     * The status (freezed/not freezed) of the party role;
     */
    private boolean frozen=false;

    /**
     * The reason why the PartyRole is freezed;
     */
    private FreezeReason freezeReason;

    /**
     * The Party that has this Role
     */
    @NonNull @DBRef(lazy=true)
    private Party party;

    /**
     * The Type of the PartyRole
     */
    @NonNull private PartyRoleType partyRoleType;

    /**
     * The name of the PartyRole. In instance "customer", "vendor", etc.
     */
    private MlText name;

    /**
     * The starting date of validity of this Role for the Party
     */
    private DateTime fromDate;

    /**
     * The ending date of validity of this Role for the Party
     */
    private DateTime thruDate;

}
