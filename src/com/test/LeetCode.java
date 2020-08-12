package com.test;

import java.util.*;

public class LeetCode {

    public static void largestSubbarray(int[] arr) {

        HashSet set = new HashSet();
        int start = arr[0];
        int index = 0;
        int cnt = 0;
        int end = 0;
        for (int i = 0; i < arr.length; i++) {
            int size = set.size();
            set.add(arr[i]);
            if (size == set.size()) {
                if (start == arr[i])
                    index++;
                else
                    end = i;
            }
            cnt++;
        }
    }

    public static void maxstock(int arr[]) {
        int minstock = 0;
        int maxstock = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            maxstock = Math.max(maxstock, minstock - arr[i]);
            minstock = Math.max(minstock, maxstock + arr[i]);
        }
        System.out.println(minstock);
    }

    public boolean canConstruct(String ransomNote, String magazine) {
        if (ransomNote == null || magazine == null) return true;
        if (ransomNote.isEmpty() && magazine.isEmpty()) return true;
        int cnt = 0;
        int next = 0;
        for (int i = 0; i < magazine.length(); i++) {
            if (next == ransomNote.length()) return true;
            if (magazine.charAt(i) == ransomNote.charAt(next)) {
                next++;
            }
        }
        return false;
    }

    public int majorityElement(int[] nums) {
        int count = 0;
        int ans = 0;
        for (int num : nums) {
            if (count == 0 || ans == num) {
                ans = num;
                count++;
            } else
                count--;
        }
        return ans;
    }


    public List<Integer> getRow(int rowIndex) {
        List list = new ArrayList<Integer>();
        list.add(1);
        if (rowIndex == 0)
            return list;
        helper(list, rowIndex, 1);
        return list;
    }

    private void helper(List list, int rowIndex, int i) {
        if (rowIndex >= i) {
            int prev = (int) list.get(0);
            for (int in = 1; in < list.size(); in++) {
                int next = (int) list.get(in);
                int res = prev + next;
                prev = next;
                list.remove(in);
                list.add(in, res);
            }
            list.add(list.size(), 1);
            helper(list, rowIndex, i + 1);
        }
    }

    public int change(int amount, int[] coins) {
        int arr[] = new int[amount + 1];
        Arrays.fill(arr, 0);
        arr[0] = 1;
        for (int c : coins)
            for (int i = c; i <= amount; i++)
                arr[i] += arr[i - c];
        return arr[amount];
    }

    public int change2(int amount, int[] coins) {
        int[] dp = new int[amount + 1];
        dp[0] = 1;
        for (int coin : coins)
            for (int i = 0; i < amount - coin + 1; i++)
                dp[i + coin] += dp[i];
        return dp[amount];
    }

    public int[] countBits(int num) {
        int dp[] = new int[num + 1];
        dp[0] = 0;
        if (num == 0)
            return dp;
        dp[1] = 1;
        int cur = 1;
        for (int i = 1; i <= num; i++) {
            if (cur == i - cur) {
                dp[i] = dp[cur];
                cur = i;
            } else
                dp[i] = dp[cur] + dp[i - cur];
        }
        return dp;
    }

    public int[] countBits1(int num) {
        int[] res = new int[num + 1];

        helper(res, 1, 1);
        return res;
    }

    private void helper(int[] res, int num, int count) {
        if (num >= res.length) return;
        res[num] = count;

        helper(res, 2 * num, count);
        helper(res, 2 * num + 1, count + 1);
    }

    int maxV;
    int maxL;
    int cs;
    int next[];
    int len[];

    public List<Integer> largestDivisibleSubset(int[] nums) {
        if (nums.length == 0)
            return new ArrayList<>();

        next = new int[nums.length];
        len = new int[nums.length];

        Arrays.sort(nums);
        maxV = nums[nums.length - 1];
        for (int j = 0; j < nums.length; j++)
            dp(1, j, nums);
        List<Integer> list = new ArrayList<>();
        int i = cs;
        while (i != -1) {
            list.add(nums[i]);
            i = next[i];
        }
        return list;
    }

    private void dp(int cL, int start, int[] nums) {
        if (len[start] == 0) {
            len[start] = 1;
            next[start] = -1;
        }
        if (len[start] > maxL) {
            maxL = len[start];
            cs = start;
        }
        int limit = maxV >> Math.max(maxL - cL, 0);
        int max = 0;
        for (int j = start + 1; j < nums.length && nums[j] <= limit; j++) {
            if (nums[j] % nums[start] == 0) {
                if (len[j] == 0) {
                    dp(cL + 1, j, nums);
                }
                if (len[j] > max) {
                    max = len[j];
                    next[start] = j;
                    len[start] = len[j] + 1;
                    if (len[start] > maxL) {
                        maxL = len[start];
                        cs = start;
                        limit = maxV >> Math.max(0, maxL - cL);
                    }
                }
            }
        }
    }

    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        Arrays.sort(flights, Comparator.comparingInt(o -> o[2]));
        int parent[] = new int[flights.length];
        for (int i = 0; i < parent.length; i++)
            parent[i] = i;
        int[][] output = new int[flights.length][3];
        int cnt = 0;
        int i = 0;
        while (cnt != flights.length - 1) {
            int[] arr = flights[i];
            int source = findParent(arr[0], parent);
            int dest = findParent(arr[1], parent);
            if (source != dest) {
                output[cnt] = arr;
                cnt++;
                parent[source] = dest;
            }
            i++;
        }
        int sum = 0;
        for (int j = 0; j < parent.length; j++) {
            if (output[j][0] < output[j][1])
                sum += output[j][2];
        }
        return sum;
    }

    int findParent(int i, int[] parent) {
        if (i == parent[i]) return i;
        return findParent(parent[i], parent);
    }

    Map<Integer, Integer> map = new HashMap<Integer, Integer>();

    public String getPermutation(int n, int k) {
        if (n == 1) return "1";
        List<Integer> list = new ArrayList();
        for (int i = 1; i <= n; i++)
            list.add(i);
        int fact = fact(n);
        String s = "";
        int res = k;
        while (list.size() > 2) {
            int part = map.get(list.size()) / list.size();
            int cnt = 0;
            while (res > part) {
                cnt++;
                res -= part;
            }
            int get = list.get(cnt);
            list.remove(cnt);
            s += "" + get;
        }
        if (list.size() == 2 && res == 1)
            s = s + list.get(0) + "" + list.get(1);
        else
            s = s + list.get(1) + "" + list.get(0);
        return s;
    }

    int fact(int n) {
        if (n == 2) return 2;
        int res = n * fact(n - 1);
        map.put(n, res);
        return res;
    }

    int index = 0;

    public TreeNode bstFromPreorder(int[] preorder, int min, int max) {
        if (preorder == null || index >= preorder.length)
            return null;
        TreeNode curr = null;
        if (preorder[index] > min && preorder[index] < max) {
            curr = new TreeNode(preorder[index]);
            index++;
            curr.left = bstFromPreorder(preorder, min, curr.val);
            curr.right = bstFromPreorder(preorder, curr.val, max);
        }

        return curr;

    }

    public int lengthOfLongestSubstring(String s) {
        //char ch[]=s.toCharArray();
        //int[][] dp = new int[ch.length][ch.length];
        int[] lastIndex = new int[256];
        Arrays.fill(lastIndex, -1);
        int i = 0;
        int res = 0;
        for (int j = 0; j < s.length(); j++) {
            i = Math.max(i, lastIndex[s.charAt(j)] + 1);
            res = Math.max(res, j - i + 1);
            lastIndex[s.charAt(j)] = j;
        }
        return res;
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}


