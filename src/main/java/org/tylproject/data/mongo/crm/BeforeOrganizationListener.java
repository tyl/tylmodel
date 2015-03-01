package org.tylproject.data.mongo.crm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.stereotype.Component;

/**
 * Created by mp on 17/02/15.
 */
@Component
public class BeforeOrganizationListener extends AbstractMongoEventListener<Organization> {

    @Autowired
    private MongoOperations mongoOps;

    @Override
    public void onBeforeConvert(Organization source) {
        super.onBeforeConvert(source);
        mongoOps.save(source.getParty());
    }
}
