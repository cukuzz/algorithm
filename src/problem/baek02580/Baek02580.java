package problem.baek02580;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Baek02580 {
	static long matrix[][] = new long[9][9];

	public static void main(String[] args) {
		try {
			String path = Baek02580.class.getResource("").getPath();
			System.setIn(new FileInputStream(path + "Baek2580.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		Scanner sc = new Scanner(System.in);

		for (int i=0; i<9; i++) {
			for (int j=0; j<9; j++) {
				matrix[i][j]=sc.nextInt();
			}
		}


		for (int i=0; i<9; i++) {
			int cnt = 0;
			int y = 0;
			// 한줄에 빈 칸이 몇개인지 검사
			for (int j=0; j<9; j++) {
				if (matrix[i][j] == 0) {
					cnt++;
					y = j;
				}
				System.out.println(" @ " + matrix[i][j]);
			}
			// 빈 칸이 한개일 경우 숫자 채워 넣음
			if (cnt == 1) {
				fillBlank(i, y);
				
			}
		}
	}
	// 없는 수 찾아서 빈  칸 채우기
	private static void fillBlank(int x, int y) {
		int sum = 0;
		for (int j=0; j<8; j++) {
			sum += matrix[x][j];
		}
		int num = 45 - sum;
		matrix[x][y] = num;
	}
}
