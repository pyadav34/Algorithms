package com.test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class TopNCompetitor {
	
	  public static void main(String[] args) {
	        int numCompetitors = 6;
	        int topNCompetitors = 2;
	        List<String> competitors = new ArrayList<>();
	        competitors.add("newshop");competitors.add("shopnow");
	        competitors.add("afshion");competitors.add("fashionbeats");
	        competitors.add("mymarket");competitors.add("tcellular");

	        int numReviews = 6;
	        List <String> reviews = new ArrayList<>();
	        reviews.add("newshop is providing good services in the city; everyone should use newshop");
	        reviews.add("best services by newshop");
	        reviews.add("fashionbeats has great services in the city");
	        reviews.add("i am proud to have fashionbeats");
	        reviews.add("mymarket has awesome services");
	        reviews.add("Thanks Newshop for the quick delivery");



	        List<String> result = getTopNCompetitors(numCompetitors, topNCompetitors, competitors, numReviews, reviews);
	        
	         System.out.println("RESULT:");
	        for(String item: result){
	            System.out.print(item+", ");
	        }
	        System.out.println();
	    }
	
	/**
	 * a) Iterate all competitors and put in HashMap "competitorMap" where key is name of competitor and value is 0.
	 * b) Iterate each review and split each review with space. lets suppose word[] is resulting array 	 *         
	 *          if(word[i] contains in "competitorMap" key set )  then increase count of competitor in map.
	 * c) initialize a min priorty Queue which is sorted based on value of "competitorMap"
	 * d) Iterate "competitorMap" keyset and add to priorityQueue
	 *          if(size of priority queue >k)  remove element from priority queue 
	 * d) add all element of priority queue to list
	 * e) return list;
	 * 
	 * Time complexity: O(numCompetitors* log(topNCompetitors))
	 * 
	 * Space complexity: O(numCompetitors)
	 * 
	 */
	private static List<String> getTopNCompetitors(int numCompetitors,
             int topNCompetitors,             
             List<String> competitors,
             int numReviews,
             List<String> reviews) {
		
		 HashMap<String, Integer> map = new HashMap<>();
		  for(String comp : competitors){
	            map.put(comp.toLowerCase(),0);
	       }
		
	        for(String sentence : reviews){
	            String[] words = sentence.split(" "); // get all the words in one sentence and put them in a String array 
	            for(String word : words){
	                if(map.containsKey(word.toLowerCase())) { // check if map has any of the words (competitor name). if yes increase its value
	                    map.put(word.toLowerCase(), map.get(word.toLowerCase()) + 1);
	                }
	            }
	        }
	        
	        PriorityQueue<String> pq =new PriorityQueue<>((String i1,String i2)->{            
	            return map.get(i1)-map.get(i2);            
	        });
	        for(String key:map.keySet()){
	            pq.add(key);
	            if(pq.size()>topNCompetitors) pq.poll();
	        }
	        List<String> result=new ArrayList<>();
	        while(!pq.isEmpty())
	          result.add(pq.poll());
	        
	         Collections.reverse(result);
	        
	        return result; 
	  
}
	
}
