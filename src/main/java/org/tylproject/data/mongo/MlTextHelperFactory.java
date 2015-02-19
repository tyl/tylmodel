package org.tylproject.data.mongo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tylproject.data.mongo.common.MlText;
import org.tylproject.data.mongo.config.Context;
import org.tylproject.data.mongo.helpers.MlTextHelper;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by evacchi on 19/02/15.
 */
@Component
@Named("mlTextHelpers")
public class MlTextHelperFactory {
    @Inject
    @Named("tylContext")
    Context tylContext;

    public MlTextHelper of(MlText mlText) {
        MlTextHelper helper = new MlTextHelper(mlText);
        helper.setTylContext(tylContext);
        return helper;
    }
    public MlText mlTextOf(String currentText) {
        MlTextHelper helper = this.of(new MlText());
        helper.setCurrentText(currentText);
        return helper.getMlText();
    }

}
