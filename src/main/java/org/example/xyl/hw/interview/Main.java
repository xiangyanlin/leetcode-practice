package org.example.xyl.hw.interview;

/**
 * @author xiangyanlin
 * @date 2023/3/25
 */
public class Main {

    //找最大积
    //给一个字符串，如“23987”，你可以在其中插入一个乘号，如“239*87”，请返回所有可能情况里最大的乘积值。
    //对于“23987”有“2*3987”，“23*987”，“239*87”，“2398*7”
    //结果分别是[7974, 22701, 20793, 16786]
    //最大值为“23*987=22701”
    //
    //返回22701


    public static void main(String[] args) {
        String params = "23987";
        int mul = 0;
        for (int i = 1; i < params.length() - 1; i++) {
            String pre = params.substring(0, i);
            int preInt = Integer.parseInt(pre);
            String end = params.substring(i, params.length());
            int endInt = Integer.parseInt(end);

            int curMul = preInt * endInt;
            mul = Math.max(curMul, mul);
        }
        System.out.println(mul);
    }


}
