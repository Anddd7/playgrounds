package github.eddy.java8core;

import java.util.AbstractCollection;
import java.util.AbstractList;
import java.util.AbstractMap;
import java.util.AbstractSequentialList;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @author and777
 * @date 2018/1/12
 *
 * collection learning
 */
public class CollectionLab {

  /**
   * 相关接口
   *
   * {@link java.util.RandomAccess}
   * 标识一个List是否是可随机访问的,可随机访问的(ArrayList)使用for循环要比iterator迭代器更快
   * Test : {@link RandomAccessTest}
   *
   * {@link Cloneable}
   * 标识一个对象是否可以使用{@link #clone()}方法
   * Test : {@link CloneableAndSerializableTest}
   *
   * {@link java.io.Serializable}
   * 标识一个对象是否可以序列化 (二进制)
   */


  /**
   * 顶层接口
   *
   * {@link Iterable}
   * 标识该对象可以使用 forEach 的语法糖
   * - forEach的底层实现 {@link IterableTest}
   *
   * {@link Collection}
   *
   */
  AbstractCollection abstractCollection;

  /**
   * List
   */
  List list;
  AbstractList abstractList;
  ArrayList arrayList;

  AbstractSequentialList abstractSequentialList;
  LinkedList linkedList;


  /**
   * Map
   */
  Map map;
  AbstractMap abstractMap;
  HashMap hashMap;

  ConcurrentMap concurrentMap;
  ConcurrentHashMap concurrentHashMap;


  /**
   * Collections inner method
   */
  Map synchronizedMap = Collections.synchronizedMap(new HashMap<>());
}
