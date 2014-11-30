/*
 ID: bhanuki2
 LANG: JAVA
 TASK: dualpal
 */
import java.io.*;
import java.util.*;

class dualpal {
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

		String inputFile = "dualpal.in";
		String outputFile = "dualpal.out";

		BufferedReader f = new BufferedReader(new FileReader(inputFile));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(
				outputFile)));
		// BufferedReader f = new BufferedReader(new
		// InputStreamReader(System.in));
		// PrintWriter out = new PrintWriter(new BufferedWriter(
		// new OutputStreamWriter(System.out)));
		String split[] = f.readLine().split(" ");
		int N = Integer.parseInt(split[0]);
		int S = Integer.parseInt(split[1]);
		List<Integer> numbers = new ArrayList<Integer>();
		for (int i = S + 1; i < Integer.MAX_VALUE; i++) {
			if (testNumber(i))
				numbers.add(i);
			if (numbers.size() == N)
				break;
		}

		if (numbers.size() == N) {
			for (Integer num : numbers) {
				out.println(num);
				out.flush();
			}
		}
	}

	private static boolean testNumber(int num) {
		int base = 0;
		for (int i = 2; i <= 10; i++) {
			String numBase = convertBase(num, i);
			if (isPalindrome(numBase)) {
				base++;
			}
			if (base >= 2) {
				return true;
			}
		}
		return false;
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
