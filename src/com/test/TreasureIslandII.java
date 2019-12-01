package com.test;

import java.util.LinkedList;
import java.util.Queue;

public class TreasureIslandII {
    private static final int[][] DIRS = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};

    public static int minDist(char[][] grid) {
        Queue<int[]> q = collectSources(grid);
        int dist = 0;
        while (!q.isEmpty()) {
        	int n=q.size();
            for (int sz =0;sz< n; sz++) {
                int[] p = q.poll();
                
                if (grid[p[0]][p[1]] == 'X') return dist;
                grid[p[0]][p[1]] = 'D'; // mark as visited
                
                for (int[] dir : DIRS) {
                    int r = p[0] + dir[0];
                    int c = p[1] + dir[1];
                    if (r >= 0 && r < grid.length && c >= 0 && c < grid[0].length && grid[r][c] != 'D') {
                        q.add(new int[]{r, c});
                    }
                }
                
            }
            dist++;
        }
        return -1;
    }
    
    private static Queue<int[]> collectSources(char[][] grid) {
        Queue<int[]> sources = new LinkedList<>();
        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[0].length; c++) {
                if (grid[r][c] == 'S') {
                    sources.add(new int[]{r, c});
                }
            }
        }
        return sources;
    }
   
    

    public static void main(String[] args) {        
        char[][] grid = {
            {'S', 'O', 'O', 'S', 'S'},
            {'D', 'O', 'D', 'O', 'D'},
            {'O', 'O', 'O', 'O', 'X'},
            {'X', 'D', 'D', 'O', 'O'},
            {'X', 'D', 'D', 'D', 'O'}}; 
        test(minDist(grid), 3);
    }
    
    private static void test(int actual, int expected) {
        if (actual == expected) {
            System.out.println("PASSED!");
        } else {
            System.out.println(String.format("FAILED! Expected: %d, but got: %d", expected, actual));
        }
    }
}
