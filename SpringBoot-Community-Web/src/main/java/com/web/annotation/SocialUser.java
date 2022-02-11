package com.web.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// State this is parameter
@Target(ElementType.PARAMETER)
// Retain this is for runtime
@Retention(RetentionPolicy.RUNTIME)
// To make an annotation, add @ prefix to the interface
public @interface SocialUser {
}