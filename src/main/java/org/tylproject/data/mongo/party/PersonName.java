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
import lombok.RequiredArgsConstructor;
import org.joda.time.DateTime;
import org.tylproject.data.mongo.common.MlText;

/**
 * Created with IntelliJ IDEA.
 * User: marco
 * Date: 04/01/15
 * Time: 20:11
 */
@Data
@RequiredArgsConstructor
public class PersonName {
    private String honorific;
    private String givenName;
    private String middleName;
    private String familyName;
    private String preferredName;
    private String motherMaidenName;
    private String suffix;
    private DateTime validFrom;
    private DateTime validThru;
    private MlText comment;
    private MlText nameUse;
}
