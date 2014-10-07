package hwk4;

import java.io.*;
import java.util.*;

public class IngenuousCubrency 
{

	/**
	 * @param args
	 */
	static long[][] memo = new long[22][10000];
	static long count(int amount, int last)
	{
		if(amount==0)
		{return 1;}
		else if(amount <0 || last == 0 )
		{return 0;}
		else if (memo[last][amount]!=0)
		{return memo[last][amount];}
		else
		{
			int new_amt = amount - (last*last*last);
			memo[last][amount] += count(amount, last-1) + count(new_amt, last);
			return memo[last][amount];
		}

	}
	
	
	static long count(int amount)
	{
		return count(amount,21);
	}
	public static void main(String[] args) throws IOException
	{
		// TODO Auto-generated method stub
		StringBuilder sb = new StringBuilder();
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line;
		while(((line = in.readLine())!= null) && !line.equals(""))
		{
			for(long[] row: memo)
			{
				Arrays.fill(row, 0);
			}
			StringTokenizer st = new StringTokenizer(line);
			int amount = Integer.parseInt(st.nextToken());
			sb.append(count(amount));
			sb.append('\n');
		}
		System.out.print(sb);
	}

}