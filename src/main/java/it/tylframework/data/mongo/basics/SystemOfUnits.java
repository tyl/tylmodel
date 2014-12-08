package it.tylframework.data.mongo.basics;

import it.tylframework.data.mongo.common.Footprint;
import it.tylframework.data.mongo.common.MlText;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by mp on 20/11/14.
 */
@Document(collection="bas_systemofunits")
@TypeAlias("bas_systemofunits")
@Data
@RequiredArgsConstructor
public class SystemOfUnits extends Footprint {
    @NonNull String code;
    @NonNull MlText name;
    MlText description;
}
