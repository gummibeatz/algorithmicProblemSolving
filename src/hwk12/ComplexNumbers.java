package hwk12;

import java.io.*;
import java.util.*;

public class ComplexNumbers 
{
	
	static final double EPS = 1e-9;
	static class Comp implements Comparator<double[]>
	{
		@Override
		public int compare(double[] o1, double[] o2)
		{
			if (Math.abs(o1[0]-o2[0]) > EPS) return o1[0] < o2[0] ? 1 : -1;
			if (Math.abs(o1[1]-o2[1]) > EPS) return o1[1] < o2[1] ? 1 : -1;
			return 0;
		}		
	}
	
	static String[] format(double[] v)
	{
		String as = String.format("%.03f",v[0]), bs = String.format("%+.03f",v[1]);
		if (as.equals("-0.000")) as = "0.000";
		if (bs.equals("-0.000")) bs = "+0.000";
		return new String[]{as,bs};
	}
	
	static int findSplitIdx(String complex)
	{
		for(int i=1; i<complex.length(); i++)
		{
			if(complex.charAt(i)>'9' || complex.charAt(i)<'0')
			{
				return i;
			}
		}
		return -1;
	}

	public static void main(String[] args) throws IOException
	{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in)); 
		StringBuilder sb = new StringBuilder();
		String line;
		int ct=0;
		boolean isFirst = true;
		while((line=in.readLine())!=null && line.length()!=0)
		{
			int a,b;
			ct++;
//			if(isFirst) {isFirst=false;}
//			else {sb.append("\n");}
			StringTokenizer st = new StringTokenizer(line);
			String complex = st.nextToken();
			int n = Integer.parseInt(st.nextToken());
			int idx = findSplitIdx(complex);
			a = Integer.parseInt(complex.substring(0,idx));
			b = Integer.parseInt(complex.substring(idx,complex.length()-1));
			double nR = Math.pow(Math.sqrt(a*a+b*b),1.0/n);
			double theta = Math.atan2(b,a);
			double[][] vals = new double[n][2];
			for(int i=0; i<n; i++)
			{
				double nTheta = (theta+2*Math.PI*i)/n;
				vals[i][0] = nR*Math.cos(nTheta);
				vals[i][1] = nR*Math.sin(nTheta); 
			}
			
			Arrays.sort(vals,new Comp());
			sb.append("Case ").append(ct).append(":\n");
			for(int i=0; i<n; i++)
			{
				String[] value = format(vals[i]);
				sb.append(value[0]).append(value[1]).append("i\n");
			}
			sb.append("\n");
		}
		System.out.print(sb);
	}

}