class Trie {

    private TrieNode node;
    public Trie() {
        node = new TrieNode();
    }

    public void insert(String word) {
        node.insert(word);
    }

    public boolean search(String word) {
        return node.search(word);
    }

    public boolean startsWith(String prefix) {
        return node.startsWith(prefix);
    }


    class TrieNode {
        private TrieNode[] children;
        private boolean hasWord;

        public TrieNode() {
            this.children = new TrieNode[26];
            this.hasWord = false;
        }

        public boolean startsWith(String prefix) {
            TrieNode node = search(prefix, 0);
            return node != null;
        }

        public boolean search(String word) {
            TrieNode node = search(word, 0);
            return node != null && node.hasWord;
        }

        public TrieNode search(String word, int index) {
            if (index == word.length()) {
                return this;
            }

            char ch = word.charAt(index);
            if (children[ch - 'a'] == null) {
                return null;
            }
            return children[ch - 'a'].search(word, index + 1);
        }

        public void insert(String word) {
            insert(word, 0);
        }

        public void insert(String word, int index) {
            if (index == word.length()) {
                this.hasWord = true;
                return;
            }

            char ch = word.charAt(index);
            if (children[ch - 'a'] == null) {
                children[ch - 'a'] = new TrieNode();
            }
            children[ch - 'a'].insert(word, index + 1);
        }
    }
}