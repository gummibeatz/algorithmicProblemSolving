package hwk9;

import java.util.*;
import java.io.*;

public class ExclusivelyEdible 
{

	public static void winner(int m, int n, int r, int c, StringBuilder sb)
	{
		if ((r^c^(m-r-1)^(n-c-1))== 0) sb.append("Hansel\n");
		else sb.append("Gretel\n");
	}

	
	public static void main(String[] args) throws IOException
	{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();			
		StringTokenizer st = new StringTokenizer(in.readLine());
		int t = Integer.parseInt(st.nextToken());
		for(int i=0; i<t; i++)
		{
			st = new StringTokenizer(in.readLine());
			int m = Integer.parseInt(st.nextToken());
			int n = Integer.parseInt(st.nextToken());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			winner(m,n,r,c,sb);
		}
		System.out.print(sb);
	}

}