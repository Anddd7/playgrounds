package github.eddy.jvm;

class Human {

  public void hello() {
    System.out.println("Hello ,i am human");
  }
}

class Man extends Human {

  public void hello() {
    System.out.println("Hello ,i am man");
  }
}

class Woman extends Human {

  public void hello() {
    System.out.println("Hello ,i am woman");
  }
}

public class StaticPaiTest {

  public void say(Human hum) {
    System.out.println("I am human");
  }

  public void say(Man hum) {
    System.out.println("I am man");
  }

  public void say(Woman hum) {
    System.out.println("I am woman");
  }

  public static void main(String[] args) {
    StaticPaiTest sp = new StaticPaiTest();
    /**
     * 1.静态分派 : 静态类型为Human ,实际类型为Man/Woman ,调用方法根据静态类型去匹配
     * 编译期就可以决定
     */
    Human man = new Man();
    Human woman = new Woman();
    sp.say(man);
    sp.say(woman);
    /**
     * 2.动态分配 : 允许子类复写方法 ,在运行时根据实际类型调用方法
     */
    man.hello();
    woman.hello();
  }
}


