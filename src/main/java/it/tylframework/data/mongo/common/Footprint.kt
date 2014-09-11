package it.tylframework.data.mongo.common

import java.util.Date
import org.springframework.data.annotation.Id

/**
 * Created by marco on 24/08/14.
 */
data class Footprint(created_by: Signature, created_on: Date, updated_by: Signature, updated_on: Date) {

    [Id]
    var id=null
}