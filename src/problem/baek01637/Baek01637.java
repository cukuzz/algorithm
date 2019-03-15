package problem.baek01637;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Baek01637 {
	static int N;
	static int[] A;
	static int[] B;
	static int[] C;
	static final int N_MAX = 20000;
	static final int MAX = 2147483647;

	public static void main(String[] args) {
		try {
			String path = Baek01637.class.getResource("").getPath();
			System.setIn(new FileInputStream(path + "Baek01637.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		Scanner sc = new Scanner(System.in);

		// input
		N = sc.nextInt();
		System.out.println("N: " + N);

		A = new int[N_MAX];
		B = new int[N_MAX];
		C = new int[N_MAX];

		// integer number group
		for (int i = 0; i < N; i++) {
			A[i] = sc.nextInt();
			C[i] = sc.nextInt();
			B[i] = sc.nextInt();
		}

		long lo = 0;
		long hi = MAX;
		while (lo < hi) {
			long mid = (lo + hi) / 2;
			long cnt = sumCnt(mid);
			if (cnt % 2 == 0) {
				lo = mid + 1;
			} else {
				hi = mid;
			}
		}

		if (lo == MAX) {
			System.out.println("NOTHING");
		} else {
			long cnt = sumCnt(lo) - sumCnt(lo - 1);
			System.out.println(lo + " " + cnt);

		}
	}

	// n[0]: integer num++
	// n[1]: count
	static long sumCnt(long mid) {
		int cnt = 0;
		for (int i = 0; i < N; i++) {
			if (mid >= A[i]) {
				cnt += (Math.min(mid, C[i]) - A[i]) / B[i] + 1;
			}
		}
		return cnt;
	}
}
