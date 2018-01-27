package github.eddy.stepbystep.tree.impl;

import github.eddy.stepbystep.tree.BaseBinaryTree;
import github.eddy.stepbystep.tree.node.BinaryLinkedElement;
import java.util.ArrayDeque;
import java.util.Iterator;

/**
 * @author and777
 * @date 2018/1/25
 *
 * 二叉搜索树
 */
public class BinarySortTree extends BaseBinaryTree<BinaryLinkedElement> {

  @Override
  public BinaryLinkedElement findElement(BinaryLinkedElement current, char id) {
    if (current == null || current.getKey() == id) {
      return current;
    }
    return findElement(
        id < current.getKey() ? current.getLeftChild() : current.getRightChild(),
        id);
  }

  @Override
  public boolean addElement(BinaryLinkedElement current, char id) {
    if (root == null) {
      root = new BinaryLinkedElement(id);
      return true;
    }

    if (id < current.getKey()) {
      //left
      BinaryLinkedElement leftChild = current.getLeftChild();
      if (leftChild == null) {
        current.setLeftChild(new BinaryLinkedElement(id));
        return true;
      }

      return addElement(leftChild, id);
    }

    //right
    BinaryLinkedElement rightChild = current.getRightChild();
    if (rightChild == null) {
      current.setRightChild(new BinaryLinkedElement(id));
      return false;
    }

    return addElement(rightChild, id);
  }

  /**
   * - 左右节点是空的 : 叶子节点 ,从树上移除
   * - 只有左/右节点 : 单链 ,直接跨过这个节点
   * - 左右节点都有 : 找左子树前驱(左边最大值) ,插入到删除的节点
   */
  @Override
  public boolean removeElement(BinaryLinkedElement current, char id) {
    //not find
    if (current == null) {
      return false;
    }

    //find in sub tree
    if (current.getKey() != id) {
      return removeElement(
          id < current.getKey() ? current.getLeftChild() : current.getRightChild(),
          id);
    }

    //current.key == id
    if (current.getLeftChild() == null && current.getRightChild() == null) {
      if (current == root) {
        root = null;
      } else {
        current.fastRemove();
      }
    } else if (current.getLeftChild() == null ^ current.getRightChild() == null) {
      BinaryLinkedElement singleChild =
          current.getLeftChild() != null ? current.getLeftChild() : current.getRightChild();

      if (current == root) {
        root = singleChild;
      } else if (current.isLeftChildOfParent()) {
        current.getParent().setLeftChild(singleChild);
      } else {
        current.getParent().setRightChild(singleChild);
      }
    } else {
      //找左子树的最大值(右子树最小值) , 一定是一个叶子节点 , 插入到删除的点
      BinaryLinkedElement leftMax = current.getLeftChild();
      while (leftMax.getRightChild() != null) {
        leftMax = leftMax.getRightChild();
      }
      //remove 叶子
      leftMax.fastRemove();
      //设置父->新的节点
      if (current == root) {
        root = leftMax;
      } else if (current.isLeftChildOfParent()) {
        current.getParent().setLeftChild(leftMax);
      } else {
        current.getParent().setRightChild(leftMax);
      }
      //设置节点->原有的子节点
      leftMax.setChildren(current.getChildren());
    }
    return true;
  }

  /**
   * 打印函数针对 完全二叉树 ,利用广度优先 ,打印每一个节点
   * 但是二叉搜索树并不是完全的 ,因此在实现的迭代器中需要补全没有的节点(占位)
   * 补全后的树作为打印对象
   */
  @Override
  public void print() {
    //计算补全后的节点数
    print((1 << root.getHeight()) - 1);
  }

  @Override
  public Iterator<Character> iterator() {
    return new NodeIterator();
  }

  class NodeIterator implements Iterator<Character> {

    int pos = 0;
    ArrayDeque<BinaryLinkedElement> deque = new ArrayDeque<>(size);

    public NodeIterator() {
      deque.offer(root);
    }

    @Override
    public boolean hasNext() {
      return pos != size;
    }

    @Override
    public Character next() {
      BinaryLinkedElement current = deque.poll();
      BinaryLinkedElement nextLeft = current.getLeftChild();
      BinaryLinkedElement nextRight = current.getRightChild();
      deque.offer(nextLeft != null ? nextLeft : new BinaryLinkedElement((char) 0));
      deque.offer(nextRight != null ? nextRight : new BinaryLinkedElement((char) 0));

      if (current.getKey() != 0) {
        pos++;
      }
      return current.getKey();
    }
  }
}
