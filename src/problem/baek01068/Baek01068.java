package problem.baek01068;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Baek01068 {
	static ArrayList<Integer>[] childrenArr;
	public static void main(String[] args) {

		try {
			String path = Baek01068.class.getResource("").getPath();
			System.setIn(new FileInputStream(path + "Baek01068.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		System.out.println("N: " + N);
		int[] parent = new int[N];
		childrenArr = (ArrayList<Integer>[]) new ArrayList[N];
		for (int i = 0; i < N; i++) {
			childrenArr[i] = new ArrayList<>();
		}
		int root = 0;

		for (int i = 0; i < N; i++) {
			int P = sc.nextInt(); // parent node
			System.out.println("P: " + P + " ");
			parent[i] = P;
			if (P == -1) {
				root = i;
				continue;
			}
			childrenArr[P].add(i);
		}

		int R = sc.nextInt(); // Node Number to remove
		int result = 0;
		// remove = root 
		if(R == root) {
			result = 0;
		} else {
			childrenArr[parent[R]].remove((Integer) R); // Remove Node
			result = leafNodeCount(childrenArr[root], 0);
		}
		System.out.println("R: " + R);
		System.out.println("result: " + result);
	}

	static int leafNodeCount(ArrayList<Integer> children, int cnt) {
		if (children.size() == 0) {
			cnt++;
			return cnt;
		} else {
			for (Integer child : children) {
				cnt = leafNodeCount(childrenArr[child], cnt);
			}
			return cnt;
		}
	}
}
