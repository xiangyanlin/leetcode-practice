package org.example.xyl.hw.imitate;

import java.util.Scanner;

/**
 * @author xiangyanlin
 * @date 2023/3/18
 */
public class Main1 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        while (in.hasNextInt()) {
            // 注意 while 处理多个 case
            int n = in.nextInt();

            if (n != 0) {
                System.out.println(getCount(n));
            }
        }
    }

    public static int getCount(int n) {
        if (n  >= 3) {
            int cur = n / 3;
            return getCount(n % 3 + cur) + cur;
        }
        if(n==2){
            return 1;
        }
        return 0;
    }
}
