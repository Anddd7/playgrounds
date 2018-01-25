package github.eddy.stepbystep.tree;

/**
 * @author and777
 * @date 2018/1/24
 */
public abstract class BaseTree {

  int size;

  public int getSize() {
    return size;
  }

  public Element find(int id) {
    return find((char) (48 + id));
  }

  public BaseTree add(int id) {
    return add((char) (48 + id));
  }


  public BaseTree remove(int id) {
    return remove((char) (48 + id));
  }

  /**
   * find 查询
   */
  public abstract Element find(char id);

  /**
   * add 添加
   */
  public abstract BaseTree add(char id);

  /**
   * remove 删除
   */
  public abstract BaseTree remove(char id);

  /**
   * 打印 ,例如
   * |  0
   * |1   2
   */
  public abstract void print();

  /**
   * 元素
   */
  public class Element implements Comparable<Element> {

    char id;

    Element(char id) {
      this.id = id;
    }

    /**
     * 获取父节点
     */
    public Element getParent() {
      throw new UnsupportedOperationException();
    }

    /**
     * 获取子节点
     */
    public Element[] getChildren() {
      throw new UnsupportedOperationException();
    }

    @Override
    public String toString() {
      return String.valueOf(id);
    }

    @Override
    public int compareTo(Element o) {
      if (this.id == o.id) {
        return 0;
      }
      return this.id > o.id ? 1 : -1;
    }
  }
}
