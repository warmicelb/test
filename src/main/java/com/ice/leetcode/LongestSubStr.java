package com.ice.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 *
 * 示例 1:
 *
 * 输入: "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-substring-without-repeating-characters
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @ClassName LongestSubStr
 * @Description TODO
 * @Author liubin
 * @Date 2019/6/19 11:49 AM
 **/
public class LongestSubStr {
    public static void main(String[] args) {
        System.out.println(strLength("abba"));
    }

    /**
     * 一个个遍历，每进行一不，得出当前的最大的长度
     * @param s
     * @return
     */
    public static int strLength(String s){
            int l = 0,r  = -1, result = 0;
            int[] t = new int[256];
            char[] chars = s.toCharArray();
            while(r + 1 < chars.length) {
                if( t[chars[r+1]] == 0) t[chars[++r]]++;
                else t[chars[l++]]--;
                result = Math.max(r-l+1,result);
            }
            return result;
        }

    /**
     * 通过窗口滑动类似的思想
     * @param s
     * @return
     */
    public static int strLength1(String s){
        int maxLength = 0;
        int l =0;
        char[] chars = s.toCharArray();
        Map<Character,Integer> map = new HashMap<>();
        for(int i=0;i<=chars.length;i++){
            if(i==chars.length){
                return Math.max(i-l,maxLength);
            }
            if(map.containsKey(chars[i])&&map.get(chars[i])>=l){
                //存在
                maxLength = Math.max(maxLength,i-l);
                l=map.get(chars[i])+1;
            }
            map.put(chars[i],i);
        }
        return maxLength;
    }
    
}
