/*
 * Copyright 2015 Tyl Consulting s.a.s.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.tylproject.data.mongo;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.tylproject.data.mongo.basics.Numerator;
import org.tylproject.data.mongo.basics.NumeratorFeeder;
import org.tylproject.data.mongo.basics.NumeratorType;
import org.tylproject.data.mongo.basics.repository.NumeratorRepository;
import org.tylproject.data.mongo.basics.repository.NumeratorTypeRepository;
import org.tylproject.data.mongo.common.MlText;
import org.tylproject.data.mongo.exceptions.BasicsError;
import org.tylproject.data.mongo.exceptions.TylModelException;

import java.util.Locale;
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

    @Autowired
    private ApplicationContext context;


    @Before
    public void init() {
        numeratorTypeRep.deleteAll();
        numeratorRep.deleteAll();
        NumeratorType invoiceType=new NumeratorType("invoiceType",new MlText("Invoice"));
        mongoTemplate.insert(invoiceType);
        NumeratorType salesOrderType=new NumeratorType("salesOrderType",new MlText("Sales Order"));
        mongoTemplate.insert(salesOrderType);
        Numerator invoiceNumerator = new Numerator("invNum",new MlText("Invoice Numerator"),invoiceType);


        NumeratorFeeder inv2015 = new NumeratorFeeder(new DateTime(2015,1,1,0,0),new DateTime(2015,12,31,23,59));
        inv2015.setLast_number_used(233);
        // TODO gestire il controllo sul fatto che l'output deve essere formattato;
        // TODO fare in modo che il test sulla data corrente funzioni sempre
        inv2015.setOutput_as_string(true);
        inv2015.setOutput_format("INVNUM%06d");
        invoiceNumerator.getNumerator_feeders().add(inv2015);


        NumeratorFeeder inv2016 = new NumeratorFeeder(new DateTime(2016,1,1,0,0),new DateTime(2016,12,31,23,59));
        invoiceNumerator.getNumerator_feeders().add(inv2016);
        NumeratorFeeder inv2017 = new NumeratorFeeder(new DateTime(2017,1,1,0,0),new DateTime(2017,12,31,23,59));
        inv2017.setOutput_as_string(true);
        inv2017.setOutput_format("INVNUM%06d");
        invoiceNumerator.getNumerator_feeders().add(inv2017);
        NumeratorFeeder inv2018 = new NumeratorFeeder(new DateTime(2018,1,1,0,0),new DateTime(2018,12,31,23,59));
        inv2018.setOutput_as_string(true);
        inv2018.setOutput_format("INVNUM%06d");
        invoiceNumerator.getNumerator_feeders().add(inv2018);
        mongoTemplate.insert(invoiceNumerator);
    }

    @Test
    public void testNumericNumerator() throws TylModelException {
        assertEquals(234, numeratorRep.getNextNum("invNum"));
        assertEquals(1,numeratorRep.getNextNum("invNum",new DateTime(2018,4,12,0,0)));
    }

    @Test
    public void testFormattedNumerator() throws TylModelException {
        assertEquals("INVNUM000234", numeratorRep.getNextFormattedNum("invNum"));
    }


    @Test(expected = TylModelException.class)
    public void testNumericNumeratorOutOfRange() throws TylModelException {
        assertEquals(234,numeratorRep.getNextNum("invNum",new DateTime(2999,4,12,0,0)));
    }

    /**
     * test the use of the key of the TylModelErrorCode included in the TylModelException as key for the
     * localized message.The message includes the code nd
     *
     */
    @Test
    public void testNumericNumeratorOutOfRangeCatched(){
        try {
            assertEquals(234, numeratorRep.getNextNum("invNum", new DateTime(2999, 4, 12, 0, 0)));
        } catch (TylModelException e) {
            if(e.getErrorCode().equals(BasicsError.NO_NUMERATOR)) {
                String italianMessage = context.getMessage(e.getErrorCode().getKey(), new Object[]{"invNum", new DateTime(2999, 4, 12, 0, 0).toString(DateTimeFormat.shortDate())}, Locale.ITALIAN);
                String englishMessage = context.getMessage(e.getErrorCode().getKey(), new Object[]{"invNum", new DateTime(2999, 4, 12, 0, 0).toString(DateTimeFormat.shortDate())}, Locale.ENGLISH);
                String itmsg = String.format("Nessun numeratore trovato per numeratore con codice %s alla data %s", e.get("code"), ((DateTime) e.get("date")).toString(DateTimeFormat.shortDate()));
                assertEquals(italianMessage, itmsg);
                String enmsg = String.format("No numerator found for code %s and date %s", e.get("code"), ((DateTime) e.get("date")).toString(DateTimeFormat.shortDate()));
                assertEquals(englishMessage, enmsg);
                System.out.println(itmsg);
            }
        }
    }

    // Da testare:
    // BAD FORMAT
    // NO FORMAT
    // Freezed (NO NUMERATOR anche se c'è perchè è freezed)
    // Creare sempre un Feeder per l'anno corrente in modo da poter testare l'acquisizione senza date

}