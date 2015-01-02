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
package org.tylproject.data.mongo.config;

import org.tylproject.data.mongo.common.LangKey;
import org.tylproject.data.mongo.common.Signature;

/**
 * Created by mp on 20/11/14.
 */
public class TylContext {
    public static LangKey defaultLang = LangKey.it;

    private static ThreadLocal<LangKey> currentLang = new ThreadLocal<LangKey>();
    private static ThreadLocal<Signature> currentUsr = new ThreadLocal<Signature>();

    static {
        currentUsr.set(Signature.EmptySignature);
        currentLang.set(defaultLang);
    }

    public static Signature currentUser(){
        return currentUsr.get();
    }

    public static void setCurrentUser(Signature signature){
        currentUsr.set(signature);
    }

    public static LangKey currentLanguage(){
        return currentLang.get();
    }

    public static void setCurrentLanguage(LangKey lang){
        currentLang.set(lang);
    }
}
