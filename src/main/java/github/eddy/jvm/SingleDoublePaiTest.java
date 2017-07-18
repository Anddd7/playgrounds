package github.eddy.jvm;


class Eat {

}

class Drink {

}

class Daddy {

  public void doSomething(Eat arg) {
    System.out.println("爸爸在吃饭");
  }

  public void doSomething(Drink arg) {
    System.out.println("爸爸在喝水");
  }
}

class Son extends Daddy {

  public void doSomething(Eat arg) {
    System.out.println("儿子在吃饭");
  }

  public void doSomething(Drink arg) {
    System.out.println("儿子在喝水");
  }
}

public class SingleDoublePaiTest {

  public static void main(String[] args) {
    /**
     * 静态阶段->多分派:方法重载
     *        根据 调用者的类型 ( Daddy ) 和 方法参数 ( Eat/Drink ) 判断选择那种方式
     * 动态阶段->单分派:方法覆写
     *        只根据实际类型进行判断
     *
     * 这里先经过静态
     * Daddy.doSomething(new Eat());
     * Daddy.doSomething(new Drink());
     *
     * 然后经过动态 改写第二个方法
     * Daddy.doSomething(new Eat());
     * Son.doSomething(new Drink());
     */
    Daddy father = new Daddy();
    Daddy child = new Son();
    father.doSomething(new Eat());
    child.doSomething(new Drink());
  }
}
