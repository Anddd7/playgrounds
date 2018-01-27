package github.eddy.stepbystep.tree;

import github.eddy.stepbystep.tree.node.BaseElement;

/**
 * @author and777
 * @date 2018/1/27
 *
 * 树的基本方法
 */
public interface Tree {

  default BaseElement find(int id) {
    return find((char) (48 + id));
  }

  default BaseTree add(int id) {
    return add((char) (48 + id));
  }

  default BaseTree remove(int id) {
    return remove((char) (48 + id));
  }

  /**
   * 节点数
   */
  int getSize();

  /**
   * 树高
   */
  int getHeight();

  /**
   * 查询
   *
   * @param id 值
   * @return 节点
   */
  BaseElement find(char id);

  /**
   * 添加
   *
   * @param id 值
   * @return 节点
   */
  BaseTree add(char id);

  /**
   * 删除
   *
   * @param id 值
   * @return 节点
   */
  BaseTree remove(char id);

  /**
   * 打印 ,例如
   * |  0
   * |1   2
   */
  void print();
}
