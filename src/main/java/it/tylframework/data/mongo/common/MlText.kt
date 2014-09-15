package it.tylframework.data.mongo.common

import java.util.HashMap
import it.tylframework.data.mongo.common.LangKey.en

/**
 * Created by marco on 12/09/14.
 */
class MlText({
    val defaultLang=en;
    val mlt = hashMapOf<LangKey,String>()

    public fun get(): String = mlt.get(defaultLang) !!
    public fun get(lang:LangKey): String = mlt.get(lang) !!
    public fun set(text: String): Any = mlt.set(defaultLang,text)  !!
    public fun set(lang:LangKey, text:String): Any = mlt.set(lang,text) !!
}
