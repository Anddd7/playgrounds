package github.eddy.stepbystep.tree;

import java.util.ArrayDeque;
import java.util.Iterator;

/**
 * @author and777
 * @date 2018/1/25
 *
 * 打印二叉树
 */
public interface BinaryTreePrint {

  /**
   * 广度优先遍历的迭代器
   */
  Iterator<Character> iterator();

  /**
   * 打印二叉树 (广度优先)
   * - 默认是平衡二叉树
   * - 叶子节点从0开始排列 ,相隔一个空格
   * - 根节点在2个叶子/子根之间
   *
   * 假设树高为 h , 则最大节点数 2^h-1
   * 以此用当前 size 反推 最大节点数 和 树高 ,然后获得叶子节点数量以确定print的字符长度
   */
  default void print(int size) {
    Iterator<Character> tree = iterator();
    int height = getHeight(size);
    int length = (1 << height) - 1;
    char[][] printTable = new char[height][length];

    ArrayDeque<Integer> deque = new ArrayDeque<>();
    deque.offer(0);
    deque.offer(length - 1);

    int printTreePos = 0;
    while (tree.hasNext()) {
      int start = deque.poll();
      int end = deque.poll();
      int printTablePos = (start + end) >> 1;
      int printLayer = getHeight(printTreePos + 1) - 1;

      printTable[printLayer][printTablePos] = tree.next();

      if (start == end) {
        continue;
      }
      deque.offer(start);
      deque.offer(printTablePos - 1);
      deque.offer(printTablePos + 1);
      deque.offer(end);
      printTreePos++;
    }

    //print
    StringBuilder boarder = new StringBuilder();
    for (int j = 0; j < length + 4; j++) {
      boarder.append('-');
    }
    boarder.append("\n");

    StringBuilder output = new StringBuilder();
    for (int i = 0; i < height; i++) {
      output.append(i == 0 ? boarder : "");
      for (int j = 0; j < length; j++) {
        output.append(j == 0 ? "| " : "")
            .append(printTable[i][j] == 0 ? " " : printTable[i][j])
            .append(j == length - 1 ? " |\n" : "");
      }
      output.append(i == height - 1 ? boarder : "");
    }
    System.out.println(output.toString());
  }

  /**
   * 获取存放 num 个元素的 完全二叉树 高度 , 即获得满足 2^h-1 > num 的最小h值
   * - 通过5次与运算(参考java集合扩容) 可以获得2^h-1 ,但是需要log等操作获得h
   * - 多次移位(除2)计数 ,效率更高
   *
   * @param num 元素个数
   * @return 树高
   */
  default int getHeight(int num) {
    int time = 0;
    while (num > 0) {
      num >>= 1;
      time++;
    }
    return time;
  }
}
