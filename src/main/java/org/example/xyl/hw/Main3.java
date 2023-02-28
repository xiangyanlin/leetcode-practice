package org.example.xyl.hw;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author xiangyanlin
 * @date 2023/2/28
 */
public class Main3 {
    public static void main(String[] args) {
        //HJ27.查找兄弟单词
        Scanner scanner = new Scanner(System.in);
        //字典中单词的个数n
        int n = scanner.nextInt();

        String dicts[] = new String[n];
        for (int i = 0; i < n; i++) {
            //再输入n个单词作为字典单词
            dicts[i] = scanner.next();
        }
        // 然后输入一个单词x 最后后输入一个整数k
        String x = scanner.next();
        int k = scanner.nextInt();
        //第一行输出查找到x的兄弟单词的个数m
        Map<Character, Integer> xchMap = new HashMap<>(16);
        for (int i = 0; i < x.length(); i++) {
            xchMap.put(x.charAt(i), xchMap.getOrDefault(x.charAt(i), 0) + 1);
        }

        int m = 0;
        List<String> brother = new ArrayList<String>();

        for (int i = 0; i < n; i++) {
            String dict = dicts[i];
            //优化  排序后x和dict是否相等
            if (dict.length() != x.length() || Objects.equals(dict, x)) {
                continue;
            }
            Map<Character, Integer> dictchMap = new HashMap<>(16);
            for (int j = 0; j < dict.length(); j++) {
                dictchMap.put(dict.charAt(j), dictchMap.getOrDefault(dict.charAt(j), 0) + 1);
            }
            AtomicBoolean isBrother = new AtomicBoolean(true);
            dictchMap.forEach((key, val) -> {
                if (xchMap.get(key) == null || !xchMap.get(key).equals(val)) {
                    isBrother.set(false);
                }
            });
            if (isBrother.get()) {
                //包含相同字母且个数相同
                brother.add(dict);
                m++;
            }

        }
        System.out.println(m);
        // 第二行输出查找到的按照字典顺序排序后的第k个兄弟单词，没有符合第k个的话则不用输出
        if (k > brother.size()) {
            return;
        }
        Collections.sort(brother);
        System.out.println(brother.get(k - 1));
    }
}
