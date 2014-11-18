package it.tylframework.data.mongo;

import it.tylframework.data.mongo.basics.Country;
import it.tylframework.data.mongo.basics.Dao.NumeratorDao;
import it.tylframework.data.mongo.basics.NumeratorType;
import it.tylframework.data.mongo.common.MlText;
import it.tylframework.data.mongo.common.Signature;
import it.tylframework.data.mongo.config.TylContext;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertTrue;

/*
 * Created with IntelliJ IDEA.
 * User: marco
 * Date: 19/09/14
 * Time: 23:31
 * <p/>
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = MongoModelConfig.class)
public class ModelTests {

    @Autowired
    private CountryRepository countryRep;

    @Autowired
    private NumeratorDao numeratorDao;

    @Before
    public void init() {
        TylContext.instance$.setCurrentUser(new Signature("mp@marcopancotti.it"));
        countryRep.deleteAll();
        countryRep.save(new Country("it", "Italia",123));
    }

    @Test
    public void testCountry(){
        Country italia = countryRep.findByTwoCharCode("it");
        assertTrue("Country with twoCharCode=it is not Italia: ", italia.getOfficialName().equals("Italia"));
        italia = countryRep.findByNumericCode(123);
        assertTrue("Country with numericCode=123 is not Italia: ", italia.getOfficialName().equals("Italia"));
    }

    @Test
    public void testNumeratorType() {
        NumeratorType numeratorType = new NumeratorType(new MlText("Fatture"), new MlText("Numeratore Fatture"), true, true, true);
        numeratorDao.createNumeratorType(numeratorType);
    }
}
