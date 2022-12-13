package com.etiya.ecommercedemopair7;

import com.etiya.ecommercedemopair7.core.utilities.exceptions.BusinessException;
import com.etiya.ecommercedemopair7.core.utilities.results.ErrorDataResult;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@SpringBootApplication
@RestControllerAdvice
public class ECommerceDemoPair7Application {

    public static void main(String[] args) {
        SpringApplication.run(ECommerceDemoPair7Application.class, args);
    }

    @Bean
    public ModelMapper getModelMapper() {
        return new ModelMapper();
    }

    @Bean
    public ResourceBundleMessageSource bundleMessageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("messages");
        return messageSource;
    }

    @Bean
    public LocaleResolver localeResolver() {
        AcceptHeaderLocaleResolver localeResolver = new AcceptHeaderLocaleResolver();
        localeResolver.setDefaultLocale(Locale.US);
        return localeResolver;
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDataResult<Object> handleValidationException(MethodArgumentNotValidException methodArgumentNotValidException) {
        Map<String, String> validationErrors = new HashMap<>();

        for (FieldError fieldError : methodArgumentNotValidException.getBindingResult().getFieldErrors()) {
            validationErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }

        return new ErrorDataResult<>(validationErrors, "VALIDATION_EXCEPTION");
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDataResult<Object> handleBusinessException(BusinessException exception) {
        ErrorDataResult<Object> businessErrors = new ErrorDataResult<>(exception.getMessage(), "BUSINESS_EXCEPTION");
        return businessErrors;
    }
}
