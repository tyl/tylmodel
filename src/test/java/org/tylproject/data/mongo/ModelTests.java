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

import org.tylproject.data.mongo.basics.repository.*;
import org.tylproject.data.mongo.common.LangKey;
import org.tylproject.data.mongo.common.MlText;
import org.tylproject.data.mongo.common.Quantity;
import org.tylproject.data.mongo.config.Context;
import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.tylproject.data.mongo.basics.*;
import org.tylproject.data.mongo.helpers.MlTextHelper;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = MongoModelConfig.class)
public class ModelTests {

    @Autowired
    private CountryRepository countryRep;

    @Autowired
    private LanguageRepository languageRep;

    @Autowired
    private  SystemOfUnitsRepository sistemOfUnitsRep;

    @Autowired
    private  UnitRepository unitRep;

    @Autowired
    private ConversionFactorRepository convFactRep;

    @Autowired
    private  MongoTemplate mongoTemplate;

    @Autowired
    private NumeratorTypeRepository numeratorTypeRep;

    @Autowired
    private NumeratorRepository numeratorRep;

    @Autowired
    private Context tylContext;

    @Autowired
    MlTextHelperFactory mlTextHelperFactory;

    private MlTextHelper mlTextHelper(MlText mlText) {
        return mlTextHelperFactory.of(mlText);
    }

    private MlText mlTextOf(String text) {
        return mlTextHelperFactory.mlTextOf(text);
    }


    @Before
    public  void init() {
        //TylContext.setCurrentUser(Signature.of("mp@marcopancotti.it"));
        countryRep.deleteAll();
        languageRep.deleteAll();
        sistemOfUnitsRep.deleteAll();
        unitRep.deleteAll();
        convFactRep.deleteAll();
        numeratorTypeRep.deleteAll();
        numeratorRep.deleteAll();

        Country addedCountry=countryRep.save(new Country("it", "Italia", 123));
        addedCountry.setNumericCode(24);
        countryRep.save(addedCountry);
        addedCountry.setNumericCode(123);
        countryRep.save(addedCountry);

        Language language=new Language("it","Italian");
        mongoTemplate.insert(language);
        language.setName("Italiano");
        mongoTemplate.save(language);
        Language english= new Language("en","English");
        mongoTemplate.insert(english);

        NumeratorType invoiceType=new NumeratorType("invoiceType", mlTextOf("Invoice"));
        mongoTemplate.insert(invoiceType);
        NumeratorType salesOrderType=new NumeratorType("salesOrderType", mlTextOf("Sales Order"));
        mongoTemplate.insert(salesOrderType);
        Numerator invoiceNumerator = new Numerator("invNum", mlTextOf("Invoice Numerator"),invoiceType);
        NumeratorFeeder inv2014 = new NumeratorFeeder(new DateTime(2014,1,1,0,0),new DateTime(2014,12,31,23,59));
        inv2014.setLastNumberUsed(233);
        invoiceNumerator.getNumeratorFeeders().add(inv2014);
        NumeratorFeeder inv2015 = new NumeratorFeeder(new DateTime(2015,1,1,0,0),new DateTime(2015,12,31,23,59));
        invoiceNumerator.getNumeratorFeeders().add(inv2015);
        NumeratorFeeder inv2016 = new NumeratorFeeder(new DateTime(2016,1,1,0,0),new DateTime(2016,12,31,23,59));
        invoiceNumerator.getNumeratorFeeders().add(inv2016);
        mongoTemplate.insert(invoiceNumerator);

        MlText si= mlTextOf("International System of Units");
        si.setText(LangKey.it, "Unità di Misura Internazionale");
        si.setText(LangKey.es, "Unitad de Misuras");
        SystemOfUnits systemOfUnits=new SystemOfUnits("SI",si);
        mongoTemplate.insert(systemOfUnits);

        MlText unitName = mlTextOf("meter");
        Unit m = new Unit("m",unitName,systemOfUnits);
        Unit km = new Unit("km",unitName,systemOfUnits);
        mongoTemplate.insert(m);
        mongoTemplate.insert(km);
        mongoTemplate.insert(new ConversionFactor(km,m,BigDecimal.valueOf(1000)));
    }

