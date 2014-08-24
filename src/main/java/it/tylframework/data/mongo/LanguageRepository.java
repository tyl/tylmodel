package it.tylframework.data.mongo;

import it.tylframework.data.mongo.basics.Language;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by marco on 25/08/14.
 */
public interface LanguageRepository extends MongoRepository<Language, String> {
}
