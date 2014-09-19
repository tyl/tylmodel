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
        var startingNumber:Int,
        var endingDate:Date,
        var warningNumber:Int,
        var incrementBy:Int,
        var lastNumberUsed:Int,
        var outputAsString: Boolean,
        var ouputFormat:String)
:Footprint(TylContext.currentUser, Date() ,TylContext.currentUser,  Date()){

}