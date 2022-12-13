package com.etiya.ecommercedemopair7.core.utilities.messages;

import org.springframework.context.MessageSource;
import org.springframework.lang.Nullable;

import java.util.Locale;

public interface IMessageSourceService {
    String getMessage(String code);
}
