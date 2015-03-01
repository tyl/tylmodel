package org.tylproject.data.mongo.crm;

import lombok.Data;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.tylproject.data.mongo.basics.Industry;
import org.tylproject.data.mongo.common.MlText;
import org.tylproject.data.mongo.party.Party;
import org.tylproject.data.mongo.party.PartyRole;

/**
 * Created by marco on 01/03/15.
 */
@Document(collection="crm_organization")
@TypeAlias("crm_OrganizationCRM")
@Data
public class Organization extends PartyRole {

    String primaryEmail;
    String website;
    Integer numberOfEmployees;
    Industry industry;
    MlText comment;

    @DBRef
    Organization memberOf;

    public Organization() {
        super();
        Party p = new Party();
        p.setOrganization(new org.tylproject.data.mongo.party.Organization());
        setParty(p);
    }

    public String toString() {
        return "";
        //return getParty().getOrganization().getOrganizationName();
    }

    public boolean equals(Object o){
        if (o instanceof Organization)
            return this.getId().equals(((Organization) o).getId());
        return false;
    }
}

