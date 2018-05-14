package github.eddy.datastructure;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

/**
 * @author Anddd7
 */
public class LimitedArrayQueueTest {

  LimitedArrayQueue<String> limitedArrayQueue = new LimitedArrayQueue<>(3);

  @Test
  public void pushAndPoll() {
    limitedArrayQueue.push("1");
    limitedArrayQueue.push("2");
    limitedArrayQueue.push("3");
    limitedArrayQueue.push("4");
    limitedArrayQueue.push("5");

    assertEquals("1", limitedArrayQueue.poll());
    assertEquals("2", limitedArrayQueue.poll());
    assertEquals("3", limitedArrayQueue.poll());
    assertEquals("4", limitedArrayQueue.poll());
    assertNull(limitedArrayQueue.poll());
  }

}