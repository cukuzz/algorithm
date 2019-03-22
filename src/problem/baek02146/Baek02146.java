package problem.baek02146;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @author jy 다리만들기
 *
 */
public class Baek02146 {

	public static final int[] dx = { 0, 0, 1, -1 };
	public static final int[] dy = { 1, -1, 0, 0 };

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream(Baek02146.class.getResource("").getPath() + "Baek02146.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		int[][] adj = new int[N + 1][N + 1];
		boolean[][] visited = new boolean[N + 1][N + 1];

		// 행렬 입력
		for (int i = 1; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				adj[i][j] = Integer.parseInt(st.nextToken(" "));
			}
		}

		int cnt = 0; // 섬번호
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if (adj[i][j] == 1 && !visited[i][j]) {
					numbering(adj, visited, i, j, ++cnt);
				}
			}
		}

		// queue 에 기존 섬 넣기(기존 섬 이후로 확장되는 것만 탐색)
		Queue<Pair> q = new LinkedList<>();
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if (a[i][j] != 0) {
					q.add(new Pair(i, j));
				}
			}

		}

		StringBuffer sb = new StringBuffer();
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				sb.append(adj[i][j]);
				sb.append(" ");
			}
			sb.append("\n");
		}
		br.close();
	}

	public static void numbering(int[][] adj, boolean[][] visited, int x, int y, int index) {
		int n = adj.length - 1;
		Queue<Pair> q = new LinkedList<>();
		q.add(new Pair(x, y));
		adj[x][y] = index;
		visited[x][y] = true;

		while (!q.isEmpty()) {
			x = q.peek().x;
			y = q.peek().y;
			q.poll();

			for (int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				if (0 < nx && nx <= n && 0 < ny && ny <= n) {
					if (adj[x][y] == 1 && !visited[x][y]) {
						q.add(new Pair(nx, ny));
						adj[x][y] = index;
						visited[x][y] = true;
					}
				}
			}
		}
	}

	public static void bfs() {

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
