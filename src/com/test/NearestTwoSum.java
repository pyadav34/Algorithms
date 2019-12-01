package com.test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class NearestTwoSum {
    /**
     * 
     * 
     * 
     *     count all the contiguous vowels in the beginning and the end of the array

			Take max of all the contiguous vowels in the middle of the string (except start and end)
			
			Add 1 and 2
     * @param args
     */
	
	/**
	 * 
	 * 
	 * 
	 * def flightDetails(arr, k):
    k-=30
    arr = sorted(arr)
    left = 0
    right = len(arr)-1
    max_val = 0
    while left<right:
        if arr[left]+arr[right]<=k:
            if max_val < arr[left]+arr[right]:
                max_val = arr[left]+arr[right]
                i = left
                j = right
            left+=1
        else:
            right-=1
    return(arr[i],arr[j])

arr = [90, 85, 75, 60, 120, 150, 125]
k = 250
print(flightDetails(arr,k))
	 * @param args
	 */
	public static void main(String[] args) {
		
		int[] movieDurations = new int[]{10, 20, 30, 40, 43,50, 60, 70};
		int k = 89;
		int[] result=getFlightDetails(movieDurations,k);
		for(int res:result){
			System.out.println(res);
		}
	}
	
	static public int[] getFlightDetails(int[] arr, int k){
		k-=30;
		
		Arrays.sort(arr);
		
		int left=0;
		int right=arr.length-1;
		 int max = 0;
		 int i=left;
		 int j=right;
		 while(left<right){
			 int sum=arr[left]+arr[right];
			 if(sum<=k){
				 if(max<sum){
					 max=sum;
					 i=left;
					 j=right;
				 }
				 left++;
			 }else{
				 right--;
			 }
			
		 }
		 return new int[]{arr[i],arr[j]};
		
	}

}
