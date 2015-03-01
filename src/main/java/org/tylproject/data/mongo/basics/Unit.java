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
package org.tylproject.data.mongo.basics;

import org.bson.types.ObjectId;
import org.tylproject.data.mongo.common.MlText;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by mp on 20/11/14.
 */
@Document(collection="bas_unit")
@TypeAlias("bas_unit")
@Data
@RequiredArgsConstructor
public class Unit {
    @Id
    private ObjectId id;

    @NonNull String code;

    @NonNull  MlText name;
    MlText description;

    @NonNull  @DBRef(lazy = true) SystemOfUnits systemOfUnits;
}

