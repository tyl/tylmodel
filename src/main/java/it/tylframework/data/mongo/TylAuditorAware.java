package it.tylframework.data.mongo;

import it.tylframework.data.mongo.common.Signature;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

/**
 * Created by mp on 21/11/14.
 */
public class TylAuditorAware implements AuditorAware<Signature> {
    public Signature getCurrentAuditor(){
        return Signature.EmptySignature;
    }
}
