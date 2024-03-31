package org.example;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.stream.IntStream;

public class Main {
	public static void main(String[] args) {
		System.out.println("Hello world!");
	}

	private class Data {

		int node;
		int dis;

		public Data (int node, int dis) {

			this.node = node;
			this.dis = dis;
		}
	}


	class Solution {


		public int solution(int n, int s, int a, int b, int[][] fares) {

			List<Map<Integer, Integer>> graph = new ArrayList<>(n+1);
			for(int i = 0; i <= n; i++) {
				graph.set(i, new HashMap<>());
			}

			for (int[] fare : fares) {

				graph.get(fare[0]).put(fare[1], fare[2]);
				graph.get(fare[1]).put(fare[0], fare[2]);
			}


			int min = Integer.MAX_VALUE;

			for(int i = 1; i<=n; i++) {

				int dis = this.dijkstra(n, i, graph) + this.dijkstra(i, a, graph) + this.dijkstra(i, b, graph);

				if(Objects.isNull(min) || dis < min) {

					min = dis;
				}
			}

			return min;
		}

		private int dijkstra(int start, int end, List<Map<Integer, Integer>> graph) {

			List<Integer> dis = new ArrayList<>(graph.size());

			dis.set(start, 0);
			PriorityQueue<Data> queue = new PriorityQueue<>(Comparator.comparingInt(o -> o.dis));
			queue.add(new Data(start, 0));

			while(!queue.isEmpty()) {

				Data data = queue.poll();

				if(data.node == end) {

					return data.dis;
				}

				for(Map.Entry<Integer, Integer> entry : graph.get(data.node).entrySet()) {

					int next = entry.getKey();
					int nextDis = entry.getValue() + data.dis;

					if(Objects.isNull(dis.get(next)) || dis.get(next) > nextDis) {

						dis.set(next, nextDis);
						queue.add(new Data(next, nextDis));
					}
				}
			}

			return Integer.MAX_VALUE;
		}
	}
}