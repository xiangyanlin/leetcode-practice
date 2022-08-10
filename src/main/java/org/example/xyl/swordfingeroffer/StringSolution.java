package org.example.xyl.swordfingeroffer;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 剑指offer-String
 */
public class StringSolution {

    /**
     * 请实现一个函数，把字符串 s 中的每个空格替换成"%20"。
     */
    public String replaceSpace(String s) {
        return s.replace(" ", "%20");
    }

    public String replaceSpaceSolution(String s) {
        int length = s.length();
        char[] array = new char[length * 3];
        int size = 0;
        for (int i = 0; i < length; i++) {
            char c = s.charAt(i);
            if (c == ' ') {
                array[size++] = '%';
                array[size++] = '2';
                array[size++] = '0';
            } else {
                array[size++] = c;
            }
        }
        String newStr = new String(array, 0, size);
        return newStr;

    }

    public String reverseLeftWords(String s, int n) {
        if (n <= 0) {
            return s;
        }
        Queue<Character> queue = new LinkedList();
        int length = s.length();
        char[] array = new char[length ];
        int size = 0;
        for (int i = 0; i < length; i++) {
            char c = s.charAt(i);
            //取n个char放在行位
            if (n > i) {
                queue.add(c);
            } else {
                array[size++] = c;
            }
        }
        while (!queue.isEmpty()) {
            array[size++] = queue.poll();
        }
        return  new String(array, 0, size);
    }

    public String reverseLeftWords1(String s, int n) {
        return s.substring(n, s.length()) + s.substring(0, n);
    }

    public String reverseLeftWords2(String s, int n) {
        StringBuilder res = new StringBuilder();
        for(int i = n; i < s.length(); i++)
            res.append(s.charAt(i));
        for(int i = 0; i < n; i++)
            res.append(s.charAt(i));
        return res.toString();
    }

    public String reverseLeftWords3(String s, int n) {
        String res = "";
        for(int i = n; i < s.length(); i++)
            res += s.charAt(i);
        for(int i = 0; i < n; i++)
            res += s.charAt(i);
        return res;
    }



}
