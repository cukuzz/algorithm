package problem.baek09461;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

import problem.baek02580.Baek02580;

public class Baek09461 {
	public static void main(String[] args) {

		try {
			String path = Baek09461.class.getResource("").getPath();
			System.setIn(new FileInputStream(path + "Baek9461.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		Scanner sc = new Scanner(System.in);
		int TESTCASE = sc.nextInt();

		long P[] = new long[101];
		P[1] = P[2] = P[3] = 1;
		P[4] = P[5] = 2;

		for (int i = 6; i <= 100; i++) {
			P[i] = P[i - 1] + P[i - 5];
		}

		for (int TC = 1; TC <= TESTCASE; TC++) {
			int N = sc.nextInt();
			System.out.println(P[N]);
		}
		sc.close(); 
	}
}
