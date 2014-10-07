package hwk3;

import java.io.*;
import java.util.*;

public class BlockVoting
{

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException 
	{
		StringBuilder sb = new StringBuilder();
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		int cases = Integer.parseInt(st.nextToken());
		for(int i =0; i<cases; i++)
		{
			st = new StringTokenizer(in.readLine());
			int numParties = Integer.parseInt(st.nextToken());
			int hTotalVotes = 0;
			int[] parties = new int[numParties];
			for(int j=0;j<numParties;j++)
			{
				int votes = Integer.parseInt(st.nextToken());
				parties[j] = votes;
				hTotalVotes = hTotalVotes + votes;
			}
			int[] partycount = new int[numParties];
			hTotalVotes = hTotalVotes/2;
			for(int mask=0; mask<(1<<numParties); mask++)
			{
				int[] partycheck = new int[numParties];
				int sum = 0;
				int flag = 0;
				for(int j=0; j< numParties;j++)
				{
					if(((mask>>j)& 1)== 1)
					{
						sum = sum + parties[j];
						partycheck[j] = 1;
						if(sum>hTotalVotes)
						{
							flag++;
						}
					} 
				}
				if(flag >0)
				{
					for(int p =0; p <numParties; p++)
					{
						if(partycheck[p] ==1)
						{
							if(sum-parties[p]<=hTotalVotes)
							{
								partycount[p]++;
							}
						}
					}
				}
			}
			for(int j=0; j< numParties;j++)
			{
				sb.append("party ");
				sb.append(j+1);
				sb.append(" has power index ");
				sb.append(partycount[j]);
				sb.append('\n');
			}
			sb.append('\n');
		}
		System.out.print(sb);
	}

}