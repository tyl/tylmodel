package it.tylframework.data.mongo.basics

import java.util.Date
import it.tylframework.data.mongo.common.Footprint
import it.tylframework.data.mongo.config.TylContext

/**
 * Created by marco on 19/09/14.
 */
data class NumeratorFeeder(
        var freezed:Boolean,
        var startingDate:Date,
        var startingNumber:Integer,
        var endingDate:Date,
        var warningNumber:Integer,
        var incrementBy:Integer,
        var lastNumberUsed:Integer,
        var outputAsString: Boolean,
        var ouputFormat:String)
:Footprint(TylContext.currentUser, Date() ,TylContext.currentUser,  Date()){

}