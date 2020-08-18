package com.test;

import java.util.*;
/*
    Input: N = 3, K = 7
    Output: [181,292,707,818,929]
    Explanation: Note that 070 is not a valid number, because it has leading zeroes.

    Input: N = 2, K = 1
    Output: [10,12,21,23,32,34,43,45,54,56,65,67,76,78,87,89,98]
 */
public class NumbersWithSameConsecutiveDifferencesSolution{
    public int[] numsSameConsecDiff(int N, int K) {
        List<Integer> list = new ArrayList<Integer>();
        for(int j=0;j<=9;++j){
            if(j==0 ){
                if(N<2)list.add(j);
                continue;
            }else
                rec(1,N,K,j,j,list);
        }
        int res[] = new int[list.size()];
        for(int j=0;j<list.size();++j)
            res[j]=list.get(j);

        return res;
    }
    public void rec(int cnt, int N,int K, int n,int m,List list){
        if(cnt==N)list.add(n);
        else
            for(int k=0;k<=9;++k)
                if(Math.abs(k-m)==K)
                    rec(cnt+1,N,K,n*10+k,k,list);

    }
}