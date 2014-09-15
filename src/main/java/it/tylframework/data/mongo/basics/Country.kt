package it.tylframework.data.mongo.basics

import it.tylframework.data.mongo.common.Footprint
import it.tylframework.data.mongo.common.Signature
import java.util.Date
import org.springframework.data.annotation.TypeAlias

/**
 * Created by marco on 25/08/14.
 */
[TypeAlias("bas_Country")]
data class Country(
        var twoCharCode: String,
        var officialName:String,
        var numericCode:Int)
        :Footprint(Signature("marco"), Date() ,Signature("marco"),  Date()) {
}