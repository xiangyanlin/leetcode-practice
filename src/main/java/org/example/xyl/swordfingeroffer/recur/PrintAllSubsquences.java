package org.example.xyl.swordfingeroffer.recur;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * 打印字符串子序列
 * 从左到右模型k
 * @author xiangyanlin
 * @date 2022/7/10
 */
public class PrintAllSubsquences {

	/**
	 * 字符串全部子序列
	 */
	static List<String> ans = new ArrayList<>();
	public static List<String> subs(String s) {
		char[] str = s.toCharArray();
		String path = "";

		process1(str, 0, path);
		return ans;
	}

	public static void process1(char[] str, int index, String path) {
		if (index == str.length) {
			ans.add(path);
			return;
		}
		String no = path;
		process1(str, index + 1, no);
		String yes = path + str[index];
		process1(str, index + 1, yes);
	}

	/**
	 * 字符串不要重复值的子序列
	 */
	public static List<String> subsNoRepeat(String s) {
		char[] str = s.toCharArray();
		String path = "";
		HashSet<String> set = new HashSet<>();
		process2(str, 0, set, path);
		return new ArrayList<>(set);
	}

	public static void process2(char[] str, int index, HashSet<String> set, String path) {
		if (index == str.length) {
			set.add(path);
			return;
		}
		String no = path;
		process2(str, index + 1, set, no);
		String yes = path + str[index];
		process2(str, index + 1, set, yes);
	}

	public static void main(String[] args) {
		String test = "aacc";
		List<String> ans1 = subs(test);
		List<String> ans2 = subsNoRepeat(test);

		for (String str : ans1) {
			System.out.println(str);
		}
		System.out.println("=================");
		for (String str : ans2) {
			System.out.println(str);
		}
		System.out.println("=================");

	}

}
