package org.example.xyl.hw.imitate;

import java.util.Scanner;

/**
 * @author xiangyanlin
 * @date 2023/3/18
 */
public class Main2 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        String source = in.next();
        char[] chars = source.toCharArray();
        int res = 0;
        for (int i = 0; i < chars.length; i++) {
            int index = chars.length - i - 1;
            if (Character.isDigit(chars[index])) {
                res += Character.getNumericValue(chars[index]) * Math.pow(16, i );
            }

            if (chars[index] == 'A') {
                res += 10 * Math.pow(16, i );
            }
            if (chars[index] == 'B') {
                res += 11 * Math.pow(16, i );
            }
            if (chars[index] == 'C') {
                res += 12 * Math.pow(16, i );
            }
            if (chars[index] == 'D') {
                res += 13 * Math.pow(16, i );
            }
            if (chars[index] == 'E') {
                res += 14 * Math.pow(16, i );
            }
            if (chars[index] == 'F') {
                res += 15 * Math.pow(16, i );
            }
        }
        System.out.println(res);
    }
}
