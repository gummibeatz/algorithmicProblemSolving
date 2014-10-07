package hwk12;

import java.io.*;
import java.util.*;

public class BaseiMinus1 
{
	public static StringBuilder solve(int a,int b)
	{
		StringBuilder sb = new StringBuilder();
		if(a==0&&b==0) return sb.append(0);
		while(a!=0 || b!=0)
		{
			int c;
			if((a+b)%2!=0) {sb.append(1); c=1;}
			else {sb.append(0); c=0;}
			int temp_a = (a-c-b)/(-2);
			b = (a-c+b)/(-2);
			a = temp_a;
//			System.out.printf("a is: %d     b is: %d   c is: %d\n",a,b,c);
		}
		return sb.reverse();
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException 
	{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(in.readLine().trim());
		int ct = 0;
		for(int i=0; i<N; i++)
		{	
			ct++;
			StringTokenizer st = new StringTokenizer(in.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			sb.append("Case #").append(ct).append(": ");
			sb.append(solve(a,b)).append("\n");
		}
		System.out.print(sb);
	}
	
}