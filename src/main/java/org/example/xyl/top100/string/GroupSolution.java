package org.example.xyl.top100.string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author xiangyanlin
 * @date 2022/9/23
 */
public class GroupSolution {


    /**
     *  字母以为词分离
     * @param strs 1 <= strs.length <= 104
     *             0 <= strs[i].length <= 100
     *             strs[i] 仅包含小写字母
     * @return 集合
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> result = new ArrayList<>();
        if(strs.length == 1) {
            List<String> collect = Arrays.stream(strs).collect(Collectors.toList());
            result.add(collect);
            return  result;
        }
        //记录分组标识
        Map<String, List<String>> map = new HashMap<>(16);
        for (String str : strs) {
            //每一个单词记录一份
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            String key = Arrays.toString(chars);
            if(map.containsKey(key)) {
                List<String> group = map.get(key);
                group.add(str);
                continue;
            }
            List<String> group = new ArrayList<>();
            group.add(str);
            map.put(key, group);
        }
        result = new ArrayList<>(map.values());
        return result;
    }

    /**
     *  官方答案1
     */
    public List<List<String>> groupAnagrams1(String[] strs) {
        Map<String, List<String>> map = new HashMap<String, List<String>>();
        for (String str : strs) {
            char[] array = str.toCharArray();
            Arrays.sort(array);
            String key = new String(array);
            List<String> list = map.getOrDefault(key, new ArrayList<String>());
            list.add(str);
            map.put(key, list);
        }
        return new ArrayList<List<String>>(map.values());
    }

    /**
     *  官方答案2
     */
    public List<List<String>> groupAnagrams2(String[] strs) {
        Map<String, List<String>> map = new HashMap<String, List<String>>();
        for (String str : strs) {
            int[] counts = new int[26];
            int length = str.length();
            for (int i = 0; i < length; i++) {
                counts[str.charAt(i) - 'a']++;
            }
            // 将每个出现次数大于 0 的字母和出现次数按顺序拼接成字符串，作为哈希表的键
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < 26; i++) {
                if (counts[i] != 0) {
                    sb.append((char) ('a' + i));
                    sb.append(counts[i]);
                }
            }
            String key = sb.toString();
            List<String> list = map.getOrDefault(key, new ArrayList<String>());
            list.add(str);
            map.put(key, list);
        }
        return new ArrayList<List<String>>(map.values());
    }




    public static void main(String[] args) {
        GroupSolution solution = new GroupSolution();
        String[] strs = {"eat", "tea", "tan", "ate", "nat", "bat"};
        System.out.println(solution.groupAnagrams(strs).toString());
    }
}
