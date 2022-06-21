package com.github.anddd7.coroutine.continuation;

import com.github.anddd7.coroutine.Continuation;
import com.github.anddd7.coroutine.ContinuationImpl;

/**
 * use scheduled thread to get higher performance
 */
public class DelayContinuation extends ContinuationImpl {

  private final int time = 5000;
  private int state = 0;

  @Override
  public Continuation resumeWith(Continuation continuation) {
    switch (state) {
      case 0:
       /* try {
          Thread.sleep(time);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }*/
        state += 1;
        return this;
      default:
        getCompletion().resumeWith(this);
        return null;
    }
  }

  @Override
  public Object getResult() {
    return time;
  }
}
