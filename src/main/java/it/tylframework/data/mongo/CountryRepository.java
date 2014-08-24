package it.tylframework.data.mongo;

import it.tylframework.data.mongo.basics.Country;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by marco on 24/08/14.
 */
public interface CountryRepository extends MongoRepository<Country,String> {
}
