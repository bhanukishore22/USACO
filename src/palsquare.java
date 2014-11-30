/*
 ID: bhanuki2
 LANG: JAVA
 TASK: palsquare
 */
import java.io.*;
import java.util.*;

class palsquare {
	private static Map<Integer, Character> mappings = new HashMap<Integer, Character>();

	public static void main(String[] args) throws IOException {
		mappings.put(0, '0');
		mappings.put(1, '1');
		mappings.put(2, '2');
		mappings.put(3, '3');
		mappings.put(4, '4');
		mappings.put(5, '5');
		mappings.put(6, '6');
		mappings.put(7, '7');
		mappings.put(8, '8');
		mappings.put(9, '9');
		mappings.put(10, 'A');
		mappings.put(11, 'B');
		mappings.put(12, 'C');
		mappings.put(13, 'D');
		mappings.put(14, 'E');
		mappings.put(15, 'F');
		mappings.put(16, 'G');
		mappings.put(17, 'H');
		mappings.put(18, 'I');
		mappings.put(19, 'J');
		mappings.put(20, 'K');
		run();
	}

	private static void run() throws IOException {

		String inputFile = "palsquare.in";
		String outputFile = "palsquare.out";

		BufferedReader f = new BufferedReader(new FileReader(inputFile));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(
				outputFile)));
		// BufferedReader f = new BufferedReader(new
		// InputStreamReader(System.in));
		// PrintWriter out = new PrintWriter(new BufferedWriter(
		// new OutputStreamWriter(System.out)));
		int B = Integer.parseInt(f.readLine());
		for (int i = 1; i <= 300; i++) {
			testNumber(i, i * i, B, out);
		}
	}

	private static void testNumber(int num, int square, int base,
			PrintWriter out) {
		String numBase = convertBase(num, base);
		String squareBase = convertBase(square, base);
		if (isPalindrome(squareBase)) {
			out.println(numBase + " " + squareBase);
			out.flush();
		}
	}

	private static String convertBase(int num, int base) {
		StringBuffer sb = new StringBuffer();
		while (num != 0) {
			int rem = num % base;
			sb.append(mappings.get(rem));
			num = num / base;
		}

		return sb.reverse().toString();
	}

	private static boolean isPalindrome(String word) {
		for (int i = 0, j = word.length() - 1; i <= j; i++, j--) {
			if (word.charAt(i) != word.charAt(j)) {
				return false;
			}
		}
		return true;
	}
}
