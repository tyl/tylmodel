package it.tylframework.data.mongo.common;

import org.springframework.data.annotation.Id;

import java.util.Date;

/**
 * Created by marco on 24/08/14.
 */
public class Footprint {
    @Id
    private String id;

    private String created_by;
    private Date created_on;
    private String updated_by;
    private Date updated_on;

    public Footprint(String created_by, Date created_on, String updated_by, Date updated_on){
        this.created_by=created_by;
        this.created_on=created_on;
        this.updated_by=updated_by;
        this.updated_on=updated_on;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreated_by() {
        return created_by;
    }

    public void setCreated_by(String created_by) {
        this.created_by = created_by;
    }

    public Date getCreated_on() {
        return created_on;
    }

    public void setCreated_on(Date created_on) {
        this.created_on = created_on;
    }

    public String getUpdated_by() {
        return updated_by;
    }

    public void setUpdated_by(String updated_by) {
        this.updated_by = updated_by;
    }

    public Date getUpdated_on() {
        return updated_on;
    }

    public void setUpdated_on(Date updated_on) {
        this.updated_on = updated_on;
    }
}
