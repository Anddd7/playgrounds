package com.github.anddd7.datastructure;

/**
 * 双向链表, 提供link/get功能
 */
class NodeLinkedList<E> {

  private Node first = null;
  private Node last = null;
  private int size = 0;

  boolean isEmpty() {
    return size == 0;
  }

  Node getFirst() {
    return first;
  }

  Node getLast() {
    return last;
  }

  void linkFirst(E e) {
    if (isEmpty()) {
      first = last = new Node(null, e, null);
    } else {
      first = first.addPrev(e);
    }
    size++;
  }

  void linkLast(E e) {
    if (isEmpty()) {
      first = last = new Node(null, e, null);
    } else {
      last = last.addNext(e);
    }
    size++;
  }

  E get(int index) {
    return getNode(index).value;
  }

  E remove(int index) {
    return getNode(index).remove();
  }

  private void checkIndex(int index) {
    if (index < 0 && index > size) {
      throw new IndexOutOfBoundsException();
    }
  }

  private Node getNode(int index) {
    checkIndex(index);

    int temp = index;
    Node node = first;
    while (--temp > 0) {
      node = node.next;
    }
    return node;
  }

  class Node {

    private Node prev;
    private E value;
    private Node next;

    Node(Node prev, E value, Node next) {
      this.prev = prev;
      this.value = value;
      this.next = next;
      if (prev != null) {
        prev.next = this;
      }
      if (next != null) {
        next.prev = this;
      }
    }

    private Node addNext(E e) {
      return new Node(this, e, this.next);
    }

    private Node addPrev(E e) {
      return new Node(this.prev, e, this);
    }

    E remove() {
      E value = this.value;
      if (this == first) {
        first = this.next;
      } else {
        this.prev.next = this.next;
      }
      if (this == last) {
        last = this.prev;
      } else {
        this.next.prev = this.prev;
      }
      clear();
      return value;
    }

    private void clear() {
      this.prev = null;
      this.value = null;
      this.next = null;
    }

    Node getPrev() {
      return prev;
    }

    E getValue() {
      return value;
    }

    Node getNext() {
      return next;
    }
  }
}
