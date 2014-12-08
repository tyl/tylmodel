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

package it.tylframework.data.mongo;

import it.tylframework.data.mongo.basics.Unit;

import java.math.BigDecimal;

/**
 * Created with IntelliJ IDEA.
 * User: marco
 * Date: 08/12/14
 * Time: 18:21
 */
public interface UnitTylRepository {
    public BigDecimal  convert(Unit from, Unit to, BigDecimal quantity);
}
