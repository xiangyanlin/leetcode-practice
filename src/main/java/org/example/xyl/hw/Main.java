package org.example.xyl.hw;

import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * @author xiangyanlin
 * @date 2023/1/9
 */
public class Main {
    static Pattern p1 = Pattern.compile("[A-Z]");
    static Pattern p2 = Pattern.compile("[a-z]");
    static Pattern p3 = Pattern.compile("[0-9]");
    static Pattern p4 = Pattern.compile("[^A-Za-z0-9]");
    public static void main(String[] args) {

        //HJ7取近似值
//        Scanner scanner = new Scanner(System.scanner);
//        double floatValue = scanner.nextDouble();
//        int res = (int) (floatValue + 0.5d);
//        System.out.println(res);

        //HJ17坐标移动
//        Scanner scanner = new Scanner(System.in);
//        int x = 0, y = 0;
//        while (scanner.hasNext()) {
//            String next = scanner.next();
//            String[] points = next.split(";");
//            for (String point : points) {
//                if (!point.matches("[WASD][0-9]{1,2}")) {
//                    continue;
//                }
//                String prefix = point.substring(0, 1);
//                int val = Integer.valueOf(point.substring(1));
//                switch (prefix){
//                    case "A":
//                        x -= val;
//                        break;
//                    case "D":
//                        x += val;
//                        break;
//                    case "W":
//                        y += val;
//                        break;
//                    case "S":
//                        y-=val;
//                        break;
//                    default:
//                }
//            }
//            System.out.println(x +","+ y);
//        }

        //HJ20密码验证合格程序
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String password = scanner.next();
            //.长度超过8位
            if(password.length() <= 8) {
                System.out.println("NG");
                continue;
            }
            //大小写字母.数字.其它符号,以上四种至少三种
            int count = 0;

            if(p1.matcher(password).find()) {
                count ++;
            }
            if(p2.matcher(password).find()) {
                count ++;
            }
            if(p3.matcher(password).find()) {
                count ++;
            }
            if(p4.matcher(password).find()) {
                count ++;
            }
            if(count < 3){
                System.out.println("NG");
                continue;
            }
            //不能有长度大于2的包含公共元素的子串重复
            if(getString(password, 0, 3)){
                System.out.println("NG");
                continue;
            }
            System.out.println("OK");
        }

    }

    private static boolean getString(String str, int l, int r) {
        if (r >= str.length()) {
            return false;
        }
        if (str.substring(r).contains(str.substring(l, r))) {
            return true;
        } else {
            return getString(str,l+1,r+1);
        }
    }
}
