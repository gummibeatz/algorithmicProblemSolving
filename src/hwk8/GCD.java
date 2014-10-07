package hwk8;

import java.io.*;
import java.util.*;

public class GCD
{
	public static int gcd(int a, int b)
	{
		return b==0?a:gcd(b,a%b);
	}

	public static int func(int N)
	{
		int G =0; 
		for(int i=1; i<N; i++){
			for(int j=i+1; j<=N; j++){
				G+=gcd(i,j);
			}
		}
		return G;
	}
	
	public static void main(String[] args) throws IOException
	{
		StringBuilder sb = new StringBuilder();
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line;
		while((line=in.readLine())!=null)
		{
			if (line.trim().length() == 0) continue;
			StringTokenizer st = new StringTokenizer(line);
			int N = Integer.parseInt(st.nextToken());
			if(N==0) break;
			sb.append(func(N)).append("\n");
		}
		System.out.print(sb);
	}

}