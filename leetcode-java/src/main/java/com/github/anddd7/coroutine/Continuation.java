package com.github.anddd7.coroutine;

public interface Continuation {

  Continuation resumeWith(Continuation continuation);

  boolean isSuspended();

  Object getResult();
}
