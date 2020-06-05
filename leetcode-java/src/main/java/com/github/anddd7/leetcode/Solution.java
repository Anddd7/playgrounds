package com.github.anddd7.leetcode;

public class Solution {

  public static final Solution INSTANCE = new Solution();

  enum Direction {
    Right, Left, Down, Up, Next_Round;
  }

  public int[] spiralOrder(int[][] matrix) {
    if (matrix.length == 0) {
      return new int[0];
    }
    int[] result = new int[matrix.length * matrix[0].length];
    int x = 0;
    int y = 0;
    int round = 0;
    Direction current = Direction.Right;

    int index = 0;
    while (index < result.length) {
      result[index++] = matrix[y][x];
      Direction next = nextDirection(current, x, y, matrix[0].length, matrix.length, round);
      if (next == Direction.Next_Round) {
        round++;
        current = Direction.Right;
      } else {
        current = next;
      }
      if (current == Direction.Right) {
        x++;
      } else if (current == Direction.Down) {
        y++;
      } else if (current == Direction.Left) {
        x--;
      } else if (current == Direction.Up) {
        y--;
      }
    }
    return result;
  }

  private Direction nextDirection(
      Direction direction, int x, int y, int maxX, int maxY, int round
  ) {
    int edgeX = maxX - round - 1;
    int edgeY = maxY - round - 1;

    if (direction == Direction.Right && x == edgeX) {
      return Direction.Down;
    }
    if (direction == Direction.Down && y == edgeY) {
      return Direction.Left;
    }
    if (direction == Direction.Left && x == round) {
      return Direction.Up;
    }
    if (direction == Direction.Up && y == round + 1) {
      return Direction.Next_Round;
    }
    return direction;
  }
}
