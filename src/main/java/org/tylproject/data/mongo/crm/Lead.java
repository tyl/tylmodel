package org.tylproject.data.mongo.crm;

import lombok.Data;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;
import org.tylproject.data.mongo.basics.Industry;
import org.tylproject.data.mongo.common.Amount;
import org.tylproject.data.mongo.common.Image;
import org.tylproject.data.mongo.common.MlText;
import org.tylproject.data.mongo.crm.lookup.Leadsource;
import org.tylproject.data.mongo.crm.lookup.Leadstatus;
import org.tylproject.data.mongo.crm.lookup.Rating;
import org.tylproject.data.mongo.party.*;

/**
 * Created by marco on 01/03/15.
 *
 */
@Document(collection="crm_lead")
@TypeAlias("crm_Lead")
@Data
public class Lead extends PartyRole {
    String company;
    Leadsource leadSource;
    Industry industry;
    //Amount annualRevenue; // TODO - fare il converter
    Integer annualRevenue;
    Leadstatus leadStatus;
    Integer numberOfEmployees;
    Rating rating;
    Boolean emailOptOut;
    Image image;

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
     * honorific
     */

    public String getHonorific(){return this.getParty().getPerson().getHonorific();}

    public void setHonorific(String honorific)  {this.getParty().getPerson().setHonorific(honorific);}

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

    /**
     * fax
     */
    public String getFax(){
        String fax="";
        for(TelecomAddress address:this.getParty().getTelecomAddress()){
            if(address.getPurpose()== TelecomAddressPurpose.MAINBUSINESS && address.getPhysicalType()== TelecomPhysicalType.FAX) {
                fax = address.getFormattedTelecomNumber();
                break;
            }
        }
        return fax;
    }

    // TODO - risolvere il problema della descomposizione del numero nei suoi componenti
    public void setFax(String fax) {
        boolean exists = false;
        for (TelecomAddress address : this.getParty().getTelecomAddress()) {
            if (address.getPurpose() == TelecomAddressPurpose.MAINBUSINESS && address.getPhysicalType() == TelecomPhysicalType.FAX) {
                address.setNumber(fax);
                exists = true;
                break;
            }
        }
        if (!exists) {
            TelecomAddress telAddress = new TelecomAddress();
            telAddress.setPurpose(TelecomAddressPurpose.MAINBUSINESS);
            telAddress.setPhysicalType(TelecomPhysicalType.FAX);
            telAddress.setNumber(fax); //TODO - da rifinire
            this.getParty().getTelecomAddress().add(telAddress);
        }
    }


        /**
         * website
         */
    public String getWebsite(){
        String website="";
        for(WebAddress address:this.getParty().getWebAddress()){
            if(address.getWebAddressType()== WebAddressType.WEBSITE) {
                website = address.getWebAddress();
                break;
            }
        }
        return website;
    }

    public void setWebSite(String webSite){
        boolean exists=false;
        for(WebAddress address:this.getParty().getWebAddress()){
            if(address.getWebAddressType()== WebAddressType.WEBSITE) {
                address.setWebAddress(webSite);
                exists = true;
                break;
            }
        }
        if (!exists){
            WebAddress webAddress= new WebAddress();
            webAddress.setWebAddressType(WebAddressType.WEBSITE);
            webAddress.setWebAddress(webSite);
            this.getParty().getWebAddress().add(webAddress);
        }
    }

    /**
     * twitter
     */
    public String getTwitter(){
        String twitter="";
        for(WebAddress address:this.getParty().getWebAddress()){
            if(address.getWebAddressType()== WebAddressType.TWITTER) {
                twitter = address.getWebAddress();
                break;
            }
        }
        return twitter;
    }

    public void setTwitter(String twitter){
        boolean exists=false;
        for(WebAddress address:this.getParty().getWebAddress()){
            if(address.getWebAddressType()== WebAddressType.TWITTER) {
                address.setWebAddress(twitter);
                exists = true;
                break;
            }
        }
        if (!exists){
            WebAddress webAddress= new WebAddress();
            webAddress.setWebAddressType(WebAddressType.TWITTER);
            webAddress.setWebAddress(twitter);
            this.getParty().getWebAddress().add(webAddress);
        }
    }

}
