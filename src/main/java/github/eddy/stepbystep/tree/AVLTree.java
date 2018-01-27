package github.eddy.stepbystep.tree;

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
  public boolean addElement(BaseBinaryTree.BinaryNodeElement parent,
      BaseBinaryTree.BinaryNodeElement target) {

    if (parent.compareTo(target) > 0) {
      //left
      BaseBinaryTree.BinaryNodeElement element = parent.getLeftElement();

      //左为空 ,直接插入
      if (element == null) {
        parent.setLeftElement(target);
        return true;
      }

      //插入到左子树 ,需要判断是否旋转
      boolean addToSubLeft = addElement(element, target);
      //当前节点的 左子树 超重 : 左边高度(2) > 右边高度(0) + 2
      if (parent.getLeftHeight() - parent.getRightHeight() == 2) {
        //插入到左边
        if (addToSubLeft) {
          //左左
          leftWithLeftRotation(parent);
        } else {
          //左右
          leftWithRightRotation(parent);
        }
      }
      return addToSubLeft;
    } else {
      //right
      BaseBinaryTree.BinaryNodeElement element = parent.getRightElement();
      if (element == null) {
        parent.setRightElement(target);
        return false;
      }
      //插入到右子树 ,需要判断是否旋转
      boolean addToSubLeft = addElement(element, target);
      //当前节点的 右子树 超重 : 左边高度(2) > 右边高度(0) + 2
      if (parent.getRightHeight() - parent.getLeftHeight() == 2) {
        //插入到右边
        if (addToSubLeft) {
          //右左
          rightWithLeftRotation(parent);
        } else {
          //右右
          rightWithRightRotation(parent);
        }
      }
      return addToSubLeft;
    }
  }


  @Override
  public boolean removeElement(BinaryNodeElement parent, char id) {
    if (parent == null) {
      return false;
    }

    if (id < parent.id) {
      boolean success = removeElement(parent.getLeftElement(), id);
      //成功删除位于左子树中的节点 ,且右子树超重
      if (success && parent.getRightHeight() - parent.getLeftHeight() == 2) {
        BinaryNodeElement subRightTree = parent.getRightElement();
        if (subRightTree.getRightHeight() > subRightTree.getLeftHeight()) {
          //右子树向右偏移
          rightWithRightRotation(parent);
        } else {
          //右子树向左偏移
          rightWithLeftRotation(parent);
        }
      }
      return success;
    }

    if (id > parent.id) {
      boolean success = removeElement(parent.getRightElement(), id);
      //成功删除位于右子树中的节点 ,且左子树超重
      if (success && parent.getLeftHeight() - parent.getRightHeight() == 2) {
        BinaryNodeElement subLeftTree = parent.getLeftElement();
        if (subLeftTree.getLeftHeight() > subLeftTree.getRightHeight()) {
          //左子树向左偏移
          leftWithLeftRotation(parent);
        } else {
          //左子树向右偏移
          leftWithRightRotation(parent);
        }
      }
      return success;
    }

    if (parent.getLeftElement() != null && parent.getRightElement() != null
        && parent.getLeftHeight() < parent.getRightHeight()) {
      //右子树比左边高 ,找右边的最小值插入到删除的点
      BinaryNodeElement rightMin = parent.getRightElement();
      while (rightMin.getLeftElement() != null) {
        rightMin = rightMin.getLeftElement();
      }
      //remove 叶子
      rightMin.fastRemove();
      //设置父->新的节点
      if (parent == root) {
        root = rightMin;
      } else if (parent.isLeftChild()) {
        parent.parent.setLeftElement(rightMin);
      } else {
        parent.parent.setRightElement(rightMin);
      }
      //设置节点->原有的子节点
      rightMin.setChildren(parent.children);
      return true;
    }

    return super.removeElement(parent, id);
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
  private void leftWithLeftRotation(BinaryNodeElement subTreeRoot) {
    BinaryNodeElement leftNode = subTreeRoot.getLeftElement();
    BinaryNodeElement rightLeafOfLeftNode = leftNode.getRightElement();

    //remove leftNode from subTreeRoot
    subTreeRoot.setLeftElement(null);

    //upper leftNode
    if (subTreeRoot == root) {
      //replace leftNode as root
      root = leftNode;
      leftNode.parent = null;
    } else {
      //set leftNode as parent.left
      subTreeRoot.parent.setLeftElement(leftNode);
    }

    //put subTreeRoot as leftNode.right
    leftNode.setRightElement(subTreeRoot);

    //put old rightLeafOfLeftNode as subTreeRoot.left
    subTreeRoot.setLeftElement(rightLeafOfLeftNode);
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
  private void leftWithRightRotation(BinaryNodeElement subTreeRoot) {
    BinaryNodeElement leftNodeWithRightLeaf = subTreeRoot.getLeftElement();
    BinaryNodeElement rightLeaf = leftNodeWithRightLeaf.getRightElement();

    //remove rightLeaf from node
    leftNodeWithRightLeaf.setRightElement(null);
    //set rightLeaf as root.left
    subTreeRoot.setLeftElement(rightLeaf);
    //set leftNode as rightLeaf.left
    rightLeaf.setLeftElement(leftNodeWithRightLeaf);
    //execute left left rotation
    leftWithLeftRotation(subTreeRoot);
  }

  /**
   * @see this#leftWithLeftRotation(BinaryNodeElement)
   */
  private void rightWithRightRotation(BinaryNodeElement subTreeRoot) {
    BinaryNodeElement rightNode = subTreeRoot.getRightElement();
    BinaryNodeElement leftLeafOfRightNode = rightNode.getLeftElement();

    //remove rightNode from subTreeRoot
    subTreeRoot.setRightElement(null);

    //upper rightNode
    if (subTreeRoot == root) {
      //replace rightNode as root
      root = rightNode;
      rightNode.parent = null;
    } else {
      //set rightNode as parent.right
      subTreeRoot.parent.setRightElement(rightNode);
    }

    //put subTreeRoot as rightNode.left
    rightNode.setLeftElement(subTreeRoot);

    //put old leftLeafOfRightNode as subTreeRoot.right
    subTreeRoot.setRightElement(leftLeafOfRightNode);
  }

  /**
   * @see this#leftWithRightRotation(BinaryNodeElement)
   */
  private void rightWithLeftRotation(BinaryNodeElement subTreeRoot) {
    BinaryNodeElement rightNodeWithLeftLeaf = subTreeRoot.getRightElement();
    BinaryNodeElement leftLeaf = rightNodeWithLeftLeaf.getLeftElement();

    //remove leftLeaf from node
    rightNodeWithLeftLeaf.setLeftElement(null);
    //set leftLeaf as root.right
    subTreeRoot.setRightElement(leftLeaf);
    //set rightNode as leftLeaf.right
    leftLeaf.setRightElement(rightNodeWithLeftLeaf);
    //execute right right rotation
    rightWithRightRotation(subTreeRoot);
  }
}
