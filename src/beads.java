/*
 ID: bhanuki2
 LANG: JAVA
 TASK: beads
 */
import java.io.*;
import java.util.*;

class beads {
	public static void main(String[] args) throws IOException {
		String inputFile = "beads.in";
		String outputFile = "beads.out";
		BufferedReader f = new BufferedReader(new FileReader(inputFile));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(
				outputFile)));
		// BufferedReader f = new BufferedReader(new
		// InputStreamReader(System.in));
		// PrintWriter out = new PrintWriter(new BufferedWriter(
		// new OutputStreamWriter(System.out)));
		f.readLine();
		String beads = f.readLine();
		List<Character> beadList = new ArrayList<Character>();
		List<Integer> count = new ArrayList<Integer>();

		char current = beads.charAt(0);
		int counter = 1;
		for (int i = 1; i < beads.length(); i++) {
			char temp = beads.charAt(i);
			if (current == temp) {
				counter++;
				continue;
			}

			beadList.add(current);
			count.add(counter);
			counter = 1;
			current = temp;
		}

		beadList.add(current);
		count.add(counter);

		int max = Integer.MIN_VALUE;
		for (int i = 0, j = 1; j < beadList.size(); i++, j++) {
			counter = 0;
			int left = i;
			int right = j;

			char leftChar = beadList.get(left);
			while (left != right) {
				if (leftChar != 'w' && beadList.get(left) != leftChar
						&& beadList.get(left) != 'w')
					break;
				counter += count.get(left);
				left = (left - 1);
				if (left < 0)
					left = beadList.size() - 1;
				if (leftChar == 'w' && beadList.get(left) != 'w') {
					leftChar = beadList.get(left);
				}
			}

			left = (left + 1) % beadList.size();

			char rightChar = beadList.get(right);
			while (right != left) {
				if (rightChar != 'w' && beadList.get(right) != rightChar
						&& beadList.get(right) != 'w')
					break;
				counter += count.get(right);
				right = (right + 1) % beadList.size();
				if (rightChar == 'w' && beadList.get(right) != 'w') {
					rightChar = beadList.get(right);
				}
			}

			if (max < counter) {
				max = counter;
			}
		}

		if (max == Integer.MIN_VALUE) {
			max = count.get(0);
		}
		out.println(max);
		out.close(); // close the output file
		System.exit(0); // don't omit this!
	}
}
