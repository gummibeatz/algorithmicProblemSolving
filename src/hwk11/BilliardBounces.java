package hwk11;

import java.io.*;
import java.util.*;

public class BilliardBounces
{
	final static double epsilon = 0.000000000001;
	
	public static int calc(double dist, int L)
	{
		
		double ans = (dist-L/2)/L;
		if(ans<0 || ans<epsilon) return 0;
		else return 1+(int)Math.floor((ans));
	}
	
	public static void main(String[] args) throws IOException 
	{
		BufferedReader in = new BufferedReader( new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		String line;
		while((line=in.readLine())!=null)
		{
			StringTokenizer st = new StringTokenizer(line);
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int A = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			if(a==0 && b==0 && v==0 && A==0 && s==0) break;
			double ddist = (v*s)/2; 
			double hdist = Math.cos(Math.toRadians(A))*ddist;
			double vdist = Math.sin(Math.toRadians(A))*ddist;
			sb.append(calc(hdist,a)).append(" ").append(calc(vdist,b)).append("\n");
		}
		System.out.print(sb);

	}

}