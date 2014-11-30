/*
 ID: bhanuki2
 LANG: JAVA
 TASK: milk2
 */
import java.io.*;
import java.util.*;

class milk2 {
	static class Node implements Comparable<Node> {
		int data;
		int eventType;

		public int getData() {
			return data;
		}

		public int getEventType() {
			return eventType;
		}

		public Node(int data, int event) {
			this.data = data;
			this.eventType = event;
		}

		@Override
		public int compareTo(Node o) {
			if (data < o.getData()) {
				return -1;
			} else if (data == o.getData()) {
				if (eventType == o.getEventType()) {
					return 0;
				} else if (eventType > o.getEventType()) {
					return 1;
				} else {
					return -1;
				}
			} else {
				return 1;
			}
		}
	}

	public static void main(String[] args) throws IOException {
		String inputFile = "milk2.in";
		String outputFile = "milk2.out";
		BufferedReader f = new BufferedReader(new FileReader(inputFile));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(
				outputFile)));
		// BufferedReader f = new BufferedReader(new
		// InputStreamReader(System.in));
		// PrintWriter out = new PrintWriter(new BufferedWriter(
		// new OutputStreamWriter(System.out)));
		int n = Integer.parseInt(f.readLine());
		PriorityQueue<Node> queue = new PriorityQueue<Node>();
		while (n-- > 0) {
			StringTokenizer st = new StringTokenizer(f.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			Node node = new Node(from, 0);
			queue.add(node);
			node = new Node(to, 1);
			queue.add(node);
		}

		int start = Integer.MIN_VALUE;
		int end = Integer.MIN_VALUE;
		int active = 0;
		int longestActive = 0;
		int longestNotActive = 0;

		while (queue.size() != 0) {
			Node node = queue.poll();
			if (node.getEventType() == 0) {
				if (active == 0) {
					start = node.getData();
					if (longestNotActive < (start - end)) {
						longestNotActive = (start - end);
					}

				}
				active++;
			} else {
				active--;
				if (active == 0) {
					end = node.getData();
					if (longestActive < (end - start)) {
						longestActive = (end - start);
					}
				}
			}
		}

		out.println(longestActive + " " + longestNotActive);
		out.close(); // close the output file
		System.exit(0); // don't omit this!
	}
}
