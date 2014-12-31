package org.tylproject.data.mongo;

import org.joda.time.DateTime;
import org.tylproject.data.mongo.basics.Exceptions.NumeratorNoFeederException;

/**
 * Created by mp on 30/12/14.
 */
public interface NumeratorRepositoryCustom {
    public int getNextNum(String code) throws NumeratorNoFeederException;
    public int getNextNum(String code, DateTime instant) throws NumeratorNoFeederException;
}
