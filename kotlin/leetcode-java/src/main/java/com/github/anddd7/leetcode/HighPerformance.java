package com.github.anddd7.leetcode;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * timing > 90%
 */
@Target(ElementType.METHOD)
public @interface HighPerformance {

  String value() default "";
}