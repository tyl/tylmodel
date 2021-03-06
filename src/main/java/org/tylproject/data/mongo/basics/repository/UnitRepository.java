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

package org.tylproject.data.mongo.basics.repository;

import org.bson.types.ObjectId;
import org.tylproject.data.mongo.basics.Unit;
import org.tylproject.data.mongo.common.MlText;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by marco on 24/08/14.
 */
public interface UnitRepository extends MongoRepository<Unit,ObjectId>{
    public Unit findByCode(String code);
    public Unit findByName(MlText name);
}
