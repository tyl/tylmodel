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

import org.tylproject.data.mongo.config.TylContext;

import java.util.HashMap;

/**
 * Created by mp on 20/11/14.
 */
public class MlText {
    HashMap<LangKey,String> mlt = new HashMap<LangKey,String>();

    public MlText(){}

    public MlText(String dt){
        if(!dt.isEmpty()){
            set(dt);
        }
    }

    // return, cascading, the text in current language, or text in default language, or void string
    public String get(){
        if(mlt.get(TylContext.currentLanguage()) ==null || mlt.get(TylContext.currentLanguage()).isEmpty())
            if(mlt.get(TylContext.defaultLang) == null || mlt.get(TylContext.defaultLang).isEmpty())
                return("");
            else
                return(mlt.get(TylContext.defaultLang));
        else
            return(mlt.get(TylContext.currentLanguage()));
    }

    public String invoke() {
        return get();
    }

    public String get(LangKey lang)
    {
        if (mlt.get(lang)==null)
            return get();
        else
            return mlt.get(lang);
    }

    public void set(String text){
        mlt.put(TylContext.currentLanguage(), text);
    }

    public void set(LangKey lang, String text){
        mlt.put(lang,text);
    }
}

