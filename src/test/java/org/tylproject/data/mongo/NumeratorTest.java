package org.tylproject.data.mongo;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.tylproject.data.mongo.basics.Exceptions.NumeratorNoFeederException;
import org.tylproject.data.mongo.basics.Numerator;
import org.tylproject.data.mongo.basics.NumeratorFeeder;
import org.tylproject.data.mongo.basics.NumeratorType;
import org.tylproject.data.mongo.common.MlText;

import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

/**
 * Created by mp on 30/12/14.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = MongoModelConfig.class)
public class NumeratorTest {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private NumeratorTypeRepository numeratorTypeRep;

    @Autowired
    private NumeratorRepository numeratorRep;

    @Before
    public void init() {
        numeratorTypeRep.deleteAll();
        numeratorRep.deleteAll();
        NumeratorType invoiceType=new NumeratorType("invoiceType",new MlText("Invoice"));
        mongoTemplate.insert(invoiceType);
        NumeratorType salesOrderType=new NumeratorType("salesOrderType",new MlText("Sales Order"));
        mongoTemplate.insert(salesOrderType);
        Numerator invoiceNumerator = new Numerator("invNum",new MlText("Invoice Numerator"),invoiceType);
        NumeratorFeeder inv2014 = new NumeratorFeeder(new DateTime(2014,1,1,0,0),new DateTime(2014,12,31,23,59));
        inv2014.setLast_number_used(233);
        invoiceNumerator.getNumerator_feeders().add(inv2014);
        NumeratorFeeder inv2015 = new NumeratorFeeder(new DateTime(2015,1,1,0,0),new DateTime(2015,12,31,23,59));
        invoiceNumerator.getNumerator_feeders().add(inv2015);
        NumeratorFeeder inv2016 = new NumeratorFeeder(new DateTime(2016,1,1,0,0),new DateTime(2016,12,31,23,59));
        invoiceNumerator.getNumerator_feeders().add(inv2016);
        NumeratorFeeder inv2017 = new NumeratorFeeder(new DateTime(2017,1,1,0,0),new DateTime(2017,12,31,23,59));
        invoiceNumerator.getNumerator_feeders().add(inv2017);
        NumeratorFeeder inv2018 = new NumeratorFeeder(new DateTime(2018,1,1,0,0),new DateTime(2018,12,31,23,59));
        invoiceNumerator.getNumerator_feeders().add(inv2018);
        mongoTemplate.insert(invoiceNumerator);
    }

    @Test
    public void testNumericNumerator() throws NumeratorNoFeederException {
        assertEquals(234, numeratorRep.getNextNum("invNum"));
        assertEquals(1,numeratorRep.getNextNum("invNum",new DateTime(2015,4,12,0,0)));
    }

}