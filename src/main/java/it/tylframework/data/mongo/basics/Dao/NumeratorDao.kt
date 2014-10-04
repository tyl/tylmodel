package it.tylframework.data.mongo.basics.Dao

import it.tylframework.data.mongo.basics.NumeratorType
import it.tylframework.data.mongo.common.MlText
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.stereotype.Component

/**
 * Created with IntelliJ IDEA.
 * User: marco
 * Date: 02/10/14
 * Time: 17:01
 */
Component
public class NumeratorDao() {

    Autowired
    private val mongoTemplate: MongoTemplate? = null

    public fun createNumeratorType(numeratorType: NumeratorType) {
        mongoTemplate?.save(numeratorType)
    }
}
