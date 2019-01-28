973. K Closest Points to Origin

https://leetcode.com/problems/k-closest-points-to-origin/

import java.util.PriorityQueue;
import java.util.Comparator;

class Point{
    int x;
    int y;
    public Point(int x, int y){
        this.x = x;
        this.y = y;
    }
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
}

class Solution {
    public int[][] kClosest(int[][] points, int K) {
        int[][] res = new int[K][];
        // add each point to a heap
        PriorityQueue<Point> pq = new PriorityQueue<Point>(K, new Comparator<Point>(){
            @Override
            public int compare(Point a, Point b){
                // descending order, if a greater, first
                return (int)(b.x*b.x + b.y*b.y - a.x*a.x - a.y*a.y);
            }
        });
        // insert k element into pq
        for(int i = 0; i < points.length; i++){
            Point p = new Point(points[i][0], points[i][1]);
            pq.offer(p);
            if(pq.size() > K){
                pq.poll(); // remove the least value
            }
        }
        // add to res
        int i = 0;
        while(!pq.isEmpty()){
            Point p = pq.poll();
            res[i++] = new int[]{p.getX(), p.getY()};
        }
        return res;
    }
}