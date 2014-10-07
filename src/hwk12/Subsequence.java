package hwk12;

import java.io.*;
import java.util.*;

public class Subsequence 
{

	public static int slidingWindow(int[] seq, int S)
	{
		int windowStart = 0;
		int sum =0;
		int minLen = 1<<30;
		for(int i=0; i<seq.length; i++)
		{
			sum+=seq[i];
			if(sum>=S)
			{
				minLen = Math.min(minLen, i-windowStart);
				for(int j=windowStart+1; j<i; j++)
				{
					if(sum-seq[j-1]>=S)
					{
						sum-= seq[j-1];
						windowStart = j;
						minLen = Math.min(minLen, i-windowStart);
					}
					else break;
				}
			}	
		}
		return (minLen==(1<<30)) ? 0:minLen+1;
	}
	
	public static void main(String[] args) throws IOException 
	{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		String line;
		while((line = in.readLine())!=null && !line.equals("yay"))
		{
			if(line.trim().length()==0) continue;
			StringTokenizer st = new StringTokenizer(line);
			int N = Integer.parseInt(st.nextToken());
			int S = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(in.readLine());
			int[] seq = new int[N];
			for(int i=0; i<N; i++)
			{
				seq[i] = Integer.parseInt(st.nextToken());
			}
			sb.append(slidingWindow(seq,S)).append("\n");
		}
		System.out.print(sb);
	}

}