package com.xjs.alg;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;


public class BestFirstSearch {

	public static Map<Edge, Integer> edges = new HashMap<>();
	public static Map<GraphNode, Boolean> visited = new HashMap<>();

	public static void main(String[] args) {
		BestFirstSearch bestFirstSearch = new BestFirstSearch();
		PriorityQueue<Entry> queue = new PriorityQueue<>(new Comparator<Entry>() {

			@Override
			public int compare(Entry o1, Entry o2) {
				if (o1.bfs == o2.bfs)
					return 0;
				return o1.bfs > o2.bfs ? 1 : -1;
			}
		});
		GraphNode start = initGraph();
		Entry startEntry = new Entry(0, Arrays.asList(start));
		queue.offer(startEntry);
		doBFS(bestFirstSearch, queue);
	}

	private static void doBFS(BestFirstSearch bestFirstSearch, PriorityQueue<Entry> queue) {
		Entry poll;

		for (;;) {
			if (!queue.isEmpty() && queue.peek().path.get(queue.peek().path.size() - 1).key == 1) {
				List<GraphNode> path = queue.peek().path;
				path.stream().forEach(x -> {
					System.out.print(x.key + ", ");
				});
				return;
			}

			poll = queue.poll();
			StringBuilder sb0 = new StringBuilder();
			poll.path.stream().forEach(x -> {
				int key = x.key;
				sb0.append(key).append(",");
			});
			System.out.println("[ poll ] bfs: " + poll.bfs + ", path: " + sb0.substring(0, sb0.length() - 1));
			List<GraphNode> path = poll.path;
			GraphNode graphNode = path.get(path.size() - 1);
			List<GraphNode> neighbors = graphNode.neighbors;

			for (GraphNode nei : neighbors) {
				if (path.size() >= 2 && path.get(path.size() - 2).equals(nei))
					continue;

				Edge edge = new Edge(graphNode, nei);
				int weight = poll.bfs + edges.get(edge);
				Entry findEntry = findEntry(queue, nei);
				if (null == findEntry) {
					List<GraphNode> newPath = new ArrayList<>();
					newPath.addAll(path);
					newPath.add(nei);
					Entry newEntry = new Entry(weight, newPath);
					queue.offer(newEntry);
				} else if (findEntry.bfs > weight) {
					List<GraphNode> prevPath = findEntry.path;
					List<GraphNode> newPath = new ArrayList<>();
					newPath.addAll(path);
					newPath.add(nei);
					if (isSamePath(prevPath, newPath))
						continue;
					Entry newEntry = new Entry(weight, newPath);
					queue.offer(newEntry);
					queue.remove(findEntry);
				}
			}
			for (Entry entry : queue) {
				StringBuilder sb = new StringBuilder();
				entry.path.stream().forEach(x -> {
					int key = x.key;
					sb.append(key).append(",");
				});

				System.out.println("[ hold ] bfs: " + entry.bfs + ", path: " + sb.substring(0, sb.length() - 1));
			}
		}

	}

	private static boolean isSamePath(List<GraphNode> prevPath, List<GraphNode> newPath) {
		if (prevPath.size() != newPath.size())
			return false;
		else {
			for (int i = 0; i < prevPath.size(); i++) {
				if (!prevPath.get(i).equals(newPath.get(i)))
					return false;
			}
			return true;
		}
	}

	private static Entry findEntry(PriorityQueue<Entry> queue, GraphNode nei) {
		for (Entry each : queue) {
			List<GraphNode> path = each.path;
			for (GraphNode node : path) {
				if (node.equals(nei))
					return each;
			}
		}
		return null;
	}

	public static GraphNode initGraph() {
		GraphNode node4 = new GraphNode(4);
		GraphNode node3 = new GraphNode(3);
		GraphNode node5 = new GraphNode(5);
		GraphNode node2 = new GraphNode(2);
		GraphNode node1 = new GraphNode(1);

		node4.neighbors = Arrays.asList(node3, node5, node2);
		node3.neighbors = Arrays.asList(node4, node2);
		node5.neighbors = Arrays.asList(node4, node2, node1);
		node2.neighbors = Arrays.asList(node3, node5, node1, node4);
		node1.neighbors = Arrays.asList(node2, node5);

		edges.put(new Edge(node4, node3), 10);
		edges.put(new Edge(node3, node4), 10);
		edges.put(new Edge(node4, node5), 21);
		edges.put(new Edge(node5, node4), 21);
		edges.put(new Edge(node3, node2), 13);
		edges.put(new Edge(node2, node3), 13);
		edges.put(new Edge(node5, node2), 3);
		edges.put(new Edge(node2, node5), 3);
		edges.put(new Edge(node2, node1), 3);
		edges.put(new Edge(node1, node2), 3);
		edges.put(new Edge(node5, node1), 1);
		edges.put(new Edge(node1, node5), 1);
		edges.put(new Edge(node4, node2), 20);
		edges.put(new Edge(node2, node4), 20);
		return node4;
	}

	static class Entry {
		public int bfs;
		public List<GraphNode> path;

		public Entry(int bfs, List<GraphNode> path) {
			this.bfs = bfs;
			this.path = path;
		}

	}

	static class Edge {
		public GraphNode one;
		public GraphNode other;

		public Edge(GraphNode one, GraphNode other) {
			this.one = one;
			this.other = other;
		}

		@Override
		public boolean equals(Object obj) {
			if (!(obj instanceof Edge))
				return false;
			Edge other = (Edge) obj;
			return (this.one.key == other.one.key && this.other.key == other.other.key)
					|| (this.one.key == other.other.key && this.other.key == other.one.key);
		}

		@Override
		public int hashCode() {
			int h = 0;
			h = 31 * h + one.key;
			h = 31 * h + other.key;
			return h;
		}
	}

	static class GraphNode {
		public int key;
		public List<GraphNode> neighbors;

		public GraphNode(int key) {
			this.key = key;
		}

		@Override
		public boolean equals(Object obj) {
			if (!(obj instanceof GraphNode))
				return false;
			GraphNode other = (GraphNode) obj;
			return this.key == other.key;
		}

		@Override
		public int hashCode() {
			return key;
		}
	}
}
