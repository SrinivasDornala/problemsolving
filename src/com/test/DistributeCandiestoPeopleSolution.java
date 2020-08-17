package com.test;

/*  Input: candies = 7, num_people = 4
    Output: [1,2,3,1]
    Explanation:
    On the first turn, ans[0] += 1, and the array is [1,0,0,0].
    On the second turn, ans[1] += 2, and the array is [1,2,0,0].
    On the third turn, ans[2] += 3, and the array is [1,2,3,0].
    On the fourth turn, ans[3] += 1 (because there is only one candy left), and the final array is [1,2,3,1].

    Input: candies = 10, num_people = 3
    Output: [5,2,3]
 */
public class DistributeCandiestoPeopleSolution {
    public int[] distributeCandies(int candies, int num_people) {
        int res[] = new int[num_people];
        int n=0;
        while(candies >0){
            int i=0;
            for(i=0;i<num_people;++i){
                if(candies>0){
                    if(candies>=i+1+n)
                        res[i]+=i+n+1;
                    else
                        res[i]+=candies;
                    candies=candies-(i+1+n);
                }
            }
            n+=i;
        }
        return  res;
    }
}