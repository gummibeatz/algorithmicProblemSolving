package hwk3;

import java.util.*;
import java.io.*;

public class DynamicFrog 
{

	/**
	 * @param args
	 */
	public static void main(String[] args) throws IOException
	{
		// TODO Auto-generated method stub
		StringBuilder sb = new StringBuilder();
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		int cases = Integer.parseInt(st.nextToken());
		for(int i=0; i<cases; i++)
		{
			st = new StringTokenizer(in.readLine());
			int stones = Integer.parseInt(st.nextToken());
			int dist = Integer.parseInt(st.nextToken());
			String[] stoneType = new String[stones];
			int[] stoneDist = new int[stones];
			int[] isUsed = new int[stones];
			st = new StringTokenizer(in.readLine());
			for(int j=0; j<stones; j++)
			{
				StringTokenizer ststones = new StringTokenizer(st.nextToken(),"-");
				stoneType[j]=ststones.nextToken();
				stoneDist[j] = Integer.parseInt(ststones.nextToken());
			}
			int max = 0;
			int tempdist = 0;
			//going there
			for(int j =0; j<stones; j++)
			{
				if(stoneType[j].equals("S"))
				{
					isUsed[j] =1;
				}
				if(j==0)
				{
					max = stoneDist[j]; 
				}
				if(j==stones-1)
				{
					tempdist = dist - stoneDist[j];
				}
				else if(j<stones-1)
				{
					if(stoneType[j+1].equals("S"))
					{
						if(j+2<stones)
						{
							tempdist = stoneDist[j+2] - stoneDist[j];
							j++;
						}
						else
						{
							tempdist = dist - stoneDist[j];
							j++;
						}
					}
				}
				else
				{
					tempdist = stoneDist[j+1] - stoneDist[j];
				}
				
				if(tempdist > max)
				{
					max = tempdist;
				}
			}
			//coming back
			int lastloc =0;
			for(int j=0; j <stones; j++)
			{
				if(isUsed[j]!=1)
				{
					tempdist = stoneDist[j]-lastloc;
					lastloc = stoneDist[j];
				}
				if(tempdist>max)
				{
					max = tempdist;
				}
			}
			if((dist-lastloc)>max)
			{
				max = dist-lastloc;
			}
			sb.append("Case ");
			sb.append(i+1);
			sb.append(": ");
			sb.append(max);
			sb.append('\n');
		}
		System.out.print(sb);
	}

}