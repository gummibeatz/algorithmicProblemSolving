package hwk7;

import java.io.*;
import java.util.*;

public class Traffic
{
	final static int OFFSET = 1;
	final static double INF = Double.POSITIVE_INFINITY;
	final static double NEGINF = Double.NEGATIVE_INFINITY;
	public static int[] isVisited = new int[200];
	public static double[] dist = new double[200];
	
	//runs Bellman_Ford twice, to check for negative cycles
	public static double[] Bellman_Ford(int n, double[] dist, ArrayList<ArrayList<Edge>> adjList)
	{
		for(int i=0; i<n-1; i++){
			for(int u=0; u<n; u++){
				for(int j=0; j<adjList.get(u).size(); j++){
					Edge e = adjList.get(u).get(j);
					// min((distance to j), (distance to u) + (distance from u to j)
					dist[e.B] = Math.min(dist[e.B], dist[u] + e.w);
				}
			}
		}
		
		//check for neg-cycles
		for(int u=0; u<n; u++){
			for(int j=0; j<adjList.get(u).size(); j++){
				Edge e = adjList.get(u).get(j);
				double temp = dist[e.B];
				dist[e.B] = Math.min(dist[e.B], dist[u] + e.w);
				if(temp!=dist[e.B]){dist[u] = NEGINF;}
			}
		}
		
		//mark reachable states from neg-cycles as neg-ing
		for(int i =0; i<n; i++)
		{
			if(dist[i]==NEGINF)
			{
				DFS(i,isVisited,dist,adjList);
			}
		}
		return dist;
	}
	
	public static void DFS(int node, int[] isVisited, double[] dist, ArrayList<ArrayList<Edge>> adjList)
	{

		ArrayList<Edge> AL = adjList.get(node);
		if(isVisited[node] >0)	{return;}
		isVisited[node] = 1;
		dist[node] = NEGINF;
		if(AL.isEmpty())	{return;}
		for(int i=0; i<AL.size(); i++)
		{
			Edge e = AL.get(i);
			DFS(e.B,isVisited,dist,adjList);
		}
		return;
	}
	
	static public class Edge implements Comparable<Edge> 
	{
		int A, B, w;
		
		public Edge(int A, int B, int w) 
		{
			this.A = A;
			this.B = B;
			this.w = w;
		}
		
		public int compareTo(Edge e) 
		{
			if (w != e.w) {return w < e.w ? -1 : 1;} 
			else {return 0;}
		}
	}
	
	public static void main(String[] args) throws IOException
	{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		String line;
		ArrayList<ArrayList<Edge>> adjList = new ArrayList<ArrayList<Edge>>();
		int count =0;
		while((line = in.readLine())!=null)
		{
			if (line.trim().length() == 0) continue;
			count++;
			adjList.clear();
			Arrays.fill(isVisited, 0);
			StringTokenizer st = new StringTokenizer(line);
			int n = Integer.parseInt(st.nextToken());
			int junction[] = new int[n];
			for(int i=0; i<n; i++)
			{
				junction[i] = Integer.parseInt(st.nextToken());
				ArrayList<Edge> buf = new ArrayList<Edge>();
				adjList.add(buf);
			}
			
			//parsing roads
			st = new StringTokenizer(in.readLine());
			int r = Integer.parseInt(st.nextToken());
			for(int i=0; i<r; i++)
			{
				st = new StringTokenizer(in.readLine());
				int A = Integer.parseInt(st.nextToken());
				int B = Integer.parseInt(st.nextToken());
				
				//make Edge
				int diff = junction[B-OFFSET] - junction[A-OFFSET];
				int busyness = diff*diff*diff;
				Edge edge = new Edge(A-OFFSET,B-OFFSET,busyness);
				ArrayList<Edge> AL = adjList.get(A-OFFSET);
				AL.add(edge);
			}
			
			//Single source shortest path
			//BellMan-Ford
			Arrays.fill(dist, INF);
			dist[0]=0;
			dist = Bellman_Ford(n,dist,adjList);
			
			sb.append("Set #").append(count).append("\n");
			//parsing destinations
			st = new StringTokenizer(in.readLine());
			int q = Integer.parseInt(st.nextToken());
			for(int i=0; i<q; i++)
			{
				st = new StringTokenizer(in.readLine());
				int endJunct = Integer.parseInt(st.nextToken());
				if(dist[endJunct-OFFSET]<3 || dist[endJunct-OFFSET]==INF || dist[endJunct-OFFSET]==NEGINF) {sb.append("?");}
				else {sb.append((int)dist[endJunct-OFFSET]);}
				sb.append("\n");
			}			
		}
		System.out.print(sb);
		
	}

}