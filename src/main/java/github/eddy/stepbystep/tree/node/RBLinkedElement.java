package github.eddy.stepbystep.tree.node;

/**
 * @author and777
 * @date 2018/1/27
 *
 * 红黑树节点
 */
public class RBLinkedElement extends BinaryLinkedElement {

  enum Color {
    RED, BLACK
  }

  protected Color color;

  public RBLinkedElement(char id) {
    super(id);
    this.color = Color.RED;
  }

  public Color getColor() {
    return color;
  }
}
