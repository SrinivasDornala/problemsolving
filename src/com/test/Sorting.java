package com.test;

import java.util.*;

public class Sorting {

    public static int[] selectionSort(int[] arr) {
        int minimum=0;
        for(int i = 0; i < arr.length-1 ; i++)  {
            minimum = i ;
            for(int j = i+1; j < arr.length ; j++ ) {
                if(arr[ j ] < arr[ minimum ])  {
                    minimum = j ;
                }
            }
            if(minimum!=i){
                int temp = arr[minimum];
                arr[minimum]= arr[i];
                arr[i]=temp;
            }
        }
        return arr;
    }

    public static int[] insertionSort(int[] arr) {
        for(int i = 0; i < arr.length-1 ; i++)  {
            int temp=arr[i];
            int minimum=i;
           while(minimum>0 && arr[minimum]<arr[minimum-1]){
               arr[minimum]=arr[minimum-1];
               minimum--;
            }
            arr[minimum]= temp;
        }
        return arr;
    }

    public static void mergeSort(int[] arr, int start , int end ) {
       if(start<end) {
           int piv = start+(end-start)/2;
           mergeSort(arr, start, piv);
           mergeSort(arr, piv + 1, end);
           merge(arr,start,piv, end);
       }
    }

    public static void merge(int[] arr, int start, int piv, int end){
        int i, j, k;
        int left = piv - start + 1;
        int right =  end - piv;

        int L[] =new int[left] ;
        int R[]= new int[right];

        for (i = 0; i < left; i++)
            L[i] = arr[start + i];
        for (j = 0; j < right; j++)
            R[j] = arr[piv + 1+ j];

        i = 0;
        j = 0;
        k = 1;
        while (i < left && j < right){
            if (L[i] <= R[j]) {
                arr[k] = L[i];
                i++;
            } else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }

        while (i < left ){
            arr[k] = L[i];
            i++;
            k++;
        }
        while (j < right ){
            arr[k] = R[j];
            j++;
            k++;
        }
    }

    public static void quickSort(int[] arr, int start, int end ){
        if(start< end){
            int piv= partition(arr,start,end);
            quickSort(arr,start,piv-1);
            quickSort(arr, piv+1, end);
        }
    }

    public static int partition(int[] arr, int start, int end ){
        int j= start;
        int piv = arr[end];
        for(int i=0;i<end-1;i++){
            if(arr[i]<=piv){
                arr[i]=arr[j];
                j++;
            }
        }
        swap(arr,j,end);
        return j;
    }
    public static void swap(int[] arr, int i, int j){
        int temp= arr[i];
        arr[i]=arr[j];
        arr[j]= temp;
    }

    public static void heapSort(int[] arr){
        int len= arr.length;
        for (int i = len/2-1;i>=0;i--){
            heapify(arr,len,i);
        }

        for (int i = len-1;i>=0;i--){
            swap(arr,0,i);
            heapify(arr,len,0);
        }
    }

    public static void heapify(int[] arr, int len,int i){
        int largest = i;
        int left = 2*i+1;
        int right = 2*i+2;
        if(left<len && arr[left]>arr[largest]){
            largest=left;
        }
        if(right<len && arr[right]>arr[largest]){
            largest=right;
        }
        if(i!= largest){
            swap(arr, largest,i);
            heapify(arr,len,largest);
        }
    }
}