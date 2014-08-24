package it.tylframework.data.mongo.basics

import it.tylframework.data.mongo.common.Footprint
import java.util.Date

/**
 * Created by marco on 24/08/14.
 */
data class Language(val code: String, var flag: String, var name:String) :Footprint("marco", Date() ,"marco",  Date()){
}
