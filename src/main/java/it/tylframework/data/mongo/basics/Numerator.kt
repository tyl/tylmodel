package it.tylframework.data.mongo.basics

import it.tylframework.data.mongo.common.MlText
import it.tylframework.data.mongo.common.Footprint
import it.tylframework.data.mongo.common.Signature
import java.util.Date

/**
 * Created by marco on 15/09/14.
 */
data class Numerator(
    var stdName: String,
    var name: MlText,
    var description: MlText)
    :Footprint(Signature("marco"), Date() ,Signature("marco"),  Date()) {
}