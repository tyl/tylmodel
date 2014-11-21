package it.tylframework.data.mongo;

import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

/**
 * Created by mp on 21/11/14.
 */
@Component
public class TylAuditorAware implements AuditorAware<SecurityProperties.User> {
    public SecurityProperties.User getCurrentAuditor(){
        return new SecurityProperties.User();
    }
}
