package it.tylframework.data.mongo.common

import java.util.HashMap
import it.tylframework.data.mongo.common.LangKey.en
import it.tylframework.data.mongo.config.TylContext

/**
 * Created by marco on 12/09/14.
 */
class MlText(defaultText : String? = null){
    val mlt = hashMapOf<LangKey,String>();
    {
        if(defaultText != null)
            this.set(defaultText)
    }

    public fun get(): String = mlt.getOrElse(TylContext.currentLang, { "" })
    public fun invoke() : String = get()
    public fun get(lang:LangKey): String = mlt.getOrElse(lang, { get() })
    public fun set(text: String) { mlt.set(TylContext.currentLang,text) }
    public fun set(lang:LangKey, text:String) { mlt.set(lang,text) }
}
