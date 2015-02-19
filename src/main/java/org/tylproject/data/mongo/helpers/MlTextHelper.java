package org.tylproject.data.mongo.helpers;

import org.tylproject.data.mongo.common.LangKey;
import org.tylproject.data.mongo.common.MlText;
import org.tylproject.data.mongo.config.Context;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by evacchi on 19/02/15.
 */
public class MlTextHelper {
    private final MlText mlText;
    @Inject @Named("tylContext")
    private Context tylContext;


    public MlTextHelper(MlText mlText) {
        this.mlText = mlText;
    }
    public MlTextHelper(String currentText) {
        this(new MlText());
        this.setCurrentText(currentText);
    }

    public void setTylContext(Context tylContext) {
        this.tylContext = tylContext;
    }

    public Context getTylContext() {
        return tylContext;
    }

    public MlText getMlText() {
        return mlText;
    }

    /**
     * return, cascading, the text in current language,
     * or text in default language, or empty string
     */
    public String getCurrentText(){
        assertTylContextIsSet();
        return getText(getTylContext().currentLanguage());
    }

    public boolean isCurrentTextEmpty() {
        return mlText.getText(getTylContext().currentLanguage()) == null;
    }

    /**
     * return, cascading, the text in the given language,
     * or text in default language, or empty string
     */
    public String getText(LangKey lang) {

        String localizedText = mlText.getText(lang);
        if (isTextEmpty(localizedText)) {
            assertTylContextIsSet();

            localizedText = mlText.getText(tylContext.defaultLanguage());
            if (isTextEmpty(localizedText)) {
                localizedText = "";
            }
        }

        return localizedText;
    }


    public void setCurrentText(String text){
        assertTylContextIsSet();
        mlText.setText(getTylContext().currentLanguage(), text);
    }


    private void assertTylContextIsSet() {
        if (getTylContext() == null) {
            throw new IllegalStateException("No tylContext, cannot infer currentLanguage");
        }
    }

    private boolean isTextEmpty(String text) {
        return text == null || text.isEmpty();
    }


}
