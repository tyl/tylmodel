package it.tylframework.data.mongo.common;

import it.tylframework.data.mongo.config.TylContext;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedBy;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: marco
 * Date: 18/11/14
 * Time: 22:37
 */
public @Data
class Footprint {
    private Signature created_by;

    @CreatedDate
    private Date created_on;

    private Signature upated_by;

    @LastModifiedBy
    private Date updated_on;

    public Footprint(){
        created_by = TylContext.currentUser();
        upated_by = TylContext.currentUser();
    }

    @Id
    private String id;
}
