import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;


/**
 * LeetCode 242. Valid Anagram
 * https://leetcode.com/problems/valid-anagram/
 */
public class ValidAnagram {


    /**
     * Given two strings s and t,
     * return true if t is an anagram of s, and false otherwise.
     * Sort char[] arrays and compare.
     * 
     * Runtime: O(n ^ 2) - Space: O(1)
     * 
     * 36 / 36 test cases passed, but took too long.
     * Status: Time Limit Exceeded
     */
    static public boolean isAnagram0(String s, String t) {

        // **** sanity checks ****
        if (s.length() != t.length()) return false;

        // **** initialization ****
        char[] arr1 = s.toCharArray();
        char[] arr2 = t.toCharArray();

        // **** sort char[] arrays - O(n ^ 2) ****
        sort(arr1);
        sort(arr2);

        // **** traverse arrays comparing characters - O(n) ****
        for (int i = 0; i < arr1.length; i++)
            if (arr1[i] != arr2[i]) return false;

        // **** strings are anagrams ****
        return true;
    }


    /**
     * Sort character array in place.
     * Auxiliary function.
     * 
     * Runtime: O(n ^ 2) - Space: O(1)
     */
    static private void sort(char[] arr) {

        // **** sanity check ****
        if (arr.length == 1) return;

        // **** initialization ****
        char tmp;
        int len = arr.length;
        int i   = 0;
        int j   = 0;

        // **** O(n ^ 2) ****
        while (i < len) {

            // **** set index ****
            j = i + 1;

            // **** sort characters remeining characters - O(n) ****
            while (j < len) {

                // **** swap characters (if needed) ****
                if (arr[i] > arr[j]) {
                    tmp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = tmp; 
                }

                // **** increment index ****
                j++;
            }

            // **** increment index ****
            i++;
        }
    }


    /**
     * Given two strings s and t,
     * return true if t is an anagram of s, and false otherwise.
     * Using hashmap.
     * 
     * Runtime: O(n) - Space: O(n)
     * 
     * 36 / 36 test cases passed.
     * Status: Accepted
     * Runtime: 9 ms
     * Memory Usage: 39.5 MB
     */
    static public boolean isAnagram1(String s, String t) {

        // **** sanity checks ****
        if (s.length() != t.length()) return false;

        // **** initialization ****
        HashMap<Character, Integer> hm = new HashMap<>(s.length());
        
        // **** populate hashmap - O(n) ****
        for (char ch : s.toCharArray()) {
            Integer cnt = hm.putIfAbsent(ch, 1);
            if (cnt != null) hm.put(ch, ++cnt);
        }

        // *** traverse t removing values from hm - O(n) ****
        for (char ch : t.toCharArray()) {

            // **** get character count ****
            Integer cnt = hm.get(ch);

            // **** character not found ****
            if (cnt == null) return false;

            // **** update hashmap ****
            if (--cnt > 0) hm.put(ch, cnt);
            else hm.remove(ch);
        }

        // **** check if hashmap is empty ****
        return hm.size() == 0 ? true : false;
    }


    /**
     * Given two strings s and t,
     * return true if t is an anagram of s, and false otherwise.
     * Using hashmap.
     * 
     * Sort char[] arrays.
     * 
     * 36 / 36 test cases passed.
     * Status: Accepted
     * Runtime: 2 ms
     * Memory Usage: 39.3 MB
     */
    static public boolean isAnagram2(String s, String t) {

        // **** sanity checks ****
        if (s.length() != t.length()) return false;

        // **** initialization ****
        char[] arr1 = s.toCharArray();
        char[] arr2 = t.toCharArray();

        // **** sort char[] arrays ****
        Arrays.sort(arr1);
        Arrays.sort(arr2);

        // **** traverse char[] arrays comparing characters - O(n) ****
        for (int i = 0; i < arr1.length; i++)
            if (arr1[i] != arr2[i]) return false;

        // **** strings are anagrams ****
        return true;
    }


    /**
     * Given two strings s and t,
     * return true if t is an anagram of s, and false otherwise.
     * Using hashmap.
     * 
     * Counting characters in both strings then comparing results.
     * Similar to using two hashmaps but considerably faster.
     * 
     * Runtime: O(n) - Space: O(n)
     * 36 / 36 test cases passed.
     * Status: Accepted
     * Runtime: 1 ms
     * Memory Usage: 39.2 MB
     */
    static public boolean isAnagram3(String s, String t) {

        // **** sanity checks ****
        if (s.length() != t.length()) return false;

        // **** initialization ****
        int[] freq1 = new int[256];
        int[] freq2 = new int[256];
        
        // **** populate int[] freq1 - O(n) ****
        for (char ch : s.toCharArray())
            freq1[ch]++;

        // **** populate int[] freq2 - O(n) ****
        for (char ch : t.toCharArray())
            freq2[ch]++;

        // **** compare frequencies of lowercase characters - O(n) ****
        for (int i = 'a'; i <= 'z' ; i++)
            if (freq1[i] != freq2[i]) return false;

        // **** strings are anagrams ****
        return true;
    }


    /**
     * Given two strings s and t,
     * return true if t is an anagram of s, and false otherwise.
     * Using hashmap.
     * 
     * Counting characters in both strings then comparing results.
     * Similar to using a single hashmap but considerably faster.
     * 
     * 36 / 36 test cases passed.
     * Status: Accepted
     * Runtime: 1 ms
     * Memory Usage: 38.7 MB
     * 
     * Runtime: 1 ms, faster than 100.00% of Java online submissions.
     * Memory Usage: 38.7 MB, less than 99.76% of Java online submissions.
     */
    static public boolean isAnagram(String s, String t) {

        // **** sanity checks ****
        if (s.length() != t.length()) return false;

        // **** initialization ****
        int[] freq = new int[256];
        
        // **** increment characters frequencies - O(n) ****
        for (char ch : s.toCharArray())
            freq[ch]++;

        // **** decrement character frequencies - O(n) ****
        for (char ch : t.toCharArray())
            freq[ch]--;

        // **** check character frequencies - O(n) ****
        for (int i = 'a'; i <= 'z' ; i++)
            if (freq[i] != 0) return false;

        // **** strings are anagrams ****
        return true;     
    }


    /**
     * Test scaffod.
     * !!! NOT PART OF SOLUTION !!!
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        
        // **** open buffered reader ****
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // **** read input strings ****
        String[] strArr = br.readLine().trim().split(", ");

        // **** close buffered reader ****
        br.close();

        // **** for ease of use ****
        String s = strArr[0];
        String t = strArr[1];

        // ???? ????
        System.out.println("main <<< s ==>" + s + "<==");
        System.out.println("main <<< t ==>" + t + "<==");

        // **** call function of interest and display result ****
        System.out.println("main <<< isAnagram0: " + isAnagram0(s, t));

        // **** call function of interest and display result ****
        System.out.println("main <<< isAnagram1: " + isAnagram1(s, t));

        // **** call function of interest and display result ****
        System.out.println("main <<< isAnagram2: " + isAnagram2(s, t));

        // **** call function of interest and display result ****
        System.out.println("main <<< isAnagram3: " + isAnagram2(s, t));

        // **** call function of interest and display result ****
        System.out.println("main <<<  isAnagram: " + isAnagram(s, t));
    }

}