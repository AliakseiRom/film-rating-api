package com.example.FilmRatingApi.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Component
public class CustomAsyncExceptionHandler implements AsyncUncaughtExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(CustomAsyncExceptionHandler.class);


    @Override
    public void handleUncaughtException(Throwable ex, Method method, Object... params) {
        log.error("Async method '{}' threw an exception: '{}'", method.getName(), ex.getMessage(), ex);
    }
}
