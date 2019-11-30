package com.test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class OptimalUtilization {

	
	 private List<int[]> getPairs(List<int[]> a, List<int[]> b, int target) {
		
		  Collections.sort(a,(int[] i,int[] j)->{
			  return i[1] - j[1];
		  });
		   

		  Collections.sort(b,(int[] i,int[] j)->{
			  return i[1] - j[1];
		  });
		  
		  List<int[]> result = new ArrayList<>();
		  int m = a.size();
	      int n = b.size();
	      int i =0;
	      int j =n-1;
	      int max=-1;
	        
	      while(i<m && j >= 0) {
	    	  int sum = a.get(i)[1] + b.get(j)[1];  
	    	  if(sum > target) {
	                 --j;
	            } else {
	            	 if(max <= sum) {
	            		 if(max < sum) {
	                         max = sum;
	                         result.clear();
	                     }
	            		 result.add(new int[]{a.get(i)[0], b.get(j)[0]});
	            		 
	            		 int index = j-1;
	                     while(index >=0 && b.get(index)[1] == b.get(index+1)[1]) {
	                          result.add(new int[]{a.get(i)[0], b.get(index--)[0]});
	                     }
	            		 
	            	 }
	            	 ++i;
	            	
	            }
	      
	      }
		  
		 return null;
	 }
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
