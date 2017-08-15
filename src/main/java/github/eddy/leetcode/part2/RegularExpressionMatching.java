package github.eddy.leetcode.part2;

import org.junit.Test;

public class RegularExpressionMatching {

  public boolean isMatch(String s, String p) {
    if (p.isEmpty()) {
      return s.isEmpty();
    }

    if (p.length() == 1 || p.charAt(1) != '*') {
      if (s.isEmpty() || (p.charAt(0) != '.' && p.charAt(0) != s.charAt(0))) {
        return false;
      } else {
        return isMatch(s.substring(1), p.substring(1));
      }
    }

    //P.length() >=2
    while (!s.isEmpty() && (s.charAt(0) == p.charAt(0) || p.charAt(0) == '.')) {
      if (isMatch(s, p.substring(2))) {
        return true;
      }
      s = s.substring(1);
    }

    return isMatch(s, p.substring(2));
  }


  public boolean _isMatch(String s, String p) {
    if (p.isEmpty()) {
      return s.isEmpty();
    }

    if (p.length() == 1) {
      if (s.isEmpty() || (p.charAt(0) != '.' && p.charAt(0) != s.charAt(0))) {
        return false;
      } else {
        return isMatch(s.substring(1), p.substring(1));
      }
    }

    if (p.charAt(0) == '.' || p.charAt(0) == s.charAt(0)) {
      if (p.charAt(1) == '*') {
        return isMatch(s.substring(1), p);
      } else {
        return isMatch(s.substring(1), p.substring(1));
      }
    }else{
      return false;
    }
  }

  @Test
  public void test() {
    System.out.println(_isMatch("abcaa", "ab*ca*b*"));
  }
}
