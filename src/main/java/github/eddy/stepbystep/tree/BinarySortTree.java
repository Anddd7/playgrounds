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
  public void addElement(BinaryNodeElement parent, BinaryNodeElement target) {
    if (parent.compareTo(target) > 0) {
      //left
      BinaryNodeElement element = parent.getLeftElement();
      if (element == null) {
        parent.setLeftElement(target);
      } else {
        addElement(element, target);
      }
    } else {
      //right
      BinaryNodeElement element = parent.getRightElement();
      if (element == null) {
        parent.setRightElement(target);
      } else {
        addElement(element, target);
      }
    }
  }

  @Override
  public void removeElement(BinaryNodeElement target) {
    if (target.getLeftElement() == null && target.getRightElement() == null) {
      if (target == root) {
        root = null;
      } else {
        target.fastRemove();
      }
      return;
    }

    if (target.getLeftElement() == null ^ target.getRightElement() == null) {
      BinaryNodeElement singleChild =
          target.getLeftElement() != null ? target.getLeftElement() : target.getRightElement();

      if (target == root) {
        root = singleChild;
      } else if (target.isLeftChild()) {
        target.parent.setLeftElement(singleChild);
      } else {
        target.parent.setRightElement(singleChild);
      }
      return;
    }

    //找左子树的最大值(右子树最小值) , 一定是一个叶子节点 , 插入到删除的点
    BinaryNodeElement leftMax = target.getLeftElement();
    while (leftMax.getRightElement() != null) {
      leftMax = leftMax.getRightElement();
    }
    //remove 叶子
    leftMax.fastRemove();
    //设置父->新的节点
    if (target == root) {
      root = leftMax;
    } else if (target.isLeftChild()) {
      target.parent.setLeftElement(leftMax);
    } else {
      target.parent.setRightElement(leftMax);
    }
    //设置节点->原有的子节点
    leftMax.setLeftElement(target.getLeftElement());
    leftMax.setRightElement(target.getRightElement());
  }

  /**
   * 打印函数针对 完全二叉树 ,利用广度优先 ,打印每一个节点
   * 但是二叉搜索树并不是完全的 ,因此在实现的迭代器中需要补全没有的节点(占位)
   * 补全后的树作为打印对象
   */
  @Override
  public void print() {
    //计算补全后的节点数
    print((1 << getDepth(root)) - 1);
  }

  /**
   * 计算当前节点的深度
   */
  private int getDepth(BinaryNodeElement element) {
    if (element == null) {
      return 0;
    }
    int depth = 0;
    if (element.getLeftElement() != null) {
      depth = Math.max(depth, getDepth(element.getLeftElement()));
    }
    if (element.getRightElement() != null) {
      depth = Math.max(depth, getDepth(element.getRightElement()));
    }
    return depth + 1;
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
