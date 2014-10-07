package hwk4;

import java.io.*;
import java.util.*;

public class DivingForGold 
{
	/**
	 * @param args
	 * @throws IOException 
	 */
	  private static final int MAX_N = 31;
	  private static final int MAX_W = 1001;
	  private static int[][] memo = new int[MAX_N][MAX_W];
	
	static int solve(int[] ds, int[] vs, int w, int pos, int t)
	{
		if(t==0 || pos == ds.length){return 0;}
		else if(memo[pos][t] != -1){return memo[pos][t];}
		else if(3*ds[pos]*w > t){return memo[pos][t] = solve(ds,vs,w,pos+1, t);}
		else
		{
			return memo[pos][t] = Math.max(solve(ds,vs,w,pos + 1, t), vs[pos] + solve(ds,vs,w,pos + 1,t-3*ds[pos]*w));
		}
		
	}
	
	static int buildOut(int[] ds, int[] vs, int w, int pos, int t, StringBuilder sb)
	{
		if(pos == ds.length) return 0;
		int left = t-3*w*ds[pos];
		if(left>=0 && solve(ds,vs,w,pos+1,left) + vs[pos] == solve(ds,vs,w,pos,t))
		{
			sb.append(ds[pos]).append(' ').append(vs[pos]).append('\n');
			return buildOut(ds,vs,w, pos+1,left, sb)+1;			
		} else return buildOut(ds,vs,w, pos+1,t, sb);
	}
	
	public static void main(String[] args) throws IOException 
	{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line;
		boolean flag = false;
		while((line=in.readLine())!=null)
		{	
			if(!flag){flag = true;}
			else{System.out.println();}
			for(int[] row: memo)
			{
				Arrays.fill(row, -1);
			}
			StringTokenizer st = new StringTokenizer(line);
			while(!st.hasMoreTokens())
			{
				st = new StringTokenizer(in.readLine());
			}
			int t = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(in.readLine());
			int num_treasures = Integer.parseInt(st.nextToken());
			int[] ds = new int[num_treasures];
			int[] vs = new int[num_treasures];;
			for(int i=0; i<num_treasures; i++)
			{
				st = new StringTokenizer(in.readLine());
				ds[i] = Integer.parseInt(st.nextToken());
				vs[i] = Integer.parseInt(st.nextToken());
			}
			System.out.println(solve(ds,vs,w,0,t));
			StringBuilder sb = new StringBuilder();
			System.out.println(buildOut(ds,vs,w,0,t,sb));
			System.out.print(sb);
		}
	}

}