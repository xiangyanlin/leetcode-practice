package org.example.xyl.swordfingeroffer.dp;

import java.util.Arrays;
import java.util.HashMap;

/**
 * 要至少多少张贴纸可以 组成目标字符串
 *
 * @author xiangyanlin
 * @date 2022/7/21
 */
public class StickersToSpellWord {

	private final static int CHAR_NUM = 26;

	public static int minStickers1(String[] stickers, String target) {
		int n = stickers.length;
		int[][] map = new int[n][26];
		HashMap<String, Integer> dp = new HashMap<>(16);
		for (int i = 0; i < n; i++) {
			char[] str = stickers[i].toCharArray();
			for (char c : str) {
				map[i][c - 'a']++;
			}
		}
		dp.put("", 0);
		return process1(dp, map, target);
	}


	/**
	 * @param dp 傻缓存，如果t已经算过了，直接返回dp中的值
	 * @param t 剩余的目标
	 * @param map 0..N每一个字符串所含字符的词频统计
	 * @return  -1表示此方案不可行
	 */
	public static int process1(HashMap<String, Integer> dp, int[][] map, String t) {
		if (dp.containsKey(t)) {
			return dp.get(t);
		}
		int ans = Integer.MAX_VALUE;
		int[] tmap = new int[26];
		char[] target = t.toCharArray();
		// t的词频
		for (char c : target) {
			tmap[c - 'a']++;
		}
		//遍历贴纸
		for (int[] sticker : map) {
			//贴纸词频 target[0] == 0 也就是贴纸不包含剩余的第一个元素
			if (sticker[target[0] - 'a'] == 0) {
				continue;
			}
			StringBuilder sb = new StringBuilder();
			for (int j = 0; j < CHAR_NUM; j++) {
				// j这个字符是target需要的
				if (tmap[j] > 0) {
					for (int k = 0; k < Math.max(0, tmap[j] - sticker[j]); k++) {
						sb.append((char) ('a' + j));
					}
				}
			}
			//剩下的字符
			String s = sb.toString();
			int tmp = process1(dp, map, s);
			if (tmp != -1) {
				ans = Math.min(ans, 1 + tmp);
			}
		}
		dp.put(t, ans == Integer.MAX_VALUE ? -1 : ans);
		return dp.get(t);
	}

	public static int minStickers2(String[] stickers, String target) {
		int n = stickers.length;
		int[][] map = new int[n][26];
		for (int i = 0; i < n; i++) {
			char[] str = stickers[i].toCharArray();
			for (char c : str) {
				map[i][c - 'a']++;
			}
		}
		char[] str = target.toCharArray();
		int[] tmap = new int[26];
		for (char c : str) {
			tmap[c - 'a']++;
		}
		HashMap<String, Integer> dp = new HashMap<>(16);
		return process2(map, 0, tmap, dp);
	}

	public static int process2(int[][] map, int i, int[] tmap, HashMap<String, Integer> dp) {
		StringBuilder keyBuilder = new StringBuilder();
		keyBuilder.append(i).append("_");
		for (int asc = 0; asc < CHAR_NUM; asc++) {
			if (tmap[asc] != 0) {
				keyBuilder.append((char) (asc + 'a')).append("_").append(tmap[asc]).append("_");
			}
		}
		String key = keyBuilder.toString();
		if (dp.containsKey(key)) {
			return dp.get(key);
		}
		boolean finish = true;
		for (int asc = 0; asc < CHAR_NUM; asc++) {
			if (tmap[asc] != 0) {
				finish = false;
				break;
			}
		}
		if (finish) {
			dp.put(key, 0);
			return 0;
		}
		if (i == map.length) {
			dp.put(key, -1);
			return -1;
		}
		int maxZhang = 0;
		for (int asc = 0; asc < CHAR_NUM; asc++) {
			if (map[i][asc] != 0 && tmap[asc] != 0) {
				maxZhang = Math.max(maxZhang, (tmap[asc] / map[i][asc]) + (tmap[asc] % map[i][asc] == 0 ? 0 : 1));
			}
		}
		int[] backup = Arrays.copyOf(tmap, tmap.length);
		int min = Integer.MAX_VALUE;
		int next = process2(map, i + 1, tmap, dp);
		tmap = Arrays.copyOf(backup, backup.length);
		if (next != -1) {
			min = next;
		}
		for (int zhang = 1; zhang <= maxZhang; zhang++) {
			for (int asc = 0; asc < CHAR_NUM; asc++) {
				tmap[asc] = Math.max(0, tmap[asc] - (map[i][asc] * zhang));
			}
			next = process2(map, i + 1, tmap, dp);
			tmap = Arrays.copyOf(backup, backup.length);
			if (next != -1) {
				min = Math.min(min, zhang + next);
			}
		}
		int ans = min == Integer.MAX_VALUE ? -1 : min;
		dp.put(key, ans);
		return ans;
	}

	public static void main(String[] args) {

	}

}
