Class Main {
	public void bfs()
	{
		// BFS uses Queue data structure - FIFO
		Queue queue = new LinkedList();
		queue.add(this.rootNode);
		printNode(this.rootNode);
		rootNode.visited = true;
		while(!queue.isEmpty()) {
			Node node = (Node)queue.remove();
			Node child=null;
			while((child=getUnvisitedChildNode(node))!=null) {
				child.visited=true;
				printNode(child);
				queue.add(child);
			}
		}
		// Clear visited property of nodes
		clearNodes();
	}

	public void dfs() {
		// DFS uses Stack data structure - LIFO
		Stack stack = new Stack();
		stack.push(this.rootNode);
		rootNode.visited=true;
		printNode(rootNode);
		while(!stack.isEmpty()) {
			Node node = (Node)s.peek();
			Node child = getUnvisitedChildNode(n);
			if(child != null) {
				child.visited = true;
				printNode(child);
				s.push(child);
			}
			else {
				s.pop();
			}
		}
		// Clear visited property of nodes
		clearNodes();
	}
}


Class Node {
   Char data;
   Public Node(char c) {
      this.data=c;
   }
}


// 表示可以经过某个位置，求解从 (0, 0) 位置到 (tr, tc) 位置的最短路径长度。

public int minPathLength(int[][] grids, int tr, int tc) {
    final int[][] direction = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    final int m = grids.length, n = grids[0].length;
    Queue<Pair<Integer, Integer>> queue = new LinkedList<>();
    queue.add(new Pair<>(0, 0));
    int pathLength = 0;
    while (!queue.isEmpty()) {
        int size = queue.size();
        pathLength++;
        while (size-- > 0) {
            Pair<Integer, Integer> cur = queue.poll();
            int cr = cur.getKey(), cc = cur.getValue();
            grids[cr][cc] = 0; // 标记
            for (int[] d : direction) {
                int nr = cr + d[0], nc = cc + d[1];
                if (nr < 0 || nr >= m || nc < 0 || nc >= n || grids[nr][nc] == 0) {
                    continue;
                }
                if (nr == tr && nc == tc) {
                    return pathLength;
                }
                queue.add(new Pair<>(nr, nc));
            }
        }
    }
    return -1;
}