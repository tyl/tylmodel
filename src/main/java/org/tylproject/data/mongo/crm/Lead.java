package org.tylproject.data.mongo.crm;

import lombok.Data;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;
import org.tylproject.data.mongo.party.PartyRole;
import org.tylproject.data.mongo.party.TelecomAddress;
import org.tylproject.data.mongo.party.TelecomAddressPurpose;
import org.tylproject.data.mongo.party.TelecomPhysicalType;

/**
 * Created by marco on 01/03/15.
 */
@Document(collection="crm_lead")
@TypeAlias("crm_Lead")
@Data
public class Lead extends PartyRole {
    String company;
    String designation;

    /**
     * givenName
     */
    public String getGivenName(){
        return this.getParty().getPerson().getGivenName();
    }

    public void setGivenName(String givenName){
        this.getParty().getPerson().setGivenName(givenName);
    }
    /**
     * middleName
     */
    public String getMiddleName(){
        return this.getParty().getPerson().getMiddleName();
    }

    public void setMiddleName(String middleName){
        this.getParty().getPerson().setGivenName(middleName);
    }

    /**
     * familyName
     */
    public String getFamilyName(){
        return this.getParty().getPerson().getFamilyName();
    }

    public void setFamilyName(String familyName){
        this.getParty().getPerson().setGivenName(familyName);
    }

    /**
     * primaryPhone
     */
    public String getPrimaryPhone(){
        String primaryPhone="";
        for(TelecomAddress address:this.getParty().getTelecomAddress()){
            if(address.getPurpose()== TelecomAddressPurpose.MAINBUSINESS && address.getPhysicalType()== TelecomPhysicalType.FIXED) {
                primaryPhone = address.getFormattedTelecomNumber();
                break;
            }
        }
        return primaryPhone;
    }

    // TODO - risolvere il problema della descomposizione del numero nei suoi componenti
    public void setPrimaryPhone(String primaryPhone){
        boolean exists=false;
        for(TelecomAddress address:this.getParty().getTelecomAddress()){
            if(address.getPurpose()== TelecomAddressPurpose.MAINBUSINESS && address.getPhysicalType()== TelecomPhysicalType.FIXED) {
                address.setNumber(primaryPhone);
                exists=true;
                break;
            }
        }
        if (!exists){
            TelecomAddress telAddress= new TelecomAddress();
            telAddress.setPurpose(TelecomAddressPurpose.MAINBUSINESS);
            telAddress.setPhysicalType(TelecomPhysicalType.FIXED);
            telAddress.setNumber(primaryPhone); //TODO - da rifinire
            this.getParty().getTelecomAddress().add(telAddress);
        }
    }

    /**
     * mobilePhone
     */
    public String getMobilePhone(){
        String primaryPhone="";
        for(TelecomAddress address:this.getParty().getTelecomAddress()){
            if(address.getPurpose()== TelecomAddressPurpose.MAINBUSINESS && address.getPhysicalType()== TelecomPhysicalType.CELLULAR) {
                primaryPhone = address.getFormattedTelecomNumber();
                break;
            }
        }
        return primaryPhone;
    }

    // TODO - risolvere il problema della descomposizione del numero nei suoi componenti
    public void setMobilePhone(String mobilePhone){
        boolean exists=false;
        for(TelecomAddress address:this.getParty().getTelecomAddress()){
            if(address.getPurpose()== TelecomAddressPurpose.MAINBUSINESS && address.getPhysicalType()== TelecomPhysicalType.CELLULAR) {
                address.setNumber(mobilePhone);
                exists = true;
                break;
            }
        }
        if (!exists){
            TelecomAddress telAddress= new TelecomAddress();
            telAddress.setPurpose(TelecomAddressPurpose.MAINBUSINESS);
            telAddress.setPhysicalType(TelecomPhysicalType.CELLULAR);
            telAddress.setNumber(mobilePhone); //TODO - da rifinire
            this.getParty().getTelecomAddress().add(telAddress);
        }
    }

}
