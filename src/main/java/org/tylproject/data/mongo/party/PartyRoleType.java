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
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;
import org.tylproject.data.mongo.common.MlText;

/**
 * Created with IntelliJ IDEA.
 * User: marco
 * Date: 04/01/15
 * Time: 21:23
 */
@Document(collection = "par_partyroletype")
@TypeAlias("party.PartyRoleType")
@Data
@RequiredArgsConstructor
public class PartyRoleType {
    @NonNull private String code;
    @NonNull private MlText name;
    private MlText description;
}
