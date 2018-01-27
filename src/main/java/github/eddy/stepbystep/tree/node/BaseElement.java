package github.eddy.stepbystep.tree.node;

/**
 * @author and777
 * @date 2018/1/27
 *
 * 节点元素基本实现
 * - 只包含Key
 */
public abstract class BaseElement implements Element {

  protected char id;

  @Override
  public char getKey() {
    return id;
  }

  @Override
  public char getValue() {
    throw new UnsupportedOperationException();
  }

  @Override
  public String toString() {
    return String.valueOf(getKey());
  }
}
