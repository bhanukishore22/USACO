/*
 ID: bhanuki2
 LANG: JAVA
 TASK: friday
 */
import java.io.*;
import java.util.*;

class friday {
	public static void main(String[] args) throws IOException {
		String inputFile = "friday.in";
		String outputFile = "friday.out";
		BufferedReader f = new BufferedReader(new FileReader(inputFile));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(
				outputFile)));
		// BufferedReader f = new BufferedReader(new
		// InputStreamReader(System.in));
		// PrintWriter out = new PrintWriter(new BufferedWriter(
		// new OutputStreamWriter(System.out)));
		int n = Integer.parseInt(f.readLine());
		int count[] = new int[7];
		Arrays.fill(count, 0);
		int days[] = new int[13];
		days[0] = 31;
		days[1] = 28;
		days[2] = 31;
		days[3] = 30;
		days[4] = 31;
		days[5] = 30;
		days[6] = 31;
		days[7] = 31;
		days[8] = 30;
		days[9] = 31;
		days[10] = 30;
		days[11] = 31;

		int day = 6;
		count[day]++;
		int month = 0;
		int year = 1900;
		int end = 1900 + n - 1;

		while (!(month == 11 && year == end)) {
			int totalDays = 0;
			if (month == 1) {
				if (leapYear(year)) {
					totalDays = 29 - 13;
				} else {
					totalDays = 28 - 13;
				}
			} else {
				totalDays = days[(month) % 12] - 13;
			}
			totalDays += 13;

			day = (day + totalDays % 7) % 7;
			count[day]++;

			month = (month + 1) % 12;
			if (month == 0)
				year++;
		}

		out.println(count[6] + " " + count[0] + " " + count[1] + " " + count[2]
				+ " " + count[3] + " " + count[4] + " " + count[5]);
		out.close(); // close the output file
		System.exit(0); // don't omit this!
	}

	private static boolean leapYear(int year) {
		if (year % 100 == 0) {
			return year % 400 == 0 ? true : false;
		} else
			return year % 4 == 0 ? true : false;
	}
}
