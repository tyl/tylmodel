package it.tylframework.data.mongo.basics;

import it.tylframework.data.mongo.common.Footprint;

import java.util.Date;

/**
 * Created by marco on 24/08/14.
 */
public class Country extends Footprint {
    private String two_char_code;
    private String official_name;
    private Integer numeric_code;

    public Country(String two_char_code, String official_name, Integer numeric_code){
        super("marco",new Date(),"marco", new Date() );
        this.two_char_code=two_char_code;
        this.official_name=official_name;
        this.numeric_code=numeric_code;
    }

    public String getTwo_char_code() {
        return two_char_code;
    }

    public void setTwo_char_code(String two_char_code) {
        this.two_char_code = two_char_code;
    }

    public String getOfficial_name() {
        return official_name;
    }

    public void setOfficial_name(String official_name) {
        this.official_name = official_name;
    }

    public Integer getNumeric_code() {
        return numeric_code;
    }

    public void setNumeric_code(Integer numeric_code) {
        this.numeric_code = numeric_code;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Country)) return false;

        Country country = (Country) o;

        if (numeric_code != null ? !numeric_code.equals(country.numeric_code) : country.numeric_code != null)
            return false;
        if (official_name != null ? !official_name.equals(country.official_name) : country.official_name != null)
            return false;
        if (two_char_code != null ? !two_char_code.equals(country.two_char_code) : country.two_char_code != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = two_char_code != null ? two_char_code.hashCode() : 0;
        result = 31 * result + (official_name != null ? official_name.hashCode() : 0);
        result = 31 * result + (numeric_code != null ? numeric_code.hashCode() : 0);
        return result;
    }
}
