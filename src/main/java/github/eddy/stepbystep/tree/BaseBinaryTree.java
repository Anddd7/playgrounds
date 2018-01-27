package github.eddy.stepbystep.tree;

import github.eddy.stepbystep.tree.node.BinaryLinkedElement;

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

  /**
   * 在子树中寻找节点
   *
   * @param parent 子树的根节点
   * @param id     id值
   */
  public abstract T findElement(T parent, char id);

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
}
