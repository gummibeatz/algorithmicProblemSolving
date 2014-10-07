package hwk12;

import java.io.*;
import java.util.*;

public class KnightMoves 
{
	static final int SPACES = 64;
	static final char LETTEROFFSET = 'a';
	static final int COLIDX = 0;
	static final int ROWIDX = 1;
	static final int R2U1 = -6;
	static final int R2D1 = 10;
	static final int D2R1 = 17;
	static final int D2L1 = 15;
	static final int L2D1 = 6;
	static final int L2U1 = -10;
	static final int U2L1 = -17;
	static final int U2R1 = -15;
	
	public static void createChessboard(ArrayList<ArrayList<Integer>> adjList)
	{
		ArrayList<Integer> al;
		int idx;
		//R2U1
		for(int i=8; i<64; i+=8){
			for(int j=0; j<6; j++){
				idx = i+j;
				al = adjList.get(idx);
				al.add(idx+R2U1);
			}
		}
		//R2D1
		for(int i=0; i<56; i+=8){
			for(int j=0; j<6; j++){
				idx = i+j;
				al = adjList.get(idx);
				al.add(idx+R2D1);
			}
		}
		//D2R1
		for(int i=0; i<48; i+=8){
			for(int j=0; j<7; j++){
				idx = i+j;
				al = adjList.get(idx);
				al.add(idx+D2R1);
			}
		}
		//D2L1
		for(int i=0; i< 48; i+=8){
			for(int j=1; j<8; j++){
				idx = i+j;
				al = adjList.get(idx);
				al.add(idx+D2L1);
			}
		}
		//L2D1
		for(int i=0; i<56; i+=8){
			for(int j=2; j<8; j++){
				idx = i+j;
				al = adjList.get(idx);
				al.add(idx+L2D1);
			}
		}
		//L2U1
		for(int i=8; i<64; i+=8){
			for(int j=2; j<8; j++){
				idx = i+j;
				al = adjList.get(idx);
				al.add(idx+L2U1);
			}
		}
		//U2L1
		for(int i=16;i<64; i+=8){
			for(int j=1; j<8; j++){
				idx = i+j;
				al = adjList.get(idx);
				al.add(idx+U2L1);
			}
		}
		//U2R1
		for(int i=16; i<64; i+=8){
			for(int j=0; j<7; j++){
				idx = i+j;
				al = adjList.get(idx);
				al.add(idx+U2R1);	
			}
		}

	}
	
	
	//runs BFS and creates distance for every node.
	//returns the distance at the end node.
	public static int solve(ArrayList<ArrayList<Integer>> adjList, int startNode, int endNode)
	{
		if(startNode == endNode) return 0;
		Queue<Integer> queue = new ArrayDeque<Integer>();
		int[] dist = new int[SPACES];
		dist[startNode] =0;
		queue.add(startNode);
		while(!queue.isEmpty())
		{
			int node = queue.poll();
			for(int i=0; i<adjList.get(node).size(); i++)
			{
				int w = adjList.get(node).get(i);			
				if(dist[w]==0)
				{
					dist[w] = dist[node]+1;
					queue.add(w);
				}
			}
		}
		return dist[endNode];
	}
	
	
	public static void main(String[] args) throws IOException
	{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		ArrayList<ArrayList<Integer>> adjList = new ArrayList<ArrayList<Integer>>();
		for(int i=0; i<SPACES; i++) {adjList.add(new ArrayList<Integer>());}
		createChessboard(adjList);
		String line;
		while((line=in.readLine())!=null)
		{
			if(line.trim().length() ==0) continue;
			StringTokenizer st = new StringTokenizer(line);
			String starting = st.nextToken();
			String ending = st.nextToken();
			int startNode = starting.charAt(COLIDX)-LETTEROFFSET 
						  + 8*(Integer.parseInt(starting.substring(ROWIDX))-1);
			int endNode = ending.charAt(COLIDX)-LETTEROFFSET
						+ 8*(Integer.parseInt(ending.substring(ROWIDX))-1);
			sb.append("To get from ").append(starting).append(" to ").append(ending);
			sb.append(" takes ").append(solve(adjList,startNode,endNode));
			sb.append(" knight moves.\n");
		}
		System.out.print(sb);
	}

}