package org.example.xyl.top100.combination;

import java.util.ArrayList;
import java.util.List;

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

    public static void main(String[] args) {
        CombinationSolution solution = new CombinationSolution();
        int[] candidates = {2, 3, 6, 7};
        List<List<Integer>> list = solution.combinationSum(candidates, 7);
        System.out.println(list.toString());
    }

}
