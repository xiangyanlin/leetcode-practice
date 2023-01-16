package org.example.xyl.nc.string;

/**
 * NC100 把字符串转换成整数(atoi)
 * @author xiangyanlin
 * @date 2023/1/16
 */
public class StrToIntSolution {

    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     * @param s string字符串
     * @return int整型
     */
    public int StrToInt(String s) {
        // write code here
        char[] chars = s.trim().toCharArray();

        int sign = s.trim().startsWith("-") ? -1 : 1;
        long res = 0L;
        for (int i = 0; i < chars.length; i++) {
            if (i == 0 & (chars[i] == '-' || chars[i] == '+')) {
                continue;
            }
            if (i == 0 & (!Character.isDigit(chars[i]))) {
                return 0;
            }
            if (!Character.isDigit(chars[i])) {
                return sign == 1 ?
                        (res <= Integer.MAX_VALUE ? (int) res : Integer.MAX_VALUE) :
                        ((res * sign) >= Integer.MIN_VALUE ? (int) (sign * res) : Integer.MIN_VALUE);
            }
            res = Math.abs(res * 10 + Integer.parseInt(String.valueOf(chars[i])));
        }
        return sign == 1 ?
                (res <= Integer.MAX_VALUE ? (int) res : Integer.MAX_VALUE) :
                ((res * sign) >= Integer.MIN_VALUE ? (int) (sign * res) : Integer.MIN_VALUE);

    }

    public static void main(String[] args) {
        StrToIntSolution solution = new StrToIntSolution();
//        System.out.println(solution.StrToInt("82"));
//        System.out.println(solution.StrToInt("+82"));\


        System.out.println(solution.StrToInt("13333744073709551617"));

    }
}
