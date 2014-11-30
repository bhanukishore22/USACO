/*
 ID: bhanuki2
 LANG: JAVA
 TASK: transform
 */
import java.io.*;
import java.util.*;

class transform {
	public static void main(String[] args) throws IOException {
		run();
	}

	private static void run() throws IOException {

		String inputFile = "transform.in";
		String outputFile = "transform.out";
		 BufferedReader f = new BufferedReader(new FileReader(inputFile));
		 PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(
		 outputFile)));
		//BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		//PrintWriter out = new PrintWriter(new BufferedWriter(
			//	new OutputStreamWriter(System.out)));
		int N = Integer.parseInt(f.readLine());
		int mat1[][] = new int[N][N];
		int mat2[][] = new int[N][N];
		for (int i = 0; i < N; i++) {
			String line = f.readLine();
			for (int j = 0; j < N; j++) {
				if (line.charAt(j) == '@') {
					mat1[i][j] = 0;
				} else {
					mat1[i][j] = 1;
				}
			}
		}

		for (int i = 0; i < N; i++) {
			String line = f.readLine();
			for (int j = 0; j < N; j++) {
				if (line.charAt(j) == '@') {
					mat2[i][j] = 0;
				} else {
					mat2[i][j] = 1;
				}
			}
		}

		int[][] mat0 = rotate90(mat1, N);
		if (isEqual(mat0, mat2, N)) {
			out.println(1);
			out.close();
			System.exit(0);
		}

		mat0 = rotate90(mat0, N);
		if (isEqual(mat0, mat2, N)) {
			out.println(2);
			out.close();
			System.exit(0);
		}

		mat0 = rotate90(mat0, N);
		if (isEqual(mat0, mat2, N)) {
			out.println(3);
			out.close();
			System.exit(0);
		}

		mat0 = mirror(mat1, N);
		if (isEqual(mat0, mat2, N)) {
			out.println(4);
			out.close();
			System.exit(0);
		}

		mat0 = rotate90(mat0, N);
		if (isEqual(mat0, mat2, N)) {
			out.println(5);
			out.close();
			System.exit(0);
		}

		mat0 = rotate90(mat0, N);
		if (isEqual(mat0, mat2, N)) {
			out.println(5);
			out.close();
			System.exit(0);
		}

		mat0 = rotate90(mat0, N);
		if (isEqual(mat0, mat2, N)) {
			out.println(5);
			out.close();
			System.exit(0);
		}

		mat0 = mat1;
		if (isEqual(mat0, mat2, N)) {
			out.println(6);
			out.close();
			System.exit(0);
		}

		out.println(7);
		out.close();
		System.exit(0);

	}

	/**
	 * a b c g d a d e f ==> h e b g h i i f c
	 * 
	 * @param mat
	 * @param N
	 * @return
	 */
	private static int[][] rotate90(int[][] mat, int N) {
		int ret[][] = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				ret[j][N - 1 - i] = mat[i][j];
			}
		}
		return ret;
	}

	private static int[][] mirror(int[][] mat, int N) {
		int ret[][] = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				ret[i][N - 1 - j] = mat[i][j];
			}
		}
		return ret;
	}

	private static boolean isEqual(int[][] mat1, int[][] mat2, int N) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (mat1[i][j] != mat2[i][j])
					return false;
			}
		}
		return true;

	}

	private static void printArray(int[][] mat) {
		for (int[] ele : mat) {
			System.out.println(Arrays.toString(ele));
		}
	}
}
