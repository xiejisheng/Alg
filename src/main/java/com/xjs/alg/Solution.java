package com.xjs.alg;
class Solution {
  public static void main(String[] args) {
      String s = "abc";
      String parlin = getShortestPal(s);
      System.out.print(parlin);
  }
  
  
 static String getShortestPal (String s) {
    // Corner case
    // Cut the s, and get the reversion of the substring
    for (int i = s.length()-1; i > 0; i--) {
        if (isPanlindrome(s.substring(0, i))) {
            String reverse = reverse(s.substring(i));
            return reverse + s;
        }
    }
    // Return
    return s; //"", a,b
  }
  
            
  static String reverse (String s) {
    char[] a = s.toCharArray();
    int i = 0;
    int j = s.length() - 1;
    while(i < j) {
      char tmp = a[i];
      a[i] = a[j];
      a[j] = tmp;
      i++;
      j--;
    }
    return a.toString();
  }
            
            
            
  static boolean isPanlindrome (String s) {
    int i = 0;
    int j = s.length() - 1;
    
    while(i < j) {
      if (s.charAt(i++) != s.charAt(j--)) {
        return false;
      }

    }
    return true;
  }
  
  
}