package it.tylframework.data.mongo.basics;

import it.tylframework.data.mongo.common.Footprint;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

/**
 * Created by mp on 20/11/14.
 */
@Data
@AllArgsConstructor
public class NumeratorFeeder{
    Boolean freezed;
    Date starting_date;
    Integer starting_number;
    Date ending_date;
    Integer warning_number;
    Integer increment_by;
    Integer last_number_used;
    Boolean output_as_string;
    String output_format;
}
