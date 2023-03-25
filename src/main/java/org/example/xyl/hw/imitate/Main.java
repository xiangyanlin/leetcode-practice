package org.example.xyl.hw.imitate;

import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author xiangyanlin
 * @date 2023/3/18
 */
public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Set<Integer> set = new TreeSet<>();
        int n = in.nextInt();
        // 注意 hasNext 和 hasNextLine 的区别
        while (in.hasNextInt()) {
            // 注意 while 处理多个 case
            int a = in.nextInt();
            set.add(a);
        }
        set.forEach(System.out::println);
    }
}
