package org.tylproject.data.mongo.basics.Dao;

import org.tylproject.data.mongo.basics.NumeratorType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 * User: marco
 * Date: 02/10/14
 * Time: 17:01
 */
@Component
public class NumeratorDao {

    @Autowired
    private  MongoTemplate mongoTemplate;

    public void createNumeratorType(NumeratorType numeratorType){
      mongoTemplate.save(numeratorType);
    }
}
