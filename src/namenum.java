/*
 ID: bhanuki2
 LANG: JAVA
 TASK: namenum
 */
import java.io.*;
import java.util.*;

class namenum {
	static Set<String> words;
	static int valid = 0;
	static Map<Character, String> mapings;

	public static void main(String[] args) throws IOException {
		run();
	}

	private static void run() throws IOException {

		String inputFile = "namenum.in";
		String outputFile = "namenum.out";
		String dictFile = "dict.txt";
		words = new HashSet<String>();
		mapings = new HashMap<Character, String>();
		mapings.put('2', "ABC");
		mapings.put('3', "DEF");
		mapings.put('4', "GHI");
		mapings.put('5', "JKL");
		mapings.put('6', "MNO");
		mapings.put('7', "PRS");
		mapings.put('8', "TUV");
		mapings.put('9', "WXY");
		BufferedReader f = new BufferedReader(new FileReader(inputFile));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(
				outputFile)));
		BufferedReader d = new BufferedReader(new FileReader(dictFile));
		String line = null;
		while ((line = d.readLine()) != null) {
			words.add(line.trim());
		}
		d.close();
		// BufferedReader f = new BufferedReader(new
		// InputStreamReader(System.in));
		// PrintWriter out = new PrintWriter(new BufferedWriter(
		// new OutputStreamWriter(System.out)));
		generateWords(f.readLine(), 0, new StringBuffer(), out);
		if (valid == 0)
			out.println("NONE");
		out.close();
		System.exit(0);
	}

	private static void generateWords(String number, int current,
			StringBuffer partial, PrintWriter out) {

		if (current == number.length()) {
			if (words.contains(partial.toString())) {
				out.println(partial.toString());
				valid++;
			}
			return;
		}

		char ch = number.charAt(current);
		String options = mapings.get(ch);
		for (int i = 0; i < options.length(); i++) {
			partial.append(options.charAt(i));
			generateWords(number, current + 1, partial, out);
			partial.deleteCharAt(partial.length() - 1);
		}
	}
}
