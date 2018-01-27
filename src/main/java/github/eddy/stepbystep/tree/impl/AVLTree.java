package github.eddy.stepbystep.tree.impl;

import github.eddy.stepbystep.tree.node.BinaryLinkedElement;

/**
 * @author and777
 * @date 2018/1/26
 *
 * 平衡二叉树 - AVL
 */
public class AVLTree extends BinarySortTree {

  /**
   * 在插入节点后需要判断左右两边的子树是否平衡 ,假设插入前树是平衡的(任意左右子树高度差不大于1)
   * - 左右都没有叶子 : 节点作为叶子插入 ,左右差为1 ,不用旋转
   * - 左右其中一个有一个叶子
   * |  - 节点插入到无叶子的位置 ,左右平衡 ,不用旋转
   * |  - 节点插入到已有叶子的子树中 ,左右高度差为2 ,需要旋转
   * |    - 右为空 ,插入到左子树的左边 : 左左 ,需要 左旋转
   * |    - 右为空 ,插入到左子树的右边 : 左右 ,需要 右旋转+左旋转
   * |    - 左为空 ,插入到右子树的左边 : 右左 ,需要 左旋转+右旋转
   * |    - 左为空 ,插入到右子树的右边 : 右右 ,需要 右旋转
   */
  @Override
  public boolean addElement(BinaryLinkedElement current, char id) {
    if (root == null) {
      root = new BinaryLinkedElement(id);
      return true;
    }

    if (id < current.getKey()) {
      //left
      BinaryLinkedElement leftChild = current.getLeftChild();

      //左为空 ,直接插入
      if (leftChild == null) {
        current.setLeftChild(new BinaryLinkedElement(id));
        return true;
      }

      //插入到左子树 ,需要判断是否旋转
      boolean isLeftLeaf = addElement(leftChild, id);
      //当前节点的 左子树 超重 : 左边高度(2) > 右边高度(0) + 2
      if (current.getLeftHeight() - current.getRightHeight() == 2) {
        //插入到左边
        if (isLeftLeaf) {
          //左左
          leftWithLeftRotation(current);
        } else {
          //左右
          leftWithRightRotation(current);
        }
      }
      return isLeftLeaf;
    } else {
      //right
      BinaryLinkedElement rightChild = current.getRightChild();
      if (rightChild == null) {
        current.setRightChild(new BinaryLinkedElement(id));
        return false;
      }
      //插入到右子树 ,需要判断是否旋转
      boolean isLeftLeaf = addElement(rightChild, id);
      //当前节点的 右子树 超重 : 左边高度(2) > 右边高度(0) + 2
      if (current.getRightHeight() - current.getLeftHeight() == 2) {
        //插入到右边
        if (isLeftLeaf) {
          //右左
          rightWithLeftRotation(current);
        } else {
          //右右
          rightWithRightRotation(current);
        }
      }
      return isLeftLeaf;
    }
  }


  @Override
  public boolean removeElement(BinaryLinkedElement current, char id) {
    if (current == null) {
      return false;
    }

    if (id < current.getKey()) {
      boolean success = removeElement(current.getLeftChild(), id);
      //成功删除位于左子树中的节点 ,且右子树超重
      if (success && current.getRightHeight() - current.getLeftHeight() == 2) {
        BinaryLinkedElement rightChild = current.getRightChild();
        if (rightChild.getRightHeight() > rightChild.getLeftHeight()) {
          //右子树向右偏移
          rightWithRightRotation(current);
        } else {
          //右子树向左偏移
          rightWithLeftRotation(current);
        }
      }
      return success;
    }

    if (id > current.getKey()) {
      boolean success = removeElement(current.getRightChild(), id);
      //成功删除位于右子树中的节点 ,且左子树超重
      if (success && current.getLeftHeight() - current.getRightHeight() == 2) {
        BinaryLinkedElement leftChild = current.getLeftChild();
        if (leftChild.getLeftHeight() > leftChild.getRightHeight()) {
          //左子树向左偏移
          leftWithLeftRotation(current);
        } else {
          //左子树向右偏移
          leftWithRightRotation(current);
        }
      }
      return success;
    }

    if (current.getLeftChild() != null && current.getRightChild() != null
        && current.getLeftHeight() < current.getRightHeight()) {
      //右子树比左边高 ,找右边的最小值插入到删除的点
      BinaryLinkedElement rightMin = current.getRightChild();
      while (rightMin.getLeftChild() != null) {
        rightMin = rightMin.getLeftChild();
      }
      //remove 叶子
      rightMin.fastRemove();
      //设置父->新的节点
      if (current == root) {
        root = rightMin;
      } else if (current.isLeftChildOfParent()) {
        current.getParent().setLeftChild(rightMin);
      } else {
        current.getParent().setRightChild(rightMin);
      }
      //设置节点->原有的子节点
      rightMin.setChildren(current.getChildren());
      return true;
    }

    return super.removeElement(current, id);
  }

