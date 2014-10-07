package hwk3;

import java.io.*;
import java.util.*;

public class TheMonkeyAndTheOiledBamboo 
{

	/**
	 * @param args
	 */
	public static int climb(int[]L, int strength)
	{
		for(int i=0; i<L.length; i++)
		{
//			System.out.println("i is "+i+", strength is "+strength);
			if(i == 0)
			{
				if(L[i]>strength) 
				{
					return -1;
				}
				else if(L[i] == strength) 
				{
					strength--;	
				}
			}
			else
			{
				if(L[i]>strength+L[i-1])
				{
					return -1;
				}
				else if(L[i]==strength+L[i-1])
				{
					strength--;
				}
			}
			
		}
		return 1;
	}
	public static int binTest(int[] L, int strength, int a, int b)
	{
		if(a == b)
		{
			return strength;
		}
		else
		{
			return climb(L,strength)<0?binTest(L,(strength+b+1)/2,strength+1,b):binTest(L,(strength+a)/2,a,strength);
		}
	}
	
	public static void main(String[] args) throws IOException
	{
		// TODO Auto-generated method stub
		StringBuilder sb = new StringBuilder();
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		while(!st.hasMoreTokens())
		{
			st = new StringTokenizer(in.readLine());
		}
		int cases = Integer.parseInt(st.nextToken());
		for(int i=0; i < cases; i++)
		{
			st = new StringTokenizer(in.readLine());
			while(!st.hasMoreTokens())
			{
				st = new StringTokenizer(in.readLine());
			}
			int n = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(in.readLine());
			while(!st.hasMoreTokens())
			{
				st = new StringTokenizer(in.readLine());
			}
			int[] rungs = new int[n];
			for(int j=0; j<n; j++)
			{
				rungs[j] = Integer.parseInt(st.nextToken());
			}
			int k = rungs[n-1]/2;
			sb.append("Case ");
			sb.append(i+1);
			sb.append(": ");
			sb.append(binTest(rungs, k, 0, rungs[n-1]));
			sb.append('\n');
		}
		System.out.print(sb);
	}

}