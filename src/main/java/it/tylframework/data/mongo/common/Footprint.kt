package it.tylframework.data.mongo.common

import java.util.Date
import org.springframework.data.annotation.Id

/**
 * Created by marco on 24/08/14.
 */
open class Footprint(
        val created_by: Signature,
        val created_on: Date,
        var updated_by: Signature,
        var updated_on: Date) {

    [Id]
    val id:String?=null
}