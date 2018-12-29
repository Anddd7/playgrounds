package com.github.anddd7.datastructure;

/**
 * @author Anddd7
 * 有限单向循环队列
 */
public class LimitedArrayQueue<E> {

  private final int capacity;
  private int size;
  private Object[] elements;
  private int pushIndex = -1;
  private int pollIndex = -1;

  public LimitedArrayQueue(int capacity) {
    this.capacity = Integer.highestOneBit(capacity) << 1;
    this.elements = new Object[this.capacity];
  }

  public void push(E e) {
    if (size == capacity) {
      return;
    }
    elements[pushIndex = (pushIndex + 1) & (capacity - 1)] = e;
    size++;
  }

  public E poll() {
    if (size == 0) {
      return null;
    }
    E value = (E) elements[pollIndex = (pollIndex + 1) & (capacity - 1)];
    size--;
    return value;
  }
}
