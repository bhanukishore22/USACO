/*
 ID: bhanuki2
 LANG: JAVA
 TASK: gift1
 */
import java.io.*;
import java.util.*;

class gift1 {
	public static void main(String[] args) throws IOException {
		String inputFile = "gift1.in";
		String outputFile = "gift1.out";
		BufferedReader f = new BufferedReader(new FileReader(inputFile));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(
				outputFile)));
		// BufferedReader f = new BufferedReader(new
		// InputStreamReader(System.in));
		// PrintWriter out = new PrintWriter(new BufferedWriter(
		// new OutputStreamWriter(System.out)));
		int n = Integer.parseInt(f.readLine());
		String persons[] = new String[n];
		int balance[] = new int[n];
		int initial[] = new int[n];
		Arrays.fill(balance, 0);
		Arrays.fill(initial, 0);
		Map<String, Integer> map = new HashMap<String, Integer>();
		for (int i = 0; i < n; i++) {
			persons[i] = f.readLine();
			map.put(persons[i], i);
		}

		int rounds = n;
		while (rounds-- > 0) {
			String person = f.readLine();
			StringTokenizer st = new StringTokenizer(f.readLine(), " ");
			int init = Integer.parseInt(st.nextToken());
			int left = init;
			initial[map.get(person)] = init;
			int numGivers = Integer.parseInt(st.nextToken());
			int iterations = numGivers;
			if (numGivers > 0) {
				left = init % numGivers;
				while (iterations-- > 0) {
					balance[map.get(f.readLine())] += (init / numGivers);
				}
			}

			balance[map.get(person)] += left;
		}

		for (String name : persons) {
			int index = map.get(name);
			out.println(name + " " + (balance[index] - initial[index]));
		}

		out.close(); // close the output file
		System.exit(0); // don't omit this!
	}
}
