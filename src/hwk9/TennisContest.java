package hwk9;

import java.util.*;
import java.io.*;
import java.text.*;

public class TennisContest
{
	public static long combination(int x, int r)
	{
		long ret = 1;
		for(int i=0; i<r; ++i)
		{
			ret*=x-i;
			ret/=1+i;
		}
		return ret;
	}
	
	public static double negBinCoef(int x, int r, double p, double fail)
	{
		return combination(x-1,r-1)*Math.pow(p, r)*Math.pow(fail, x-r);
	}
	public static void main(String[] args) throws IOException
	{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(in.readLine());
		DecimalFormat df = new DecimalFormat("0.00");
		while(!st.hasMoreTokens()){st = new StringTokenizer(in.readLine());}
		int cases = Integer.parseInt(st.nextToken());
		for(int i=0; i<cases; i++)
		{
			while(!st.hasMoreTokens()){st = new StringTokenizer(in.readLine());}
			int n = Integer.parseInt(st.nextToken());
			while(!st.hasMoreTokens()){st = new StringTokenizer(in.readLine());}
			double p = Double.parseDouble(st.nextToken());
			int matches = 2*n-1;
			double fail = 1.0-p;
			double winning=0;
			for(int j=n; j<=matches; j++)
			{
				winning += negBinCoef(j,n,p,fail);
			}
			sb.append(df.format(winning)).append("\n");
		}
		System.out.print(sb);
	}

}