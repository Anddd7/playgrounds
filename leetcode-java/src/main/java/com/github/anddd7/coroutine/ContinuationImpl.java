package com.github.anddd7.coroutine;

public abstract class ContinuationImpl implements Continuation {

  private Continuation completion;
  private CoroutineDispatcher dispatcher;
  private boolean suspended;

  public void complete() {
    completion.resumeWith(this);
  }

  @Override
  public boolean isSuspended() {
    return suspended;
  }

  public void setSuspended(boolean suspended) {
    this.suspended = suspended;
  }

  public Continuation getCompletion() {
    return completion;
  }

  public void setCompletion(Continuation completion) {
    this.completion = completion;
  }

  public CoroutineDispatcher getDispatcher() {
    return dispatcher;
  }

  public void setDispatcher(CoroutineDispatcher dispatcher) {
    this.dispatcher = dispatcher;
  }
}
