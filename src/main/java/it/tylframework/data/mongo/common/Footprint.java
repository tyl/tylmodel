package it.tylframework.data.mongo.common;

import lombok.Data;
import lombok.NonNull;
import org.springframework.data.annotation.Id;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: marco
 * Date: 18/11/14
 * Time: 22:37
 */
public @Data
class Footprint {
    @NonNull private Signature created_by;
    @NonNull private Date created_on;
    @NonNull private Signature upatedBy;
    @NonNull private Date updated_on;

    @Id
    String id;
}
