/*
 ID: bhanuki2
 LANG: JAVA
 TASK: ride
 */
import java.io.*;
import java.util.*;

class ride {
	public static void main(String[] args) throws IOException {
		String inputFile = "ride.in";
		String outputFile = "ride.out";
		BufferedReader f = new BufferedReader(new FileReader(inputFile));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(
				outputFile)));
		// BufferedReader f = new BufferedReader(new
		// InputStreamReader(System.in));
		// PrintWriter out = new PrintWriter(new BufferedWriter(new
		// OutputStreamWriter(System.out)));
		String cometName = f.readLine();
		String cometGroup = f.readLine();
		if (calculate(cometGroup) == calculate(cometName)) {
			out.println("GO");
		} else {
			out.println("STAY");
		}
		out.close(); // close the output file
		System.exit(0); // don't omit this!
	}

	private static int calculate(String word) {
		int mul = 1;
		for (int i = 0; i < word.length(); i++) {
			mul = (mul * ((word.charAt(i) - 'A') + 1)) % 47;
		}
		return mul;
	}
}
