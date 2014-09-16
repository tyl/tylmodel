package it.tylframework.data.mongo.basics

import it.tylframework.data.mongo.common.Footprint
import java.util.Date
import it.tylframework.data.mongo.common.Signature
import it.tylframework.data.mongo.config.TylContext

/**
 * Created by marco on 24/08/14.
 */
data class Language(
        var code: String,
        var flag: String,
        var name:String)
            :Footprint(TylContext.currentUser, Date() ,TylContext.currentUser,  Date()){
}
