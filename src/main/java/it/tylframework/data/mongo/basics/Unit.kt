/*
 * Copyright 2012-2013 Tyl Consulting s.a.s.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package it.tylframework.data.mongo.basics

import it.tylframework.data.mongo.common.MlText
import it.tylframework.data.mongo.common.Footprint
import it.tylframework.data.mongo.config.TylContext
import java.util.Date

/**
 * Created by marco on 19/09/14.
 */
data class Unit(
        var name: MlText,
        var description: MlText,
        var systemOfUnits: SystemOfUnits
) :Footprint(TylContext.currentUser, Date() ,TylContext.currentUser,  Date()) {}
