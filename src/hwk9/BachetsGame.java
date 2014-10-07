package hwk9;

import java.util.*;
import java.io.*;


public class BachetsGame
{
	
	public static boolean winCalc(int n, int m, boolean[] winposit, int[] moves)
	{
		for(int i=1; i<=n; i++)
		{
			for(int j=0; j<m; j++)
			{
				if(i>=moves[j]){
					if(!winposit[i-moves[j]])
					{
						winposit[i] = true;
						break;
					}
				}
			}
		}
		return winposit[n];
	}

	
	public static void main(String[] args) throws IOException
	{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();		
		String line;
		while((line = in.readLine())!=null && !line.equals("yay"))
		{
			if (line.trim().length() == 0) continue;
			StringTokenizer st = new StringTokenizer(line);
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			boolean[] winposit = new boolean[n+1];
			int[] moves = new int[m];
			for(int i=0; i<m; i++)
			{
				moves[i] = Integer.parseInt(st.nextToken());
			}
			
			if(winCalc(n,m,winposit,moves)) sb.append("Stan wins\n");
			else sb.append("Ollie wins\n");
		}
		System.out.print(sb);
	}

}