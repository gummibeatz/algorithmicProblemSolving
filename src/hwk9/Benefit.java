package hwk9;

import java.io.*;
import java.util.*;

public class Benefit 
{
	final static int MAXNUM = 3200;
	static boolean[] isPrime = new boolean[MAXNUM];
	
	
	public static int gcd(int a, int b) {return b==0?a:gcd(b,a%b);}
	public static int lcm(int a, int b) {return (a*b)/gcd(a,b);}
	
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
	
	public static int factor(boolean[] isPrime, int A, int C)
	{
		int ans = 1;
		int prime = 2;
		int temp = C;
		
		while(prime <= (int)Math.sqrt(temp))
		{
			int count1 = 0;
			int count2 = 0;
			while(C%prime==0)
			{
				C/=prime;
				count1++;
			}
			while(A%prime==0)
			{
				A/=prime;
				count2++;
			}
			if(count1>count2)
			{
				ans *= Math.pow(prime, count1);
			}
			prime = findNextPrime(isPrime,prime);
		}
		if(C>1 && A ==1) ans *= C;
		return ans;
	}
	
	public static void main(String[] args) throws IOException 
	{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(in.readLine());
		Arrays.fill(isPrime, true);
		sieveOfEras(isPrime, MAXNUM);
		int T = Integer.parseInt(st.nextToken());
		int A=0, C=0;
		for(int i=0; i<T; i++)
		{
			st = new StringTokenizer(in.readLine());
			A = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			if(C%A==0)
			{
				sb.append(factor(isPrime,A,C)).append("\n");
			}
			else sb.append("NO SOLUTION\n");
		}
		System.out.print(sb);
	}

}