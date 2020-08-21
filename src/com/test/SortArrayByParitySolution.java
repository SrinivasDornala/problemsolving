package com.test;

import java.util.*;
/*
    Input: [3,1,2,4]
    Output: [2,4,3,1]
    The outputs [4,2,3,1], [2,4,1,3], and [4,2,1,3] would also be accepted.
 */
public class SortArrayByParitySolution{
    public int[] sortArrayByParity(int[] A){
        if(A==null ||A.length<2)return A;
        int low=0;
        int high= A.length-1;

        while(low<high){
            if(A[low]%2!=0){
                while(high>low && A[high]%2!=0)
                    high--;
                swap(A,low,high);
            }
            low++;
        }
        return A;
    }
    void swap(int[] A,int i,int j){
        int temp = A[i];
        A[i]=A[j];
        A[j]=temp;
    }
}