package org.tylproject.data.mongo.config;

import org.tylproject.data.mongo.common.LangKey;
import org.tylproject.data.mongo.common.Signature;

/**
 * Created by evacchi on 04/02/15.
 */
public interface Context {


    public Signature currentUser();
    public void setCurrentUser(Signature signature);

    public LangKey currentLanguage();
    public void setCurrentLanguage(LangKey language);

    public LangKey defaultLanguage();
    public void setDefaultLanguage(LangKey language);
}
