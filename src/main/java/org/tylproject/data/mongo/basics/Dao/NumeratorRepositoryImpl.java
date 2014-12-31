package org.tylproject.data.mongo.basics.Dao;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.tylproject.data.mongo.NumeratorRepository;
import org.tylproject.data.mongo.NumeratorRepositoryCustom;
import org.tylproject.data.mongo.basics.Exceptions.NumeratorNoFeederException;
import org.tylproject.data.mongo.basics.Numerator;
import org.tylproject.data.mongo.basics.NumeratorFeeder;

/**
 * Created by mp on 30/12/14.
 */
public class NumeratorRepositoryImpl implements NumeratorRepositoryCustom {

    Numerator numerator;

    //TODO - sostituire con MongoTemplate e Query
    @Autowired
    private NumeratorRepository numeratorRep;

    @Override
    public int getNextNum(String code) throws NumeratorNoFeederException {
        return getNextNum(code,new DateTime());
    }

    @Override
    // TODO - fare in modo che il tutto avvenga con optimistic lock con reiterazione sulla lettura in caso di eccezione
    public int getNextNum(String code, DateTime instant) throws NumeratorNoFeederException {
        NumeratorFeeder nf=getNumeratorFeeder(code,instant);
        if(nf==null)
            //TODO - I18n del messaggio
            throw new NumeratorNoFeederException("NessunNumeroPerQuestoNumeratore");
        else {
            int nextNum=nf.getLast_number_used()+nf.getIncrement_by();
            nf.setLast_number_used(nextNum);
            numeratorRep.save(numerator);
            return nextNum;
        }
    }

    private NumeratorFeeder getNumeratorFeeder(String code, DateTime date){
        numerator= numeratorRep.findByCode(code);
        NumeratorFeeder feeder=null;
        for( NumeratorFeeder nf:numerator.getNumerator_feeders())
            if((nf.getStarting_date().isBefore(date) || nf.getStarting_date().isEqual(date)) && (nf.getEnding_date().isAfter(date) || nf.getEnding_date().isEqual(date))){
                feeder=nf;
                break;
            }
        return feeder;
    }
}
