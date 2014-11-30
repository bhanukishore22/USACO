/*
 ID: bhanuki2
 LANG: JAVA
 TASK: barn1
 */
import java.io.*;
import java.util.*;

public class barn1 {
	static class ArrayComparator implements Comparator<int[]> {
		private final int columnToSort;
		private final boolean ascending;

		public ArrayComparator(int columnToSort, boolean ascending) {
			this.columnToSort = columnToSort;
			this.ascending = ascending;
		}

		public int compare(int[] c1, int[] c2) {
			int cmp = Integer.valueOf(c1[columnToSort]).compareTo(
					Integer.valueOf(c2[columnToSort]));

			return ascending ? cmp : -cmp;
		}
	}

	public static void main(String[] args) throws IOException {
		String inputFile = "barn1.in";
		String outputFile = "barn1.out";
		// BufferedReader f = new BufferedReader(new FileReader(inputFile));
		// PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(
		// outputFile)));
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new BufferedWriter(
				new OutputStreamWriter(System.out)));
		StringTokenizer st = new StringTokenizer(f.readLine());
		int boards = Integer.parseInt(st.nextToken());
		int stalls = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		int[] occupied = new int[C];
		int diff[][] = new int[C - 1][2];
		int index = 0;
		int prev = Integer.MIN_VALUE;
		while (C-- > 0) {
			int stallNumer = Integer.parseInt(f.readLine());
			occupied[index] = stallNumer;
			if (prev != Integer.MIN_VALUE) {
				diff[index - 1][0] = stallNumer - prev;
				diff[index - 1][1] = index - 1;
			}
			prev = stallNumer;
			index++;
		}

		Arrays.sort(diff, new ArrayComparator(0, false));
		index = 0;
		int breakpoints[] = new int[boards - 1];
		for (int i = 0; i <= boards - 2; i++) {
			breakpoints[index++] = diff[i][1];
		}

		int coveredStalls = 0;
		Arrays.sort(breakpoints);
		List<Integer> list = new ArrayList(Arrays.asList(breakpoints));
		list.add(0, 0);
		list.add(list.size() - 1, occupied.length - 1);
		int startIndex = Integer.MIN_VALUE;
		for (int i = 0; i < list.size(); i++) {
			if (startIndex != Integer.MIN_VALUE)
			coveredStalls += -(occupied[startIndex] - occupied[breakpoints[list.get(i)]])
					+ 1;
			startIndex = list.get(i);
		}
		out.println(coveredStalls);
		out.close(); // close the output file
		System.exit(0); // don't omit this!
	}
}
