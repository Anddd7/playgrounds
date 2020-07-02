package com.github.anddd7.coroutine;

public class DelayContinuation extends Continuation {

  private int state = 0;

  @Override
  public Continuation resumeWith() {
    switch (state) {
      case 0:
        try {
          Thread.sleep(5000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        state += 1;
        return this;
      default:
        complete("done");
        return null;
    }
  }
}
