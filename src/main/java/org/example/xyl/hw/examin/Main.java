package org.example.xyl.hw.examin;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author xiangyanlin
 * @date 2023/3/18
 */
public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int M = Integer.parseInt(in.nextLine());
        String s = in.nextLine();
        String[] sStr = s.split(" ");
        String size = in.nextLine();
        String[] sizeStr = size.split(" ");

        //出现次数
        Map<String, Integer> sumMap = new HashMap<>();
        //大小
        Map<String, Integer> sizeMap = new HashMap<>();


        for (int i = 0; i < sStr.length; i++) {
            sumMap.put(sStr[i], sumMap.getOrDefault(sStr[i], 0) + 1);
            sizeMap.put(sStr[i], Integer.valueOf(sizeStr[i]));
        }

        AtomicInteger res = new AtomicInteger();
        sumMap.forEach((k, v) -> res.addAndGet(Math.min(sizeMap.get(k) * v, M + sizeMap.get(k))));
        System.out.println(res.get());
    }
}
