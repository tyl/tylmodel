package it.tylframework.data.mongo.common;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.joda.time.DateTime;
import org.springframework.data.annotation.*;
import org.springframework.data.domain.Auditable;

/**
 * Created with IntelliJ IDEA.
 * User: marco
 * Date: 18/11/14
 * Time: 22:37
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class Footprint implements Auditable<Signature,String> {

    @CreatedBy
    private Signature createdBy;

    @CreatedDate
    private DateTime createdDate;

    @LastModifiedBy
    private Signature lastModifiedBy;

    @LastModifiedDate
    private DateTime lastModifiedDate;

    @Version
    private Long version;

    @Id
    private String id;

    @Override
    public boolean isNew() {
        return (id == null);
    }

}
