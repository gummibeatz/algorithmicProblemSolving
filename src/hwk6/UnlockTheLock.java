package hwk6;

import java.io.*;
import java.util.*;

public class UnlockTheLock
{

	/**
	 * @param args
	 */
	final static int MOD = 10000;
	final static int NOTVISITED = -1;
	public static void main(String[] args) throws IOException
	{
		// TODO Auto-generated method stub
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		String line;
		int case_count = 0;
		while((line=in.readLine()) !=null)
		{
			case_count++;
			StringTokenizer st = new StringTokenizer(line);
			while(!st.hasMoreTokens())
			{
				st = new StringTokenizer(in.readLine());
			}
			int L = Integer.parseInt(st.nextToken());
			int U = Integer.parseInt(st.nextToken());
			int R = Integer.parseInt(st.nextToken());
			if( L==0 && U==0 && R==0) break;
			st = new StringTokenizer(in.readLine());
			int[] buttons = new int[R];
			for(int i =0; i <R; i++ )
			{
				buttons[i] = Integer.parseInt(st.nextToken());
			}
			int[] bpresses = new int[MOD];
			Arrays.fill(bpresses,NOTVISITED);
			Queue<Integer> queue = new ArrayDeque<Integer>();
			queue.add(L);
			bpresses[L] = 0;
			while(queue.size()>0)
			{
				int v = queue.poll();
				for(int i =0;i<R; i++)
				{
					int w = (v+buttons[i])%MOD;
					if(bpresses[w]== NOTVISITED)
					{
						queue.add(w);
						bpresses[w] = bpresses[v]+1;
					}
		
				}
			}
			sb.append("Case ").append(case_count).append(": ");
			if(bpresses[U]!=-1) sb.append(bpresses[U]);
			else sb.append("Permanently Locked");
			sb.append("\n");
		}
		System.out.print(sb);
	}

}