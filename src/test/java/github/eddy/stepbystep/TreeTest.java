package github.eddy.stepbystep;

import github.eddy.stepbystep.tree.impl.AVLTree;
import github.eddy.stepbystep.tree.impl.BinarySortTree;
import github.eddy.stepbystep.tree.impl.SimpleBinaryTree;
import github.eddy.stepbystep.tree.node.BaseElement;
import java.util.ArrayDeque;
import org.junit.Test;

/**
 * @author and777
 * @date 2018/1/24
 */
public class TreeTest {

  int calHeight(int num) {
    int time = 0;
    while (num > 0) {
      num >>= 1;
      time++;
    }
    return time;
  }

  @Test
  public void calHeight() {
    int size = 7;
    size |= size >>> 1;
    size |= size >>> 2;
    size |= size >>> 4;
    size |= size >>> 8;
    size |= size >>> 16;
    System.out.println(size);
    size++;

    System.out.println(calHeight(7));
    System.out.println(calHeight(size));
  }

  @Test
  public void fillNodeInMultiArray() {
    int size = 6;
    int height = 3;
    int[][] table = new int[height][(1 << height) - 1];

    int time = 0;

    ArrayDeque<Integer> deque = new ArrayDeque();
    deque.offer(0);
    deque.offer(table[0].length - 1);
    while (time < size) {
      int start = deque.poll();
      int end = deque.poll();

      int index = (start + end) >> 1;
      int layer = (calHeight(time + 1) - 1);
      System.out.println("start:" + start + "|index:" + index + "|end:" + end);
      System.out.println("time:" + time + "|layer:" + layer);

      table[layer][index] = 1;
      time++;

      if (start == end) {
        continue;
      }
      deque.offer(start);
      deque.offer(index - 1);
      deque.offer(index + 1);
      deque.offer(end);
    }

    for (int i = 0; i < table.length; i++) {
      for (int j = 0; j < table[i].length; j++) {
        if (table[i][j] == 0) {
          System.out.print(" ");
        } else {
          System.out.print(table[i][j]);
        }
      }
      System.out.println();
    }
  }

  @Test
  public void testBinaryTree() {
    SimpleBinaryTree tree = new SimpleBinaryTree();
    tree.add(1).add(2).add(6).add(2).add(8).add(5).add(5);
    tree.print();
    BaseElement node = tree.find(6);
    System.out.println(node.getParent());
    BaseElement[] children = node.getParent().getChildren();
    System.out.println(children[0]);
    System.out.println(children[1]);
  }

  @Test
  public void testBinarySortTree() {
    BinarySortTree tree = new BinarySortTree();
    tree.add(4).add(2).add(3).add(1).add(7).add(5).add(9);
    tree.getRoot();
    tree.print();
    tree.remove(1);
    tree.print();
    tree.remove(7);
    tree.print();
    tree.remove(4);
    tree.print();
  }

  @Test
  public void testAVLTree() {
    AVLTree tree = new AVLTree();
    tree.add(4)
        .add(2)
        .add(3)
        .add(1)
        .add(7)
        .add(5)
        .add(9);

    tree.getRoot();
    tree.print();
    tree.remove(1);
    tree.print();
    tree.remove(7);
    tree.print();
    tree.remove(4);
    tree.print();
    tree.remove(9);
    tree.print();
  }
}
