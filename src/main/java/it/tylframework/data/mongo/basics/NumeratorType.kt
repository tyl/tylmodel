package it.tylframework.data.mongo.basics

import it.tylframework.data.mongo.common.MlText
import it.tylframework.data.mongo.common.Footprint
import it.tylframework.data.mongo.config.TylContext
import java.util.Date

/**
 * Created by marco on 19/09/14.
 */

data class NumeratorType(
        var name:MlText,
        var description:MlText,
        var orderedBy:Boolean,
        var automaticAttribution:Boolean,
        var manualAdmitted:Boolean)
        :Footprint(TylContext.currentUser, Date() ,TylContext.currentUser,  Date()
){}