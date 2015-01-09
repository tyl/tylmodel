package org.tylproject.data.mongo;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.tylproject.data.mongo.party.repository.PartyRepository;
import org.tylproject.data.mongo.common.MlText;
import org.tylproject.data.mongo.party.*;
import org.tylproject.data.mongo.party.repository.PartyRelationshipRepository;
import org.tylproject.data.mongo.party.repository.PartyRelationshipTypeRepository;
import org.tylproject.data.mongo.party.repository.PartyRoleRepository;
import org.tylproject.data.mongo.party.repository.PartyRoleTypeRepository;

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

    @Autowired
    private PartyRelationshipTypeRepository partyRelationshipTypeRep;

    @Autowired
    private PartyRoleTypeRepository partyRoleTypeRep;

    @Autowired
    private PartyRelationshipRepository partyRelationshipRep;

    @Autowired
    private PartyRoleRepository partyRoleRep;


    @Before
    public void init() {
        partyRep.deleteAll();
        Party party = new Party();
        party.setCode("party001");
        party.setShortName("Tyl Consulting");
        party.getPartyIdentifier().add(new PartyIdentifier(PartyIdentifierType.FISCAL_CODE, "PNCMRC56P14F205U"));
        party.getPartyIdentifier().add(new PartyIdentifier(PartyIdentifierType.VAT_CODE,"12345678901"));
        mongoTemplate.insert(party);
        Party party2 = new Party();
        party2.setCode("party002");
        party2.setShortName("Tyl Consulting2");
        party2.getPartyIdentifier().add(new PartyIdentifier(PartyIdentifierType.COMPANY_IDENTIFIER, "PNCMRC56P14F205U"));
        party2.getPartyIdentifier().add(new PartyIdentifier(PartyIdentifierType.VAT_CODE,"12345678902"));
        mongoTemplate.insert(party2);

    }

    @Test
    public void testPartyRelationshipType(){
        partyRelationshipTypeRep.deleteAll();
        PartyRelationshipType prt01 = new PartyRelationshipType("prt01");
        mongoTemplate.save(prt01);
        PartyRelationshipType prt02 = new PartyRelationshipType("prt02");
        mongoTemplate.save(prt02);
    }

    @Test
    public void testPartyRelationship(){
        partyRelationshipTypeRep.deleteAll();
        partyRoleTypeRep.deleteAll();
        partyRelationshipRep.deleteAll();
        partyRoleRep.deleteAll();
        partyRep.deleteAll();


        PartyRelationshipType prt01=new PartyRelationshipType("prt02");
        prt01.setDescription(new MlText("prt01description"));
        mongoTemplate.save(prt01);
        PartyRoleType proletype01 = new PartyRoleType("prolet01",new MlText("partyroletype01"));
        PartyRoleType proletype02 = new PartyRoleType("prolet02",new MlText("partyroletype02"));
        mongoTemplate.save(proletype01);
        mongoTemplate.save(proletype02);

        Party party01= new Party();
        party01.setCode("party01");
        mongoTemplate.save(party01);
        Party party02 = new Party();
        party02.setCode("party02");
        mongoTemplate.save(party02);

        PartyRole partyRole01 = new PartyRole(party01,proletype01);
        partyRole01.setName(new MlText("role01name"));
        PartyRole partyRole02 = new PartyRole(party02,proletype02);
        partyRole02.setName(new MlText("role02name"));
        mongoTemplate.save(partyRole01);
        mongoTemplate.save(partyRole02);

        PartyRelationship pr = new PartyRelationship();
        pr.setPartyRelationshipType(prt01);
        pr.setLeftPartyRole(partyRole01);
        pr.setRightPartyRole(partyRole02);
        pr.setLeftPartyCode(partyRole01.getName().getText());
        pr.setRightPartyCode(partyRole02.getName().getText());
        mongoTemplate.save(pr);
    }
}


