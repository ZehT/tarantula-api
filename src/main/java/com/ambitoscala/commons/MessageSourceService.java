package com.ambitoscala.commons;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class MessageSourceService {

    private final MessageSource messageSource;

    public MessageSourceService(final MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public String getMessage(final String key) {
        return this.messageSource.getMessage(key, null, Locale.getDefault());
    }

    public String getMessage(final String key, final String param) {
        return this.messageSource.getMessage(key, new Object[]{param}, Locale.getDefault());
    }

}
