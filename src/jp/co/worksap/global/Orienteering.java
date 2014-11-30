package jp.co.worksap.global;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Orienteering {

	/**
	 * Infinite Value to represent there was no edge between vertices.
	 */
	private static final int INF = Integer.MAX_VALUE;
	/**
	 * Width Inout Param
	 */
	private static int width;
	/**
	 * height Inout Param
	 */
	private static int height;
	/**
	 * Total number of checkpoints
	 */
	private static int checkpoints;

	/**
	 * Helper Class used to store the information of a single cell.
	 * 
	 */
	static class Node {

		private final char ch;
		private final int nodeNumber;
		private boolean isVisited;
		private final List<Node> neighbours;
		private Integer distance;

		public Node(final int nodeId, final char ch) {
			this.nodeNumber = nodeId;
			this.ch = ch;
			this.isVisited = false;
			this.neighbours = new ArrayList<Node>();
			this.distance = Integer.MAX_VALUE;
		}

		public int getDistance() {
			return this.distance;
		}

		public void setDistance(final int distance) {
			this.distance = distance;
		}

		public void addEdge(final Node n) {
			this.neighbours.add(n);
		}

		public List<Node> getneighbours() {
			return this.neighbours;
		}

		public boolean getIsVisited() {
			return this.isVisited;
		}

		public char getChar() {
			return this.ch;
		}

		public int getNodeId() {
			return this.nodeNumber;
		}

		public void setVisited(final boolean isVisited) {
			this.isVisited = isVisited;
		}
	}

	public static void main(final String args[]) throws Exception {
		final char[][] map = readInput();
		final Node[] nodes = new Node[width * height];
		Node startNode = null;
		Node goalNode = null;
		List<Node> newGraphNodes = new ArrayList<Node>();

		int currentIndex = -1;
		int neighbourIndex = -1;
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				currentIndex++;
				final int nodenumber = i * width + j;
				nodes[currentIndex] = new Node(nodenumber, map[i][j]);
				if (map[i][j] == 'S') {
					startNode = nodes[currentIndex];
				} else if (map[i][j] == '@') {
					checkpoints++;
					newGraphNodes.add(nodes[currentIndex]);
				} else if (map[i][j] == 'G') {
					goalNode = nodes[currentIndex];
				}
			}
		}

		newGraphNodes.add(0, startNode);
		newGraphNodes.add(newGraphNodes.size(), goalNode);

		// Validate Inputs and return -1
		if (!validateArguments()) {
			int res = -1;
			System.out.println(res);
		}
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				currentIndex = i * width + j;
				if (i - 1 >= 0 && map[i][j] != '#') {
					neighbourIndex = (i - 1) * width + j;
					nodes[currentIndex].addEdge(nodes[neighbourIndex]);
				}
				if (i + 1 < height && map[i][j] != '#') {
					neighbourIndex = (i + 1) * width + j;
					nodes[currentIndex].addEdge(nodes[neighbourIndex]);
				}
				if (j - 1 >= 0 && map[i][j] != '#') {
					neighbourIndex = i * width + (j - 1);
					nodes[currentIndex].addEdge(nodes[neighbourIndex]);
				}
				if (j + 1 < width && map[i][j] != '#') {
					neighbourIndex = i * width + (j + 1);
					nodes[currentIndex].addEdge(nodes[neighbourIndex]);
				}
			}
		}

		int dist[][] = FloydWarshall(nodes);

		// Construct a new Graph having only Start Node, Goal Node, Check
		// Points.
		int V = newGraphNodes.size();
		int newDist[][] = new int[V][V];
		for (int i = 0; i < V; i++) {
			for (int j = 0; j < V; j++) {
				if (i == j) {
					newDist[i][j] = 0;
				} else {
					newDist[i][j] = dist[newGraphNodes.get(i).getNodeId()][newGraphNodes
							.get(j).getNodeId()];
				}
			}
		}

		System.out.println(getHamiltonianDistance(newDist));
	}

	/**
	 * Vaidates the given input arguments
	 * 
	 * @return
	 */
	private static boolean validateArguments() {
		if (width < 1 || width > 100)
			return false;
		else if (width < 1 || width > 100)
			return false;
		else if (checkpoints > 18)
			return false;
		return true;
	}

	/**
	 * Gets the Shortest Hamiltonian Distance
	 * 
	 * @param dist
	 * @return
	 */
	private static int getHamiltonianDistance(int dist[][]) {
		int n = dist.length;
		int[][] dp = new int[1 << n][n];
		for (int[] d : dp)
			Arrays.fill(d, Integer.MAX_VALUE);
		dp[1][0] = 0;
		for (int mask = 1; mask < 1 << n; mask += 2) {
			for (int i = 1; i < n; i++) {
				if ((mask & 1 << i) != 0) {
					for (int j = 0; j < n; j++) {
						if ((mask & 1 << j) != 0 && dist[j][i] != INF
								&& dp[mask ^ (1 << i)][j] != INF) {
							dp[mask][i] = Math.min(dp[mask][i], dp[mask
									^ (1 << i)][j]
									+ dist[j][i]);
						}
					}
				}
			}
		}

		int requiredMask = (int) (Math.pow(2, n) - 1);
		int res = Integer.MAX_VALUE;
		if (dp[requiredMask][n - 1] < res) {
			res = dp[requiredMask][n - 1];
		} else {
			res = -1;
		}

		return res;
	}

	/**
	 * Gets the Floyd Warshall Distances.
	 * 
	 * @param nodes
	 * @return
	 */
	private static int[][] FloydWarshall(final Node[] nodes) {
		int distTo[][] = new int[nodes.length][nodes.length];

		int V = nodes.length;
		// initialize distances to infinity
		for (int v = 0; v < nodes.length; v++) {
			for (int w = 0; w < nodes.length; w++) {
				distTo[v][w] = INF;
			}
		}

		// initialize distances
		for (Node node : nodes) {
			for (Node neighbour : node.getneighbours()) {
				distTo[node.getNodeId()][neighbour.getNodeId()] = 1;
			}
		}

		// Floyd-Warshall updates
		int n = distTo.length;
		for (int k = 0; k < n; k++) {
			for (int i = 0; i < n; i++) {
				if (distTo[i][k] == INF)
					continue;
				for (int j = 0; j < n; j++) {
					if (distTo[k][j] == INF)
						continue;
					if (distTo[i][j] > distTo[i][k] + distTo[k][j]) {
						distTo[i][j] = distTo[i][k] + distTo[k][j];

					}
				}
			}
		}

		return distTo;
	}

	/**
	 * Read the Input.
	 * 
	 * @return
	 */
	private static char[][] readInput() throws Exception {
		final BufferedReader bin = new BufferedReader(new InputStreamReader(
				System.in));
		String readLine = bin.readLine();
		final String[] split = readLine.split(" ");
		width = Integer.parseInt(split[0]);
		height = Integer.parseInt(split[1]);
		final char[][] map = new char[height][width];
		int currentRow = -1;
		int iterations = height;
		while (iterations-- > 0) {
			readLine = bin.readLine();
			currentRow++;
			for (int i = 0; i < width; i++) {
				map[currentRow][i] = readLine.charAt(i);
			}
		}
		bin.close();
		return map;
	}
}
