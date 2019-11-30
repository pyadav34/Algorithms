package com.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

public class ProductSuggestion {
private TrieNode root;
    
    public List<String> productSuggestions(String[] repo, String query) {
        root = new TrieNode();
        
        for (String str : repo) {
            add(str);
        }
        int len = query.length();
        // mouse
        for (int i = len - 3; i <= len; i++) {
            search(query.substring(0, i));
        }
        
        return null;
    }
    
    private void add(String s) {
        TrieNode cur = root;
        for (char c : s.toCharArray()) {
            TrieNode next = cur.children.get(c);
            if (next == null) {
                next = new TrieNode();
                cur.children.put(c, next);
            }
            next.queue.offer(s);
            if (next.queue.size() > 3) {
                next.queue.poll();
            }
            cur = next;
            
        }
    }
    
    private List<String> search(String input) {
        List<String> res = new ArrayList<>();
        TrieNode p = root;
        for (char c : input.toCharArray()) {
            TrieNode child = p.children.get(c);
            if (child == null) { // if not found, return an empty 
                return new ArrayList<>();
            }
            p = child;
        }
        PriorityQueue<String> pq = new PriorityQueue<>(p.queue);
        int cnt = 0;
        while (!pq.isEmpty() && cnt < 3) {
            res.add(0, pq.poll());
        }
        System.out.println(res.toString());
        return res;
        
    }
    
    public class TrieNode {
        Map<Character, TrieNode> children;
        Queue<String> queue;
        public TrieNode() {
            this.children = new HashMap<Character, TrieNode>();
            this.queue = new PriorityQueue<>((a, b) -> b.toLowerCase().compareTo(a.toLowerCase()));
        }
    }
}
