package github.eddy.stepbystep.tree;

import github.eddy.stepbystep.tree.node.BinaryLinkedElement;
import java.util.ArrayDeque;
import java.util.Iterator;

/**
 * @author and777
 * @date 2018/1/25
 */
public abstract class BaseBinaryTree<T extends BinaryLinkedElement>
    extends BaseTree implements BinaryTreePrint {

  protected T root;

  public T getRoot() {
    return root;
  }

  @Override
  public T find(char id) {
    return findElement(root, id);
  }

  @Override
  public BaseTree add(char id) {
    addElement(root, id);
    size++;
    System.out.println("添加节点成功:" + id);
    return this;
  }

  @Override
  public BaseTree remove(char id) {
    if (removeElement(root, id)) {
      size--;
      System.out.println("删除节点成功:" + id);
    }
    return this;
  }

  @Override
  public int getHeight() {
    return root == null ? 0 : root.getHeight();
  }

  private T findElement(T current, char id) {
    if (current == null || current.getKey() == id) {
      return current;
    }
    return findElement(
        id < current.getKey() ? (T) current.getLeftChild() : (T) current.getRightChild(),
        id);
  }

  /**
   * 在子树中添加节点
   *
   * @param parent 子树的根节点
   * @param id     id值
   * @return true - 左叶子 ; false - 右叶子
   */
  public abstract boolean addElement(T parent, char id);

  /**
   * 在子树中删除节点
   *
   * @param parent 子树的根节点
   * @param id     id值
   * @return true - 成功 ; false - 失败
   */
  public abstract boolean removeElement(T parent, char id);



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
