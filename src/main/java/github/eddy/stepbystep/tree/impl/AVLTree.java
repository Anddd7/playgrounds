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
        if (leftChild.getLeftHeight() > leftChild.getRightHeight()) {
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
        if (rightChild.getLeftHeight() > rightChild.getRightHeight()) {
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
   * 处理 进阶 左左偏移 : 最后插入的是2 ,但节点3左右高度差为1 ,满足条件不进行调整
   * |               7
   * |       5                8
   * |   3       6       -       9
   * | 1   4   -   -   -   -   -   -
   * |- 2 - - - - - - - - - - - - - -
   *
   * 上升到节点 5 的时候 ,左子树高度比右边大2 (3:1) ,且节点1高度大于节点4(2-1) , 5-3-1 形成左左偏移
   * - 需要将 左子树(节点3) 上升 , 节点 5 被挤到右子树
   * |               7
   * |       3                8
   * |   1       5       -       9
   * | -   2   -   6   -   -   -   -
   * |- - - - - - - - - - - - - - - -
   * - 这时节点 4 因为和节点 5 重合 (都是3的右子树) ,但 4 一定比 5 小 (未移动前在 5 的左子树中) ,所以将 4 放到 5 的左子树
   * |               7
   * |       3                8
   * |   1       5       -       9
   * | -   2   4   6   -   -   -   -
   * |- - - - - - - - - - - - - - - -
   *
   *
   * 20180201 优化名称定义
   * 如上 ,5-3-1 构成的左右偏移 : 7 = grandpa ,5 = parent ,3 = child
   */
  private void leftWithLeftRotation(BinaryLinkedElement parent) {
    BinaryLinkedElement child = parent.getLeftChild();

    if (parent == root) {
      root = child;
    } else if (parent.isLeftChildOfParent()) {
      parent.getParent().setLeftChild(child);
    } else {
      parent.getParent().setRightChild(child);
    }

    parent.setLeftChild(child.getRightChild());
    child.setRightChild(parent);
  }

  /**
   * 处理 进阶 左右偏移 : 最后插入的是3 ,但节点2左右高度差为1 ,满足条件不进行调整
   * |               7
   * |       5                8
   * |   2       6       -       9
   * | 1   4   -   -   -   -   -   -
   * |- - 3 - - - - - - - - - - - - -
   *
   * 上升到节点 5 的时候 ,左子树高度比右边大2 (3-1) ,且 节点4高度大于节点1 (5-2-4形成左右偏移)
   * - 需要转化成左左偏移 , 将节点 4 (左右偏移发生点) 上升 ,节点 2 下降到左边
   * - 因为节点4还有左节点3 ,上升后被节点2覆盖 ,因此需要加到2的右节点上(一定比2大)
   * |               7
   * |       5                8
   * |   4       6       -       9
   * | 2   -   -   -   -   -   -   -
   * |1 3 - - - - - - - - - - - - - -
   *
   * 20180201 优化名称定义
   * 如上 ,5-2-4 构成的左右偏移 : 5 = grandpa ,2 = parent , 4 = child
   */
  private void leftWithRightRotation(BinaryLinkedElement grandpa) {
    BinaryLinkedElement parent = grandpa.getLeftChild();
    BinaryLinkedElement child = parent.getRightChild();

    //child replace the parent
    grandpa.setLeftChild(child);
    //append child.left to parent.right
    parent.setRightChild(child.getLeftChild());
    //set parent as child.left
    child.setLeftChild(parent);

    //execute left left rotation
    leftWithLeftRotation(grandpa);
  }

  /**
   * @see this#leftWithLeftRotation(BinaryLinkedElement)
   */
  private void rightWithRightRotation(BinaryLinkedElement parent) {
    BinaryLinkedElement child = parent.getRightChild();

    if (parent == root) {
      root = child;
    } else if (parent.isLeftChildOfParent()) {
      parent.getParent().setLeftChild(child);
    } else {
      parent.getParent().setRightChild(child);
    }

    parent.setRightChild(child.getLeftChild());
    child.setLeftChild(parent);
  }

  /**
   * @see this#leftWithRightRotation(BinaryLinkedElement)
   */
  private void rightWithLeftRotation(BinaryLinkedElement grandpa) {
    BinaryLinkedElement parent = grandpa.getRightChild();
    BinaryLinkedElement child = parent.getLeftChild();

    //child replace the parent
    grandpa.setRightChild(child);
    //append child.right to parent.left
    parent.setLeftChild(child.getRightChild());
    //set parent as child.right
    child.setRightChild(parent);

    //execute right right rotation
    leftWithLeftRotation(grandpa);
  }
}
