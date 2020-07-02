package com.github.anddd7.coroutine;

public abstract class Continuation {

  public Continuation() {
  }
  public Continuation resumeWith() {
    throw new UnsupportedOperationException("You have to override this via extending continuation");
  }
}
