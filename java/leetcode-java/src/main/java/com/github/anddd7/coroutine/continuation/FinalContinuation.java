package com.github.anddd7.coroutine.continuation;

import com.github.anddd7.coroutine.Continuation;
import java.util.concurrent.CompletableFuture;

public class FinalContinuation implements Continuation {

  private final CompletableFuture<Object> hook;

  public FinalContinuation() {
    hook = new CompletableFuture<>();
  }

  @Override
  public Continuation resumeWith(Continuation continuation) {
    hook.complete(continuation.getResult());
    return null;
  }

  @Override
  public boolean isSuspended() {
    return false;
  }

  @Override
  public Object getResult() {
    return "Completed";
  }

  public CompletableFuture<Object> getHook() {
    return hook;
  }
}
