package hwk8;

import java.io.*;
import java.util.*;

public class MyTShirtSuitsMe
{
	static HashMap<String, Integer> volMap = new HashMap<String, Integer>();
	static HashMap<String, Integer> shirtMap = new HashMap<String, Integer>();
	static ArrayList<String> revVolMap = new ArrayList<String>();
	static ArrayList<String> revShirtMap = new ArrayList<String>();
	final static int SHIRTSPERLINE = 2;
	final static int numSizes = 6;
	
	static int getNum(HashMap<String,Integer> map, ArrayList<String> rev, String s)
	{
		if (!map.containsKey(s)) 
		{
			map.put(s, map.size());
			rev.add(s);
		}
		return map.get(s);
	}
	public static void main(String[] args) throws IOException
	{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		int cases = Integer.parseInt(st.nextToken());
		for(int i=0; i<cases; i++)
		{
			volMap.clear(); shirtMap.clear();
			revVolMap.clear(); revShirtMap.clear();
			st = new StringTokenizer(in.readLine());
			ArrayList<ArrayList<Integer>> input = new ArrayList<ArrayList<Integer>>();
			int numShirts = Integer.parseInt(st.nextToken());
			int numVols = Integer.parseInt(st.nextToken());
			for(int j=0; j<numVols; j++)
			{
				st = new StringTokenizer(in.readLine());
				ArrayList<Integer> al = new ArrayList<Integer>();
				al.add(getNum(volMap,revVolMap,Integer.toString(j)));
				for(int k=0; k<SHIRTSPERLINE; k++)
				{
					String size = st.nextToken();
					for(int l=0; l<numShirts/numSizes; l++)
					{
						String sizePlace = size.concat(Integer.toString(l));
						al.add(getNum(shirtMap,revShirtMap,sizePlace));
					}
					input.add(al);
				}
			}
			process(input);
		}	
	}
	
	static void addEdge(int u, int v, int cap, int[][] caps, ArrayList<Integer>[] adj)
	{
		if (caps[u][v] == 0 && caps[v][u] == 0)
		{
			adj[u].add(v); adj[v].add(u);
		} 
		caps[u][v] += cap;
	}
	static void process(ArrayList<ArrayList<Integer>> input)
	{
		int N = volMap.size(), P = shirtMap.size();
		int s = 0, t = 1, sN = 2, sP = sN+N, V = N+P+2;
		ArrayList<Integer>[] adj = new ArrayList[V];
		for (int i = 0; i < V; ++i) adj[i] = new ArrayList<Integer>();
		int[][] caps = new int[V][V];
		for (ArrayList<Integer> al : input)
		{
			int n = al.get(0)+sN;
			int p = 0;
			for(int j=1;j<al.size();j++)
			{
				p = al.get(j)+sP;
				addEdge(n,p,1,caps,adj);
			}
			
		}
		for (int n = 0; n < N; ++n) addEdge(s,n+sN,1,caps,adj);
		for (int p = 0; p < P; ++p) addEdge(p+sP,t,1,caps,adj);
		int flow = maxflow(adj,caps,s,t);
		if (flow>=N) System.out.println("YES");
		else System.out.println("NO");
	}
	static int maxflow(ArrayList<Integer>[] adj, int[][] caps, int source, int sink)
	{
		int ret = 0;
		while (true)
		{
			int f = augment(adj,caps,source,sink);
			if (f == 0) break;
			ret += f;
		}
		return ret;
	}
	static int augment(ArrayList<Integer>[] adj, int[][] caps, int source, int sink)
	{
		Queue<Integer> q = new ArrayDeque<Integer>();
		int[] pred = new int[adj.length];
		Arrays.fill(pred,-1);
		int[] f = new int[adj.length];
		pred[source] = source; f[source] = Integer.MAX_VALUE; q.add(source);
		while (!q.isEmpty())
		{
			int curr = q.poll(), currf = f[curr];
			if (curr == sink)
			{
				update(caps,pred,curr,f[curr]);
				return f[curr];
			}
			for (int i = 0; i < adj[curr].size(); ++i)
			{
				int j = adj[curr].get(i);
				if (pred[j] != -1 || caps[curr][j] == 0) continue;
				pred[j] = curr; f[j] = Math.min(currf, caps[curr][j]); q.add(j);
			}
		}
		return 0;
	}
	
	static void update(int[][] caps, int[] pred, int curr, int f)
	{
		int p = pred[curr];
		if (p == curr) return;
		caps[p][curr] -= f;  caps[curr][p] += f;
		update(caps,pred,p,f);
	}

}