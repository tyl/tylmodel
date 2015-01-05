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
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.joda.time.DateTime;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.tylproject.data.mongo.common.MlText;
import org.tylproject.data.mongo.common.SignatureReason;

import java.util.Date;

/**
 * Created by mp on 05/01/15.
 */
@Document(collection = "par_partysignature")
@TypeAlias("party.PartySignature")
@Data
@RequiredArgsConstructor
public class PartySignature {
    /**
     * The code of the party who signed the transaction or the modification of a transaction
     */
    @NonNull
    private String signer_party_code;

    /**
     * DateTime of the signature
     */
    private DateTime whenSigned;

    /**
     * Comment of the signature
     */
    private MlText comment;

    /**
     * Reason of the signature
     */
    private SignatureReason reason;

    /**
     * Ref to the Party who signed the transaction
     */
    @DBRef(lazy=true)
    private Party signer_party;
}
