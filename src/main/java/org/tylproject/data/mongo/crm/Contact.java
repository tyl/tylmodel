package org.tylproject.data.mongo.crm;

import lombok.Data;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;
import org.tylproject.data.mongo.party.PartyRole;

/**
 * Created by marco on 01/03/15.
 */
@Document(collection="crm_contact")
@TypeAlias("crm_Contact")
@Data
public class Contact extends PartyRole {
}
