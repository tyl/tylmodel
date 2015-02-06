package org.tylproject.data.mongo.config;

import org.tylproject.data.mongo.common.LangKey;
import org.tylproject.data.mongo.common.Signature;

import javax.inject.Named;

/**
 * Created by evacchi on 04/02/15.
 */
@Named("tylContext")
public class ThreadSafeContext implements Context {

    ThreadLocal<State> instance = new ThreadLocal<State>() {
        @Override
        protected State initialValue() {
            return new State();
        }
    };

    private static class State {
        protected Signature currentUsr;
        protected LangKey currentLang = TylContext.defaultLang;
    }

    public Signature currentUser(){
        return instance.get().currentUsr;
    }

    public void setCurrentUser(Signature signature){
        instance.get().currentUsr = signature;
    }

    public LangKey currentLanguage(){
        return instance.get().currentLang;
    }

    public void setCurrentLanguage(LangKey lang){
        instance.get().currentLang = lang;
    }

    @Override
    public LangKey defaultLanguage() {
        return TylContext.defaultLang;
    }

    @Override
    public void setDefaultLanguage(LangKey language) {
        TylContext.defaultLang = language;
    }
}
