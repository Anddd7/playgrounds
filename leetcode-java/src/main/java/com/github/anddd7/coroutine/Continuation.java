package com.github.anddd7.coroutine;

import java.util.concurrent.CompletableFuture;

public abstract class Continuation {

  private CompletableFuture<Object> hook;
  private CoroutineDispatcher dispatcher;

  public Continuation() {
    hook = new CompletableFuture<>();
  }

  public abstract Continuation resumeWith();

  public void complete(Object result) {
    hook.complete(result);
  }

  public boolean isCompleted() {
    return isCompleted();
  }

  public CompletableFuture<Object> getHook() {
    return hook;
  }

  public void setDispatcher(CoroutineDispatcher dispatcher) {
    this.dispatcher = dispatcher;
  }

  public CoroutineDispatcher getDispatcher() {
    return dispatcher;
  }
}
