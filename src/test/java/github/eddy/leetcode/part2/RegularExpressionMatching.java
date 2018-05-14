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

  /**
   * my method
   */
  public boolean _isMatch(String s, String p) {
    //if p is simple '' or char
    if (p.isEmpty()) {
      return s.isEmpty();
    }

    /**
     *   return false :
     *   p is simple char & s is not match it
     *   p is not simple char & s is not matched & p is not x* ,so it can't ignore
     */
    if (p.length() == 1 || p.charAt(1) != '*') {
      if (s.isEmpty() || (p.charAt(0) != '.' && p.charAt(0) != s.charAt(0))) {
        return false;
      }
      //matched p[0] s[0]
      return _isMatch(s.substring(1), p.substring(1));
    }

    if (!s.isEmpty() && (p.charAt(0) == '.' || p.charAt(0) == s.charAt(0))) {
      if (p.charAt(1) != '*') {
        return _isMatch(s.substring(1), p.substring(1));
      }
      /**
       *  test if substring is matched then ignore p[0,1] ,
       *  like 'aaa' 'a*a' ,
       *   s             p
       *  'aa' match 'a*'
       *  test 'a' match 'a' ,so ignore 'a*' to match last 'a'
       *  return true : 'aa a' matched 'a* a'
       */
      if (_isMatch(s, p.substring(2))) {
        return true;
      }
      return _isMatch(s.substring(1), p);
    }
    return _isMatch(s, p.substring(2));
  }

  @Test
  public void test() {
    System.out.println(_isMatch("aaa", "a*a"));
  }
}