    @Test
    public void testMlText(){
        MlText mlt = new MlText();
        MlTextHelper mlth = mlTextHelper(mlt);

        final String
            es = "Texto en español",
            en = "Text in English",
            it = "Testo in italiano";

        tylContext.setDefaultLanguage(LangKey.en);
        tylContext.setCurrentLanguage(LangKey.it);

        mlt.setText(LangKey.es, es);
        mlt.setText(LangKey.en, en);
        mlth.setCurrentText(it);

        assertEquals(mlth.getText(LangKey.de),en);
        assertEquals(mlth.getText(LangKey.it),it);
        assertEquals(mlth.getText(LangKey.es),es);

        MlText mlt2 = new MlText();
        assertEquals(mlt2.getText(LangKey.es), "");

        MlText mlt3 = new MlText();
        mlt3.setText(LangKey.en, en);
        assertEquals(mlTextHelperFactory.of(mlt3).getText(LangKey.es),en);
    }

    @Test
        public void testCountry(){
        Country italia = countryRep.findByTwoCharCode("it");
        assertTrue("Country with twoCharCode=it is not Italia: ", italia.getOfficialName().equals("Italia"));
        italia = countryRep.findByNumericCode(123);
        assertTrue("Country with numericCode=123 is not Italia: ", italia.getOfficialName().equals("Italia"));
    }

    @Test
    public void testLanguage(){
        Language italian = languageRep.findByCode("it");
        assertTrue("Language with Code=it is not Italia: ", italian.getName().equals("Italiano"));
        italian = languageRep.findByName("Italiano");
        assertTrue("Language with name=Italian is not Italian ", italian.getCode().equals("it"));
        Language english = languageRep.findByCode("en");
        assertTrue("Language with Code=en is not English: ", english.getName().equals("English"));
    }


    @Test
    public void testUnits(){
        Unit meter =  unitRep.findByCode("m");
        assertTrue("Unit with Code=m is not meter but: " +
                mlTextHelper(meter.getName()).getCurrentText(),
                mlTextHelper(meter.getName()).getCurrentText().equals("meter"));
        SystemOfUnits su = meter.getSystemOfUnits();
        assertTrue("SystemOfUnits of m is not SI but: "+su.getCode(), su.getCode().equals("SI"));
        Unit kilometer = unitRep.findByCode("km");

        Quantity quantityToConvert=new Quantity(BigDecimal.valueOf(10),kilometer.getCode());
        ConversionFactor fromKmToMeter = convFactRep.findByFromAndTo(kilometer,meter);
        Assert.assertEquals(fromKmToMeter.convert(quantityToConvert), new Quantity(BigDecimal.valueOf(10000), meter.getCode()));

        quantityToConvert=new Quantity(BigDecimal.valueOf(20000),meter.getCode());
        Assert.assertEquals(fromKmToMeter.reverseConvert(quantityToConvert), new Quantity(BigDecimal.valueOf(20), kilometer.getCode()));

    }

    /**
     * Test the optimistick versioning of the Auditable objects
     */
    @Test(expected = org.springframework.dao.OptimisticLockingFailureException.class)
    public void testVersion(){
        Numerator nl =  numeratorRep.findByCode("invNum");
        Numerator nl2 =  numeratorRep.findByCode("invNum");
        mlTextHelper(nl2.getName()).setCurrentText("description modified");
        mongoTemplate.save(nl2);
        mongoTemplate.save(nl);
    }

    @Test
    public void testNumeratorType() {
        NumeratorType invoiceType = numeratorTypeRep.findByCode("invoiceType");
        Assert.assertEquals(mlTextHelper(invoiceType.getName()).getCurrentText(), "Invoice");
    }
}
