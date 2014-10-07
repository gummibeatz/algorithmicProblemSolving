package hwk9;

import java.io.*;
import java.util.*;

public class CoconutsRevisted
{
	
	public static int solve(int coconuts)
	{
		int upperbd = (int)Math.sqrt(coconuts)+1;
		for(int i =upperbd; i>1; i--)
		{
			boolean broken = false;
			int temp = coconuts;
			for(int j=0; j<i; j++)
			{
				if(i*(temp/i)+1 == temp){temp= temp - (temp/i) -1;}
				else 
				{
					broken = true;
					break;
				}
			}
			if(broken) continue;
			if(temp%i == 0) return i;
		}
		return -1;
	}

	
	public static void main(String[] args) throws IOException 
	{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		String line;
		while((line=in.readLine())!=null)
		{
			if (line.trim().length() == 0) continue;
			StringTokenizer st = new StringTokenizer(line);
			int coconuts = Integer.parseInt(st.nextToken());
			if(coconuts<0) break;
			int ans = solve(coconuts);
			sb.append(coconuts).append(" coconuts, ");
			if(ans<0) sb.append("no solution\n");
			else sb.append(ans).append(" people and 1 monkey\n");
		}
		System.out.print(sb);

	}

}