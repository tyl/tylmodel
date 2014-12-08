package it.tylframework.data.mongo.basics;

import it.tylframework.data.mongo.common.Footprint;
import it.tylframework.data.mongo.common.MlText;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by mp on 20/11/14.
 */
@Document(collection="bas_unit")
@TypeAlias("bas_unit")
@Data
@RequiredArgsConstructor
public class Unit extends Footprint{
    @NonNull String code;
    @NonNull  MlText name;
    MlText description;
    @NonNull  @DBRef(lazy = true) SystemOfUnits system_of_units;
}
