package hwk7;

import java.io.*;
import java.util.*;

public class Commandos 
{

	final static int INF = 10000;
	
	public static int arrayMax(int[] array)
	{
		int max = Integer.MIN_VALUE;
		for(int i=0; i<array.length;i++)
		{
//			System.out.printf("array[%d] is %d\n",i,array[i]);
			if(max<array[i])
			{
				
				max = array[i];
			}
		}
		return max;
	}
	
	public static void main(String[] args) throws IOException
	{
		StringBuilder sb = new StringBuilder();
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		int cases = Integer.parseInt(st.nextToken());
		for(int q=0; q<cases; q++)
		{
			st = new StringTokenizer(in.readLine());
			int buildings = Integer.parseInt(st.nextToken());
			int[][] adjmat = new int[buildings][buildings];
			for(int[] row:adjmat)
			{
				Arrays.fill(row, INF);
			}
			st = new StringTokenizer(in.readLine());
			int roads = Integer.parseInt(st.nextToken());
			for(int p=0; p<roads; p++)
			{
				st = new StringTokenizer(in.readLine());
				int house1 = Integer.parseInt(st.nextToken());
				int house2 = Integer.parseInt(st.nextToken());
				adjmat[house1][house2] = 1;
				adjmat[house2][house1] = 1;
			}
			st = new StringTokenizer(in.readLine());
			int source = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			for(int k=0; k<buildings; k++)
			{
				for(int i=0; i<buildings; i++)
				{
					for(int j=0; j<buildings; j++)
					{
						adjmat[i][j] = Math.min(adjmat[i][j],adjmat[i][k]+adjmat[k][j]);
					}
				}
			}
			for(int k=0;k<buildings; k++)
			{
				adjmat[k][k] = 0;
			}
			int[] totalength = new int[buildings];
			for(int i=0; i<buildings; i++)
			{
				totalength[i] = adjmat[source][i];
				totalength[i] += adjmat[i][end];
			}
			sb.append("Case ").append(q+1).append(": ").append(arrayMax(totalength)).append("\n");
		}
		System.out.print(sb);
		
	}

}