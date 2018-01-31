package github.eddy.stepbystep.tree.impl;

import github.eddy.stepbystep.tree.BaseBinaryTree;
import github.eddy.stepbystep.tree.node.RBLinkedElement;
import github.eddy.stepbystep.tree.node.RBLinkedElement.Color;

/**
 * @author and777
 * @date 2018/1/27
 *
 * 红黑树
 * 1. 根节点是黑的
 * 2. 节点是黑色或者红色
 * 3. 叶节点(NIL空节点)是黑的
 * 4. 红色节点的子节点都是黑色的
 * 5. 任意节点到其叶节点(树的末端)的路径包含相同数目的黑色节点
 */
public class RBTree extends BaseBinaryTree<RBLinkedElement> {

  @Override
  public boolean addElement(RBLinkedElement current, char id) {
    if (root == null) {
      root = new RBLinkedElement(id);
      fixAfterInsertion(root);
      return true;
    }

    if (id < current.getKey()) {
      //left
      RBLinkedElement leftChild = (RBLinkedElement) current.getLeftChild();
      if (leftChild == null) {
        RBLinkedElement child = new RBLinkedElement(id);
        current.setLeftChild(child);
        fixAfterInsertion(child);
        return true;
      }

      //next left
      return addElement(leftChild, id);
    }

    //right
    RBLinkedElement rightChild = (RBLinkedElement) current.getRightChild();
    if (rightChild == null) {
      RBLinkedElement child = new RBLinkedElement(id);
      current.setRightChild(child);
      fixAfterInsertion(child);
      return false;
    }

    //next right
    return addElement(rightChild, id);
  }


  @Override
  public boolean removeElement(RBLinkedElement current, char id) {
    return false;
  }

  private void fixAfterInsertion(RBLinkedElement child) {
    //(1) 插入根节点 ,直接涂黑
    if (root == child) {
      root.setColor(Color.BLACK);
      return;
    }

    RBLinkedElement parent = (RBLinkedElement) child.getParent();

    //(1) 插入的父节点是黑色 ,直接插入 : 不影响性质
    if (Color.BLACK.equals(parent.getColor())) {
      return;
    }

    /**
     * (2) 插入的父节点是红色 (则一定有祖父节点 ,且为黑色)
     * - child 和 parent 都是红色 ,违反了性质4
     */
    RBLinkedElement grandpa = (RBLinkedElement) parent.getParent();
    RBLinkedElement uncle = parent.isLeftChildOfParent() ?
        (RBLinkedElement) grandpa.getRightChild() : (RBLinkedElement) grandpa.getLeftChild();
    if (uncle != null && Color.RED.equals(uncle.getColor())) {
      /**
       * (2.1) 叔父节点是红色
       * - 将 父/叔父 都涂黑 , 祖父涂红
       * - 把 祖父 当做新插入的点 , 递归判断 祖父向上的关系
       */
      parent.setColor(Color.BLACK);
      uncle.setColor(Color.BLACK);
      grandpa.setColor(Color.RED);
      fixAfterInsertion(grandpa);
      return;
    }

    //
    if (parent.isLeftChildOfParent()) {
      /**
       * (2.2) 叔父节点是黑色 ,且插入节点是祖父节点的左支
       *
       * 为什么叔父节点可能是黑色或者空 ??
       * * 在 child 插入前树是满足所有性质的 ,假设根到达 G 路径的黑色节点数是k
       * * 如果是插入时引起的这种情况 ,那么U一定是空节点 : 否则违反5
       * * 如果是调整过程中引起的这种情况 ,那么U可能为黑 ,同时 n 和 p 一定都有黑色的子节点
       *
       * 插入节点在右边
       *
       * |       G                     p
       * |   p       U             n       U
       * | o   n   -   -         p   -   -   -
       * |o o - - - - - -       o o - - - - - -
       *
       * 如上 ,如果出现左右偏移 ,先调整为左左偏移
       *
       * 插入节点在左边
       *
       * |       G                     p                     P
       * |   p       U             n       G             n       g
       * | n   o   -   -         o   o   o   U         o   o   o   U
       * |o o - - - - - -       - - - - - - - -       - - - - - - - -
       *
       * 大写黑色 ,小写红色 ; n 是新插入的
       *
       * - 如上图 , 因为 n 的插入破坏了性质4 ,需要进行旋转调整 : 从顶点到叶子的距离都相同 (右旋)
       * - 原顶点(G)是黑色的(根节点或顶点的父节点是红色的) ,所以新的顶点也必须是黑色 : p->P ,G->g
       */

      if (!child.isLeftChildOfParent()) {
        RBLinkedElement newRightOfChild = (RBLinkedElement) child.getLeftChild();
        RBLinkedElement temp = parent;
        parent = child;
        child = temp;

        grandpa.setLeftChild(parent);
        parent.setLeftChild(child);
        child.setRightChild(newRightOfChild);
      }

      if (grandpa == root) {
        root = parent;
      } else if (grandpa.isLeftChildOfParent()) {
        grandpa.getParent().setLeftChild(parent);
      } else {
        grandpa.getParent().setRightChild(parent);
      }

      RBLinkedElement oldRightOfParent = (RBLinkedElement) parent.getRightChild();

      parent.setLeftChild(child);
      parent.setRightChild(grandpa);
      grandpa.setLeftChild(oldRightOfParent);

      parent.setColor(Color.BLACK);
      grandpa.setColor(Color.RED);
      return;
    }

    //(2.3) 叔父节点是黑色 ,且插入节点是祖父节点的右支 (同上)
    if (child.isLeftChildOfParent()) {
      RBLinkedElement newLeftOfChild = (RBLinkedElement) child.getRightChild();
      RBLinkedElement temp = parent;
      parent = child;
      child = temp;

      grandpa.setRightChild(parent);
      parent.setRightChild(child);
      child.setLeftChild(newLeftOfChild);
    }

    if (grandpa == root) {
      root = parent;
    } else if (grandpa.isLeftChildOfParent()) {
      grandpa.getParent().setLeftChild(parent);
    } else {
      grandpa.getParent().setRightChild(parent);
    }

    RBLinkedElement oldLeftOfParent = (RBLinkedElement) parent.getRightChild();

    parent.setRightChild(child);
    parent.setLeftChild(grandpa);
    grandpa.setRightChild(oldLeftOfParent);

    parent.setColor(Color.BLACK);
    grandpa.setColor(Color.RED);
  }

  private void fixAfterRemove() {
//TODO
  }
}
