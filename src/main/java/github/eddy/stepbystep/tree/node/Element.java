package github.eddy.stepbystep.tree.node;

/**
 * @author and777
 * @date 2018/1/27
 *
 * 节点元素
 */
public interface Element extends Comparable<Element> {

  /**
   * 获取元素Key
   */
  char getKey();

  /**
   * 获取元素值
   *
   * 暂不支持
   */
  @Deprecated
  char getValue();

  /**
   * 获取父节点
   */
  BaseElement getParent();

  /**
   * 获取子节点
   */
  BaseElement[] getChildren();

  /**
   * 比较器实现
   */
  @Override
  default int compareTo(Element another) {
    if (getKey() == another.getKey()) {
      return 0;
    }
    return getKey() > another.getKey() ? 1 : -1;
  }
}
