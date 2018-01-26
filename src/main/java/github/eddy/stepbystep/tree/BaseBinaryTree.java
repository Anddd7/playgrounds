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

  protected class BinaryNodeElement extends Element {

    BinaryNodeElement parent;
    BinaryNodeElement[] children;

    BinaryNodeElement(char id) {
      super(id);
      children = new BinaryNodeElement[2];
    }

    private void setParent(BinaryNodeElement parent) {
      this.parent = parent;
    }

    public void setChildren(BinaryNodeElement[] children) {
      this.children = children;
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

    /**
     * remove connection with parent
     */
    void fastRemove() {
      if (isLeftChild()) {
        parent.setLeftElement(null);
      } else {
        parent.setRightElement(null);
      }
      parent = null;
    }

    /**
     * 从根节点倒序记录各节点深度
     */
    int getDepth() {
      if (parent == null) {
        return 0;
      }
      return parent.getDepth() + 1;
    }

    /**
     * 计算当前节点的高度
     */
    int getHeight() {
      int depth = 0;
      if (this.getLeftElement() != null) {
        depth = Math.max(depth, this.getLeftElement().getHeight());
      }
      if (this.getRightElement() != null) {
        depth = Math.max(depth, this.getRightElement().getHeight());
      }
      return depth + 1;
    }


    int getLeftHeight() {
      if (getLeftElement() == null) {
        return 0;
      }
      return getLeftElement().getHeight();
    }

    int getRightHeight() {
      if (getRightElement() == null) {
        return 0;
      }
      return getRightElement().getHeight();
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

  /**
   * 在子树中寻找节点
   *
   * @param parent 子树的根节点
   * @param id     id值
   */
  public abstract BinaryNodeElement findElement(BinaryNodeElement parent, char id);

  /**
   * 在子树中添加节点
   *
   * @param parent 子树的根节点
   * @param target 子节点
   * @return true - 左叶子 ; false - 右叶子
   */
  public abstract boolean addElement(BinaryNodeElement parent, BinaryNodeElement target);

  /**
   * 在子树中删除节点
   *
   * @param parent 子树的根节点
   * @param id     id值
   * @return true - 成功 ; false - 失败
   */
  public abstract boolean removeElement(BinaryNodeElement parent, char id);
}
