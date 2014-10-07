package hwk3;

import java.util.*;
import java.io.*;

public class WhereIsTheMarble {

	/**
	 * @param args
	 * @throws IOException 
	 */
	
	public static int leastAnt(int antpos[], double midpt, int length)
	{
		Arrays.sort(antpos);
		double min = Integer.MAX_VALUE;
		int ant = -1;
		for(int i =0; i<antpos.length; i++)
		{
			double dist = Math.abs(midpt-antpos[i]);
			if(dist<min)
			{
				min = dist;
				ant = i;
			}
		}
		if(antpos[ant]>midpt)
		{
			return length - antpos[ant];
		}
		return antpos[ant];
	}
	
	public static int mostAnt(int antpos[], double length)
	{
		int lastIdx = antpos.length-1;
		Arrays.sort(antpos);
		if(antpos[lastIdx]>(length-antpos[0]))
		{
			return antpos[lastIdx];
		}
		return (int) (length-antpos[0]);
	}

	
	
	public static void main(String[] args) throws IOException 
	{
		StringBuilder sb = new StringBuilder();
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		int length, numants, least, most;
		int c = Integer.parseInt(st.nextToken());
		for(int i=0; i <c; i++)
		{
			 if(st.hasMoreTokens())
			 {
				 length = Integer.parseInt(st.nextToken());
			 }
			 else
			 {
				 st = new StringTokenizer(in.readLine());
				 length = Integer.parseInt(st.nextToken());
			 }
			 if(st.hasMoreTokens())
			 {
				 numants = Integer.parseInt(st.nextToken());
			 }
			 else
			 {
				 st = new StringTokenizer(in.readLine());
				 numants = Integer.parseInt(st.nextToken());
			 }
			 
			 int[] antpos = new int[numants];
			 double midpt = length/2.0;
			 
			 for(int j=0;j<numants;j++)
			 {
				 if(!st.hasMoreTokens())
				 {
					st = new StringTokenizer(in.readLine()); 
				 }
				 antpos[j]=Integer.parseInt(st.nextToken());
			 }
				 least = leastAnt(antpos, midpt, length);
				 most = mostAnt(antpos, length);

			 sb.append(least);
			 sb.append(' ');
			 sb.append(most);
			 sb.append('\n');			
		}
		System.out.print(sb);
	}

}