package com.github.anddd7.coroutine;

public class Continuation {

  private volatile boolean resumable;
  private Runnable runnable;

  public Continuation(Runnable block) {
    this(block, true);
  }

  public Continuation(Runnable block, boolean resumable) {
    this.runnable = block;
    this.resumable = resumable;
  }

  public boolean isResumable() {
    return resumable;
  }

  public Runnable getRunnable() {
    return runnable;
  }
}
