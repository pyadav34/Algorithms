package com.test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SubStringKDistinct {
	
	 public static List<String> kSubstring(String s, int k) {
	        Set<Character> window = new HashSet<>();
	        Set<String> result = new HashSet<>();
	        int start = 0;
	        for (int end = 0; end < s.length(); end++) {
	            while (window.contains(s.charAt(end)) ) {
	                window.remove(s.charAt(start));
	                start++;
	            }

	            window.add(s.charAt(end));

	            if ((end-start+1) == k) {
	                result.add(s.substring(start, end + 1));
	                window.remove(s.charAt(start++));
	            }
	        }
	        return new ArrayList<>(result);
	    }
	    
	    public static void main(String[] args) {
	        System.out.println(kSubstring("awaglknagawunagwkwagl", 4));
	    }
	
	

}
