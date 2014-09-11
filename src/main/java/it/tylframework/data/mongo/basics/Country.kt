package it.tylframework.data.mongo.basics

import it.tylframework.data.mongo.common.Footprint
import it.tylframework.data.mongo.common.Signature
import java.util.Date

/**
 * Created by marco on 25/08/14.
 */
data class Country(val two_char_code: String, var numeric_code:int, var official_name:String)
            :Footprint(Signature("marco"), Date() ,Signature("marco"),  Date()) {
}