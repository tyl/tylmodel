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

import org.springframework.data.annotation.Transient;
import org.tylproject.data.mongo.config.Context;
import org.tylproject.data.mongo.config.ThreadSafeContext;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by mp on 20/11/14.
 */
public class MlText {


    transient final Context tylContext;
    final Map<LangKey,String> mlt = new EnumMap<LangKey,String>(LangKey.class);

    @Inject
    public MlText(@Named("tylContext") Context ctx) {
        this.tylContext = ctx;
    }

    public MlText(){
        this.tylContext = new ThreadSafeContext();
    }

    public MlText(String dt){
        this();
        if(!dt.isEmpty()){
            setText(dt);
        }
    }

    @Transient
    public Context getTylContext() {
        return tylContext;
    }

    // return, cascading, the text in current language, or text in default language, or empty string
    public String getText(){
        final Context tylContext = getTylContext();

        String localizedText = mlt.get(tylContext.currentLanguage());

        if(isTextEmpty(localizedText)) {
            localizedText = mlt.get(tylContext.defaultLanguage());
            if (isTextEmpty(localizedText)) {
                return "";
            }
        }

        return localizedText;
    }

    public String invoke() {
        return getText();
    }

    public String getText(LangKey lang) {
        String localizedText = mlt.get(lang);
        if (isTextEmpty(localizedText)) {
            localizedText = mlt.get(tylContext.defaultLanguage());
            if (isTextEmpty(localizedText)) {
                localizedText = "";
            }
        }

        return localizedText;
    }

    private boolean isTextEmpty(String text) {
        return text == null || text.isEmpty();
    }

    public void setText(String text){
        mlt.put(tylContext.currentLanguage(), text);
    }

    public void setText(LangKey lang, String text){
        mlt.put(lang,text);
    }

    @Override
    public String toString() {
        return getText();
    }
}

