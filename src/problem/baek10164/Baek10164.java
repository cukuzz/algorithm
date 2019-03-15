package problem.baek10164;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Baek10164 {
	public static void main(String[] args) {

		try {
			String path = Baek10164.class.getResource("").getPath();
			System.setIn(new FileInputStream(path + "Baek10164.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int M = sc.nextInt();
		int K = sc.nextInt();
		int answer = 0;

		System.out.println("N: " + N + " M: " + M + " K: " + K);
		int[][] matrix = initMatrix(N, M);

		// find path
		matrix[0][1] = 1;
		for (int i=1; i<N+1; i++) {
			for (int j=1; j<M+1; j++) {
				matrix[i][j] = matrix[i-1][j] + matrix[i][j-1];
			}
		}

		// find K (x, y) Coordinate
		int x = 1;
		int y = 1;
		if (K > 0) {
			x = (K - 1) / M + 1;
			y = (K - 1) % M + 1;
			int a = 0;
			int b = 0;
			a = matrix[x][y];
			b = matrix[N-x+1][M-y+1];
			answer = a*b;
		} else {
			x = N;
			y = M;
			answer = matrix[x][y];
		}
		System.out.println("answer : " + answer);
	}

	public static int[][] initMatrix(int N, int M) {
		int[][] matrix = new int[N+1][M+1];
		for (int i=0; i<N+1; i++) {
			for (int j=0; j<M+1; j++) {
				matrix[i][j] = 0;
			}
		}
		return matrix;
	}
}
