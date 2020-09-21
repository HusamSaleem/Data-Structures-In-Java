package DataStructures;

// Graph represented as an adjacency list
public class Graph {

	public int[][] matrix;

	public Graph(int size) {
		this.matrix = new int[size][size];
		initializeGraph();
	}

	public static void main(String[] args) {
		Graph g = new Graph(5);
		g.insert(0, 4, 1);
		g.insert(0, 2, 1);
		g.insert(0, 1, 1);
		g.insert(2, 4, 1);
		g.insert(2, 3, 1);
		
		//g.delete(0, 4);
		
		System.out.println("Depth first search results");
		g.depthFirstSearch(0);
		System.out.println();
		
		System.out.println("Breadth first search results");
		g.breadthFirstSearch(0);
		System.out.println();
		
		g.dijkstrasAlgorithm(0);
		System.out.println();
		
		g.printGraph();
	}

	// O(n^2)
	private void initializeGraph() {
		for (int i = 0; i < this.matrix.length; i++) {
			for (int j = 0; j < this.matrix.length; j++) {
				this.matrix[i][j] = 0;
			}
		}
	}

	// O(1)
	public boolean insert(int source, int newVertex, int weight) {
		try {
			if (this.matrix[source][newVertex] == 1) {
				System.out.println("There is already an edge at {" + source + newVertex + " }");
				return false;
			}

			// The 1 could be modified to be a different weight
			this.matrix[source][newVertex] = weight;
			this.matrix[newVertex][source] = weight;
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("Not a valid source / new vertex");
			return false;
		}
		return true;
	}

	// O(1)
	public boolean delete(int source, int newVertex) {
		try {
			if (this.matrix[source][newVertex] == 0) {
				System.out.println("There is no edge at {" + source + newVertex + " }");
				return false;
			}

			// The 1 could be modified to be a different weight
			this.matrix[source][newVertex] = 0;
			this.matrix[newVertex][source] = 0;
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("Not a valid source / new vertex");
			return false;
		}
		return true;
	}
	
    void printSolution(Integer dist[]) 
    { 
        System.out.println("Vertex \t\t Distance from Source"); 
        for (int i = 0; i < this.matrix.length; i++) 
            System.out.println(i + " \t\t " + dist[i]); 
    } 
	
	private int minDistance(Integer dist[], boolean visited[]) {
		int min = Integer.MAX_VALUE;
		int minIndex = 0;
		
		for (int i = 0; i < visited.length; i++) {
			// Finds the minimum distance from the next one that was not visited yet
			if (visited[i] == false && dist[i] <= min) {
				min = dist[i];
				minIndex = i;
			}
		}
		return minIndex;
	}
	
	// Finds the shortest path from source A to B
	// Will not work with negative weights
	// Will work for both weighted and unweighted ( weight >= 1) graphs
	// This specific way of implementation will not work for directed graphs
	private void dijkstrasAlgorithm(int source) {
		boolean[] visited = new boolean[this.matrix.length];
		Integer[] distances = new Integer[this.matrix.length];
		
		for (int i = 0; i < this.matrix.length; i++) {
			distances[i] = Integer.MAX_VALUE;
			visited[i] = false;
		}
		
		distances[source] = 0;
		
		for (int i = 0; i < this.matrix.length - 1; i++) {
			int minDistIndex = minDistance(distances, visited);
			
			visited[minDistIndex] = true;
			
			for (int j = 0; j < this.matrix.length; j++) {
				// If it has not been visted
				// If there is an edge from min dist to j
				// If current value is less than the new distance
				// Then add up the distances from the previous vertex
				if (!visited[j] && matrix[minDistIndex][j] != 0 && distances[minDistIndex] != Integer.MAX_VALUE && distances[minDistIndex] + matrix[minDistIndex][j] < distances[j]) 
					distances[j] = distances[minDistIndex] + matrix[minDistIndex][j]; 
			}
		}
		
		printSolution(distances);
	}

	// O(V+E)
	// (V + Edges)
	// For now this just displays all nodes
	public void depthFirstSearch(int source) {
		boolean[] visited = new boolean[this.matrix.length];
		depthFirstSearch(source, visited);
	}

	private void depthFirstSearch(int source, boolean[] visited) {
		visited[source] = true;
		System.out.println("Vertex: " + source);

		// Loop over every vertex's connected edges and visit them if they are not
		// visited
		for (int i = 0; i < this.matrix[source].length; i++) {
			if (this.matrix[source][i] == 1 && !visited[i]) {
				depthFirstSearch(i, visited);
			}
		}
	}

	// O(V+E)
	// (V + Edges)
	// For now this just displays the nodes on the graph
	// But useful for finding shortest paths on a unweighted graph
	public void breadthFirstSearch(int source) {
		boolean[] visited = new boolean[this.matrix.length];
		Queue<Integer> q = new Queue<Integer>(this.matrix.length);

		q.enQueue(source);
		visited[0] = true;

		while (!q.isEmpty()) {
			source = q.deQueue();
			System.out.println("Vertex: " + source);
			
			q = getNeighbors(q, source, visited);
		}

	}

	private Queue<Integer> getNeighbors(Queue<Integer> q, int source, boolean[] visited) {
		// Loop over every vertex's connected edges and visit them if they are not
		// visited
		for (int i = 0; i < this.matrix[source].length; i++) {
			if (this.matrix[source][i] == 1 && !visited[i]) {
				q.enQueue(i);
				visited[i] = true;
			}
		}
		
		return q;
	}

	// O(n^2)
	public void printGraph() {
		for (int i = 0; i < this.matrix.length; i++) {
			for (int j = 0; j < this.matrix.length; j++) {
				System.out.print(this.matrix[i][j] + " ");
			}
			System.out.println();
		}
	}

}