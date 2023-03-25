package org.example.xyl.hw.examin.two;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @author xiangyanlin
 * @date 2023/3/18
 */
public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String content = in.next();
        String word = in.next();

        Map<Character, Integer> map = new HashMap<>();
        char[] wordChars = word.toCharArray();
        for (char wordChar : wordChars) {
            map.put(wordChar, map.getOrDefault(wordChar, 0) + 1);
        }

        char[] contentChars = content.toCharArray();

        int res = 0;
        Map<Character, Integer> temp = new HashMap<>(map);

        int start = 0;
        for (char contentChar : contentChars) {

            if (temp.containsKey(contentChar)) {
                if (temp.get(contentChar) > 1) {
                    temp.put(contentChar, map.get(contentChar) - 1);
                    continue;
                }
                temp.remove(contentChar);
                if (temp.isEmpty()) {
                    res++;
                    temp = new HashMap<>(map);
                }
                continue;
            }
            //此元素不在剩余之中 重新开始
            temp = new HashMap<>(map);


            if(temp.containsKey(contentChar)) {
                if (temp.get(contentChar) > 1) {
                    temp.put(contentChar, map.get(contentChar) - 1);
                    continue;
                }
                temp.remove(contentChar);
            }

        }
        System.out.println(res);
    }
}
