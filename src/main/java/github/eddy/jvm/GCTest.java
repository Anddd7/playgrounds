package github.eddy.jvm;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import org.junit.Test;

public class GCTest {

  @Test
  public void soft_test_method() {
    SoftReference softRef = new SoftReference(new String("soft"));
    System.gc();
    /**
     * 软引用的对象在没有强引用指向时 ,可以被GC ; GC结果根据当前内存情况和get方法调用来决定
     */
    System.out.println(softRef.get());
  }

  @Test
  public void weak_test_method() {
    WeakReference weakRef = new WeakReference(new String("weak"));
    System.gc();
    /**
     * 弱引用的对象在经历GC时一定会被清除
     */
    System.out.println(weakRef.get());
  }


  @Test
  public void phantom_test_method() {
    ReferenceQueue<String> refQueue = new ReferenceQueue<String>();
    PhantomReference phantomRef = new PhantomReference(new String("phantom"), refQueue);

    //虚引用get永远为null ,无法访问对象本身
    System.out.println(phantomRef.get());// null

    System.gc();
    System.runFinalization();

    //虚引用被gc后 ,自身会被放入queue用于追踪他的回收情况
    System.out.println(refQueue.poll() == phantomRef); //true
  }

  @Test
  public void queue_test_method() {
    ReferenceQueue<String> refQueue = new ReferenceQueue<String>();

    String hardRef = new String("hard");
    SoftReference softRef = new SoftReference(new String("soft"), refQueue);
    WeakReference weakRef = new WeakReference(new String("weak"), refQueue);
    PhantomReference phantomRef = new PhantomReference(new String("phantom"), refQueue);

    System.gc();
    System.runFinalization();

    while (true) {
      Reference ref = refQueue.poll();
      if (ref == null) {
        break;
      }
      System.out.println(ref + "|" + ref.get());
    }
  }


  public static void main(String[] a) throws InterruptedException {
    //设置vm options
    //-Xmx2m -Xms2m -XX:-UseGCOverheadLimit -XX:+PrintGCDetails
    int size = 15000;
    SoftReference[] softRefs = new SoftReference[size];
    for (int i = 0; i < size; i++) {
      softRefs[i] = new SoftReference(new String("SoftReference"));
    }
    Thread.sleep(1*1000);
    System.out.println(softRefs[0].get());
    System.out.println(softRefs[size - 1].get());
  }
}
