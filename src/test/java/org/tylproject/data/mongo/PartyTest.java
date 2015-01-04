package org.tylproject.data.mongo;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.tylproject.data.mongo.basics.repository.PartyRepository;
import org.tylproject.data.mongo.party.Party;
import org.tylproject.data.mongo.party.PartyIdentifier;
import org.tylproject.data.mongo.party.PartyIdentifierType;

import static org.junit.Assert.assertTrue;

/**
 * Created by marco on 03/01/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = MongoModelConfig.class)
public class PartyTest {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private PartyRepository partyRep;

    @Before
    public void init() {
        partyRep.deleteAll();
        Party party = new Party();
        party.setCode("party001");
        party.setShort_name("Tyl Consulting");
        party.getParty_identifier().add(new PartyIdentifier(PartyIdentifierType.FISCAL_CODE, "PNCMRC56P14F205U"));
        party.getParty_identifier().add(new PartyIdentifier(PartyIdentifierType.VAT_CODE,"12345678901"));
        mongoTemplate.insert(party);

    }

    @Test
    public void testDataParty(){
        assertTrue(true);
    }
}

        /*
        private Boolean freezed;

    private FreezeReason freeze_reason;
    private boolean canceled;

    @Indexed
    private String code;

    @Indexed
    private String short_name;

    private ArrayList<GeographicAddress> geografic_address=new ArrayList<GeographicAddress>();
    private ArrayList<TelecomAddress> telecom_address=new ArrayList<TelecomAddress>();
    private ArrayList<WebAddress> web_address=new ArrayList<WebAddress>();
    private ArrayList<EmailAddress> email_address=new ArrayList<EmailAddress>();

    @Indexed
    private ArrayList<PartyIdentifier> party_identifier=new ArrayList<PartyIdentifier>();
         */
