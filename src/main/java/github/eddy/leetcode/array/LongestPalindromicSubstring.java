package github.eddy.leetcode.array;

public class LongestPalindromicSubstring {

  /**
   * 2017/07/17
   * select a char in string ,and check sub-string which is around the char is Palindrome.
   * .....a.... : select s[i]=a
   * ....bab... : check s[i-1]-s[i+1] ,next
   * ...cbabd.. : check s[i-2]-s[i+2] , not equals , return bab
   */
  public String longestPalindrome(String s) {
    //if only have 1 char
    if (s.length() < 2) {
      return s;
    }

    String result = "";
    for (int i = 0; i < s.length(); i++) {
      //get palindrome from s[i] to left and right side
      String temp = findPalindrome(s, i, i);
      //replace if new string is longer
      if (result.length() < temp.length()) {
        result = temp;
      }
    }
    return result;
  }

  /**
   * to reduce running time ,use more variables to store data ,and check 'abcba' and 'abccba'
   * together.
   */
  public String findPalindrome(String s, int s1, int s2) {
    int start1 = s1, end1 = s2, start2 = s1, end2 = s2 + 1;

    boolean stop1 = false, stop2 = false;
    while (true) {
      //if elements in symmetrical position are same ,move the pointer to next
      if (start1 >= 0 && end1 < s.length() && s.charAt(start1) == s.charAt(end1)) {
        start1--;
        end1++;
      } else {
        stop1 = true;
      }
      if (start2 >= 0 && end2 < s.length() && s.charAt(start2) == s.charAt(end2)) {
        start2--;
        end2++;
      } else {
        stop2 = true;
      }

      //move start&end pointer util both string has checked.
      if (stop1 && stop2) {
        break;
      }
    }

    if (end1 - start1 > end2 - start2) {
      return s.substring(start1 + 1, end1);//like 'abcba' ,the middle is a single element
    } else {
      return s.substring(start2 + 1, end2);//like 'abccba' ,2 same element in middle
    }
  }


  public static void main(String[] a) {
    LongestPalindromicSubstring lab = new LongestPalindromicSubstring();
    System.out.println(lab.longestPalindrome(
        "xxxaxxxsf"));
  }
}
