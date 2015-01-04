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

package org.tylproject.data.mongo.basics.customrepimpl;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.tylproject.data.mongo.basics.repository.NumeratorRepositoryCustom;
import org.tylproject.data.mongo.exceptions.BasicsError;
import org.tylproject.data.mongo.basics.Numerator;
import org.tylproject.data.mongo.basics.NumeratorFeeder;
import org.tylproject.data.mongo.exceptions.TylModelException;

import java.util.IllegalFormatException;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

/**
 * Created by mp on 30/12/14.
 */
public class NumeratorRepositoryImpl implements NumeratorRepositoryCustom {

    Numerator numerator;

    @Autowired
    private MongoTemplate mongoOps;

    @Override
    public int getNextNum(String code) throws TylModelException {
        return getNextNum(code, new DateTime());
    }

    public String getNextFormattedNum(String code) throws TylModelException {
        return getNextFormattedNum(code, new DateTime());
    }

    @Override
    public int getNextNum(String code, DateTime date) throws TylModelException {
        return new DocNumber().invoke(code, date).getNumber();
    }

    public String getNextFormattedNum(String code, DateTime date) throws TylModelException {
        return new DocNumber().invoke(code, date).getFormattedNumber();
    }


    private NumeratorFeeder getNextFeeder(String code, DateTime date) throws TylModelException {
        NumeratorFeeder nf = getNumeratorFeeder(code, date);
        if (nf == null)
            throw new TylModelException(BasicsError.NO_NUMERATOR)
                    .set("code", code)
                    .set("date", date);
        else {
            return nf;
        }
    }

    private NumeratorFeeder getNumeratorFeeder(String code, DateTime date) {
        Query byCode = query(where("code").is(code));
        numerator = mongoOps.findOne(byCode, Numerator.class);
        NumeratorFeeder feeder = null;
        for (NumeratorFeeder nf : numerator.getNumerator_feeders())
            if (((nf.getStarting_date().isBefore(date) || nf.getStarting_date().isEqual(date)) && (nf.getEnding_date().isAfter(date) || nf.getEnding_date().isEqual(date))) && !nf.isFreezed()){
                feeder = nf;
                break;
            }
        return feeder;
    }

    private class DocNumber {
        private int number;
        private String formattedNumber;

        public DocNumber invoke(String code, DateTime date) throws TylModelException {
            NumeratorFeeder nf = getNextFeeder(code, date);
            number = nf.getLast_number_used() + nf.getIncrement_by();
            // If you expect a formatted String as output, try to format the number using the output format
            if (nf.getOutput_as_string()) {
                try {
                    formattedNumber = String.format(nf.getOutput_format(), number);
                } catch (IllegalFormatException ife) {
                    throw new TylModelException(BasicsError.BAD_NUMERATOR_FORMAT)
                            .set("code", code)
                            .set("date", date)
                            .set("format", nf.getOutput_format());
                } catch (NullPointerException npe) {
                    throw new TylModelException(BasicsError.NO_NUMERATOR_FORMAT)
                            .set("code", code)
                            .set("date", date);
                }
            }
            // update the last number used
            nf.setLast_number_used(number);
            try {
                mongoOps.save(numerator);
                return this;
            } catch (OptimisticLockingFailureException e) {
                //Recursively retry in case of OptimistickLock
                return invoke(code, date);
            }
        }

        public int getNumber() {
            return number;
        }

        public String getFormattedNumber() {
            return formattedNumber;
        }
    }

}
