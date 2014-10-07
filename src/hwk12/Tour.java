package hwk12;

import java.io.*;
import java.text.*;
import java.util.*;


public class Tour 
{
	public static final int xIdx = 0;
	public static final int yIdx = 1;
	static final double EPS = 1e-9;
	public static final double[][]memo2D = new double[1000][1000]; // fix it later
	
	/** Distance from a to b */
	static double dist(int pt1, int pt2, double[][]points)
	{ 
		double xdist = points[pt1][xIdx] - points[pt2][xIdx];
		double ydist = points[pt1][yIdx] - points[pt2][yIdx];
		return Math.hypot(xdist,ydist); 
	}
	
	public static double BitonicTSP(int p1, int p2, double[][]points)
	{
		int v = 1 + Math.max(p1, p2);
		if(v == points.length-1) {return dist(p1,v,points) + dist(v,p2,points); }
		if(memo2D[p1][p2] > -0.5) {return memo2D[p1][p2];}
		return memo2D[p1][p2] = Math.min(
				dist(p1,v,points) + BitonicTSP(v,p2,points),
				dist(v,p2,points) + BitonicTSP(p1,v,points));
	}
	
	public static void main(String[] args) throws IOException 
	{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		String line;
		DecimalFormat df = new DecimalFormat("#.00");
		while((line = in.readLine())!=null)
		{
			if(line.trim().length()==0) continue;
			StringTokenizer st = new StringTokenizer(line);
			int setSize = Integer.parseInt(st.nextToken());
			double[][] points = new double[setSize][2];
			//clear memo
			for(int i=0; i<setSize; i++)
			{
				for(int j=0; j<setSize; j++)
				{
					memo2D[i][j] = -100;
				}
			}
			for(int i=0; i<setSize; i++)
			{
				while(!(st = new StringTokenizer(in.readLine())).hasMoreTokens()){}
				points[i][xIdx] = Integer.parseInt(st.nextToken());
				points[i][yIdx] = Integer.parseInt(st.nextToken());
			}
			sb.append((df.format(BitonicTSP(0,0,points)))).append("\n");
			
		}
		System.out.print(sb);
		
	}

}