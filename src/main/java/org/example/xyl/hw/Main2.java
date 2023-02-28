package org.example.xyl.hw;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author xiangyanlin
 * @date 2023/2/27
 */
public class Main2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        // 注意 while 处理多个 case
        //HJ14.字符串排序
        int size = in.nextInt();
        String[] res = new String[size];
        for (int i = 0; i < size; i++) {
            res[i] = in.next();
        }
        Arrays.sort(res);
        for (int i = 0; i < size; i++) {
            System.out.println(res[i]);
        }
    }
}