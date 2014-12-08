package it.tylframework.data.mongo.common;

import it.tylframework.data.mongo.config.TylContext;

import java.util.HashMap;

/**
 * Created by mp on 20/11/14.
 */
public class MlText {
    String dt;
    HashMap<LangKey,String> mlt = new HashMap<LangKey,String>();

    public MlText(String dt){
        if(!dt.isEmpty()){
            this.dt=dt;
            set(dt);
        }
    }

    public String get(){
        if(mlt.get(TylContext.currentLanguage()).isEmpty())
            return("");
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

