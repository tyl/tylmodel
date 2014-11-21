package it.tylframework.data.mongo.config;

import it.tylframework.data.mongo.common.LangKey;
import it.tylframework.data.mongo.common.Signature;

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
