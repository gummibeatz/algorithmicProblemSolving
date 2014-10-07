package hwk3;

import java.io.*;
import java.util.*;

public class SumItUp 
{
	
	public static void main(String[] args) throws IOException
	{
		// TODO Auto-generated method stub
		StringBuilder sb = new StringBuilder();
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		while(true)
		{
			StringTokenizer st = new StringTokenizer(in.readLine());
			while(!st.hasMoreTokens())
			{
				st = new StringTokenizer(in.readLine());
			}
			int total = Integer.parseInt(st.nextToken());
			int n = Integer.parseInt(st.nextToken());
			if(total ==0 && n == 0)
			{
				break;
			}
			Integer arr[] = new Integer[n];
			for(int i =0; i<n; i++)
			{
				arr[i] = Integer.parseInt(st.nextToken());
			}
			Arrays.sort(arr, Collections.reverseOrder());
			List<String> AL = new ArrayList<String>();
			boolean NONE = true;
			for(int mask=0; mask < (1<<n); mask++)
			{
				StringBuilder sb2 = new StringBuilder();
				int temp = 0;
				boolean firstnum =true;
				for(int j=0; j<n; j++)
				{
					if(((mask>>j)&1)!=0)
					{
						if(firstnum)
						{
							temp = arr[j];
							sb2.append(arr[j]);
							firstnum =false;
						}
						else
						{
							temp = temp + arr[j];
							sb2.append("+");
							sb2.append(arr[j]);
						}
					}
				}
				if(temp == total)
				{
					sb2.append('\n');
					if(!AL.contains(sb2.toString()))
					{
						AL.add(sb2.toString());
					}
					NONE = false;
				}
			}
			sb.append("Sums of ");
			sb.append(total);
			sb.append(":\n");
			if(NONE)
			{
				sb.append("NONE\n");
			}
			else
			{
				Collections.sort(AL,Collections.reverseOrder());
				for(String s:AL)
				{
					sb.append(s);
				}
			}
						
		}
		System.out.print(sb);
		
		
	}

}