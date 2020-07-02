package com.github.anddd7.coroutine;

public abstract class Continuation {

  private volatile boolean resumable;

  public Continuation() {
    this(true);
  }

  public Continuation(boolean resumable) {
    this.resumable = resumable;
  }

  public void setResumable(boolean resumable) {
    this.resumable = resumable;
  }

  public boolean isResumable() {
    return resumable;
  }

  public Continuation resumeWith(Object parameters) {
    throw new UnsupportedOperationException("You have to override this via extending continuation");
  }
}