  /**
   * 处理左左偏移 : 最后插入的是1
   * |               7
   * |       5                8
   * |   3       6       -       9
   * | 2   4   -   -   -   -   -   -
   * |1 - - - - - - - - - - - - - - -
   *
   * 如图 ,节点 5 的左子树高度比右边大2 (3:1) ,且 1 节点在左边
   * - 需要将 左子树(节点3) 上升 , 节点 5 被挤到右子树
   * |               7
   * |       3                8
   * |   2       5       -       9
   * | 1   -   -   6   -   -   -   -
   * |- - - - - - - - - - - - - - - -
   * - 这时节点 4 因为和节点 5 重合 (都是3的右子树) ,但 4 一定比 5 小 (未移动前在 5 的左子树中) ,所以将 4 放到 5 的左子树
   * |               7
   * |       3                8
   * |   2       5       -       9
   * | 1   -   4   6   -   -   -   -
   * |- - - - - - - - - - - - - - - -
   */
  private void leftWithLeftRotation(BinaryLinkedElement current) {
    BinaryLinkedElement leftChild = current.getLeftChild();
    BinaryLinkedElement rightOfLeftChild = leftChild.getRightChild();

    //remove leftNode from subTreeRoot
    current.setLeftChild(null);

    //upper leftNode
    if (current == root) {
      //replace leftNode as root
      root = leftChild;
      leftChild.setParent(null);
    } else {
      //set leftNode as parent.left
      current.getParent().setLeftChild(leftChild);
    }

    //put subTreeRoot as leftNode.right
    leftChild.setRightChild(current);

    //put old rightLeafOfLeftNode as subTreeRoot.left
    current.setLeftChild(rightOfLeftChild);
  }

  /**
   * 处理左右偏移 : 最后插入的是2
   * |               7
   * |       5                8
   * |   3       6       -       9
   * | 1   4   -   -   -   -   -   -
   * |- 2 - - - - - - - - - - - - - -
   *
   * 如图 ,节点 5 的左子树高度比右边大2 (3:1) ,且 2 节点在右边
   * - 需要转化成左左偏移 , 将节点 2 (左右偏移发生点) 上升 ,节点 1 下降到左边
   * |               7
   * |       5                8
   * |   3       6       -       9
   * | 2   4   -   -   -   -   -   -
   * |1 - - - - - - - - - - - - - - -
   */
  private void leftWithRightRotation(BinaryLinkedElement subTreeRoot) {
    BinaryLinkedElement leftChildWithRightLeaf = subTreeRoot.getLeftChild();
    BinaryLinkedElement rightLeaf = leftChildWithRightLeaf.getRightChild();

    //remove rightLeaf from node
    leftChildWithRightLeaf.setRightChild(null);
    //set rightLeaf as root.left
    subTreeRoot.setLeftChild(rightLeaf);
    //set leftNode as rightLeaf.left
    rightLeaf.setLeftChild(leftChildWithRightLeaf);
    //execute left left rotation
    leftWithLeftRotation(subTreeRoot);
  }

  /**
   * @see this#leftWithLeftRotation(BinaryLinkedElement)
   */
  private void rightWithRightRotation(BinaryLinkedElement current) {
    BinaryLinkedElement rightChild = current.getRightChild();
    BinaryLinkedElement leftOfRightChild = rightChild.getLeftChild();

    //remove rightNode from subTreeRoot
    current.setRightChild(null);

    //upper rightNode
    if (current == root) {
      //replace rightNode as root
      root = rightChild;
      rightChild.setParent(null);
    } else {
      //set rightNode as parent.right
      current.getParent().setRightChild(rightChild);
    }

    //put subTreeRoot as rightNode.left
    rightChild.setLeftChild(current);

    //put old leftLeafOfRightNode as subTreeRoot.right
    current.setRightChild(leftOfRightChild);
  }

  /**
   * @see this#leftWithRightRotation(BinaryLinkedElement)
   */
  private void rightWithLeftRotation(BinaryLinkedElement current) {
    BinaryLinkedElement rightChildWithLeftLeaf = current.getRightChild();
    BinaryLinkedElement leftLeaf = rightChildWithLeftLeaf.getLeftChild();

    //remove leftLeaf from node
    rightChildWithLeftLeaf.setLeftChild(null);
    //set leftLeaf as root.right
    current.setRightChild(leftLeaf);
    //set rightNode as leftLeaf.right
    leftLeaf.setRightChild(rightChildWithLeftLeaf);
    //execute right right rotation
    rightWithRightRotation(current);
  }
}
