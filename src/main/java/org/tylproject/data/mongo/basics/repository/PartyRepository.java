package org.tylproject.data.mongo.basics.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.tylproject.data.mongo.party.Party;

/**
 * Created by marco on 03/01/15.
 */
public interface PartyRepository extends MongoRepository<Party,String> {
}
