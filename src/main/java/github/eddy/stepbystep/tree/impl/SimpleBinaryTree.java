package github.eddy.stepbystep.tree.impl;

import github.eddy.stepbystep.tree.BaseTree;
import github.eddy.stepbystep.tree.BinaryTreePrint;
import github.eddy.stepbystep.tree.node.BaseElement;
import java.util.Iterator;

/**
 * @author and777
 * @date 2018/1/24
 *
 * 完全二叉树
 * - 基于数组(广度优先排布)
 */
public final class SimpleBinaryTree extends BaseTree implements BinaryTreePrint {

  private BaseElement[] tree;

  @Override
  public BaseElement find(char id) {
    for (int i = 0; i < size; i++) {
      if (tree[i].getKey() == id) {
        return tree[i];
      }
    }
    return null;
  }

  @Override
  public BaseTree add(char id) {
    if (tree == null) {
      tree = new ArrayElement[10];
    }
    //扩容
    if (size == tree.length) {
      grow();
    }
    tree[size++] = new ArrayElement(id, size);
    return this;
  }

  @Override
  public BaseTree remove(char id) {
    for (int i = 0; i < size; i++) {
      if (tree[i].getKey() == id) {
        System.arraycopy(tree, i + 1, tree, i, --size - i);
      }
    }
    return this;
  }

  @Override
  public int getHeight() {
    return getHeight(size);
  }

  @Override
  public void print() {
    print(size);
  }

  @Override
  public Iterator<Character> iterator() {
    return new ArrayIterator();
  }

  class ArrayIterator implements Iterator<Character> {

    int pos = 0;

    @Override
    public boolean hasNext() {
      return pos != size;
    }

    @Override
    public Character next() {
      return tree[pos++].getKey();
    }
  }

  /**
   * 重载Node节点
   * - 二叉树只有2个节点
   * - 基于数组实现 ,通过index可以直接访问父子节点
   */
  class ArrayElement extends BaseElement {

    int innerIndex = 0;

    ArrayElement(char id, int innerIndex) {
      this.id = id;
      this.innerIndex = innerIndex;
    }

    @Override
    public BaseElement getParent() {
      if (innerIndex == 0) {
        return this;
      }
      return tree[((innerIndex + 1) >> 1) - 1];
    }

    @Override
    public BaseElement[] getChildren() {
      int leftIndex = (innerIndex << 1) + 1;
      int rightIndex = (innerIndex << 1) + 2;
      return new BaseElement[]{
          leftIndex < size ? tree[leftIndex] : null,
          rightIndex < size ? tree[rightIndex] : null};
    }
  }


  private void grow() {
    BaseElement[] newTree = new ArrayElement[tree.length * 2];
    System.arraycopy(tree, 0, newTree, 0, size);
    tree = newTree;
  }
}
