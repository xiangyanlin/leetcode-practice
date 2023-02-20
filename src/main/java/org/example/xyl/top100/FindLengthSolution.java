package org.example.xyl.top100;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xiangyanlin
 * @date 2023/2/20
 */
public class FindLengthSolution {

    /**
     * @param nums 1 <= nums.length <= 104
     *             -109 <= nums[i] <= 109
     * @return 长度
     */
    public int findLengthOfLCIS(int[] nums) {
        if (nums.length == 1) {
            return 1;
        }
        int l = 0, r = 1;
        int max = 1;
        while (l < nums.length && r < nums.length && l < r) {
            if (nums[r - 1] < nums[r]) {
                max = Math.max(max, r - l + 1);
                r++;
            } else {
                l++;
                r = l + 1;
            }
        }
        return max;
    }


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
        boolean[] lowerVisit = new boolean[26];
        boolean[] upperVisit = new boolean[26];
        for (int j = i; j < chars.length; j++) {
            if(Character.isLowerCase(chars[j])) {
                if (!lowerVisit[chars[j] - 'a']) {
                    lowerVisit[chars[j] - 'a'] = true;
                    swap(chars, i, j);
                    process( i + 1);
                    swap(chars, i, j);
                }
            } else {
                if (!upperVisit[chars[j] - 'A']) {
                    upperVisit[chars[j] - 'A'] = true;
                    swap(chars, i, j);
                    process( i + 1);
                    swap(chars, i, j);
                }
            }
        }
    }

    public  void swap(char[] chs, int i, int j) {
        char tmp = chs[i];
        chs[i] = chs[j];
        chs[j] = tmp;
    }

    public static void main(String[] args) {
        FindLengthSolution solution = new FindLengthSolution();
        System.out.println(solution.findLengthOfLCIS(new int[]{1, 3, 5, 4, 7}));
    }
}
