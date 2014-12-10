package it.tylframework.data.mongo.common;

import it.tylframework.data.mongo.config.TylContext;

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

