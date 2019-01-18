package com.github.anddd7.leetcode;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;


/**
 * 50% < timing < 90%
 */
@Target(ElementType.METHOD)
public @interface NormalPerformance {

  String value() default "";
}
