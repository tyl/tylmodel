package it.tylframework.data.mongo.basics;

import it.tylframework.data.mongo.common.Footprint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by mp on 20/11/14.
 */
@Document(collection="bas_language")
@TypeAlias("bas_language")
@Data
@RequiredArgsConstructor
public class Language extends Footprint{
    @NonNull String code;
    String flag;
    @NonNull String name;

}
