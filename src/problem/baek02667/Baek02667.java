package problem.baek02667;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author jy
 * 단지번호붙이기
 */
public class Baek02667 {

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream(Baek02667.class.getResource("").getPath() + "Baek02667.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());

		// 방문 여부는 단지 번호를 삽입
		int[][] a = new int[n + 1][n + 1];
		int[][] c = new int[n + 1][n + 1];
		int[] ans = new int[n * n];

		for (int i = 1; i <= n; i++) {
			String[] s = br.readLine().split("");
			for (int j = 1; j <= n; j++) {
				a[i][j] = Integer.parseInt(s[j - 1]);
			}
		}

		int cnt = 0;	// 단지수
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				// 집 있고, 방문 X -> bfs 탐색 시작
				if (a[i][j] == 1 && c[i][j] == 0) {
					bfs(a, c, i, j, ++cnt);
				}
			}
		}

		// 각 단지 count
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				ans[c[i][j]] += 1;
			}
		}

		System.out.println(cnt);
		// 1단지 ~ cnt 단지까지 오름차순 정렬
		Arrays.sort(ans, 1, cnt + 1);
		for (int i = 1; i <= cnt; i++) {
			System.out.println(ans[i]);
		}
	}

	public static void bfs(int[][] a, int[][] c, int x, int y, int cnt) {
		int n = a.length - 1;
		int ax = 0;
		int ay = 0;

		Queue<Pair> q = new LinkedList<>();
		q.add(new Pair(x, y));
		c[x][y] = cnt;

		while (!q.isEmpty()) {
			ax = q.peek().x;
			ay = q.peek().y;
			q.poll();

			// 상하좌우 4방향 고려
			// 방문여부를 단지번호로 표시
			// 좌측집 O, 방문 X
			if (ax > 1 && a[ax - 1][ay] == 1 && c[ax - 1][ay] == 0) {
				q.add(new Pair(ax - 1, ay));
				c[ax - 1][ay] = cnt;
			}
			// 우측집 O, 방문 X
			if (ax < n && a[ax + 1][ay] == 1 && c[ax + 1][ay] == 0) {
				q.add(new Pair(ax + 1, ay));
				c[ax + 1][ay] = cnt;
			}
			// 아래쪽 집 O, 방문 X
			if (ay > 1 && a[ax][ay - 1] == 1 && c[ax][ay - 1] == 0) {
				q.add(new Pair(ax, ay - 1));
				c[ax][ay - 1] = cnt;
			}
			// 위쪽 집 O, 방문 X
			if (ay < n && a[ax][ay + 1] == 1 && c[ax][ay + 1] == 0) {
				q.add(new Pair(ax, ay + 1));
				c[ax][ay + 1] = cnt;
			}
		}
	}

	static class Pair {
		int x;
		int y;

		Pair(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}
