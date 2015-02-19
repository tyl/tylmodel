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
package org.tylproject.data.mongo.common;

import org.tylproject.data.mongo.config.Context;

import java.util.EnumMap;
import java.util.Map;

/**
 * Created by mp on 20/11/14.
 */
public class MlText {

    final Map<LangKey,String> mlt = new EnumMap<LangKey,String>(LangKey.class);
    public MlText() {}
    public String getText(LangKey lang) {
        return mlt.get(lang);
    }
    public void setText(LangKey lang, String text){
        mlt.put(lang,text);
    }
    @Override
    public String toString() {
        return mlt.values().toString();
    }
}

