package org.example.xyl.swordfingeroffer.analog;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xiangyanlin
 * @date 2022/7/11
 */
public class StringSolution {

    //-----------------剑指 Offer 20. 表示数值的字符串---------------

    /**
     * 数值（按顺序）可以分成以下几个部分：
     * <p>
     * 若干空格
     * 一个小数 或者 整数
     * （可选）一个 'e' 或 'E' ，后面跟着一个 整数
     * 若干空格
     * 小数（按顺序）可以分成以下几个部分：
     * <p>
     * （可选）一个符号字符（'+' 或 '-'）
     * 下述格式之一：
     * 至少一位数字，后面跟着一个点 '.'
     * 至少一位数字，后面跟着一个点 '.' ，后面再跟着至少一位数字
     * 一个点 '.' ，后面跟着至少一位数字
     * 整数（按顺序）可以分成以下几个部分：
     * <p>
     * （可选）一个符号字符（'+' 或 '-'）
     * 至少一位数字
     * <p>
     */
    public boolean isNumber(String s) {
        // s为空对象或 s长度为0(空字符串)时, 不能表示数值
        if(s == null || s.length() == 0) {
            return false;
        }
        // 标记是否遇到数位、小数点、‘e’或'E'
        boolean isNum = false, isDot = false, ise_or_E = false;
        // 删除字符串头尾的空格，转为字符数组，方便遍历判断每个字符
        char[] str = s.trim().toCharArray();
        for(int i=0; i<str.length; i++) {
            // 判断当前字符是否为 0~9 的数位
            if(str[i] >= '0' && str[i] <= '9') {
                isNum = true;
            } else if(str[i] == '.') {
                // 遇到小数点
                if(isDot || ise_or_E) {
                    // 小数点之前可以没有整数，但是不能重复出现小数点、或出现‘e’、'E'
                    return false;
                }
                // 标记已经遇到小数点
                isDot = true;
            }
            else if(str[i] == 'e' || str[i] == 'E') {
                // 遇到‘e’或'E'
                if(!isNum || ise_or_E) {
                    // ‘e’或'E'前面必须有整数，且前面不能重复出现‘e’或'E'
                    return false;
                }
                // 标记已经遇到‘e’或'E'
                ise_or_E = true;
                // 重置isNum，因为‘e’或'E'之后也必须接上整数，防止出现 123e或者123e+的非法情况
                isNum = false;
            }
            else if(str[i] == '-' ||str[i] == '+') {
                if(i!=0 && str[i-1] != 'e' && str[i-1] != 'E') {
                    // 正负号只可能出现在第一个位置，或者出现在‘e’或'E'的后面一个位置
                    return false;
                }
            }
            else {
                // 其它情况均为不合法字符
                return false;
            }
        }
        return isNum;
    }


    //---------- 剑指 Offer 67. 把字符串转换成整数 ---------------

    public int strToInt(String str) {
        char[] c = str.trim().toCharArray();
        if(c.length == 0) {
            return 0;
        }
        //注意此处Integer范围的判断 bndry 最大值的前一位
        int res = 0, bndry = Integer.MAX_VALUE / 10;
        //开始判断数字位置
        int i = 1, sign = 1;
        if(c[0] == '-') {
            sign = -1;
        } else if(c[0] != '+') {
            i = 0;
        }
        for(int j = i; j < c.length; j++) {
            if(c[j] < '0' || c[j] > '9') {
                break;
            }
            if(res > bndry || res == bndry && c[j] > '7') {
                return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }
            //(c[j] - '0') c[j] 的in 值
            res = res * 10 + (c[j] - '0');
        }
        return sign * res;
    }

    //------------------ 剑指 Offer 38. 字符串的排列-----------------

    /**
     * 无重复全排列
     */
    List<String> res ;
    char[] chars;
    public String[] permutation(String s) {
        res = new ArrayList<>();
        if (s == null || s.length() == 0) {
            return res.toArray(new String[0]);
        }
        chars = s.toCharArray();
        //递归
        process(0);
        return res.toArray(new String[0]);
    }

    public  void process( int i) {
        if (i == chars.length) {
            res.add(String.valueOf(chars));
        }
        // visit[0 1 .. 25]
        boolean[] visit = new boolean[26];
        for (int j = i; j < chars.length; j++) {
            if (!visit[chars[j] - 'a']) {
                visit[chars[j] - 'a'] = true;
                swap(chars, i, j);
                process( i + 1);
                swap(chars, i, j);
            }
        }
    }

    public  void swap(char[] chs, int i, int j) {
        char tmp = chs[i];
        chs[i] = chs[j];
        chs[j] = tmp;
    }


    public static void main(String[] args) {
        StringSolution solution = new StringSolution();
//        boolean isNumber = solution.isNumber("3.");
//        System.out.println(isNumber);
//        System.out.println(Integer.MAX_VALUE );
//        System.out.println(Integer.MAX_VALUE );
//        System.out.println(Math.pow(2, 31) -1);
//        System.out.println(solution.strToInt("as"));
//        String[] abcs = solution.permutation("abc");
//        for (String s: abcs){
//            System.out.println(s);
//        }
        System.out.println('A' - 'a');
        System.out.println('f' - 'a');
    }
}
