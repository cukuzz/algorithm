package problem.baek11403;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class Baek11403 {

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream(Baek11403.class.getResource("").getPath() + "Baek11403.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		int[][] adj = new int[N + 1][N + 1];
		int[] visited = new int[N + 1];
		int[][] result = new int[N + 1][N + 1];

		// 인접행렬 만들기
		for (int i = 1; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				// 방향그래프
				adj[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 1; i <= N; i++) {
//			dfs(adj, visited, i);
			dfs_stack(adj, visited, i, true);
			for (int j = 1; j <= N; j++) {
				result[i][j] = visited[j];
			}
			Arrays.fill(visited, 0);
		}

		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				sb.append(result[i][j] + " ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
		br.close();
	}

	public static void dfs(int[][] adj, int[] visited, int v) {
		int n = adj.length - 1;
		for (int i = 1; i <= n; i++) {
			if (adj[v][i] == 1 && visited[i] == 0) {
				visited[i] = 1;
				dfs(adj, visited, i);
			}
		}
	}

	public static void dfs_stack(int[][] adj, int[] visited, int v, boolean flag) {
		Stack<Integer> stack = new Stack<>();
		int n = adj.length - 1;
		stack.push(v);
		visited[v] = 1;

		while (!stack.isEmpty()) {
			int vv = stack.peek();
			flag = false;
			for (int i = 1; i <= n; i++) {
				// 인접한게 있고, 방문하지 않았을때
				if (adj[vv][i] == 1 && visited[i] == 0) {
					stack.push(i);
					visited[i] = 1;
					flag = true;
					break;
				}
			}
			if (!flag) {
				stack.pop();
			}
		}
	}
}
