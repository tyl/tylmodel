package it.tylframework.data.mongo.basics;

import it.tylframework.data.mongo.common.Footprint;
import it.tylframework.data.mongo.common.MlText;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.TypeAlias;

/**
 * Created by mp on 20/11/14.
 */
@TypeAlias("bas_SystemOfUnits")
@Data
@AllArgsConstructor
public class SystemOfUnits extends Footprint {
    MlText name;
    MlText description;
}
