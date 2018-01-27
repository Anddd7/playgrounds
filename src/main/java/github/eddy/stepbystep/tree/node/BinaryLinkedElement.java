package github.eddy.stepbystep.tree.node;

/**
 * @author and777
 * @date 2018/1/27
 */
public class BinaryLinkedElement extends BaseElement {

  protected BinaryLinkedElement parent;
  protected BinaryLinkedElement[] children;
  protected boolean isLeftChildOfParent = true;

  public BinaryLinkedElement(char id) {
    this.id = id;
    this.children = new BinaryLinkedElement[2];
  }

  @Override
  public BinaryLinkedElement getParent() {
    return parent;
  }

  @Override
  public BinaryLinkedElement[] getChildren() {
    return children;
  }

  public void setLeftChild(BinaryLinkedElement left) {
    children[0] = left;
    if (left != null) {
      left.parent = this;
      left.isLeftChildOfParent = true;
    }
  }

  public void setRightChild(BinaryLinkedElement right) {
    children[1] = right;
    if (right != null) {
      right.parent = this;
      right.isLeftChildOfParent = false;
    }
  }

  public BinaryLinkedElement getLeftChild() {
    return children[0];
  }

  public BinaryLinkedElement getRightChild() {
    return children[1];
  }

  public boolean isLeftChildOfParent() {
    return this.isLeftChildOfParent;
  }

  /**
   * remove connection with parent
   */
  public void fastRemove() {
    if (isLeftChildOfParent()) {
      parent.setLeftChild(null);
    } else {
      parent.setRightChild(null);
    }
    parent = null;
  }

  /**
   * 计算当前节点的高度
   */
  public int getHeight() {
    int depth = 0;
    if (this.getLeftChild() != null) {
      depth = Math.max(depth, this.getLeftChild().getHeight());
    }
    if (this.getRightChild() != null) {
      depth = Math.max(depth, this.getRightChild().getHeight());
    }
    return depth + 1;
  }


  public int getLeftHeight() {
    if (getLeftChild() == null) {
      return 0;
    }
    return getLeftChild().getHeight();
  }

  public int getRightHeight() {
    if (getRightChild() == null) {
      return 0;
    }
    return getRightChild().getHeight();
  }

  public void setParent(BinaryLinkedElement parent) {
    this.parent = parent;
  }

  public void setChildren(BinaryLinkedElement[] children) {
    this.children = children;
  }
}