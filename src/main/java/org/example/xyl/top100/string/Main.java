package org.example.xyl.top100.string;

import java.util.Iterator;
import java.util.Scanner;
import java.util.TreeSet;

/**
 * @author xiangyanlin
 * @date 2022/12/7
 */
public class Main {

//    public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);
//        // 注意 hasNext 和 hasNextLine 的区别
//        while (in.hasNextLine()) { // 注意 while 处理多个 case
//            String s = in.nextLine();
//
//            int res = Integer.parseInt(s.substring(2, s.length()), 16);
//            System.out.println(res);
//
//            char[] chars = s.toCharArray();
//            Set<Character> exist = new HashSet<>();
//            int r = 0;
//            for (char ch:chars){
//                if (!exist.contains(ch)) {
//                    exist.add(ch);
//                    r++;
//                }
//            }
//            System.out.println(r);
//        }
//    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //获取个数
        int num = sc.nextInt();

        TreeSet<Integer> set = new TreeSet<>();
        //输入
        for(int i =0 ; i < num ;i++){
            set.add(sc.nextInt());
        }

        //输出
        for (Integer integer : set) {
            System.out.println(integer);
        }
    }
}
