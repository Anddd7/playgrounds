package com.github.anddd7.datastructure;

import java.util.Arrays;

/**
 * 存放integer的小顶堆
 */
public class SimpleMinHeap {

  private int[] array;
  private int size;

  public SimpleMinHeap(int capacity) {
    this.array = new int[capacity];
  }

  private boolean isFull() {
    return size == array.length;
  }

  private boolean isEmpty() {
    return size == 0;
  }

  public void add(int item) {
    assert !isFull();
    array[size] = item;
    shiftUp(size);
    size++;
  }

  private void shiftUp(int index) {
    if (index == 0) {
      return;
    }
    int item = array[index];
    int parentIndex = (index - 1) >> 1;
    int parent = array[parentIndex];
    if (item < parent) {
      array[parentIndex] = item;
      array[index] = parent;
      shiftUp(parentIndex);
    }
  }

  public int poll() {
    int item = array[0];
    array[0] = array[size];
    size--;
    shiftDown(0);
    return item;
  }

  private void shiftDown(int index) {
    if (index == size - 1) {
      return;
    }
    int item = array[index];
    int leftIndex = index << 1 + 1;
    int rightIndex = index << 1 + 2;

    if (leftIndex < size) {
      if (rightIndex < size && array[leftIndex] > array[rightIndex]) {
        array[index] = array[rightIndex];
        array[rightIndex] = item;
        shiftDown(rightIndex);
      } else {
        array[index] = array[leftIndex];
        array[leftIndex] = item;
        shiftDown(leftIndex);
      }
    }
  }

  public int peek() {
    return array[0];
  }

  @Override
  public String toString() {
    return Arrays.toString(array);
  }
}
