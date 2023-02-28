package org.example.xyl.hw;

import java.util.HashMap;
import java.util.Scanner;

/**
 * @author xiangyanlin
 * @date 2023/2/27
 */
public class Main1 {
    public static void main(String[] args) {
        //HJ8.合并表记录
        Scanner scanner = new Scanner(System.in);

        int size = scanner.nextInt();
        HashMap<Integer, Integer> map = new HashMap<>(size);
        // 注意 while 处理多个 case
        while (scanner.hasNextInt()) {
            int key = scanner.nextInt();
            int value = scanner.nextInt();

            map.put(key, map.getOrDefault(key, 0) + value);

        }
        map.forEach((k, v) -> System.out.println(k + " " + v));
    }
}
