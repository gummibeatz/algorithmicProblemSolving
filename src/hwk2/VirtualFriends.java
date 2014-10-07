package hwk2;

import java.io.*;
import java.util.*;


public class VirtualFriends
{
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
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException 
	{
		BufferedReader in = new BufferedReader( new InputStreamReader(System.in));
		StringTokenizer st1 = new StringTokenizer(in.readLine());
		StringBuilder sb = new StringBuilder();
		int cases = Integer.parseInt(st1.nextToken());
		Map<String, Integer> names = new HashMap<String, Integer>();
		for(int i=0; i<cases; i++)
		{
			//instantiate unionfind
			names.clear();
			StringTokenizer st2 = new StringTokenizer(in.readLine());
			int connections = Integer.parseInt(st2.nextToken());
			UnionFind sets = new UnionFind(connections*2);
			int count =0;
			for(int j=0; j<connections; j++)
			{
				StringTokenizer friends = new StringTokenizer(in.readLine());
				String friend1 = friends.nextToken();
				String friend2 = friends.nextToken();
				if(names.get(friend1)==null)
				{
					names.put(friend1, count);
					count++;
				}
				if(names.get(friend2)==null)
				{
					names.put(friend2, count);
					count++;
				}
				if(!sets.isSameSet(names.get(friend1), names.get(friend2)))
				{
					sets.unionSet(names.get(friend1), names.get(friend2));
				}
				sb.append(sets.sizeOfSet(names.get(friend1)));
				sb.append('\n');
			}
			
		}
		System.out.print(sb);
	}

}