package com.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
/**
 * 
 * 
 * 
 * A O( V+E) algorithm to find all Articulation Points (APs)
        The idea is to use DFS (Depth First Search). In DFS, we follow vertices in tree form called DFS tree. 
        In DFS tree, a vertex u is parent of another vertex v, if v is discovered by u (obviously v is an adjacent of u in graph). 
        In DFS tree, a vertex u is articulation point if one of the following two conditions is true.
        1) u is root of DFS tree and it has at least two children.
        2) u is not root of DFS tree and it has a child v such that no vertex in subtree rooted with v has a back edge to one of the ancestors (in DFS tree) of u.
 * 
 * 
 * In DFS traversal, we maintain a parent[] array where parent[u] stores parent of vertex u. 
 * Among the above mentioned two cases, the first case is simple to detect. 
 * For every vertex, count children. 
 * If currently visited vertex u is root (parent[u] is NIL) and has more than two children, print it.
 * 
How to handle second case? The second case is trickier. 
We maintain an array disc[] to store discovery time of vertices. 
For every node u, we need to find out the earliest visited vertex (the vertex with minimum discovery time) that can be reached from subtree rooted with u. 
So we maintain an additional array low[] which is defined as follows.

low[u] = min(disc[u], disc[w]) 
where w is an ancestor of u and there is a back edge from 
some descendant of u to w.


result will be low[nei] >= ids[cur]
 *
 */
public class CriticalRouters {

	static int time = 0;

	public static void main(String[] args) {
		int numRouters1 = 7;
		int numLinks1 = 7;
		int[][] links1 = {{0, 1}, {0, 2}, {1, 3}, {2, 3}, {2, 5}, {5, 6}, {3, 4}};
		System.out.println(getCriticalNodes(links1, numLinks1, numRouters1));
	}

	private static List<Integer> getCriticalNodes(int[][] links, int numLinks, int numRouters) {
		time = 0;
		Map<Integer, Set<Integer>> map = new HashMap<>();
		for(int i=0;i<numRouters;i++) {
			map.put(i, new HashSet<>());
		}
		for(int[] link : links) {
			map.get(link[0]).add(link[1]);
			map.get(link[1]).add(link[0]);
		}
		Set<Integer> set = new HashSet<>();
		int[] low = new int[numRouters];
		int[] ids = new int[numRouters];
		int parent[] = new int[numRouters]; 
		Arrays.fill(ids, -1);
		Arrays.fill(parent, -1);
		for(int i=0;i<numRouters;i++) {
			if(ids[i] == -1)
				dfs(map, low, ids, parent, i, set);
		}
		return new ArrayList<>(set);
	}



	private static void dfs(Map<Integer, Set<Integer>> map, int[] low, int[] ids, int[] parent, int cur, Set<Integer> res) {
		int children = 0; 
		ids[cur] = low[cur]= ++time;
		for(int nei : map.get(cur)) {
			if(ids[nei] == -1) {
				children++;
				parent[nei] = cur;
				dfs(map, low, ids, parent,nei, res);
				low[cur] = Math.min(low[cur], low[nei]);
				if((parent[cur] == -1 && children > 1) || (parent[cur] != -1 && low[nei] >= ids[cur]))
					res.add(cur);
			}
			else if(nei != parent[cur])
				low[cur] = Math.min(low[cur], ids[nei]);
		}
	}

}
