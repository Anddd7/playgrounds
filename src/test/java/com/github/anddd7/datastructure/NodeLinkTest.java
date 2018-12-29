package com.github.anddd7.datastructure;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.github.anddd7.datastructure.NodeLinkList.Node;
import org.junit.jupiter.api.Test;

/**
 * @author Anddd7
 */
class NodeLinkTest {

  private NodeLinkList<Integer> nodeLink = new NodeLinkList<>();

  @Test
  void linkFirst() {
    nodeLink.linkFirst(1);
    nodeLink.linkFirst(2);
    nodeLink.linkFirst(3);

    Node first = nodeLink.getFirst();
    assertEquals(3, first.getValue());
    first = first.getNext();
    assertEquals(2, first.getValue());
    first = first.getNext();
    assertEquals(1, first.getValue());

    Node last = nodeLink.getLast();
    assertEquals(1, last.getValue());
    last = last.getPrev();
    assertEquals(2, last.getValue());
    last = last.getPrev();
    assertEquals(3, last.getValue());
  }

  @Test
  public void linkLast() {
    nodeLink.linkLast(1);
    nodeLink.linkLast(2);
    nodeLink.linkLast(3);

    Node first = nodeLink.getFirst();
    assertEquals(1, first.getValue());
    first = first.getNext();
    assertEquals(2, first.getValue());
    first = first.getNext();
    assertEquals(3, first.getValue());

    Node last = nodeLink.getLast();
    assertEquals(3, last.getValue());
    last = last.getPrev();
    assertEquals(2, last.getValue());
    last = last.getPrev();
    assertEquals(1, last.getValue());
  }

  @Test
  void remove() {
    nodeLink.linkLast(1);
    nodeLink.linkLast(2);
    nodeLink.linkLast(3);

    Node first = nodeLink.getFirst();
    assertEquals(1, first.getValue());
    first = first.getNext();
    assertEquals(2, first.remove());

    Node last = nodeLink.getLast();
    assertEquals(3, last.getValue());
    last = last.getPrev();
    assertEquals(1, last.getValue());
  }

}