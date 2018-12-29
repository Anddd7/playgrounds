package com.github.anddd7.datastructure;

import java.util.Objects;

/**
 * @author Anddd7
 * 双向链表, 提供link/get功能
 */
public class NodeLinkList<E> {

  private Node first;
  private Node last;

  public Node getFirst() {
    return first;
  }

  public Node getLast() {
    return last;
  }

  public boolean isEmpty() {
    return Objects.isNull(first);
  }

  public void linkFirst(E e) {
    if (isEmpty()) {
      first = last = new Node(null, e, null);
    } else {
      first = first.addPrev(e);
    }
  }

  public void linkLast(E e) {
    if (isEmpty()) {
      first = last = new Node(null, e, null);
    } else {
      last = last.addNext(e);
    }
  }

  class Node {

    private Node prev;
    private E value;
    private Node next;

    Node(Node prev, E value, Node next) {
      this.prev = prev;
      this.value = value;
      this.next = next;
    }

    private Node addNext(E e) {
      Node nextNode = new Node(this, e, this.next);
      this.next = nextNode;
      return nextNode;
    }

    private Node addPrev(E e) {
      Node prevNode = new Node(this.prev, e, this);
      this.prev = prevNode;
      return prevNode;
    }

    public E remove() {
      E value = this.value;
      if (this.equals(first)) {
        first = this.next;
      } else {
        this.prev.next = this.next;
      }
      if (this.equals(last)) {
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

    public Node getPrev() {
      return prev;
    }

    public E getValue() {
      return value;
    }

    public Node getNext() {
      return next;
    }
  }
}
