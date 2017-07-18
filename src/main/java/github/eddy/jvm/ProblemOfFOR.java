package github.eddy.jvm;

import java.lang.ref.SoftReference;

public class ProblemOfFOR {

  int size = 100000;


  public void test1() {
    SoftReference[] softRefs = new SoftReference[size];
    for (int i = 0; i < size; i++) {
      softRefs[i] = new SoftReference(new String("soft"));
    }
    System.out.println(softRefs[0].get());
  }

  public void test2() {
    SoftReference[] softRefs = new SoftReference[size];
    for (SoftReference softRef : softRefs) {
      softRef = new SoftReference(new String("soft"));
    }
    System.out.println(softRefs[0].get());
  }

  public static void main(String[] a) {
    ProblemOfFOR lab = new ProblemOfFOR();
    lab.test1();
    //lab.test2();
  }
}
