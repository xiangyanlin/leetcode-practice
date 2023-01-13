package org.example.xyl.hw;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

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
//        Scanner scanner = new Scanner(System.in);
//        while (scanner.hasNext()) {
//            String password = scanner.next();
//            //.长度超过8位
//            if(password.length() <= 8) {
//                System.out.println("NG");
//                continue;
//            }
//            //大小写字母.数字.其它符号,以上四种至少三种
//            int count = 0;
//
//            if(p1.matcher(password).find()) {
//                count ++;
//            }
//            if(p2.matcher(password).find()) {
//                count ++;
//            }
//            if(p3.matcher(password).find()) {
//                count ++;
//            }
//            if(p4.matcher(password).find()) {
//                count ++;
//            }
//            if(count < 3){
//                System.out.println("NG");
//                continue;
//            }
//            //不能有长度大于2的包含公共元素的子串重复
//            if(getString(password, 0, 3)){
//                System.out.println("NG");
//                continue;
//            }
//            System.out.println("OK");
//        }

        // HJ23 删除字符串中出现次数最少的字符
//        Scanner in = new Scanner(System.in);
//        String str = in.next();
//        Map<Character, Integer> map  = new HashMap<>();
//        char[] chars = str.toCharArray();
//        for (char ch : chars) {
//            if(map.containsKey(ch)) {
//                map.put(ch, map.get(ch) + 1);
//            } else {
//                map.put(ch, 1);
//            }
//        }
//        List<Map.Entry<Character,Integer>> list = new ArrayList(map.entrySet());
//        list.sort(Comparator.comparingInt(Map.Entry::getValue));
//        int minCount = list.get(0).getValue();
//        List<Character> minCh = new ArrayList<>();
//        for (Map.Entry<Character,Integer> entry: list) {
//            if(entry.getValue() == minCount) {
//                minCh.add(entry.getKey());
//            }
//        }
//        char[] newChars = new char[chars.length - minCount * minCh.size()];
//        int i = 0;
//        for (char ch : chars) {
//            if(!minCh.contains(ch)) {
//                newChars[i++] = ch;
//            }
//        }
//        System.out.println(String.valueOf(newChars));

        // HJ33 整数与IP地址间的转换
//        Scanner in = new Scanner(System.in);
//        while (in.hasNext()) {
//            String str = in.next();
//            String res = convert(str);
//            System.out.println(res);
//        }

        // HJ46 截取字符串
//        Scanner scanner = new Scanner(System.in);
//        String str = scanner.next();
//        int len = scanner.nextInt();
//        String res = str.substring(0, len);
//        System.out.println(res);

        // HJ101 输入整型数组和排序标识，对其元素按照升序或降序进行排序
        Scanner scanner = new Scanner(System.in);
        String len = scanner.nextLine();
        String arrStr = scanner.nextLine();
        String flag = scanner.nextLine();

        String[] arr = arrStr.split(" ");
        List<Integer> list = Arrays.stream(arr)
                .map(Integer::valueOf).collect(Collectors.toList());
        if(Objects.equals(flag, "0")) {
            Collections.sort(list);
        } else {
            list.sort(Collections.reverseOrder());
        }
        String res = list.stream().map(String::valueOf).collect(Collectors.joining(" "));
        System.out.println(res);

    }

    public static String convert(String str) {
        // ipv4 -> int
        if (str.contains(".")) {
            String[] fields = str.split("\\.");
            long result = 0;
            for (int i = 0; i < 4; i++) {
                result = result * 256 + Integer.parseInt(fields[i]);
            }
            return "" + result;
        }
        // int -> ipv4
        else {
            long ipv4 = Long.parseLong(str);
            StringBuilder resultBuilder = new StringBuilder();
            for (int i = 0; i < 4; i++) {
                resultBuilder.insert(0, ipv4 % 256 + ".");
                ipv4 /= 256;
            }
            String result = resultBuilder.toString();
            return result.substring(0, result.length() - 1);
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
