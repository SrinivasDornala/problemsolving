package com.test;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Flow;
import java.util.function.BiConsumer;

public class Test {
    public static void main(String[] args) {
        System.out.println("HELLO");
        String s1=null;
        BiConsumer<Integer,Integer> BiC= new BiConsumer<Integer, Integer>() {
            @Override
            public void accept(Integer integer, Integer integer2) {
                Map map =new TreeMap();
                map.values();
            }
        };
    }

    public static String getSmallestAndLargest(String s, int k) {
        String smallest = "";
        String largest = "";
        String str[] = new String[1];
        str[0] = s;
        int cnt = 0;
        for (int i = 0; i < s.length() - k; i++) {
            for (int j = i; j <= s.length() - k && i != k; j = j + k) {
                str[cnt] = s.substring(j, j + k);
                str = increaseSize(str, 1);
                cnt++;
            }
        }

        if (str.length > 1) {
            str = increaseSize(str, 0);
            sort(str);
        }
        smallest = str[0];
        largest = str[str.length - 1];
        return smallest + "\n" + largest;
    }

    static String[] increaseSize(String[] str, int in) {
        String[] newStr = null;
        int len = 0;
        if (in == 0) {
            newStr = new String[str.length - 1];
            len = newStr.length;
        } else {
            newStr = new String[str.length + 1];
            len = str.length;
        }
        System.arraycopy(str, 0, newStr, 0, len);
        return newStr;
    }

    static void sort(String[] str) {
        int n = str.length;
        for (int i = 1; i < n; ++i) {
            String key = str[i];
            int j = i - 1;
            while (j >= 0 && str[j].compareTo(key) > 0) {
                str[j + 1] = str[j];
                j = j - 1;
            }
            str[j + 1] = key;
        }
    }

    public int[][] kClosest(int[][] points, int K) {
        if (points == null || points.length == 0) {
            return new int[][]{};
        }
        int left = 0; int right = points.length-1;
        while (left <= right) {
            int pivot = partion(points, left, right);
            if (pivot == K) {
                break;
            }else if (pivot < K) {
                left = pivot+1;
            } else {
                right = pivot-1;
            }
        }
        return Arrays.copyOfRange(points, 0, K);
    }
    public int partion(int[][] points, int lo, int hi) {
        int pivot = points[hi][0] * points[hi][0] + points[hi][1] * points[hi][1];
        int s = lo;
        for (int i = lo; i < hi; i++) {
            if (points[i][0] * points[i][0] + points[i][1] * points[i][1] < pivot) {
                int[] temp = points[s];
                points[s] = points[i];
                points[i] = temp;
                s++;
            }
        }
        int[] temp = points[s];
        points[s] = points[hi];
        points[hi] = temp;
        return s;
    }
    public int compare(int[] p1, int[] p2) {
        return p1[0]*p1[0] + p1[1]*p1[1] - p2[0]*p2[0] - p2[1]*p2[1];
    }

}
