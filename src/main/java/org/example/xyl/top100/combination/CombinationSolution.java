package org.example.xyl.top100.combination;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author xiangyanlin
 * @date 2022/9/20
 */
public class CombinationSolution {


    int[] candidates;
    List<List<Integer>> ans;
    List<Integer> combine;

    /**
     * 组合总数
     *
     * @param candidates 1 <= candidates.length <= 30
     *                   1 <= candidates[i] <= 200
     *                   candidate 中的每个元素都 互不相同
     * @param target     1 <= target <= 500
     * @return 组合
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        this.ans = new ArrayList<>();
        this.combine = new ArrayList<>();
        this.candidates = candidates;
        dfs(target, 0);
        return ans;
    }

    public void dfs(int target, int idx) {
        if (idx == candidates.length) {
            return;
        }
        if (target == 0) {
            ans.add(new ArrayList<>(combine));
            return;
        }
        // 直接跳过
        dfs(target, idx + 1);
        // 选择当前数
        if (target - candidates[idx] >= 0) {
            combine.add(candidates[idx]);
            dfs(target - candidates[idx], idx);
            combine.remove(combine.size() - 1);
        }
    }

    /**
     * 全排列
     *
     * @param nums 1 <= nums.length <= 6
     *             -10 <= nums[i] <= 10
     *             nums 中的所有整数 互不相同
     * @return 所有不从夫的排列
     */
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> combine = new ArrayList<>();
        if (nums.length == 1) {
            combine.add(nums[0]);
            res.add(combine);
            return res;
        }
        process(nums, 0, res);
        return res;
    }


    public  void process(int[] nums, int i, List<List<Integer>> res) {
        if (i == nums.length) {
            List<Integer> list = Arrays.stream(nums).boxed().collect(Collectors.toList());
            res.add(list);
        }
        for (int j = i; j < nums.length; j++) {
            swap(nums, i, j);
            process(nums, i + 1, res);
            swap(nums, i, j);
        }
    }

    public void process2(int[] nums, int i, List<List<Integer>> res) {
        if (i == nums.length) {
            List<Integer> list = Arrays.stream(nums).boxed().collect(Collectors.toList());
            res.add(list);
        }
        // visit[0 1 .. 19] -10 <= nums[i] <= 10
        boolean[] visit = new boolean[21];
        for (int j = i; j < nums.length; j++) {
            if (!visit[nums[j] + 10]) {
                visit[nums[j] + 10] = true;
                swap(nums, i, j);
                process2(nums, i + 1, res);
                swap(nums, i, j);
            }
        }
    }

    public void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public List<List<Integer>> permute1(int[] nums) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();

        List<Integer> output = new ArrayList<Integer>();
        for (int num : nums) {
            output.add(num);
        }

        int n = nums.length;
        backtrack(n, output, res, 0);
        return res;
    }

    public void backtrack(int n, List<Integer> output, List<List<Integer>> res, int first) {
        // 所有数都填完了
        if (first == n) {
            res.add(new ArrayList<Integer>(output));
        }
        for (int i = first; i < n; i++) {
            // 动态维护数组
            Collections.swap(output, first, i);
            // 继续递归填下一个数
            backtrack(n, output, res, first + 1);
            // 撤销操作
            Collections.swap(output, first, i);
        }
    }


    public static void main(String[] args) {
        CombinationSolution solution = new CombinationSolution();
        int[] candidates = {2, 3, 6, 7};
//        List<List<Integer>> list = solution.combinationSum(candidates, 7);
//        System.out.println(list.toString());

        int[] nums = {1,2,3};
        List<List<Integer>> permute = solution.permute(nums);
        System.out.println(permute.toString());
    }

}
