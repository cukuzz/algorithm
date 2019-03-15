package samsung.swtest.findTallAncestor;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class FindTallAncestor {
	public static int T; // Test Scenario
	public static int N; // Member
	public static int E; // Test count in scenario
	public static int K; // Selected Member count

	public static int[] parent; // parent node number
	public static int[] depth; // depth

	public static LinkedList<Integer>[] adjList; // adjacent node

	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream(FindTallAncestor.class.getResource("").getPath() + "input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken(" "));
			E = Integer.parseInt(st.nextToken(" "));

			Node[] nodes = new Node[N + 1];

			for (int i = 0; i <= N; i++) {
				nodes[i] = new Node();
			}

			// tree
			for (int i = 1; i <= N; i++) {
				st = new StringTokenizer(br.readLine());
				int parent = Integer.parseInt(st.nextToken(" "));
				int height = Integer.parseInt(st.nextToken(" "));
				nodes[i].ancestorNode = parent;
				nodes[i].selfNode = i;
				nodes[i].height = height;
				nodes[parent].children.add(nodes[i]);
				if (parent == 0) {
					nodes[parent].depth = 0;
					nodes[parent].tallest = height;
				}
			}

			// fill the depth field
			Queue<Node> queue = new LinkedList<>();
			queue.add(nodes[0]);
			while (!queue.isEmpty()) {
				Node current = queue.poll();
				for (Node child : current.children) {
					if (child.depth == -1) {
						child.depth = current.depth + 1;
						child.tallest = Math.max(child.height, current.tallest);
						queue.add(child);
					}
				}
			}

			long start = System.currentTimeMillis();

			StringBuffer sb = new StringBuffer();
			sb.append("#" + t + " ");
			// LCA (Lowest Common Ancestor)
			for (int i = 1; i <= E; i++) {
				st = new StringTokenizer(br.readLine());
				K = Integer.parseInt(st.nextToken(" "));

				Node node1 = nodes[Integer.parseInt(st.nextToken(" "))];
				int tallest = 0;
				for (int j = 1; j < K; j++) {
					Node node2 = nodes[Integer.parseInt(st.nextToken(" "))];
					if (node1.depth > node2.depth) {
						Node temp = node1;
						node1 = node2;
						node2 = temp;
					}

					while (node1.depth != node2.depth) {
						node2 = nodes[node2.ancestorNode];
					}

					if (node1.selfNode == node2.selfNode) {
						tallest = node1.tallest;
					} else {
						while (node1.ancestorNode != node2.ancestorNode) {
							node1 = nodes[node1.ancestorNode];
							node2 = nodes[node2.ancestorNode];
						}
						tallest = nodes[node1.ancestorNode].tallest;
					}
				}
				sb.append(tallest);
				sb.append(" ");
			}
			System.out.println(sb);
			long end = System.currentTimeMillis();
			System.out.println("running time : " + (end - start) / 1000.0 + " second\n");
		}
		br.close();
	}

	static class Node {
		int ancestorNode = -1;
		int selfNode = -1;
		List<Node> children = new ArrayList<>();
		int height = -1;
		int depth = -1;
		int tallest = 0;
	}
}
