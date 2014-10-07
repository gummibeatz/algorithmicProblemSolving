package hwk6;

import java.util.*;
import java.io.*;

public class OrderingTasks
{

	/**
	 * @param args
	 */
	static ArrayList<Integer> ans = new ArrayList<Integer>();
	final static int NOTVISITED =0;
	final static int VISITED = -1;
	public static int[] DFS(HashMap<Integer, ArrayList<Integer>>adjList, int node, int[]visited)
	{
		visited[node-1] = VISITED;
		if(adjList.get(node)!=null)
		{
			for(int i =0; i<adjList.get(node).size(); i ++)
			{
				int child = adjList.get(node).get(i);
				if(visited[child-1]==NOTVISITED)
				{
					visited = DFS(adjList,child, visited);
				}
			}
		}
		ans.add(node);
		return visited;
	}
	
	
	
	
	public static void main(String[] args) throws IOException
	{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line;
		StringBuilder sb = new StringBuilder();
		
		while((line = in.readLine())!=null)
		{
			StringTokenizer st = new StringTokenizer(line);
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			int[] visited = new int[n];
			Arrays.fill(visited, 0);
			if(n == 0 && m ==0) break;
			HashMap<Integer, ArrayList<Integer>> adjList = new HashMap<Integer,ArrayList<Integer>>();
			ans.clear();
			for(int i=0; i < m; i++)
			{
				st = new StringTokenizer(in.readLine());
				int pre = Integer.parseInt(st.nextToken());
				int post = Integer.parseInt(st.nextToken());
				ArrayList<Integer> AL = adjList.get(pre);
				if(AL == null)
				{
					AL = new ArrayList<Integer>();
					adjList.put(pre, AL);
				}
				AL.add(post);
			}
			//try out each node
			for(int i=1; i<n+1; i++)
			{
				if (adjList.containsKey(i) && (visited[i-1] == NOTVISITED))
				{
					visited = DFS(adjList,i,visited);
				}
			}
			
			boolean isFirst = true;
			for(int i=ans.size()-1;i>=0;i--)
			{
				if(isFirst) 
				{
					isFirst = false;
					sb.append(ans.get(i));
				}
				else 
				{
					sb.append(" ").append(ans.get(i));
				}
			}
			for(int i=1; i<n+1; i++)
			{
				if(visited[i-1] == NOTVISITED )
				{
					if(isFirst) 
					{
						isFirst = false;
						sb.append(i);
					}
					else sb.append(" ").append(i);
				}
			}
			sb.append("\n");
		}
		System.out.print(sb);
	}

}