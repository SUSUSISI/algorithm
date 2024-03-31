package org.example.grpah;

public class Floyd {


	public void floyd(int[][] graph) {

		int size = graph.length;

		for(int mid=0; mid< size; mid++) {
			for(int from=0; from<size; from++) {
				for(int to=0; to<size; to++) {
					if(graph[from][to] > graph[from][mid] + graph[mid][to]) {
						graph[from][to] = graph[from][mid] + graph[mid][to];
					}
				}
			}
		}
	}


	// https://school.programmers.co.kr/learn/courses/30/lessons/72413
	public int solution(int n, int s, int a, int b, int[][] fares) {
		int MAX = 20_000_000;
		int[][] graph = new int[n+1][n+1];
		for(int i=0; i<=n;i++) {
			for(int j=0; j<=n; j++) {
				graph[i][j] = i==j ? 0 : MAX;
			}
		}

		for(int[] fare : fares) {
			graph[fare[0]][fare[1]] = fare[2];
			graph[fare[1]][fare[0]] = fare[2];
		}

		floyd(graph);

		int answer = MAX;

		for(int i = 1 ; i<=n; i++) {
			int dis = graph[s][i] + graph[i][a] + graph[i][b];

			if(dis < answer) answer = dis;
		}

		return answer;
	}



}
