package it.tylframework.data.mongo.basics

import it.tylframework.data.mongo.common.MlText
import it.tylframework.data.mongo.common.Footprint
import it.tylframework.data.mongo.common.Signature
import java.util.Date
import it.tylframework.data.mongo.config.TylContext
import org.springframework.data.annotation.TypeAlias

/**
 * Created by marco on 15/09/14.
 */
[TypeAlias("bas_Numerator")]
data class Numerator(
    var stdName: String,
    var name: MlText,
    var description: MlText,
    var numeratorType:NumeratorType,
    var numeratorFeeders: Array<NumeratorFeeder>)
    :Footprint(TylContext.currentUser, Date() ,TylContext.currentUser,  Date()) {
}