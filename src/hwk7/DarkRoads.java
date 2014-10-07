package hwk7;

import java.util.*;
import java.io.*;

public class DarkRoads
{
	final static int CONNECTIONIDX = 0;
	final static int LENGTHIDX = 1;
	
	static public class UnionFind 
	{                                              
		  private ArrayList<Integer> p, rank, setSize;
		  private int numSets;

		  public UnionFind(int N) 
		  {
			  p = new ArrayList<Integer>(N);
			  rank = new ArrayList<Integer>(N);
			  setSize = new ArrayList<Integer>(N);
			  numSets = N;
			  for (int i = 0; i < N; i++) 
			  {
				  p.add(i);
				  rank.add(0);
				  setSize.add(1);
			  }
		  }

		  public int findSet(int i) 
		  { 
			  if (p.get(i) == i) return i;
			  else 
			  {
				  int ret = findSet(p.get(i)); 
				  p.set(i, ret);
				  return ret; 
			  } 
		  }

		  public Boolean isSameSet(int i, int j) 
		  { 
			  return findSet(i) == findSet(j); 
		  }

		  public void unionSet(int i, int j) 
		  { 
			  if (!isSameSet(i, j)) 
			  { 
				  numSets--;
			  }
			  int x = findSet(i), y = findSet(j);
			  //rank for path compression
			  if (rank.get(x) > rank.get(y)) 
			  { 
				  p.set(y, x); 
				  setSize.set(x, setSize.get(x) + setSize.get(y)); 
			  }
			  else                           
			  { 
				  p.set(x, y); 
				  setSize.set(y, setSize.get(y) + setSize.get(x));
			  }
			  if (rank.get(x) == rank.get(y))   
			  {
				  rank.set(y, rank.get(y) + 1); 
			  }  
		  }
		  
		  public int numDisjointSets() 
		  { 
			  return numSets; 
		  }
		  
		  public int sizeOfSet(int i) 
		  { 
			  return setSize.get(findSet(i)); 
		  }
	}
	
	static public class Edge implements Comparable<Edge> 
	{
		int A, B, w;
		
		public Edge(int A, int B, int w) 
		{
			this.A = Math.min(A, B);
			this.B = Math.max(A, B);
			this.w = w;
		}
		
		public int getNodeA(){return A;}
		public int getNodeB(){return B;}
		public int getWeight(){return w;}
		
		public int compareTo(Edge e) 
		{
			if (w != e.w) {return w < e.w ? -1 : 1;} 
			else {return 0;}
		}
	}

	
	public static void main(String[] args) throws IOException
	{
		StringBuilder sb = new StringBuilder();
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line;
		ArrayList<Edge> edgeList = new ArrayList<Edge>();
		while((line=in.readLine())!=null)
		{
			edgeList.clear();
			StringTokenizer st = new StringTokenizer(line);
			int m = Integer.parseInt(st.nextToken());
			int n = Integer.parseInt(st.nextToken());
			//termination condition
			if(m == 0 && n ==0) break;

			//build the edgeList
			int totalcost = 0;
			for(int i=0; i <n; i++)
			{
				st = new StringTokenizer(in.readLine());
				int node1 = Integer.parseInt(st.nextToken());
				int node2 = Integer.parseInt(st.nextToken());
				int length = Integer.parseInt(st.nextToken());
				totalcost+=length;
				Edge edge = new Edge(node1,node2,length);
				edgeList.add(edge);
			}
			Collections.sort(edgeList);
			
			int mstCost =0;
			UnionFind UF = new UnionFind(m);
			
			for(int i=0; i < edgeList.size(); i++)
			{
				int node1 = edgeList.get(i).getNodeA();
				int node2 = edgeList.get(i).getNodeB();
				if(!UF.isSameSet(node1, node2))
				{
					mstCost += edgeList.get(i).getWeight();
					UF.unionSet(node1, node2);
				}
			}
			sb.append(totalcost-mstCost).append("\n");
		}
		System.out.print(sb);
	}

}