package com.cybr406.account.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.event.ValidatingRepositoryEventListener;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.validation.Validator;
import org.springframework.web.filter.ForwardedHeaderFilter;

@Configuration
public class RestConfiguration implements RepositoryRestConfigurer {

    @Autowired
    private Validator validator;

    @Override
    public void configureValidatingRepositoryEventListener(ValidatingRepositoryEventListener validatingListener) {
        validatingListener.addValidator("beforeCreate", validator);
        validatingListener.addValidator("beforeSave", validator);
    }

    // Standardizes how application determines host name for responses (Uses Url from gateway)
    @Bean
    ForwardedHeaderFilter forwardedHeaderFilter()   {
        return new ForwardedHeaderFilter();
    }

}
