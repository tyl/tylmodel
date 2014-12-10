package it.tylframework.data.mongo.basics;

import it.tylframework.data.mongo.common.Footprint;
import it.tylframework.data.mongo.common.MlText;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.TypeAlias;

import java.util.ArrayList;

/**
 * Created by mp on 20/11/14.
 */
@TypeAlias("bas_Numerator")
@Data
@AllArgsConstructor
public class Numerator extends Footprint {
    String code;
    MlText name;
    MlText description;
    NumeratorType numerator_type;
    ArrayList<NumeratorFeeder> numerator_feeders = new ArrayList<NumeratorFeeder>();
}