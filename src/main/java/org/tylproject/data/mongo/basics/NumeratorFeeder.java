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
package org.tylproject.data.mongo.basics;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.joda.time.DateTime;

/**
 * Created by mp on 20/11/14.
 */
@Data
@RequiredArgsConstructor
public class NumeratorFeeder{
    boolean freezed=false;
    @NonNull DateTime starting_date;
    @NonNull int starting_number=1;
    @NonNull DateTime ending_date;
    int warning_number=1000;
    int increment_by=1;
    int last_number_used=1;
    Boolean output_as_string=true;
    String output_format="%0d";
}
