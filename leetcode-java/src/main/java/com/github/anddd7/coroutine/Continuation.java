package com.github.anddd7.coroutine;

import java.util.concurrent.CompletableFuture;

public abstract class Continuation {

  private CompletableFuture<Object> hook;

  public Continuation() {
    hook = new CompletableFuture<>();
  }

  public Continuation resumeWith() {
    throw new UnsupportedOperationException("You have to override this via extending continuation");
  }

  public boolean isCompleted() {
    return hook.isDone();
  }

  public void complete(Object result) {
    hook.complete(result);
  }

  public CompletableFuture<Object> getHook() {
    return hook;
  }
}
