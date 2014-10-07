package hwk1;

import java.io.*;
import java.util.*;

public class ThreeNPlusOne {
	//The 3n+1 problem
	
	static int maxCycle(int lowerbound, int upperbound){
		int max=0;
		for(int i = lowerbound; i<=upperbound; i++)
		{
			int count = 1;
			int n=i;
			while(n!=1)
			{
				count+=1;
				if(n%2 == 1)
				{
					n = 3*n+1;
				}
				else
				{
					n = n/2;
				}
			}
			if(count>max)
			{
				max = count;
			}
		}
		return max;
	}

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line;
		int bound1, bound2;
		int max=0;
		StringBuilder sb = new StringBuilder();
		while((line = in.readLine()) != null) // delete later
		{
			StringTokenizer st = new StringTokenizer(line);
			bound1 = Integer.parseInt(st.nextToken());
			sb.append(bound1);
			sb.append(' ');
			bound2 = Integer.parseInt(st.nextToken());
			sb.append(bound2);
			sb.append(' ');
			if(bound1<bound2)
			{
				max = maxCycle(bound1,bound2);
			}
			else
			{
				max = maxCycle(bound2,bound1);
			}
		
			sb.append(max);
			sb.append('\n');
		}
		System.out.print(sb);
	
	}

}