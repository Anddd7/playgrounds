package github.eddy.stepbystep.tree;

import java.util.ArrayDeque;
import java.util.Iterator;

/**
 * @author and777
 * @date 2018/1/25
 *
 * 二叉搜索树
 */
public class BinarySortTree extends BaseBinaryTree {

  @Override
  public BinaryNodeElement findElement(BinaryNodeElement parent, char id) {
    if (parent == null || parent.id == id) {
      return parent;
    }
    return findElement(id < parent.id ? parent.getLeftElement() : parent.getRightElement(), id);
  }

  @Override
  public boolean addElement(BinaryNodeElement parent, BinaryNodeElement target) {
    if (parent.compareTo(target) > 0) {
      //left
      BinaryNodeElement element = parent.getLeftElement();
      if (element == null) {
        parent.setLeftElement(target);
        return true;
      }

      return addElement(element, target);
    }

    //right
    BinaryNodeElement element = parent.getRightElement();
    if (element == null) {
      parent.setRightElement(target);
      return false;
    }

    return addElement(element, target);
  }

  /**
   * - 左右节点是空的 : 叶子节点 ,从树上移除
   * - 只有左/右节点 : 单链 ,直接跨过这个节点
   * - 左右节点都有 : 找左子树前驱(左边最大值) ,插入到删除的节点
   */
  @Override
  public boolean removeElement(BinaryNodeElement parent, char id) {
    if (parent == null) {
      return false;
    }

    if (parent.id != id) {
      return removeElement(id < parent.id ? parent.getLeftElement() : parent.getRightElement(), id);
    }

    if (parent.getLeftElement() == null && parent.getRightElement() == null) {
      if (parent == root) {
        root = null;
      } else {
        parent.fastRemove();
      }
    } else if (parent.getLeftElement() == null ^ parent.getRightElement() == null) {
      BinaryNodeElement singleChild =
          parent.getLeftElement() != null ? parent.getLeftElement() : parent.getRightElement();

      if (parent == root) {
        root = singleChild;
      } else if (parent.isLeftChild()) {
        parent.parent.setLeftElement(singleChild);
      } else {
        parent.parent.setRightElement(singleChild);
      }
    } else {
      //找左子树的最大值(右子树最小值) , 一定是一个叶子节点 , 插入到删除的点
      BinaryNodeElement leftMax = parent.getLeftElement();
      while (leftMax.getRightElement() != null) {
        leftMax = leftMax.getRightElement();
      }
      //remove 叶子
      leftMax.fastRemove();
      //设置父->新的节点
      if (parent == root) {
        root = leftMax;
      } else if (parent.isLeftChild()) {
        parent.parent.setLeftElement(leftMax);
      } else {
        parent.parent.setRightElement(leftMax);
      }
      //设置节点->原有的子节点
      leftMax.setChildren(parent.children);
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
    ArrayDeque<BinaryNodeElement> deque = new ArrayDeque<>(size);

    public NodeIterator() {
      deque.offer(root);
    }

    @Override
    public boolean hasNext() {
      return pos != size;
    }

    @Override
    public Character next() {
      BinaryNodeElement current = deque.poll();
      BinaryNodeElement nextLeft = current.getLeftElement();
      BinaryNodeElement nextRight = current.getRightElement();
      deque.offer(nextLeft != null ? nextLeft : new BinaryNodeElement((char) 0));
      deque.offer(nextRight != null ? nextRight : new BinaryNodeElement((char) 0));

      if (current.id != 0) {
        pos++;
      }
      return current.id;
    }
  }
}
