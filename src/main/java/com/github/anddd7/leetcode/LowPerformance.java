package com.github.anddd7.leetcode;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * timing < 50%
 */
@Target(ElementType.METHOD)
public @interface LowPerformance {

  String value() default "";
}

