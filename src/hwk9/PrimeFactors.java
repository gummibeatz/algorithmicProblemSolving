package hwk9;

import java.util.*;
import java.io.*;

public class PrimeFactors
{
	final static int MAXNUM = 46500;
	static boolean[] isPrime = new boolean[MAXNUM];
	
	public static boolean[] sieveOfEras(boolean[] isPrime,int num)
	{
		for(int i=3; i<(int)Math.sqrt(num); i+=2)
		{
			if(isPrime[i])
			{
				for(long j = i; i*j<isPrime.length; j++)
				{
					isPrime[(int)(i*j)] = false;
				}
			}
		}
		return isPrime;
	}
	
	public static int findNextPrime(boolean[] isPrime, int currentNum)
	{
		for(int i=currentNum+1; i<isPrime.length; i++)
		{
			if(isPrime[i]) return i;
		}
		return -1;
	}
	
	public static void findFactors(StringBuilder sb, boolean[] isPrime, int num, boolean isFirst)
	{
		int mult = 2;
		while(num>1)
		{
			while(num%mult == 0)
			{
				if(isFirst) 
				{
					sb.append(mult);
					isFirst = false;
				}
				else sb.append(" x ").append(mult);
				num/=mult;
			}
			mult = findNextPrime(isPrime, mult);
			if (mult<0) 
			{
				if(isFirst)	sb.append(num);
				else sb.append(" x ").append(num);
				break;
			}
		}
	}
	
	public static void main(String[] args) throws IOException
	{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		Arrays.fill(isPrime, true);
		sieveOfEras(isPrime,MAXNUM);
		String line;
		while((line = in.readLine())!=null)
		{
			if (line.trim().length() == 0) continue;
			StringTokenizer st = new StringTokenizer(line);
			int num = Integer.parseInt(st.nextToken());
			if(num == 0) break;
			boolean isFirst = true;
			sb.append(num).append(" = ");
			if(num <0)
			{
				num*=-1;
				sb.append(-1);
				isFirst = false;
			}
			findFactors(sb,isPrime,num,isFirst);
			sb.append("\n");
		}
		System.out.print(sb);
	}

}