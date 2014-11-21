package it.tylframework.data.mongo.basics;

import it.tylframework.data.mongo.common.Footprint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import org.springframework.data.annotation.TypeAlias;

/**
 * Created by mp on 20/11/14.
 */
@TypeAlias("bas_language")
@Data
@AllArgsConstructor
public class Language extends Footprint{
    @NonNull
    String code;
    String flag;
    String name;

}
