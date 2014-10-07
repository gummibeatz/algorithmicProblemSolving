package hwk7;

import java.io.*;
import java.util.*;


public class InspectorsDilemma 
{
	
	final static int VISITED =1;
	final static int CONNECTED = 1;
	final static int MAXCITIES = 1000;
	final static int OFFSET = 1;
	public static int edges;
	public static int numOddV;
	public static int[] isVisited = new int[MAXCITIES];
	
	public static void DFS(int node, int[] isVisited, ArrayList<ArrayList<Integer>> adjList)
	{

		ArrayList<Integer> AL = adjList.get(node);
//		System.out.printf("AL.get(first item) is %d\n",AL.get(0));
//		System.out.printf("node is %d\n",node);
		if(isVisited[node] ==VISITED)	{return;}
		isVisited[node] = VISITED;
//		System.out.printf("AL.size is %d\n",AL.size());
		if(AL.size()%2 == 1)	{numOddV++;}
		for(int i=0; i<AL.size(); i++)
		{
			if(isVisited[AL.get(i)]!=VISITED)
			{
				edges++;
				DFS(AL.get(i),isVisited,adjList);
			}
		}
		return;
	}
	
	public static int edgeCount(int edges, int numOddV)
	{
		int totalEdges =0;
		if (numOddV>0){totalEdges += numOddV/2-1;}
		return totalEdges;
	}
	public static void main(String[] args) throws IOException
	{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		String line;
		int count =0;
		ArrayList<ArrayList<Integer>> adjList = new ArrayList<ArrayList<Integer>>();
		while((line=in.readLine())!=null)
		{
			adjList.clear();
			if(line.trim().length() ==0) continue;
			count++;
			Arrays.fill(isVisited, 0);
			
			StringTokenizer st = new StringTokenizer(line);
			int V = Integer.parseInt(st.nextToken()); // number of cities
			int E = Integer.parseInt(st.nextToken()); // number of highways
			int T = Integer.parseInt(st.nextToken()); // time to travel a highway

			if(V==0 && E ==0 && T==0) break;
			for(int i=0; i<V; i++)
			{
				ArrayList<Integer> buf = new ArrayList<Integer>();
				adjList.add(buf);
			}
			ArrayList<Integer> connection = new ArrayList<Integer>();
			for(int i =0; i<E; i++)
			{
				st = new StringTokenizer(in.readLine());
				int city1 = Integer.parseInt(st.nextToken());
				int city2 = Integer.parseInt(st.nextToken());
				connection = adjList.get(city1-OFFSET);
				connection.add(city2-OFFSET);
				connection = adjList.get(city2-OFFSET);
				connection.add(city1-OFFSET);
			}
			
			int graphCount = 0;
			int totalEdges = 0;
			//check for connected components
			for(int i=0; i<V; i++)
			{
				numOddV =0;
				edges = 0;
				ArrayList<Integer> AL = adjList.get(i);
				for(int j=0; j <AL.size(); j++)
				{
					int node = AL.get(j);
					if(isVisited[node] != VISITED)
					{
						graphCount++;
					}
					DFS(node,isVisited,adjList);
				}
				if(edges>0){totalEdges+=edgeCount(edges,numOddV);}
//				System.out.printf("numOddV is %d\n",numOddV);
//				System.out.printf("edges is %d\n" ,edges);
			}
//			System.out.println(graphCount);
			if(graphCount>0){totalEdges+=graphCount-1;}
//			System.out.println(E);
			totalEdges+=E;
			sb.append("Case ").append(count).append(": ").append(totalEdges*T).append("\n");
// for more than one connected graphs ->(odd degree nodes/2 + edges) - 1
// for just one connected graph. edges 
// 		
//			
//	
			//clear values we used
			
		}
		//while ended
		System.out.print(sb);
	}

}