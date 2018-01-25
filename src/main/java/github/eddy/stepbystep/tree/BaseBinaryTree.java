package github.eddy.stepbystep.tree;

/**
 * @author and777
 * @date 2018/1/25
 */
public abstract class BaseBinaryTree extends BaseTree implements BinaryTreePrint {

  BinaryNodeElement root;

  public BinaryNodeElement getRoot() {
    return root;
  }

  @Override
  public BinaryNodeElement find(char id) {
    return findElement(root, id);
  }

  @Override
  public BaseTree add(char id) {
    if (size == 0) {
      root = new BinaryNodeElement(id);
    } else {
      addElement(root, new BinaryNodeElement(id));
    }
    size++;
    return this;
  }

  /**
   * - 左右节点是空的 : 叶子节点 ,从树上移除
   * - 只有左/右节点 : 单链 ,直接跨过这个节点
   * - 左右节点都有 : 找左子树前驱(左边最大值) ,插入到删除的节点
   */
  @Override
  public BaseTree remove(char id) {
    BinaryNodeElement target = find(id);
    if (target != null) {
      removeElement(target);
      size--;
    }
    return this;
  }

  protected class BinaryNodeElement extends Element {

    BinaryNodeElement parent;
    BinaryNodeElement[] children;

    BinaryNodeElement(char id) {
      super(id);
      children = new BinaryNodeElement[2];
    }

    void setParent(BinaryNodeElement parent) {
      this.parent = parent;
    }

    void setLeftElement(BinaryNodeElement left) {
      children[0] = left;
      if (left != null) {
        left.setParent(this);
      }
    }

    void setRightElement(BinaryNodeElement right) {
      children[1] = right;
      if (right != null) {
        right.setParent(this);
      }
    }

    BinaryNodeElement getLeftElement() {
      return children[0];
    }

    BinaryNodeElement getRightElement() {
      return children[1];
    }

    boolean isLeftChild() {
      return parent.getLeftElement() == this;
    }

    void fastRemove() {
      if (isLeftChild()) {
        parent.setLeftElement(null);
      } else {
        parent.setRightElement(null);
      }
    }

    @Override
    public BinaryNodeElement getParent() {
      return parent;
    }

    @Override
    public BinaryNodeElement[] getChildren() {
      return children;
    }
  }

  public abstract BinaryNodeElement findElement(BinaryNodeElement parent, char id);

  public abstract void addElement(BinaryNodeElement parent, BinaryNodeElement target);

  public abstract void removeElement(BinaryNodeElement target);
}
