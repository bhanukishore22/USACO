/*
 ID: bhanuki2
 LANG: JAVA
 TASK: milk
 */
import java.io.*;
import java.util.*;

public class milk {
	static class ArrayComparator implements Comparator<int[]> {
		private final int columnToSort;
		private final boolean ascending;

		public ArrayComparator(int columnToSort, boolean ascending) {
			this.columnToSort = columnToSort;
			this.ascending = ascending;
		}

		public int compare(int[] c1, int[] c2) {
			int cmp = Integer.valueOf(c1[columnToSort]).compareTo(Integer.valueOf(c2[columnToSort]));

			return ascending ? cmp : -cmp;
		}
	}

	public static void main(String[] args) throws IOException {
		String inputFile = "milk.in";
		String outputFile = "milk.out";
		BufferedReader f = new BufferedReader(new FileReader(inputFile));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(
				outputFile)));
		// BufferedReader f = new BufferedReader(new
		// InputStreamReader(System.in));
		// PrintWriter out = new PrintWriter(new BufferedWriter(
		// new OutputStreamWriter(System.out)));
		StringTokenizer st = new StringTokenizer(f.readLine());
		int requiredUnits = Integer.parseInt(st.nextToken());
		int farmers = Integer.parseInt(st.nextToken());
		int[][] costs = new int[farmers][2];
		int counter = 0;
		while (counter != farmers) {
			st = new StringTokenizer(f.readLine());
			costs[counter][0] = Integer.parseInt(st.nextToken());
			costs[counter][1] = Integer.parseInt(st.nextToken());
			counter++;
		}

		int minCost = 0;
		Arrays.sort(costs, new ArrayComparator(0, true));
		// System.out.println(Arrays.deepToString(costs));
		for (int[] unitCost : costs) {
			if (requiredUnits <= 0)
				break;
			int consumed = requiredUnits >= unitCost[1] ? unitCost[1]
					: (requiredUnits);
			minCost += consumed * unitCost[0];
			requiredUnits = requiredUnits - consumed;
		}

		out.println(minCost);
		out.close(); // close the output file
		System.exit(0); // don't omit this!
	}
}
