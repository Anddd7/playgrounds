package github.eddy.stepbystep.tree;

/**
 * @author and777
 * @date 2018/1/24
 */
public abstract class BaseTree implements Tree {

  protected int size;

  @Override
  public int getSize() {
    return size;
  }
}
